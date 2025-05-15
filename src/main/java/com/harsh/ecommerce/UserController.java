package com.harsh.ecommerce;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;


@RestController
public class UserController {

       @Autowired // wired controller from the UserService
       private UserService userService;   // Use UserService from service layer

       private List<User> userList = new ArrayList<>();

       @GetMapping("/api/users")
       public  List<User> getAllUser(){
              return userService.fetchAllUser();    // Use Service Layer
       }

       /*
        @PostMapping("/api/users")
        public List<User> createUser(@RequestBody  User user){    // without using Service Layer
              return userService.addUser(user);
       }
        */

       @PostMapping("/api/users")
       public String createUser(@RequestBody  User user){       // using Service Layer
              userService.addUser(user);
              return "User added Successfully";
       }

}
