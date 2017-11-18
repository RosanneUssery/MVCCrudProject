package com.skilldistillery.characters.data;

import java.sql.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;


@Repository
public class CharacterDAOInMemoryImpl implements CharactersDAO{

	private static String url = "jdbc:mysql://localhost:3306/sdvid";
	private String user = "student";
	private String pass = "student";

	public CharacterDAOInMemoryImpl() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			System.err.println("Error loading MySQL Driver!!!");
		}
	}

	@Override
	public Characters getCharacterById(int id) {
		Characters character = null;
		try {
			Connection conn = DriverManager.getConnection(url, user, pass);

			String sql = "SELECT id, name, age, gender, role, backstory FROM characters WHERE id = ?";
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setInt(1, id);
			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				id = rs.getInt(1);
				String name = rs.getString(2);
				int age = rs.getInt(3);
				//do i do gender as an int or as a string, given the double tables? 
				//Do i do a nested table?
				//need to do a join***
				int gender = rs.getInt(4);
				int role = rs.getInt(5);
				String backstory = rs.getString(6);
				character = new Characters(id, name, age, gender, role, backstory);
			}
			rs.close();
			stmt.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return character;
	}

	@Override
	public Characters addCharacter(Characters newCharacter) {
		Characters returnCharacter = null;
		String sql = "INSERT INTO characters (name, age, gender, role, backstory) "
				+ " VALUES (?, ?, ?, ?, ?)";
		Connection conn = null;
		try {
			conn = DriverManager.getConnection(url, user, pass);
			conn.setAutoCommit(false);
			PreparedStatement st = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			st.setString(1, newCharacter.getName());
			st.setInt(2, newCharacter.getAge());
			st.setString(3, newCharacter.getGender());
			st.setString(4, newCharacter.getRole());
			st.setString(5, newCharacter.getBackstory());

			int uc = st.executeUpdate();
			if (uc == 1) {
				ResultSet keys = st.getGeneratedKeys();
				if (keys.next()) {
					int filmId = keys.getInt(1);
					conn.commit();
					newCharacter.setId(filmId);
					
				}
			}
			st.close();
			conn.close();
			returnCharacter = newCharacter;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return returnCharacter;
	}

	@Override
	public boolean deleteCharacter(Characters characters) throws SQLException {
		Connection conn = null;
		try {
			conn = DriverManager.getConnection(url, user, pass);
			String sql = "DELETE FROM characters WHERE id= ?";
			conn.setAutoCommit(false);
			PreparedStatement st = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			st.setInt(1, characters.getId());
			st.executeUpdate();
			conn.commit();
			st.close();
		} catch (SQLException e) {
			e.printStackTrace();
			if (conn != null) {
				try {
					conn.rollback();
				} catch (SQLException e2) {
					System.err.println("Error trying to rollback");
				}
			}
			return false;
		}
		conn.close();
		return true;

	}

	@Override
	public Characters updateCharacter(Characters newCharacter) {
		Characters returnCharacter = null;
		String sql = "UPDATE characters SET name = ?, " + " age = ?, " + " gender = ?, " + " language_id = ?, "
				+ " rental_duration = ?, " + " rental_rate = ?, " + " length = ?, " + " replacement_cost = ?, "
				+ " rating = ?, " + " special_features = ?" + " WHERE id = ?";
		Connection conn = null;
		try {
			conn = DriverManager.getConnection(url, user, pass);
			conn.setAutoCommit(false);
			PreparedStatement st = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			st.setString(1, newCharacter.getName());
			st.setInt(2, newCharacter.getAge());
			st.setString(3, newCharacter.getGender());
			st.setString(4, newCharacter.getRole());
			st.setString(5, newCharacter.getBackstory());
			// trying to get it to add to the list, so far no luck
			int uc = st.executeUpdate();
			if (uc == 1) {
				ResultSet keys = st.getGeneratedKeys();
				if (keys.next()) {
					int filmId = keys.getInt(1);
					newCharacter.setId(filmId);
				}
			}
			conn.commit();
			st.close();
			conn.close();
			returnCharacter = newCharacter;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return returnCharacter;
	}

	@Override
	public List<Characters> getAllCharacters() {
		// TODO Auto-generated method stub
		return null;
	}

}

