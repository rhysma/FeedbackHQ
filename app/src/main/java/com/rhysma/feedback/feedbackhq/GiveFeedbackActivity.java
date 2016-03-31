package com.rhysma.feedback.feedbackhq;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;

public class GiveFeedbackActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_give_feedback);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

       /* FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        getSupportActionBar().setDisplayHomeAsUpEnabled(true); */
    }

    //handles the click event for the submit button
    public void submitFeedbackButton(View view)
    {
        //get the session ID so we will know who to apply this rating to
        EditText session = (EditText)findViewById(R.id.sessionID);
        int id = Integer.parseInt(session.getText().toString());

        //get the rating bar object
        RatingBar bar  =(RatingBar)findViewById(R.id.sessionRating);
        float numStars = bar.getRating();

        //create the file to upload to s3 which will contain the rating feedback
        //new session filename
        String filename = Integer.toString(id) + "f";
        File file = new File(getFilesDir() + "/" + filename);

        //create new session object file
        FileOutputStream outputStream;

        try
        {
            //write out the number of stars given to the file
            //so it can be stored and uploaded
            outputStream = openFileOutput(file.getName(), Context.MODE_PRIVATE);
            outputStream.write(Float.toString(numStars).getBytes());
            outputStream.close();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }

        //start a new AWS Session
        AWSHelper.startAWSSession(getApplicationContext());

        //upload
        AWSHelper.uploadFile(file, filename, getApplicationContext());

        //notify the user
        Toast.makeText(this, "You have rated this session " + numStars + " stars", Toast.LENGTH_LONG).show();

        //all done?  go back to previous activity
        finish();
    }

}
