package de.peterloos.knowledgeexam.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import java.util.ArrayList;

import de.peterloos.knowledgeexam.Globals;
import de.peterloos.knowledgeexam.R;
import de.peterloos.knowledgeexam.parcels.QuestionParcel;

public class QuestionsSummaryFragment extends Fragment {

    private Button btnSend;

    // no-args c'tor required
    public QuestionsSummaryFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_questions_summary, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // extract this fragment's question from bundle
        Bundle bundle = this.getArguments();
        if (bundle != null) {

            ArrayList<QuestionParcel> summary = bundle.getParcelableArrayList(Globals.ALL_QUESTIONS_PARCEL);

            Log.v(Globals.TAG, "QuestionsSummaryFragment::onViewCreated");
            Log.v(Globals.TAG, "  --> all questions:  " + summary.size());
        }
        else {
            Log.e(Globals.TAG, "NO Bundle found !!!");
        }

        // setup UI
        this.btnSend = view.findViewById(R.id.buttonSend);
        this.btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.v(Globals.TAG ,"arghhh");
            }
        });
    }
}
