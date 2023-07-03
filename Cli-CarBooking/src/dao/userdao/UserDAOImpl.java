package dao.userdao;

import models.user.User;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class UserDAOImpl implements UserDAO {
    private List<User> usersArray;

    {
        loadUsers();
    }

    @Override
    public void loadUsers() {
        usersArray = List.of(
                new User("Manus"),
                new User("Jukka Sarasti"),
                new User("Atul"),
                new User("Sand dan Glokta")
        );
    }

    @Override
    public Optional<User> getUserById(String id) {

        UUID uuid = UUID.fromString(id);

        return usersArray.stream()
                .filter(user -> user.getId().equals(uuid))
                .findFirst();
    }

    @Override
    public List<User> getAllUsers() {
        return usersArray;
    }
}
