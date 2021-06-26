package services.api;

import entities.Product;

import java.util.List;

public interface ProductService {
    public void addProduct(String name, String indications, String manufacturerInfo,
                           String sideEffects, int quantity, int price, Boolean prescribed, Byte image);

    public void updateProduct(String... args);
    public List<Product> getListOfProducts();
    public void getProductInfo(Long ProductId);
}
