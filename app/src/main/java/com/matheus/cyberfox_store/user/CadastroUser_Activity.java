package com.matheus.cyberfox_store.user;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import com.matheus.cyberfox_store.R;
import com.matheus.cyberfox_store.model.Usuario;

public class CadastroUser_Activity extends AppCompatActivity {

    EditText edtEmail, edtSenha;

    AppCompatButton btnVoltar, btnCadastrar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_user);

        btnVoltar = (AppCompatButton) findViewById(R.id.btnVoltar);
        btnCadastrar = (AppCompatButton) findViewById(R.id.btnCadastrar);

        edtEmail = (EditText) findViewById(R.id.edtEmail);
        edtSenha = (EditText) findViewById(R.id.edtSenha);

        btnVoltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        btnCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Usuario user = new Usuario();

                user.setEmail(edtEmail.getText().toString());
                user.setSenha(edtSenha.getText().toString());

                Intent perfil = new Intent(CadastroUser_Activity.this, CadastrarPerfilActivity.class);
                perfil.putExtra("objUsuario" , user);

                startActivity(perfil);
                finish();
            }
        });
    }
}