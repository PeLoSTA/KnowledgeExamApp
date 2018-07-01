package de.peterloos.knowledgeexam.models;

import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.util.Log;

import java.util.Locale;

import de.peterloos.knowledgeexam.Globals;

public class ExamParcel implements Parcelable {

    private String pin;
    private String description;
    private String[] questionKeys;
    private int numQuestions;

    // system-defined c'tor from Parcel, reads back fields IN THE ORDER they were written
    public ExamParcel(Parcel pc) {
        this.pin = pc.readString();
        this.description = pc.readString();
        this.numQuestions = pc.readInt();
        this.questionKeys = pc.createStringArray();
    }

    // user-defined c'tor
    public ExamParcel(Exam exam) {

        this.pin = exam.getPin();
        this.description = exam.getDescription();
        this.numQuestions = exam.getNumQuestions();

        this.questionKeys = new String[exam.getQuestions().size()];
        int k = 0;
        for (String key : exam.getQuestions().values()) {
            this.questionKeys[k] = key;
            k++;
        }

        Log.v(Globals.TAG, " Size des Arrays ist gleich " + this.questionKeys.length);
    }

    /**
     * static field used to regenerate object, individually or as array
     */
    public static final Parcelable.Creator<ExamParcel> CREATOR =
            new Parcelable.Creator<ExamParcel>() {
                public ExamParcel createFromParcel(Parcel pc) {
                    return new ExamParcel(pc);
                }

                public ExamParcel[] newArray(int size) {

                    return new ExamParcel[size];
                }
            };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {

        parcel.writeString(this.pin);
        parcel.writeString(this.description);
        parcel.writeInt(this.numQuestions);
        parcel.writeStringArray(this.questionKeys);
    }

    @Override
    public String toString() {

        StringBuilder sb = new StringBuilder();
        sb.append(String.format("Pin: %s", this.pin));
        sb.append(" - ");
        sb.append(String.format("Description: %s", this.description));
        sb.append(" - ");
        sb.append(String.format(Locale.getDefault(), "NumQuestions: %d", this.numQuestions));
        sb.append(System.getProperty("line.separator"));

        for (int i = 0; i < this.questionKeys.length; i++) {
            sb.append(String.format("    Question-Key: %s", this.questionKeys[i]));
            sb.append(System.getProperty("line.separator"));
        }

        return sb.toString();
    }
}
