package com.example.guest.wednesday;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private RelativeLayout background;
    private ListView listView;
    private Button submitButton;
    private RelativeLayout inputLayout;
    private RelativeLayout storyLayout;
    private EditText editText;
    private TextView story;

    ArrayList<String> madlibHints;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = (ListView) findViewById(R.id.list);
        submitButton = (Button) findViewById(R.id.submitButton);
        inputLayout = (RelativeLayout) findViewById(R.id.inputLayout);
        storyLayout = (RelativeLayout) findViewById(R.id.storyLayout);
        editText = (EditText) findViewById(R.id.editText);
        story = (TextView) findViewById(R.id.story);

        madlibHints = new ArrayList<String>(Arrays.asList(getResources().getStringArray(R.array.madlibHints)));
        final FormAdapter adapter = new FormAdapter(this, madlibHints);
        listView.setAdapter(adapter);

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int listLength = listView.getChildCount();
                ArrayList<String> results = new ArrayList<String>();
                for (int i = 0; i < listLength; i++) {
                    v = listView.getChildAt(i);
                    editText = (EditText) v.findViewById(R.id.editText);
                    results.add(editText.getText().toString());
                }
                Log.d("RESULTS", results.toString());

                String[] words = {"Adjective: ", "Noun: ", "Verb: ", "Person in room: "};
                String storyFinal = words[0] + results.get(0) + words[1] + results.get(1);
                story.setText(storyFinal);

                inputLayout.setVisibility(View.INVISIBLE);
                storyLayout.setVisibility(View.VISIBLE);
            }
        });





        background = (RelativeLayout) findViewById(R.id.background);
        Random randomGenerator = new Random();
        int randomNumber = randomGenerator.nextInt(getResources().getStringArray(R.array.colors).length);
        String color = getResources().getStringArray(R.array.colors)[randomNumber];
        background.setBackgroundColor(Color.parseColor(color));
    }
}
