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
	
//	@RequestMapping(path="home.do", method=RequestMethod.GET)
//	public String listCharacters() {
//		return null;
//	}
	
	@RequestMapping(path="intro.do", method=RequestMethod.POST)
	public ModelAndView addCharName(@RequestParam("characterName") String characterName, 
			@ModelAttribute("characterName") Set<String> name) {
		ModelAndView mv = new ModelAndView();
		//Add name/age/gender/position/backstory to map
		if(name != null) {
			characterName = characterName.trim();
			if (characterName.length() > 0) {
				name.add(characterName);
			}
		}
		mv.setViewName("intro");
		return mv;
	}
	
	@RequestMapping(path="intro.do", method=RequestMethod.POST)
	public ModelAndView addCharAge(@RequestParam("characterAge") int age, 
			@ModelAttribute("characterAge") Set<Characters> charAge) {
		ModelAndView mv = new ModelAndView();
		//Add name/age/gender/position/backstory to map
		if(age != 0) {
			charAge.add(age);
			}
		}
		mv.setViewAge("intro");
		return mv;
	}
}
