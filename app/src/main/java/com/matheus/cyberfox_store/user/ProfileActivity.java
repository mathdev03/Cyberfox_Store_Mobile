package com.matheus.cyberfox_store.user;

import android.content.Intent;
import android.media.Image;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
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
import com.matheus.cyberfox_store.BiblitocaActivity;
import com.matheus.cyberfox_store.MainActivity;
import com.matheus.cyberfox_store.R;
import com.matheus.cyberfox_store.dashboardActivity;
import com.matheus.cyberfox_store.model.Usuario;

import org.w3c.dom.Text;

public class ProfileActivity extends AppCompatActivity {

    TextView txtTituloUsuario;

    AppCompatButton btnBiblioteca, btnSair, btnVoltarProfile, btnEditPerfil;

    ImageView imgPerfil;

    Usuario user = new Usuario();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        txtTituloUsuario = (TextView) findViewById(R.id.txtTituloUsuario);

        btnBiblioteca = (AppCompatButton) findViewById(R.id.btnBiblioteca);
        btnSair = (AppCompatButton) findViewById(R.id.btnSair);
        btnVoltarProfile = (AppCompatButton) findViewById(R.id.btnVoltarProfile);
        btnEditPerfil = (AppCompatButton) findViewById(R.id.btnEditPerfil);

        imgPerfil = (ImageView) findViewById(R.id.imgPerfil);


        Glide.with(this)
                .load(R.drawable.ic_profile_placeholder)
                .apply(RequestOptions.circleCropTransform())
                .placeholder(R.drawable.ic_profile_placeholder)
                .error(R.drawable.ic_profile_placeholder)
                .into(imgPerfil);

        Intent receberDados = getIntent();

        if (receberDados.hasExtra("objUser")) {

            // Verificação de versão
            if (Build.VERSION.SDK_INT >= 33) {
                user = receberDados.getSerializableExtra("objUser", Usuario.class);
            } else {
                user = (Usuario) receberDados.getSerializableExtra("objUser");
            }
        }

        txtTituloUsuario.setText(user.getUsuario());

        btnBiblioteca.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent bio = new Intent(ProfileActivity.this, BiblitocaActivity.class);
                bio.putExtra("objUser", user);

                startActivity(bio);
            }
        });

        btnSair.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ProfileActivity.this, MainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
                finish();
            }
        });

        btnVoltarProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        btnEditPerfil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent editar = new Intent(ProfileActivity.this, EditarPerfilActivity.class);
                editar.putExtra("objUser", user);

                startActivity(editar);
            }
        });
    }
}