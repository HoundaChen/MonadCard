package hd.work.monadcard;



import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Configuration;
import android.database.Cursor;
import android.database.SQLException;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.Spinner;
import android.widget.Toast;

public class Setpage extends Activity  {
private Button bt2000,btuser,edbt2000,eduserbt,gotbt;

private CheckBox cb2000,cbuser;
int i;
	/**
	 * @param args
	 */

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.newsetpg);
		
		String DB_PATH = "/data/data/hd.work.monadcard/databases/";
		String DB_NAME = "card.db";
			// 檢查 SQLite 數據庫是否存在
			if ((new File(DB_PATH + DB_NAME)).exists() == false) {
				// 如 SQLite 數據庫不存在剛檢查 database 目錄是否存在
				File f = new File(DB_PATH);
				// 如 database 目錄不存在,則建立該目錄
				if (!f.exists()) {
					f.mkdir();
				}

				try {
					// 取得 assets 目錄下所準備好的 SQLite數據庫作為輸入流
					InputStream is = getBaseContext().getAssets().open(DB_NAME);
					// 輸出流
					OutputStream os = new FileOutputStream(DB_PATH + DB_NAME);

					// 文件寫入
					byte[] buffer = new byte[1024];
					int length;
					while ((length = is.read(buffer)) > 0) {
						os.write(buffer, 0, length);
					}

					// 關閉文件流
					os.flush();
					os.close();
					is.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			//定義類別實作
		bt2000 = (Button)findViewById(R.id.bt2000);
		edbt2000 = (Button)findViewById(R.id.edit2000button);
		eduserbt = (Button)findViewById(R.id.usereditbutton);
		btuser = (Button)findViewById(R.id.btuseself);
		cb2000=(CheckBox)findViewById(R.id.checkBox2000);
		cbuser=(CheckBox)findViewById(R.id.checkBoxself);
		gotbt= (Button)findViewById(R.id.gotestbt);
		
		/*ArrayAdapter<CharSequence> adapter = //設定下拉選單
				ArrayAdapter.createFromResource(this, R.array.list_array, android.R.layout.simple_spinner_item);
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		sp.setAdapter(adapter);
				//建立監聽功能
		sp.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> arg0, View arg1,
					int arg2, long arg3) {
				// TODO 自動產生的方法 Stub
				return1(arg3);
			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
				// TODO 自動產生的方法 Stub
				
			}
		});*/
		/**
		 * 以下相對應按鈕和CHECK方塊
		 */
		
	cb2000.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
			
			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				// TODO 自動產生的方法 Stub
				if(isChecked){
					Toast.makeText(Setpage.this,"單字顯示為隨機方式",Toast.LENGTH_SHORT).show();
				}else{
					
				}
			}
		});
		
         cbuser.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
			
			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				// TODO 自動產生的方法 Stub
				if(isChecked){
					Toast.makeText(Setpage.this,"單字顯示為隨機方式",Toast.LENGTH_SHORT).show();
				}else{
					
				}
			}
		});
         gotbt.setOnClickListener(new Button.OnClickListener() {

 			@Override
 			public void onClick(View arg0) {
 				// TODO 自動產生的方法 Stub
 				
 				
 						Intent intent = new Intent();
 						//Bundle b = new Bundle();
 						//b.putInt("c_table", 1);
 						//intent.putExtras(b);
 						intent.setClass(Setpage.this,reciveText.class);
 						startActivity(intent);
 						finish();  
 					
 				}
 						
 		});
		bt2000.setOnClickListener(new Button.OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO 自動產生的方法 Stub
				
					if (cb2000.isChecked()==true){
						Intent intent = new Intent();
						Bundle b = new Bundle();
						b.putInt("c_table", 1);
						intent.putExtras(b);
						intent.setClass(Setpage.this,Card2.class);
						startActivity(intent);
						finish();  }else{
							Intent intent = new Intent();
							Bundle b = new Bundle();
							b.putInt("c_table", 1);
							intent.putExtras(b);
							intent.setClass(Setpage.this,Card.class);
							startActivity(intent);
							
			 				finish(); 
							
						}
					
				}
						
		});
		btuser.setOnClickListener(new Button.OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO 自動產生的方法 Stub
				
					if (cbuser.isChecked()==true){
						Intent intent = new Intent();
						Bundle b = new Bundle();
						b.putInt("c_table", 2);
						intent.putExtras(b);
						intent.setClass(Setpage.this,Card2.class);
						startActivity(intent);
						finish();  
						}else{
							Intent intent = new Intent();
							Bundle b = new Bundle();
							b.putInt("c_table", 2);
							intent.putExtras(b);
							intent.setClass(Setpage.this,Card.class);
							startActivity(intent);
							finish();  
						}
					
				}
						
		});
		
		edbt2000.setOnClickListener(new Button.OnClickListener(){
			public void onClick(View arg0) {
				
				
				Intent intent = new Intent();
				Bundle b = new Bundle();
				b.putInt("c_table", 1);
				intent.putExtras(b);
				intent.setClass(Setpage.this,wordlist.class);
				startActivity(intent);
				finish();  
				
			}
		});
		eduserbt.setOnClickListener(new Button.OnClickListener(){
			public void onClick(View arg0) {
				
				
				Intent intent = new Intent();
				Bundle b = new Bundle();
				b.putInt("c_table", 2);
				intent.putExtras(b);
				intent.setClass(Setpage.this,wordlist.class);
				startActivity(intent);
				finish();  
				
			}
		});
	}  
	
/*public int return1(long x){ // 讓其他物件利用下拉式選點所拋出出的資料轉換
	int g = (int)(x);
	i = g;
//	Toast.makeText(Setpage.this,"依據單字庫大小隨機選"+g,Toast.LENGTH_SHORT).show();
	return i;
}
*/
}