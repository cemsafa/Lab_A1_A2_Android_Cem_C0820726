package com.cemsafa.lab_a1_a2_android_cem_c0820726;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;

import com.cemsafa.lab_a1_a2_android_cem_c0820726.model.ProductViewModel;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements TabLayout.OnTabSelectedListener {

    private TabLayout tabLayout;
    private ViewPager viewPager;

    private ProductViewModel productViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        productViewModel = new ViewModelProvider.AndroidViewModelFactory(this.getApplication()).create(ProductViewModel.class);

        tabLayout = (TabLayout) findViewById(R.id.tabLayout);
        tabLayout.addTab(tabLayout.newTab().setText("Providers"));
        tabLayout.addTab(tabLayout.newTab().setText("Products"));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        viewPager = (ViewPager) findViewById(R.id.viewPager);
        PagerAdapter adapter = new PagerAdapter(getSupportFragmentManager(), tabLayout.getTabCount());
        viewPager.setAdapter(adapter);
        tabLayout.setOnTabSelectedListener(this);

        List<String> sample1 = new ArrayList<>();
        sample1.add("pen");
        productViewModel.insertProducts("BIC", sample1);
        List<String> sample2 = new ArrayList<>();
        sample2.add("laptop");
        productViewModel.insertProducts("HP", sample2);
        List<String> sample3 = new ArrayList<>();
        sample3.add("cell phone");
        productViewModel.insertProducts("Apple", sample3);
        List<String> sample4 = new ArrayList<>();
        sample4.add("freezer");
        productViewModel.insertProducts("Electrolux", sample4);
        List<String> sample5 = new ArrayList<>();
        sample5.add("tv");
        productViewModel.insertProducts("LG", sample5);
        List<String> sample6 = new ArrayList<>();
        sample6.add("game console");
        productViewModel.insertProducts("Sony", sample6);
        List<String> sample7 = new ArrayList<>();
        sample7.add("table");
        sample7.add("chair");
        sample7.add("desk");
        productViewModel.insertProducts("Ikea", sample7);
        List<String> sample8 = new ArrayList<>();
        sample8.add("couch");
        productViewModel.insertProducts("Structube", sample8);
    }

    @Override
    public void onTabSelected(TabLayout.Tab tab) {
        viewPager.setCurrentItem(tab.getPosition());
    }

    @Override
    public void onTabUnselected(TabLayout.Tab tab) {

    }

    @Override
    public void onTabReselected(TabLayout.Tab tab) {

    }
}