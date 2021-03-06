package com.skilldistillery.characters.web;


import java.sql.SQLException;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.skilldistillery.characters.data.Characters;
import com.skilldistillery.characters.data.CharactersDAO;

@SessionAttributes("selectCharacter")
@Controller
public class CharacterController {
	// Autowiring is where it's tying all the beans together.
	@Autowired
	CharactersDAO dao;

	// This should return the characters that are already in the list and display them.
	@ModelAttribute("selectCharacter")
	public Characters newCharacter() {
		return new Characters();
	}
	
	@RequestMapping(path = "home.do")
	public ModelAndView getAllCharacters(){
		//need to set id to something
		String viewName = "intro";
		ModelAndView mv = new ModelAndView(viewName);
		List<Characters> characters= dao.getAllCharacters();
		mv.addObject("characters", characters);
		return mv;
	}
	
	@RequestMapping(path = "getCharacter.do", method = RequestMethod.GET)
	public String addCharacter(Model m) {
		Characters newCharacter = new Characters();
		m.addAttribute("character", newCharacter);
		return "insert";
	}

	@RequestMapping(path = "getCharacter.do", method = RequestMethod.POST)
	public ModelAndView doAdd(@Valid Characters id, Errors error) {
		ModelAndView mv = new ModelAndView();
		if (error.hasErrors()) {
			mv.setViewName("intro");
			return mv;
		}
		dao.addCharacter(id);
		List<Characters> d = dao.getAllCharacters();
		mv.addObject("characters", d);
		mv.setViewName("intro");
		return mv;
	}
	
	
	//is used to get to the update page
	@RequestMapping(path="update.do", params="id")
	public ModelAndView update(@RequestParam("id") Integer id) {
		ModelAndView mv = new ModelAndView(); 
		Characters change = dao.getCharacterById(id);
		mv.addObject("character", change);
		mv.setViewName("update");
		return mv;
	}
	//actually makes the update and returns the user to the homepage
	//isn't updating the character in question, likely because it's
	//not pulling the id correctly, or at all.
	@RequestMapping(path="updateCharacter.do", method=RequestMethod.POST)
	public ModelAndView doUpdate(Characters characters) {
		ModelAndView mv = new ModelAndView();
		dao.updateCharacter(characters);
		List<Characters> d = dao.getAllCharacters();
		mv.addObject("characters", d);
		mv.setViewName("intro");
		return mv;
	}

	@RequestMapping("delete.do")
	public ModelAndView delete(@RequestParam("id") Integer id) throws SQLException {
		ModelAndView mv = new ModelAndView("deleted"); 
		dao.deleteCharacter(id);
		List<Characters> d = dao.getAllCharacters();
		mv.addObject("characters", d);
		mv.setViewName("intro");
		return mv;
	}
}
