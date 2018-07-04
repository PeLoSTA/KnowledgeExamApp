package de.peterloos.knowledgeexam.fragments;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import java.util.Locale;

import de.peterloos.knowledgeexam.Globals;
import de.peterloos.knowledgeexam.R;
import de.peterloos.knowledgeexam.adapters.AnswersAdapter;
import de.peterloos.knowledgeexam.interfaces.OnAnswersListener;
import de.peterloos.knowledgeexam.interfaces.OnQuestionAndAnswersListener;
import de.peterloos.knowledgeexam.models.Answer;
import de.peterloos.knowledgeexam.models.QuestionParcel;

public class QuestionFragment extends Fragment implements OnAnswersListener {

    private TextView tvQuestionHeader;
    private TextView tvQuestion;
    private ListView lvAnswers;

    // data of this question
    // TODO: DAS IST ZU KLÄREN, OB EINE PARCEL KLASSE HIER GEEIGNET IST ....
    private QuestionParcel question;

    private OnQuestionAndAnswersListener listener;

    // no-args c'tor required
    public QuestionFragment() {
    }

    public static QuestionFragment newInstance() {
        return new QuestionFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_question, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        view.setBackgroundColor(Color.RED);

        this.tvQuestionHeader = view.findViewById(R.id.textviewQuestionHeader);
        this.tvQuestion = view.findViewById(R.id.textviewQuestion);
        this.lvAnswers = view.findViewById(R.id.listviewAnswers);

        // =============================================================================

        // extract this fragment's question from bundle
        Bundle bundle = this.getArguments();

        if (bundle != null) {
            this.question = bundle.getParcelable(Globals.QUESTION_PARCEL);

            Log.v(Globals.TAG, "FragmentQuestion ==> Frage " + question.getQuestion());
        } else {
            this.question = new QuestionParcel();
            this.question.setQuestionNumber(0);
            this.question.setQuestion("INTERNAL ERROR");
            this.question.setNumberAnswers(1);
            this.question.setAnswers(new String[]{"NO ANSWER"});
            this.question.setCorrectAnswers(new int[]{0});

            // TODO: Die nächste Zeile möglicherweise freischalten
            // this.question.setUsersAnswer(0, new boolean[] {false});
        }

        // =============================================================================

        // setup UI
        int number = question.getQuestionNumber();
        String header = String.format(Locale.getDefault(), "Frage %d:", number);
        this.tvQuestionHeader.setText(header);
        this.tvQuestion.setText(question.getQuestion());

        // setup adapter for ListView with answers and
        // the latest user input according to these answers
        Answer[] answers = new Answer[question.getNumberAnswers()];
        String[] tmp = question.getAnswers();

        for (int i = 0; i < question.getNumberAnswers(); i++) {
            answers[i] = new Answer(tmp[i], question.getUsersAnswer(i));
        }

        boolean useCheckBox = (this.question.getCorrectAnswers().length == 1) ? false : true;
        AnswersAdapter adapter = new AnswersAdapter(
                this.getActivity(),
                R.layout.answer_row,
                answers,
                useCheckBox
        );
        adapter.addOnAnswersListener(this);
        this.lvAnswers.setAdapter(adapter);
    }

    // implementation of interface 'OnAnswersListener'
    @Override
    public void answerSelected(int position, boolean checked) {

        if (this.listener != null) {

            Log.v(Globals.TAG, "FragmentQuestion:: answerSelected ===> Frage = " + question.getQuestionNumber() + ", Antwort zu " + position + ", checked = " + checked);

            this.listener.answerOfQuestionSelected(question.getQuestionNumber(), position, checked);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        try {
            this.listener = (OnQuestionAndAnswersListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString() + " must implement OnQuestionAndAnswersListener");
        }
    }

    @Override
    public void onDetach() {

        this.listener = null;
        super.onDetach();
    }
}
