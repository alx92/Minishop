package minishop.ui;

import minishop.persistence.model.CategoryModel;
import minishop.service.CategoryNotFoundException;
import minishop.service.CategoryService;

import java.util.List;
import java.util.Scanner;

public class CategoryUI
{
    private CategoryService categoryService = new CategoryService();
    private Scanner scanner = new Scanner(System.in);
    CategoryModel categoryModel = new CategoryModel();

    public void startCategoryManagement()
    {
        int option = -1;

        while (option != 7)
        {
            displayMenu();
            option = scanner.nextInt();
            scanner.nextLine();

            if (option == 1)
            {
                viewCategories();
            }
            if (option == 2)
            {
                findCategoryById();
            }
            if (option == 3)
            {
                viewOrderedCategories();
            }
            if (option == 4)
            {
                updateCategory();
            }
            if (option == 5)
            {
                addCategory();
            }
            if (option == 6)
            {
                removeCategory();
            }
        }
    }

    private void displayMenu()
    {
        System.out.println("---Category Management---\n" +
                "1. View categories\n" +
                "2. Find category by id \n" +
                "3. View ordered categories\n" +
                "4. Update existing category\n" +
                "5. Add new category.\n" +
                "6. Remove category\n" +
                "7. Exit");
    }

    //1
    private void viewCategories()
    {
        List<CategoryModel> categoryModelList = categoryService.getAllCategories();

        categoryModelList.forEach(categoryModel -> System.out.println(categoryModel.getId() +
                "----" + categoryModel.getCategoryName()));
    }

    //2
    private void findCategoryById()
    {
        System.out.println("Enter category id: ");
        String categoryId = scanner.nextLine();
        try
        {
            CategoryModel categoryModel = categoryService.getCategoryById(categoryId);

            System.out.println("\n" + categoryModel.getCategoryName() +
                    " (" + categoryModel.getId() + ")\n");
        }
        catch (CategoryNotFoundException e)
        {
            System.out.println("Category not found" + e.getMessage());
        }
    }

    //3
    private void viewOrderedCategories()
    {
        List<CategoryModel> categoryModelList = categoryService.getCategoriesOrderByName();

        categoryModelList.forEach(category -> System.out.println(category.getId() +
                "----" + category.getCategoryName()));
    }

    //4
    private void updateCategory()
    {
        System.out.println("Enter new category name:");
        String updatedCategoryName = scanner.nextLine();

        System.out.println("Enter new category id: ");
        String updatedId = scanner.nextLine();

        CategoryModel categoryModel = new CategoryModel();
        categoryModel.setCategoryName(updatedCategoryName);
        categoryModel.setId(updatedId);

        categoryService.updateCategory(categoryModel);
    }

    //5
    private void addCategory()
    {
        System.out.println("Enter category name: ");
        String categoryName = scanner.nextLine();

        categoryModel.setCategoryName(categoryName);
        categoryService.addCategory(categoryModel);
    }

    //6
    private void removeCategory()
    {
        System.out.println("Enter category id: ");
        String id = scanner.nextLine();

        categoryService.removeCategory(id);
    }
}
