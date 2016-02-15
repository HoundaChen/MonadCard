package hd.work.monadcard;



import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DbAdapter {

    
    public static final String KEY_ROWID = "_id";
    public static final String KEY_wrod = "wrod";
    public static final String KEY_chiness ="chiness";
    public static final String KEY_idnum ="idnum";
    public static final String KEY_text ="etext";
    public static final String KEY_ctext ="ctext";
    
    

    
    private DatabaseHelper mDbHelper;
    private SQLiteDatabase mDb;
   //資料庫建立字串
   /* private static final String DATABASE_CREATE =
        "create table monad (_id integer primary key autoincrement, "
        + "wrod text, chiness text );";*/

    private static final String DATABASE_NAME = "card.db";
    private static final String DATABASE_TABLE = "monad";
    private static final String DATABASE_TABLE2 = "radom";
    private static final String DATABASE_TABLE1 = "monad1";
    private static final String DATABASE_TABLE3 = "monad3";
    private static final int DATABASE_VERSION = 1;

    private final Context mCtx;

    private static class DatabaseHelper extends SQLiteOpenHelper {

        DatabaseHelper(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {

  /*    db.execSQL(DATABASE_CREATE);*/
        }

        //資料庫更新
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
     /*     Log.w("TAG", "Upgrading database from version " + oldVersion + " to "
                    + newVersion + ", which will destroy all old data");
            db.execSQL("DROP TABLE IF EXISTS notes");
            onCreate(db);*/
        }
    }

   
    public DbAdapter(Context ctx) {
        this.mCtx = ctx;
    }

    public DbAdapter open() throws SQLException {
        mDbHelper = new DatabaseHelper(mCtx);
        mDb = mDbHelper.getWritableDatabase();
        return this;
    }

    public void close() {
        mDbHelper.close();
    }

// 自定單字新增
    public long user_create(String wrod,String chiness ,String etext , String ctext) {
        ContentValues initialValues = new ContentValues();
     
        initialValues.put(KEY_wrod, wrod);
        initialValues.put(KEY_chiness, chiness);
        initialValues.put(KEY_text, etext);
        initialValues.put(KEY_ctext, ctext);
       
        return mDb.insert(DATABASE_TABLE3, null, initialValues);
    }

    public boolean user_delete(long rowId) {

       return mDb.delete(DATABASE_TABLE3, KEY_ROWID + "=" + rowId, null) > 0; 
    }

    public Cursor radomAllNotes(int rowId) throws SQLException {
        //查詢radom全部資料  -- 
    	  Cursor mCursor =
                  
    	           mDb.query(true, DATABASE_TABLE2, new String[] {KEY_ROWID,
    	        		   KEY_idnum}, KEY_ROWID + "=" + rowId, null,
    	                    null, null,null,null);
    	        if (mCursor != null) {
    	            mCursor.moveToFirst();
    	        }
    	        return mCursor;

    	    }
    public Cursor All2000() {
        //查詢monad_id全部資料  -- 
        return mDb.query(DATABASE_TABLE1, new String[] {KEY_ROWID,KEY_wrod,KEY_chiness
       }, null, null, null,null,null);
    }
    public Cursor Alluser() {
        //查詢monad_id全部資料  -- 
        return mDb.query(DATABASE_TABLE3, new String[] {KEY_ROWID,KEY_wrod,KEY_chiness
       }, null, null, null,null,null);
    }
   
    public Cursor userAllid() {
        //查詢monad_id全部資料  -- 
        return mDb.query(DATABASE_TABLE3, new String[] {KEY_ROWID
       }, null, null, null,null,null);
    }
    public Cursor monad1allid() {
        //查詢monad1_id全部資料  -- 
        return mDb.query(DATABASE_TABLE1, new String[] {KEY_ROWID
       }, null, null, null,null,null);
    }
    public Cursor user_word(long rowId) throws SQLException {
//查詢monad單筆資料
        Cursor mCursor =
                 mDb.query(true, DATABASE_TABLE3, new String[] {KEY_ROWID,
        	        KEY_wrod,KEY_chiness,KEY_text,KEY_ctext}, KEY_ROWID + "=" + rowId, null,
                    null, null,null,null);
        if (mCursor != null) {
            mCursor.moveToFirst();
        }
        return mCursor;

    }
    public Cursor monad1note(long rowId) throws SQLException {
    	//查詢monad1單筆資料
    	        Cursor mCursor =
    	              
    	           mDb.query(true, DATABASE_TABLE1, new String[] {KEY_ROWID,
    	        	        KEY_wrod,KEY_chiness,KEY_text,KEY_ctext}, KEY_ROWID + "=" + rowId, null,
    	                    null, null,null,null);
    	        if (mCursor != null) {
    	            mCursor.moveToFirst();
    	        }
    	        return mCursor;

    	    }
//    自定單字更新資料
    public boolean user_updater(long rowId, String wrod,String chiness ,String etext , String ctext) {
    	
        ContentValues args = new ContentValues();
      
        args.put(KEY_wrod, wrod);
        args.put(KEY_chiness, chiness);
        args.put(KEY_text, etext);
        args.put(KEY_ctext, ctext);
        
        return mDb.update(DATABASE_TABLE3, args, KEY_ROWID + "=" + rowId, null) > 0;
    }
//  2000單字更新資料
  public boolean wrod2000_updater(long rowId, String wrod,String chiness ,String etext , String ctext) {
  	
      ContentValues args = new ContentValues();
    
      args.put(KEY_wrod, wrod);
      args.put(KEY_chiness, chiness);
      args.put(KEY_text, etext);
      args.put(KEY_ctext, ctext);
      
      return mDb.update(DATABASE_TABLE1, args, KEY_ROWID + "=" + rowId, null) > 0;
  }
  //redom(亂數資料儲存)
    public boolean updateradom(int rowId, int idnum) {
    	
        ContentValues args = new ContentValues();
      
        args.put(KEY_idnum, idnum);
    
        
       
      

        return mDb.update(DATABASE_TABLE2, args, KEY_ROWID + "=" + rowId, null) > 0;
    }
    public String[] getItems(String KEY_ITEM) {
		String selectQuery = "SELECT " + "*" + " FROM " + DATABASE_TABLE;
       
		Cursor cursor = mDb.rawQuery(selectQuery, null);
		String[] items = new String[cursor.getCount()];
		int i=0;
		if(cursor.moveToFirst()){
			do {
				
			        String item = cursor.getString(cursor.getColumnIndex(KEY_ITEM));
				    items[i] = item;
				    i++;
				
			} while (cursor.moveToNext());
		}
		
		cursor.close();
		
	    return items;
	}
}
