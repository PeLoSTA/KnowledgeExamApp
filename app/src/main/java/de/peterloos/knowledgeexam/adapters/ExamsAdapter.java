package de.peterloos.knowledgeexam.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import de.peterloos.knowledgeexam.Globals;
import de.peterloos.knowledgeexam.models.Exam;

public class ExamsAdapter extends ArrayAdapter<Exam> {

    private Context context;
    private FirebaseDatabase database;
    private DatabaseReference ref;

    public ExamsAdapter(@NonNull Context context) {
        super(context, android.R.layout.simple_list_item_1);
        this.context = context;

        this.database = FirebaseDatabase.getInstance();
        this.ref = database.getReference();
    }

    @Override
    public View getView(int pos, View convertView, ViewGroup parent) {

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(android.R.layout.simple_list_item_1, parent, false);
        TextView tv = rowView.findViewById(android.R.id.text1);  // predefined id (!)
        Exam exam = this.getItem(pos);
        tv.setText(exam.getPin());
        return rowView;
    }

    public void update() {

        this.ref.child("exams").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {

                for (DataSnapshot child : snapshot.getChildren()) {

                    String key = child.getKey();
                    Log.v(Globals.TAG, "Found key ==> " + key);

                    Exam exam = child.getValue(Exam.class);

                    // add pin of exam to listview
                    ExamsAdapter.this.add(exam);
                }
            }

            @Override
            public void onCancelled(DatabaseError err) {
                Log.w(Globals.TAG, "ExamsAdapter:onCancelled", err.toException());
            }
        });
    }
}
