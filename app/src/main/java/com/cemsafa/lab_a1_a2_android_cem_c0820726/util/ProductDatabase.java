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

import java.util.ArrayList;
import java.util.List;
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
                productDao.deleteAll();

                String bic = "BIC";
                String hp = "HP";
                String apple = "Apple";
                String electrolux = "Electrolux";
                String lg = "LG";
                String sony = "Sony";
                String ikea = "Ikea";
                String structube = "Structube";

                List<Product> productList;

                Product product_pen = new Product();
                product_pen.setName("pen");
                product_pen.setProvider_name(bic);
                Provider provider_bic = new Provider();
                provider_bic.setName(bic);
                productDao.insert(provider_bic, product_pen);

                Product product_laptop = new Product();
                product_laptop.setName("laptop");
                product_laptop.setProvider_name(hp);
                Provider provider_hp = new Provider();
                provider_hp.setName(hp);
                productDao.insert(provider_hp, product_laptop);

                Product product_cell = new Product();
                product_cell.setName("cell phone");
                product_cell.setProvider_name(apple);
                Provider provider_apple = new Provider();
                provider_apple.setName(apple);
                productDao.insert(provider_apple, product_cell);

                Product product_freezer = new Product();
                product_freezer.setName("freezer");
                product_freezer.setProvider_name(electrolux);
                Provider provider_electrolux = new Provider();
                provider_electrolux.setName(electrolux);
                productDao.insert(provider_electrolux, product_freezer);

                Product product_tv = new Product();
                product_tv.setName("tv");
                product_tv.setProvider_name(lg);
                Provider provider_lg = new Provider();
                provider_lg.setName(lg);
                productDao.insert(provider_lg, product_tv);

                Product product_console = new Product();
                product_console.setName("game console");
                product_console.setProvider_name(sony);
                Provider provider_sony = new Provider();
                provider_sony.setName(sony);
                productDao.insert(provider_sony, product_console);

                Product product_couch = new Product();
                product_couch.setName("couch");
                product_couch.setProvider_name(structube);
                Provider provider_structube = new Provider();
                provider_structube.setName(structube);
                productDao.insert(provider_structube, product_couch);

                productList = new ArrayList<>();
                Product product_table = new Product();
                product_table.setName("table");
                product_table.setProvider_name(ikea);
                productList.add(product_table);
                Product product_chair = new Product();
                product_chair.setName("chair");
                product_chair.setProvider_name(ikea);
                productList.add(product_chair);
                Product product_desk = new Product();
                product_desk.setName("desk");
                product_desk.setProvider_name(ikea);
                productList.add(product_desk);
                Provider provider_ikea = new Provider();
                provider_ikea.setName(ikea);
                productDao.insert(provider_ikea, productList);
            });
        }
    };
}
