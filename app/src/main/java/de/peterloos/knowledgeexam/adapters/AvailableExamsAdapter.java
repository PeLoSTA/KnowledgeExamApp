package de.peterloos.knowledgeexam.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import de.peterloos.knowledgeexam.R;
import de.peterloos.knowledgeexam.models.Exam;

public class AvailableExamsAdapter extends ArrayAdapter<String> {

    private String[] exams;
    private Context context;

    private FirebaseDatabase database;
    private DatabaseReference ref;

    public AvailableExamsAdapter(@NonNull Context context) {
        super(context, android.R.layout.simple_list_item_1);
        this.context = context;

        this.database = FirebaseDatabase.getInstance();
        this.ref = database.getReference();
    }

    public void update() {

        this.ref.child("exams").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {

                for (DataSnapshot child : snapshot.getChildren()) {

                    String key = child.getKey();
                    Log.v("PeLo", "Found key ==> " + key);

                    Exam exam = child.getValue(Exam.class);

                    // add pin of exam to listview
                    AvailableExamsAdapter.this.add(exam.getPin());
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.w("", "getUser:onCancelled", databaseError.toException());
            }
        });
    }
}
