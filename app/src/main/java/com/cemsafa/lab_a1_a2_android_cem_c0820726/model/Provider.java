package com.cemsafa.lab_a1_a2_android_cem_c0820726.model;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.Index;
import androidx.room.PrimaryKey;

@Entity(tableName = "provider", indices = @Index(value = "name", unique = true))
public class Provider {

    @PrimaryKey(autoGenerate = true)
    private Integer id;

    @NonNull
    private String name;

    private String email;

    private Integer phone;

    private Double latitude;

    private Double longitude;

    @Ignore
    public Provider() {}

    @Ignore
    public Provider(@NonNull String name) {
        this.name = name;
    }

    public Provider(@NonNull String name, String email, Integer phone, Double latitude, Double longitude) {
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @NonNull
    public String getName() {
        return name;
    }

    public void setName(@NonNull String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getPhone() {
        return phone;
    }

    public void setPhone(Integer phone) {
        this.phone = phone;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }
}
