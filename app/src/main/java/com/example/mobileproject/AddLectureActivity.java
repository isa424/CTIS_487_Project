package com.example.mobileproject;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;

public class AddLectureActivity extends AppCompatActivity {
	EditText lectureName, teacherName, courseCode, classroom;
	Spinner spDay, spStartHour, spStartMinute, spEndHour, spEndMinute;
	int day, startHour, startMinute, endHour, endMinute;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_add_lecture);

		lectureName = findViewById(R.id.add_lecture_lecture_name);
		teacherName = findViewById(R.id.add_lecture_teacher_name);
		courseCode = findViewById(R.id.add_lecture_code);
		classroom = findViewById(R.id.add_lecture_classroom);
		spDay = findViewById(R.id.add_lecture_day);
		spStartHour = findViewById(R.id.add_lecture_startHour);
		spStartMinute = findViewById(R.id.add_lecture_startMinute);
		spEndHour = findViewById(R.id.add_lecture_endHour);
		spEndMinute = findViewById(R.id.add_lecture_endMinute);
	}

	public void addLecture(View view) {
		day = spDay.getSelectedItemPosition() + 1;
		startHour = spStartHour.getSelectedItemPosition();
		startMinute = spStartMinute.getSelectedItemPosition();
		endHour = spEndHour.getSelectedItemPosition();
		endMinute = spEndMinute.getSelectedItemPosition();

		Intent i = new Intent();
		Bundle b = new Bundle();
		b.putString("name", lectureName.getText().toString());
		b.putString("teacher", teacherName.getText().toString());
		b.putString("code", courseCode.getText().toString());
		b.putString("classroom", classroom.getText().toString());
		b.putInt("day", day);
		b.putInt("start_hour", startHour);
		b.putInt("start_minute", startMinute);
		b.putInt("end_hour", endHour);
		b.putInt("end_minute", endMinute);
		i.putExtras(b);
		setResult(RESULT_OK, i);
		finish();
	}
}
