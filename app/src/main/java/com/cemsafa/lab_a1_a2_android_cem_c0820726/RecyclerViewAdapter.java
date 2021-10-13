package com.cemsafa.lab_a1_a2_android_cem_c0820726;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.cemsafa.lab_a1_a2_android_cem_c0820726.model.Product;

import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {

    private List<Product> data;
    private LayoutInflater inflater;
    private AdapterView.OnItemClickListener itemClickListener;

    RecyclerViewAdapter(Context context, List<Product> data) {
        this.inflater = LayoutInflater.from(context);
        this.data = data;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.product_row, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Product product = data.get(position);
        holder.productNameTV.setText(product.getName());
        holder.providerNameTV.setText(product.getProvider_name());
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView productNameTV;
        TextView providerNameTV;

        public ViewHolder(View view) {
            super(view);
            productNameTV = view.findViewById(R.id.productNameTV);
            providerNameTV = view.findViewById(R.id.providerNameTV);
            view.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {

        }
    }
}
