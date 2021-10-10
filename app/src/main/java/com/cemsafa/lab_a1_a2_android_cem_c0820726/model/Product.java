package com.cemsafa.lab_a1_a2_android_cem_c0820726.model;

import static androidx.room.ForeignKey.CASCADE;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Ignore;
import androidx.room.Index;
import androidx.room.PrimaryKey;

@Entity(tableName = "product", foreignKeys = @ForeignKey(entity = Provider.class, parentColumns = "id", childColumns = "provider_id", onDelete = CASCADE), indices = @Index("provider_id"))
public class Product {

    @PrimaryKey(autoGenerate = true)
    private Integer id;

    private long provider_id;

    @NonNull
    private String name;

    private String description;

    private Double price;

    @Ignore
    public Product() {}

    @Ignore
    public Product(@NonNull String name) {
        this.name = name;
    }

    public Product(@NonNull String name, String description, Double price) {
        this.name = name;
        this.description = description;
        this.price = price;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public long getProvider_id() {
        return provider_id;
    }

    public void setProvider_id(long provider_id) {
        this.provider_id = provider_id;
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
