package com.wwsis.microblog.dao;

import com.wwsis.microblog.model.User;

public interface UserDao {
    User getUserByUsername(String username); // Retrieve user by username
    void addUser(User user); // Register a new user
}