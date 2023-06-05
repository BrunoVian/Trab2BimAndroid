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
import br.unipar.trabalho2bimandroid.adpaters.MediaAdapter;
import br.unipar.trabalho2bimandroid.adpaters.NotaAdapter;
import br.unipar.trabalho2bimandroid.globais.Globais;
import br.unipar.trabalho2bimandroid.model.NotasAluno;

public class MediasActivity extends AppCompatActivity {

    private Spinner spDisciplina;
    private ListView lvMedias;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medias);

        spDisciplina = findViewById(R.id.spDisciplina);
        lvMedias = findViewById(R.id.lvMedias);


        String[] vetorDisciplina = new String[]{"Todas", "Web", "Frameworks", "Mobile", "Qualidade", "Projetos"};

        ArrayAdapter adapterDisciplina = new ArrayAdapter(this, android.R.layout.simple_list_item_1, vetorDisciplina);

        spDisciplina.setAdapter(adapterDisciplina);

        spDisciplina.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                atualizaLista(Globais.listaNotas, position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }

    private void atualizaLista(ArrayList<NotasAluno> lista, int disciplinaSelecionadaPosicao) {

        ArrayList<NotasAluno> listaFiltrada = new ArrayList<>();

        if (disciplinaSelecionadaPosicao == 0) {
            listaFiltrada.addAll(lista);
        } else {
            for (int i = 0; i < lista.size(); i++) {
                String nomeDiscuplinaSelecionada = spDisciplina.getItemAtPosition(disciplinaSelecionadaPosicao).toString();
                if (lista.get(i).getDisciplina().equals(nomeDiscuplinaSelecionada)) {
                    listaFiltrada.add(lista.get(i));
                }
            }
        }
        MediaAdapter adapter = new MediaAdapter(this, listaFiltrada);
        lvMedias.setAdapter(adapter);
    }




}