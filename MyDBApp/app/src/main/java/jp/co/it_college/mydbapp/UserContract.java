package jp.co.it_college.mydbapp;

import android.provider.BaseColumns;

/**
 * Created by takagi-yuzuho on 2017/06/09.
 */

public final class UserContract {

	public UserContract() {

	}

	public static abstract class Users implements BaseColumns { //自動でid
		public static final String TABLE_NAME="users";
		public static final String COL_NAME="name";
		public static final String COL_SCORE="score";
	}
}
