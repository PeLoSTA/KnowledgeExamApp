package de.peterloos.knowledgeexam.models;

import android.os.Parcel;
import android.os.Parcelable;

public class QuestionParcel implements Parcelable {

    // member data
    private int questionNumber;
    private String question;
    private int numberAnswers;
    private String[] answers;
    private int[] correctAnswers;
    private boolean[] usersAnswers;

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
        // this.setCorrectAnswers(pc.readInt());
        pc.readIntArray(this.getCorrectAnswers());
        pc.readBooleanArray(this.usersAnswers);
    }

    // user-defined c'tor(s)
    public QuestionParcel() {
        this.setQuestionNumber(0);
        this.setQuestion("");
        this.setNumberAnswers(0);
        this.setAnswers(null);
        this.setCorrectAnswers(new int[] { 0 });
    }

//    public QuestionParcel(int questionNumber, String question, int numberAnswers, String[] answers) {
//        this.setQuestionNumber(questionNumber);
//        this.setQuestion(question);
//        this.setNumberAnswers(numberAnswers);
//        this.setAnswers(answers);
//    }

    @Override
    public void writeToParcel(Parcel parcel, int flags) {
        parcel.writeInt(this.getQuestionNumber());
        parcel.writeString(this.getQuestion());
        parcel.writeInt(this.getNumberAnswers());
        parcel.writeStringArray(this.getAnswers());
        // parcel.writeInt(this.getCorrectAnswers());
        parcel.writeIntArray(this.getCorrectAnswers());
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

        // allocate array of boolean values according to user's answers
        this.usersAnswers = new boolean[this.numberAnswers];
    }

    public String[] getAnswers() {
        return this.answers;
    }

    public void setAnswers(String[] answers) {
        this.answers = answers;
    }

    public boolean getUsersAnswer(int index) {
        return usersAnswers[index];
    }

    public void setUsersAnswer(int index, boolean value) {
        this.usersAnswers[index] = value;
    }

    public int[] getCorrectAnswers() {
        return this.correctAnswers;
    }

    public void setCorrectAnswers(int[] correctAnswers) {
        this.correctAnswers = correctAnswers;
    }
}
