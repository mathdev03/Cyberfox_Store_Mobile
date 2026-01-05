package com.matheus.cyberfox_store;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import com.matheus.cyberfox_store.adapter.ListaArrayAdapterGames;
import com.matheus.cyberfox_store.dao.JogoDao;
import com.matheus.cyberfox_store.model.Jogos;
import com.matheus.cyberfox_store.model.Usuario;

import java.util.List;

public class BiblitocaActivity extends AppCompatActivity {

    ListView lsvJogos;

    AppCompatButton btnVoltar;

    Usuario user = new Usuario();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_biblitoca);

        btnVoltar = (AppCompatButton) findViewById(R.id.btnVoltar);

        Intent receberDados = getIntent();

        if (receberDados.hasExtra("objUser")) {

            // Verificação de versão
            if (Build.VERSION.SDK_INT >= 33) {
                user = receberDados.getSerializableExtra("objUser", Usuario.class);
            } else {
                user = (Usuario) receberDados.getSerializableExtra("objUser");
            }

            lsvJogos = (ListView) findViewById(R.id.lsvJogos);

            JogoDao jdao = new JogoDao();
            jdao.Biblioteca(BiblitocaActivity.this, user, new JogoDao.ChamarListaJogos() {
                @Override
                public void onSucesso(List<Jogos> ListaJogos) {
                    Listar(ListaJogos);
                }

                @Override
                public void onErro(String mensagem) {

                }
            });
        }

        lsvJogos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> lista, View view, int i, long l) {
                Jogos game = (Jogos) lista.getItemAtPosition(i);

                Bundle bundle = new Bundle();
                bundle.putSerializable("objgame", game);
                bundle.putSerializable("objuser", user);

                // Abrir a janela do game!
                Intent janelagame = new Intent(BiblitocaActivity.this, GameActivity.class);
                janelagame.putExtras(bundle);

                startActivity(janelagame);
            }
        });

        btnVoltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    public void Listar(List<Jogos> j){

        try {
            List<Jogos> lista = j;

            ListaArrayAdapterGames adaptador = new ListaArrayAdapterGames(this, R.layout.item_lista,
                    lista);

            lsvJogos.setAdapter(adaptador);
        }catch (Exception e) {
            Log.e("MainActivity", "Erro no adapter", e);
        }
    }
}