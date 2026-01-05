package com.matheus.cyberfox_store.dao;

import android.content.Context;
import android.text.TextUtils;
import android.widget.Toast;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;
import com.matheus.cyberfox_store.model.Usuario;
import com.matheus.cyberfox_store.user.DeletarContaActivity;

import java.net.InetAddress;
import java.net.UnknownHostException;

import java.util.ArrayList;
import java.util.List;

public class UserDao {

    private static String ip = "192.168.0.107";
    private static String base = "http://"+ip+":/mobilecyberfox";

    public UserDao(){
    }

    public interface ChamarLogin{
        void onSucesso(Usuario userlog);
        void onErro(String mensagem);
    }

    // Inserção do usuário
    public void Inserir(Usuario us, Context c){
        String nome = us.getUsuario();
        String email = us.getEmail();
        String senha = us.getSenha();


        if(TextUtils.isEmpty(nome.toString())){
            Toast.makeText(c, "Nome não informado", Toast.LENGTH_LONG).show();
        } else if(TextUtils.isEmpty(email.toString())){
            Toast.makeText(c, "Email não informado", Toast.LENGTH_LONG).show();
        } else if(TextUtils.isEmpty(senha.toString())){
            Toast.makeText(c, "Senha não informado", Toast.LENGTH_LONG).show();
        } else {
            String url = base + "/inserirUsuario.php";

            Ion.with(c)
                    .load(url)
                    .setBodyParameter("Txtnome", nome)
                    .setBodyParameter("Txtemail", email)
                    .setBodyParameter("Txtsenha", senha)
                    .asJsonObject()
                    .setCallback(new FutureCallback<JsonObject>() {
                        @Override
                        public void onCompleted(Exception e, JsonObject result) {
                            if(e != null){
                                Toast.makeText(c, "Erro ao cadastrar, Mensagem: " + e.getMessage(), Toast.LENGTH_LONG).show();
                            } else{
                                if(result.get("INSERT").getAsString().equals("OK")){
                                    Toast.makeText(c, "usuário inserido com sucesso!", Toast.LENGTH_LONG).show();
                                } else {
                                    Toast.makeText(c, "Erro ao inserir!", Toast.LENGTH_LONG).show();
                                }
                            }
                        }
                    });
        }
    }

    // Lista de usuários
    public void ListaUSU(Usuario us, Context c, ChamarLogin login) {
        String url = base + "/ListarUsuario.php";

        List<Usuario> users = new ArrayList<Usuario>();

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

                                int id = obj.has("id_user") ? obj.get("id_user").getAsInt() : 0;
                                String nome = obj.has("nome_user") ? obj.get("nome_user").getAsString().replace("'", "") : "Sem nome";
                                String email = obj.has("email_user") ? obj.get("email_user").getAsString().replace("'", "") : "Sem email";
                                String senha = obj.has("senha_user") ? obj.get("senha_user").getAsString().replace("'", "") : "Sem senha";

                                Usuario usu = new Usuario();
                                usu.setId(id);
                                usu.setUsuario(nome);
                                usu.setEmail(email);
                                usu.setSenha(senha);

                                users.add(usu);
                            }

                            VerificarLogin(users, us, c, login);

                        } else {
                            Toast.makeText(c, "Sem usuários", Toast.LENGTH_LONG).show();
                        }
                    }
                });
    }

    // Logando o usuário
    public void VerificarLogin(List<Usuario> bd, Usuario re, Context c, ChamarLogin login){

        boolean encontrou = false;

        for(Usuario item : bd){
            boolean emailCerto = !re.getEmail().isEmpty() && re.getEmail().equals(item.getEmail());
            boolean usuarioCerto = !re.getUsuario().isEmpty() && re.getUsuario().equals(item.getUsuario());

            if( (usuarioCerto || emailCerto) && re.getSenha().equals(item.getSenha())){
                login.onSucesso(item);
                encontrou = true;
                break;
            }
        }

        if(!encontrou){
            login.onErro("Usuário ou senha incorreto!");
        }
    }

    // Alterar Email

    public void AtualizarEmail(Context c, Usuario us, String email) {
        String id = String.valueOf(us.getId());

        if (TextUtils.isEmpty(email)) {
            Toast.makeText(c, "Email não informado", Toast.LENGTH_LONG).show();
        } else {
            String url = base + "/AtualizaEmail.php";

            Ion.with(c)
                    .load(url)
                    .setBodyParameter("Iduser", id)
                    .setBodyParameter("Txtemail", email)
                    .asJsonObject()
                    .setCallback(new FutureCallback<JsonObject>() {
                        @Override
                        public void onCompleted(Exception e, JsonObject result) {
                            if (e != null) {
                                Toast.makeText(c, "Erro ao cadastrar, Mensagem: " + e.getMessage(), Toast.LENGTH_LONG).show();
                            } else {
                                if (result.get("UPDATE").getAsString().equals("OK")) {
                                    Toast.makeText(c, "usuário inserido com sucesso!", Toast.LENGTH_LONG).show();
                                } else {
                                    Toast.makeText(c, "Erro ao inserir!", Toast.LENGTH_LONG).show();
                                }
                            }
                        }
                    });
        }
    }

    public void AtualizarSenha(Context c, Usuario us, String senhaN, String senhaA) {
        String id = String.valueOf(us.getId());

        if (TextUtils.isEmpty(senhaN)) {
            Toast.makeText(c, "Senha Nova não inserido", Toast.LENGTH_LONG).show();
        } else if (TextUtils.isEmpty(senhaA)) {
            Toast.makeText(c, "Senha Antiga não inserido", Toast.LENGTH_LONG).show();
        } else {
            String url = base + "/AlterarSenha.php";

            Ion.with(c)
                    .load(url)
                    .setBodyParameter("Iduser", id)
                    .setBodyParameter("TxtsenhaNova", senhaN)
                    .setBodyParameter("TxtsenhaAntiga", senhaA)
                    .asJsonObject()
                    .setCallback(new FutureCallback<JsonObject>() {
                        @Override
                        public void onCompleted(Exception e, JsonObject result) {
                            if (e != null) {
                                Toast.makeText(c, "Erro ao cadastrar, Mensagem: " + e.getMessage(), Toast.LENGTH_LONG).show();
                            } else {
                                if (result.get("UPDATE").getAsString().equals("OK")) {
                                    Toast.makeText(c, "usuário inserido com sucesso!", Toast.LENGTH_LONG).show();
                                } else {
                                    Toast.makeText(c, "Erro ao inserir!", Toast.LENGTH_LONG).show();
                                }
                            }
                        }
                    });
        }
    }

    public void DeletarConta(Context c, Usuario us) {
        String id = String.valueOf(us.getId());

        Toast.makeText(c, String.valueOf(us.getId()), Toast.LENGTH_LONG).show();

        String url = base + "/ExcluirConta.php";

        Ion.with(c)
                .load(url)
                .setBodyParameter("Iduser", id)
                .asJsonObject()
                .setCallback(new FutureCallback<JsonObject>() {
                    @Override
                    public void onCompleted(Exception e, JsonObject result) {
                        if (e != null) {
                            Toast.makeText(c, "Erro ao excluir, Mensagem: " + e.getMessage(), Toast.LENGTH_LONG).show();
                        } else {
                            if (result.get("DELETE").getAsString().equals("OK")) {
                                Toast.makeText(c, "usuário excluido com sucesso!", Toast.LENGTH_LONG).show();
                            } else {
                                Toast.makeText(c, "Erro ao excluir!" + result.get("mensagem"), Toast.LENGTH_LONG).show();
                            }
                        }
                    }
                });
    }
}
