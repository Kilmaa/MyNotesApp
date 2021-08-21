package sg.edu.rp.c346.id20022404.mynotesapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;

public class DBHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "note.db";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_NOTE = "Note";
    private static final String COLUMN_ID = "_id";
    private static final String COLUMN_TITLE = "title";
    private static final String COLUMN_DESCRIPTION = "description";
    private static final String COLUMN_PIN = "pin";

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // CREATE TABLE Song
        // (id INTEGER PRIMARY KEY AUTOINCREMENT, title TEXT,
        // singers TEXT, stars INTEGER, year INTEGER );
        String createIslandTableSql = "CREATE TABLE " + TABLE_NOTE + "("
                + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + COLUMN_TITLE + " TEXT, "
                + COLUMN_DESCRIPTION + " TEXT, "
                + COLUMN_PIN + " TEXT )";
        db.execSQL(createIslandTableSql);
        Log.i("info", createIslandTableSql + "\ncreated tables");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NOTE);
        onCreate(db);
    }

    public long insertNote(String name, String description, String pin) {
        // Get an instance of the database for writing
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_TITLE, name);
        values.put(COLUMN_DESCRIPTION, description);
        values.put(COLUMN_PIN, pin);
        // Insert the row into the TABLE_SONG
        long result = db.insert(TABLE_NOTE, null, values);
        // Close the database connection
        db.close();
        Log.d("SQL Insert","" + result);
        return result;
    }

    public ArrayList<Note> getAllNotes() {
        ArrayList<Note> noteList = new ArrayList<Note>();
        String selectQuery = "SELECT " + COLUMN_ID + ","
                + COLUMN_TITLE + "," + COLUMN_DESCRIPTION + ","
                + COLUMN_PIN + " FROM " + TABLE_NOTE;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {
                int id = cursor.getInt(0);
                String name = cursor.getString(1);
                String description = cursor.getString(2);
                String pin = cursor.getString(3);

                Note newIsland = new Note(id, name, description, pin);
                noteList.add(newIsland);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return noteList;
    }

    public ArrayList<Note> getNotesByPin(String pinFilter) {
        ArrayList<Note> notelist = new ArrayList<Note>();

        SQLiteDatabase db = this.getReadableDatabase();
        String[] columns= {COLUMN_ID, COLUMN_TITLE, COLUMN_DESCRIPTION, COLUMN_PIN};
        String condition = COLUMN_PIN + "= ?";
        String[] args = {String.valueOf(pinFilter)};

        Cursor cursor;
        cursor = db.query(TABLE_NOTE, columns, condition, args, null, null, null, null);

        // Loop through all rows and add to ArrayList
        if (cursor.moveToFirst()) {
            do {
                int id = cursor.getInt(0);
                String name = cursor.getString(1);
                String description = cursor.getString(2);
                String pin = cursor.getString(3);

                Note newNote = new Note(id, name, description, pin);
                notelist.add(newNote);
            } while (cursor.moveToNext());
        }
        // Close connection
        cursor.close();
        db.close();
        return notelist;
    }



    public int updateNote(Note data){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_TITLE, data.getName());
        values.put(COLUMN_DESCRIPTION, data.getDescription());
        values.put(COLUMN_PIN, data.getPin());
        String condition = COLUMN_ID + "= ?";
        String[] args = {String.valueOf(data.getId())};
        int result = db.update(TABLE_NOTE, values, condition, args);
        db.close();
        return result;
    }


    public int deleteNote(int id){
        SQLiteDatabase db = this.getWritableDatabase();
        String condition = COLUMN_ID + "= ?";
        String[] args = {String.valueOf(id)};
        int result = db.delete(TABLE_NOTE, condition, args);
        db.close();
        return result;
    }

}

