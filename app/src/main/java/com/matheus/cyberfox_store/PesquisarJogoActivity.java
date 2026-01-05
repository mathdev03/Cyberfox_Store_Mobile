package com.matheus.cyberfox_store;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.koushikdutta.ion.builder.Builders;
import com.matheus.cyberfox_store.adapter.ListaArrayAdapterGames;
import com.matheus.cyberfox_store.dao.JogoDao;
import com.matheus.cyberfox_store.model.Jogos;
import com.matheus.cyberfox_store.model.Usuario;

import java.util.List;

public class PesquisarJogoActivity extends AppCompatActivity {



    ListView lsvJogos;

    EditText edtNomeP;

    ImageButton btnPesquisar;

    AppCompatButton btnVoltar;

    Usuario user = new Usuario();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pesquisar_jogo);

        lsvJogos = (ListView) findViewById(R.id.lsvJogo);

        btnPesquisar = (ImageButton) findViewById(R.id.btnPesquisar);
        btnVoltar = (AppCompatButton) findViewById(R.id.btnPaginaP);

        edtNomeP = (EditText) findViewById(R.id.edtNomeP);

        // Pegar o usuário

        Intent receberDados = getIntent();

        if (receberDados.hasExtra("objUser")) {

            // Verificação de versão
            if (Build.VERSION.SDK_INT >= 33) {
                user = receberDados.getSerializableExtra("objUser", Usuario.class);
            } else {
                user = (Usuario) receberDados.getSerializableExtra("objUser");
            }
        }

        // Instanciar

        btnPesquisar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String nomeJogo = edtNomeP.getText().toString();

                if(nomeJogo.isEmpty()){
                    JogoDao jdaoTodo = new JogoDao();
                    jdaoTodo.ListarJogos(PesquisarJogoActivity.this, new JogoDao.ChamarListaJogos() {
                        @Override
                        public void onSucesso(List<Jogos> ListaJogos) { Listar(ListaJogos);}

                        @Override
                        public void onErro(String mensagem) {

                        }
                    });
                } else {
                    JogoDao jdao = new JogoDao();
                    jdao.PesquisarJogo(PesquisarJogoActivity.this, edtNomeP.getText().toString(), new JogoDao.ChamarListaJogos() {
                        @Override
                        public void onSucesso(List<Jogos> ListaJogos) {
                            Listar(ListaJogos);
                        }

                        @Override
                        public void onErro(String mensagem) {

                        }
                    });
                }
            }
        });

        lsvJogos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> lista, View view, int i, long l) {
                Jogos game = (Jogos) lista.getItemAtPosition(i);

                Bundle bundle = new Bundle();
                bundle.putSerializable("objgame", game);
                bundle.putSerializable("objuser", user);

                // Abrir a janela do game!
                Intent janelagame = new Intent(PesquisarJogoActivity.this, GameActivity.class);
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