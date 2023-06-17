package service.userservice;

import dao.userdao.UserDAO;
import dao.userdao.UserDAOFromCSV;
import dao.userdao.UserDAOImpl;
import models.user.User;

import java.util.Optional;

public class UserServiceImpl implements UserService {

    UserDAO userDAO;

    public UserServiceImpl(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    @Override
    public Optional<User> getUserById(String id) {
        return userDAO.getUserById(id);
    }

    @Override
    public User[] getAllUsers() {
        return userDAO.getAllUsers();
    }
}
