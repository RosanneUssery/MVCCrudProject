package com.skilldistillery.characters.data;

import java.sql.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

@Primary
@Repository
public class CharacterDAOInMemoryImpl implements CharactersDAO{

	private static String url = "jdbc:mysql://localhost:3306/characterDB";
	private String user = "author";
	private String pass = "author";
//changed string to id in gender/role to get the errors to go away, not sure if it works as a fix
	private String getCharacterGenderIdByName(String id) {
		try {
			Connection conn = DriverManager.getConnection(url, user, pass);

			String sql = "SELECT gender FROM gender WHERE id = ?";
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1, id);
			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				id = rs.getString(1);
			}
			rs.close();
			stmt.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		//SELECT id FROM pokemon type WHERE name =?
		//statement.setString(1, typeName);
		return id;
		
	}
	
	private String getCharacterRoleIdByName(String id) {
		try {
			Connection conn = DriverManager.getConnection(url, user, pass);

			String sql = "SELECT role FROM role WHERE id = ?";
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1, id);
			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				id = rs.getString(1);
			}
			rs.close();
			stmt.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		//SELECT id FROM pokemon type WHERE name =?
		//statement.setString(1, typeName);
		return id;
	}
	
	
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

			String sql = "SELECT id, char_name, age, gender_id, role_id, backstory FROM characters; WHERE id = ?";
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setInt(1, id);
			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				id = rs.getInt(1);
				String name = rs.getString(2);
				int age = rs.getInt(3);
				String gender = rs.getString(4);
				String role = rs.getString(5);
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
		String sql = "INSERT INTO characters (char_name, age, gender_id, role_id, backstory) "
				+ " VALUES (?, ?, ?, ?, ?)";
		Connection conn = null;
		try {
			conn = DriverManager.getConnection(url, user, pass);
			conn.setAutoCommit(false);
			PreparedStatement st = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			st.setString(1, newCharacter.getName());
			st.setInt(2, newCharacter.getAge());
			st.setString(3, this.getCharacterGenderIdByName(newCharacter.getGender()));
			st.setString(4, this.getCharacterRoleIdByName(newCharacter.getRole()));
//			st.setString(3, newCharacter.getGender()); //need id number for gender
//			st.setString(4, newCharacter.getRole());	//need id number for role -- Use the top method to pull ids
			
			//sytring sql Insert into pokemon (id name type id
			//stmt .setInt(3, this.getPokemonTypeIdByName(p.getType())
			st.setString(5, newCharacter.getBackstory());

			int uc = st.executeUpdate();
			if (uc == 1) {
				ResultSet keys = st.getGeneratedKeys();
				if (keys.next()) {
					int charId = keys.getInt(1);
					conn.commit();
					newCharacter.setId(charId);
					
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
		String sql = "UPDATE characters SET char_name = ?, " + " age = ?, " + " gender_id = ?, " + " role_id = ?, "
				+ " backstory = ?, "
				+ " WHERE id = ?";
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
					int charId = keys.getInt(1);
					newCharacter.setId(charId);
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
	public List<Characters> getAllCharacters(int id) {
		
		List <Characters> list = new ArrayList<>();
		
		
		Characters character = null;
		try {
			Connection conn = DriverManager.getConnection(url, user, pass);

			String sql = "SELECT id, char_name, age, gender_id, role_id, backstory FROM characters; WHERE id = ?";
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setInt(1, id);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				id = rs.getInt(1);
				String name = rs.getString(2);
				int age = rs.getInt(3);
				String gender = rs.getString(4);
				String role = rs.getString(5);
				String backstory = rs.getString(6);
				character = new Characters(id, name, age, gender, role, backstory);
				list.add(character);
			}
			rs.close();
			stmt.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

}

