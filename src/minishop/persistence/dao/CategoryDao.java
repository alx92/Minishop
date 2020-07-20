package minishop.persistence.dao;

import minishop.persistence.model.CategoryModel;

import java.util.List;
import java.util.Optional;

public class CategoryDao extends ModelDao<CategoryModel>
{
    public CategoryDao()
    {
        super("category.txt");
    }
}
