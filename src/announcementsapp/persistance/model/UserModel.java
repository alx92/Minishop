package announcementsapp.persistance.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class UserModel implements Serializable
{
    private String name;
    private String lastName;
    private String username;
    private String password;
    private List<AnnouncementModel> announcementModelList = new ArrayList<>();

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getLastName()
    {
        return lastName;
    }

    public void setLastName(String lastName)
    {
        this.lastName = lastName;
    }

    public String getUsername()
    {
        return username;
    }

    public void setUsername(String username)
    {
        this.username = username;
    }

    public String getPassword()
    {
        return password;
    }

    public void setPassword(String password)
    {
        this.password = password;
    }

    public List<AnnouncementModel> getAnnouncementModelList()
    {
        return announcementModelList;
    }

    public void setAnnouncementModelList(List<AnnouncementModel> announcementModelList)
    {
        this.announcementModelList = announcementModelList;
    }
}
