package com.cicdapp.component;

import org.springframework.boot.actuate.endpoint.annotation.Endpoint;
import org.springframework.boot.actuate.endpoint.annotation.ReadOperation;
import org.springframework.boot.actuate.endpoint.annotation.Selector;
import org.springframework.boot.actuate.endpoint.annotation.DeleteOperation;
import org.springframework.stereotype.Component;


import com.cicdapp.service.CICDService;



import com.cicdapp.entity.Users;

import java.util.List;


@Component
@Endpoint(id = "user-management")
public class UserManagementEndpoint {

    private final CICDService cicdService;


    public UserManagementEndpoint(CICDService cicdService) {
        this.cicdService = cicdService;
    }



    @ReadOperation
    public Users getUserById(@Selector int id) {
        return cicdService.getAllUsersById(id);
    }

    @ReadOperation
    public List<Users> getAllUsers() {
        return cicdService.getAllUsers();
    }

//http://localhost:8090/manage/user-management?id=1

    @DeleteOperation
    public void deleteUser(@Selector int id) {
        cicdService.deleteUsersById(id);
    }

    @DeleteOperation
    public void deleteAllUsers() {
        cicdService.deleteAllUsers();
    }

}
