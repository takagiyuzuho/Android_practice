package jp.co.it_college.mydbapp;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		// tableの定義 - contract class
		// users(name, score)

		//open helper

		//open db
		UseOpenHelper userOpenHelper = new UseOpenHelper(this);
		SQLiteDatabase db = userOpenHelper.getWritableDatabase();

		//transaction
		try {
			db.beginTransaction();
			db.execSQL("update users " +
					"set score = score + 10 " +
					"where name = 'taguchi'");
			db.execSQL("update users " +
					"set score = score - 10 " +
					"where name = 'fkoji'");
			db.setTransactionSuccessful();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			db.endTransaction();
		}

//		//処理（select,insert,update,delete)
//		//insert
//		ContentValues newUser = new ContentValues();
//		newUser.put(UserContract.Users.COL_NAME, "tanaka");
//		newUser.put(UserContract.Users.COL_SCORE, 50);
//		long newId = db.insert(
//				UserContract.Users.TABLE_NAME,
//				null,
//				newUser
//		);
//
//		//update
//		ContentValues newScore = new ContentValues();
//		newScore.put(UserContract.Users.COL_SCORE, 100);
//		int updatedCount = db.update(
//			UserContract.Users.TABLE_NAME,
//			newScore,
//			UserContract.Users.COL_NAME + "= ?",
//			new String[] {"fkoji"}
//		);
//
//		//delete
//		int deletedCount = db.delete(
//				UserContract.Users.TABLE_NAME,
//				UserContract.Users.COL_NAME + "= ?",
//				new String[] {"dotinstall"}
//		);

		//全件表示
		//結果レコードにアクセスするためのインターフェース
		Cursor c = null;
		c = db.query(
				UserContract.Users.TABLE_NAME,
//				null,
//				UserContract.Users.COL_SCORE + " > ?",
//				new String[]{ "50" },
//				null,
//				null,
//				UserContract.Users.COL_SCORE + " desc",
//				"1"
				null,
				null,
				null,
				null,
				null,
				null,
				null

		);
		Log.v("DB_TEST", "Count:" + c.getCount());
		while(c.moveToNext()) {
			int id = c.getInt(c.getColumnIndex(UserContract.Users._ID));
			String name = c.getString(c.getColumnIndex(UserContract.Users.COL_NAME));
			String score = c.getString(c.getColumnIndex(UserContract.Users.COL_SCORE));
			Log.v("DB_TEST", "id:" + id + "name:" + name +"score:" + score );
		}

		//close
	}
}
