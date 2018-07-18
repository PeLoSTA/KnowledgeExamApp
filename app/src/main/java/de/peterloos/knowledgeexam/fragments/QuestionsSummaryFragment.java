package de.peterloos.knowledgeexam.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import de.peterloos.knowledgeexam.Globals;
import de.peterloos.knowledgeexam.R;
import de.peterloos.knowledgeexam.adapters.QuestionsSummaryAdapter;
import de.peterloos.knowledgeexam.models.QuestionSummaryModel;
import de.peterloos.knowledgeexam.parcels.QuestionParcel;

public class QuestionsSummaryFragment extends Fragment {

    private ListView lvSummary;
    private Button btnSend;
    private QuestionsSummaryAdapter adapter;

    // no-args c'tor required
    public QuestionsSummaryFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Log.v(Globals.TAG, "QuestionsSummaryFragment::onCreateView");
        return inflater.inflate(R.layout.fragment_questions_summary, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // extract this fragment's question from bundle
        Bundle bundle = this.getArguments();
        ArrayList<QuestionParcel> parcels = null;
        if (bundle != null) {
            parcels = bundle.getParcelableArrayList(Globals.ALL_QUESTIONS_PARCEL);
        } else {
            Log.e(Globals.TAG, "NO Bundle found !!!");
        }

        // setup UI
        this.lvSummary = view.findViewById(R.id.listviewSummary);
        List<QuestionSummaryModel> summary = new ArrayList<>();

        if (parcels != null) {
            for (int i = 0; i < parcels.size(); i++) {

                QuestionParcel parcel = parcels.get(i);
                QuestionSummaryModel model = new QuestionSummaryModel();
                model.setQuestionNumber(i+1);
                model.setNumberAnswers(parcel.getNumberAnswers());
                model.setUserResults(parcel.getUserResults());
                model.setSingleChoice(parcel.isSingleChoice());

                summary.add(model);
            }
        }

        this.adapter = new QuestionsSummaryAdapter(this.getContext(), summary);
        this.lvSummary.setAdapter(this.adapter);

        // setup UI
        this.btnSend = view.findViewById(R.id.buttonSend);
        this.btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.v(Globals.TAG, "arghhh");
            }
        });
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.v(Globals.TAG, "QuestionsSummaryFragment::onDestroy");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Log.v(Globals.TAG, "QuestionsSummaryFragment::onDestroyView");
    }
}
