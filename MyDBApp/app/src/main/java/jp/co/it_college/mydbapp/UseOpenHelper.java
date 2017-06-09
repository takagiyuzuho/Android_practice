package jp.co.it_college.mydbapp;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by takagi-yuzuho on 2017/06/09.
 */

public class UseOpenHelper extends SQLiteOpenHelper {

	public static final String DB_NAME = "myapp.db";
	public static final int DB_VERSION = 2;
	public static final String CREATE_TABLE = "CREATE TABLE " +
			UserContract.Users.TABLE_NAME + "(" +
			UserContract.Users._ID + " integer primary key autoincrement," +
			UserContract.Users.COL_NAME + " text," +
			UserContract.Users.COL_SCORE + " integer)";

	public static final String INIT_TABLE =
			"insert into users (name, score) values " +
					"('taguchi', 42), " +
					"('fkoji', 82), " +
					"('dotinstall', 62)";

	public static final String DROP_TABLE =
			"drop table if exists users";

	public UseOpenHelper(Context c) {
		super(c, DB_NAME, null, DB_VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		// create table
		db.execSQL(CREATE_TABLE);
		// init table
		db.execSQL(INIT_TABLE);

	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// drop table
		db.execSQL(DROP_TABLE);
		// onCreate
		onCreate(db);

	}
}
