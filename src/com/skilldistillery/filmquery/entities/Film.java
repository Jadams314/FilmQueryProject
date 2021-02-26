package com.skilldistillery.filmquery.entities;

import java.util.List;

public class Film {
	private int id;
	private String title;
	private String description;
	private String releaseYear;
	private int langaugeId;
	private double rentalDuration;
	private double rentalRate;
	private Integer length;
	double replacementCost;
	private String rating;
	private String specialFeatures;
	private List<Actor> Actor;
	
	public Film() {}


	public Film(int id, String title, String description, String releaseYear, int langaugeId, double rentalDuration,
			double rentalRate, Integer length, double replacementCost, String rating, String specialFeatures) {
		super();
		this.id = id;
		this.title = title;
		this.description = description;
		this.releaseYear = releaseYear;
		this.langaugeId = langaugeId;
		this.rentalDuration = rentalDuration;
		this.rentalRate = rentalRate;
		this.length = length;
		this.replacementCost = replacementCost;
		this.rating = rating;
		this.specialFeatures = specialFeatures;
	}


	public List<Actor> getActor(int filmId) {
		return Actor;
	}


	public void setActor(List<Actor> actor) {
		Actor = actor;
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getTitle() {
		return title;
	}


	public void setTitle(String title) {
		this.title = title;
	}


	public String getDescription() {
		return description;
	}


	public void setDescription(String description) {
		this.description = description;
	}


	public String getReleaseYear() {
		return releaseYear;
	}


	public void setReleaseYear(String releaseYear) {
		this.releaseYear = releaseYear;
	}


	public int getLangaugeId() {
		return langaugeId;
	}


	public void setLangaugeId(int langaugeId) {
		this.langaugeId = langaugeId;
	}


	public double getRentalDuration() {
		return rentalDuration;
	}


	public void setRentalDuration(double rentalDuration) {
		this.rentalDuration = rentalDuration;
	}


	public double getRentalRate() {
		return rentalRate;
	}


	public void setRentalRate(double rentalRate) {
		this.rentalRate = rentalRate;
	}


	public Integer getLength() {
		return length;
	}


	public void setLength(Integer length) {
		this.length = length;
	}


	public double getReplacementCost() {
		return replacementCost;
	}


	public void setReplacementCost(double replacementCost) {
		this.replacementCost = replacementCost;
	}


	public String getRating() {
		return rating;
	}


	public void setRating(String rating) {
		this.rating = rating;
	}


	public String getSpecialFeatures() {
		return specialFeatures;
	}


	public void setSpecialFeatures(String specialFeatures) {
		this.specialFeatures = specialFeatures;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((releaseYear == null) ? 0 : releaseYear.hashCode());
		return result;
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!(obj instanceof Film))
			return false;
		Film other = (Film) obj;
		if (releaseYear == null) {
			if (other.releaseYear != null)
				return false;
		} else if (!releaseYear.equals(other.releaseYear))
			return false;
		return true;
	}


	@Override
	public String toString() {
		return "Film id : " + id + "| Title :" + title + "| description :" + description + "| Release Year :" + releaseYear
				+ "| Langauge Id :" + langaugeId + "| Rental Duration :" + rentalDuration + "| Rental Rate :"
				+ rentalRate + "| Length :" + length + "| Replacement Cost :" + replacementCost + "| Rating :" + rating
				+ "| Special Features :" + specialFeatures + " |";
	}
	
	
}
