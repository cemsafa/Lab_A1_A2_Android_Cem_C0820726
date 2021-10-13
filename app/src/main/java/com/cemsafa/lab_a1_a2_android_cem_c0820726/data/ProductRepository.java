package com.cemsafa.lab_a1_a2_android_cem_c0820726.data;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.cemsafa.lab_a1_a2_android_cem_c0820726.model.Product;
import com.cemsafa.lab_a1_a2_android_cem_c0820726.model.Provider;
import com.cemsafa.lab_a1_a2_android_cem_c0820726.model.ProviderWithProducts;
import com.cemsafa.lab_a1_a2_android_cem_c0820726.util.ProductDatabase;

import java.util.ArrayList;
import java.util.List;

public class ProductRepository {

    private ProductDao productDao;
    private LiveData<List<Product>> allProducts;
    private LiveData<List<ProviderWithProducts>> providerWithProducts;

    public ProductRepository(Application application) {
        ProductDatabase productDatabase = ProductDatabase.getInstance(application);
        productDao = productDatabase.productDao();
        allProducts = productDao.getAllProducts();
        providerWithProducts = productDao.getProviderWithProducts();
    }

    public void insert(Product product) {
        ProductDatabase.dbWriteExecutor.execute(() -> {
            productDao.insert(product);
        });
    }

    public LiveData<List<Product>> getAllProducts() {
        return allProducts;
    }

    public LiveData<Product> getProduct(long id) {
        return productDao.getProduct(id);
    }

    public void update(Product product) {
        ProductDatabase.dbWriteExecutor.execute(() -> {
            productDao.update(product);
        });
    }

    public void delete(Product product) {
        ProductDatabase.dbWriteExecutor.execute(() -> {
            productDao.delete(product);
        });
    }

    public LiveData<List<Provider>> getAllProviders() {
        return productDao.getAllProviders();
    }

    public LiveData<Provider> getProvider(long id) {
        return productDao.getProvider(id);
    }

    public long insert(Provider provider) {
        ProductDatabase.dbWriteExecutor.execute(() -> {
            productDao.insert(provider);
        });
        return provider.getId();
    }

    public void update(Provider provider) {
        ProductDatabase.dbWriteExecutor.execute(() -> {
            productDao.update(provider);
        });
    }

    public void delete(Provider provider) {
        ProductDatabase.dbWriteExecutor.execute(() -> {
            productDao.delete(provider);
        });
    }

    public LiveData<List<Product>> getProductsInProvider(String provider_name) {
        return productDao.getProductsInProvider(provider_name);
    }

    public LiveData<List<ProviderWithProducts>> getProviderWithProducts() {
        return productDao.getProviderWithProducts();
    }

    public void insert(Provider provider, Product product) {
        ProductDatabase.dbWriteExecutor.execute(() -> {
            productDao.insert(provider, product);
        });
    }

    public void updateProductInProvider(Provider provider, Product product) {
        ProductDatabase.dbWriteExecutor.execute(() -> {
            productDao.updateProductInProvider(provider, product);
        });
    }
}
