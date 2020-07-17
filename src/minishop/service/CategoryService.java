package minishop.service;

import minishop.persistence.dao.CategoryDao;
import minishop.persistence.model.CategoryModel;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

public class CategoryService
{
    private CategoryDao categoryDao = new CategoryDao();
    private CategoryModel categoryModel = new CategoryModel();
   // private ObjectFileScanner<CategoryModel> fileScanner = new ObjectFileScanner<>("category.txt");

    public List<CategoryModel> getAllCategories()
    {
        return categoryDao.getAll();
    }

    public void addCategory(CategoryModel categoryModel)
    {
        categoryModel.setId(String.valueOf(System.currentTimeMillis()));
        categoryDao.add(categoryModel);
    }

    public CategoryModel getCategoryById(String id) throws CategoryNotFoundException
    {
        Optional<CategoryModel> optionalCategoryModel = categoryDao.findById(id);

        if(!optionalCategoryModel.isPresent())
        {
            throw new CategoryNotFoundException();
        }

        return optionalCategoryModel.get();
    }

    public List<CategoryModel> getCategoriesOrderByName() // should be without parameter
    {
        List<CategoryModel> categoryModels = categoryDao.getAll();

        categoryModels.sort(Comparator.comparing(CategoryModel::getCategoryName));

        return categoryModels;
    }

    public void removeCategory(String id)
    {
        categoryDao.remove(id);
    }

    public void updateCategory(CategoryModel updatedCategoryModel)
    {
        categoryDao.update(updatedCategoryModel);
    }
}
