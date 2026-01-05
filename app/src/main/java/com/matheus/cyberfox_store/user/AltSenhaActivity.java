package com.matheus.cyberfox_store.user;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.matheus.cyberfox_store.R;
import com.matheus.cyberfox_store.dao.UserDao;
import com.matheus.cyberfox_store.model.Usuario;

public class AltSenhaActivity extends AppCompatActivity {

    EditText edtSenhaA, edtSenhaN;

    AppCompatButton btnAltSenha, btnVoltar;

    Usuario user = new Usuario();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alt_senha);

        edtSenhaA = (EditText) findViewById(R.id.edtSenhaA);
        edtSenhaN = (EditText) findViewById(R.id.edtSenhaN);

        btnAltSenha = (AppCompatButton) findViewById(R.id.btnAltSenha);
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


        btnVoltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        btnAltSenha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                UserDao usudao = new UserDao();

                usudao.AtualizarSenha(AltSenhaActivity.this, user,
                        edtSenhaN.getText().toString(), edtSenhaA.getText().toString());
                finish();
            }
        });
    }
}