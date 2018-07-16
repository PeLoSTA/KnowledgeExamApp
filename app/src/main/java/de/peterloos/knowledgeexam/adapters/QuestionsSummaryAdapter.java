package de.peterloos.knowledgeexam.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import de.peterloos.knowledgeexam.R;
import de.peterloos.knowledgeexam.models.ExamModel;
import de.peterloos.knowledgeexam.models.QuestionSummaryModel;

public class QuestionsSummaryAdapter extends ArrayAdapter<QuestionSummaryModel> {

    private Context context;

    public QuestionsSummaryAdapter(@NonNull Context context) {
        super(context, R.layout.summary_row);
        this.context = context;
    }

    public QuestionsSummaryAdapter(@NonNull Context context, List<QuestionSummaryModel> values) {
        super(context, R.layout.summary_row);
        this.context = context;
        this.addAll(values);
    }

    @Override
    public View getView(int pos, View convertView, ViewGroup parent) {

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.summary_row, parent, false);
        TextView tv = rowView.findViewById(R.id.labelQuestionNumber);
        QuestionSummaryModel questionSummaryModel = this.getItem(pos);
        // tv.setText(questionSummaryModel.getPin());
        String s = String.format("Frage %d", pos);
        tv.setText(s);
        return rowView;
    }
}