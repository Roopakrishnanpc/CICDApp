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
		// TODO Auto-generated method stub
		return cicdRepo.findAll();
	}
	public Users getAllUsersById(int id) {
		// TODO Auto-generated method stub
		Optional<Users> optionalUser=cicdRepo.findById(id);
		//return optionalUser.orElseThrow(() -> new UserNotFoundException("User not found with ID: " + id));
		if(optionalUser.isPresent())
			return optionalUser.get();
		else
			throw new UserNotFoundException("User not found with ID: " + id);
			
	}
	public void deleteUsersById(int id) {
		// TODO Auto-generated method stub
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
		// TODO Auto-generated method stub
		cicdRepo.deleteAll();
		
	}
	public Users addUsers(Users users) {
		// TODO Auto-generated method stub
		return cicdRepo.save(users);
	}
	public Users updateUsersById(int id, Users updatedUser) {
		// TODO Auto-generated method stub
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
/*Optional<Users> optionalUser = cicdRepo.findById(id);

        // Check if the user is present
        if (optionalUser.isPresent()) {
            // Get the existing user
            Users existingUser = optionalUser.get();

            // Update the existing user details with new values
            existingUser.setName(updatedUser.getName());
            existingUser.setJobName(updatedUser.getJobName());
            existingUser.setEmail(updatedUser.getEmail());
            existingUser.setPhonenNo(updatedUser.getPhonenNo());

            // Save the updated user
            return cicdRepo.save(existingUser);
        } else {
            // Throw an exception if user is not found
            throw new UserNotFoundException("User not found with ID: " + id);
        }*/
}
