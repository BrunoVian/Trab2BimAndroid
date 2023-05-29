package br.unipar.trabalho2bimandroid.activitys;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;

import br.unipar.trabalho2bimandroid.R;
import br.unipar.trabalho2bimandroid.adpaters.NotaAdapter;
import br.unipar.trabalho2bimandroid.globais.Globais;
import br.unipar.trabalho2bimandroid.model.NotasAluno;

public class NotasActivity extends AppCompatActivity {

    private ListView lvNotas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notas);

        lvNotas = findViewById(R.id.lvNotas);

        atualizaLista(Globais.listaNotas);



    }

    private void atualizaLista(ArrayList<NotasAluno> lista){
        NotaAdapter adapter = new NotaAdapter(this, lista);
        lvNotas.setAdapter(adapter);
    }

}