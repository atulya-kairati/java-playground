package dao.userdao;

import models.user.User;

import java.util.Optional;
import java.util.UUID;

public class UserDAOImpl implements UserDAO{
    private User[] usersArray;

    {
        loadUsers();
    }

    @Override
    public void loadUsers() {
        usersArray = new User[] {
                new User("Manus"),
                new User("Jukka Sarasti"),
                new User("Atul"),
                new User("Sand dan Glokta"),
        };
    }

    @Override
    public Optional<User> getUserById(String id){

        UUID uuid = UUID.fromString(id);

        for (User user : usersArray){
            if(user == null) break;

            if(user.getId().equals(uuid)) return Optional.of(user);
        }

        return Optional.empty();
    }

    @Override
    public User[] getAllUsers(){
        return usersArray;
    }
}
