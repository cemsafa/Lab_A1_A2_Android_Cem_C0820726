package com.cemsafa.lab_a1_a2_android_cem_c0820726;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

public class PagerAdapter extends FragmentStatePagerAdapter {

    private int tabCount;

    public PagerAdapter(FragmentManager fragmentManager, int tabCount) {
        super(fragmentManager);
        this.tabCount = tabCount;
    }

    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                ProviderFragment providerFragment = new ProviderFragment();
                return providerFragment;
            case 1:
                ProductFragment productFragment = new ProductFragment();
                return productFragment;
            default:
                return null;
        }
    }

    public int getCount() {
        return tabCount;
    }
}
