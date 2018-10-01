package br.com.senaijandira.mybooks.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import br.com.senaijandira.mybooks.CadastroActivity;
import br.com.senaijandira.mybooks.R;
import br.com.senaijandira.mybooks.Utils;
import br.com.senaijandira.mybooks.db.MyBooksDatabase;
import br.com.senaijandira.mybooks.model.Livro;

import static android.support.v4.content.ContextCompat.startActivity;

/**
 * Created by sn1041520 on 01/10/2018.
 */

public class LivroAdapter extends ArrayAdapter<Livro> {

    //Banco de doados
    private MyBooksDatabase mybooksDb;

    public LivroAdapter(Context ctx,MyBooksDatabase mybooksDb){
        super(ctx, 0, new ArrayList<Livro>());

        this.mybooksDb = mybooksDb;
    }

        private void deletarLivro(Livro livro){

        //Remover do banco de dados
            mybooksDb.daoLivro().deletar(livro);

        //remover livro lista
            remove(livro);
        }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        View v = convertView;

        if(v == null){
            v = LayoutInflater.from(getContext())
                    .inflate(R.layout.livro_layout,
                            parent, false);
        }

        final Livro livro = getItem(position);

        ImageView imgLivroCapa = v.findViewById(R.id.imgLivroCapa);
        TextView txtLivroTitulo = v.findViewById(R.id.txtLivroTitulo);
        TextView txtLivroDescricao = v.findViewById(R.id.txtLivroDescricao);

        ImageView imgDeleteLivro = v.findViewById(
                R.id.imgapagarLivro);

        imgDeleteLivro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                deletarLivro(livro);
            }
        });

        //Setando a imagem
        imgLivroCapa.setImageBitmap(
                Utils.toBitmap(livro.getCapa()) );

        //Setando o titulo do livro
        txtLivroTitulo.setText(livro.getTitulo());

        //Setando a descrição do livro
        txtLivroDescricao.setText(livro.getDescricao());

        final Button btnLeu= v.findViewById(R.id.btnLeu);

        btnLeu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                System.out.println("vc quer ler o livro "+livro.getTitulo());
                /*abrirCadastro(btnLeu);*/

            }
        });

        return v;
    }
}
