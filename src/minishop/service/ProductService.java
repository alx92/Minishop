package minishop.service;

import minishop.persistence.dao.CategoryDao;
import minishop.persistence.dao.ProductDao;
import minishop.persistence.model.CategoryModel;
import minishop.persistence.model.ProductModel;

import javax.swing.text.html.Option;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

public class ProductService
{
    private ProductDao productDao = new ProductDao();
    private CategoryDao categoryDao = new CategoryDao();

    public void addProduct(ProductModel productModel) throws InvalidPriceException
    {
        productModel.setId(String.valueOf(System.currentTimeMillis()));

        if (productModel.getPrice() <= 0)
        {
            throw new InvalidPriceException();
        }
        else
        {
            productDao.add(productModel);
        }
    }

    public ProductModel getProductById(String id) throws ProductNotFoundException
    {
        Optional<ProductModel> optionalProductModel = productDao.findById(id);

        if (!optionalProductModel.isPresent())
        {
            throw new ProductNotFoundException();
        }

        return optionalProductModel.get();
    }

    public void assignProductToCategory(String idProduct, String idCategory)
    {
        Optional<ProductModel> productModelOptional = productDao.findById(idProduct); //gasim produs dupa id

        if (!productModelOptional.isPresent())
        { return; }

        ProductModel productModel = productModelOptional.get(); // get product out of the box

        Optional<CategoryModel> categoryModelOptional = categoryDao.findById(idCategory);

        if (!categoryModelOptional.isPresent())
        { return; }

        CategoryModel categoryModel = categoryModelOptional.get(); // get category out of the box
        List<ProductModel> products = categoryModel.getProducts();

        if(products == null)
        {
            products =  new ArrayList<>();
        }

        products.add(productModel);
        categoryModel.setProducts(products);
        categoryDao.update(categoryModel);
    }

    public List<ProductModel> findAllProductsSortedById()
    {
        List<ProductModel> productModels = productDao.getAll();
        productModels.sort(Comparator.comparing(ProductModel::getId));
        return productModels;
    }

    public List<ProductModel> viewAllProductsInACategory(String categoryName) throws CategoryNotFoundException
    {
        List<CategoryModel> categories = categoryDao.getAll();

        Optional<CategoryModel> categoryModelOptional =  categories.stream()
                .filter(categoryModel -> categoryModel.getCategoryName().equals(categoryName)).findFirst();

        if (!categoryModelOptional.isPresent())
        {
            throw new CategoryNotFoundException();
        }

        CategoryModel categoryModel = categoryModelOptional.get();

        return categoryModel.getProducts();
    }
}
