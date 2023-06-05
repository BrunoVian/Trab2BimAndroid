package br.unipar.trabalho2bimandroid.adpaters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import br.unipar.trabalho2bimandroid.R;
import br.unipar.trabalho2bimandroid.model.NotasAluno;

public class MediaAdapter extends BaseAdapter {

    private Context context;
    private ArrayList<NotasAluno> lista;

    public MediaAdapter(Context context, ArrayList<NotasAluno> lista) {
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
        if (convertView == null) {
            convertView = LayoutInflater.from(context).
                    inflate(R.layout.item_list_medias_aluno,
                            parent, false);
        }

        String resultado = "";

        int media = 0;

        NotasAluno notasAluno = lista.get(position);

        media = (lista.get(position).getPriBim() + lista.get(position).getSegBim() + lista.get(position).getTerBim() + lista.get(position).getQuaBim())/4;

        if(media >= 60){
            resultado = "APROVADO";
        }else{
            resultado = "REPROVADO";
        }

        TextView tvAlunoMedia = convertView.findViewById(R.id.tvAlunoMedia);
        TextView tvRa = convertView.findViewById(R.id.tvRa);
        TextView tvResultado = convertView.findViewById(R.id.tvResultado);
        TextView tvMediaTotal = convertView.findViewById(R.id.tvMediaTotal);
        TextView tvMateria = convertView.findViewById(R.id.tvMateria);


        tvAlunoMedia.setText(notasAluno.getNome());
        tvRa.setText(tvRa.getText().toString() + notasAluno.getRa());
        tvResultado.setText(resultado);
        tvMediaTotal.setText(String.valueOf(media));
        tvMateria.setText(notasAluno.getDisciplina());

        return convertView;
    }


}
