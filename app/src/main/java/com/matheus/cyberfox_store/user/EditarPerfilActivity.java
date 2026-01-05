package com.matheus.cyberfox_store.user;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.matheus.cyberfox_store.R;
import com.matheus.cyberfox_store.model.Usuario;

public class EditarPerfilActivity extends AppCompatActivity {

    AppCompatButton btnEmail, btnSenha, btnExcluir, btnVoltar;

    TextView TxtTituloUsuario;

    ImageView imgPerfil;

    Usuario user = new Usuario();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar_perfil);

        btnEmail = (AppCompatButton) findViewById(R.id.btnAltEmail);
        btnSenha = (AppCompatButton) findViewById(R.id.btnAltSenha);
        btnExcluir = (AppCompatButton) findViewById(R.id.btnExcluirConta);
        btnVoltar = (AppCompatButton) findViewById(R.id.btnVoltarProfile);

        imgPerfil = (ImageView) findViewById(R.id.imgPerfil);

        TxtTituloUsuario = (TextView) findViewById(R.id.txtTituloUsuario);

        Glide.with(this)
                .load(R.drawable.ic_profile_placeholder)
                .apply(RequestOptions.circleCropTransform())
                .placeholder(R.drawable.ic_profile_placeholder)
                .error(R.drawable.ic_profile_placeholder)
                .into(imgPerfil);

        // Pega o usuário

        Intent receberDados = getIntent();

        if (receberDados.hasExtra("objUser")) {

            // Verificação de versão
            if (Build.VERSION.SDK_INT >= 33) {
                user = receberDados.getSerializableExtra("objUser", Usuario.class);
            } else {
                user = (Usuario) receberDados.getSerializableExtra("objUser");
            }
        }

        TxtTituloUsuario.setText(user.getUsuario());

        btnEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent email = new Intent(EditarPerfilActivity.this, AltEmailActivity.class);

                email.putExtra("objUser", user);

                startActivity(email);
            }
        });

        btnSenha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent senha = new Intent(EditarPerfilActivity.this, AltSenhaActivity.class);

                senha.putExtra("objUser", user);

                startActivity(senha);
            }
        });

        btnExcluir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent senha = new Intent(EditarPerfilActivity.this, DeletarContaActivity.class);
                senha.putExtra("objUser", user);

                startActivity(senha);
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