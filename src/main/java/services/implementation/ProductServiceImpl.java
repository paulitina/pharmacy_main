package services.implementation;

import entities.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import repositories.ProductDao;
import services.api.ProductService;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService{
    @Autowired
    ProductDao productDao;

    @Override
    public void addProduct(String name, String indications, String manufacturerInfo, String sideEffects, int quantity,
                           int price, Boolean prescribed, Byte image) {
        productDao.save(new Product(name, indications, manufacturerInfo, sideEffects, quantity, price, prescribed, image));
    }

    @Override
    public void updateProduct(String... args) {
    }

    @Override
    public List<Product> getListOfProducts() {
        return productDao.findAll();
    }

    @Override
    public void getProductInfo(Long productId) {
        productDao.findById(productId);
    }
}
