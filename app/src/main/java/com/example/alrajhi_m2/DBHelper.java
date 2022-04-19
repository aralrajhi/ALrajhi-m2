package com.example.alrajhi_m2;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;


public class DBHelper extends SQLiteOpenHelper {

	public static final String DATABASE_NAME = "userDB";
	public static final int DATABASE_VERSION = 1;

	public static final String TABLE_USER= "user";

	public static final String USER_ROW_ID = "_row_id";
	public static final String USER_ID = "user_id";
	public static final String USER_FIRST_NAME = "first_name";
	public static final String USER_SURNAME = "surname";
	public static final String USER_NATIONAL_ID = "national_id";

	private String[] allColumns = {USER_ROW_ID, USER_ID, USER_FIRST_NAME,USER_SURNAME,USER_NATIONAL_ID};

	private static final String DATABASE_CREATE = "create table "
			+ TABLE_USER + "( " + USER_ROW_ID
			+ " integer primary key autoincrement, "
			+ USER_ID +" text not null,"
			+ USER_NATIONAL_ID +" text not null,"
			+ USER_FIRST_NAME +" text not null,"
			+ USER_SURNAME +" text not null" +
			");";

	public DBHelper(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase database) {
		database.execSQL(DATABASE_CREATE);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_USER);
		onCreate(db);
	}


	public List<String> getAllData()
	{
		List<String> list = new ArrayList<String>();
		SQLiteDatabase db = getReadableDatabase();
		Cursor cursor = db.query(TABLE_USER,allColumns, null, null, null, null, null);

		if (cursor==null || cursor.getCount()==0)
		{
			cursor.close();
			db.close();
			return list;
		}

		cursor.moveToFirst();
		while (!cursor.isAfterLast()) {

			long row_id = cursor.getLong(0);
			String id = cursor.getString(1);
			String natId = cursor.getString(2);
			String name = cursor.getString(3);
			String surname = cursor.getString(4);
			User user = new User();
			user.set_rowId(row_id);
			user.set_Id(id);
			user.set_Surname(surname);
			user.set_NationalId(natId);
			user.set_firstName(name);
			String str = "ID: "+user.get_Id()+"\nFirst Name: "+user.get_firstName()+"\nSurname: "+user.get_Surname()+"\nNational ID: "+user.get_NationalId();
			list.add(str);
			cursor.moveToNext();
		}

		db.close();
		cursor.close();
		return list;
	}

	public long insertUser(User user) {
		ContentValues values = new ContentValues();
		values.put(USER_ID,user.get_Id());
		values.put(USER_FIRST_NAME,user.get_firstName());
		values.put(USER_SURNAME,user.get_Surname());
		values.put(USER_NATIONAL_ID,user.get_NationalId());
		SQLiteDatabase db = getWritableDatabase();
		long dataId = db.insert(TABLE_USER, null,values);
		db.close();
		return dataId;
	}


	public void deleteData(String term) {
		try{
			SQLiteDatabase db = getWritableDatabase();
			String deleteQuery = "DELETE FROM "+TABLE_USER+" WHERE first_name  LIKE '%"+term+"%'";
			db.execSQL(deleteQuery);
			db.close();
		}
		catch (Exception e){
			e.getMessage();
		}
	}
}
