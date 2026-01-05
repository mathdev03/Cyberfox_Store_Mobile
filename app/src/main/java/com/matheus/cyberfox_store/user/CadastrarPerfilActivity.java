package com.matheus.cyberfox_store.user;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import com.matheus.cyberfox_store.R;
import com.matheus.cyberfox_store.dao.UserDao;
import com.matheus.cyberfox_store.model.Usuario;

public class CadastrarPerfilActivity extends AppCompatActivity {

    ImageView imgFoto;

    EditText edtPerfil;

    AppCompatButton btnCadastrar;

    Usuario user = new Usuario();

    private static final int PERMISSION_CODE = 100;
    private static final int REQUEST_CAMERA = 2;
    private static final int REQUEST_GALLERY = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastrar_perfil);

        imgFoto = (ImageView) findViewById(R.id.imgPerfil);

        edtPerfil = (EditText) findViewById(R.id.edtApelido);

        btnCadastrar = (AppCompatButton) findViewById(R.id.btnCadastrar);

        Glide.with(this)
                .load(R.drawable.ic_profile_placeholder)
                .apply(RequestOptions.circleCropTransform())
                .placeholder(R.drawable.ic_profile_placeholder)
                .error(R.drawable.ic_profile_placeholder)
                .into(imgFoto);

        // Receber o usuário

        Intent receberDados = getIntent();

        if(receberDados.hasExtra("objUsuario")){

            // Verificação de versão
            if(Build.VERSION.SDK_INT >= 33){
                user = receberDados.getSerializableExtra("objUsuario", Usuario.class);
            } else {
                user = (Usuario) receberDados.getSerializableExtra("objUsuario");
            }
        }

        imgFoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(VerificarPermissoes()){
                    AbrirGaleria();
                }
            }
        });

        btnCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                user.setUsuario(edtPerfil.getText().toString());
                user.setImagem(imgFoto.getImageAlpha());

                UserDao bd = new UserDao();
                bd.Inserir(user, CadastrarPerfilActivity.this);

                startActivity(new Intent(CadastrarPerfilActivity.this, LoginActivity.class));
                finish();
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(resultCode == RESULT_OK){
            if(requestCode == REQUEST_GALLERY){
                Uri imagem = data.getData();
                Glide.with(this)
                        .load(imagem)
                        .apply(RequestOptions.circleCropTransform())
                        .placeholder(R.drawable.ic_profile_placeholder)
                        .error(R.drawable.ic_profile_placeholder)
                        .into(imgFoto);

                Toast.makeText(this, "IMAGEM: " + imagem.toString(), Toast.LENGTH_LONG).show();
            } else if(requestCode == REQUEST_CAMERA){
                Bitmap foto = (Bitmap) data.getExtras().get("data");
                imgFoto.setImageBitmap(foto);
            }
        }
    }

    private boolean VerificarPermissoes(){
        if(ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED ||
                ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED){

            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.CAMERA}, PERMISSION_CODE);

            return false;
        }

        return true;
    }

    private void AbrirCamera() {
        Intent intentCamera = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intentCamera, REQUEST_CAMERA);
    }

    private void AbrirGaleria() {

        Intent intentGaleria = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        intentGaleria.setType("image/*"); // Filtra apenas imagens

        // Inicie a activity e espere o resultado
        startActivityForResult(intentGaleria, REQUEST_GALLERY);
    }
}