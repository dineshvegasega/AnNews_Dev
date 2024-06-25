package com.vegasega.amnews.ui;

import android.content.Intent;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.view.MotionEvent;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.stfalcon.imageviewer.StfalconImageViewer;
import com.vegasega.amnews.R;

import java.util.ArrayList;
import java.util.Locale;

public class AAA extends AppCompatActivity {


    private static final int MY_DATA_CHECK_CODE = 12;

    //    ViewPager viewPager;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.abc);



//        Intent checkIntent = new Intent();
//        checkIntent.setAction(TextToSpeech.Engine.ACTION_CHECK_TTS_DATA);
//        startActivityForResult(checkIntent, MY_DATA_CHECK_CODE);

//        horizontalScrollView.setOnTouchListener(new View.OnTouchListener() {
//                                                    float rawX;
//                                                    int mTouchSlop =  ViewConfiguration.get(getActivity()).getScaledTouchSlop();
//
//                                                    @Override
//                                                    public boolean onTouch(View v, MotionEvent event) {
//                                                        switch (event.getActionMasked()) {
//                                                            case MotionEvent.ACTION_DOWN:
//                                                                v.getParent().requestDisallowInterceptTouchEvent(true);
//                                                                rawX = event.getRawX();
//                                                                break;
//                                                            case MotionEvent.ACTION_CANCEL:
//                                                            case MotionEvent.ACTION_UP:
//                                                                v.getParent().requestDisallowInterceptTouchEvent(false);
//                                                                rawX = 0f;
//                                                                break;
//                                                            case MotionEvent.ACTION_MOVE:
//                                                                if (Math.abs(rawX - event.getRawX()) > mTouchSlop)
//                                                                    v.getParent().requestDisallowInterceptTouchEvent(true);
//                                                                break;
//                                                        }
//                                                        return false;
//                                                    }

    }


//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//        if (requestCode == MY_DATA_CHECK_CODE) {
//            if (resultCode == TextToSpeech.Engine.CHECK_VOICE_DATA_PASS) {
//                // success, create the TTS instance
//                initTTS();
//            } else {
//                // missing data, install it
//                Intent installIntent = new Intent();
//                installIntent.setAction(
//                        TextToSpeech.Engine.ACTION_INSTALL_TTS_DATA);
//                ArrayList<String> languages = new ArrayList<String>();
//                languages.add("hin-IND"); // hin - hindi, IND - INDIA
//                installIntent.putStringArrayListExtra(
//                        TextToSpeech.Engine.EXTRA_CHECK_VOICE_DATA_FOR, languages);
//                startActivity(installIntent);
//            }
//        }
//    }

    private TextToSpeech mTts;
    public void initTTS() {
        //mTts = new TextToSpeech(this, this);
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
                            Toast.makeText(AAA.this, "हाँ, मैं हिंदी बोल सकता हूँ", Toast.LENGTH_LONG).show();
                            mTts.speak("हाँ, मैं हिंदी बोल सकता हूँ", TextToSpeech.QUEUE_FLUSH, null,null);

                            break;
                    }
                } else {
                    Toast.makeText(AAA.this, "TTS initialization Failed", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    ViewPager viewPager = new ViewPager(this){
        @Override
        public boolean onInterceptHoverEvent(MotionEvent event) {
            return super.onInterceptHoverEvent(event);
        }
    };
}
