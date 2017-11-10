package com.skilldistillery.characters.web;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.skilldistillery.characters.data.Characters;
import com.skilldistillery.characters.data.CharactersDAO;

@SessionAttributes
@Controller
public class CharacterController {
	
	@Autowired
	CharactersDAO dao;
	
	@ModelAttribute("selectCharacter")
	public Characters newCharacter() {
		return new Characters();
	}
	@RequestMapping(path="getCharacter.do", method=RequestMethod.POST, params="characterName")
	public ModelAndView getCharacterName(@RequestParam("characterName") String name) {
		ModelAndView mv = new ModelAndView("intro");
		Characters newChar = dao.getCharacterByName(name);
		mv.addObject("characterName", newChar);
		return mv;
	}
	@RequestMapping(path="home.do", method=RequestMethod.GET)
	public String home() {
		return "intro"; 
	}
	
	@RequestMapping(path="home.do", method=RequestMethod.GET)
	public String listCharacters() {
		return null;
	}
	
	@RequestMapping(path="intro.do", method=RequestMethod.POST)
	public ModelAndView handleSubmit(@RequestParam("characterName") String characterName, 
			@ModelAttribute("characterName") Set<String> characterInfo) {
		ModelAndView mv = new ModelAndView();
		//Use name generator
		if(characterInfo != null) {
			characterName = characterName.trim();
			if (name.length() > 0) {
				StringBuilder translatedName = new StringBuilder();
				//I have a name. Is it first and last?
				String[] tokens = name.split("\\s+");
				if (tokens.length > 1) {
					//first and last
					String first = generator.getTranslatedFirstName(tokens[0]);
					String last = generator.getTranslatedLastName(tokens[1]);
					translatedName.append(first).append(" ").append(last);
				} else {
					//just first
					String first = generator.getTranslatedFirstName(tokens[0]);
					String last = generator.getTranslatedLastName(tokens[0]);
					translatedName.append(first).append(" ").append(last);
				}
				String tn = translatedName.toString();
				mv.addObject("translatedName", tn);
				namesList.add(tn);
				mv.addObject("mostRecentName", tn);
			}
		}
		mv.setViewName("WEB-INF/index.jsp");
		return mv;
	}
}
