package com.matheus.cyberfox_store;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.provider.MediaStore;
import android.view.View;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.matheus.cyberfox_store.model.Jogos;
import com.matheus.cyberfox_store.dao.JogoDao;
import com.matheus.cyberfox_store.model.Usuario;
import com.matheus.cyberfox_store.user.ProfileActivity;

import java.util.List;

public class dashboardActivity extends AppCompatActivity {

    ImageButton btnAntes, btnProximo, navBiblioteca, navPesquisar, navPerfil;

    TextView txtNomeJogo, txtTituloDash;

    RadioGroup rgCategoria;

    AppCompatButton btnDetalheJogo;

    int count;

    Usuario user = new Usuario();
    Jogos[] infoJogo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        btnAntes = (ImageButton) findViewById(R.id.btnAntes);
        btnProximo = (ImageButton) findViewById(R.id.btnProximo);
        btnDetalheJogo = (AppCompatButton) findViewById(R.id.btnDetalhesJogo);

        navBiblioteca = (ImageButton) findViewById(R.id.navBiblioteca);
        navPesquisar = (ImageButton) findViewById(R.id.navPesquisar);
        navPerfil = (ImageButton) findViewById(R.id.navProfile);


        rgCategoria = (RadioGroup) findViewById(R.id.rgCategorias);

        txtNomeJogo = (TextView) findViewById(R.id.txtNomeJogo);
        txtTituloDash = (TextView) findViewById(R.id.txtTituloDash);


        JogoDao listar = new JogoDao();

        listar.ListarJogos(dashboardActivity.this, new JogoDao.ChamarListaJogos() {
            @Override
            public void onSucesso(List<Jogos> ListaJogos) {
                // Contar quantos jogos possui
                for(int i = 0; i < (ListaJogos.size() - 1); i++){
                    count++;
                }

                // Transformar em array
                infoJogo = ListaJogos.toArray(new Jogos[0]);

                txtNomeJogo.setText(infoJogo[count].getNome());
                btnProximo.setEnabled(false);

                // Receber dados do usuário!
                Intent receberDados = getIntent();

                if(receberDados.hasExtra("objUser")){

                    // Verificação de versão
                    if(Build.VERSION.SDK_INT >= 33){
                        user = receberDados.getSerializableExtra("objUser", Usuario.class);
                    } else {
                        user = (Usuario) receberDados.getSerializableExtra("objUser");
                    }
                }

                Toast.makeText(dashboardActivity.this, String.valueOf(user.getId()), Toast.LENGTH_LONG).show();


                // Definir os botões
                btnAntes.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        count --;

                        if(count == -1){
                            btnAntes.setEnabled(false);
                            return;
                        } else if(count == (infoJogo.length - 2)){
                            btnProximo.setEnabled(true);
                        }

                        txtNomeJogo.setText(infoJogo[count].getNome());
                    }
                });

                btnProximo.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        count ++;

                        if(count == (infoJogo.length - 1)) {
                            btnProximo.setEnabled(false);
                        } else if(count == 1){
                            btnAntes.setEnabled(true);
                        }

                        txtNomeJogo.setText(infoJogo[count].getNome());
                    }
                });


                btnDetalheJogo.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        String nome = txtNomeJogo.getText().toString();

                        Jogos game = new Jogos();



                        for(Jogos j : infoJogo){
                            if(nome.equals(j.getNome())){
                                game = j;
                                break;
                            }
                        }

                        Bundle bundle = new Bundle();
                        bundle.putSerializable("objgame", game);
                        bundle.putSerializable("objuser", user);

                        // Abrir a janela do game!
                        Intent janelagame = new Intent(dashboardActivity.this, GameActivity.class);
                        janelagame.putExtras(bundle);

                        startActivity(janelagame);
                    }
                });

                // Radios buttons
                rgCategoria.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(@NonNull RadioGroup grupo, int i) {

                            String filtro = "";

                            if(i  == R.id.rbRpg){
                                txtTituloDash.setText("RPG");

                                filtro = "RPG";

                            } else if(i == R.id.rbAventura){
                                txtTituloDash.setText("AVENTURA");

                                filtro = "Aventura";

                            }else if(i == R.id.rbCasual){
                                txtTituloDash.setText("CASUAL");

                                filtro = "Casual";

                            }else if(i == R.id.rbEsporte){
                                txtTituloDash.setText("ESPORTE");

                                filtro = "Esporte";
                            }

                            JogoDao jdao = new JogoDao();
                            jdao.FiltrarCategoria(dashboardActivity.this, filtro, new JogoDao.ChamarListaJogos() {
                                @Override
                                public void onSucesso(List<Jogos> fav) {

                                    count = 0;

                                    // Mudando a lista por categoria!
                                    for(int i = 0; i < (fav.size() - 1); i++){
                                        count++;
                                    }
                                    infoJogo = fav.toArray(new Jogos[0]);

                                    txtNomeJogo.setText(infoJogo[count].getNome());
                                    btnProximo.setEnabled(false);
                                    btnAntes.setEnabled(true);
                                }

                                @Override
                                public void onErro(String mensagem) {

                                }
                            });
                    }
                });

                // Botões abaixo
                navBiblioteca.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent bio = new Intent(dashboardActivity.this, BiblitocaActivity.class);
                        bio.putExtra("objUser", user);

                        startActivity(bio);
                    }
                });

                navPesquisar.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent pes = new Intent(dashboardActivity.this, PesquisarJogoActivity.class);
                        pes.putExtra("objUser", user);

                        startActivity(pes);
                    }
                });

                navPerfil.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent perfil = new Intent(dashboardActivity.this, ProfileActivity.class);
                        perfil.putExtra("objUser", user);

                        startActivity(perfil);
                    }
                });
            }



            @Override
            public void onErro(String mensagem) {

            }
        });

    }


    private void filtrar(String name){

    }
}