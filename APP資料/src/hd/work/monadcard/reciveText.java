package hd.work.monadcard;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class reciveText extends Activity{
	private static final int return_MENU = Menu.FIRST;
	private Button bt1,bt2,bt3,bt4;
	private TextView tv1;
	private DbAdapter mDbHelper;
	int j,count;
	int [] re4num = new int[4];//存入輸出4個按鈕隨機位置
	String correcttxt;//存入正確答案值
	ArrayList<String> txtlist = new ArrayList<String>();//存入4個答案字串
	ArrayList<Integer>list100 = new ArrayList<Integer>();//存入100題序列
	private static final String tag = "text";
	void srandom(){
		//由2000題取100題
		 Cursor gg = mDbHelper.monad1allid();
		  startManagingCursor(gg);
		  int gb = gg.getCount();
		RandomText rt = new RandomText();
		int [] a = rt.Rdnum(gb, 100);
		  for(int q=0;q<100;q++){
			  list100.add(a[q]);
			//  Log.i(tag,String.valueOf(a[q]) );
			// mDbHelper.updateradom(q, a[q]);
		  }
		  gg.close();
			 
	}
	/**
	 * @param args
	 */
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.testpage);
		bt1 = (Button)findViewById(R.id.testbutton1);
		bt2 = (Button)findViewById(R.id.testbutton2);
		bt3 = (Button)findViewById(R.id.testbutton3);
		bt4 = (Button)findViewById(R.id.testbutton4);
		tv1 = (TextView)findViewById(R.id.testwrod);
		mDbHelper = new DbAdapter(this);//實體化DbAdapter.java中的DbAdapter.Class
	     mDbHelper.open();//開起DbAdapter.Class
		srandom();
		if(count !=1){
			populateFields(list100.get(count));
		}else{
			count = 1;
			populateFields(list100.get(count));
		}
	
	  
	   
	   
	   bt1.setOnClickListener(new Button.OnClickListener(){
		   
		@Override
		public void onClick(View arg0) {
			// TODO Auto-generated method stub
			checktxt(txtlist.get(re4num[0]));
		}
		   
	   });
	   bt2.setOnClickListener(new Button.OnClickListener(){
		   
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				checktxt(txtlist.get(re4num[1]));
			}
			   
		   });
	   bt3.setOnClickListener(new Button.OnClickListener(){
		   
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				checktxt(txtlist.get(re4num[2]));
			}
			   
		   });
	   bt4.setOnClickListener(new Button.OnClickListener(){
		   
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				checktxt(txtlist.get(re4num[3]));
			}
			   
		   });
	   
	 

	}
	 private void populateFields(int x) {
		  //取資料輸出
		      long qq = x;
	            Cursor note = mDbHelper.monad1note(qq);
	            startManagingCursor(note);
	           tv1.setText(note.getString(
	                    note.getColumnIndexOrThrow(DbAdapter.KEY_wrod)));
	             
	           txtlist.add(0,note.getString(
	                    note.getColumnIndexOrThrow(DbAdapter.KEY_chiness)));
	           correcttxt = txtlist.get(0);
	          
	           note.close();
	            RandomText rt = new RandomText();
	           int [] b = rt.Rdnum(500, 3);
	           long [] qb = new long[b.length];
	          
	           for(int k =0 ; k< 3 ; k++){
	        	 qb[k] = b[k];
	        	 	  Cursor note1 = mDbHelper.monad1note(qb[k]);
		            startManagingCursor(note1);
		                
		          txtlist.add( (k+1),note1.getString(
		                    note.getColumnIndexOrThrow(DbAdapter.KEY_chiness)));
		            note1.close();
		            	        
		            	           }
	           re4num = rt.Rdnum(4, 4);
	           bt1.setText(txtlist.get(re4num[0]));
	       	   bt2.setText(txtlist.get(re4num[1]));
	       	   bt3.setText(txtlist.get(re4num[2]));
	       	   bt4.setText(txtlist.get(re4num[3]));
	      
	         
	    }
	 private void checktxt(String ntxt){
		 if(ntxt.equals(correcttxt)){
			 count++;
			 populateFields(list100.get(count));
			 		 }
		  
	 }
	  public boolean onCreateOptionsMenu(Menu menu) {
	        menu.add(0, return_MENU, 0, R.string.m_menu);
	        return true;
	    }
	  public boolean onMenuItemSelected(int featureId, MenuItem item) {
	        switch(item.getItemId()) {
	            case return_MENU:
	            	Intent intent = new Intent();
					intent.setClass(reciveText.this,Setpage.class);
					startActivity(intent);
					finish();  
	               
	                return true;
	     
	        }

	        return super.onMenuItemSelected(featureId, item);
	    }
	}
