package com.cemsafa.lab_a1_a2_android_cem_c0820726;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.cemsafa.lab_a1_a2_android_cem_c0820726.model.Product;
import com.cemsafa.lab_a1_a2_android_cem_c0820726.model.ProductViewModel;

public class AddProductActivity extends AppCompatActivity {

    public static final String PRODUCT_NAME_REPLY = "product_name_reply";
    public static final String PRICE_REPLY = "price_reply";
    public static final String DESCRIPTION_REPLY = "description_reply";
    public static final String PROVIDER_NAME_REPLY = "provider_name_reply";

    private EditText productNameET, providerNameET, priceET, descriptionET;
    private TextView productLabel;

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
                    productNameET.setText(product.getName());
                    providerId = product.getProvider_id();
                    productViewModel.getProvider(providerId).observe(this, provider -> {
                        providerNameET.setText(provider.getName());
                    });
                    priceET.setText(String.valueOf(product.getPrice()));
                    descriptionET.setText(product.getDescription());
                    productToUpdate = product;
                }
            });
            productLabel = findViewById(R.id.product_label);
            isEditing = true;
            productLabel.setText(R.string.edit_product);
        }
    }

    private void addEditProduct() {
        String productName = productNameET.getText().toString().trim();
        String providerName = providerNameET.getText().toString().trim();
        String price = priceET.getText().toString().trim();
        String description = descriptionET.getText().toString().trim();

        if (productName.isEmpty()) {
            productNameET.setError("Name must have a value");
            productNameET.requestFocus();
            return;
        }

        if (providerName.isEmpty()) {
            providerNameET.setError("Provider must have a value");
            providerNameET.requestFocus();
            return;
        }

        if (isEditing) {
            Product product = new Product();
            product.setName(productName);
            product.setPrice(Double.parseDouble(price));
            product.setDescription(description);
            productViewModel.update(product);
        } else {
            Intent replyIntent = new Intent();
            replyIntent.putExtra(PROVIDER_NAME_REPLY, providerName);
            replyIntent.putExtra(PRODUCT_NAME_REPLY, productName);
            replyIntent.putExtra(PRICE_REPLY, price);
            replyIntent.putExtra(DESCRIPTION_REPLY, description);
            setResult(RESULT_OK,replyIntent);
            Toast.makeText(this, "Product created", Toast.LENGTH_SHORT).show();
        }
        finish();
    }
}