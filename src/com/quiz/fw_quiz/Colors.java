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

public class Colors extends Activity implements OnClickListener{

	TextView tv;
	Button  button1, button2, button3, button4;
	
	String questions[] = {"What fruit is red?", "Where is the brown car?", "What animal is black?", 
			"What vegetable is orange?", "Where is the purple flower?", "What fruit is yellow?", 
			"What animal is gray?", "Where is the green frog?" };
	String ans[] ={"R.drawable.strawberry", "R.drawable.brown", "R.drawable.blacksheep", "R.drawable.carrot",
			"R.drawable.flowerpurple", "R.drawable.lemon", "R.drawable.mouse", "R.drawable.frog"};
	
	String opt[] = {"R.drawable.pear",  "R.drawable.strawberry",    "R.drawable.orange", "R.drawable.banana",
					"R.drawable.car",    "R.drawable.green",   "R.drawable.brown", "R.drawable.orangecar", 
					"R.drawable.blacksheep",   "R.drawable.dog",   "R.drawable.cat", "R.drawable.duck",
					"R.drawable.tomato",     "R.drawable.pepper",   "R.drawable.eggplant",  "R.drawable.carrot",
					"R.drawable.floweror",     "R.drawable.floweryellou",   "R.drawable.flowerpurple",  "R.drawable.roses",
					"R.drawable.lemon",     "R.drawable.orange",   "R.drawable.pear",  "R.drawable.apple",
					"R.drawable.mouse",     "R.drawable.cow",   "R.drawable.monkey",  "R.drawable.elefant",
					"R.drawable.ff",     "R.drawable.frog",   "R.drawable.frogblue",  "R.drawable.frogred"
					};
	
	Integer opp[] ={R.drawable.pear,  R.drawable.strawberry,   R.drawable.orange, R.drawable.banana, 
					R.drawable.car,    R.drawable.green,  R.drawable.brown, R.drawable.orangecar, 
					R.drawable.blacksheep,   R.drawable.dog,   R.drawable.cat, R.drawable.duck,
					R.drawable.tomato,     R.drawable.pepper,   R.drawable.eggplant,  R.drawable.carrot,
					R.drawable.floweror, R.drawable.floweryellou,   R.drawable.flowerpurple,  R.drawable.roses,
					R.drawable.lemon,     R.drawable.orange,   R.drawable.pear,  R.drawable.apple,
					R.drawable.mouse,     R.drawable.cow,   R.drawable.monkey,  R.drawable.dog,
					R.drawable.ff,     R.drawable.frog,   R.drawable.frogblue,  R.drawable.frogred};
	
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
		 
		ttsobject = new TextToSpeech(Colors.this, new TextToSpeech.OnInitListener() {
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
					Intent in = new Intent(getApplicationContext(), Result3.class);
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
