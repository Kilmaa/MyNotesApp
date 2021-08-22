package sg.edu.rp.c346.id20022404.mynotesapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.ToggleButton;

import java.util.ArrayList;

public class SecondActivity extends AppCompatActivity {
    ToggleButton tgBtn;
    TextView tvPinTitle;
    ListView lv;
    ArrayList<Note> noteList;
    CustomAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        tgBtn = findViewById(R.id.tgBtn);
        tvPinTitle = findViewById(R.id.tvPinTitle);
        lv  = findViewById(R.id.lv);

        tvPinTitle.setText("\nALL THE NOTES:");

        DBHelper dbh = new DBHelper(this);
        noteList = dbh.getAllNotes();
        dbh.close();

        adapter = new CustomAdapter(this, R.layout.row, noteList);
        lv.setAdapter(adapter);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent i = new Intent(SecondActivity.this, ThirdActivity.class);
                i.putExtra("note", noteList.get(position));
                startActivity(i);
            }
        });

        tgBtn.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    // The toggle is enabled
                    tvPinTitle.setText("\nIMPORTANT NOTES:");
                    DBHelper dbh = new DBHelper(SecondActivity.this);
                    noteList.clear();
                    noteList.addAll(dbh.getNotesByPin("*"));
                    adapter.notifyDataSetChanged();

                } else {
                    // The toggle is disabled
                    tvPinTitle.setText("\nALL THE NOTES:");
                    DBHelper dbh = new DBHelper(SecondActivity.this);
                    noteList.clear();
                    noteList.addAll(dbh.getAllNotes());
                    adapter.notifyDataSetChanged();

                }
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        DBHelper dbh = new DBHelper(SecondActivity.this);
        noteList.clear();
        noteList.addAll(dbh.getAllNotes());
        adapter.notifyDataSetChanged();
    }
}