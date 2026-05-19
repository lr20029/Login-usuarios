package com.example.login;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Configurar Toolbar
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // Botón Ingresar
        Button btnIngresar = findViewById(R.id.button);
        btnIngresar.setOnClickListener(v -> validarUsuario());

        // Botón Salir
        Button btnSalir = findViewById(R.id.button2);
        btnSalir.setOnClickListener(v -> finishAffinity());
    }

    // Método para validar usuario y contraseña
    private void validarUsuario() {
        EditText etUsuario = findViewById(R.id.editTextText);
        EditText etPassword = findViewById(R.id.editTextTextPassword);

        String usuario = etUsuario.getText().toString().trim();
        String password = etPassword.getText().toString().trim();

        if (usuario.isEmpty() || password.isEmpty()) {
            Toast.makeText(this, "Usuario y password son obligatorios", Toast.LENGTH_SHORT).show();
            return;
        }

        // Obtener SharedPreferences
        SharedPreferences preferences = getSharedPreferences("Usuarios", Context.MODE_PRIVATE);
        String usuarioGuardado = preferences.getString(usuario, "");
        String passwordGuardado = preferences.getString(usuario + "_password", "");

        if (usuarioGuardado.equals(usuario) && passwordGuardado.equals(password)) {
            // Usuario y password correctos, abrir HomeActivity
            Intent intent = new Intent(MainActivity.this, HomeActivity.class);
            startActivity(intent);
            finish(); // Cierra el Login
        } else {
            Toast.makeText(this, "Error: Usuario y clave inválidos", Toast.LENGTH_SHORT).show();
        }
    }

    // Inflar el menú
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    // Manejar clics en el menú
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_registrar) {
            Intent intent = new Intent(MainActivity.this, RegisterActivity.class);
            startActivity(intent);
            return true;
        } else if (id == R.id.action_salir) {
            finishAffinity();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}