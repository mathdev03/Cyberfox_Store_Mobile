package com.matheus.cyberfox_store.user;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.matheus.cyberfox_store.R;
import com.matheus.cyberfox_store.dao.UserDao;
import com.matheus.cyberfox_store.model.Usuario;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class AltEmailActivity extends AppCompatActivity {

    EditText edtEmail;

    AppCompatButton btnAltEmail, btnVoltar;

    Usuario user = new Usuario();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alt_email);

        edtEmail = (EditText) findViewById(R.id.edtAltEmail);

        btnAltEmail = (AppCompatButton) findViewById(R.id.btnAltEmail);
        btnVoltar = (AppCompatButton) findViewById(R.id.btnVoltar);

        Intent receberDados = getIntent();

        if (receberDados.hasExtra("objUser")) {

            // Verificação de versão
            if (Build.VERSION.SDK_INT >= 33) {
                user = receberDados.getSerializableExtra("objUser", Usuario.class);
            } else {
                user = (Usuario) receberDados.getSerializableExtra("objUser");
            }
        }

        btnAltEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                UserDao usdao = new UserDao();

                if(edtEmail.getText().toString().contains("@")){
                    usdao.AtualizarEmail(AltEmailActivity.this, user, edtEmail.getText().toString());
                } else{
                    Toast.makeText(AltEmailActivity.this, "Email inválido", Toast.LENGTH_LONG).show();
                }

                finish();
            }
        });

        btnVoltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}