package br.unipar.trabalho2bimandroid;

import androidx.appcompat.app.AppCompatActivity;

import br.unipar.trabalho2bimandroid.activitys.MediasActivity;
import br.unipar.trabalho2bimandroid.activitys.NotasActivity;
import br.unipar.trabalho2bimandroid.globais.Globais;
import br.unipar.trabalho2bimandroid.model.NotasAluno;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {

    private Button btnAdiconar, btnverNota, btnverMedia;
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
        btnverMedia = findViewById(R.id.btnverMedia);
        btnverNota = findViewById(R.id.btnverNota);
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

        btnverNota.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                abrirNotas();
            }
        });

        btnverMedia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                abrirMedias();
            }
        });

        btnAdiconar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                salvarAluno();
            }
        });

        if (Globais.listaNotas == null) {
            Globais.listaNotas = new ArrayList<>();
        }

    }

    private void abrirNotas() {
        Intent intent = new Intent(this, NotasActivity.class);
        startActivity(intent);
    }

    private void abrirMedias() {
        Intent intent = new Intent(this, MediasActivity.class);
        startActivity(intent);
    }

    private void setSpinnerError(Spinner spinner, String error) {
        View selectedView = spinner.getSelectedView();
        if (selectedView != null && selectedView instanceof TextView) {
            TextView errorText = (TextView) selectedView;
            errorText.setError(error);
        }
    }

    private void salvarAluno() {
        int nota = 0;
        if (!edNota.getText().toString().isEmpty()) {
            nota = Integer.parseInt(edNota.getText().toString());
        }
        if (edNome.getText().toString().isEmpty()) {
            edNome.setError("Informe o Nome do Aluno");
        }
        if (edNota.getText().toString().isEmpty()) {
            edNota.setError("Informe a nota");
        }
        if (edRa.getText().toString().isEmpty()) {
            edRa.setError("Informe o RA do Aluno");
        }
        if (spBimestre.getSelectedItemPosition() == 0 || !spBimestre.isSelected()) {
            setSpinnerError(spBimestre, "Informe o Bimestre");
        }
        if (spDisciplina.getSelectedItemPosition() == 0 || !spDisciplina.isSelected()) {
            setSpinnerError(spDisciplina, "Informe a Disciplina");
        }
        if (nota < 0 || nota > 100) {
            edNota.setError("Informe uma nota entre 0 e 100");
        }
        else if (!edNome.getText().toString().isEmpty()) {

            try {

                NotasAluno notasAluno = new NotasAluno();

                int posicaoAluno = -1;

                for (int i = 0; i < Globais.listaNotas.size(); i++) {

                    if (String.valueOf(Globais.listaNotas.get(i).getRa()).equals(edRa.getText().toString())
                            && Globais.listaNotas.get(i).getNome().equals(edNome.getText().toString())
                            && Globais.listaNotas.get(i).getDisciplina().equals(disciplinaSelecionada)) {
                        posicaoAluno = i;
                    }
                }

                if (posicaoAluno == -1) {

                    notasAluno.setNome(edNome.getText().toString());
                    notasAluno.setRa(Integer.parseInt(edRa.getText().toString()));
                    notasAluno.setDisciplina(disciplinaSelecionada);

                    switch (bimestreSelecionado) {
                        case "1 Bim":
                            notasAluno.setPriBim(Integer.parseInt(edNota.getText().toString()));
                            break;
                        case "2 Bim":
                            notasAluno.setSegBim(Integer.parseInt(edNota.getText().toString()));
                            break;
                        case "3 Bim":
                            notasAluno.setTerBim(Integer.parseInt(edNota.getText().toString()));
                            break;
                        case "4 Bim":
                            notasAluno.setQuaBim(Integer.parseInt(edNota.getText().toString()));
                            break;

                    }
                    Globais.listaNotas.add(notasAluno);
                    posicaoAluno = -1;
                }


                if (posicaoAluno != -1) {

                    Globais.listaNotas.get(posicaoAluno).setNome(edNome.getText().toString());
                    Globais.listaNotas.get(posicaoAluno).setRa(Integer.parseInt(edRa.getText().toString()));
                    Globais.listaNotas.get(posicaoAluno).setDisciplina(disciplinaSelecionada);

                    switch (bimestreSelecionado) {
                        case "1 Bim":
                            Globais.listaNotas.get(posicaoAluno).setPriBim(Integer.parseInt(edNota.getText().toString()));
                            break;
                        case "2 Bim":
                            Globais.listaNotas.get(posicaoAluno).setSegBim(Integer.parseInt(edNota.getText().toString()));
                            break;
                        case "3 Bim":
                            Globais.listaNotas.get(posicaoAluno).setTerBim(Integer.parseInt(edNota.getText().toString()));
                            break;
                        case "4 Bim":
                            Globais.listaNotas.get(posicaoAluno).setQuaBim(Integer.parseInt(edNota.getText().toString()));
                            break;
                    }
                    posicaoAluno = -1;
                }

                Toast.makeText(this,
                        "Aluno Salvo com Sucesso!",
                        Toast.LENGTH_LONG).show();
                limpaCampos();

            } catch (Exception ex) {
                Log.e("ERRO SALVAR ALUNO: ", ex.getMessage());
            }
        }
    }

    private void limpaCampos() {

        try {
            edNota.setText("0");
            spDisciplina.setSelection(0);
            spBimestre.setSelection(0);

        } catch (Exception ex) {
            Log.e("ERRO LIMPACAMPOS", ex.getMessage());
        }
    }

}