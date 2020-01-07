package com.example.mobileproject;

import java.util.Objects;

public class LectureDate implements Comparable {
	public final int day,
		startHour,
		startMinute,
		endHour,
		endMinute;

	public LectureDate(Integer day, Integer startHour, Integer startMinute, Integer endHour, Integer endMinute) throws InvalidLectureDateException {
		this.day = day;
		this.startHour = startHour; // 0 -> 23
		this.startMinute = startMinute; // 0 -> 59
		this.endHour = endHour; // 0 -> 23
		this.endMinute = endMinute; // 0 -> 59

		if (day < 1 || day > 7) {
			throw new InvalidLectureDateException("Invalid day: " + this);
		}

		if (startHour < 0 || startHour > 23) {
			throw new InvalidLectureDateException("Invalid start hour: " + this);
		}

		if (startMinute < 0 || startMinute > 59) {
			throw new InvalidLectureDateException("Invalid start minute: " + this);
		}

		if (endHour < 0 || endHour > 23) {
			throw new InvalidLectureDateException("Invalid end hour: " + this);
		}

		if (endMinute < 0 || endMinute > 59) {
			throw new InvalidLectureDateException("Invalid end minute: " + this);
		}

		if (startHour > endHour) {
			throw new InvalidLectureDateException("Invalid start and end hours: " + this);
		}

		if (startMinute > endMinute && Objects.equals(startHour, endHour)) {
			throw new InvalidLectureDateException("Invalid start and end hours: " + this);
		}
	}

	/**
	 * If the function returns 0, there is a collision between two lectures
	 */
	@Override
	public int compareTo(Object o) {
		LectureDate ld1 = (LectureDate) o;
		LectureDate ld2 = this;

		// If lectures are on different days, no need to look at their hours.
		if (ld1.day > ld2.day) {
			return 1;
		} else if (ld1.day < ld2.day) {
			return -1;
		} else {
			// If lectures are on the same days, check that their times are not colliding with each other.
			if (Objects.equals(ld1.startHour, ld2.endHour)) {
				if (ld1.startMinute > ld2.endMinute) { // Lecture 1 is later
					return 1;
				} else if (ld1.endMinute < ld2.startMinute) { // Lecture 2 is later
					return -1;
				}
			} else if (Objects.equals(ld1.endHour, ld2.startHour)) {
				if (ld1.startMinute > ld2.endMinute) { // Lecture 1 is later
					return -1;
				} else if (ld1.endMinute < ld2.startMinute) { // Lecture 2 is later
					return 1;
				}
			} else if (ld1.startHour > ld2.endHour) { // Lecture 1 is later
				return 1;
			} else if (ld1.endHour < ld2.startHour) { // Lecture 2 is later
				return -1;
			}
		}

		return 0;
	}

	@Override
	public String toString() {
        return startHour + ":"+ startMinute + " - " + endHour  + ":" + endMinute;
	}

	public String test() {
		return this.day + " => " + this.toString();
	}
}
