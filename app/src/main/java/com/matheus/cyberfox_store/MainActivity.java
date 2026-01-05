package com.matheus.cyberfox_store;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.appcompat.widget.AppCompatButton;

import com.matheus.cyberfox_store.user.CadastroUser_Activity;
import com.matheus.cyberfox_store.user.LoginActivity;

public class MainActivity extends AppCompatActivity {

    AppCompatButton btnCriar, btnLogar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnCriar = (AppCompatButton) findViewById(R.id.btnCriar);
        btnLogar = (AppCompatButton) findViewById(R.id.btnLogar);

        btnCriar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent janelacad = new Intent(MainActivity.this, CadastroUser_Activity.class);

                startActivity(janelacad);
            }
        });

        btnLogar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent janelalogin = new Intent(MainActivity.this, LoginActivity.class);

                startActivity(janelalogin);
                finish();
            }
        });
    }
}