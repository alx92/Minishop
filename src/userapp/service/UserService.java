package userapp.service;

import userapp.persistence.dao.UserDao;
import userapp.persistence.model.AddressModel;
import userapp.persistence.model.UserModel;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

// sorting, filtering, these types of business cases
// methods will differ depending on the case
// below examples of possible methods for the service level cases
public class UserService
{
    private UserDao userDao = new UserDao();

    // custom add user method in service
    public void addUser(UserModel userModel) throws IllegalPostalCodException
    {
        // we used this for the id because the value is unique, just as an example because when we use
        // databases, there are other ways
        userModel.setId(System.currentTimeMillis());
        AddressModel addressModel = userModel.getAddress();
        long postalCode = addressModel.getPostalCode();
        String postalCodeAsString = "" + postalCode;
        if (postalCodeAsString.length() != 6) {
            throw new IllegalPostalCodException();
        }
        userDao.addUser(userModel);
    }

    public List<UserModel> getSortedUsers()
    {
        List<UserModel> users =  userDao.readUsers();

        users.sort(Comparator.comparing(UserModel :: getLastName));
        return  users;
    }

    public void changeName(String newName, int id)
    {
        Optional<UserModel> optional = userDao.readUser(id);

        if (optional.isPresent())
        {
            UserModel userModel = optional.get();
            userModel.setName(newName);
            userDao.update(userModel);
        }
    }
}
