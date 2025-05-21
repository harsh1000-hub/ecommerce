package com.harsh.ecommerce;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
public class UserService {

    private Long userId = 1L;
    private List<User> userList = new ArrayList<>();

    public List<User> fetchAllUsers(){
        return userList;
    }

    // fetch user per id
    public Optional<User> fetchSingleUserById(Long id) {
//        for (User user : userList) {
//            if (user.getId().equals(id)) {
//                return user;
//            }
//        }
//        return null;
          return userList.
                  stream().
                  filter(user -> user.getId().equals(id)).
                  findFirst();  // using Java Stream
    }

    // Method for update specific user
    public boolean updateUserById(Long id, User user){
        for (User el : userList) {
            if (el.getId().equals(id)) {
                el.setFirstName(user.getFirstName());
                el.setLastName(user.getLastName());
                return true;
            }
        }
        return false;
    }

    public void addUser(User user){
        if (user.getId() == null) {          // Check if id is missing
            user.setId(userId++);    // Assign new ID
        }
        userList.add(user);
    }
}
