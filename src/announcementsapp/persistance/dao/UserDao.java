package announcementsapp.persistance.dao;

import announcementsapp.persistance.FileUtils;
import announcementsapp.persistance.model.UserModel;

import java.util.List;
import java.util.Optional;

public class UserDao
{
    private FileUtils<UserModel> fileUtils = new FileUtils("users.txt");

    //Create
    public void addUser(UserModel newUser)
    {
        List<UserModel> users = fileUtils.read();
        users.add(newUser);
        fileUtils.write(users);
    }

    //Read all
    public List<UserModel> getUsers()
    {
        return fileUtils.read();
    }

    //Read
    public Optional<UserModel> findByUsername(String username)
    {
        List<UserModel> users = fileUtils.read();
        return users.stream().filter(user -> user.getUsername()
                .equals(username))
                .findFirst();
    }

    //Update
    public void updateUser(UserModel updatedUser)
    {
        List<UserModel> users = fileUtils.read();
        Optional<UserModel> userModelOptional = users.stream().filter(user -> user.getUsername()
                .equals(updatedUser.getUsername()))
                .findFirst();

        if (userModelOptional.isPresent())
        {
            UserModel oldUser = userModelOptional.get(); //scot din optional
            users.remove(oldUser);
            users.add(updatedUser);
        }
        fileUtils.write(users);
    }

    public void deleteByUsername(String username)
    {
        List<UserModel> users = fileUtils.read();
        Optional<UserModel> userModelOptional = users.stream().filter(user -> user.getUsername()
                .equals(username))
                .findFirst();

        //even better then with if
        users.removeIf(user -> user.getUsername().equals(username));

//        if (userModelOptional.isPresent())
//        {
//            UserModel oldUser = userModelOptional.get(); //scot din optional cu .get()
//            users.remove(oldUser);
//        }
        fileUtils.write(users);
    }
}
