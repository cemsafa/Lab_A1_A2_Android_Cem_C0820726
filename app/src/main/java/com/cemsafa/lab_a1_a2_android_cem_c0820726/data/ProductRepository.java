package com.cemsafa.lab_a1_a2_android_cem_c0820726.data;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.cemsafa.lab_a1_a2_android_cem_c0820726.model.Product;
import com.cemsafa.lab_a1_a2_android_cem_c0820726.model.Provider;
import com.cemsafa.lab_a1_a2_android_cem_c0820726.util.ProductDatabase;

import java.util.ArrayList;
import java.util.List;

public class ProductRepository {

    private ProductDao productDao;
    private LiveData<List<Product>> allProducts;
    private LiveData<List<Provider>> allProviders;

    public ProductRepository(Application application) {
        ProductDatabase productDatabase = ProductDatabase.getInstance(application);
        productDao = productDatabase.productDao();
        allProducts = productDao.getAllProducts();
        allProviders = productDao.getAllProviders();
    }

    public LiveData<List<Product>> getAllProducts() {
        return allProducts;
    }

    public LiveData<List<Provider>> getAllProviders() { return allProviders; }

    public LiveData<Product> getProduct(int id) {
        return productDao.getProduct(id);
    }

    public LiveData<Provider> getProvider(String name) { return productDao.getProvider(name); }

    public void insertProduct(Product product) {
        ProductDatabase.dbWriteExecutor.execute(() -> {
            productDao.insertProduct(product);
        });
    }

    public void insertProvider(Provider provider) {
        ProductDatabase.dbWriteExecutor.execute(() -> {
            productDao.insertProvider(provider);
        });
    }

    public void insertProducts(String providerName, List<String> productsNames) {
        ProductDatabase.dbWriteExecutor.execute(() -> {
            List<Product> products = new ArrayList<>(productsNames.size());
            for (String name : productsNames) {
                products.add(new Product(name));
            }
            productDao.insertProducts(new Provider(providerName), products);
        });
    }

    public void updateProduct(Product product) {
        ProductDatabase.dbWriteExecutor.execute(() -> {
            productDao.updateProduct(product);
        });
    }

    public void updateProvider(Provider provider) {
        ProductDatabase.dbWriteExecutor.execute(() -> {
            productDao.updateProvider(provider);
        });
    }

    public void deleteProduct(Product product) {
        ProductDatabase.dbWriteExecutor.execute(() -> {
            productDao.deleteProduct(product);
        });
    }

    public void deleteProvider(Provider provider) {
        ProductDatabase.dbWriteExecutor.execute(() -> {
            productDao.deleteProvider(provider);
        });
    }
}
