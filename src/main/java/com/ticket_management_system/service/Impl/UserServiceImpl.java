package com.ticket_management_system.service.Impl;

import com.ticket_management_system.converters.UserConverters;
import com.ticket_management_system.model.dao.UserDao;
import com.ticket_management_system.model.dto.UserDto;
import com.ticket_management_system.repository.UserRepository;
import com.ticket_management_system.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserConverters userConverters;
    @Autowired
    private UserRepository userRepository;

    @Override
    public void SaveSignup(UserDto userDto) {
        UserDao userDao = userConverters.toUserDao(userDto);
        userRepository.save(userDao);

    }

}
