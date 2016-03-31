package com.rhysma.feedback.feedbackhq;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;

import java.io.File;
import java.io.FileOutputStream;
import java.util.Random;


public class NewSessionActivity extends AppCompatActivity {

    private GoogleApiClient client;
    private String sessionType;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_session);
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
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        */

        Intent intent = getIntent();

        //add session types to list
        final ListView lv = (ListView) findViewById(R.id.typeList);

        //list of values
        String[] values = new String[]{
                "Lecture",
                "Seminar",
                "Theatre"
        };

        // Defined Array values to show in ListView
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, android.R.id.text1, values);

        // Assign adapter to ListView
        if(lv != null)
            lv.setAdapter(adapter);


        // ListView Item Click Listener
        if (lv == null) throw new AssertionError();
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {

                // ListView Clicked item value
                sessionType = (String)lv.getItemAtPosition(position);

            }


        }); //end of ListView click listener
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }

    @Override
    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "NewSession Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app deep link URI is correct.
                Uri.parse("android-app://com.rhysma.feedback.feedbackhq/http/host/path")
        );
        AppIndex.AppIndexApi.start(client, viewAction);
    }

    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "NewSession Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app deep link URI is correct.
                Uri.parse("android-app://com.rhysma.feedback.feedbackhq/http/host/path")
        );
        AppIndex.AppIndexApi.end(client, viewAction);
        client.disconnect();
    }

    public void startSessionClick(View view)
    {
        //this session needs a new unique id
        Random rand = new Random();
        int id = rand.nextInt(10000);

        //what kind of session is this? Check the variable and create an object
        FeedbackSession mySession = new FeedbackSession();

        //set the ID
        mySession.setSessionId(id);

        //set the session as created now
        mySession.setDateCreated();

        //set the session type that the user chose
        mySession.setSessionType(sessionType);

        //new session filename
        String filename = Integer.toString(id);
        File file = new File(getFilesDir() + "/" + filename);

        //create new session object file
        FileOutputStream outputStream;

        try
        {
            //write out the contents of the session object to the file
            //so it can be stored and uploaded
            outputStream = openFileOutput(file.getName(), Context.MODE_PRIVATE);
            outputStream.write(mySession.toString().getBytes());
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

        //notify user session is ready
        Toast.makeText(this, "Your session is ready!", Toast.LENGTH_LONG).show();


        //all done?  go back to previous activity
        finish();

    }
}
