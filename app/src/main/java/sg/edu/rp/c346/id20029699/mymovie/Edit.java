package sg.edu.rp.c346.id20029699.mymovie;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;


import java.lang.reflect.Array;
import java.util.ArrayList;

public class Edit extends AppCompatActivity {

    TextView tvID, tvTitle, tvGenre, tvYear, tvRating;
    EditText etID,etTitle, etGenre, etYear;
    Button update, delete, returnbtn;
    Spinner spinner;

    Movie edit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

        tvID = findViewById(R.id.textViewID);
        tvTitle = findViewById(R.id.textViewName);
        tvGenre = findViewById(R.id.textViewGenre);
        tvYear = findViewById(R.id.textViewYear);
        tvRating = findViewById(R.id.textViewRate);
        etID = findViewById(R.id.editTextID);
        etTitle = findViewById(R.id.editTextName);
        etGenre = findViewById(R.id.editTextGenre);
        etYear= findViewById(R.id.editTextYear);
        spinner = findViewById(R.id.spinnerRating);
        update = findViewById(R.id.UpdateButton);
        delete = findViewById(R.id.deleteButton);
        returnbtn = findViewById(R.id.returnButton);


        Intent a = getIntent();
        edit = (Movie) a.getSerializableExtra("edit");

        etID.setText(edit.getId()+"");
        etTitle.setText(edit.getTitle());
        etGenre.setText(edit.getGenre());
        etYear.setText(edit.getYear()+"");
           if (edit.getRate().equals("G")){
                spinner.setSelection(0);
            } else if (edit.getRate().equals("PG")){
               spinner.setSelection(1);
            } else if (edit.getRate().equals("PG13")){
               spinner.setSelection(2);
            }else if (edit.getRate().equals("NC16")){
               spinner.setSelection(3);
            }else if (edit.getRate().equals("M18")){
               spinner.setSelection(4);
            }else if (edit.getRate().equals("R21")){
               spinner.setSelection(5);
            }

        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DBHelper dbh = new DBHelper(Edit.this);
                edit.setTitle(etTitle.getText().toString());
                edit.setGenre(etGenre.getText().toString());
                edit.setYear(Integer.parseInt(etYear.getText().toString()));
                edit.setRate(spinner.getSelectedItem().toString());
                dbh.updateMovie(edit);
                dbh.close();
                Toast.makeText(Edit.this, "Update Successful", Toast.LENGTH_SHORT).show();
                clear();
            }
        });

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DBHelper dbh = new DBHelper(Edit.this);
                dbh.deleteSong(edit.getId());
                Toast.makeText(Edit.this, "Delete Successful", Toast.LENGTH_SHORT).show();
                clear();
            }
        });

        returnbtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    private void clear() {

        etTitle.setText("");
        etGenre.setText("");
        etYear.setText("");
    }
}