package com.skilldistillery.characters.data;

import java.util.List;


public interface CharactersDAO {
	
	Characters addCharacter(Characters c); //Create a character
	List<Characters> getAllCharacters(); //Retrieve a character
	Characters getCharacterById(int id);
	Characters updateCharacter(Characters c); //Update a character
	Characters deleteCharacter(Characters g); //Delete a character
	
}
