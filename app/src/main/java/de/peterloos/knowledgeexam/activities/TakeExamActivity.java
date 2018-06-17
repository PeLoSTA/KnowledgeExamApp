package de.peterloos.knowledgeexam.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import de.peterloos.knowledgeexam.Globals;
import de.peterloos.knowledgeexam.R;
import de.peterloos.knowledgeexam.models.ExamParcel;

public class TakeExamActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.activity_take_exam);

        // retrieve pin from initiating activity
        Bundle extras = this.getIntent().getExtras();
        if (extras != null) {
//            String pin = extras.getString(Globals.EXTRA_PIN_EXAM);
//            Log.v(Globals.TAG, pin);

            ExamParcel parcel = this.getIntent().getParcelableExtra(Globals.EXTRA_PIN_EXAM);
                        Log.v(Globals.TAG, parcel.toString());
        }
    }
}
