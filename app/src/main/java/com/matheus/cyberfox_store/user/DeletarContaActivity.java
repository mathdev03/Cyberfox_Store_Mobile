package com.matheus.cyberfox_store.user;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.matheus.cyberfox_store.MainActivity;
import com.matheus.cyberfox_store.R;
import com.matheus.cyberfox_store.dao.UserDao;
import com.matheus.cyberfox_store.model.Usuario;

public class DeletarContaActivity extends AppCompatActivity {

    AppCompatButton btnExcluir, btnVoltar;

    Usuario user = new Usuario();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_deletar_conta);

        btnExcluir = (AppCompatButton) findViewById(R.id.btnExcluir);
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

        btnExcluir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                UserDao userdao = new UserDao();

                userdao.DeletarConta(DeletarContaActivity.this, user);

                Intent intent = new Intent(DeletarContaActivity.this, MainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
                finish();
            }
        });
    }
}