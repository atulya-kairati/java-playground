package dao.userdao;

import models.user.User;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class UserDAOFromCSV implements UserDAO {

    private final List<User> users = new ArrayList<>();

    {
        loadUsers();
    }

    @Override
    public void loadUsers() {
        String csvResourcePath = getClass().getClassLoader().getResource("users.csv").getPath();
        File file = new File(csvResourcePath);

        try (Scanner fileScanner = new Scanner(file)) {
            while (fileScanner.hasNext()){
                String line = fileScanner.nextLine().trim();

                String[] data = line.split(",", 2);
                UUID uuid = UUID.fromString(data[0]);
                String name = data[1];

                User user = new User(uuid, name);
                users.add(user);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Optional<User> getUserById(String id) {

        UUID uuid = UUID.fromString(id);

        return users.stream()
                .filter(user -> user.getId().equals(uuid))
                .findFirst();
    }

    @Override
    public List<User> getAllUsers() {
        return users;
    }
}
