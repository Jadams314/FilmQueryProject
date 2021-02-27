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

	
	@Override
	public List<Film> findFilmBySearch(String search) throws SQLException {
		List<Film> filmList = new ArrayList<>();
		Film film = null;
		
		Connection conn = DriverManager.getConnection(URL, user, pass);

		String sql = "SELECT film.id, film.title AS Title, "
				+ "film.description AS Description, "
				+ "film.release_year AS Year, "
				+ "film.rating AS Rating, "
				+ "language.name AS Language "
				+ "FROM film JOIN language ON film.language_id = language.id\n"
				+ "WHERE film.title LIKE ? OR film.description LIKE ?";
		
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setString(1, "%" + search + "%");
		stmt.setString(2, "%" + search + "%");
		ResultSet filmSearchResult = stmt.executeQuery();

		while (filmSearchResult.next()) {
			film = new Film();
			
			film.setTitle(filmSearchResult.getString("film.title"));
			film.setDescription(filmSearchResult.getString("film.description"));
			film.setReleaseYear(filmSearchResult.getString("film.year"));
			film.setRating(filmSearchResult.getString("film.rating"));
			film.setLangaugeId(filmSearchResult.getString("Language"));
			film.setActors(findActorsByFilmId(filmSearchResult.getInt("film.id")));
			
			
			filmList.add(film);
			
		}
		
		filmSearchResult.close();
		stmt.close();
		conn.close();
		return filmList;
	}
	
	
	@Override
	public Film findFilmById(int filmId) throws SQLException {
		Film film = null;
		Actor actor = null;
		List<Actor> actorList = new ArrayList<>();
		
		Connection conn = DriverManager.getConnection(URL, user, pass);

		String sql = " SELECT film.title AS Title, "
				+ "film.description AS Description, "
				+ "film.release_year AS Year, "
				+ "film.rating AS Rating, "
				+ "language.name AS Language, "
				+ "actor.first_name, actor.last_name "
				+ "FROM film JOIN language ON film.language_id = language.id "
				+ "JOIN film_actor ON film.id = film_actor.film_id "
				+ "JOIN actor ON actor.id = film_actor.actor_id "
				+ "WHERE film.id = ?; ";
		PreparedStatement stmt = conn.prepareStatement(sql);

		stmt.setInt(1, filmId);
		ResultSet filmResult = stmt.executeQuery();

		while (filmResult.next()) {
			film = new Film();
			actor = new Actor();
			
			film.setTitle(filmResult.getString("film.title"));
			film.setDescription(filmResult.getString("film.description"));
			film.setReleaseYear(filmResult.getString("film.year"));
			film.setRating(filmResult.getString("film.rating"));
			film.setLangaugeId(filmResult.getString("Language"));
		
			film.setActors(findActorsByFilmId(filmId));
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

		List<Actor> actorList = new ArrayList<>();
		Actor actor = null;

		Connection conn = DriverManager.getConnection(URL, user, pass);

		String sql = "SELECT actor.first_name, actor.last_name "
				+ "FROM actor JOIN film_actor ON actor.id = film_actor.actor_id "
				+ "JOIN film ON film_actor.film_id = film.id " + "WHERE film.id = ?";
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setInt(1, filmId);
		ResultSet actorFilmResult = stmt.executeQuery();

		while (actorFilmResult.next()) {
			actor = new Actor();
			actor.setfirstName(actorFilmResult.getString("first_name"));
			actor.setlastName(actorFilmResult.getString("last_name"));
			actorList.add(actor);
		}
		actorFilmResult.close();
		stmt.close();
		conn.close();
		return actorList;
	}

}
