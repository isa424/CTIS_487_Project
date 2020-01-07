package com.example.mobileproject;

import java.io.Serializable;

public class Lecture implements Serializable {
	private final String name, teacher, code, classroom;
	private final LectureDate dates;

	public Lecture(String name, String teacher, String code, String classroom, LectureDate dates) {
		this.name = name;
		this.teacher = teacher;
		this.code = code;
		this.classroom = classroom;
		this.dates = dates;
	}

	public String getName() {
		return name;
	}

	public LectureDate getDates() {
		return dates;
	}

	public LectureDate getDate() {
		return this.dates;
	}

	public String getTeacher() {
		return teacher;
	}

	public String getCode() {
		return code;
	}

	public String getClassroom() {
		return classroom;
	}

	public String getRoom() {
		return classroom;
	}

	public String getHours() {
		return dates.startHour + ":" + dates.startMinute + " " + dates.endHour + ":" + dates.endMinute;
	}

	@Override
	public String toString() {
		return "Lecture{" +
				"name='" + name + '\'' +
				", teacher='" + teacher + '\'' +
				", code='" + code + '\'' +
				", classroom='" + classroom + '\'' +
				", dates=" + dates +
				'}';
	}

	public String test() {
		return "Lecture{" +
				"name='" + name + '\'' +
				", teacher='" + teacher + '\'' +
				", code='" + code + '\'' +
				", classroom='" + classroom + '\'' +
				", dates=" + dates.test() +
				'}';
	}
}
