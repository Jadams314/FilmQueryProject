package com.skilldistillery.filmquery.entities;

import java.util.List;

public class Actor {
	private int actorId;
	private String firstName;
	private String lastName;
	private List<Film> films;

	public Actor() {
	}

	public Actor(int actorId, String firstName, String lastName) {
		super();
		this.actorId = actorId;
		this.firstName = firstName;
		this.lastName = lastName;
	}

	public int getactorId() {
		return actorId;
	}

	public void setactorId(int actorId) {
		this.actorId = actorId;
	}

	public String getfirstName() {
		return firstName;
	}

	public void setfirstName(String first_name) {
		this.firstName = first_name;
	}

	public String getlastName() {
		return lastName;
	}

	public void setlastName(String lastName) {
		this.lastName = lastName;
	}

	public List<Film> getFilms() {
		return films;
	}

	public void setFilms(List<Film> films) {
		this.films = films;
	}

	@Override
	public String toString() {
		return "Actor: " + firstName + " " + lastName;
	}

}
