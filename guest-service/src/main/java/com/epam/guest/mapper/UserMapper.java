package com.epam.guest.mapper;

import com.epam.guest.dto.UserDto;
import com.epam.guest.entity.User;

public interface UserMapper {
   public User convert(UserDto userDto);
   public UserDto convert(User user);
}