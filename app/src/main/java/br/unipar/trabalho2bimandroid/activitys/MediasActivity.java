package br.unipar.trabalho2bimandroid.activitys;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Spinner;

import br.unipar.trabalho2bimandroid.R;

public class MediasActivity extends AppCompatActivity {

    private Spinner spAluno;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medias);

    }
}