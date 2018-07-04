package de.peterloos.knowledgeexam.adapters;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.util.Log;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import de.peterloos.knowledgeexam.Globals;
import de.peterloos.knowledgeexam.fragments.QuestionFragment;
import de.peterloos.knowledgeexam.models.Question;
import de.peterloos.knowledgeexam.models.QuestionParcel;

public class QuestionsAdapter extends FragmentPagerAdapter {

    private List<Question> data;
    private Context context;

    private FirebaseDatabase database;
    private DatabaseReference ref;

    public QuestionsAdapter(FragmentManager fm, Context context) {
        super(fm);

        this.context = context;
        this.data = new ArrayList<>();
        this.database = FirebaseDatabase.getInstance();
        this.ref = database.getReference();
        this.update();
    }

    private void update() {

        this.ref.child("questions").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {

                for (DataSnapshot child : snapshot.getChildren()) {

                    // String key = child.getKey();
                    // Log.v(Globals.TAG, "Foundddddddd key ==> " + key);

                    Question question = child.getValue(Question.class);
                    Log.v(Globals.TAG, "==> " + question.toString());

                    // add pin of exam to listview
                    // QuestionsAdapter.this.data.add(question);
                }
            }

            @Override
            public void onCancelled(DatabaseError err) {
                Log.w(Globals.TAG, "QuestionsAdapter:onCancelled", err.toException());
            }
        });
    }

    @Override
    public int getCount() {
        int tmp = this.data.size();
        Log.v(Globals.TAG, "getCount --> " + tmp);
        return tmp;
    }

    @Override
    public Fragment getItem(int pos) {

        Log.v(Globals.TAG, "getItem --> " + pos);

        Fragment fragment = null;

        if (pos < this.data.size()) {

            fragment = QuestionFragment.newInstance();
            // SO: https://stackoverflow.com/questions/9245408/best-practice-for-instantiating-a-new-android-fragment

            // TODO

            // setup fragment according to existing data
//            Question question = this.data.get(pos);
//
//            QuestionParcel parcel = new QuestionParcel (question);
//            Bundle bundle = new Bundle();
//            bundle.putParcelable(Globals.QUESTION_PARCEL, parcel);
//            fragment.setArguments(bundle);
        }
        else {
            Log.v(Globals.TAG, "bin hier Arghhhhhhhhhhhhhhhhhhhhhhh" );
            fragment = new Fragment();
            fragment.getView().setBackgroundColor(Color.GREEN);
        }

        return fragment;
    }

    // public interface
//    public void updateAnswer(int questionNumber, int answerPosition, boolean checked) {
//
//        this.parcels[questionNumber].setUsersAnswer(answerPosition, checked);
//
//        Log.v(Globals.TAG, "PagerAdapter ### ===> Frage = " + questionNumber + ", Antwort zu " + answerPosition + ", checked = " + checked);
//    }

    public void updateAnswer(int questionNumber, int answerPosition, boolean checked) {

        Question question = this.data.get(questionNumber);
        // TODO
        // question.setUsersAnswer(answerPosition, checked);

        Log.v(Globals.TAG, "PagerAdapter ### ===> Frage = " + questionNumber + ", Antwort zu " + answerPosition + ", checked = " + checked);
    }

    // private helper methods

}
