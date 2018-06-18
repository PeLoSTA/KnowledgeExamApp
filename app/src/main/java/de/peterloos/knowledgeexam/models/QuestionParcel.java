package de.peterloos.knowledgeexam.models;

import android.os.Parcel;
import android.os.Parcelable;

public class QuestionParcel implements Parcelable {

    // member data
    private int questionNumber;     // TODO ???
    private String question;
    private int numberAnswers;
    private String[] answers;
    // private int correctAnswer;   // TODO ???
    private boolean[] usersAnswers; // TODO ???  Wie kommen die Antworten zurück ?!?!?!

    // static field used to regenerate object, individually or as array
    public static final Parcelable.Creator<QuestionParcel> CREATOR =
        new Parcelable.Creator<QuestionParcel>() {
            public QuestionParcel createFromParcel(Parcel pc) {
                return new QuestionParcel(pc);
            }

            public QuestionParcel[] newArray(int size) {
                return new QuestionParcel[size];
            }
        };

    // system-defined c'tor from Parcel, reads back fields IN THE ORDER they were written
    public QuestionParcel(Parcel pc) {
        this.setQuestionNumber(pc.readInt());
        this.setQuestion(pc.readString());
        this.setNumberAnswers(pc.readInt());
        pc.readStringArray(this.getAnswers());
        // this.correctAnswer = pc.readInt();
        pc.readBooleanArray(this.usersAnswers);
    }

    // user-defined c'tor(s)
    public QuestionParcel() {
        this.setQuestionNumber(0);
        this.setQuestion("");
        this.setNumberAnswers(0);
    }

    public QuestionParcel(int questionNumber, String question, int numberAnswers, String[] answers) {
        this.setQuestionNumber(questionNumber);
        this.setQuestion(question);
        this.setNumberAnswers(numberAnswers);
        this.setAnswers(answers);
    }

    @Override
    public void writeToParcel(Parcel parcel, int flags) {
        parcel.writeInt(this.getQuestionNumber());
        parcel.writeString(this.getQuestion());
        parcel.writeInt(this.getNumberAnswers());
        parcel.writeStringArray(this.getAnswers());
        // parcel.writeInt(this.correctAnswer);
        parcel.writeBooleanArray(this.usersAnswers);

    }

    @Override
    public int describeContents() {
        return 0;
    }

    public int getQuestionNumber() {
        return this.questionNumber;
    }

    public void setQuestionNumber(int questionNumber) {
        this.questionNumber = questionNumber;
    }

    public String getQuestion() {
        return this.question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public int getNumberAnswers() {
        return this.numberAnswers;
    }

    public void setNumberAnswers(int numberAnswers) {
        this.numberAnswers = numberAnswers;
    }

    public String[] getAnswers() {
        return answers;
    }

    public void setAnswers(String[] answers) {
        this.answers = answers;
    }
}
