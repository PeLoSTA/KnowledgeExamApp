package de.peterloos.knowledgeexam.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import de.peterloos.knowledgeexam.Globals;
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

    @Override
    public View getView(int pos, View convertView, ViewGroup parent) {

//        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//        View rowView = inflater.inflate(R.layout.rowlayout, parent, false);
//        TextView textView = (TextView) rowView.findViewById(R.id.label);
//        ImageView imageView = (ImageView) rowView.findViewById(R.id.icon);
//        textView.setText(values[position]);
//        // change the icon for Windows and iPhone
//        String s = values[position];
//        if (s.startsWith("iPhone")) {
//            imageView.setImageResource(R.drawable.no);
//        } else {
//            imageView.setImageResource(R.drawable.ok);
//        }
//
//        return rowView;

        Log.v(Globals.TAG, "==> getView !!!" );

        return super.getView(pos, convertView, parent);
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
                    AvailableExamsAdapter.this.add(exam.getPin());
                }
            }

            @Override
            public void onCancelled(DatabaseError err) {
                Log.w(Globals.TAG, "AvailableExamsAdapter:onCancelled", err.toException());
            }
        });
    }
}
