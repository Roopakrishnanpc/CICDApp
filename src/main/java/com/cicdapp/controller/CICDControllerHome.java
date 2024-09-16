package com.cicdapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import com.cicdapp.entity.Users;
import com.cicdapp.service.CICDService;

@Controller
@RequestMapping("/")
public class CICDControllerHome {

    private static final String REDIRECT_HOME = "redirect:/";
    private static final String USERS = "users";
    private static final String USER = "user";
    private static final String MESSAGE = "message";
    private static final String USER_FOR_UPDATE = "userForUpdate";
    
    @Autowired
    private CICDService cicdservice;


    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute(USER, new Users()); // For add user form
        model.addAttribute(USERS, cicdservice.getAllUsers()); // List of users for displaying
        return "home";
    }

    @GetMapping("/getUsers")
    public String getUserById(@RequestParam("id") int id, Model model) {
        Users user = cicdservice.getAllUsersById(id);
        model.addAttribute(USER_FOR_UPDATE, user);
        return "updateUser"; // Redirect to the new JSP for updating user
    }

    @PostMapping("/updateUser")
    public String updateUser(@ModelAttribute(USER_FOR_UPDATE) Users user) {
        cicdservice.updateUsersById(user.getId(), user);
        return REDIRECT_HOME; 
    }

    @PostMapping("/addUsers")
    public String addUser(@ModelAttribute(USER) Users user, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "home"; // Show validation errors on the home page
        }
        cicdservice.addUsers(user); // Add the user
        model.addAttribute(USER, new Users()); // Clear form
        model.addAttribute(USERS, cicdservice.getAllUsers()); // Refresh list of users
        model.addAttribute(MESSAGE, "User added successfully.");
        return REDIRECT_HOME; 
    }

    @PostMapping("/deleteUsers")
    public String deleteUser(@RequestParam int id, Model model) {
        cicdservice.deleteUsersById(id);
        model.addAttribute(USER, new Users()); // Clear form
        model.addAttribute(USERS, cicdservice.getAllUsers()); // Refresh list of users
        model.addAttribute(MESSAGE, "User deleted successfully.");
        return REDIRECT_HOME; 
    }

    @PostMapping("/deleteAllUsers")
    public String deleteAllUsers(Model model) {
        cicdservice.deleteAllUsers();
        model.addAttribute(USER, new Users()); // Clear form
        model.addAttribute(USERS, cicdservice.getAllUsers()); // Refresh list of users
        model.addAttribute(MESSAGE, "All users deleted successfully.");
        return REDIRECT_HOME; 
    }
}
