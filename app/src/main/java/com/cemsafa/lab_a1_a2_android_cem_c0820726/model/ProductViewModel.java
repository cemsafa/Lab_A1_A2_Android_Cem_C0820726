package com.cemsafa.lab_a1_a2_android_cem_c0820726.model;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.cemsafa.lab_a1_a2_android_cem_c0820726.data.ProductRepository;

import java.util.List;

public class ProductViewModel extends AndroidViewModel {

    private ProductRepository repository;
    private final LiveData<List<Product>> allProducts;

    public ProductViewModel(@NonNull Application application) {
        super(application);

        repository = new ProductRepository(application);
        allProducts = repository.getAllProducts();
    }

    public void insert(Product product) {
        repository.insert(product);
    }

    public LiveData<List<Product>> getAllProducts() {
        return allProducts;
    }

    public LiveData<Product> getProduct(long id) {
        return repository.getProduct(id);
    }

    public void update(Product product) {
        repository.update(product);
    }

    public void delete(Product product) {
        repository.delete(product);
    }

    public LiveData<List<Provider>> getAllProviders() {
        return repository.getAllProviders();
    }

    public LiveData<Provider> getProvider(long id) {
        return repository.getProvider(id);
    }

    public long insert(Provider provider) {
        repository.insert(provider);
        return provider.getId();
    }

    public void updateProvider(Provider provider) {
        repository.update(provider);
    }

    public void deleteProvider(Provider provider) {
        repository.delete(provider);
    }

    public LiveData<List<Product>> getProductsInProvider(String provider_name) {
        return repository.getProductsInProvider(provider_name);
    }

    public LiveData<List<ProviderWithProducts>> getProviderWithProducts() {
        return repository.getProviderWithProducts();
    }

    public void insert(Provider provider, Product product) {
        repository.insert(provider, product);
    }

    public void updateProductInProvider(Provider provider, Product product) {
        repository.updateProductInProvider(provider, product);
    }
}
