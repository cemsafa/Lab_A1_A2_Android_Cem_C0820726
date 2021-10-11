package com.cemsafa.lab_a1_a2_android_cem_c0820726;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.cemsafa.lab_a1_a2_android_cem_c0820726.model.Product;
import com.cemsafa.lab_a1_a2_android_cem_c0820726.model.ProductViewModel;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ProductFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ProductFragment extends Fragment implements ProductRVAdapter.OnProductClickListener {

    private static final String PRODUCT_ID = "id";
    private ProductViewModel productViewModel;
    private List<Product> productList;

    private RecyclerView recyclerView;
    private ProductRVAdapter productRVAdapter;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public ProductFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ProductFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ProductFragment newInstance(String param1, String param2) {
        ProductFragment fragment = new ProductFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.product_tab, container, false);

        productViewModel = new ViewModelProvider.AndroidViewModelFactory(getActivity().getApplication()).create(ProductViewModel.class);

        recyclerView = view.findViewById(R.id.rvProducts);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity().getApplicationContext()));

        productViewModel.getAllProducts().observe(getViewLifecycleOwner(), products -> {
            productRVAdapter = new ProductRVAdapter(products, getActivity().getApplicationContext(), this, productViewModel);
            recyclerView.setAdapter(productRVAdapter);
        });

        return view;
    }

    @Override
    public void onProductClick(int position) {
        productToEdit(position);
    }

    private void productToEdit(int position) {
        Product product = productViewModel.getAllProducts().getValue().get(position);
        Intent intent = new Intent(getActivity(), ProductFragment.class);
        intent.putExtra(PRODUCT_ID, product.getId());
        startActivity(intent);
    }
}