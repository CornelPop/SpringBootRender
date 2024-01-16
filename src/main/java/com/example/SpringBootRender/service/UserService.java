package com.example.SpringBootRender.service;

import com.example.SpringBootRender.dto.UserDto;
import com.example.SpringBootRender.model.RegistrationRequest;
import com.example.SpringBootRender.model.User;

import java.util.List;
import java.util.Optional;

public interface UserService {

    boolean checkEmail(String email);

    UserDto registerUser(RegistrationRequest registrationRequest);

    UserDto getLoginUser();

    UserDto getUserById(Integer id);

    List<UserDto> getAllUsers();
    List<User> getAllUsersNotDtoCuzIDontKnowToUseThatAtTheIfInUpdateUser();

    UserDto createUser(User user);

    UserDto updateUser(User user);

    void deleteUser(User user);

    void deleteUserById(int id);

    Optional<User> findById(int id);
}
