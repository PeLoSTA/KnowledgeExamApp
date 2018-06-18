package de.peterloos.knowledgeexam.adapters;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.util.Log;

import de.peterloos.knowledgeexam.Globals;
import de.peterloos.knowledgeexam.fragments.QuestionFragment;
import de.peterloos.knowledgeexam.models.QuestionParcel;

public class QuestionsAdapter extends FragmentPagerAdapter {

    private final int MaxQuestions = 10;
    private QuestionParcel[] parcels;

    public QuestionsAdapter(FragmentManager fm) {
        super(fm);
        this.setupQuestions();
    }

    @Override
    public Fragment getItem(int position) {

        Log.v(Globals.TAG, "getItem at " + position + " called !!!");

        Fragment fragment = QuestionFragment.newInstance();

        // setup data for corresponding fragment
        QuestionParcel parcel = this.parcels[position];
        Bundle bundle = new Bundle();
        bundle.putParcelable(Globals.QUESTION_PARCEL, parcel);
        fragment.setArguments(bundle);

        return fragment;
    }

    @Override
    public int getCount() {
        return 3;
    }

    // private helper methods
    private void setupQuestions() {

        this.parcels = new QuestionParcel[MaxQuestions];

        QuestionParcel dsc1 = new QuestionParcel();
        dsc1.setQuestionNumber(0);
        dsc1.setQuestion("Wieviel ist 1 + 1?");
        dsc1.setNumberAnswers(4);
        dsc1.setAnswers(new String[]{"3", "1", "0", "2"});
//        dsc1.setCorrectAnswer(3);

        QuestionParcel dsc2 = new QuestionParcel();
        dsc2.setQuestionNumber(1);
        dsc2.setQuestion("Wieviel ist 2 - 1?");
        dsc2.setNumberAnswers(4);
        dsc2.setAnswers(new String[]{"1", "0", "2", "Keine dieser Antworten ist korrekt"});
//        dsc2.setCorrectAnswer(0);

        QuestionParcel dsc3 = new QuestionParcel();
        dsc3.setQuestionNumber(2);
        dsc3.setQuestion("Wie viele Mitgliedstaaten hat die Europäische Union?");
        dsc3.setNumberAnswers(5);
        dsc3.setAnswers(new String[]{"14", "17", "24", "27", "29"});
//        dsc3.setCorrectAnswer(3);

        QuestionParcel dsc4 = new QuestionParcel();
        dsc4.setQuestionNumber(3);
        dsc4.setQuestion("Wie heißt die Hauptstadt von Frankreich?");
        dsc4.setNumberAnswers(3);
        dsc4.setAnswers(new String[]{"Marseille", "Paris", "Brüssel"});
//        dsc4.setCorrectAnswer(1);

        QuestionParcel dsc5 = new QuestionParcel();
        dsc5.setQuestionNumber(4);
        dsc5.setQuestion("Wann wurde die Europäische Union gegründet?");
        dsc5.setNumberAnswers(7);
        dsc5.setAnswers(new String[]{"01. Januar 1990", "01. Dezember 1991", "01. April 1992", "01. November 1993", "01. Juli 1994", "01. Februar 1995", "01. Dezember 1996"});
//        dsc5.setCorrectAnswer(3);

        QuestionParcel dsc6 = new QuestionParcel();
        dsc6.setQuestionNumber(5);
        dsc6.setQuestion("Wie heißt das berühmte Bauwerk in Rom, wo die Gladiatoren kämpften?");
        dsc6.setNumberAnswers(4);
        dsc6.setAnswers(new String[]{"Forum Romanum", "Fontana di Trevi", "Colosseo", "Piazza Popolo"});
//        dsc6.setCorrectAnswer(2);

        QuestionParcel dsc7 = new QuestionParcel();
        dsc7.setQuestionNumber(6);
        dsc7.setQuestion("Wie viele Sterne hat die EU-Flagge?");
        String[] answers7 = new String[]{
                "5 Sterne",
                "6 Sterne",
                "7 Sterne",
                "8 Sterne",
                "9 Sterne",
                "10 Sterne",
                "11 Sterne",
                "12 Sterne",
                "13 Sterne",
                "14 Sterne",
                "15 Sterne",
                "16 Sterne",
                "17 Sterne",
                "18 Sterne",
                "19 Sterne",
                "Diese Anwort ist falsch - sie ist also nur dazu da, einen wirklich seeeehr langen Antwortsatz zu testen! Um es noch einmal zu sagen: Hier kein Kreuz machen !!!"
        };
        dsc7.setNumberAnswers(answers7.length);
        dsc7.setAnswers(answers7);
//        dsc7.setCorrectAnswer(15);

        QuestionParcel dsc8 = new QuestionParcel();
        dsc8.setQuestionNumber(7);
        dsc8.setQuestion("Welches französische Denkmal steht in Paris und ist 327 Meter hoch?");
        dsc8.setNumberAnswers(5);
        dsc8.setAnswers(new String[]{"Chinesische Turm", "Empire State Building", "Eiffellturm", "Türme von Hanoi", "Sinwellturm"});
//        dsc8.setCorrectAnswer(2);

        QuestionParcel dsc9 = new QuestionParcel();
        dsc9.setQuestionNumber(8);
        dsc9.setQuestion("Welche Bedeutung hat der Satz 'The quick brown fox jumps over the lazy dog'? Oder aber der Satz 'Zwölf Boxkämpfer jagen Viktor quer über den großen Sylter Deich'.");
        String[] answers9 = new String[]{
                "Ja",
                "Die Frage könnte man auch mit dem Satz 'Franz jagt im komplett verwahrlosten Taxi quer durch Bayern' gleichsetzen. Oder auch mit dem Satz 'Prall vom Whisky flog Quax den Jet zu Bruch'!",
                "Weiß nicht",
                "Auch diese Anwort sollte man nicht ankreuzen - aber sie hat den Zweck, einen wirklich seeeehr langen Antwortsatz zu testen!"
        };
        dsc9.setNumberAnswers(answers9.length);
        dsc9.setAnswers(answers9);
//        dsc9.setCorrectAnswer(0);

        QuestionParcel dsc10 = new QuestionParcel();
        dsc10.setQuestionNumber(9);
        dsc10.setQuestion("Wie heißt die Hauptstadt von Portugal?");
        dsc10.setNumberAnswers(4);
        dsc10.setAnswers(new String[]{"Barcelona", "Lissabon", "Porto", "La Valetta"});
//        dsc10.setCorrectAnswer(3);

        this.parcels[0] = dsc1;
        this.parcels[1] = dsc2;
        this.parcels[2] = dsc3;
        this.parcels[3] = dsc4;
        this.parcels[4] = dsc5;
        this.parcels[5] = dsc6;
        this.parcels[6] = dsc7;
        this.parcels[7] = dsc8;
        this.parcels[8] = dsc9;
        this.parcels[9] = dsc10;
    }
}
