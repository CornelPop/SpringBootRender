package com.example.SpringBootRender.service;

import com.example.SpringBootRender.dto.UserDto;
import com.example.SpringBootRender.mapper.RoleMapper;
import com.example.SpringBootRender.mapper.UserMapper;
import com.example.SpringBootRender.model.RegistrationRequest;
import com.example.SpringBootRender.model.User;
import com.example.SpringBootRender.repository.RoleRepository;
import com.example.SpringBootRender.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{

    private final UserRepository userRepository;

    private final UserMapper userMapper;

    private final BCryptPasswordEncoder passwordEncoder;

    private final RoleRepository roleRepository;

    private final RoleMapper roleMapper;

    @Override
    public boolean checkEmail(String email) {
        return userRepository.existsByEmailAddress(email);
    }

    @Override
    public UserDto registerUser(RegistrationRequest registrationRequest) {
        User user = User.builder()
                .username(registrationRequest.getUsername())
                .firstName(registrationRequest.getFirstName())
                .lastName(registrationRequest.getLastName())
                .password(registrationRequest.getPassword())
                .emailAddress(registrationRequest.getEmailAddress())
                .role((roleRepository.findByRole("USER")))
                .build();




        return this.createUser(user);
    }

    public UserDto getLoginUser(){
        return userMapper.userEntityToDto(userRepository.findLoginUser().orElse(null));
    }

    public UserDto getUserById(Integer id){
        return userMapper.userEntityToDto(userRepository.findById(id).orElse(null));
    }

    public List<UserDto> getAllUsers(){
        return userMapper.userListEntityToDto(userRepository.findAll());
    }

    @Override
    public List<User> getAllUsersNotDtoCuzIDontKnowToUseThatAtTheIfInUpdateUser() {
        return userRepository.findAll();
    }

    public UserDto createUser(User user){
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userMapper.userEntityToDto(userRepository.save(user));
    }

    public UserDto updateUser(User user){
        return userMapper.userEntityToDto(userRepository.save(user));
    }

    public void deleteUser(User user){
        userRepository.delete(user);
    }

    public void deleteUserById(int id){
        userRepository.deleteById(id);
    }

    @Override
    public Optional<User> findById(int id) {
        Optional<User> user = userRepository.findById(id);
        return userRepository.findById(id);
    }
}
