package userapp.persistence.dao;

import userapp.persistence.FileUtil;
import userapp.persistence.model.UserModel;

import java.util.*;

// DAO - data access object
// contains CRUD methods
// this is just for basic CRUD methods
public class UserDao
{
    private FileUtil fileUtil = new FileUtil("D:\\SDA\\Exercitii\\IntelliJProjects\\JavaAdvancedCoding03\\user.txt");

    // Create or ADD
    public void addUser(UserModel user)
    {
        List<UserModel> userModelList = fileUtil.read();
        userModelList.add(user);

        fileUtil.write(userModelList);
    }

    // Read
    public List<UserModel> readUsers()
    {
        return fileUtil.read();
    }

    // Read
    public Optional<UserModel> readUser(int id)
    {
        List<UserModel> users = fileUtil.read();

        return users.stream().filter(user -> user.getId() == id)
                .findFirst();
//        for (UserModel user : users)
//        {
//            if (user.getId() == id)
//            {
//                return user;
//            }
//        }
//        return null;
    }

    // Update
    public void update(UserModel updateUser)
    {
        List<UserModel> users = fileUtil.read();
        for (int i = 0; i < users.size(); i++)
        {
            UserModel oldUser = users.get(i);
            if (oldUser.getId() == updateUser.getId())
            {
                users.remove(oldUser);
                users.add(updateUser);
            }
        }
        fileUtil.write(users);
    }

    // Delete
    public void delete(int id)
    {
        List<UserModel> users = fileUtil.read();

        for (int i = 0; i < users.size(); i++)
        {
            UserModel userModel = users.get(i);

            if (userModel.getId() == id)
            {
                users.remove(userModel);
            }
        }
        fileUtil.write(users);
    }
}
