package sg.edu.rp.c346.id20029699.mymovie;

import android.content.Context;
import android.text.method.MovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class CustomAdapter extends ArrayAdapter {

    Context parent_context;
    int layout_id = 1;
    ArrayList<Movie> MovieList1;

    public CustomAdapter(Show context, int resource, ArrayList<Movie> objects){
        super(context, resource, objects);
        parent_context = context;
        layout_id = resource;
        MovieList1 = objects;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
         // Obtain the LayoutInflater object
        LayoutInflater inflater = (LayoutInflater)
                parent_context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        // "Inflate" the View for each row
        View rowView = inflater.inflate(layout_id, parent, false);

        // Obtain the UI components and do the necessary binding
        TextView tvTitle = rowView.findViewById(R.id.textViewTitle1);
        TextView tvGenre = rowView.findViewById(R.id.textViewGenre1);
        TextView tvYear = rowView.findViewById(R.id.textViewYear1);
        ImageView rate = rowView.findViewById(R.id.imageView);

        // Obtain the Android Version information based on the position
        Movie c = MovieList1.get(position);

        // Set values to the TextView to display the corresponding information
        tvTitle.setText(c.getTitle());
        tvGenre.setText(c.getGenre());
        tvYear.setText(c.getYear()+"");
        String imageURL = "http://i.imgur.com/tGbaZCY.jpg";
        Picasso.with(parent_context).load(imageURL).into(rate);

        if (c.getRate().equals("G")){
            rate.setImageResource(R.drawable.rating_g);
        } else if (c.getRate().equals("PG")){
            rate.setImageResource(R.drawable.rating_pg);
        } else if (c.getRate().equals("PG13")){
            rate.setImageResource(R.drawable.rating_pg13);
        }else if (c.getRate().equals("NC16")){
            rate.setImageResource(R.drawable.rating_nc16);
        }else if (c.getRate().equals("M18")){
            rate.setImageResource(R.drawable.rating_m18);
        }else if (c.getRate().equals("R21")){
            rate.setImageResource(R.drawable.rating_r21);
        }
        return rowView;
    }
}
