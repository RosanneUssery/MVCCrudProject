package com.skilldistillery.characters.web;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
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
	//Autowiring is where it's tying all the beans together, which allows line 41 and 
	//similar lines to work. 
	@Autowired
	CharactersDAO dao;
	
	
	
	//This should return the characters that are already in the list and display them.
	//it is not currently
	@ModelAttribute("selectCharacter")
	public Characters newCharacter() {
		return new Characters();
	}
	
	
//	@RequestMapping(path="getCharacter.do", method=RequestMethod.POST, params= {"characterName", "characterAge"})
//	public ModelAndView add(@RequestParam("characterName") String name) {
//		ModelAndView mv = new ModelAndView("intro");
//		Characters theCharacter = dao.getCharacterByName(name);
//		mv.addObject("character", theCharacter);
//		return mv;
//	}
	
//	@RequestMapping(path="home.do", method=RequestMethod.GET)
//	public String home() {
//		return "intro"; 
//	}
	
	@RequestMapping(path="getCharacter.do",
			method=RequestMethod.POST)
	public ModelAndView addCharacter(Characters characters) {
		dao.addCharacter(characters);
		ModelAndView mv = new ModelAndView();
		mv.addObject("selectCharacter", characters);
		mv.addObject("characterList", dao.getAllCharacters());
		mv.setViewName("intro");
		return mv;
	}
	
	
	@RequestMapping(path="home.do", method=RequestMethod.GET)
	public ModelAndView home() {
		ModelAndView mv = new ModelAndView();
		mv.addObject("characterList", dao.getAllCharacters());
		mv.setViewName("intro");

		return mv;
	}
	
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
	
//	@RequestMapping(path="intro.do", method=RequestMethod.POST)
//	public ModelAndView addCharAge(@RequestParam("characterAge") int age, 
//			@ModelAttribute("characterAge") Set<Characters> charAge) {
//		ModelAndView mv = new ModelAndView();
//		//Add name/age/gender/position/backstory to map
//		if(age != 0) {
//			charAge.add(age);
//			}
//		}
//		mv.setViewAge("intro");
//		return mv;
//	}
}
