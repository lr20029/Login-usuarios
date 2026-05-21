package com.example.login;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle("Home");
        }

        BottomNavigationView bottomNav = findViewById(R.id.bottomNavigationView);

        if (savedInstanceState == null) {
            reemplazarFragmento(new InicioFragment());
        }

        bottomNav.setOnItemSelectedListener(item -> {
            int id = item.getItemId();

            if (id == R.id.nav_inicio) {
                reemplazarFragmento(new InicioFragment());
            } else if (id == R.id.nav_productos) {
                reemplazarFragmento(new ProductosFragment());
            } else if (id == R.id.nav_perfil) {
                reemplazarFragmento(new PerfilFragment());
            }
            return true;
        });
    }
    private void reemplazarFragmento(Fragment fragmento) {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragmentContainerView, fragmento)
                .commit();
    }
}