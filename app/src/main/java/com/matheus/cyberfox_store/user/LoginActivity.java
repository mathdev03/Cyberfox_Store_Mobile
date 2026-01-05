package com.matheus.cyberfox_store.user;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import com.matheus.cyberfox_store.MainActivity;
import com.matheus.cyberfox_store.R;
import com.matheus.cyberfox_store.dao.UserDao;
import com.matheus.cyberfox_store.dashboardActivity;
import com.matheus.cyberfox_store.model.Usuario;

public class LoginActivity extends AppCompatActivity {

    EditText edtUser, edtSenha;

    AppCompatButton btnLogar, btnVoltar;

    TextView txtCadastrar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        edtUser = (EditText) findViewById(R.id.edtUser);
        edtSenha = (EditText) findViewById(R.id.edtSenha);

        btnLogar = (AppCompatButton) findViewById(R.id.btnLogar);
        btnVoltar = (AppCompatButton) findViewById(R.id.btnVoltar);

        txtCadastrar = (TextView) findViewById(R.id.txtCadastrar);


        btnLogar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Usuario user = new Usuario();

                user.setUsuario(edtUser.getText().toString());
                user.setEmail(edtUser.getText().toString());
                user.setSenha(edtSenha.getText().toString());

                UserDao dados = new UserDao();

                dados.ListaUSU(user, LoginActivity.this, new UserDao.ChamarLogin() {
                    @Override
                    public void onSucesso(Usuario userlog) {
                        Toast.makeText(LoginActivity.this, "Bem vindo " + userlog.getUsuario(), Toast.LENGTH_LONG).show();

                        Intent dados = new Intent(LoginActivity.this, dashboardActivity.class);
                        dados.putExtra("objUser", userlog);

                        startActivity(dados);
                        finish();
                    }

                    @Override
                    public void onErro(String mensagem) {
                        Toast.makeText(LoginActivity.this, mensagem, Toast.LENGTH_LONG).show();
                    }
                });
            }
        });

        btnVoltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LoginActivity.this, MainActivity.class));
                finish();
            }
        });

        txtCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LoginActivity.this, CadastroUser_Activity.class));
                finish();
            }
        });
    }
}