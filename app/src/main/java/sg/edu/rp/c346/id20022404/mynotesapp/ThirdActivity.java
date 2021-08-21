package sg.edu.rp.c346.id20022404.mynotesapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class ThirdActivity extends AppCompatActivity {
    EditText etID, etTitle, etDescription;
    Button btnCancel, btnUpdate, btnDelete;
    RadioGroup rg;
    RadioButton rbPin, rbNotPin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);

        btnCancel = findViewById(R.id.btnCancel);
        btnDelete = findViewById(R.id.btnDelete);
        btnUpdate = findViewById(R.id.btnUpdate);
        etID = findViewById(R.id.etID);
        etTitle = findViewById(R.id.etTitle);
        etDescription = findViewById(R.id.etDescription);

        rg = findViewById(R.id.rg);
        rbPin = (RadioButton) findViewById(R.id.rbPin);
        rbNotPin = (RadioButton) findViewById(R.id.rbNotPin);

        Intent i = getIntent();
        final Note currentNote = (Note) i.getSerializableExtra("note");

        etID.setText(currentNote.getId()+"");
        etTitle.setText(currentNote.getTitle());
        etDescription.setText(currentNote.getDescription());
        if (rg.getCheckedRadioButtonId() == R.id.rbPin) {
            rbPin.setChecked(true);
        } else if (rg.getCheckedRadioButtonId() == R.id.rbNotPin) {
            rbNotPin.setChecked(true);
        }

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DBHelper dbh = new DBHelper(ThirdActivity.this);
                currentNote.setTitle(etTitle.getText().toString().trim());
                currentNote.setDescription(etDescription.getText().toString().trim());

                String pin = "";
                if (rg.getCheckedRadioButtonId() == R.id.rbPin) {
                    pin = "yes";
                } else if (rg.getCheckedRadioButtonId() == R.id.rbNotPin) {
                    pin = "no";
                }
                currentNote.setPin(pin);
                int result = dbh.updateNote(currentNote);
                if (result>0){
                    Toast.makeText(ThirdActivity.this, "Note updated", Toast.LENGTH_LONG).show();
                    finish();
                } else {
                    Toast.makeText(ThirdActivity.this, "Update failed", Toast.LENGTH_SHORT).show();
                }
            }
        });


        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DBHelper dbh = new DBHelper(ThirdActivity.this);
                int result = dbh.deleteNote(currentNote.getId());
                if (result>0){
                    Toast.makeText(ThirdActivity.this, "Note deleted", Toast.LENGTH_LONG).show();
                    finish();
                } else {
                    Toast.makeText(ThirdActivity.this, "Delete failed", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}