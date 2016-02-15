package hd.work.monadcard;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.SimpleAdapter;
import android.widget.Toast;

public class wordeditpg extends Activity {
	private DbAdapter mDbHelper;
	private ImageButton okbn, cancelbt, homebt;
	private EditText wrod, chiness, text, ctext;
	private Long mRowId;
	private int qq;

	/**
	 * @param args
	 */

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.app.Activity#onCreate(android.os.Bundle)
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.editpage);
		mDbHelper = new DbAdapter(this);// 實體化DbAdapter.java中的DbAdapter.Class
		mDbHelper.open();// 開起DbAdapter.Class
		wrod = (EditText) findViewById(R.id.editword);
		chiness = (EditText) findViewById(R.id.editchiness);
		text = (EditText) findViewById(R.id.editText);
		ctext = (EditText) findViewById(R.id.editcText);
		okbn = (ImageButton) findViewById(R.id.imageButtona1dd);
		cancelbt = (ImageButton) findViewById(R.id.imageButton1del);
		homebt = (ImageButton) findViewById(R.id.imageButton1home);
		// 依_ID 把資料庫資料分別載入
		mRowId = (savedInstanceState == null) ? null
				: (Long) savedInstanceState
						.getSerializable(DbAdapter.KEY_ROWID);
		// Toast.makeText(getApplicationContext(),
		// "從savedInstanceState取得id="+mRowId, Toast.LENGTH_SHORT).show();
		if (mRowId == null) {
			Bundle extras = getIntent().getExtras();
			mRowId = extras != null ? extras.getLong(DbAdapter.KEY_ROWID)
					: null;

		}
		Bundle bundle = this.getIntent().getExtras();
		
		if ((bundle==null)||bundle.isEmpty()) {
		//	Toast.makeText(getApplicationContext(),"請加入單字",Toast.LENGTH_SHORT).show();
			}else{
			int num =	bundle.getInt("c_table");
			qq = num;
//			Toast.makeText(getApplicationContext(),"qq = " +String.valueOf(qq),Toast.LENGTH_SHORT).show();
		} 
		populateFields();

		homebt.setOnClickListener(new ImageButton.OnClickListener() {
			public void onClick(View v) {
				// TODO 自動產生的方法 Stub
				Intent mainIntent = new Intent(wordeditpg.this, Setpage.class);
				startActivity(mainIntent);
			}
		});
		okbn.setOnClickListener(new ImageButton.OnClickListener() {
			public void onClick(View v) {
				// TODO 自動產生的方法 Stub
				setResult(RESULT_OK);
				Intent intent = new Intent();
				Bundle b = new Bundle();
				//Toast.makeText(getApplicationContext(),"qq = " +String.valueOf(qq),Toast.LENGTH_SHORT).show();
				if(qq==1){
						b.putInt("c_table", 1);
				}else{
					b.putInt("c_table", 2);
				}
				intent.putExtras(b);
				intent.setClass(wordeditpg.this, wordlist.class);
				startActivity(intent);
				finish();
			}
		});
		cancelbt.setOnClickListener(new ImageButton.OnClickListener() {
			public void onClick(View v) {
				// TODO 自動產生的方法 Stub
				wrod.setText("");
				chiness.setText("");
				text.setText("");
				ctext.setText("");
			}
		});
	}

	private void populateFields() {
		switch (qq) {
		case 1:
			if (mRowId != null) {

				Cursor note = mDbHelper.monad1note(mRowId);
				
				
					startManagingCursor(note);
					wrod.setText(note.getString(note
							.getColumnIndexOrThrow(DbAdapter.KEY_wrod)));
					chiness.setText(note.getString(note
							.getColumnIndexOrThrow(DbAdapter.KEY_chiness)));
					if ((note.getString(note
							.getColumnIndexOrThrow(DbAdapter.KEY_text))) == null) {
						text.setText("");
					} else {
						text.setText(note.getString(note
								.getColumnIndexOrThrow(DbAdapter.KEY_text)));
					}
					if ((note.getString(note
							.getColumnIndexOrThrow(DbAdapter.KEY_ctext))) == null) {
						ctext.setText("");
					} else {
						ctext.setText(note.getString(note
								.getColumnIndexOrThrow(DbAdapter.KEY_ctext)));
					}
				
			}
			break;
		case 2:
			if (mRowId != null) {

				Cursor note1 = mDbHelper.user_word(mRowId);
				startManagingCursor(note1);
				 int i = note1.getColumnCount();
				 if (note1.getColumnCount() > 0) {
				// System.out.print("--------------"+i);
				wrod.setText(note1.getString(note1
						.getColumnIndexOrThrow(DbAdapter.KEY_wrod)));
				chiness.setText(note1.getString(note1
						.getColumnIndexOrThrow(DbAdapter.KEY_chiness)));
				text.setText(note1.getString(note1
						.getColumnIndexOrThrow(DbAdapter.KEY_text)));
				ctext.setText(note1.getString(note1
						.getColumnIndexOrThrow(DbAdapter.KEY_ctext)));
		    	}
			}
			break;
		}

	}

	protected void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);
		saveState();
		outState.putSerializable(DbAdapter.KEY_ROWID, mRowId);

	}

	@Override
	protected void onPause() {
		super.onPause();
		saveState();
	}

	@Override
	protected void onResume() {
		super.onResume();
		// populateFields();
	}

	private void saveState() {
		String wrod1 = wrod.getText().toString();
		String chiness1 = chiness.getText().toString();
		String text1 = text.getText().toString();
		String ctext1 = ctext.getText().toString();

		if (wrod.getText().toString() == null
				|| "".equals(wrod.getText().toString().trim())) {
			/*
			 * equals => 是否與 括號 裡的物件 相等 edittext.gettext() => 取得 EditText 資料
			 * .toString() => 轉型 成String trim() => 資料內容去除 空格後，剩下的資料
			 */
		//	 Toast.makeText(getApplicationContext(),"沒有資料喔!!!",Toast.LENGTH_SHORT).show();
			finish();
		} else if (mRowId == null) {

			long id = mDbHelper.user_create(wrod1, chiness1, text1, ctext1);
			if (id > 0) {
				mRowId = id;

			}

		} else {
			switch (qq) {
			case 1:
				mDbHelper.wrod2000_updater(mRowId, wrod1, chiness1, text1,
						ctext1);
				finish();
				break;
			case 2:
				mDbHelper.user_updater(mRowId, wrod1, chiness1, text1, ctext1);
				finish();
				break;
			}

		}
	}
}
