package com.cemsafa.lab_a1_a2_android_cem_c0820726;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.cemsafa.lab_a1_a2_android_cem_c0820726.model.ProductViewModel;
import com.cemsafa.lab_a1_a2_android_cem_c0820726.model.Provider;

import java.util.List;

public class ProviderRVAdapter extends RecyclerView.Adapter<ProviderRVAdapter.ViewHolder> {

    private List<Provider> providerList;
    private Context context;
    private OnProviderClickListener onProviderClickListener;
    private ProductViewModel productViewModel;

    public ProviderRVAdapter(List<Provider> providerList, Context context, OnProviderClickListener onProviderClickListener, ProductViewModel productViewModel) {
        this.providerList = providerList;
        this.context = context;
        this.onProviderClickListener = onProviderClickListener;
        this.productViewModel = productViewModel;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.provider_row, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Provider provider = providerList.get(position);
        holder.providerName.setText(provider.getName());
        holder.productCount.setText(productViewModel.getAllProducts().getValue().size());
    }

    @Override
    public int getItemCount() {
        return providerList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView providerName;
        private TextView productCount;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            providerName = itemView.findViewById(R.id.providerNameTV);
            productCount = itemView.findViewById(R.id.productCountTV);

            itemView.setOnClickListener(this);
        }


        @Override
        public void onClick(View view) {
            onProviderClickListener.onProviderClick(getAdapterPosition());
        }
    }

    public interface OnProviderClickListener { void onProviderClick(int position); }
}
