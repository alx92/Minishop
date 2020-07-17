package minishop.persistence.model;

import java.io.Serializable;

public abstract class Model implements Serializable //DID NOT IMPLEMENT SERIALIZABLE hence the bug
{
    private String id;

    public String getId()
    {
        return id;
    }

    public void setId(String id)
    {
        this.id = id;
    }
}
