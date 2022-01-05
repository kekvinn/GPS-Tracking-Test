package com.example.gpstrackingtest;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;

public class WaypointForm extends AppCompatActivity {

    EditText edtTxtTitle, edtTxtNotes;
    TextView txtLongLabel, txtLatLabel, txtLongDisplay, txtLatDisplay, txtIssueLabel;
    RadioButton rbBrokenSidewalk, rbFireHydrant;
    Button btnSave;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_waypoint_form);


        edtTxtTitle = findViewById(R.id.edtTxtTitle);
        edtTxtNotes = findViewById(R.id.edtTxtNotes);
        txtLongLabel = findViewById(R.id.txtLongLabel);
        txtLatLabel = findViewById(R.id.txtLatLabel);
        txtLongDisplay = findViewById(R.id.txtLongDisplay);
        txtLatDisplay = findViewById(R.id.txtLatDisplay);
        txtIssueLabel = findViewById(R.id.txtIssueLabel);
        rbBrokenSidewalk = findViewById(R.id.rbBrokenSidewalk);
        rbFireHydrant = findViewById(R.id.rbFireHydrant);
        btnSave = findViewById(R.id.btnSave);


        // TODO: add a picture
    }
}