package de.peterloos.knowledgeexam.models;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.Map;

public class ExamParcel implements Parcelable {

    private String pin;
    private String description;
    // private Map<String, String> questions;
    private int numQuestions;

    /**
     * c'tor from Parcel, reads back fields IN THE ORDER they were written
     */
    public ExamParcel(Parcel pc) {
        this.pin = pc.readString();
        this.description = pc.readString();
        this.numQuestions = pc.readInt();
//        pc.readStringArray(this.answers);
//        this.correctAnswer = pc.readInt();
//        pc.readBooleanArray(this.usersAnswers);
    }

    public ExamParcel(String pin, String description, int numQuestions) {

        this.pin = pin;
        this.description = description;
        this.numQuestions = numQuestions;
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
    }

    @Override
    public String toString() {
        return "Yeahhhhhhhhhhhhhhhhhhh";
    }
}
