package sg.edu.rp.c346.id20029699.mymovie;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import java.io.Serializable;
import java.util.ArrayList;

public class Show extends AppCompatActivity {

    Button showBtn;
    ListView lv;
    ArrayAdapter<Movie> adapter;
    ArrayList<Movie> al;
    CustomAdapter CAMovie;
    Spinner filter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show);

        showBtn = findViewById(R.id.showPG13);
        lv = findViewById(R.id.list);
        filter = findViewById(R.id.spinnerFilter);

        DBHelper db = new DBHelper(Show.this);
        al = db.getAllMovies();
        adapter = new ArrayAdapter<Movie>(this, android.R.layout.simple_list_item_1, al);
        CAMovie = new CustomAdapter(this, R.layout.row,al);
        lv.setAdapter(CAMovie);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long identity) {
                Movie data = al.get(position); //user can select movie to edit
                Intent i = new Intent(Show.this, Edit.class);
                i.putExtra("edit", data);
                startActivity(i);
            }
        });

        showBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                al.clear();
                DBHelper db = new DBHelper(Show.this);
                al.addAll(db.getAllMovies());
                CAMovie.notifyDataSetChanged();
            }
        });

        filter.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long id) {
                DBHelper db = new DBHelper(Show.this);
                switch (i){
                    case 0:
                        al.clear();
                        al.addAll(db.getRate("G"));
                        CAMovie.notifyDataSetChanged();
                        break;
                    case 1:
                        al.clear();
                        al.addAll(db.getRate("PG"));
                        CAMovie.notifyDataSetChanged();
                        break;
                    case 2:
                        al.clear();
                        al.addAll(db.getRate("PG13"));
                        CAMovie.notifyDataSetChanged();
                        break;
                    case 3:
                        al.clear();
                        al.addAll(db.getRate("NC16"));
                        CAMovie.notifyDataSetChanged();
                        break;
                    case 4:
                        al.clear();
                        al.addAll(db.getRate("M18"));
                        CAMovie.notifyDataSetChanged();
                        break;
                    case 5:
                        al.clear();
                        al.addAll(db.getRate("R21"));
                        CAMovie.notifyDataSetChanged();
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    //app will autoload savedData
    @Override
    protected void onResume() {
        super.onResume();
        Log.d("OnResume", "Data changed");
        DBHelper dbh = new DBHelper(this);
        al.clear();
        al.addAll(dbh.getAllMovies());
        CAMovie.notifyDataSetChanged();

    }
}