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

        ArrayList<String> arrayAlunos = new ArrayList<>();

        arrayAlunos.add("Todos");

        for (int i = 0; i < Globais.listaNotas.size(); i++) {
            String nome = Globais.listaNotas.get(i).getNome();
            if (!arrayAlunos.contains(nome)) {
                arrayAlunos.add(nome);
            }
        }

        ArrayAdapter adapterAlunos = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, arrayAlunos);

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

    private void atualizaLista(ArrayList<NotasAluno> lista, int alunoSelecionadoPosicao) {

        ArrayList<NotasAluno> listaFiltrada = new ArrayList<>();

        if (alunoSelecionadoPosicao == 0) {
            listaFiltrada.addAll(lista);
        } else {
            for (int i = 0; i < lista.size(); i++) {
                String nomeAlunoSelecionado = spAluno.getItemAtPosition(alunoSelecionadoPosicao).toString();
                if (lista.get(i).getNome().equals(nomeAlunoSelecionado)) {
                    listaFiltrada.add(lista.get(i));
                }
            }
        }
        NotaAdapter adapter = new NotaAdapter(this, listaFiltrada);
        lvNotas.setAdapter(adapter);
    }
}