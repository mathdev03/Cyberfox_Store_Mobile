package com.matheus.cyberfox_store.dao;

import android.content.Context;
import android.net.Uri;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Toast;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;
import com.matheus.cyberfox_store.model.Jogos;
import com.matheus.cyberfox_store.model.Usuario;

import java.util.ArrayList;
import java.util.List;

public class JogoDao {

    private static String ip = "Seu Endereço IP";
    private static String base = "http://"+ip+":/mobilecyberfox";

    public JogoDao(){

    }

    public interface ChamarListaJogos{
        void onSucesso(List<Jogos> ListaJogos);
        void onErro(String mensagem);
    }

    public void Listar(){

    }

    public void ListarJogos(Context c, ChamarListaJogos chamar){
        String url = base + "/ListarJogo.php";

        List<Jogos> jogos = new ArrayList<Jogos>();

        Ion.with(c)
                .load("GET", url)
                .asJsonArray()
                .setCallback(new FutureCallback<JsonArray>() {
                    @Override
                    public void onCompleted(Exception e, JsonArray lista) {
                        if (e != null) {
                            Toast.makeText(c, "Erro ao obter dados\n" + e.getMessage(), Toast.LENGTH_LONG).show();
                            return;
                        }
                        if (lista != null) {
                            for (JsonElement item : lista) {
                                JsonObject obj = item.getAsJsonObject();

                                int id = obj.has("id_jogo") ? obj.get("id_jogo").getAsInt() : 0;
                                String nome = obj.has("nome_jogo") ? obj.get("nome_jogo").getAsString().replace("'", "") : "Sem nome";
                                String desc = obj.has("descricao_jogo") ? obj.get("descricao_jogo").getAsString().replace("'", "") : "Sem descrição";
                                float preco = obj.has("preco_jogo") ? obj.get("preco_jogo").getAsFloat() : 0;

                                Jogos game = new Jogos();
                                game.setId(id);
                                game.setNome(nome);
                                game.setDescricao(desc);
                                game.setPreco(preco);

                                jogos.add(game);
                            }

                            chamar.onSucesso(jogos);
                        } else {
                            Toast.makeText(c, "Sem usuários", Toast.LENGTH_LONG).show();
                        }
                    }
                });
    }


    public void ComprarJogo(Context c, Jogos game, Usuario user){
        String iduser = String.valueOf(user.getId());
        String idgame = String.valueOf(game.getId());

        Toast.makeText(c, iduser, Toast.LENGTH_LONG).show();

        String url = base + "/ComprarJogo.php";

        Ion.with(c)
                .load(url)
                .setBodyParameter("Idjogo", idgame)
                .setBodyParameter("Iduser", iduser)
                .asJsonObject()
                .setCallback(new FutureCallback<JsonObject>() {
                    @Override
                    public void onCompleted(Exception e, JsonObject result) {
                        if(e != null){
                            Toast.makeText(c, "Erro ao cadastrar, Mensagem: " + e.getMessage(), Toast.LENGTH_LONG).show();
                        } else{
                            if(result.get("INSERT").getAsString().equals("OK")){
                                Toast.makeText(c, "jogo inserido com sucesso!", Toast.LENGTH_LONG).show();
                            } else {
                                Toast.makeText(c, "Erro ao inserir!", Toast.LENGTH_LONG).show();
                            }
                        }
                    }
                });
    }

    public void Biblioteca(Context c, Usuario user, ChamarListaJogos chamar){

        String iduser = String.valueOf(user.getId());
        String url = base + "/ListarBiblioteca.php";

        List<Jogos> jogos = new ArrayList<Jogos>();

        Ion.with(c)
                .load("GET", url)
                .addQuery("Iduser",iduser)
                .asJsonArray()
                .setCallback(new FutureCallback<JsonArray>() {
                    @Override
                    public void onCompleted(Exception e, JsonArray lista) {
                        if (e != null) {
                            Toast.makeText(c, "Erro ao obter dados\n" + e.getMessage(), Toast.LENGTH_LONG).show();
                            return;
                        }
                        if (lista != null) {
                            for (JsonElement item : lista) {
                                JsonObject obj = item.getAsJsonObject();

                                int id = obj.has("id_jogo") ? obj.get("id_jogo").getAsInt() : 0;
                                String nome = obj.has("nome_jogo") ? obj.get("nome_jogo").getAsString().replace("'", "") : "Sem nome";
                                String desc = obj.has("descricao_jogo") ? obj.get("descricao_jogo").getAsString().replace("'", "") : "Sem descrição";
                                float preco = obj.has("preco_jogo") ? obj.get("preco_jogo").getAsFloat() : 0;

                                Jogos game = new Jogos();
                                game.setId(id);
                                game.setNome(nome);
                                game.setDescricao(desc);
                                game.setPreco(preco);

                                jogos.add(game);
                            }

                            chamar.onSucesso(jogos);
                        } else {
                            Toast.makeText(c, "Sem usuários", Toast.LENGTH_LONG).show();
                        }
                    }
                });
    }

    public void FiltrarCategoria(Context c, String categ, ChamarListaJogos chamar){

        String url = base + "/FiltrarCategoria.php";

        List<Jogos> jogos = new ArrayList<Jogos>();

        Ion.with(c)
                .load("GET", url)
                .addQuery("CategNome",categ)
                .asJsonArray()
                .setCallback(new FutureCallback<JsonArray>() {
                    @Override
                    public void onCompleted(Exception e, JsonArray lista) {
                        if (e != null) {
                            Toast.makeText(c, "Erro ao obter dados\n" + e.getMessage(), Toast.LENGTH_LONG).show();
                            return;
                        }
                        if (lista != null) {
                            for (JsonElement item : lista) {
                                JsonObject obj = item.getAsJsonObject();

                                int id = obj.has("id_jogo") ? obj.get("id_jogo").getAsInt() : 0;
                                String nome = obj.has("nome_jogo") ? obj.get("nome_jogo").getAsString().replace("'", "") : "Sem nome";
                                String desc = obj.has("descricao_jogo") ? obj.get("descricao_jogo").getAsString().replace("'", "") : "Sem descrição";
                                float preco = obj.has("preco_jogo") ? obj.get("preco_jogo").getAsFloat() : 0;

                                Jogos game = new Jogos();
                                game.setId(id);
                                game.setNome(nome);
                                game.setDescricao(desc);
                                game.setPreco(preco);

                                jogos.add(game);
                            }

                            chamar.onSucesso(jogos);
                        } else {
                            Toast.makeText(c, "Sem usuários", Toast.LENGTH_LONG).show();
                        }
                    }
                });
    }

    public void PesquisarJogo(Context c, String pesq, ChamarListaJogos chamar){
        String url = base + "/PesquisaJogo.php";

        pesq = pesq + "%";

        List<Jogos> jogos = new ArrayList<Jogos>();

        Ion.with(c)
                .load("GET", url)
                .addQuery("Pesquisar",pesq)
                .asJsonArray()
                .setCallback(new FutureCallback<JsonArray>() {
                    @Override
                    public void onCompleted(Exception e, JsonArray lista) {
                        if (e != null) {
                            Toast.makeText(c, "Erro ao obter dados\n" + e.getMessage(), Toast.LENGTH_LONG).show();
                            return;
                        }
                        if (lista != null) {
                            for (JsonElement item : lista) {
                                JsonObject obj = item.getAsJsonObject();

                                int id = obj.has("id_jogo") ? obj.get("id_jogo").getAsInt() : 0;
                                String nome = obj.has("nome_jogo") ? obj.get("nome_jogo").getAsString().replace("'", "") : "Sem nome";
                                String desc = obj.has("descricao_jogo") ? obj.get("descricao_jogo").getAsString().replace("'", "") : "Sem descrição";
                                float preco = obj.has("preco_jogo") ? obj.get("preco_jogo").getAsFloat() : 0;

                                Jogos game = new Jogos();
                                game.setId(id);
                                game.setNome(nome);
                                game.setDescricao(desc);
                                game.setPreco(preco);

                                jogos.add(game);
                            }

                            chamar.onSucesso(jogos);
                        } else {
                            Toast.makeText(c, "Sem usuários", Toast.LENGTH_LONG).show();
                        }
                    }
                });
    }
}
