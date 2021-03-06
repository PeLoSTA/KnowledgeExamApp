package de.peterloos.knowledgeexam.activities;

import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import java.util.Locale;

import de.peterloos.knowledgeexam.Globals;
import de.peterloos.knowledgeexam.R;
import de.peterloos.knowledgeexam.adapters.QuestionsAdapter;
import de.peterloos.knowledgeexam.interfaces.OnQuestionAndAnswersListener;
import de.peterloos.knowledgeexam.interfaces.OnQuestionSelection;
import de.peterloos.knowledgeexam.parcels.ExamParcel;

public class TakeExamActivity extends AppCompatActivity implements OnQuestionAndAnswersListener, OnQuestionSelection {

    /**
     * The android.support.v4.view.PagerAdapter that will provide
     * fragments for each of the sections. We use a
     * FragmentPagerAdapter derivative, which will keep every
     * loaded fragment in memory. If this becomes too memory intensive, it
     * may be best to switch to a android.support.v4.app.FragmentStatePagerAdapter
     */
    private ViewPager viewPager;
    private QuestionsAdapter questionsAdapter;
    private ExamParcel parcel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.activity_take_exam);

        // retrieve pin from initiating activity
        Bundle extras = this.getIntent().getExtras();
        if (extras != null) {
            this.parcel = this.getIntent().getParcelableExtra(Globals.EXAM_PARCEL);
        }

        // create the adapter that will return a fragment for each question
        this.questionsAdapter = new QuestionsAdapter(
            this.getSupportFragmentManager(),
            this.getApplicationContext());

        // setup the ViewPager with the question adapter
        this.viewPager = this.findViewById(R.id.container);
        this.viewPager.setAdapter(this.questionsAdapter);
    }

    // implementation of interface 'OnQuestionAndAnswersListener'
    @Override
    public void answerOfQuestionSelected(int questionNumber, int answerPosition, boolean checked) {
        this.questionsAdapter.updateAnswer(questionNumber, answerPosition, checked);
    }

    // implementation of interface 'OnQuestionSelection'
    @Override
    public void selectQuestion(int number) {

        String msg = String.format(Locale.getDefault(), "TakeExamActivity::selectQuestion ----> %d", number);
        Log.v(Globals.TAG, msg);

        this.viewPager.setCurrentItem(number, true);
    }

    // public interface
    public void postResults () {

        this.questionsAdapter.postResults();
    }
}
