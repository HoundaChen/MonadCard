package hd.work.monadcard;

import java.util.Timer;
import java.util.TimerTask;

import android.os.Bundle;
import android.os.Handler;
import android.app.Activity;
import android.content.Intent;


public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
        timer(400);
	
	}
        public void timer(int counter){ 
            new Handler().postDelayed(new Runnable(){
                @Override
                // counter is in milliseconds.
                public void run() {             
                    Intent mainIntent = new Intent(MainActivity.this,Setpage.class);
                    startActivity(mainIntent);
                    finish();  

                }
            }, counter);
        }
}
