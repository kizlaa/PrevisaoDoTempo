package com.lucas.previsaodotempo;

import android.content.Intent;
import android.os.Bundle;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.lucas.previsaodotempo.adapters.MainPagerAdapter;

public class MainActivity extends AppCompatActivity {

    private TabLayout tabLayout;
    private ViewPager2 viewPager;
    private FloatingActionButton fab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tabLayout = findViewById(R.id.tabLayout);
        viewPager = findViewById(R.id.viewPager);
        fab = findViewById(R.id.fab);

        viewPager.setAdapter(new MainPagerAdapter(this));

        new TabLayoutMediator(tabLayout, viewPager, (tab, position) -> {
            switch (position) {
                case 0:
                    tab.setText("PREVISÃO");
                    break;
                case 1:
                    tab.setText("MAPA");
                    break;
                case 2:
                    tab.setText("SOBRE");
                    break;
            }
        }).attach();

        fab.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, QrCodeActivity.class);
            startActivityForResult(intent, 100);
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 100 && resultCode == RESULT_OK && data != null) {
            String cidade = data.getStringExtra("cidade");
            if (cidade != null && !cidade.isEmpty()) {
                Intent i = new Intent(MainActivity.this, MainActivity.class);
                i.putExtra("cidade", cidade);
                startActivity(i);
                finish();
            }
        }
    }

    public static String getSavedCity(MainActivity activity) {
        if (activity == null) return "MaringaPR";
        String c = activity.getIntent() != null
                ? activity.getIntent().getStringExtra("cidade")
                : null;
        return (c == null || c.isEmpty()) ? "MaringaPR" : c;
    }
}
