package de.peterloos.knowledgeexam.models;

import com.google.firebase.database.PropertyName;

import java.util.Locale;
import java.util.Map;

public class Question {

    private String question;
    private int numAnswers;
    private int numCorrectAnswers;
    private String courseKey;
    private Map<String, String> answers;
    private Map<String, Boolean> correctAnswers;

    // mandatory: default constructor that takes no arguments
    public Question() {
    }

    @SuppressWarnings("unused")
    public String print() {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("Course-Key: %s", this.getCourseKey()));
        sb.append(System.getProperty("line.separator"));
        sb.append(String.format(Locale.getDefault(), "Number of Answers: %d", this.getNumAnswers()));
        sb.append(" - ");
        sb.append(String.format(Locale.getDefault(), "Number of correct Answers: %d", this.getNumCorrectAnswers()));
        sb.append(System.getProperty("line.separator"));
        sb.append(String.format("Text of Question: %s", this.getQuestion()));
        sb.append(System.getProperty("line.separator"));

        // traversal runs unordered --- TODO !!!!!!!!!!!!!
        for (String value : this.answers.values()) {
            sb.append(String.format("    Answer: %s", value));
            sb.append(System.getProperty("line.separator"));
        }

        return sb.toString();
    }

    @SuppressWarnings("unused")
    public String getQuestion() {
        return this.question;
    }

    @SuppressWarnings("unused")
    public void setQuestion(String question) {
        this.question = question;
    }

    @SuppressWarnings("unused")
    @PropertyName("num-answers")
    public int getNumAnswers() {
        return this.numAnswers;
    }

    @SuppressWarnings("unused")
    @PropertyName("num-answers")
    public void setNumAnswers(int numAnswers) {
        this.numAnswers = numAnswers;
    }

    @SuppressWarnings("unused")
    @PropertyName("num-correct-answers")
    public int getNumCorrectAnswers() {
        return this.numCorrectAnswers;
    }

    @SuppressWarnings("unused")
    @PropertyName("num-correct-answers")
    public void setNumCorrectAnswers(int numCorrectAnswers) {
        this.numCorrectAnswers = numCorrectAnswers;
    }

    @SuppressWarnings("unused")
    @PropertyName("course-key")
    public String getCourseKey() {
        return courseKey;
    }

    @SuppressWarnings("unused")
    @PropertyName("course-key")
    public void setCourseKey(String courseKey) {
        this.courseKey = courseKey;
    }

    @SuppressWarnings("unused")
    public Map<String, String> getAnswers() {
        return this.answers;
    }

    @SuppressWarnings("unused")
    public void setAnswers(Map<String, String> answers) {
        this.answers = answers;
    }

    @SuppressWarnings("unused")
    @PropertyName("correct-answers")
    public Map<String, Boolean> getCorrectAnswers() {
        return correctAnswers;
    }

    @SuppressWarnings("unused")
    @PropertyName("correct-answers")
    public void setCorrectAnswers(Map<String, Boolean> correctAnswers) {
        this.correctAnswers = correctAnswers;
    }

    @Override
    public String toString() {
        return this.print();
    }
}
