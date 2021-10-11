package com.cemsafa.lab_a1_a2_android_cem_c0820726;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import com.cemsafa.lab_a1_a2_android_cem_c0820726.model.Product;
import com.cemsafa.lab_a1_a2_android_cem_c0820726.model.ProductViewModel;

public class AddProductActivity extends AppCompatActivity {

    private EditText productNameET, providerNameET, priceET, descriptionET;

    private boolean isEditing = false;
    private int productId = 0;
    private long providerId = 0;
    private Product productToUpdate;

    private ProductViewModel productViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_product);

        productViewModel = new ViewModelProvider.AndroidViewModelFactory(this.getApplication()).create(ProductViewModel.class);

        productNameET = findViewById(R.id.et_product_name);
        providerNameET = findViewById(R.id.et_provider_name);
        priceET = findViewById(R.id.et_price);
        descriptionET = findViewById(R.id.et_description);

        Button addEditBtn = findViewById(R.id.btn_addEdit_product);

        addEditBtn.setOnClickListener(v -> {
            addEditProduct();
        });

        if (getIntent().hasExtra(ProductFragment.PRODUCT_ID)) {
            productId = getIntent().getIntExtra(ProductFragment.PRODUCT_ID, 0);

            productViewModel.getProduct(productId).observe(this, product -> {
                if (product != null) {
                    providerNameET.setText(product.getName());
                    providerId = product.getProvider_id();
                    productViewModel.getProvider(providerId).observe(this, provider -> {
                        providerNameET.setText(provider.getName());
                    });
                }
            });
        }
    }

    private void addEditProduct() {
    }
}