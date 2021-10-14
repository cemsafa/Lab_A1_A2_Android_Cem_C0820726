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
import com.cemsafa.lab_a1_a2_android_cem_c0820726.model.Provider;

public class AddProviderActivity extends AppCompatActivity {

    public static final String PROVIDER_NAME_REPLY = "provider_name_reply";
    public static final String EMAIL_REPLY = "email_reply";
    public static final String PHONE_REPLY = "phone_reply";
    public static final String LATITUDE_REPLY = "latitude_reply";
    public static final String LONGITUDE_REPLY = "longitude_reply";

    private EditText providerNameET, providerEmailET, providerPhoneET, latitudeET, longitudeET;
    private TextView providerLabel;

    private boolean isEditing = false;
    private long providerId = 0;
    private Provider providerToUpdate;

    private ProductViewModel productViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_provider);

        productViewModel = new ViewModelProvider.AndroidViewModelFactory(this.getApplication()).create(ProductViewModel.class);

        providerNameET = findViewById(R.id.et_provider_name);
        providerEmailET = findViewById(R.id.et_provider_email);
        providerPhoneET = findViewById(R.id.et_provider_phone);
        latitudeET = findViewById(R.id.et_provider_latitude);
        longitudeET = findViewById(R.id.et_provider_longitude);

        Button addEditBtn = findViewById(R.id.btn_addEdit_provider);

        addEditBtn.setOnClickListener(v -> {
            addEditProvider();
        });

        if (getIntent().hasExtra(ProviderFragment.PROVIDER_ID)) {
            providerId = getIntent().getLongExtra(ProviderFragment.PROVIDER_ID, 0);

            productViewModel.getProvider(providerId).observe(this, provider -> {
                if (provider != null) {
                    providerNameET.setText(provider.getName());
                    providerEmailET.setText(provider.getEmail());
                    providerPhoneET.setText(provider.getPhone());
                    latitudeET.setText(String.valueOf(provider.getLatitude()));
                    longitudeET.setText(String.valueOf(provider.getLongitude()));
                    providerToUpdate = provider;
                }
            });
            providerLabel = findViewById(R.id.provider_label);
            isEditing = true;
            providerLabel.setText(R.string.edit_provider);
        }
    }

    private void addEditProvider() {
        String providerName = providerNameET.getText().toString().trim();
        String providerEmail = providerEmailET.getText().toString().trim();
        String phone = providerPhoneET.getText().toString().trim();
        String latitude = latitudeET.getText().toString().trim();
        String longitude = longitudeET.getText().toString().trim();

        if (providerName.isEmpty()) {
            providerNameET.setError("Name must have a value");
            providerNameET.requestFocus();
            return;
        }

        if (providerEmail.isEmpty()) {
            providerEmailET.setError("Email must have a value");
            providerEmailET.requestFocus();
            return;
        }

        if (phone.isEmpty()) {
            providerPhoneET.setError("Phone must have a value");
            providerPhoneET.requestFocus();
            return;
        }

        if (latitude.isEmpty()) {
            latitudeET.setError("Latitude must have a value");
            latitudeET.requestFocus();
            return;
        }

        if (longitude.isEmpty()) {
            longitudeET.setError("Longitude must have a value");
            longitudeET.requestFocus();
            return;
        }

        if (isEditing) {
            Provider provider = new Provider();
            provider.setName(providerName);
            provider.setEmail(providerEmail);
            provider.setPhone(phone);
            provider.setLatitude(Double.parseDouble(latitude));
            provider.setLongitude(Double.parseDouble(longitude));
            productViewModel.update(provider);
        } else {
            Intent replyIntent = new Intent();
            replyIntent.putExtra(PROVIDER_NAME_REPLY, providerName);
            replyIntent.putExtra(EMAIL_REPLY, providerEmail);
            replyIntent.putExtra(PHONE_REPLY, phone);
            replyIntent.putExtra(LATITUDE_REPLY, latitude);
            replyIntent.putExtra(LONGITUDE_REPLY, longitude);
            setResult(RESULT_OK,replyIntent);
            Toast.makeText(this, "Provider created", Toast.LENGTH_SHORT).show();
        }
        finish();
    }
}