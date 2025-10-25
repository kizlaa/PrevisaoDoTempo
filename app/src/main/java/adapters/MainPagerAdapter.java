package com.lucas.previsaodotempo.adapters;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.lucas.previsaodotempo.fragments.MapaFragment;
import com.lucas.previsaodotempo.fragments.PrevisaoFragment;
import com.lucas.previsaodotempo.fragments.SobreFragment;

public class MainPagerAdapter extends FragmentStateAdapter {

    public MainPagerAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position) {
            case 0:
                return new PrevisaoFragment();
            case 1:
                return new MapaFragment();
            case 2:
                return new SobreFragment();
            default:
                return new PrevisaoFragment();
        }
    }

    @Override
    public int getItemCount() {
        return 3;
    }
}

