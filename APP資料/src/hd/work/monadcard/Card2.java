package hd.work.monadcard;


import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.os.Handler;
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

public class Card2 extends Activity {
	private static final int ACTIVITY_CREATE = 0;
	private Button set1,frb,nexb;
	private TextView tv1,tv2,tv3,tv4,tv5;
	private DbAdapter mDbHelper;
	private RelativeLayout rl;
	private CheckBox cb1;
	int i , j ,count,qq;
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
		 mDbHelper = new DbAdapter(this);//�����DbAdapter.java����DbAdapter.Class
	     mDbHelper.open();//�}�_DbAdapter.Class
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
	    if(qq == 2){
			  Cursor gg = mDbHelper.Alluser();
			  startManagingCursor(gg);
			 int  testgb = gg.getCount();
			 if( testgb >1){
				 
			 }else{
				 Toast.makeText(getApplicationContext(), "�L�����r�зs�W", Toast.LENGTH_SHORT).show();
				 Intent i = new Intent(this, wordeditpg.class);
			        startActivityForResult(i, ACTIVITY_CREATE);
					finish();  
			 }
		  }
	 
		  aradom();
		  loadrandom(i=1);
	      populateFields(j);
	      countm(count=1);
     nexb.setOnClickListener(new Button.OnClickListener(){
    	 public void onClick(View v) {
			// TODO �۰ʲ��ͪ���k Stub
    		// redown();
    		 nexewd(i);
		}
    	 
     });
     set1.setOnClickListener(new Button.OnClickListener(){

		@Override
		public void onClick(View v) {
			// TODO �۰ʲ��ͪ���k Stub
			  Intent intent = new Intent();
				intent.setClass(Card2.this,Setpage.class);
				startActivity(intent);
			
				finish(); 
		}
    	 
     });
   
    frb.setOnClickListener(new Button.OnClickListener(){

		@Override
		public void onClick(View v) {
			// TODO �۰ʲ��ͪ���k Stub
		
			backwd(i); 
		
			
		}
    	
    });
cb1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
		
		@Override
		public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
			// TODO �۰ʲ��ͪ���k Stub
			if(isChecked){
				Toast.makeText(Card2.this,"�}��",Toast.LENGTH_SHORT).show();
				mHandlerTime.postDelayed(timerRun, 5000);
			}else{
				Toast.makeText(Card2.this,"����",Toast.LENGTH_SHORT).show();
				mHandlerTime.removeCallbacks(timerRun);
			}
		}
	});
	}
	public int countm(int cot){
		
		 tv3.setText(String.valueOf(cot));
		 count = cot;
		return count;
	}
	  public int nexewd(int z){
		  int sum;
		  countm(count+1);
		  sum = z+1;
		 
			      if(sum>99){
		    	  
		    	  aradom();
		    	  loadrandom(1);
			      populateFields(1);
			      
			      i = 1;   
		            return i;
		      }else{
		      loadrandom(sum);
		     
		      populateFields(j);
		      }
		      	   i = sum;   
		            return i;
		    
		  }
		  public int backwd(int b){
			   int sum;
			    
			      sum = b-1;
			      if(sum <= 0){
			        	Toast.makeText(Card2.this,"�O�Ĥ@�ӳ�r!!!",Toast.LENGTH_SHORT).show();
			        	countm(count=1);
			        }else{
			      loadrandom(sum);
			      populateFields(j);
			      countm(count-1);
			      i = sum; 	 }     
			            return i;
			    
			  }
		  private int loadrandom(int g){
		      int rqq = g;
	            Cursor note = mDbHelper.radomAllNotes(rqq);
	            startManagingCursor(note);
	            String  bb = note.getString(note.getColumnIndexOrThrow(DbAdapter.KEY_idnum));
	            int qb =  Integer.parseInt(bb);
	             j = qb;
	             return j;
	        
	    }
		  private void populateFields(int x) {
			  switch(qq){
			  case 1:
				  long bbq = x;
		            Cursor note = mDbHelper.monad1note(bbq);
		            startManagingCursor(note);
		           tv1.setText(note.getString(
		                    note.getColumnIndexOrThrow(DbAdapter.KEY_wrod)));
		            tv2.setText(note.getString(
		                    note.getColumnIndexOrThrow(DbAdapter.KEY_chiness)));
		           
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
				  break;
			  case 2:
				  long bbqc = x;
				 // Toast.makeText(Card2.this,String.valueOf(bbqc),Toast.LENGTH_SHORT).show();
		            Cursor note1 = mDbHelper.user_word(bbqc);
		            startManagingCursor(note1);
		            int i = note1.getColumnCount();
					 if (note1.getColumnCount() < 0) {
		           tv1.setText(note1.getString(
		                    note1.getColumnIndexOrThrow(DbAdapter.KEY_wrod)));
		            tv2.setText(note1.getString(
		                    note1.getColumnIndexOrThrow(DbAdapter.KEY_chiness)));
		           
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
				  break;
					 }
			  }
					
		            
		        
		    }
		  private final Runnable timerRun = new Runnable()
		  {
		    public void run()
		    {
		    	nexewd(i);
		      mHandlerTime.postDelayed(this, 5000);
		      // �Y�n�����i�H�g�@�ӧP�_�b�o�M�w�O�_�ҰʤU�@���Y�i
		     
		    }
		  };
		  public void aradom(){
			  if(qq == 2){
			  Cursor cuser = mDbHelper.userAllid();
			  startManagingCursor(cuser);
			   int gb = cuser.getCount();
			   if(gb >10){
				         for(int a=1;a<100;a++){
					      int num = (int) (Math.random()*(gb-1))+1;
						  mDbHelper.updateradom(a, num);
						  }
			   }else{
				   //Toast.makeText(Card2.this,"��r�Ƥ֩�10�ӵL�k�H��",Toast.LENGTH_SHORT).show();   
				   Intent intent = new Intent();
					Bundle b = new Bundle();
					b.putInt("c_table", 2);
					intent.putExtras(b);
					intent.setClass(Card2.this,Card.class);
					startActivity(intent);
					finish();  
			   }
			          
			  }else{
				  Cursor c2000 = mDbHelper.All2000();
				  startManagingCursor(c2000);
				  int gb = c2000.getCount();
				  for(int a=1;a<100;a++){
					  int num = (int) (Math.random()*(gb-1))+1;
					  mDbHelper.updateradom(a, num);
						  }
			  }
				
		  }
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
								//velocityText.setText(MainActivity.this.getString(R.string.velocity_string)+"������V => �V�k :"+m_tracker.getXVelocity());
							}else{
								backwd(i); 
								//velocityText.setText(MainActivity.this.getString(R.string.velocity_string)+"������V => �V�� :"+m_tracker.getXVelocity());
							}
						
						}else if (Math.abs(m_tracker.getXVelocity()) < Math.abs(m_tracker.getYVelocity())) {
							if(m_tracker.getYVelocity()<0){
								//velocityText.setText(MainActivity.this.getString(R.string.velocity_string)+"������V => �V�W :"+m_tracker.getYVelocity());
							}else{
								//velocityText.setText(MainActivity.this.getString(R.string.velocity_string)+"������V => �V�U :"+m_tracker.getYVelocity());
							}
							//velocityText.setText(MainActivity.this.getString(R.string.velocity_string)+"������V:"+m_tracker.getYVelocity());
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

