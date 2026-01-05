package com.matheus.cyberfox_store.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.matheus.cyberfox_store.R;
import com.matheus.cyberfox_store.model.Jogos;

import java.util.List;

public class ListaArrayAdapterGames extends ArrayAdapter<Jogos> {
    private int layout;
    private LayoutInflater layoutInflater;

    public ListaArrayAdapterGames(Activity activity, int layout, List<Jogos> dados){
        super(activity, layout, dados);

        layoutInflater = (LayoutInflater) activity.getSystemService((Context.LAYOUT_INFLATER_SERVICE));
        this.layout = layout;
    }

    public View getView(int pos, View linha, ViewGroup parent){
        ViewHolder viewHolder;

        if(linha == null){
            // Cria um novo item
            linha = layoutInflater.inflate(layout, null);
            viewHolder = new ViewHolder();

            viewHolder.txtNome = linha.findViewById(R.id.txtNomeGame);

            linha.setTag(viewHolder);
        } else{
            viewHolder = (ViewHolder) linha.getTag();
        }

        Jogos games = getItem(pos);

        if(games != null){

            viewHolder.txtNome.setText(games.getNome());
        }

        return linha;
    }

    class ViewHolder {
        TextView txtNome;
    }
}
