package dao.userdao;

import models.user.User;

import java.util.List;
import java.util.Optional;

public interface UserDAO {

    void loadUsers();
    Optional<User> getUserById(String id);

    List<User> getAllUsers();
}
