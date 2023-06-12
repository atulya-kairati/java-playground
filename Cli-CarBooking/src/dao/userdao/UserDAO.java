package dao.userdao;

import models.user.User;

import java.util.Optional;
import java.util.UUID;

public class UserDAO {
    private final int CAPACITY = 100;
    private int currentSlot = 0;
    private final User[] usersArray = new User[CAPACITY];

    public UserDAO() {
        usersArray[0] = new User("Manus");
        usersArray[1] = new User("Pepe");
        usersArray[2] = new User("Gen Doo");
        currentSlot = 3;
    }

    public void addNewUser(String name){
        User newUser = new User(name);
        usersArray[currentSlot++] = newUser;
    }

    public Optional<User> getUserById(String id){

        UUID uuid = UUID.fromString(id);

        for (User user : usersArray){
            if(user == null) break;

            if(user.getId().equals(uuid)) return Optional.of(user);
        }

        return Optional.empty();
    }

    public User[] getAllUsers(){
        User[] users = new User[currentSlot];

        System.arraycopy(usersArray, 0, users, 0, currentSlot);

        return users;
    }
}
