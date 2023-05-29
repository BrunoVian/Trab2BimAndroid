package br.unipar.trabalho2bimandroid.adpaters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import br.unipar.trabalho2bimandroid.R;
import br.unipar.trabalho2bimandroid.globais.Globais;
import br.unipar.trabalho2bimandroid.model.NotasAluno;

public class NotaAdapter extends BaseAdapter {

    private Context context;
    private ArrayList<NotasAluno> lista;

    public NotaAdapter(Context context, ArrayList<NotasAluno> lista) {
        this.context = context;
        this.lista = lista;
    }


    @Override
    public int getCount() {
        return lista.size();
    }

    @Override
    public Object getItem(int position) {
        return lista.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView == null){
            convertView = LayoutInflater.from(context).
                    inflate(R.layout.item_list_notas_aluno,
                            parent, false);
        }



        NotasAluno notasAluno = lista.get(position);
        TextView tvMateria = convertView.findViewById(R.id.tvMateria);
        TextView tvMedia = convertView.findViewById(R.id.tvMedia);
        TextView tvPriBim = convertView.findViewById(R.id.tvPriBim);
        TextView tvSegBim = convertView.findViewById(R.id.tvSegBim);
        TextView tvTerBim = convertView.findViewById(R.id.tvTerBim);
        TextView tvQuaBim = convertView.findViewById(R.id.tvQuaBim);


        tvMateria.setText(notasAluno.getDisciplina());
        tvPriBim.setText(tvPriBim.getText().toString() + notasAluno.getPriBim());
        tvSegBim.setText(tvSegBim.getText().toString() + notasAluno.getSegBim());
        tvTerBim.setText(tvTerBim.getText().toString() + notasAluno.getTerBim());
        tvQuaBim.setText(tvQuaBim.getText().toString() + notasAluno.getQuaBim());
        return convertView;

    }


}
