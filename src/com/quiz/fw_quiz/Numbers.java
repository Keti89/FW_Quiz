package com.quiz.fw_quiz;

import java.util.Locale;

import android.app.Activity;
import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.SoundPool;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class Numbers extends Activity implements OnClickListener{

	TextView tv;
	Button  button1, button2, button3, button4;
	
	String questions[] = {"Where are there four things?", "Where are there nine things?",
						  "Where is the number three?", "Where is the number seven?", 
						  "Which the number is the largest",
						  "What is 3+5?","What is 4+3?", 
						  "What is 5-4?", "What is 9-3?"};
	String ans[] ={"R.drawable.num4", "R.drawable.num9", 
				   "R.drawable.three", "R.drawable.seven",
				   "R.drawable.eight",
				   "R.drawable.eight", "R.drawable.seven",
				   "R.drawable.one","R.drawable.six"};
	
	String opt[] = {"R.drawable.num2",     "R.drawable.num4",   "R.drawable.num6",  "R.drawable.num8",
					"R.drawable.num5",     "R.drawable.num7",   "R.drawable.num9",  "R.drawable.num10",
					"R.drawable.four",  "R.drawable.ten",    "R.drawable.three", "R.drawable.five",
					"R.drawable.six",    "R.drawable.seven",   "R.drawable.two", "R.drawable.ten", 
					"R.drawable.four",    "R.drawable.seven",   "R.drawable.two", "R.drawable.eight",
					"R.drawable.eight",   "R.drawable.two",   "R.drawable.nine", "R.drawable.six",
					"R.drawable.four",    "R.drawable.seven",   "R.drawable.two", "R.drawable.ten", 
					"R.drawable.one",     "R.drawable.five",   "R.drawable.three",  "R.drawable.four",
					"R.drawable.six",    "R.drawable.seven",   "R.drawable.two", "R.drawable.ten"
					};
	
	Integer opp[] ={R.drawable.num2,     R.drawable.num4,   R.drawable.num6,  R.drawable.num8,
					R.drawable.num5,     R.drawable.num7,   R.drawable.num9,  R.drawable.num10,
					R.drawable.four,  R.drawable.ten,    R.drawable.three, R.drawable.five,
					R.drawable.six,    R.drawable.seven,   R.drawable.two, R.drawable.ten, 
					R.drawable.four,    R.drawable.seven,   R.drawable.two, R.drawable.eight,
					R.drawable.eight,   R.drawable.two,   R.drawable.nine, R.drawable.six,
					R.drawable.four,    R.drawable.seven,   R.drawable.two, R.drawable.ten, 
					R.drawable.one,     R.drawable.five,   R.drawable.three,  R.drawable.four,
					R.drawable.six,    R.drawable.seven,   R.drawable.two, R.drawable.ten
	};
	
	int flag = 0;
	public static int correct, wrong;
	TextToSpeech ttsobject;
	int result;
	String text;

	SoundPool sp;
	int no=0;
	int yes=0;
	MediaPlayer mp;
	
	@SuppressWarnings("deprecation")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.quiz2);
		
		tv = (TextView)findViewById(R.id.tvque);
		
		button1 = (Button)findViewById(R.id.button1);
        button2 = (Button)findViewById(R.id.button2);
        button3 = (Button)findViewById(R.id.button3);
        button4 = (Button)findViewById(R.id.button4);
        
        button1.setOnClickListener(this);
        button2.setOnClickListener(this);
        button3.setOnClickListener(this);
        button4.setOnClickListener(this);
        
		tv.setText(questions[flag]);
		button1.setText(opt[0]);
		button2.setText(opt[1]);
		button3.setText(opt[2]);
		button4.setText(opt[3]);
		
		button1.setTextColor(getResources().getColor(android.R.color.transparent));
		button2.setTextColor(getResources().getColor(android.R.color.transparent));
		button3.setTextColor(getResources().getColor(android.R.color.transparent));
		button4.setTextColor(getResources().getColor(android.R.color.transparent));
		
		
		button1.setBackgroundDrawable(getResources().getDrawable(opp[0]));
		button2.setBackgroundDrawable(getResources().getDrawable(opp[1]));
		button3.setBackgroundDrawable(getResources().getDrawable(opp[2]));
	    button4.setBackgroundDrawable(getResources().getDrawable(opp[3]));
		
		sp = new SoundPool(5, AudioManager.STREAM_MUSIC, 0);
		no = sp.load(this, R.raw.no, 1);
		mp = MediaPlayer.create(this, R.raw.no);
		yes = sp.load(this, R.raw.yeaahh, 1);
		mp = MediaPlayer.create(this, R.raw.yeaahh);
		 
		ttsobject = new TextToSpeech(Numbers.this, new TextToSpeech.OnInitListener() {
			@Override
			public void onInit(int status) {
				// TODO Auto-generated method stub
				if(status==TextToSpeech.SUCCESS){
					result = ttsobject.setLanguage(Locale.ENGLISH);	
				}else{
					Toast.makeText(getApplicationContext(), "Feature not Supported in Your Device", Toast.LENGTH_SHORT).show();
				}
			}
				
		});
		 
	}
	@SuppressWarnings("deprecation")
	public void onClick(View v) {
			 
		Button clickedButton = (Button) v;
		String ansText= clickedButton.getText().toString();
		if(ansText.equalsIgnoreCase(ans[flag])){
			if(yes != 0)
			sp.play(yes, 1, 1, 0, 0, 1);
			correct++;
			flag++;
			if(flag<questions.length){
				tv.setText(questions[flag]);
				button1.setText(opt[flag*4]);
				button2.setText(opt[flag*4+1]);
				button3.setText(opt[flag*4+2]);
			    button4.setText(opt[flag*4+3]);
						
				button1.setBackgroundDrawable(getResources().getDrawable(opp[flag*4]));
				button2.setBackgroundDrawable(getResources().getDrawable(opp[flag*4+1]));
				button3.setBackgroundDrawable(getResources().getDrawable(opp[flag*4+2]));
				button4.setBackgroundDrawable(getResources().getDrawable(opp[flag*4+3]));	
				}else{
					Intent in = new Intent(getApplicationContext(), Result4.class);
					startActivity(in);
				}
			}else{
			if(no != 0)
			sp.play(no, 1, 1, 0, 0, 1);
			wrong++;
		}		
	}
					
	public void doSomehting(View v){
		if(result == TextToSpeech.LANG_NOT_SUPPORTED||result== TextToSpeech.LANG_MISSING_DATA){
			Toast.makeText(getApplicationContext(), "Feature not Supported in Your Device", Toast.LENGTH_SHORT).show();
		}else{
			text = tv.getText().toString();
			ttsobject.speak(text, TextToSpeech.QUEUE_FLUSH, null);
		}
	 }	
}
