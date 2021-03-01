package com.skilldistillery.filmquery.app;

import java.sql.SQLException;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

import com.skilldistillery.filmquery.database.DatabaseAccessor;
import com.skilldistillery.filmquery.database.DatabaseAccessorObject;
import com.skilldistillery.filmquery.entities.Actor;
import com.skilldistillery.filmquery.entities.Film;

public class FilmQueryApp {

	DatabaseAccessor db = new DatabaseAccessorObject();

	public static void main(String[] args) throws SQLException {
		FilmQueryApp app = new FilmQueryApp();
		// app.test();
		app.launch();
	}

	// private void test() throws SQLException {
	// Film film = db.findFilmById(1);
	// System.out.println(film);

//	  List<Actor> act = db.findActorsByFilmId(1);
//	  System.out.print(act);
//  }

	private void launch() throws SQLException {
		Scanner input = new Scanner(System.in);

		startUserInterface(input);

		input.close();
	}

	private void startUserInterface(Scanner input) {
		boolean keepGoing = true;

		while (keepGoing) {

			System.out.println("Welcome to the Film Query App!");
			System.out.println();
			System.out.println("****** Menu ******");
			System.out.println("* 1) Look up film by it's id");
			System.out.println("* 2) Look up film by keyword search");
			System.out.println("* 3) Exit");

			switch (input.nextInt()) {
			case 1:
				userFindFilmId(input);
				break;
			case 2:
				userSearchKeyword(input);
				break;
			case 3:
				System.out.println("You have chosen to exit");
				System.out.println("GoodBye");
				keepGoing = false;
				break;
			default:
				System.out.println("That is not a valid input. Enter a number 1-3");
				break;
			}
			keepGoing = false;
		}
	}

	private void userFindFilmId(Scanner input) {
		System.out.println("What is the id of the film you are looking for?");
		int id = input.nextInt();

		try {
			Film film = db.findFilmById(id);

			if (film != null) {
				System.out.println();
				System.out.println("Here is the film matching id " + id);
				System.out.println(film);
				startUserInterface(input);
			} else {
				System.out.println("Sorry but that is not a valid id. Please try again or search");
				System.out.println("By a keyword");
				startUserInterface(input);
			}

		} catch (SQLException e) {
			System.out.println("Sorry but that is not a valid id. Please try again or search");
			System.out.println("By a keyword");
			startUserInterface(input);
		}

	}

	private void userSearchKeyword(Scanner input) {

		System.out.println("What is the keyword you would like to use?");
		String search = input.next();
		try {

			List<Film> films = db.findFilmBySearch(search);

			if (films.size() > 0) {
				System.out.println("Here are the films I found that match " + search);

				for (Film currentFilm : films) {
					System.out.println(currentFilm);

					startUserInterface(input);
				}

			} else {
				System.out.println("Sorry I could not find a match with " + search);
				startUserInterface(input);
			}

		} catch (SQLException e) {
			System.out.println("That didnt work");
			e.printStackTrace();
		}
	}

}
