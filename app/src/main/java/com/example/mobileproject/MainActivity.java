package com.example.mobileproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

	private ArrayList<Lecture> lectures;
	TopFragment topFragment;
	RecyclerView recyclerLectures;
	RecyclerViewAdapter adapter;
	LinearLayoutManager layoutManager;

	BottomFragment bottomFragment;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		recyclerLectures = findViewById(R.id.recyclerLectures);

		if(topFragment == null){
			topFragment =  (TopFragment) getSupportFragmentManager().findFragmentById(R.id.fragmentTop);
		}
		if(bottomFragment == null)
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
		layoutManager = new LinearLayoutManager(this);
		layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
		recyclerLectures.setLayoutManager(layoutManager);
		recyclerLectures.hasFixedSize();
		adapter = new RecyclerViewAdapter(this, LectureSys.getLectures());
		recyclerLectures.setAdapter(adapter);
	}


}
