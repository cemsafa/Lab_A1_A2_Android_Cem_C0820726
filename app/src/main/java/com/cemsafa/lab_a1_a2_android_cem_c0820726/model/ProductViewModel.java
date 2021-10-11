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
    private final LiveData<List<Provider>> allProviders;

    public ProductViewModel(@NonNull Application application) {
        super(application);
        repository = new ProductRepository(application);
        allProducts = repository.getAllProducts();
        allProviders = repository.getAllProviders();
    }

    public LiveData<List<Product>> getAllProducts() {
        return allProducts;
    }

    public LiveData<List<Provider>> getAllProviders() {
        return allProviders;
    }

    public LiveData<Product> getProduct(int id) {
        return repository.getProduct(id);
    }

    public LiveData<Provider> getProvider(long id) {
        return repository.getProvider(id);
    }

    public void insertProduct(Product product) {
        repository.insertProduct(product);
    }

    public void insertProvider(Provider provider) {
        repository.insertProvider(provider);
    }

    public void insertProducts(String providerName, List<String> productsNames) {
        repository.insertProducts(providerName, productsNames);
    }

    public void updateProduct(Product product) {
        repository.updateProduct(product);
    }

    public void updateProvider(Provider provider) {
        repository.updateProvider(provider);
    }

    public void deleteProduct(Product product) {
        repository.deleteProduct(product);
    }

    public void deleteProvider(Provider provider) {
        repository.deleteProvider(provider);
    }
}
