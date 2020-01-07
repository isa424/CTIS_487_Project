package com.example.mobileproject;

import android.content.ContentValues;
import android.database.Cursor;
import java.util.ArrayList;
import java.util.List;

public class LectureDB {
	public static final String TABLE_NAME = "lectures",
		FIELD_ID = "_id",
		FIELD_NAME = "name",
		FIELD_TEACHER = "teacher",
		FIELD_CODE = "code",
		FIELD_ROOM = "classroom",
		FIELD_DAY = "day",
		FIELD_START_HOUR = "start_hour",
		FIELD_START_MINUTE = "start_minute",
		FIELD_END_HOUR = "end_hour",
		FIELD_END_MINUTE = "end_minute";

	public static final String CREATE_TABLE_SQL = "CREATE TABLE " + TABLE_NAME + " (" + FIELD_ID +
		" integer primary key, " + FIELD_NAME + " text, " + FIELD_TEACHER + " text, " + FIELD_CODE + " text, " +
		FIELD_ROOM + " text, " + FIELD_DAY + " integer, " + FIELD_START_HOUR + " integer, " +
		FIELD_START_MINUTE + " integer, " + FIELD_END_HOUR + " integer, " + FIELD_END_MINUTE +
		" integer);";
	public static final String DROP_TABLE_SQL = "DROP TABLE if exists " + TABLE_NAME;

	public static List<Lecture> getAllLectures(DatabaseHelper db) {
		Cursor cursor = db.getAllRecords(TABLE_NAME, null);
		//Cursor cursor  db.getAllRecordsMethod2("SELECT * FROM "+TABLE_NAME, null)
		List<Lecture> data = new ArrayList<>();
		Lecture pro = null;
		LectureDate date = null;
		while (cursor.moveToNext()) {
			int id = cursor.getInt(0);
			String name = cursor.getString(1);
			String teacher = cursor.getString(2);
			String code = cursor.getString(3);
			String classroom = cursor.getString(4);
			int day = cursor.getInt(5);
			int startHour = cursor.getInt(6);
			int startMinute = cursor.getInt(7);
			int endHour = cursor.getInt(8);
			int endMinute = cursor.getInt(9);

			try {
				date = new LectureDate(day, startHour, startMinute, endHour, endMinute);
				pro = new Lecture(name, teacher, code, classroom, date);
				data.add(pro);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return data;
	}

	public static List<Lecture> findPro(DatabaseHelper db, String key) {

		String where = FIELD_NAME + " like '%" + key + "%'";
		Cursor cursor = db.getSomeRecords(TABLE_NAME, null, where);
		List<Lecture> data = new ArrayList<>();
		Lecture pro = null;
		while (cursor.moveToNext()) {
			int id = cursor.getInt(0);
			String name = cursor.getString(1);
			double price = cursor.getDouble(2);

//			pro = new Lecture(id, name, price);
//			data.add(pro);
		}

		return data;
	}

	public static long insert(DatabaseHelper db, Lecture l) {
		ContentValues contentValues = new ContentValues();
		contentValues.put(FIELD_NAME, l.getName());
		contentValues.put(FIELD_TEACHER, l.getTeacher());
		contentValues.put(FIELD_CODE, l.getCode());
		contentValues.put(FIELD_ROOM, l.getRoom());
		contentValues.put(FIELD_DAY, l.getDate().day);
		contentValues.put(FIELD_START_HOUR, l.getDate().startHour);
		contentValues.put(FIELD_START_MINUTE, l.getDate().startMinute);
		contentValues.put(FIELD_END_HOUR, l.getDate().endHour);
		contentValues.put(FIELD_END_MINUTE, l.getDate().endMinute);

		long res = db.insert(TABLE_NAME, contentValues);

		return res;
	}

	public static boolean updatePro(DatabaseHelper db, int id, String name, double price) {
		ContentValues contentValues = new ContentValues();
		contentValues.put(FIELD_NAME, name);
		String where = FIELD_ID + " = " + id;

		boolean res = db.update(TABLE_NAME, contentValues, where);

		return res;
	}

	public static boolean deletePro(DatabaseHelper db, int id) {
		String where = FIELD_ID + " = " + id;
		boolean res = db.delete(TABLE_NAME, where);
		return res;
	}
}
