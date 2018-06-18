package de.peterloos.knowledgeexam.models;

public class Answer {

    private String answer;     /* answer */
    private boolean checked;   /* false -> checkbox disabled, true -> checkbox enabled */

    public Answer(String answer, boolean checked){
        this.answer = answer;
        this.checked = checked;
    }

    public String getAnswer(){
        return this.answer;
    }

    public boolean getChecked(){
        return this.checked;
    }
}
