package com.matheus.cyberfox_store;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.koushikdutta.async.future.Converter;
import com.matheus.cyberfox_store.dao.JogoDao;
import com.matheus.cyberfox_store.model.Jogos;
import com.matheus.cyberfox_store.model.Usuario;

import org.w3c.dom.Text;

import java.util.List;

public class GameActivity extends AppCompatActivity {

    AppCompatButton btnVoltar, btnComprar;

    TextView txtNome, txtDesc, txtPreco;

    Jogos game = new Jogos();
    Usuario user = new Usuario();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        btnComprar = (AppCompatButton) findViewById(R.id.btnComprar);
        btnVoltar = (AppCompatButton) findViewById(R.id.btnVoltar);

        txtNome = (TextView) findViewById(R.id.txtTituloGame);
        txtDesc = (TextView) findViewById(R.id.txtDescricao);
        txtPreco = (TextView) findViewById(R.id.txtPreco);

        Bundle bundle = getIntent().getExtras();

        if(bundle != null){
            // Verificação de versão
            if(Build.VERSION.SDK_INT >= 33){
                game = bundle.getSerializable("objgame", Jogos.class);
                user = bundle.getSerializable("objuser", Usuario.class);
            } else {
                game = (Jogos) bundle.getSerializable("objgame");
                user = (Usuario) bundle.getSerializable("objuser");
            }
        }



        txtNome.setText(game.getNome());
        txtDesc.setText(game.getDescricao());
        txtPreco.setText(String.valueOf(game.getPreco()));

        // Pegar lista de Jogos já comprados!

        JogoDao jdao = new JogoDao();

        jdao.Biblioteca(GameActivity.this, user, new JogoDao.ChamarListaJogos() {
            @Override
            public void onSucesso(List<Jogos> ListaJogos) {
                for(Jogos item : ListaJogos){
                    if(item.getId() == game.getId()){
                        btnComprar.setEnabled(false);
                        btnComprar.setText("Já adquirido!");
                    }
                }
            }

            @Override
            public void onErro(String mensagem) {

            }
        });

        btnComprar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                JogoDao comprar = new JogoDao();

                comprar.ComprarJogo(GameActivity.this, game ,user);

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