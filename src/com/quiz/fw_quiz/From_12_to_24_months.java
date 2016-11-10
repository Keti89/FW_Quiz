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

public class From_12_to_24_months extends Activity implements OnClickListener{

	TextView tv;
	Button  button1, button2, button3;
	
	String questions[] = {"Where is the car?", "Where are the socks?" , "Where is the cat?", 
				          "Where is the television?", "Where is the ball?", "Where is the apple?"
				          , "Where is the tomato?", "Where is the rabbit?", "Where is the door?"};
	
	String ans[] ={"R.drawable.car", "R.drawable.socks", "R.drawable.cat", "R.drawable.tv", 
				   "R.drawable.ball", "R.drawable.apple", "R.drawable.tomato", "R.drawable.rabbit",
				   "R.drawable.door"};
	
	String opt[] = {"R.drawable.table",  "R.drawable.car",    "R.drawable.chairs",
					"R.drawable.dog",    "R.drawable.duck",   "R.drawable.socks", 
					"R.drawable.bear",   "R.drawable.baby",   "R.drawable.cat", 
					"R.drawable.tv",     "R.drawable.bike",   "R.drawable.bus",
					"R.drawable.banana", "R.drawable.ball",   "R.drawable.pear", 
					"R.drawable.apple",  "R.drawable.bottle", "R.drawable.pacifier",
					"R.drawable.carrot",  "R.drawable.pepper", "R.drawable.tomato",
					"R.drawable.monkey",  "R.drawable.rabbit", "R.drawable.elephant",
					"R.drawable.door",  "R.drawable.chairs", "R.drawable.table"};
	
	Integer opp[] ={R.drawable.table,  R.drawable.car,    R.drawable.chairs, 
					R.drawable.dog,    R.drawable.duck,   R.drawable.socks, 
					R.drawable.bear,   R.drawable.baby,   R.drawable.cat,
					R.drawable.tv,     R.drawable.bike,   R.drawable.bus,
					R.drawable.banana, R.drawable.ball,   R.drawable.pear,
					R.drawable.apple,  R.drawable.bottle, R.drawable.pacifier,
					R.drawable.carrot,  R.drawable.pepper, R.drawable.tomato,
					R.drawable.monkey,  R.drawable.rabbit, R.drawable.elephant,
					R.drawable.door,  R.drawable.chairs, R.drawable.table};
	
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
		setContentView(R.layout.quiz1);
		
		tv = (TextView)findViewById(R.id.tvque);
		
		button1 = (Button)findViewById(R.id.button1);
        button2 = (Button)findViewById(R.id.button2);
        button3 = (Button)findViewById(R.id.button3);
        //button4 = (Button)findViewById(R.id.button4);
        
        button1.setOnClickListener(this);
        button2.setOnClickListener(this);
        button3.setOnClickListener(this);
        //button4.setOnClickListener(this);
        
		tv.setText(questions[flag]);
		button1.setText(opt[0]);
		button2.setText(opt[1]);
		button3.setText(opt[2]);
		//button4.setText(opt[3]);
		
		button1.setTextColor(getResources().getColor(android.R.color.transparent));
		button2.setTextColor(getResources().getColor(android.R.color.transparent));
		button3.setTextColor(getResources().getColor(android.R.color.transparent));
		//button4.setTextColor(getResources().getColor(android.R.color.transparent));
		
		
		button1.setBackgroundDrawable(getResources().getDrawable(opp[0]));
		button2.setBackgroundDrawable(getResources().getDrawable(opp[1]));
		button3.setBackgroundDrawable(getResources().getDrawable(opp[2]));
	    //button4.setBackgroundDrawable(getResources().getDrawable(opp[3]));
		
		sp = new SoundPool(5, AudioManager.STREAM_MUSIC, 0);
		no = sp.load(this, R.raw.no, 1);
		mp = MediaPlayer.create(this, R.raw.no);
		yes = sp.load(this, R.raw.yeaahh, 1);
		mp = MediaPlayer.create(this, R.raw.yeaahh);
		 
		ttsobject = new TextToSpeech(From_12_to_24_months.this, new TextToSpeech.OnInitListener() {
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
				button1.setText(opt[flag*3]);
				button2.setText(opt[flag*3+1]);
				button3.setText(opt[flag*3+2]);
			    //button4.setText(opt[flag*4+3]);
						
				button1.setBackgroundDrawable(getResources().getDrawable(opp[flag*3]));
				button2.setBackgroundDrawable(getResources().getDrawable(opp[flag*3+1]));
				button3.setBackgroundDrawable(getResources().getDrawable(opp[flag*3+2]));
				//button4.setBackgroundDrawable(getResources().getDrawable(opp[flag*4+3]));	
				}else{
					Intent in = new Intent(getApplicationContext(), Result.class);
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
