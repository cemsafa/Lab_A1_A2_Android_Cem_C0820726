package com.cemsafa.lab_a1_a2_android_cem_c0820726.model;

import static androidx.room.ForeignKey.CASCADE;
import static androidx.room.ForeignKey.NO_ACTION;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Ignore;
import androidx.room.Index;
import androidx.room.PrimaryKey;

@Entity(tableName = "product", foreignKeys = @ForeignKey(entity = Provider.class, parentColumns = "id", childColumns = "provider_id", onDelete = CASCADE, onUpdate = NO_ACTION))
public class Product {

    @PrimaryKey(autoGenerate = true)
    private long id;

    private long provider_id;

    private String provider_name;

    @NonNull
    private String name;

    private String description;

    private Double price;

    @Ignore
    public Product() {}

    public Product(String provider_name, @NonNull String name, String description, Double price) {
        this.provider_name = provider_name;
        this.name = name;
        this.description = description;
        this.price = price;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getProvider_id() {
        return provider_id;
    }

    public void setProvider_id(long provider_id) {
        this.provider_id = provider_id;
    }

    public String getProvider_name() {
        return provider_name;
    }

    public void setProvider_name(String provider_name) {
        this.provider_name = provider_name;
    }

    @NonNull
    public String getName() {
        return name;
    }

    public void setName(@NonNull String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
}
