package com.example.mobileproject;

public class Lecture {
	public String getName() {
		return name;
	}

	public String getDates() {
		return dates.toString();
	}

	private final String name, teacher, code, classroom;
	private final LectureDate dates;

	public Lecture(String name, String teacher, String code, String classroom, LectureDate dates) {
		this.name = name;
		this.teacher = teacher;
		this.code = code;
		this.classroom = classroom;
		this.dates = dates;
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
}
