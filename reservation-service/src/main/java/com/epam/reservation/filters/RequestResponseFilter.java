package com.epam.reservation.filters;

import java.io.ByteArrayInputStream;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.ServletOutputStream;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;

import org.apache.commons.io.IOUtils;
import org.apache.commons.io.output.TeeOutputStream;
import org.springframework.stereotype.Component;

import com.epam.reservation.exception.InvalidPayloadException;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class RequestResponseFilter implements Filter {

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		MyCustomHttpRequestMapper requestWrapper = new MyCustomHttpRequestMapper((HttpServletRequest) request);
		String uri = requestWrapper.getRequestURI();
		String requestData = new String(requestWrapper.getByteArray());

		log.info("Requeust URI: {}", uri);
		log.info("Requeust Method: {}", requestWrapper.getMethod());
		log.info("Request Body {}: " + requestData);

		MyCustomHttpResponseWrapper responseWrapper = new MyCustomHttpResponseWrapper((HttpServletResponse) response);
		chain.doFilter(requestWrapper, responseWrapper);
		String responseData = new String(responseWrapper.getBaos().toByteArray());
		log.info("Response status: {}" + responseWrapper.getStatus());
		log.info("Response status: {}" + responseData);
	}

}

class MyCustomHttpRequestMapper extends HttpServletRequestWrapper {

	private byte[] byteArray;

	public MyCustomHttpRequestMapper(HttpServletRequest request) {
		super(request);
		try {
			byteArray = IOUtils.toByteArray(request.getInputStream());
		} catch (IOException e) {
			throw new InvalidPayloadException("Issue while reading request stream");
		}
	}

	@Override
	public ServletInputStream getInputStream() throws IOException {
		return new MyDelegatingServletInputStream(new ByteArrayInputStream(byteArray));
	}

	public byte[] getByteArray() {
		return byteArray;
	}
}

class MyCustomHttpResponseWrapper extends HttpServletResponseWrapper {

	private ByteArrayOutputStream baos = new ByteArrayOutputStream();

	private PrintStream printStream = new PrintStream(baos);

	public ByteArrayOutputStream getBaos() {
		return baos;
	}

	public MyCustomHttpResponseWrapper(HttpServletResponse response) {
		super(response);
	}

	@Override
	public ServletOutputStream getOutputStream() throws IOException {
		return new MyDelegatingServletOutputStream(new TeeOutputStream(super.getOutputStream(), printStream));
	}

	@Override
	public PrintWriter getWriter() throws IOException {
		return new PrintWriter(new TeeOutputStream(super.getOutputStream(), printStream));
	}

}
