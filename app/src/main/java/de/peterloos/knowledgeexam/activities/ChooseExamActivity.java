package de.peterloos.knowledgeexam.activities;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.Locale;

import de.peterloos.knowledgeexam.Globals;
import de.peterloos.knowledgeexam.R;
import de.peterloos.knowledgeexam.adapters.ExamsAdapter;
import de.peterloos.knowledgeexam.models.Exam;
import de.peterloos.knowledgeexam.models.ExamParcel;

public class ChooseExamActivity extends AppCompatActivity
        implements AdapterView.OnItemClickListener, View.OnClickListener {

    // controls
    private ListView lvAvailableExams;
    private EditText etSelectedExam;
    private Button btnStartExam;

    // miscellaneous
    private ExamsAdapter adapter;

    private String pin;
    // ODER
    private Exam exam;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.activity_choose_exam);

        // retrieve control references
        this.lvAvailableExams = this.findViewById(R.id.listviewAvailableExams);
        this.etSelectedExam = this.findViewById(R.id.edittextSelectedExam);
        this.etSelectedExam.setEnabled(false);
        this.btnStartExam = this.findViewById(R.id.buttonStartExam);

        // connect controls with event handling methods
        this.lvAvailableExams.setOnItemClickListener(this);
        this.btnStartExam.setOnClickListener(this);

        // connect list view with adapter
        this.adapter = new ExamsAdapter(this);
        this.lvAvailableExams.setAdapter(this.adapter);
        this.adapter.update();
    }

    @Override
    public void onClick(View view) {

        Toast.makeText(this.getApplicationContext(), "Xeahhhh", Toast.LENGTH_SHORT).show();

        AlertDialog alertDialog = new AlertDialog.Builder(ChooseExamActivity.this).create();
        alertDialog.setTitle("Start Exam"); //set title
        String msg = String.format(Locale.getDefault(),
                "Do you want to start the selected exam with pin %s", this.pin);
        alertDialog.setMessage(msg); //set Message
        alertDialog.setIcon(R.drawable.ic_launcher_background); //set icon/image
        // TODO: Wie sieht dieses Icon aus ???

        alertDialog.setButton(DialogInterface.BUTTON_POSITIVE, "Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

                Intent intent = new Intent(getApplicationContext(), TakeExamActivity.class);
                // intent.putExtra(Globals.EXTRA_PIN_EXAM, pin);

                // ExamParcel parcel = new ExamParcel("123", "asdasdsad", 10);

                ExamParcel parcel = new ExamParcel(ChooseExamActivity.this.exam);

                intent.putExtra(Globals.EXAM_PARCEL, parcel);

                ChooseExamActivity.this.startActivity(intent);
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

//        this.pin = this.adapter.getItem(i);
//        this.etSelectedExam.setText(this.pin);

        this.exam = this.adapter.getItem(i);
        this.etSelectedExam.setText(this.exam.getPin());

    }
}
