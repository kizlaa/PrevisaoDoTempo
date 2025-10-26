package com.lucas.previsaodotempo.adapters;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.lucas.previsaodotempo.fragments.MapFragment;
import com.lucas.previsaodotempo.fragments.WeatherListFragment;

public class MainPagerAdapter extends FragmentStateAdapter {

    public MainPagerAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        return position == 0 ? new WeatherListFragment() : new MapFragment();
    }

    @Override
    public int getItemCount() {
        return 2;
    }
}
