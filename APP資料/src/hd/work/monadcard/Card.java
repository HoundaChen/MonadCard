package hd.work.monadcard;




import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.os.Handler;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

public class Card extends Activity {
	private static final int ACTIVITY_CREATE = 0;
	private Button set1,frb,nexb;
	private TextView tv1,tv2,tv3,tv4,tv5;
	private DbAdapter mDbHelper;
	private RelativeLayout rl;
	private CheckBox cb1;
	int i,gb,qq ;
	 private Handler mHandlerTime = new Handler();
	private void setin(){
		set1 = (Button)findViewById(R.id.menubutton);
		frb = (Button)findViewById(R.id.upbutton);
		nexb = (Button)findViewById(R.id.downbutton);
		tv1 =(TextView)findViewById(R.id.wrod);
		tv2 =(TextView)findViewById(R.id.chiness);
		tv3=(TextView)findViewById(R.id.count);
		tv4=(TextView)findViewById(R.id.text);
		tv5=(TextView)findViewById(R.id.ctext);
		
		 mDbHelper = new DbAdapter(this);//實體化DbAdapter.java中的DbAdapter.Class
	     mDbHelper.open();//開起DbAdapter.Class
	     rl =  (RelativeLayout)findViewById(R.id.rootwindow1);
	     cb1=(CheckBox)findViewById(R.id.autoplay1);
	     Bundle bundle = this.getIntent().getExtras();
		  qq=bundle.getInt("c_table");
		
	}
	/**
	 * @param args
	 */
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.showpage);
		setin();
		
	     rl.setOnTouchListener(new setTouchwindow());
		 switch(qq){
		 case 1:
			 populateFields(i=1);
			 break;
		 case 2:
			 Cursor gg = mDbHelper.Alluser();
			  startManagingCursor(gg);
			 int  testgb = gg.getCount();
			 if( testgb > 1){
				 populateFields(i=1);
			 }else{
				  Toast.makeText(getApplicationContext(), "無任何單字請新增", Toast.LENGTH_SHORT).show();
				 Intent i = new Intent(this, wordeditpg.class);
			        startActivityForResult(i, ACTIVITY_CREATE);
					finish();  
			 }
			 
		 }
	     
	  	
     nexb.setOnClickListener(new Button.OnClickListener(){
    	 public void onClick(View v) {
			// TODO 自動產生的方法 Stub
    			nexewd(i);
		}
    	 
     });
     set1.setOnClickListener(new Button.OnClickListener(){

 		@Override
 		public void onClick(View v) {
 			// TODO 自動產生的方法 Stub
 			  Intent intent = new Intent();
 				intent.setClass(Card.this,Setpage.class);
 				startActivity(intent);
 				
 				finish(); 
 		}
     	 
      });
    frb.setOnClickListener(new Button.OnClickListener(){

		@Override
		public void onClick(View v) {
			// TODO 自動產生的方法 Stub
		
			backwd(i); 
		}
    	
    });
cb1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
		
		@Override
		public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
			// TODO 自動產生的方法 Stub
			if(isChecked){
				Toast.makeText(Card.this,"開啟",Toast.LENGTH_SHORT).show();
				mHandlerTime.postDelayed(timerRun, 5000);
			}else{
				Toast.makeText(Card.this,"關閉",Toast.LENGTH_SHORT).show();
				mHandlerTime.removeCallbacks(timerRun);
			}
		}
	});
	}
	
	  /* (non-Javadoc)
	 * @see android.app.Activity#onCreateOptionsMenu(android.view.Menu)
	 */
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// TODO Auto-generated method stub
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.m_option, menu);
		return super.onCreateOptionsMenu(menu);
	}
	
	/* (non-Javadoc)
	 * @see android.app.Activity#onOptionsItemSelected(android.view.MenuItem)
	 */
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
		int id_menu = item.getItemId();
		switch(id_menu){
		case R.id.menu_search:
			Intent intent = new Intent();
			intent.setClass(Card.this,wordeditpg.class);
			startActivity(intent);
			
			break;
		case R.id.del:
			finish();  
			break;
		}
		return super.onOptionsItemSelected(item);
	}
	
	public int nexewd(int z){
		  int sum;
		  returecount();
		  if( z == gb){
				Toast.makeText(Card.this,"最後一個單字了!!!",Toast.LENGTH_SHORT).show();
			}else{
		      sum = z+1;
		      populateFields(sum);
		      	   i = sum;   }
		            return i;
		    
		  }
		  public int backwd(int b){
			   int sum;
			        sum = b-1;
			        if(sum <= 0){
			        	Toast.makeText(Card.this,"是第一個單字!!!",Toast.LENGTH_SHORT).show();
			        }else{
			      populateFields(sum);
			      i = sum; 	}      
			            return i;
			    
			  }
		  public int returecount(){
			  switch(qq){
			  case 1:
				  Cursor gg = mDbHelper.monad1allid();
				  startManagingCursor(gg);
				  gb = gg.getCount();
				 
				  break;
			  case 2:
				  Cursor gg1 = mDbHelper.userAllid();
				  startManagingCursor(gg1);
				  gb = gg1.getCount();
				  break;
			  }
			  return gb;
		  }
		  private void populateFields(int x) {
			  if(qq==1){
			  
				  long bbq = x;
		            Cursor note = mDbHelper.monad1note(bbq);
		            startManagingCursor(note);
		           tv1.setText(note.getString(
		                    note.getColumnIndexOrThrow(DbAdapter.KEY_wrod)));
		            tv2.setText(note.getString(
		                    note.getColumnIndexOrThrow(DbAdapter.KEY_chiness)));
		            tv3.setText(String.valueOf(x));
		            if((note.getString(note.getColumnIndexOrThrow(DbAdapter.KEY_text)))!=null){
		            tv4.setText(note.getString(
		                    note.getColumnIndexOrThrow(DbAdapter.KEY_text)));
		            }else{
		            	tv4.setText("");
		            }
		            if((note.getString(note.getColumnIndexOrThrow(DbAdapter.KEY_ctext)))!=null){
		            tv5.setText(note.getString(
		                    note.getColumnIndexOrThrow(DbAdapter.KEY_ctext)));
		            }else{
		            	tv5.setText("");
		            }
			  }else{
				  
			 
				 long bbqc = x;
		            Cursor note1 = mDbHelper.user_word(bbqc);
		            startManagingCursor(note1);
		           tv1.setText(note1.getString(
		                    note1.getColumnIndexOrThrow(DbAdapter.KEY_wrod)));
		            tv2.setText(note1.getString(
		                    note1.getColumnIndexOrThrow(DbAdapter.KEY_chiness)));
		            tv3.setText(String.valueOf(x));
		            if((note1.getString(note1.getColumnIndexOrThrow(DbAdapter.KEY_text)))!=null){
			            tv4.setText(note1.getString(
			                    note1.getColumnIndexOrThrow(DbAdapter.KEY_text)));
			            }else{
			            	tv4.setText("Null");
			            }
			            if((note1.getString(note1.getColumnIndexOrThrow(DbAdapter.KEY_ctext)))!=null){
			            tv5.setText(note1.getString(
			                    note1.getColumnIndexOrThrow(DbAdapter.KEY_ctext)));
			            }else{
			            	tv5.setText("Null");
			            }
				  
			  }
			    
		    }
		  private final Runnable timerRun = new Runnable()
		  {
		    public void run()
		    {
		    	nexewd(i);
		      mHandlerTime.postDelayed(this, 5000);
		      // 若要取消可以寫一個判斷在這決定是否啟動下一次即可
		     
		    }
		  };
		  public class setTouchwindow implements OnTouchListener{
			  private VelocityTracker  m_tracker;
			  public boolean onTouch(View v, MotionEvent event) {
				  int type = event.getAction();
					float pressure = event.getPressure();
					if (type == MotionEvent.ACTION_UP){
						
					   	m_tracker.addMovement(event);
						m_tracker.computeCurrentVelocity(1);
						
						if (Math.abs(m_tracker.getXVelocity()) > Math.abs(m_tracker.getYVelocity())) {
							if(m_tracker.getXVelocity()>0){
								nexewd(i);
								//velocityText.setText(MainActivity.this.getString(R.string.velocity_string)+"水平方向 => 向右 :"+m_tracker.getXVelocity());
							}else{
								backwd(i); 
								//velocityText.setText(MainActivity.this.getString(R.string.velocity_string)+"水平方向 => 向左 :"+m_tracker.getXVelocity());
							}
						
						}else if (Math.abs(m_tracker.getXVelocity()) < Math.abs(m_tracker.getYVelocity())) {
							if(m_tracker.getYVelocity()<0){
								//velocityText.setText(MainActivity.this.getString(R.string.velocity_string)+"垂直方向 => 向上 :"+m_tracker.getYVelocity());
							}else{
								//velocityText.setText(MainActivity.this.getString(R.string.velocity_string)+"垂直方向 => 向下 :"+m_tracker.getYVelocity());
							}
							//velocityText.setText(MainActivity.this.getString(R.string.velocity_string)+"垂直方向:"+m_tracker.getYVelocity());
						}
						m_tracker.recycle();
						m_tracker = null;
						//return false;
					}
					if (type == MotionEvent.ACTION_MOVE){
				
						m_tracker.addMovement(event);
					}
					if (type == MotionEvent.ACTION_DOWN){
					
						m_tracker = VelocityTracker.obtain();
					}
					return true;
			  }
		  }
		}

