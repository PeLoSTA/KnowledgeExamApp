package de.peterloos.knowledgeexam.parcels;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.Locale;
import java.util.Map;

import de.peterloos.knowledgeexam.models.QuestionModel;

@SuppressWarnings("WeakerAccess")
public class QuestionParcel implements Parcelable {

    // member data
    private int questionNumber;
    private String question;
    private int numberAnswers;
    private String[] answers;
    // TODO: Das eine ist BOOL , das andere ist INTEGER .... EINHEITLICH Machen !!!
    private int[] results;
    private boolean[] userResults;

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
        pc.readIntArray(this.getResults());
        pc.readBooleanArray(this.getUserResults());
    }

    // user-defined c'tor(s)
    public QuestionParcel(int number, QuestionModel questionModel) {

        this.setQuestionNumber(number);
        this.setQuestion(questionModel.getQuestion());

        int numAnswers = questionModel.getNumAnswers();
        this.setNumberAnswers(numAnswers);

        // traverse hash map of answers
        String[] answers = new String[numAnswers];
        Map<String, String> answersMap = questionModel.getAnswers();
        for (int i = 0; i < numAnswers; i++) {
            String key = "answer" + (i + 1);
            String value = answersMap.get(key);
            answers[i] = value;
        }
        this.setAnswers(answers);

        // traverse hash map of correct answers
        int[] correctAnswers = new int[numAnswers];
        Map<String, Boolean> correctAnswersMap = questionModel.getResults();
        for (int i = 0; i < numAnswers; i++) {
            String key = "answer" + (i + 1);
            Boolean value = correctAnswersMap.get(key);
            correctAnswers[i] = value ? 1 : 0;
        }
        this.setResults(correctAnswers);

        // provide (yet) empty array of users answers
        boolean[] usersAnswers = new boolean[numAnswers];
        this.setUserResults(usersAnswers);
    }

    @Override
    public void writeToParcel(Parcel parcel, int flags) {
        parcel.writeInt(this.getQuestionNumber());
        parcel.writeString(this.getQuestion());
        parcel.writeInt(this.getNumberAnswers());
        parcel.writeStringArray(this.getAnswers());
        parcel.writeIntArray(this.getResults());
        parcel.writeBooleanArray(this.getUserResults());
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
        return this.answers;
    }

    public void setAnswers(String[] answers) {
        this.answers = answers;
    }

    public int[] getResults() {
        return this.results;
    }

    public void setResults(int[] results) {
        this.results = results;
    }

    public boolean[] getUserResults() {
        return this.userResults;
    }

    public void setUserResults(boolean[] userResults) {
        this.userResults = userResults;
    }

    @SuppressWarnings("unused")
    public boolean getUsersAnswer(int index) {
        return this.userResults[index];
    }

    public void setUsersAnswer(int index, boolean value) {
        this.userResults[index] = value;
    }

    @Override
    public String toString() {
        return this.print();
    }

    @SuppressWarnings("unused")
    private String print() {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format(Locale.getDefault(), "Number of Answers: %d", this.getNumberAnswers()));
        sb.append(System.getProperty("line.separator"));
        sb.append(String.format("Text of Question: %s", this.getQuestion()));
        sb.append(System.getProperty("line.separator"));

        // traverse answers
        for (int i = 0; i < this.answers.length; i++) {
            sb.append(String.format(
                    Locale.getDefault(),
                    "  Answer %d: %s", (i + 1), this.answers[i]));
            sb.append(System.getProperty("line.separator"));
        }

        // traverse results of answers
        for (int i = 0; i < this.results.length; i++) {
            sb.append(String.format(
                    Locale.getDefault(),
                    "  Result %d: %d", (i + 1), this.results[i]));
            sb.append(System.getProperty("line.separator"));
        }

        // traverse results of answers
        for (int i = 0; i < this.results.length; i++) {
            sb.append(String.format(
                    Locale.getDefault(),
                    "  User Result %d: %b", (i + 1), this.userResults[i]));
            sb.append(System.getProperty("line.separator"));
        }

        return sb.toString();
    }
}
