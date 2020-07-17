package userapp.persistence.model;

import java.io.Serializable;

public class UserModel implements Serializable
{
    private long id;
    private String name;
    private String lastName;
    private AddressModel address;

    public AddressModel getAddress()
    {
        return address;
    }

    public void setAddress(AddressModel address)
    {
        this.address = address;
    }

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

    public long getId()
    {
        return id;
    }

    public void setId(long id)
    {
        this.id = id;
    }
}
