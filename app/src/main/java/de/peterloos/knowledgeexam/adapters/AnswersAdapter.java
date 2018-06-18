package de.peterloos.knowledgeexam.adapters;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;

import de.peterloos.knowledgeexam.R;
import de.peterloos.knowledgeexam.models.Answer;

public class AnswersAdapter extends ArrayAdapter<Answer> {

    private final int resource;

    static class ViewHolder {
        public CheckBox checkbox;
    }

    public AnswersAdapter(@NonNull Context context, int resource, @NonNull Answer[] answers) {
        super(context, resource, answers);
        this.resource = resource;
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
            viewHolder.checkbox = convertView.findViewById(R.id.checkBoxAnswer);

            // store the holder within the view
            convertView.setTag(viewHolder);
        } else {

            // ViewHolder object has been created before
            viewHolder = (ViewHolder) convertView.getTag();
        }

        Answer answer = this.getItem(position);
        viewHolder.checkbox.setText(answer.getAnswer());
        // TODO: Wenn das läuft, diese Variable weg-optimieren ....
        boolean isChecked = answer.getChecked();
        viewHolder.checkbox.setChecked( isChecked);
        viewHolder.checkbox.setTag(new Integer(position));

//        viewHolder.checkbox.setOnClickListener(new View.OnClickListener() {
//            public void onClick(View view) {
//
//                CheckBox cb = (CheckBox) view;
//                int pos = ((Integer) cb.getTag()).intValue();
//                if (listener != null) {
//
//                    listener.answerSelected(pos, cb.isChecked());
//                }
//            }
//        });

        return convertView;
    }
}
