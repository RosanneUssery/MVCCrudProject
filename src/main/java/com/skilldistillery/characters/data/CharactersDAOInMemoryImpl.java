//package com.skilldistillery.characters.data;
//
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Map;
//import java.util.TreeMap;
//
//import org.springframework.context.annotation.Primary;
//import org.springframework.stereotype.Repository;
//
//
//
//public class CharactersDAOInMemoryImpl implements CharactersDAO {
//
//	Map<Integer, Characters> characters;
//	
//	private int id = 1;
//	public CharactersDAOInMemoryImpl() {
//		characters = new TreeMap<>();
//		loadExampleCharacters();
//	}
//
//	private void loadExampleCharacters()  {
//
//
//		
//		characters.put(id, new Characters(id++, "Jon", 33, "agender", "love interest", "fell in love with the main character three days before the world ended"));
//		characters.put(id, new Characters(id++, "Cecily", 42, "female", "sidekick", "likes blowing up bad guys' airships and baking cakes"));
//		characters.put(id, new Characters(id++, "Morgan", 36, "female", "villain", "only wants to do to the world what it has done to her"));
//		characters.put(id, new Characters(id++, "Alice", 35, "female", "protagonist", "hates being a protagonist, but will do anything to stop Morgan"));
//	}
//	
//	@Override
//	public Characters addCharacter(Characters c) {
//		c.setId(id++);
//		return characters.put(c.getId(), c);
//	}
//
//	@Override
//	public List<Characters> getAllCharacters() {
//		return new ArrayList<Characters>(characters.values());
//		
//	}
//
//	@Override
//	public Characters getCharacterById(int id) {
//		return characters.get(id);
//	}
//
//	@Override
//	public Characters updateCharacter(Characters c) {
//		return characters.put(c.getId(), c);
//	}
//
//	@Override
//	public Characters deleteCharacter(Characters c) {
//		return characters.remove(c.getId());
//	}
//
//}
