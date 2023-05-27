package br.unipar.trabalho2bimandroid;

import androidx.appcompat.app.AppCompatActivity;
import br.unipar.trabalho2bimandroid.R;
import br.unipar.trabalho2bimandroid.globais.Globais;
import br.unipar.trabalho2bimandroid.model.Aluno;
import br.unipar.trabalho2bimandroid.model.Bimestre;
import br.unipar.trabalho2bimandroid.model.NotasAluno;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {

    private Button btnAdiconar;
    private EditText edNome;
    private EditText edRa;
    private Spinner spDisciplina;
    private Spinner spBimestre;
    private EditText edNota;

    private String disciplinaSelecionada;
    private String bimestreSelecionado;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edRa = findViewById(R.id.edRa);
        edNome = findViewById(R.id.edNome);
        edNota = findViewById(R.id.edNota);
        btnAdiconar = findViewById(R.id.btnAdicionar);
        spBimestre = findViewById(R.id.spBimestre);
        spDisciplina = findViewById(R.id.spDisciplina);

        String[] vetorBimestres = new String[]{"", "1 Bim", "2 Bim", "3 Bim", "4 Bim"};
        String[] vetorDisciplina = new String[]{"", "Web", "Frameworks", "Mobile", "Qualidade", "Projetos"};

        ArrayAdapter adapterBimestre = new ArrayAdapter(this, android.R.layout.simple_list_item_1, vetorBimestres);
        ArrayAdapter adapterDisciplina = new ArrayAdapter(this, android.R.layout.simple_list_item_1, vetorDisciplina);

        spDisciplina.setAdapter(adapterDisciplina);
        spBimestre.setAdapter(adapterBimestre);

        spDisciplina.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                disciplinaSelecionada = (String) spDisciplina.getItemAtPosition(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        spBimestre.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                bimestreSelecionado = (String) spBimestre.getItemAtPosition(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        if(Globais.listaAlunos == null ) {
            Globais.listaAlunos = new ArrayList<>();
        }
        if(Globais.listaNotas == null){
            Globais.listaNotas = new ArrayList<>();
        }

        btnAdiconar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                salvarAluno();
            }
        });


    }

    private void salvarAluno(){
/*
        if(edNome.getText().toString() == null){
            edNome.setError("Informe o Nome");
        }

        if(edNota.getText().toString() == null){
            edNota.setError("Informe a nota");
        } else if (edNome.getText().toString() != null) {
*/
            try{
            Aluno aluno = new Aluno();
            aluno.setRa(Integer.parseInt(edRa.getText().toString()));
            aluno.setNome(edNome.getText().toString());

            NotasAluno notasAluno = new NotasAluno();
            notasAluno.setAluno(aluno);
            notasAluno.setNota(Integer.parseInt(edNota.getText().toString()));
            notasAluno.setBimestre(bimestreSelecionado);
            notasAluno.setDisciplina(disciplinaSelecionada);

            Globais.listaAlunos.add(aluno);

            Toast.makeText(this,
                    "Aluno Salvo com Sucesso!",
                    Toast.LENGTH_LONG).show();
            }catch (Exception ex){
                Log.e("ERRO SALVAR ALUNO: ", ex.getMessage());
            }

            limpaCampos();
        }

    public void limpaCampos(){
        try {
            //edNome.setText(null);
           //edRa.setText(null);
            edNota.setText(0);
            spDisciplina.setSelected(false);
            spBimestre.setSelected(false);

        }catch (Exception ex){
            Log.e("ERRO LIMPACAMPOS", ex.getMessage());
        }

    }

}