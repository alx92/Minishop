package minishop.persistence.dao;

import minishop.persistence.model.ProductModel;

public class ProductDao extends ModelDao<ProductModel> {
    public ProductDao() {
        super("products.txt");
    }
}
