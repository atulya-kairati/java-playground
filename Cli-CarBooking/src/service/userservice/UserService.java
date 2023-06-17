package service.userservice;

import models.user.User;

import java.util.Optional;

public interface UserService {
    Optional<User> getUserById(String id);

    User[] getAllUsers();
}
