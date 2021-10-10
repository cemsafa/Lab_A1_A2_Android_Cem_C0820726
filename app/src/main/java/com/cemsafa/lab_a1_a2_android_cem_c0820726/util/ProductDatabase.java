package com.cemsafa.lab_a1_a2_android_cem_c0820726.util;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.cemsafa.lab_a1_a2_android_cem_c0820726.data.ProductDao;
import com.cemsafa.lab_a1_a2_android_cem_c0820726.model.Product;
import com.cemsafa.lab_a1_a2_android_cem_c0820726.model.Provider;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {Product.class, Provider.class}, version = 1, exportSchema = false)
public abstract class ProductDatabase extends RoomDatabase {

    private static final String DB_NAME = "product_db";

    public abstract ProductDao productDao();

    private static volatile ProductDatabase INSTANCE;

    private static final int THREAD_NO = 4;

    public static final ExecutorService dbWriteExecutor = Executors.newFixedThreadPool(THREAD_NO);

    public static ProductDatabase getInstance(final Context context) {
        if (INSTANCE == null) {
            synchronized (ProductDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(), ProductDatabase.class, DB_NAME).addCallback(dbCallback).build();
                }
            }
        }
        return INSTANCE;
    }

    private static final RoomDatabase.Callback dbCallback = new RoomDatabase.Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);

            dbWriteExecutor.execute(() -> {
                ProductDao productDao = INSTANCE.productDao();
                productDao.deleteAllProducts();
            });
        }
    };
}
