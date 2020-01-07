package com.example.mobileproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.AsyncTask;
import android.app.Dialog;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import org.w3c.dom.Text;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
	String jsonStr = null;
	private ArrayList<Lecture> lectures = new ArrayList<>();
	Dialog customDialog;
	TopFragment topFragment;
	RecyclerView recyclerLectures;
	RecyclerViewAdapter adapter;
	LinearLayoutManager layoutManager;
	TextView tvName, tvTeacher, tvCode, tvClassroom, tvDate;

	BottomFragment bottomFragment;
	Intent addLectureIntent;
	DatabaseHelper dbHelper;
	JSONObject jsondata;
	JSONArray lecturesArray;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		dbHelper = new DatabaseHelper(this);

		recyclerLectures = findViewById(R.id.recyclerLectures);

		if (topFragment == null) {
			topFragment = (TopFragment) getSupportFragmentManager().findFragmentById(R.id.fragmentTop);
		}
		if (bottomFragment == null)
			bottomFragment = (BottomFragment) getSupportFragmentManager().findFragmentById(R.id.fragmentBottom);

//		bottomFragment = new BottomFragment();
//		Bundle args = new Bundle();
//		args.putInt("position", 1);
//		bottomFragment.setArguments(args);
//
//		FragmentManager fragmentManager = this.getSupportFragmentManager();
//		FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
//		fragmentTransaction.replace(R.id.dynamicFragmentLayout, bottomFragment);
//		fragmentTransaction.commit();

		LectureSys.prepareData();

//		Log.d("MyDebug", String.valueOf(recyclerLectures));

		jsonStr = loadFileFromAssets("lectures.json");

		new GetLecturesJSON().execute();
	}

	public void displayDialog(int selectedPoistion, Lecture lecture ){
		//Album selectedalbum = albumList.get(selectedPoistion);
		final Lecture selectedLecture = lecture;

		customDialog = new Dialog(this);
		customDialog.setContentView(R.layout.dialog);
		customDialog.setTitle("Custom Dialog");

		tvName = (TextView) customDialog.findViewById(R.id.tvName);
		tvTeacher =(TextView) customDialog.findViewById(R.id.tvTeacher);
		tvCode = (TextView) customDialog.findViewById(R.id.tvCode);
		tvClassroom = (TextView) customDialog.findViewById(R.id.tvClassroom);
		tvDate = (TextView) customDialog.findViewById(R.id.tvDates);

		tvName.setText(lecture.getName());
		tvTeacher.setText(lecture.getTeacher());
		tvCode.setText(lecture.getCode());
	tvClassroom.setText(lecture.getClassroom());
		tvDate.setText(lecture.getDates().toString());

		customDialog.show();
	}

	public void openAddLectureActivity(View view) {
		addLectureIntent = new Intent(this, AddLectureActivity.class);
		startActivityForResult(addLectureIntent, 1);
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		if (requestCode == 1) {
			if (resultCode == RESULT_OK) {
				int day = data.getExtras().getInt("day");
				int startHour = data.getExtras().getInt("start_hour");
				int startMinute = data.getExtras().getInt("start_minute");
				int endHour = data.getExtras().getInt("end_hour");
				int endMinute = data.getExtras().getInt("end_minute");

				try {
					Lecture l = new Lecture(
						data.getExtras().getString("name"),
						data.getExtras().getString("teacher"),
						data.getExtras().getString("code"),
						data.getExtras().getString("classroom"),
						new LectureDate(
							day, startHour, startMinute, endHour, endMinute
						)
					);

					LectureDB.insert(dbHelper, l);
					lectures = (ArrayList<Lecture>) LectureDB.getAllLectures(dbHelper);

					layoutManager = new LinearLayoutManager(MainActivity.this);
					layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
					recyclerLectures.setLayoutManager(layoutManager);
					recyclerLectures.hasFixedSize();
					adapter = new RecyclerViewAdapter(MainActivity.this, lectures);
					recyclerLectures.setAdapter(adapter);
					Log.d("mydebug", l.test());
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}

	private String loadFileFromAssets(String fileName) {
		String file = null;

		try {
			InputStream is = getBaseContext().getAssets().open(fileName);

			int size = is.available();
			byte[] buffer = new byte[size];

			is.read(buffer);
			is.close();

			file = new String(buffer, "UTF-8");
		} catch (IOException ex) {
			ex.printStackTrace();
			return null;
		}

		return file;
	}

	private class GetLecturesJSON extends AsyncTask<Void, Void, Void> {
		// Main job should be done here
		@Override
		protected Void doInBackground(Void... params) {
			//Log.d("TAG", "HERE.....");

			if (jsonStr != null) {
				try {
					jsondata = new JSONObject(jsonStr);
					lecturesArray = jsondata.getJSONArray("lectures");

					// looping through all books
					for (int i = 0; i < lecturesArray.length(); i++) {
						JSONObject c = lecturesArray.getJSONObject(i);

						Thread.sleep(1000);

						LectureDate d = new LectureDate(
							c.getInt("day"),
							c.getInt("start_hour"),
							c.getInt("start_minute"),
							c.getInt("end_hour"),
							c.getInt("end_minute")
						);
						Lecture l = new Lecture(
							c.getString("name"),
							c.getString("teacher"),
							c.getString("code"),
							c.getString("classroom"),
							d
						);

						lectures.add(l);
					}
				} catch (JSONException ee) {
					ee.printStackTrace();
				} catch (InterruptedException e) {
					e.printStackTrace();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}

			return null;
		}

		@Override
		protected void onPreExecute() {
			super.onPreExecute();
		}

		// What do you want to do after doInBackground() finishes
		@Override
		protected void onPostExecute(Void aVoid) {
			super.onPostExecute(aVoid);
			// Dismiss the progress dialog

			if (lectures.size() > 0) {
				for (int i = 0; i < lectures.size(); i++) {
					LectureDB.insert(dbHelper, lectures.get(i));
				}

				layoutManager = new LinearLayoutManager(MainActivity.this);
				layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
				recyclerLectures.setLayoutManager(layoutManager);
				recyclerLectures.hasFixedSize();
				adapter = new RecyclerViewAdapter(MainActivity.this, lectures);
				recyclerLectures.setAdapter(adapter);
			}

//			pb.setVisibility(View.INVISIBLE);
//			if (mArrayList != null) {
//				mRecyclerAdapter = new RecyclerAdapter(MainActivity.this, mArrayList);
//				mRecyclerView.setAdapter(mRecyclerAdapter);
//			} else
//				Toast.makeText(MainActivity.this, "Not Found", Toast.LENGTH_LONG).show();
		}
	}
}
