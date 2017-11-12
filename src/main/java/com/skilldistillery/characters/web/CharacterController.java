package com.skilldistillery.characters.web;


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
	//There is an issue where the added character gets added twice. Not sure where it's
	//popping up. Removed/changed lines in both add characters and home to no success.
	//When deleting the added character, the duplicate entry remains, but with no way to
	//remove it. 
	//**Fixed in jsp**
	@RequestMapping(path="getCharacter.do", method=RequestMethod.POST)
	public ModelAndView addCharacter(Characters characters) {
		dao.addCharacter(characters);
		ModelAndView mv = new ModelAndView();
		mv.addObject("selectCharacter", characters);
		mv.addObject("characterList", dao.getAllCharacters());
		mv.setViewName("intro");
		return mv;
	}	
	//gets all the characters from a list and displays them as the homepage
	@RequestMapping(path="home.do", method=RequestMethod.GET)
	public ModelAndView home() {
		ModelAndView mv = new ModelAndView();
		mv.addObject("characterList", dao.getAllCharacters());
		mv.setViewName("intro");
		return mv;
	}
	//removes a character from the list
	@RequestMapping("delete.do")
	public ModelAndView delete(@RequestParam("id") Integer id) {
		ModelAndView mv = new ModelAndView("deleted"); 
		Characters change = dao.getCharacterById(id);
		mv.addObject("id", change.getId());
		dao.deleteCharacter(change);
		return mv;
	}
	//is used to get to the update page
	@RequestMapping(path="update.do", params="id")
	public ModelAndView update(@RequestParam("id") Integer id) {
		ModelAndView mv = new ModelAndView("update"); 
		Characters change = dao.getCharacterById(id);
		mv.addObject("character", change);
		return mv;
	}
	//actually makes the update and returns the user to the homepage
	//issue: it is not displaying the same homepage as we left. No data is presented.
	@RequestMapping(path="updateCharacter.do", method=RequestMethod.POST)
	public ModelAndView doUpdate(Characters characters) {
		ModelAndView mv = new ModelAndView();
		dao.updateCharacter(characters);
		mv.setViewName("intro");
		return mv;
	}

}
