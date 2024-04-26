package com.kahoot.kahoot.Service;

import com.kahoot.kahoot.Entity.User;
import java.util.List;

public interface UserServicess {
    User createUser(User user);
    List<User> getAllUsers();
    User getUserById(Long userId);
    User updateUser(Long userId, User user);
    void deleteUser(Long userId);
}
