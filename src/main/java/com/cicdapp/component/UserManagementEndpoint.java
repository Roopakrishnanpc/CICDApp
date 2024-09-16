//package com.CICD.component;
//
//import org.springframework.boot.actuate.endpoint.annotation.Endpoint;
//import org.springframework.boot.actuate.endpoint.annotation.ReadOperation;
//import org.springframework.boot.actuate.endpoint.annotation.WriteOperation;
//import org.springframework.boot.actuate.endpoint.annotation.DeleteOperation;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.stereotype.Component;
//import org.springframework.beans.factory.annotation.Autowired;
//
//import com.CICD.service.CICDService;
//import com.CICD.entity.Users;
//
//import java.util.List;
//
//@Component
//@Endpoint(id = "user-management")
//public class UserManagementEndpoint {
//
//    private final CICDService cicdService;
//
//    @Autowired
//    public UserManagementEndpoint(CICDService cicdService) {
//        this.cicdService = cicdService;
//    }
//
//    @ReadOperation
//    @RequestMapping(method = RequestMethod.GET, path = "/all")
//    public List<Users> getAllUsers() {
//        return cicdService.getAllUsers();
//    }
//
//    @ReadOperation
//    @RequestMapping(method = RequestMethod.GET, path = "/{id}")
//    public Users getUserById(@RequestParam int id) {
//        return cicdService.getAllUsersById(id);
//    }
//
//    @WriteOperation
//    @RequestMapping(method = RequestMethod.POST, path = "/add")
//    public Users addUser(@RequestParam Users user) {
//        return cicdService.addUsers(user);
//    }
//
//    @WriteOperation
//    @RequestMapping(method = RequestMethod.PUT, path = "/update/{id}")
//    public Users updateUser(@RequestParam int id, @RequestParam Users updatedUser) {
//        return cicdService.updateUsersById(id, updatedUser);
//    }
//
//    @DeleteOperation
//    @RequestMapping(method = RequestMethod.DELETE, path = "/delete/{id}")
//    public void deleteUser(@RequestParam int id) {
//        cicdService.deleteUsersById(id);
//    }
//
//    @DeleteOperation
//    @RequestMapping(method = RequestMethod.DELETE, path = "/deleteAll")
//    public void deleteAllUsers() {
//        cicdService.deleteAllUsers();
//    }
//}
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

//    @WriteOperation
////	@RequestMapping(
////    	    value = "/process", 
////    	    method = RequestMethod.POST)
//    public Users addUser(@RequestBody Users user) {
//
//        return cicdService.addUsers(user);
//    }
//
//    @WriteOperation
//
////    	@RequestMapping(
////    	    value = "/process", 
////    	    method = RequestMethod.POST)
//    public Users updateUser(@Selector int id, @RequestBody Users updatedUser) {
//        return cicdService.updateUsersById(id, updatedUser);
//    }
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
