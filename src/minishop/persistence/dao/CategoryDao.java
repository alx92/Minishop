package minishop.persistence.dao;

import minishop.persistence.model.CategoryModel;

public class CategoryDao extends ModelDao<CategoryModel>
{
    public CategoryDao()
    {
        super("category.txt");
    }
}
