package com.example.jamie.sentwinel;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


public class MainActivity extends AppCompatActivity {


    private Button button;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button = findViewById(R.id.searchBtn);


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openListActivity();
            }
        });
    }

    public void openListActivity() {
        //Sets variables to transfer from textboxes
        EditText editText1 = findViewById(R.id.editText1);
        String tweetText = editText1.getText().toString();

        EditText editText2 = findViewById(R.id.editText2);
        String tweetNo = editText2.getText().toString();
        //int tweetNo = Integer.parseInt(editText2.getText().toString());

        //EXTRA_TEXT = tweetText;
        //EXTRA_NUMBER = tweetNo;



        //Sends the variables to Activity 2
        Intent intent = new Intent(this, ListActivity.class);
        intent.putExtra("EXTRA_TEXT", tweetText);
        intent.putExtra("EXTRA_NUMBER", tweetNo);
        startActivity(intent);
    }



}
