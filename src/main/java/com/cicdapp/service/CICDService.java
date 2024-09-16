package com.cicdapp.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cicdapp.entity.Users;
import com.cicdapp.exception.UserNotFoundException;
import com.cicdapp.repository.CICDRepo;
@Service
public class CICDService {
@Autowired
CICDRepo cicdRepo;
	public List<Users> getAllUsers() {
		
		return cicdRepo.findAll();
	}
	public Users getAllUsersById(int id) {
		
		Optional<Users> optionalUser=cicdRepo.findById(id);
		//return optionalUser.orElseThrow(() -> new UserNotFoundException("User not found with ID: " + id));
		if(optionalUser.isPresent())
			return optionalUser.get();
		else
			throw new UserNotFoundException("User not found with ID: " + id);
			
	}
	public void deleteUsersById(int id) {
		
		if(cicdRepo.existsById(id))
		{
			cicdRepo.deleteById(id);
			
			String result= "Deleted by " +id;
			System.out.println(result);
		}
		else
			throw new UserNotFoundException("User not found with ID: " + id);
	}
	public void deleteAllUsers() {
		
		cicdRepo.deleteAll();
		
	}
	public Users addUsers(Users users) {
		
		return cicdRepo.save(users);
	}
	public Users updateUsersById(int id, Users updatedUser) {
		
		 System.out.println("Updating user with ID: " + id);
        return cicdRepo.findById(id)
                .map(existingUser -> {
                    existingUser.setName(updatedUser.getName());
                    existingUser.setJobName(updatedUser.getJobName());
                    existingUser.setEmail(updatedUser.getEmail());
                    existingUser.setPhonenNo(updatedUser.getPhonenNo());
                    return cicdRepo.save(existingUser);
                })
                .orElseThrow(() -> new UserNotFoundException("User not found with ID: " + id));

	}

}
