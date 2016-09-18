package com.elizabethdetiene.hackmit2016;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class FeedbackActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback);

        //Capture intent from recording screen activity (?)
        Intent intent = getIntent();

        //Get speech text and info on use of filler words
        String speech = intent.getStringExtra("speech");
        ArrayList<List> fillerWordIndices = (ArrayList<List>) intent.getSerializableExtra("fillerWordIndices");

        TextView speechText = (TextView) findViewById(R.id.speechText);

        //Make spannable string to add spans for highlighting filler words
        Spannable speechWithSpans = new SpannableString(speech);

        //Loop through all filler words and highlight them
        for (int i = 0; i < fillerWordIndices.size(); i++) {
            int start = (int) fillerWordIndices.get(i).get(0);
            int end = (int) fillerWordIndices.get(i).get(1);
            speechWithSpans.setSpan(new ForegroundColorSpan(Color.RED), start, end, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        }

        //Populate textView with highlighted speech
        speechText.setText(speechWithSpans);
    }
}
