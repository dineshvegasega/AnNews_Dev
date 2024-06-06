package com.vegasega.amnews.ui;

import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.vegasega.amnews.R;

import java.util.Locale;
import android.content.Intent;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.view.MotionEvent;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.vegasega.amnews.R;

import java.util.ArrayList;
import java.util.Locale;


public class Demo extends AppCompatActivity {
    private static final int MY_DATA_CHECK_CODE = 12;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home);

        Intent installTTSIntent = new Intent();
        installTTSIntent.setAction(TextToSpeech.Engine.ACTION_INSTALL_TTS_DATA);
        ArrayList<String> languages = new ArrayList<String>();
        languages.add("hi-IN"); // hin - hindi, IND - INDIA
        installTTSIntent.putStringArrayListExtra(
                TextToSpeech.Engine.ACTION_CHECK_TTS_DATA, languages);
        startActivity(installTTSIntent);

//        Intent checkIntent = new Intent();
//        checkIntent.setAction(TextToSpeech.Engine.ACTION_CHECK_TTS_DATA);
//        startActivityForResult(checkIntent, MY_DATA_CHECK_CODE);

        initTTS();
    }


        @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == MY_DATA_CHECK_CODE) {
            if (resultCode == TextToSpeech.Engine.CHECK_VOICE_DATA_PASS) {
                // success, create the TTS instance
                initTTS();
            } else {
                // missing data, install it
                Intent installIntent = new Intent();
                installIntent.setAction(
                        TextToSpeech.Engine.ACTION_INSTALL_TTS_DATA);
                ArrayList<String> languages = new ArrayList<String>();
                languages.add("hi-IN"); // hin - hindi, IND - INDIA
                installIntent.putStringArrayListExtra(
                        TextToSpeech.Engine.EXTRA_CHECK_VOICE_DATA_FOR, languages);
                startActivity(installIntent);
            }
        }
    }

    private TextToSpeech mTts;
    public void initTTS() {
//        mTts = new TextToSpeech(this, this);
        mTts = new TextToSpeech(getApplicationContext(), new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if(status != TextToSpeech.ERROR) {

                    int isLanguageAvailable = mTts.isLanguageAvailable(new Locale("hi_IN"));
                    switch (isLanguageAvailable) {
                        case TextToSpeech.LANG_AVAILABLE:
                            //t1.setLanguage(Locale.UK);
                            mTts.setLanguage(new Locale("hin"));
                            mTts.setPitch(1.2f);
                            mTts.setSpeechRate(0.8f);
                            Toast.makeText(Demo.this, "हाँ, मैं हिंदी बोल सकता हूँ", Toast.LENGTH_LONG).show();
                            mTts.speak("हाँ, मैं हिंदी बोल सकता हूँ", TextToSpeech.QUEUE_FLUSH, null,null);

                            break;
                    }
                } else {
                    Toast.makeText(Demo.this, "TTS initialization Failed", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

}
