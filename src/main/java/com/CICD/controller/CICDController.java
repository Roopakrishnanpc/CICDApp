package com.CICD.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.CICD.entity.Users;
import com.CICD.service.CICDService;

@Controller
@RequestMapping("/homepage")
public class CICDController {

	@Autowired
	CICDService cicdservice;
	@GetMapping("/users/getUsers")
	public ResponseEntity<List<Users>> getAllUsers()
	{
		List<Users> getusers=cicdservice.getAllUsers();
		return new ResponseEntity<>(getusers, HttpStatus.OK);
	}
	@GetMapping("/users/getUsersusers/{id}")
	public ResponseEntity<Users> getAllUsersById(@PathVariable int id)
	{
		Users getuserbyId=cicdservice.getAllUsersById(id);
		return new ResponseEntity<>(getuserbyId, HttpStatus.OK);
	}
	@PostMapping("/users/addUsers")
	public ResponseEntity<Users> getAllUsersById(@RequestBody Users users)
	{
		Users addUsers=cicdservice.addUsers(users);
		return new ResponseEntity<>(addUsers, HttpStatus.CREATED);
	}
	@PutMapping("/users/updateUsersusers/{id}")
	public ResponseEntity<Users> updateUsersById(@PathVariable int id, @RequestBody Users users)
	//    public String updateUser(@RequestParam int id, @RequestParam String name, @RequestParam String jobName, @RequestParam String email, @RequestParam Long phonenNo) {
	{
		Users updateUsersById=cicdservice.updateUsersById(id,users);
		return new ResponseEntity<>(updateUsersById, HttpStatus.OK);
	}
	@DeleteMapping("/users/deleteUsers/{id}")
	public ResponseEntity<Void> deleteUsersById(@PathVariable int id)
	{
		cicdservice.deleteUsersById(id);
		return new ResponseEntity<>( HttpStatus.ACCEPTED);
	}
	@DeleteMapping("/users/deleteAllUsers")
	public ResponseEntity<Void> deleteAllUsers()
	{
		cicdservice.deleteAllUsers();
		return new ResponseEntity<>( HttpStatus.OK);
	}
	
}
