package com.quiz.fw_quiz;



import android.support.v7.app.ActionBarActivity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.Menu;
import android.view.MenuItem;


public class FirstPages extends ActionBarActivity {
	
	MediaPlayer ourSong; 
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.firstpages);
        
        ourSong =MediaPlayer.create(FirstPages.this, R.raw.oldmac);
        
        SharedPreferences getPrefs = PreferenceManager.getDefaultSharedPreferences(getBaseContext());
		boolean music = getPrefs.getBoolean("checkbox", true);
		if(music==true)
			ourSong.start();
        
		Thread timer = new Thread(){
			public void run(){
				try{
					sleep(10000);
				} catch (InterruptedException e){
					e.printStackTrace();
				}finally{
					Intent i = new Intent("com.quiz.fw_quiz.MENU");
					startActivity(i);
					
				}
			}
		};
		timer.start();
    }
    
    @Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		ourSong.release();
		finish();
	}

}
