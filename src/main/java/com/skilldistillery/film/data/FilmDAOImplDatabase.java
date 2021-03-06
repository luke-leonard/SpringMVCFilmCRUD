package com.skilldistillery.film.data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import com.skilldistillery.film.entities.Actor;
import com.skilldistillery.film.entities.Category;
import com.skilldistillery.film.entities.Film;

@Primary
@Component
public class FilmDAOImplDatabase implements FilmDAO {
	private static final String url = "jdbc:mysql://localhost:3306/sdvid?useSSL=false";
	private static final String user = "student";
	private static final String pass = "student";


	static {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	private class QueryRunner implements AutoCloseable {
		private Connection conn;
		private PreparedStatement pstmt;
		private ResultSet rs;
		private boolean successful;

		public QueryRunner() {
			startTansaction();
			successful = true;
		}

		public void startTansaction() {
			try {
				conn = DriverManager.getConnection(url, user, pass);
				conn.setAutoCommit(false);
			} catch (Exception e) {
				failTransaction();
				System.err.println(e);
			}
		}

		public ResultSet runSelectQuery(String sql, String... args) {
			try {
				pstmt = conn.prepareStatement(sql);
				for (int i = 0; i < args.length; i++) {
					try {
						int arg = Integer.parseInt(args[i]);
						pstmt.setInt(i + 1, arg);
					}catch(NumberFormatException e) {
						pstmt.setString(i + 1, args[i]);
					}
				}
				rs = pstmt.executeQuery();
			} catch (SQLException e) {
				failTransaction();
				System.err.println(e);
			}
			return rs;
		}

		public int runInsertQuery(String sql, String... args) {
			try {
				pstmt = conn.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
				for (int i = 0; i < args.length; i++) {
					pstmt.setString(i + 1, args[i]);
				}
				int updateCount = pstmt.executeUpdate();
				if(updateCount != 1) 
				{
					failTransaction();
					return -1;
				}
				
				ResultSet rs = pstmt.getGeneratedKeys();
				int generatedKey = -1;
				if(rs.next()) {
					generatedKey = rs.getInt(1);
				} 
				else {
					failTransaction();
					return -1;
				}
				return generatedKey;
				
			} catch (SQLException e) {
				failTransaction();
				System.err.println(e);
			}
			return -1;
		}
		public boolean runUpdateQuery(String sql, String... args) {
			try {
				pstmt = conn.prepareStatement(sql);
				for (int i = 0; i < args.length; i++) {
					pstmt.setString(i + 1, args[i]);
				}
				int updateCount = pstmt.executeUpdate();
				if(updateCount != 1) 
				{
					failTransaction();
					return false;
				}else {
					return true;	
				}
				
			} catch (SQLException e) {
				failTransaction();
				System.err.println(e);
			}
			return false;
		}
		public boolean runDeleteQuery(String sql, String... args) {
			try {
				pstmt = conn.prepareStatement(sql);
				for (int i = 0; i < args.length; i++) {
					pstmt.setString(i + 1, args[i]);
				}
				int updateCount = pstmt.executeUpdate();
				if(updateCount != 1) 
				{
					failTransaction();
					return false;
				}else {
					return true;	
				}
				
			} catch (SQLException e) {
				failTransaction();
				System.err.println(e);
			}
			return false;
		}

		public void failTransaction() {
			successful = false;
		}

		@Override
		public void close() {
			try {
				if (successful) {
					conn.commit();
					System.out.println("Commited Sucessfully");
				} else {
					conn.rollback();
					System.out.println("Rolled Back Sucessfully");
				}
				if (pstmt != null) {
					pstmt.close();
				}
				conn.close();
			} catch (Exception e) {
				System.err.println(e);
			}
		}

	}

	@Override
	public Film findFilmById(int filmId) {
		String sql = "SELECT film.id, film.title, film.description, film.release_year,"
				+ " film.language_id, film.rental_duration, " + "film.rental_rate, film.length, film.replacement_cost,"
				+ " film.rating, film.special_features, language.name "
				+ "FROM film JOIN language ON language.id = film.language_id " + "WHERE film.id = ?";
		try ( // Dont Wrap
				QueryRunner qr = new QueryRunner(); // Dont Wrap
				ResultSet rs = qr.runSelectQuery(sql, String.valueOf(filmId));// Dont Wrap
		) {
			if (rs.next()) {
				return filmFromResultSet(rs);
			} else {
				return null;
			}
		} catch (SQLException e) {
			System.err.println(e);
		}
		return null;
	}

	@Override
	public Actor findActorById(int actorId) {
		String sql = "SELECT id, first_name, last_name " + "FROM actor " + "WHERE actor.id = ?";
		try ( // Dont Wrap
				QueryRunner qr = new QueryRunner(); // Dont Wrap
				ResultSet rs = qr.runSelectQuery(sql, String.valueOf(actorId));// Dont Wrap
		) {
			if (rs.next()) {
				return actorFromResultSet(rs);
			} else {
				return null;
			}
		} catch (SQLException e) {
			System.err.println(e);
		}
		return null;
	}

	@Override
	public List<Actor> findActorsByFilmId(int filmId) {
		List<Actor> actorsInFilm = new ArrayList<>();
		String sql = "SELECT actor.id, actor.first_name, actor.last_name "
				+ "FROM actor JOIN film_actor ON actor.id = film_actor.actor_id " + "WHERE film_actor.film_id = ?";
		try ( // Dont Wrap
				QueryRunner qr = new QueryRunner(); // Dont Wrap
				ResultSet rs = qr.runSelectQuery(sql, String.valueOf(filmId));// Dont Wrap
		) {

			while (rs.next()) {
				actorsInFilm.add(actorFromResultSet(rs));
			}
			return actorsInFilm;
		} catch (SQLException e) {
			System.err.println(e);
		}
		return null;
	}
	
	@Override
	public List<Category> findCategoryByFilmId(int filmId) {
		List<Category> categoryInFilm = new ArrayList<>();
		String sql = "SELECT category.id, category.name "
				+ "FROM category JOIN film_category ON category.id = film_category.category_id " + "WHERE film_category.film_id = ?";
		try ( // Dont Wrap
				QueryRunner qr = new QueryRunner(); // Dont Wrap
				ResultSet rs = qr.runSelectQuery(sql, String.valueOf(filmId));// Dont Wrap
		) {

			while (rs.next()) {
				categoryInFilm.add(categoryFromResultSet(rs));
			}
			return categoryInFilm;
		} catch (SQLException e) {
			System.err.println(e);
		}
		return null;
	}

	@Override
	public List<Film> findFilmByKeyWord(String keyword) {
		List<Film> possibleFilms = new ArrayList<>();
		String sql = "SELECT film.id, film.title, film.description, film.release_year, "
				+ "film.language_id, film.rental_duration, " + "film.rental_rate, film.length, film.replacement_cost, "
				+ "film.rating, film.special_features, language.name "
				+ "FROM film JOIN language ON language.id = film.language_id "
				+ "WHERE film.title LIKE ? OR film.description LIKE ?";
		String input = "%" + keyword + "%";

		try ( // Dont Wrap
				QueryRunner qr = new QueryRunner(); // Dont Wrap
				ResultSet rs = qr.runSelectQuery(sql, input, input);// Dont Wrap
		) {
			while (rs.next()) {
				possibleFilms.add(filmFromResultSet(rs));
			}
			return possibleFilms;
		} catch (SQLException e) {
			System.err.println(e);
		}
		return null;
	}

	@Override
	public Film createFilm(Film film) {
		String sql = "INSERT INTO " + "film(title,description,release_year,language_id,"
				+ "rental_duration,rental_rate,length,replacement_cost,"
				+ "rating,special_features) Values (?,?,?,?,?,?,?,?,?,?)";
		try (QueryRunner qr = new QueryRunner()) {
			int generatedId = qr.runInsertQuery(sql,
					String.valueOf(film.getTitle()),
					String.valueOf(film.getDescription()),
					String.valueOf(film.getReleaseYear()),
					String.valueOf(film.getLanguageId()),
					String.valueOf(film.getRentalDuration()),
					String.valueOf(film.getRentalRate()),
					String.valueOf(film.getLength()),
					String.valueOf(film.getReplacementCost()),
					String.valueOf(film.getRating()),
					String.valueOf(film.getSpecialFeatures()));
			if(generatedId > 0) {
				film.setId(generatedId);
				return film;
			}
			else {
				qr.failTransaction();
			}
		} catch (Exception e) {
			System.err.println(e);
		}
		return null;
	}

	@Override
	public Film updateFilm(Film film) {
		String sql = "UPDATE film SET title = ?,"
				+ "description = ?,"
				+ "release_year = ?,"
				+ "language_id = ?,"
				+ "rental_duration = ?,"
				+ "rental_rate = ?,"
				+ "length = ?,"
				+ "replacement_cost = ?,"
				+ "rating = ?,"
				+ "special_features = ?"
				+ "WHERE id = ?";
		try (QueryRunner qr = new QueryRunner()) {
			boolean didWork = qr.runUpdateQuery(sql,
					String.valueOf(film.getTitle()),
					String.valueOf(film.getDescription()),
					String.valueOf(film.getReleaseYear()),
					String.valueOf(film.getLanguageId()),
					String.valueOf(film.getRentalDuration()),
					String.valueOf(film.getRentalRate()),
					String.valueOf(film.getLength()),
					String.valueOf(film.getReplacementCost()),
					String.valueOf(film.getRating()),
					String.valueOf(film.getSpecialFeatures()),
					String.valueOf(film.getId())
					);
			if(didWork) {
				return film;
			}
			else {
				qr.failTransaction();
			}
		} catch (Exception e) {
			System.err.println(e);
		}
		return null;
	}

	@Override
	public boolean deleteFilm(Film film) {
		String sql = "DELETE FROM film "
				+ "WHERE id = ?";
		try (QueryRunner qr = new QueryRunner()) {
			boolean didWork = qr.runUpdateQuery(sql,
					String.valueOf(film.getId())
					);
			if(didWork) {
				return true;
			}
			else {
				qr.failTransaction();
			}
		} catch (Exception e) {
			System.err.println(e);
		}
		return false;
	}

	private Film filmFromResultSet(ResultSet rs) {
		Film film = null;
		try {
			film = new Film(// Dont Wrap
					rs.getInt("film.id"), // Dont Wrap
					rs.getString("film.title"), // Dont Wrap
					rs.getString("film.description"), // Dont Wrap
					rs.getInt("film.release_year"), // Dont Wrap
					rs.getInt("film.language_id"), // Dont Wrap
					rs.getInt("film.rental_duration"), // Dont Wrap
					rs.getInt("film.rental_rate"), // Dont Wrap
					rs.getInt("film.length"), // Dont Wrap
					rs.getDouble("film.replacement_cost"), // Dont Wrap
					rs.getString("film.rating"), // Dont Wrap
					rs.getString("film.special_features"), // Dont Wrap
					rs.getString("language.name"));// Dont Wrap
		} catch (SQLException e) {
			System.err.println(e);
			return null;
		}
		return film;
	}

	private Actor actorFromResultSet(ResultSet rs) {
		Actor actor = null;
		try {
			actor = new Actor( // Dont Wrap
					rs.getInt("actor.id"), // Dont Wrap
					rs.getString("actor.first_name"), // Dont Wrap
					rs.getString("actor.last_name"));// Dont Wrap
		} catch (SQLException e) {
			System.err.println(e);
			return null;
		}
		return actor;
	}
	
	private Category categoryFromResultSet(ResultSet rs) {
		Category category = null;
		try {
			category = new Category( // Dont Wrap
					rs.getInt("category.id"), // Dont Wrap
					rs.getString("category.name"));// Dont Wrap
		} catch (SQLException e) {
			System.err.println(e);
			return null;
		}
		return category;
	}

}
