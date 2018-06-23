package de.peterloos.knowledgeexam.adapters;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.RadioButton;

import java.util.Locale;

import de.peterloos.knowledgeexam.Globals;
import de.peterloos.knowledgeexam.R;
import de.peterloos.knowledgeexam.interfaces.OnAnswersListener;
import de.peterloos.knowledgeexam.models.Answer;

public class AnswersAdapter extends ArrayAdapter<Answer> {

    private final int resource;
    private boolean useCheckBox;
    private OnAnswersListener listener;

    static class ViewHolder {
        public CheckBox checkbox;
        public RadioButton radioButton;
    }

    // c'tor
    public AnswersAdapter(@NonNull Context context, int resource, @NonNull Answer[] answers, boolean useCheckBox) {
        super(context, resource, answers);
        this.resource = resource;
        this.listener = null;
        this.useCheckBox = useCheckBox;
    }

    // public interface
    public void addOnAnswersListener(OnAnswersListener listener) {
        this.listener = listener;
    }

    public void removeOnAnswersListener(OnAnswersListener l) {
        this.listener = null;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder viewHolder;
        if (convertView == null) {

            // inflate the layout
            LayoutInflater inflater = ((Activity) this.getContext()).getLayoutInflater();
            convertView = inflater.inflate(this.resource, parent, false);

            // setup ViewHolder object
            viewHolder = new ViewHolder();
            viewHolder.checkbox = convertView.findViewById(R.id.cbAnswer);
            viewHolder.radioButton = convertView.findViewById(R.id.rbAnswer);

            // store the holder within the view
            convertView.setTag(viewHolder);
        } else {

            // ViewHolder object has been created before
            viewHolder = (ViewHolder) convertView.getTag();
        }

        Answer answer = this.getItem(position);

        if (this.useCheckBox) {
            viewHolder.radioButton.setVisibility(View.INVISIBLE);
            viewHolder.checkbox.setText(answer.getAnswer());
            viewHolder.checkbox.setChecked(answer.getChecked());
            viewHolder.checkbox.setTag(position);
            viewHolder.checkbox.setOnClickListener(new View.OnClickListener() {

                public void onClick(View view) {
                    CheckBox cb = (CheckBox) view;
                    int pos = ((Integer) cb.getTag()).intValue();
                    String msg = String.format(
                            Locale.getDefault(), "clicked at check box: %d ==> %b",
                            pos, cb.isChecked());
                    Log.v(Globals.TAG, msg);
                    if (listener != null) {
                        listener.answerSelected(pos, cb.isChecked());
                    }
                }
            });
        } else {
            viewHolder.checkbox.setVisibility(View.INVISIBLE);
            viewHolder.radioButton.setText(answer.getAnswer());
            viewHolder.radioButton.setChecked(answer.getChecked());
            viewHolder.radioButton.setTag(position);
            viewHolder.radioButton.setOnClickListener(new View.OnClickListener() {

                public void onClick(View view) {
                    RadioButton rb = (RadioButton) view;
                    int pos = ((Integer) rb.getTag()).intValue();
                    String msg = String.format(
                            Locale.getDefault(), "clicked at radio button: %d ==> %b",
                            pos, rb.isChecked());
                    Log.v(Globals.TAG, msg);
                    if (listener != null) {
                        listener.answerSelected(pos, rb.isChecked());
                    }
                }
            });
        }

        return convertView;
    }
}
