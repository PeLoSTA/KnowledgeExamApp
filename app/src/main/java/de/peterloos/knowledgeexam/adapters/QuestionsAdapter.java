package de.peterloos.knowledgeexam.adapters;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
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
import de.peterloos.knowledgeexam.fragments.QuestionsSummaryFragment;
import de.peterloos.knowledgeexam.models.QuestionModel;
import de.peterloos.knowledgeexam.parcels.QuestionParcel;

public class QuestionsAdapter extends FragmentPagerAdapter {

    private FirebaseDatabase database;
    private DatabaseReference ref;
    private ArrayList<QuestionParcel> data;
    private Context context;

    public QuestionsAdapter(FragmentManager fm, Context context) {
        super(fm);

        this.context = context;
        this.database = FirebaseDatabase.getInstance();
        this.ref = database.getReference();
        this.data = new ArrayList<>();
        this.update();
    }

    private void update() {

        this.ref.child("questions").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                for (DataSnapshot child : snapshot.getChildren()) {

                    QuestionModel questionModel = child.getValue(QuestionModel.class);
                    int position = QuestionsAdapter.this.data.size();
                    QuestionParcel parcel = new QuestionParcel(position, questionModel);
                    QuestionsAdapter.this.data.add(parcel);
                }

                QuestionsAdapter.this.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError err) {
                Log.w(Globals.TAG, "QuestionsAdapter:onCancelled", err.toException());
            }
        });
    }

    @Override
    public int getCount() {

        return (this.data.size() == 0) ? 0 : (1 + this.data.size());
    }

    @Override
    public Fragment getItem(int pos) {

        Log.v(Globals.TAG, "getItem --> " + pos);

        Fragment fragment = null;

        if (this.data.size() == 0) {

            fragment = new Fragment();
        }
        else if ((this.data.size() > 0) && (pos < this.data.size())) {

            fragment = QuestionFragment.newInstance();

            // setup fragment according to existing data
            QuestionParcel parcel = this.data.get(pos);
            Bundle bundle = new Bundle();
            bundle.putParcelable(Globals.QUESTION_PARCEL, parcel);
            fragment.setArguments(bundle);
        }
        else {

            fragment = new QuestionsSummaryFragment();

            // need to pass all questions to summary fragment
            // QuestionParcel parcel = this.data.get(pos);
            Bundle bundle = new Bundle();
            bundle.putParcelableArrayList(Globals.ALL_QUESTIONS_PARCEL, this.data);
            fragment.setArguments(bundle);
        }

        return fragment;
    }

    // public interface
    public void updateAnswer(int questionNumber, int answerPosition, boolean checked) {

        QuestionParcel question = this.data.get(questionNumber);
        question.setUsersAnswer(answerPosition, checked);
        Log.v(Globals.TAG, "PagerAdapter ### ===> Frage = " + questionNumber + ", Antwort zu " + answerPosition + ", checked = " + checked);
    }

    // private helper methods
//    private void setupQuestions2(int index) {
//
//        switch (index) {
//            case 0:
//                QuestionParcel dsc0 = new QuestionParcel();
//                dsc0.setQuestionNumber(1);
//                dsc0.setQuestion("Wieviel ist 1 + 1 + 1 + 1 + 1?");
//                dsc0.setNumberAnswers(4);
//                dsc0.setAnswers(new String[]{"3", "4", "5", "6"});
//                dsc0.setResults(new int[]{2});
//                this.parcels2.add(dsc0);
//                break;
//
//            case 1:
//                QuestionParcel dsc1 = new QuestionParcel();
//                dsc1.setQuestionNumber(2);
//                dsc1.setQuestion("Wieviel ist 1 + 1?");
//                dsc1.setNumberAnswers(4);
//                dsc1.setAnswers(new String[]{"3", "1", "0", "2"});
//                dsc1.setResults(new int[]{3});
//                this.parcels2.add(dsc1);
//                break;
//
//            case 2:
//                QuestionParcel dsc2 = new QuestionParcel();
//                dsc2.setQuestionNumber(3);
//                dsc2.setQuestion("Wieviel ist 2 - 1?");
//                dsc2.setNumberAnswers(4);
//                dsc2.setAnswers(new String[]{"1", "0", "2", "Keine dieser Antworten ist korrekt"});
//                dsc2.setResults(new int[]{0});
//
//                this.parcels2.add(dsc2);
//                break;
//
//            case 3:
//                QuestionParcel dsc3 = new QuestionParcel();
//                dsc3.setQuestionNumber(4);
//                dsc3.setQuestion("Wie viele Mitgliedstaaten hat die Europäische Union?");
//                dsc3.setNumberAnswers(5);
//                dsc3.setAnswers(new String[]{"14", "17", "24", "27", "29"});
//                dsc3.setResults(new int[]{3});
//                this.parcels2.add(dsc3);
//                break;
//
//            case 4:
//                QuestionParcel dsc4 = new QuestionParcel();
//                dsc4.setQuestionNumber(5);
//                dsc4.setQuestion("Wie heißt die Hauptstadt von Frankreich?");
//                dsc4.setNumberAnswers(3);
//                dsc4.setAnswers(new String[]{"Marseille", "Paris", "Brüssel"});
//                dsc4.setResults(new int[]{1});
//
//                this.parcels2.add(dsc4);
//                break;
//
//            case 5:
//                QuestionParcel dsc5 = new QuestionParcel();
//                dsc5.setQuestionNumber(6);
//                dsc5.setQuestion("Wann wurde die Europäische Union gegründet?");
//                dsc5.setNumberAnswers(7);
//                dsc5.setAnswers(new String[]{"01. Januar 1990", "01. Dezember 1991", "01. April 1992", "01. November 1993", "01. Juli 1994", "01. Februar 1995", "01. Dezember 1996"});
//                dsc5.setResults(new int[]{3});
//                this.parcels2.add(dsc5);
//                break;
//
//            case 6:
//                QuestionParcel dsc6 = new QuestionParcel();
//                dsc6.setQuestionNumber(7);
//                dsc6.setQuestion("Wie heißt das berühmte Bauwerk in Rom, wo die Gladiatoren kämpften?");
//                dsc6.setNumberAnswers(4);
//                dsc6.setAnswers(new String[]{"Forum Romanum", "Fontana di Trevi", "Colosseo", "Piazza Popolo"});
//                dsc6.setResults(new int[]{2});
//                this.parcels2.add(dsc6);
//                break;
//
//            case 7:
//                QuestionParcel dsc7 = new QuestionParcel();
//                dsc7.setQuestionNumber(8);
//                dsc7.setQuestion("Wie viele Sterne hat die EU-Flagge?");
//                String[] answers7 = new String[]{
//                        "5 Sterne",
//                        "6 Sterne",
//                        "7 Sterne",
//                        "8 Sterne",
//                        "9 Sterne",
//                        "10 Sterne",
//                        "11 Sterne",
//                        "12 Sterne",
//                        "13 Sterne",
//                        "14 Sterne",
//                        "15 Sterne",
//                        "16 Sterne",
//                        "17 Sterne",
//                        "18 Sterne",
//                        "19 Sterne",
//                        "Diese Anwort ist falsch - sie ist also nur dazu da, einen wirklich seeeehr langen Antwortsatz zu testen! Um es noch einmal zu sagen: Hier kein Kreuz machen !!!"
//                };
//                dsc7.setNumberAnswers(answers7.length);
//                dsc7.setAnswers(answers7);
//                dsc7.setResults(new int[]{15});
//                this.parcels2.add(dsc7);
//                break;
//
//            case 8:
//                QuestionParcel dsc8 = new QuestionParcel();
//                dsc8.setQuestionNumber(9);
//                dsc8.setQuestion("Welches französische Denkmal steht in Paris und ist 327 Meter hoch?");
//                dsc8.setNumberAnswers(5);
//                dsc8.setAnswers(new String[]{"Chinesische Turm", "Empire State Building", "Eiffellturm", "Türme von Hanoi", "Sinwellturm"});
//                dsc8.setResults(new int[]{2});
//                this.parcels2.add(dsc8);
//                break;
//
//            case 9:
//                QuestionParcel dsc9 = new QuestionParcel();
//                dsc9.setQuestionNumber(10);
//                dsc9.setQuestion("Welche Bedeutung hat der Satz 'The quick brown fox jumps over the lazy dog'? Oder aber der Satz 'Zwölf Boxkämpfer jagen Viktor quer über den großen Sylter Deich'.");
//                String[] answers9 = new String[]{
//                        "Ja",
//                        "Die Frage könnte man auch mit dem Satz 'Franz jagt im komplett verwahrlosten Taxi quer durch Bayern' gleichsetzen. Oder auch mit dem Satz 'Prall vom Whisky flog Quax den Jet zu Bruch'!",
//                        "Weiß nicht",
//                        "Auch diese Anwort sollte man nicht ankreuzen - aber sie hat den Zweck, einen wirklich seeeehr langen Antwortsatz zu testen!"
//                };
//                dsc9.setNumberAnswers(answers9.length);
//                dsc9.setAnswers(answers9);
//                dsc9.setResults(new int[]{0});
//                this.parcels2.add(dsc9);
//                break;
//
//            case 10:
//                QuestionParcel dsc10 = new QuestionParcel();
//                dsc10.setQuestionNumber(11);
//                dsc10.setQuestion("Wie heißt die Hauptstadt von Portugal?");
//                dsc10.setNumberAnswers(4);
//                dsc10.setAnswers(new String[]{"Barcelona", "Lissabon", "Porto", "La Valetta"});
//                dsc10.setResults(new int[]{0});
//                this.parcels2.add(dsc10);
//                break;
//        }

}
