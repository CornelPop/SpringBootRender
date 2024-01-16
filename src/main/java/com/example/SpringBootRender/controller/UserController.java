package com.example.SpringBootRender.controller;

import com.example.SpringBootRender.dto.UserDto;
import com.example.SpringBootRender.model.Purse;
import com.example.SpringBootRender.model.User;
import com.example.SpringBootRender.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Controller
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/allUsers")
    public String getUsers(Model model){
        List<UserDto> userDtos = userService.getAllUsers();
        model.addAttribute("title", "Users");
        model.addAttribute("users", userDtos);
        return "user/index";
    }

    @GetMapping("/allUsers/{id}")
    public UserDto getUserById(@PathVariable Integer id){
        return userService.getUserById(id);
    }

    @GetMapping("createUser")
    public String renderCreateUser(Model model) {
        model.addAttribute("user", new User());
        return "user/create";
    }

    /*@PostMapping("createUser")
    public String processCreatePurse(@ModelAttribute User newUser, Model model) {
        userService.createUser(newUser);
        return "redirect:/";
    }*/

    @GetMapping("deleteUser")
    public String renderDeleteUser (Model model) {
        model.addAttribute("users", userService.getAllUsersNotDtoCuzIDontKnowToUseThatAtTheIfInUpdateUser());
        return "user/delete";
    }

    @PostMapping("deleteUser")
    public String processDeleteUser(@RequestParam(required = false) int[] userIds) {
        if (userIds != null) {
            for (int id : userIds) {
                userService.deleteUserById(id);
            }
        }
        return "redirect:/";
    }

    @GetMapping("updateUser")
    public String renderUpdateUser(Model model) {
        model.addAttribute("purseList", userService.getAllUsers());
        return "user/update";
    }

    @PostMapping("updateUser")
    public String processUpdateUser(@ModelAttribute User updatedUser,
                                         @RequestParam(required = false) int selectedId,
                                         @RequestParam(required = false) String username,
                                         @RequestParam(required = false) String firstName,
                                         @RequestParam(required = false) String lastName,
                                         @RequestParam(required = false) String email) {

        User userToUpdate = null;
        for (User user : userService.getAllUsersNotDtoCuzIDontKnowToUseThatAtTheIfInUpdateUser()) {
            if (user.getId() == selectedId) {
                userToUpdate = user;
                break;
            }
        }
        if (userToUpdate != null) {
            userToUpdate.setFirstName(firstName);
            userToUpdate.setUsername(username);
            userToUpdate.setLastName(lastName);
            userToUpdate.setEmailAddress(email);

            userService.updateUser(userToUpdate);
        }
        return "redirect:/";
    }

}
