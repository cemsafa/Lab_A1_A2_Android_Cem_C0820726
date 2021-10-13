package com.cemsafa.lab_a1_a2_android_cem_c0820726.data;

import static androidx.room.OnConflictStrategy.IGNORE;

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

    @Insert(onConflict = IGNORE)
    public abstract void insert(Product product);

    @Query("DELETE FROM product")
    public abstract void deleteAll();

    @Query("SELECT * FROM product ORDER BY name ASC")
    public abstract LiveData<List<Product>> getAllProducts();

    @Query("SELECT * FROM product WHERE id = :id")
    public abstract LiveData<Product> getProduct(long id);

    @Update
    public abstract void update(Product product);

    @Delete
    public abstract void delete(Product product);

    @Query("SELECT * FROM provider ORDER BY name ASC")
    public abstract LiveData<List<Provider>> getAllProviders();

    @Query("SELECT * FROM provider WHERE id = :id")
    public abstract LiveData<Provider> getProvider(long id);

    @Insert(onConflict = IGNORE)
    public abstract long insert(Provider provider);

    @Update
    public abstract void update(Provider provider);

    @Delete
    public abstract void delete(Provider provider);

    @Query("SELECT * FROM product WHERE provider_name = :provider_name")
    public abstract LiveData<List<Product>> getProductsInProvider(String provider_name);

    @Transaction
    @Query("SELECT * FROM provider")
    public abstract LiveData<List<ProviderWithProducts>> getProviderWithProducts();


    @Transaction
    public void insert(Provider provider, List<Product> products) {

        final long provider_id = insert(provider);

        for (Product product : products) {
            product.setProvider_id(provider_id);
            insert(product);
        }
    }

    @Transaction
    public void insert(Provider provider, Product product) {
        final long provider_id = insert(provider);
        product.setProvider_id(provider_id);
        insert(product);
    }

    @Transaction
    public void updateProductInProvider(Provider provider, Product product) {
        update(provider);
        update(product);
    }
}
