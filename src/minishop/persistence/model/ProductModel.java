package minishop.persistence.model;

import java.io.Serializable;

public class ProductModel extends Model implements Serializable
{
    private String productName, description; // guess this also works - to define multiple parameters of the same type on the same line
    private double price;
    //private String description;

    public String getProductName()
    {
        return productName;
    }

    public void setProductName(String productName)
    {
        this.productName = productName;
    }

    public double getPrice()
    {
        return price;
    }

    public void setPrice(double price)
    {
        this.price = price;
    }

    public String getDescription()
    {
        return description;
    }

    public void setDescription(String description)
    {
        this.description = description;
    }
}
