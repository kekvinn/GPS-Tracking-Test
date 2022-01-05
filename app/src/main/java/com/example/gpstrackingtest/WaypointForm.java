package com.example.gpstrackingtest;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class WaypointForm extends AppCompatActivity {

    EditText edtTxtTitle, edtTxtNotes;
    TextView txtLongLabel, txtLatLabel, txtLongDisplay, txtLatDisplay, txtIssueLabel, txtDateTimeLabel, txtDateTimeDisplay;
    RadioButton rbBrokenSidewalk, rbFireHydrant;
    Button btnSave;
    Calendar calendar;
    SimpleDateFormat simpleDateFormat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_waypoint_form);


        edtTxtTitle = findViewById(R.id.edtTxtTitle);
        edtTxtNotes = findViewById(R.id.edtTxtNotes);
        txtLongLabel = findViewById(R.id.txtLongLabel);
        txtLatLabel = findViewById(R.id.txtLatLabel);
        txtDateTimeLabel = findViewById(R.id.txtDateTimeLabel);
        txtDateTimeDisplay = findViewById(R.id.txtDateTimeDisplay);
        txtLongDisplay = findViewById(R.id.txtLongDisplay);
        txtLatDisplay = findViewById(R.id.txtLatDisplay);
        txtIssueLabel = findViewById(R.id.txtIssueLabel);
        rbBrokenSidewalk = findViewById(R.id.rbBrokenSidewalk);
        rbFireHydrant = findViewById(R.id.rbFireHydrant);
        btnSave = findViewById(R.id.btnSave);

        calendar = Calendar.getInstance();
        simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy, HH:mm:ss");
        String date = simpleDateFormat.format(calendar.getTime());
        txtDateTimeDisplay.setText(date);

        btnSave.setOnClickListener(v -> {

            String title = edtTxtTitle.getText().toString();
            String notes = edtTxtTitle.getText().toString();


        });


        // TODO: implement time and date, figure out issue types, maybe allow users to add pictures
    }
}