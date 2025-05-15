package com.harsh.ecommerce;

import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;
import java.util.List;


@Service
public class UserService {

    private Long userId = 1L;
    private List<User> userList = new ArrayList<>();

    public List<User> fetchAllUser(){
        return userList;
    }

    public void addUser(User user){
        if (user.getId() == null) {          // Check if id is missing
            user.setId(userId++);    // Assign new ID
        }
        userList.add(user);
    }
}
