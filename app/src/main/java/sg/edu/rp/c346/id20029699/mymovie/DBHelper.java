package sg.edu.rp.c346.id20029699.mymovie;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;

public class DBHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "Movie.db";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_MOVIE = "Movie";
    private static final String COLUMN_ID = "_id";
    private static final String COLUMN_MOVIE_TITLE = "title";
    private static final String COLUMN_MOVIE_GENRE = "genre";
    private static final String COLUMN_MOVIE_YEAR = "year";
    private static final String COLUMN_MOVIE_RATE = "rate";

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String createMovieTableSql = "CREATE TABLE " + TABLE_MOVIE + "("
                + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + COLUMN_MOVIE_TITLE + " TEXT,"
                + COLUMN_MOVIE_GENRE + " TEXT,"
                + COLUMN_MOVIE_YEAR + " INTEGER,"
                + COLUMN_MOVIE_RATE + " TEXT)";
        db.execSQL(createMovieTableSql);
        Log.i("info", "created tables");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_MOVIE);
        onCreate(db);
    }

    public long insertMovie(String title, String genre, int year, String rate) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_MOVIE_TITLE, title);
        values.put(COLUMN_MOVIE_GENRE, genre);
        values.put(COLUMN_MOVIE_YEAR, year);
        values.put(COLUMN_MOVIE_RATE, rate);

        long result = db.insert(TABLE_MOVIE, null, values);
        db.close();
        Log.d("SQL Insert", "ID:" + result); //id returned, shouldn’t be -1
        return result;

    }
    public ArrayList<Movie> getAllMovies() {
        ArrayList<Movie> al = new ArrayList<Movie>();

        SQLiteDatabase db = this.getReadableDatabase();

        String[] columns= {COLUMN_ID, COLUMN_MOVIE_TITLE, COLUMN_MOVIE_GENRE, COLUMN_MOVIE_YEAR, COLUMN_MOVIE_RATE};
        Cursor cursor = db.query(TABLE_MOVIE, columns, null, null,
                null, null, null, null);

        if (cursor.moveToFirst()) {
            do {
                int id = cursor.getInt(0);
                String title = cursor.getString(1);
                String genre = cursor.getString(2);
                int year = cursor.getInt(3);
                String rate = cursor.getString(4);

                Movie note = new Movie(id,title, genre,year,rate);
                al.add(note);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return al;
    }

    public ArrayList<Movie> getPg13() {
        ArrayList<Movie> al = new ArrayList<Movie>();

        SQLiteDatabase db = this.getReadableDatabase();

        String[] columns= {COLUMN_ID, COLUMN_MOVIE_TITLE, COLUMN_MOVIE_GENRE, COLUMN_MOVIE_YEAR, COLUMN_MOVIE_RATE};
        String filter = COLUMN_MOVIE_RATE + "= ?";
        String[] args = {"PG13"} ;
        Cursor cursor = db.query(TABLE_MOVIE, columns, filter, args,
                null, null, null, null);

        if (cursor.moveToFirst()) {
            do {
                int id = cursor.getInt(0);
                String title = cursor.getString(1);
                String genre = cursor.getString(2);
                int year = cursor.getInt(3);
                String rate = cursor.getString(4);

                Movie note = new Movie(id,title, genre,year,rate);
                al.add(note);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return al;
        //return getStar;
    }


    public int updateMovie(Movie edit){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_MOVIE_TITLE, edit.getTitle());
        values.put(COLUMN_MOVIE_GENRE, edit.getGenre());
        values.put(COLUMN_MOVIE_YEAR, edit.getYear());
        values.put(COLUMN_MOVIE_RATE, edit.getRate());
        String condition = COLUMN_ID + "= ?";
        String[] args = {String.valueOf(edit.getId())};
        int result = db.update(TABLE_MOVIE, values, condition, args);
        db.close();
        return result;
    }

    public int deleteSong(int id){
        SQLiteDatabase db = this.getWritableDatabase();
        String condition = COLUMN_ID + "= ?";
        String[] args = {String.valueOf(id)};
        int result = db.delete(TABLE_MOVIE, condition, args);
        db.close();
        return result;
    }
    public ArrayList<Movie> getRate(String choice) {
        ArrayList<Movie> al = new ArrayList<Movie>();

        SQLiteDatabase db = this.getReadableDatabase();

        String[] columns= {COLUMN_ID, COLUMN_MOVIE_TITLE, COLUMN_MOVIE_GENRE, COLUMN_MOVIE_YEAR, COLUMN_MOVIE_RATE};
        String filter = COLUMN_MOVIE_RATE + "= ?";
        String[] a = {choice};
        Cursor cursor = db.query(TABLE_MOVIE, columns, filter, a,
                null, null, null, null);

        if (cursor.moveToFirst()) {
            do {
                int id = cursor.getInt(0);
                String title = cursor.getString(1);
                String genre = cursor.getString(2);
                int year = cursor.getInt(3);
                String rate = cursor.getString(4);

                Movie note = new Movie(id,title, genre,year,rate);
                al.add(note);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return al;
        //return getStar;
    }


}