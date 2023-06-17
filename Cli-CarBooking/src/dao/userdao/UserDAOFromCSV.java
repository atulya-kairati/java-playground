package dao.userdao;

import models.user.User;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class UserDAOFromCSV implements UserDAO {

    private User[] users;

    {
        loadUsers();
    }

    @Override
    public void loadUsers() {
        File file = new File("users.csv");
        List<User> userList = new ArrayList<>();
        try (Scanner fileScanner = new Scanner(file)) {
            while (fileScanner.hasNext()){
                String line = fileScanner.nextLine().trim();

                String[] data = line.split(",", 2);
                UUID uuid = UUID.fromString(data[0]);
                String name = data[1];

                User user = new User(uuid, name);
                userList.add(user);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        users = userList.toArray(new User[0]);
    }

    @Override
    public Optional<User> getUserById(String id) {

        UUID uuid = UUID.fromString(id);

        for (User user : users) {
            if (user == null) break;

            if (user.getId().equals(uuid)) return Optional.of(user);
        }

        return Optional.empty();
    }

    @Override
    public User[] getAllUsers() {
        return users;
    }
}
