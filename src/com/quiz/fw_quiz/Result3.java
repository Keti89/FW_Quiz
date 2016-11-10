package com.quiz.fw_quiz;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class Result3 extends Activity {
	
	TextView tv;
	Button btnRestart;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.result);
		tv = (TextView)findViewById(R.id.tvres);
		btnRestart= (Button)findViewById(R.id.btnRestart);
		
		int marks = Colors.correct + Colors.wrong;
		StringBuffer sb = new StringBuffer();
		sb.append("Correct ANS: " + Colors.correct);
		sb.append("\n Wrong Ans: "+ Colors.wrong);
		sb.append("\n Final Score: "+ marks);
		tv.setText(sb);
		
		Colors.correct=0;
		Colors.wrong=0;
		btnRestart.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent in= new Intent(getApplicationContext(), Menu.class);
				startActivity(in);
			}
		});
	}
	
	
}

