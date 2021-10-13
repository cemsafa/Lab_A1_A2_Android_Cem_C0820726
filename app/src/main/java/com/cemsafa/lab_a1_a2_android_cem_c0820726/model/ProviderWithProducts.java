package com.cemsafa.lab_a1_a2_android_cem_c0820726.model;

import androidx.room.Embedded;
import androidx.room.Relation;

import java.util.List;

public class ProviderWithProducts {

    @Embedded
    public Provider provider;

    @Relation(entity = Product.class, parentColumn = "id", entityColumn = "provider_id")
    public List<Product> products;

    public ProviderWithProducts(Provider provider, List<Product> products) {
        this.provider = provider;
        this.products = products;
    }

    public Provider getProvider() {
        return provider;
    }

    public List<Product> getProducts() {
        return products;
    }
}
