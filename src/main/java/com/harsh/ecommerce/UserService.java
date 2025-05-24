package com.harsh.ecommerce;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private Long userId = 1L;
    private final List<User> userList = new ArrayList<>();

    public List<User> fetchAllUsers() {
        return userList;
    }

    public Optional<User> fetchSingleUserById(Long id) {
        return userList.stream()
                       .filter(user -> user.getId().equals(id))
                       .findFirst();
    }

    public boolean updateUserById(Long id, User updatedUser) {
        return userList.stream()
                       .filter(user -> user.getId().equals(id))
                       .findFirst()
                       .map(existingUser -> {
                           existingUser.setFirstName(updatedUser.getFirstName());
                           existingUser.setLastName(updatedUser.getLastName());
                           return true;
                       }).orElse(false);
    }

    public void addUser(User user) {
        if (user.getId() == null) {
            user.setId(userId++);
        }
        userList.add(user);
    }
}
