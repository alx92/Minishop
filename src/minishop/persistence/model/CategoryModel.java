package minishop.persistence.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class CategoryModel extends Model implements Serializable
{
    private String categoryName;
    private List<ProductModel> products = new ArrayList<>();

    public String getCategoryName()
    {
        return categoryName;
    }

    public void setCategoryName(String categoryName)
    {
        this.categoryName = categoryName;
    }

    public List<ProductModel> getProducts()
    {
        return products;
    }

    public void setProducts(List<ProductModel> products)
    {
        this.products = products;
    }
}
