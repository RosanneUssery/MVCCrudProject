package com.skilldistillery.characters.data;

import java.sql.SQLException;
import java.util.List;


public interface CharactersDAO {
	
	Characters addCharacter(Characters c); //Create a character
	List<Characters> getAllCharacters(); //Retrieve a character
	Characters getCharacterById(int id);
	Characters updateCharacter(Characters c); //Update a character
	boolean deleteCharacter(int id) throws SQLException; //Delete a character
	
}
