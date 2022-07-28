package sg.edu.rp.c346.id20029699.mymovie;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Movie;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    TextView tvTitle, tvGenre, tvYear, tvRating;
    EditText etTitle, etGenre, etYear;
    Button insert, show;
    Spinner spinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvTitle = findViewById(R.id.textViewName);
        tvGenre = findViewById(R.id.textViewGenre);
        tvYear = findViewById(R.id.textViewYear);
        tvRating = findViewById(R.id.textViewRate);
        etTitle = findViewById(R.id.editTextName);
        etGenre = findViewById(R.id.editTextGenre);
        etYear= findViewById(R.id.editTextYear);
        spinner = findViewById(R.id.spinnerRating);
        insert = findViewById(R.id.insertButton);
        show = findViewById(R.id.showButton);

        show.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this, Show.class);
                startActivity(i);
            }
        });

        insert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String titleData = etTitle.getText().toString();
                String genreData = etGenre.getText().toString();
                int yearData = Integer.parseInt(etYear.getText().toString());
                String rate = spinner.getSelectedItem().toString();

                DBHelper db = new DBHelper(MainActivity.this);
                long inserted_id = db.insertMovie(titleData,genreData, yearData, rate);

                if (inserted_id != -1){
                    Toast.makeText(MainActivity.this, "Insert successful",
                            Toast.LENGTH_SHORT).show();
                    clear();
                }
            }
        });

    }

    private void clear() {
        etTitle.setText("");
        etGenre.setText("");
        etYear.setText("");
    }
}