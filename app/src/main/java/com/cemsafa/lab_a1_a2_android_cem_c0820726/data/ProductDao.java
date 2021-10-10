package com.cemsafa.lab_a1_a2_android_cem_c0820726.data;

import static androidx.room.OnConflictStrategy.REPLACE;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Transaction;
import androidx.room.Update;

import com.cemsafa.lab_a1_a2_android_cem_c0820726.model.Product;
import com.cemsafa.lab_a1_a2_android_cem_c0820726.model.Provider;
import com.cemsafa.lab_a1_a2_android_cem_c0820726.model.ProviderWithProducts;

import java.util.List;

@Dao
public abstract class ProductDao {

    @Transaction
    @Query("SELECT * FROM provider WHERE name LIKE :name")
    public abstract LiveData<List<ProviderWithProducts>> getProductsWithProvider(String name);

    @Query("SELECT * FROM product ORDER BY name ASC")
    public abstract LiveData<List<Product>> getAllProducts();

    @Query("SELECT * FROM product WHERE id = :id")
    public abstract LiveData<Product> getProduct(int id);

    @Insert
    public abstract void insertProduct(Product product);

    @Update
    public abstract void updateProduct(Product product);

    @Delete
    public abstract void deleteProduct(Product product);

    @Query("DELETE FROM product")
    public abstract void deleteAllProducts();

    @Transaction
    public void insertProducts(Provider provider, List<Product> products) {

        final long provider_id = insertProvider(provider);

        for (Product product : products) {
            product.setProvider_id(provider_id);
            insertProduct(product);
        }
    }

    @Query("SELECT * FROM provider")
    public abstract LiveData<List<Provider>> getAllProviders();

    @Query("SELECT * FROM provider WHERE name = :name")
    public abstract LiveData<Provider> getProvider(String name);

    @Insert(onConflict = REPLACE)
    public abstract long insertProvider(Provider provider);

    @Update
    public abstract void updateProvider(Provider provider);

    @Delete
    public abstract void deleteProvider(Provider provider);

    @Query("DELETE FROM provider")
    public abstract void deleteAllProviders();
}
