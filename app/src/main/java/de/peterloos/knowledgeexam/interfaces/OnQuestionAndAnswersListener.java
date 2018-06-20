package de.peterloos.knowledgeexam.interfaces;

public interface OnQuestionAndAnswersListener {
    void answerOfQuestionSelected(int questionNumber, int answerPosition, boolean checked);
}
