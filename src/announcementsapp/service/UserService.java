package announcementsapp.service;

import announcementsapp.persistance.dao.UserDao;
import announcementsapp.persistance.model.AnnouncementModel;
import announcementsapp.persistance.model.UserModel;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UserService
{
    private UserDao userDao = new UserDao();
    private AnnouncementService announcementService = new AnnouncementService();

    public void register(UserModel newUser) throws UsernameAlreadyTakenException
    {
        Optional<UserModel> userModelOptional = userDao.findByUsername(newUser.getUsername());
        if (userModelOptional.isPresent())
        {
            throw new UsernameAlreadyTakenException();
        }

        //putem face diverse verificari inainte de a adauga userul
        userDao.addUser(newUser);
    }

    public boolean login(String username, String password)
    {
        Optional<UserModel> userModelOptional = userDao.findByUsername(username);
        if (userModelOptional.isPresent())
        {
            UserModel user = userModelOptional.get();
            if (user.getPassword().equals(password))
            {
                return true;
            }
        }
        return  false;
    }

    public  List<AnnouncementModel> viewMyAnnouncements(String username)
    {
        Optional<UserModel> userModelOptional = userDao.findByUsername(username);
        if (userModelOptional.isPresent())
        {
            UserModel user = userModelOptional.get();
            List<AnnouncementModel> announcementModels = user.getAnnouncementModelList();
            return announcementModels;
        }
        return new ArrayList<>();
    }

    public void addAnnouncement(String username, AnnouncementModel announcementModel)
    {
        announcementService.addAnnouncement(announcementModel);
        Optional<UserModel> userModelOptional = userDao.findByUsername(username);
        if (userModelOptional.isPresent())
        {
            UserModel userModel = userModelOptional.get();
            List<AnnouncementModel> announcementModelList = userModel.getAnnouncementModelList();
            announcementModelList.add(announcementModel);
            userDao.updateUser(userModel);
        }
    }
}
