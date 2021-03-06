package de.peterloos.knowledgeexam.models;

import com.google.firebase.database.PropertyName;

import java.util.Locale;
import java.util.Map;

public class ExamModel {

    private String description;
    private String pin;
    private int numQuestions;
    private Map<String, String> questions;

    // mandatory: default constructor that takes no arguments
    public ExamModel() {
    }

    @SuppressWarnings("unused")
    public String getDescription() {
        return this.description;
    }

    @SuppressWarnings("unused")
    public void setDescription(String description) {
        this.description = description;
    }

    @SuppressWarnings("unused")
    public String getPin() {
        return this.pin;
    }

    @SuppressWarnings("unused")
    public void setPin(String pin) {
        this.pin = pin;
    }

    @SuppressWarnings("unused")
    public Map<String, String> getQuestions() {
        return this.questions;
    }

    @SuppressWarnings("unused")
    public void setQuestions(Map<String, String> questions) {
        this.questions = questions;
    }

    @SuppressWarnings("unused")
    @PropertyName("num-questions")
    public int getNumQuestions() {
        return numQuestions;
    }

    @SuppressWarnings("unused")
    @PropertyName("num-questions")
    public void setNumQuestions(int num_questions) {
        this.numQuestions = num_questions;
    }

    @Override
    public String toString() {
        return this.print();
    }

    @SuppressWarnings("unused")
    private String print() {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("Pin: %s", this.pin));
        sb.append(" - ");
        sb.append(String.format("Description: %s", this.description));
        sb.append(" - ");
        sb.append(String.format(Locale.getDefault(), "NumQuestions: %d", this.numQuestions));
        sb.append(System.getProperty("line.separator"));

        for (String key : this.questions.values()) {
            sb.append(String.format("    QuestionModel: %s", key));
            sb.append(System.getProperty("line.separator"));
        }

        return sb.toString();
    }
}
