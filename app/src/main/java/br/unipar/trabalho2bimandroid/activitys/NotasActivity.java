package br.unipar.trabalho2bimandroid.activitys;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;

import java.util.ArrayList;

import br.unipar.trabalho2bimandroid.R;
import br.unipar.trabalho2bimandroid.adpaters.NotaAdapter;
import br.unipar.trabalho2bimandroid.globais.Globais;
import br.unipar.trabalho2bimandroid.model.NotasAluno;

public class NotasActivity extends AppCompatActivity {

    private ListView lvNotas;
    private Spinner spAluno;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notas);

        lvNotas = findViewById(R.id.lvNotas);
        spAluno = findViewById(R.id.spAluno);

        String[] vetorAlunos = new String[Globais.listaNotas.size() + 1];

        vetorAlunos[0] = "Todos";

        for (int i =0; i< Globais.listaNotas.size();i++){
            vetorAlunos[i+1] = Globais.listaNotas.get(i).getNome();
        }

        ArrayAdapter adapterAlunos = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,vetorAlunos);

        spAluno.setAdapter(adapterAlunos);

        spAluno.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                atualizaLista(Globais.listaNotas, position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


    }

    private void atualizaLista(ArrayList<NotasAluno> lista, int alunoSelecionado){

        ArrayList<NotasAluno> listaFiltrada = new ArrayList<>();

        if (alunoSelecionado == 0) {
            listaFiltrada.addAll(lista);
        } else {
            String nomeAlunoSelecionado = lista.get(alunoSelecionado - 1).getNome();
            for (NotasAluno notasAluno : lista) {
                if (notasAluno.getNome().equals(nomeAlunoSelecionado)) {
                    listaFiltrada.add(notasAluno);
                }
            }
        }

        NotaAdapter adapter = new NotaAdapter(this, listaFiltrada);
        lvNotas.setAdapter(adapter);
    }

}