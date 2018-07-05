package de.peterloos.knowledgeexam.activities;

/*
 * TODO Liste
 *
 * Es ist in der Answers View zwischen Radio Buttons und Checkboxes zu unterscheiden
 * Das ist bei Radiobuttons nicht so einfach, da diese nicht in einer Group zusammengefasst werden können!
 */


/*Some hints:
 *
 * Best practives for fragments:
 * SO: https://stackoverflow.com/questions/9245408/best-practice-for-instantiating-a-new-android-fragment
 */

// LINK FOR IMPORTANT SAMPLES

// http://www.zoftino.com/android-viewpager-with-custom-pager-adapter

// https://sdgsystems.com/blog/android-viewpager-fragments-tutorial-and-sample-project

// creating Fragments:
// https://stackoverflow.com/questions/9245408/best-practice-for-instantiating-a-new-android-fragment



import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

import de.peterloos.knowledgeexam.R;

public class MainActivity extends AppCompatActivity {

    private Button buttonLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
//        this.setSupportActionBar(toolbar);

        // retrieve control references
        this.buttonLogin = this.findViewById(R.id.buttonLogIn);

        this.buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent demoIntent = new Intent(getApplicationContext(), ChooseExamActivity.class);
                MainActivity.this.startActivity(demoIntent);
            }
        });
    }
}
