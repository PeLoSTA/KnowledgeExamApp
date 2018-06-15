package de.peterloos.knowledgeexam.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import de.peterloos.knowledgeexam.R;

public class MainActivity extends AppCompatActivity {

    private Button buttonLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // retrieve control references
        this.buttonLogin = this.findViewById(R.id.buttonLogIn);

        this.buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent demoIntent = new Intent(getApplicationContext(), ActivityChooseExam.class);
                startActivity(demoIntent);
            }
        });
    }
}
