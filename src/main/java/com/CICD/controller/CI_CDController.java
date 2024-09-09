package com.CICD.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import com.CICD.entity.Users;
import com.CICD.service.CICDService;

@Controller
//@RequestMapping("/home")
@RequestMapping("/")
public class CI_CDController {

    @Autowired
    private CICDService cicdservice;

    // Controller Method for Home
    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("user", new Users()); // For add user form
        //model.addAttribute("userForUpdate", new Users()); // Initialize for update
        model.addAttribute("users", cicdservice.getAllUsers()); // List of users for displaying
        return "home";
    }

//    @GetMapping("/getUsers/{id}")
//    public String getAllUsersById(@PathVariable int id, Model model) {
//        Users user = cicdservice.getAllUsersById(id);
//        model.addAttribute("userForUpdate", user);
//        model.addAttribute("users", cicdservice.getAllUsers()); // Ensure the list is also populated
//        return "home";
//    }
    @GetMapping("/getUsers")
    public String getUserById(@RequestParam("id") int id, Model model) {
        Users user = cicdservice.getAllUsersById(id);
        model.addAttribute("userForUpdate", user);
        return "updateUser"; // Redirect to the new JSP for updating user
    }

    @PostMapping("/updateUser")
    public String updateUser(@ModelAttribute("userForUpdate") Users user) {//,@RequestParam("id") int id) {
        cicdservice.updateUsersById(user.getId(),user);
        //return "home"; 
        //return "redirect:/home"; // Redirect to home after updating user
        return "redirect:/"; 
    }


    @PostMapping("/addUsers")
    public String addUser(@ModelAttribute("user") Users user, Model model) {
        cicdservice.addUsers(user); // Add the user
        model.addAttribute("user", new Users()); // Clear form
        model.addAttribute("users", cicdservice.getAllUsers()); // Refresh list of users
        model.addAttribute("message", "User added successfully.");
        return "home";
    }
//    @PostMapping("/updateUsers")
//    public String updateUser(@ModelAttribute("userForUpdate") Users user, BindingResult result, Model model) {
//        if (result.hasErrors()) {
//            // Handle validation errors
//            return "home";
//        }
//        cicdservice.updateUsersById(user.getId(), user);
//        model.addAttribute("userForUpdate", new Users()); // Clear form
//        model.addAttribute("users", cicdservice.getAllUsers()); // Refresh list of users
//        model.addAttribute("message", "User updated successfully.");
//        return "home";
//    }




    @PostMapping("/deleteUsers")
    public String deleteUser(@RequestParam int id, Model model) {
        cicdservice.deleteUsersById(id);
        model.addAttribute("user", new Users()); // Clear form
        model.addAttribute("users", cicdservice.getAllUsers()); // Refresh list of users
        model.addAttribute("message", "User deleted successfully.");
        return "home";
    }

    @PostMapping("/deleteAllUsers")
    public String deleteAllUsers(Model model) {
        cicdservice.deleteAllUsers();
        model.addAttribute("user", new Users()); // Clear form
        model.addAttribute("users", cicdservice.getAllUsers()); // Refresh list of users
        model.addAttribute("message", "All users deleted successfully.");
        return "home";
    }
}



//    @GetMapping("/getUsers")
//    public String getAllUsers(Model model) {
//        List<Users> users = cicdservice.getAllUsers(); // Get all users
//        model.addAttribute("users", users); // Use 'users' for the list of users
//        model.addAttribute("message", "User list retrieved successfully.");
//        return "home";
//    }

   