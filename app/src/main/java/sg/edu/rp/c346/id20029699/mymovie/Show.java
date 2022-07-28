package sg.edu.rp.c346.id20029699.mymovie;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.io.Serializable;
import java.util.ArrayList;

public class Show extends AppCompatActivity {

    Button showBtn;
    ListView lv;
    ArrayAdapter<Movie> adapter;
    ArrayList<Movie> al;
    CustomAdapter CAMovie;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show);

        showBtn = findViewById(R.id.showPG13);
        lv = findViewById(R.id.list);

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
                al.addAll(db.getPg13());
                CAMovie.notifyDataSetChanged();
            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();

        String msg = "No Movie";
        Toast toast = Toast.makeText(Show.this,msg,Toast.LENGTH_LONG);
    }
}