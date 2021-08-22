package sg.edu.rp.c346.id20022404.mynotesapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText etTitle, etDescription;
    Button btnAdd, btnShow;
    RadioGroup rg;
    RadioButton rbNotPin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etTitle = findViewById(R.id.etTitle);
        etDescription = findViewById(R.id.etDescription);
        btnAdd = findViewById(R.id.btnAdd);
        btnShow = findViewById(R.id.btnShow);
        rg = findViewById(R.id.rg);
        rbNotPin = findViewById(R.id.rbNotPin);

        rbNotPin.setChecked(true);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String title = etTitle.getText().toString().trim();
                String description = etDescription.getText().toString().trim();
                if (title.length() == 0 || description.length() == 0){
                    Toast.makeText(MainActivity.this, "Incomplete data", Toast.LENGTH_SHORT).show();
                    return;
                }

                String checkPin = checkPin();

                DBHelper dbh = new DBHelper(MainActivity.this);

                dbh.insertNote(title, description, checkPin);
                dbh.close();
                Toast.makeText(MainActivity.this, "Inserted", Toast.LENGTH_LONG).show();

                etTitle.setText("");
                etDescription.setText("");
                rbNotPin.setChecked(true);
            }
        });

        btnShow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                Intent i = new Intent(MainActivity.this, SecondActivity.class);
                startActivity(i);
            }
        });

    }

    private String checkPin() {
        String check = "";
        switch (rg.getCheckedRadioButtonId()) {
            case R.id.rbPin:
                check = "*";
                break;
            case R.id.rbNotPin:
                check = "";
                break;
        }
        return check;
    }
}