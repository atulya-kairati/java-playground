package dao.userdao;

import models.user.User;

import java.util.Optional;

public interface UserDAO {

    void loadUsers();
    Optional<User> getUserById(String id);

    User[] getAllUsers();
}
