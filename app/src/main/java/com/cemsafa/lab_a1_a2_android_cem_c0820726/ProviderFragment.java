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

import com.cemsafa.lab_a1_a2_android_cem_c0820726.model.ProductViewModel;
import com.cemsafa.lab_a1_a2_android_cem_c0820726.model.Provider;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ProviderFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ProviderFragment extends Fragment implements ProviderRVAdapter.OnProviderClickListener {

    private static final String PROVIDER_ID = "id";
    private ProductViewModel productViewModel;
    private List<Provider> providerList;

    private RecyclerView recyclerView;
    private ProviderRVAdapter providerRVAdapter;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public ProviderFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ProviderFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ProviderFragment newInstance(String param1, String param2) {
        ProviderFragment fragment = new ProviderFragment();
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
        View view = inflater.inflate(R.layout.provider_tab, container, false);

        productViewModel = new ViewModelProvider.AndroidViewModelFactory(getActivity().getApplication()).create(ProductViewModel.class);

        recyclerView = view.findViewById(R.id.rvProviders);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity().getApplicationContext()));

        productViewModel.getAllProviders().observe(getViewLifecycleOwner(), providers -> {
            providerRVAdapter = new ProviderRVAdapter(providers, getActivity().getApplicationContext(), this, productViewModel);
            recyclerView.setAdapter(providerRVAdapter);
        });

        return view;
    }

    @Override
    public void onProviderClick(int position) {
        providerToEdit(position);
    }

    private void providerToEdit(int position) {
        Provider provider = productViewModel.getAllProviders().getValue().get(position);
        Intent intent = new Intent(getActivity(), ProviderFragment.class);
        intent.putExtra(PROVIDER_ID, provider.getId());
        startActivity(intent);
    }
}