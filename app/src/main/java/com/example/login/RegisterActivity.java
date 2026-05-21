package com.example.login;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import java.util.regex.Pattern;

public class RegisterActivity extends AppCompatActivity {

    private EditText etNombreUsuario, etEmail, etPassword, etConfirmarPassword;
    private Button btnGuardar, btnRegresar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        // Configurar Toolbar
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayShowTitleEnabled(false);
        }

        // Vincular elementos de la UI
        etNombreUsuario = findViewById(R.id.etNombreUsuario);
        etEmail = findViewById(R.id.etEmail);
        etPassword = findViewById(R.id.etPassword);
        etConfirmarPassword = findViewById(R.id.etConfirmarPassword);
        btnGuardar = findViewById(R.id.btnGuardar);
        btnRegresar = findViewById(R.id.btnRegresar);

        // Botón Guardar
        btnGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registrarUsuario();
            }
        });

        // Botón Regresar
        btnRegresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish(); // Cierra la actividad y regresa al Login
            }
        });
    }

    // Método para registrar usuario con validaciones
    private void registrarUsuario() {
        String nombreUsuario = etNombreUsuario.getText().toString().trim();
        String email = etEmail.getText().toString().trim();
        String password = etPassword.getText().toString().trim();
        String confirmarPassword = etConfirmarPassword.getText().toString().trim();

        // Validación 1: Nombre de usuario (mínimo 3 caracteres)
        if (nombreUsuario.length() < 3) {
            Toast.makeText(this, "El nombre de usuario debe tener al menos 3 caracteres", Toast.LENGTH_SHORT).show();
            return;
        }

        // Validación 2: Email (formato correcto)
        if (!isValidEmail(email)) {
            Toast.makeText(this, "El email no tiene un formato válido", Toast.LENGTH_SHORT).show();
            return;
        }

        // Validación 3: Password (mínimo 5 caracteres alfanuméricos)
        if (password.length() < 5 || !password.matches("^[a-zA-Z0-9]+$")) {
            Toast.makeText(this, "El password debe tener al menos 5 caracteres alfanuméricos", Toast.LENGTH_SHORT).show();
            return;
        }

        // Validación 4: Confirmar password
        if (!password.equals(confirmarPassword)) {
            Toast.makeText(this, "Los passwords no coinciden", Toast.LENGTH_SHORT).show();
            return;
        }

        // Guardar en SharedPreferences
        SharedPreferences preferences = getSharedPreferences("Usuarios", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(nombreUsuario, nombreUsuario);
        editor.putString(nombreUsuario + "_password", password);
        editor.putString(nombreUsuario + "_email", email);
        editor.apply();

        // Mensaje de éxito
        Toast.makeText(this, "Usuario registrado con éxito", Toast.LENGTH_SHORT).show();

        // Limpiar campos
        etNombreUsuario.setText("");
        etEmail.setText("");
        etPassword.setText("");
        etConfirmarPassword.setText("");
    }

    // Método para validar formato de email
    private boolean isValidEmail(String email) {
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
        Pattern pattern = Pattern.compile(emailRegex);
        return pattern.matcher(email).matches();
    }
}