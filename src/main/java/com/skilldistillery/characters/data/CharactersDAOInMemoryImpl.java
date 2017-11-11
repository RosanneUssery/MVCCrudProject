package com.skilldistillery.characters.data;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;


@Primary
@Repository
public class CharactersDAOInMemoryImpl implements CharactersDAO {

	Map<String, Characters> characters;
	public CharactersDAOInMemoryImpl() {
		characters = new TreeMap<>();
		loadExampleCharacters();
	}

	private void loadExampleCharacters()  {
		characters.put("Jon", new Characters("Jon", 33, "agender", "love interest", "fell in love with the main character three days before the world ended"));
		characters.put("Cecily", new Characters("Cecily", 42, "female", "sidekick", "likes blowing up bad guys' airships and baking cakes"));
		characters.put("Morgan", new Characters("Morgan", 36, "female", "villain", "only wants to do to the world what it has done to her"));
		characters.put("Alice", new Characters("Alice", 35, "female", "protagonist", "hates being a protagonist, but will do anything to stop Morgan"));
	}
	
	@Override
	public Characters addCharacter(Characters c) {
		return characters.put(c.getName(), c);
	}

	@Override
	public List<Characters> getAllCharacters() {
		return new ArrayList<Characters>(characters.values());
		
	}

	@Override
	public Characters getCharacterByName(String name) {
		return characters.get(name);
	}

	@Override
	public Characters updateCharacter(Characters c) {
		return characters.put(c.getName(), c);
	}

	@Override
	public Characters deleteCharacter(Characters c) {
		return characters.remove(c.getName());
	}

}
