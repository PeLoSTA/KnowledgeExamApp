package de.peterloos.knowledgeexam.models;

public class QuestionSummaryModel {

    // member data
    private int questionNumber;
    private int numberAnswers;
    private boolean[] userResults;
    private boolean singleChoice;

    public int getQuestionNumber() {
        return questionNumber;
    }

    public void setQuestionNumber(int questionNumber) {
        this.questionNumber = questionNumber;
    }

    public int getNumberAnswers() {
        return numberAnswers;
    }

    public void setNumberAnswers(int numberAnswers) {
        this.numberAnswers = numberAnswers;
    }

//    public boolean isSingleChoice() {
//        return this.numberAnswers == 1;
//    }

    public boolean[] getUserResults() {
        return userResults;
    }

    public void setUserResults(boolean[] userResults) {
        this.userResults = userResults;
    }

    public boolean isSingleChoice() {
        return this.singleChoice;
    }

    public void setSingleChoice(boolean singleChoice) {
        this.singleChoice = singleChoice;
    }
}
