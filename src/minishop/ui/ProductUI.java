package minishop.ui;

import minishop.persistence.model.CategoryModel;
import minishop.persistence.model.ProductModel;
import minishop.service.*;
import userapp.persistence.model.DepartmentModel;

import java.util.List;
import java.util.Scanner;

public class ProductUI
{
    private ProductService productService = new ProductService();
    private CategoryService categoryService = new CategoryService();
    private Scanner scanner = new Scanner(System.in);

    public void startProductManagement()
    {
        int option = -1;

        while (option != 0)
        {
            displayMenu();
            option = scanner.nextInt();
            scanner.nextLine();

            if (option == 1)
            {
                addProduct();
            }
            if (option == 2)
            {
                findProductById();
            }

            if (option == 3)
            {
                assignProductToCategory();
            }

            if (option == 4)
            {
                viewOrderedProducts();
            }

            if (option == 5)
            {
                viewProductsInACategory();
                //viewCategories();
            }
        }
    }

    private void displayMenu()
    {
        System.out.println("---Product Management---\n" +
                "1. Add new product\n" +
                "2. Find product by id \n" +
                "3. Assign existing category to an existing product \n" +
                "4. View all products by id \n" +
                "5. View all products in a category \n" +
                //"5. View categories \n" +
                "0. Exit");
    }

    //1
    private void addProduct()
    {
        System.out.println("Enter product name: ");
        String productName = scanner.nextLine();

        System.out.println("Enter product price: ");
        double price = scanner.nextDouble();
        scanner.nextLine();

        System.out.println("Enter product description: ");
        String description = scanner.nextLine();

        ProductModel productModel = new ProductModel();
        productModel.setProductName(productName);
        productModel.setPrice(price);
        productModel.setDescription(description);
        try
        {
            productService.addProduct(productModel);
        }
        catch (InvalidPriceException e)
        {
            System.out.println("Price is below 0");
        }
    }

    //2
    private void findProductById()
    {
        System.out.println("Enter product id: ");
        String productId = scanner.nextLine();
        try
        {
            ProductModel productModel = productService.getProductById(productId);

            System.out.println("\n" + productModel.getProductName() +
                    " (" + productModel.getId() + ")\nPrice: " + productModel.getPrice() + "\n" +
                    "Description: " + productModel.getDescription() + "\n");
        }
        catch (ProductNotFoundException e)
        {
            System.out.println("Product not found!");
        }
    }

    //3
    private void assignProductToCategory() {
        System.out.println("Enter id of product:");
        String productId = scanner.nextLine();

        System.out.println("Enter id of category:");
        String categoryId = scanner.nextLine();

        productService.assignProductToCategory(productId, categoryId);
    }

    //4
    private void viewOrderedProducts()
    {
        List<ProductModel> productModels = productService.findAllProductsSortedById();

        productModels.forEach(productModel -> System.out.println("\n" + productModel.getProductName() +
                " (" + productModel.getId() + ")\nPrice: " + productModel.getPrice() + "\n" +
                "Description: " + productModel.getDescription() + "\n"));
    }

    //5
    private void viewProductsInACategory()
    {
        System.out.println("Enter category name: ");
        String categoryName = scanner.nextLine();

        try
        {
            List<ProductModel> products = productService.viewAllProductsInACategory(categoryName);

            products.forEach(productModel -> System.out.println("\n" + productModel.getProductName() +
                    " (" + productModel.getId() + ")\nPrice: " + productModel.getPrice() + "\n" +
                    "Description: " + productModel.getDescription() + "\n"));
        }
        catch (CategoryNotFoundException e)
        {
            System.out.println("Category not found!");
        }
    }


//    private void viewCategories() {
//        System.out.println("Categories:");
//        List<CategoryModel> categoryModelList = categoryService.getAllCategories();
//        categoryModelList.forEach(categoryModel ->{
//            System.out.println(categoryModel.getId() + " " + categoryModel.getCategoryName());
//            List<ProductModel> products = categoryModel.getProducts();
//            products.forEach(product -> System.out.println(product.getId() + " " + product.getProductName()));
//
//        });
//    }
}
