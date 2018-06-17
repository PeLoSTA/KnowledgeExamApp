package de.peterloos.knowledgeexam.activities;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

import de.peterloos.knowledgeexam.R;
import de.peterloos.knowledgeexam.adapters.AvailableExamsAdapter;



public class ActivityChooseExam extends AppCompatActivity
        implements AdapterView.OnItemClickListener, View.OnClickListener {

    private ListView listviewAvailableExams;
    private AvailableExamsAdapter adapter;
    private EditText edittextSelectedExam;
    private Button buttonStartExam;



    @Override
    protected void onCreate(Bundle savedInstanceState) {

        Log.v("PeLo", "> onCreate");

        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.activity_choose_exam);

        // retrieve control references
        this.listviewAvailableExams = this.findViewById(R.id.listviewAvailableExams);
        this.edittextSelectedExam = this.findViewById(R.id.edittextSelectedExam);
        this.edittextSelectedExam.setEnabled(false);
        this.buttonStartExam =this.findViewById(R.id.buttonStartExam);

        // connect list view with adapter
        this.adapter = new AvailableExamsAdapter(this);
        this.listviewAvailableExams.setAdapter(this.adapter);

        // handle click events on list view and button
        this.listviewAvailableExams.setOnItemClickListener(this);
        this.buttonStartExam.setOnClickListener(this);

        this.adapter.update();
        Log.v("PeLo", "< onCreate");
    }

    @Override
    public void onClick(View view) {

        Toast.makeText(this.getApplicationContext(), "Xeahhhh", Toast.LENGTH_SHORT).show();

        AlertDialog alertDialog = new AlertDialog.Builder(
                ActivityChooseExam.this).create();
        alertDialog.setTitle("Start Exam"); //set title
        alertDialog.setMessage("Do you want to start the selected exam Math 1?"); //set Message
        // alertDialog.setIcon(R.drawable.ic_launcher_background); //set icon/image


        alertDialog.setButton(DialogInterface.BUTTON_POSITIVE, "Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

//                Intent demoIntent = new Intent(getApplicationContext(), ActivityPassExam.class);
//                ActivityChooseExam.this.startActivity(demoIntent);
            }
        });
        alertDialog.setButton(DialogInterface.BUTTON_NEGATIVE, "Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });

        alertDialog.show();
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

        String choosenExam = this.adapter.getItem(i);

        this.edittextSelectedExam.setText(choosenExam);
    }
}
