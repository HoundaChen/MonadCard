package hd.work.monadcard;




import android.app.Activity;
import android.app.AlertDialog;
import android.app.ListActivity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.drm.DrmStore.Action;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ContextMenu.ContextMenuInfo;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;
import android.widget.AdapterView.AdapterContextMenuInfo;

public class wordlist extends ListActivity {
	  private static final int ACTIVITY_CREATE=0;
	    private static final int ACTIVITY_EDIT=1;
	    //設定 Menu 的數字別
	    private static final int INSERT_ID = Menu.FIRST;
	    private static final int DELETE_ID = Menu.FIRST + 1;
	    private static final int return_MENU = Menu.FIRST + 2;
	private DbAdapter mDbHelper;
	private  int qq,gg;
	
	/**
	 * @param args
	 */
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		mDbHelper = new DbAdapter(this);//實體化DbAdapter.java中的DbAdapter.Class
	    mDbHelper.open();//開起DbAdapter.Class
	    Bundle bundle = this.getIntent().getExtras();
	    qq=bundle.getInt("c_table");
	    fillData();
		 registerForContextMenu(getListView());

	}
	 public void fillData(){
			switch(qq){
			case 1:
				
				Cursor notesCursor = mDbHelper.All2000();//建立實體Cursor 由mDbGelper(對映 DbAdpter.java)中的fetchAllNotes子程式
		        startManagingCursor(notesCursor);
		        String[] from = new String[]{DbAdapter.KEY_wrod,DbAdapter.KEY_chiness,DbAdapter.KEY_ROWID};
		        int[] to = new int[]{android.R.id.text1,android.R.id.text2};
		        SimpleCursorAdapter notes = 
			            new SimpleCursorAdapter(this,android.R.layout.simple_list_item_2, notesCursor , from ,to);
			           setListAdapter(notes);
				break;
			case 2:
				Cursor notesCursor1 = mDbHelper.Alluser();//建立實體Cursor 由mDbGelper(對映 DbAdpter.java)中的fetchAllNotes子程式
				int gb = notesCursor1.getCount();
				if(gb>1){
				  startManagingCursor(notesCursor1);
		        String[] from1 = new String[]{DbAdapter.KEY_ROWID,DbAdapter.KEY_chiness,DbAdapter.KEY_ROWID};
		        int[] to1 = new int[]{android.R.id.text1,android.R.id.text2};
		        SimpleCursorAdapter notes1 = 
			            new SimpleCursorAdapter(this,android.R.layout.simple_list_item_2, notesCursor1 , from1 ,to1);
			           setListAdapter(notes1);
			           }else{
			        	   Toast.makeText(getApplicationContext(), "請新增單字", Toast.LENGTH_SHORT).show();
			        	   createNote();
			           }
				
				break;
			}
	 }
	 public boolean onCreateOptionsMenu(Menu menu) {
	        menu.add(0, INSERT_ID, 0, R.string.m_menu);
	        menu.add(0, return_MENU, 0, R.string.m_add);
	        return true;
	    }

	    @Override
	 public boolean onMenuItemSelected(int featureId, MenuItem item) {
	        switch(item.getItemId()) {
	            case INSERT_ID:
	            	Intent intent = new Intent();
					intent.setClass(wordlist.this,Setpage.class);
					startActivity(intent);
					finish();  
	               
	                return true;
	            case return_MENU:
	            	 createNote();
	            	//openOptionsDialog();
	                return true;
	        }

	        return super.onMenuItemSelected(featureId, item);
	    }

	  public void onCreateContextMenu(ContextMenu menu, View v,
	            ContextMenuInfo menuInfo) {
	        super.onCreateContextMenu(menu, v, menuInfo);
	         menu.add(0, DELETE_ID, 0, R.string.menu_delete);
	         menu.add(0, return_MENU, 0, R.string.m_add);
	    }

	    @Override
	    public boolean onContextItemSelected(MenuItem item) {
	        switch(item.getItemId()) {
	            case DELETE_ID:
	            	if(qq ==2){
	                AdapterContextMenuInfo info = (AdapterContextMenuInfo) item.getMenuInfo();
	                mDbHelper.user_delete(info.id);
	                fillData();
	                return true;}else{
	                	Toast.makeText(getApplicationContext(), "這單字表禁止這動作", Toast.LENGTH_SHORT).show();
	                }
	            case return_MENU:
	            	createNote();
	            	//openOptionsDialog();
	                return true;
	            	
	                   }
	        return super.onContextItemSelected(item);
	    }

	    private void createNote() {
	    	if(qq==2){
	    		
	        Intent i = new Intent(this, wordeditpg.class);
	        startActivityForResult(i, ACTIVITY_CREATE);
	        }else{
	        	Toast.makeText(getApplicationContext(), "這單字表禁止這動作", Toast.LENGTH_SHORT).show();
	        }
	    }

	   
	  protected void onListItemClick(ListView l, View v, int position, long id) {
	        super.onListItemClick(l, v, position, id);
	        if(qq == 2){
	        Intent i = new Intent();
	        Bundle b = new Bundle();
			b.putInt("c_table", 2);
			b.putLong(DbAdapter.KEY_ROWID, id);
	        i.putExtras(b);
	        i.setClass(wordlist.this,wordeditpg.class);
			startActivity(i);
	        
	        startActivityForResult(i, ACTIVITY_EDIT);
	        mDbHelper.close();
				finish(); 
	        }else{
	        	 Intent i = new Intent();
	 	        Bundle b = new Bundle();
	 			b.putInt("c_table", 1);
	 			b.putLong(DbAdapter.KEY_ROWID, id);
	 	        i.putExtras(b);
	 	        i.setClass(wordlist.this,wordeditpg.class);
	 			startActivity(i);
		        startActivityForResult(i, ACTIVITY_EDIT);
		        mDbHelper.close();
  				finish(); 
	        }
	    }

	    @Override
	    protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
	        super.onActivityResult(requestCode, resultCode, intent);
	        fillData();
	    }
	/*    private void openOptionsDialog(){
	   	 AlertDialog.Builder dialog = new AlertDialog.Builder(wordlist.this);
	   	 dialog.setTitle(R.string.app_name);
	   	 dialog.setMessage(R.string.listhelp);
	   	 dialog.setPositiveButton("OK",new DialogInterface.OnClickListener() {
	   		
	   		public void onClick(DialogInterface dialog, int which) {
	   			// TODO 自動產生的方法 Stub
	   			
	   		}
	   	});
	   	dialog.show();
	    }*/

}
