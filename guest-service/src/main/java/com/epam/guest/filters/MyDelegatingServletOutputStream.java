package com.epam.guest.filters;

import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.ServletOutputStream;
import javax.servlet.WriteListener;

import org.springframework.util.Assert;

public class MyDelegatingServletOutputStream extends ServletOutputStream {

	private final OutputStream targetStream;

	public MyDelegatingServletOutputStream(OutputStream targetStream) {
		Assert.notNull(targetStream, "Target OutputStream must not be null");
		this.targetStream = targetStream;
	}

	/**
	 * Return the underlying target stream (never {@code null}).
	 */
	public final OutputStream getTargetStream() {
		return this.targetStream;
	}

	@Override
	public void write(int b) throws IOException {
		this.targetStream.write(b);
	}

	@Override
	public void flush() throws IOException {
		super.flush();
		this.targetStream.flush();
	}

	@Override
	public void close() throws IOException {
		super.close();
		this.targetStream.close();
	}

	@Override
	public boolean isReady() {
		return true;
	}

	@Override
	public void setWriteListener(WriteListener writeListener) {
		throw new UnsupportedOperationException();
	}

}