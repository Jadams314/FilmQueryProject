package com.skilldistillery.filmquery.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.skilldistillery.filmquery.entities.Actor;
import com.skilldistillery.filmquery.entities.Film;

public class DatabaseAccessorObject implements DatabaseAccessor {
	private static final String URL = "jdbc:mysql://localhost:3306/sdvid?useSSL=false";
	private static final String user = "student";
	private static final String pass = "student";

	DatabaseAccessorObject dbo = new DatabaseAccessorObject();
	
	@Override
	public Film findFilmById(int filmId) throws SQLException {
		Film film = null;
		
		Connection conn = DriverManager.getConnection(URL, user, pass);
		
		String sql = " SELECT * FROM film WHERE id = ? ";
		PreparedStatement stmt = conn.prepareStatement(sql);
		
		stmt.setInt(1, filmId);
		ResultSet filmResult = stmt.executeQuery();
		
		if (filmResult.next()) {
			film = new Film();
			
			film.setId(filmResult.getInt("id"));
			film.setTitle(filmResult.getString("title"));
			film.setDescription(filmResult.getString("description"));
			film.setReleaseYear(filmResult.getString("release_year"));
			film.setLangaugeId(filmResult.getInt("language_id"));
			film.setRentalDuration(filmResult.getDouble("rental_duration"));
			film.setRentalRate(filmResult.getDouble("rental_rate"));
			film.setLength(filmResult.getInt("length"));
			film.setReplacementCost(filmResult.getDouble("replacement_cost"));
			film.setRating(filmResult.getString("rating"));
			film.setSpecialFeatures(filmResult.getString("special_features"));
		}
		
		
		filmResult.close();
		stmt.close();
		conn.close();
		
		return film;
		
	}

	@Override
	public Actor findActorById(int actorId) throws SQLException {
		Actor actor = null;

		Connection conn = DriverManager.getConnection(URL, user, pass);

		String sql = "SELECT * FROM actor WHERE id = ?";
		PreparedStatement stmt = conn.prepareStatement(sql);

		stmt.setInt(1, actorId);
		ResultSet actorResult = stmt.executeQuery();

		if (actorResult.next()) {
			actor = new Actor(); // Create the object
			// Here is our mapping of query columns to our object fields:
			actor.setactorId(actorResult.getInt("id"));
			actor.setfirstName(actorResult.getString("first_name"));
			actor.setlastName(actorResult.getString("last_name"));
		}
		
		actorResult.close();
		stmt.close();
		conn.close();
		return actor;
	}

	@Override
	public List<Actor> findActorsByFilmId(int filmId) throws SQLException {
		List<Actor> actor = new ArrayList<>();
		
		Connection conn = DriverManager.getConnection(URL, user, pass);
		
		String sql = "SELECT actor.first_name, actor.last_name "
				+ "FROM actor JOIN film_actor ON actor.id = film_actor.actor_id "
				+ "JOIN film ON film_actor.film_id = film.id "
				+ "WHERE film.id = ?";
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setInt(1, filmId);
		ResultSet actorFilmResult = stmt.executeQuery();
		
			if (actorFilmResult.next()) {
					actor.add((Actor) actorFilmResult);
			}
			actorFilmResult.close();
			stmt.close();
			conn.close();
		return actor;
	}

}
