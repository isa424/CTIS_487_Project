package com.example.mobileproject;


import android.util.Log;

import java.util.ArrayList;

public class LectureSys {
    private static ArrayList<Lecture> lectures = new ArrayList<Lecture>();

    public static void prepareData() {
        try {
            Lecture l0 = new Lecture("intro to prog", "ISA", "151", "g250", new LectureDate(1, 3, 30, 5, 30));
            Lecture l1 = new Lecture("intro to t", "ISA", "151", "g250", new LectureDate(1, 3, 30, 5, 30));
            Lecture l2 = new Lecture("intro to pr", "ISA", "151", "g250", new LectureDate(2, 3, 30, 5, 30));
            Lecture l3 = new Lecture("intro to gay", "ISA", "151", "g250", new LectureDate(4, 3, 30, 5, 30));

            lectures.add(l0);
            lectures.add(l1);
            lectures.add(l2);
            lectures.add(l3);

            for (int i = 0; i <lectures.size(); i++){
                Log.d("MyDebug", String.valueOf(lectures.get(i)));
            }
        } catch (InvalidLectureDateException e) {
            e.printStackTrace();
        }
    }

    public static ArrayList<Lecture> getLectures() {
        return lectures;
    }
}
