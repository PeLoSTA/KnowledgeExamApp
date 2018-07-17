package de.peterloos.knowledgeexam.adapters;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;

import java.util.List;

import de.peterloos.knowledgeexam.R;
import de.peterloos.knowledgeexam.models.QuestionSummaryModel;

public class QuestionsSummaryAdapter extends ArrayAdapter<QuestionSummaryModel> {

    private enum WhichButton {UseRadioButton, UseCheckBox}

    ;

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

        // construct horizontal row of checkboxes / radio buttons
        LinearLayout layoutUsersAnswers = rowView.findViewById(R.id.users_answers);

        WhichButton whichButton = (questionSummaryModel.isSingleChoice()) ?
            WhichButton.UseRadioButton :
            WhichButton.UseCheckBox;

        boolean[] userResults = questionSummaryModel.getUserResults();
        for (int i = 0; i < questionSummaryModel.getNumberAnswers(); i++) {

            CompoundButton button = (whichButton == WhichButton.UseRadioButton) ?
                new RadioButton(this.getContext()) :
                new CheckBox(this.getContext());
            button.setEnabled(false);

            if (userResults[i]) {
                button.setChecked(true);
            }

            layoutUsersAnswers.addView(button);
        }


                // CompoundButton
//        if (questionSummaryModel.isSingleChoice()) {
//
//            boolean[] userResults = questionSummaryModel.getUserResults();
//            for (int i = 0; i < questionSummaryModel.getNumberAnswers(); i++) {
//
//                RadioButton rb = new RadioButton(this.getContext());
//                rb.setEnabled(false);
//
//                if (userResults[i]) {
//                    rb.setChecked(true);
//                }
//
//                layoutUsersAnswers.addView(rb);
//            }
//        }
//        else {
//
//            boolean[] userResults = questionSummaryModel.getUserResults();
//            for (int i = 0; i < questionSummaryModel.getNumberAnswers(); i++) {
//
//                CheckBox cb = new CheckBox(this.getContext());
//                cb.setEnabled(false);
//
//                if (userResults[i]) {
//                    cb.setChecked(true);
//                }
//
//                layoutUsersAnswers.addView(cb);
//            }
//        }

                // assign drawable to this imageView
                ImageView iv = rowView.findViewById(R.id.ivQuestionAnswered);

        Drawable drawable = null;
        if (pos % 2 == 0) {

            drawable = ContextCompat.getDrawable(this.context, R.drawable.minus_box);
        } else {
            drawable = ContextCompat.getDrawable(this.context, R.drawable.checkbox_marked);
        }

        iv.setImageDrawable(drawable);

        return rowView;
    }
}
