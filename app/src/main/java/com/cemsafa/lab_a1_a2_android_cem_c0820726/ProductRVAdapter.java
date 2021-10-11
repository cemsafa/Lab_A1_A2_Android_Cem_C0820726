package com.cemsafa.lab_a1_a2_android_cem_c0820726;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;

import com.cemsafa.lab_a1_a2_android_cem_c0820726.model.Product;
import com.cemsafa.lab_a1_a2_android_cem_c0820726.model.ProductViewModel;

import java.util.List;

public class ProductRVAdapter extends RecyclerView.Adapter<ProductRVAdapter.ViewHolder> {

    private List<Product> productList;
    private Context context;
    private OnProductClickListener onProductClickListener;
    private ProductViewModel productViewModel;

    public ProductRVAdapter(List<Product> productList, Context context, OnProductClickListener onProductClickListener, ProductViewModel productViewModel) {
        this.productList = productList;
        this.context = context;
        this.onProductClickListener = onProductClickListener;
        this.productViewModel = productViewModel;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.product_row, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Product product = productList.get(position);
        holder.productName.setText(product.getName());
//        holder.providerName.setText(productViewModel.getProvider(product.getName()).getValue().getName());
    }

    @Override
    public int getItemCount() {
        return productList.size();
    }

    public void setProductList(List<Product> productList) {
        this.productList = productList;
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView productName;
        private TextView providerName;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            productName = itemView.findViewById(R.id.productNameTV);
            providerName = itemView.findViewById(R.id.providerNameTV);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            onProductClickListener.onProductClick(getAdapterPosition());
        }
    }

    public interface OnProductClickListener { void onProductClick(int position); }
}
