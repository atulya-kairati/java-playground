package dao.userdao;

import com.github.javafaker.Faker;
import models.user.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class UserDAOFaker implements UserDAO {

    private final List<User> users = new ArrayList<>();

    {
        loadUsers();
    }

    @Override
    public void loadUsers() {
        Faker faker = new Faker();

        for (int i = 0; i < 20; i++) {
            String name = faker.name().fullName();

            users.add(new User(name));
        }
    }

    @Override
    public Optional<User> getUserById(String id) {
        UUID uuid = UUID.fromString(id);
        return users.stream()
                .filter((user -> user.getId() == uuid))
                .findFirst();
    }

    @Override
    public List<User> getAllUsers() {
        return users;
    }
}
