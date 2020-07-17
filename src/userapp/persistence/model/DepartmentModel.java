package userapp.persistence.model;

import java.io.Serializable;
import java.util.List;

public class DepartmentModel implements Serializable
{

    private long id;
    private String name;
    private List<UserModel> users;

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setUsers(List<UserModel> users) {
        this.users = users;
    }

    public List<UserModel> getUsers() {
        return users;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
