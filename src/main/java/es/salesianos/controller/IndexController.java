package es.salesianos.controller;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import es.salesianos.model.PokemonTrainer;
import es.salesianos.model.Pokemon;
import es.salesianos.model.Team;
import es.salesianos.model.AbstractPokeball;
import es.salesianos.model.AbstractTeam;
import es.salesianos.model.PokemonTrainer;

@Controller
public class IndexController {

	private static Logger log = LogManager.getLogger(IndexController.class);

	@Autowired
	private PokemonTrainer pokemonTrainer;
	@Autowired
	private Pokemon pokemon;
	@Autowired
	private AbstractTeam team;
	@Autowired
	private AbstractPokeball pokeball;
	
	public void addAllObjects(ModelAndView modelAndView) {
		modelAndView.addObject("pokemonTrainer", this.pokemonTrainer);
		modelAndView.addObject("pokemon", this.pokemon);
		modelAndView.addObject("team", this.team);
		modelAndView.addObject("pokeball", this.pokeball);
	}
	
	@GetMapping("/")
	public ModelAndView index(Model model) {
		ModelAndView modelAndView = new ModelAndView("index");
//		model.addAttribute("pokemonTrainer", new PokemonTrainer());
		addAllObjects(modelAndView);
		log.debug(modelAndView);
		return modelAndView;
	}
	
	@PostMapping("/insertTrainer")
	public ModelAndView pokemonTrainerInsert(@ModelAttribute("pokemonTrainer") PokemonTrainer pokeTrainer) {
		log.debug("pokemonTrainerInsert:" + this.pokemonTrainer.toString());		
		ModelAndView modelAndView = new ModelAndView("index");	
		addPageDataTrainer(pokeTrainer);
		addAllObjects(modelAndView);
		return modelAndView;
	}
	
	@PostMapping("/insertPokemon")
	public ModelAndView pokemonInsert(@ModelAttribute("pokemon") Pokemon poke) {
		log.debug("pokemonInsert:" + this.pokemon.toString());		
		ModelAndView modelAndView = new ModelAndView("index");
		addPageDataPokemon(poke);
		addAllObjects(modelAndView);
		return modelAndView;
	}
	
	private void addPageDataTrainer(PokemonTrainer pokemonTrainerForm) {
		if (!StringUtils.isEmpty(pokemonTrainerForm.getName())) {
			pokemonTrainer.setName(pokemonTrainerForm.getName());
		}
		
		if (!StringUtils.isEmpty(pokemonTrainerForm.isGymLeader())) {
			pokemonTrainer.setGymLeader(pokemonTrainerForm.isGymLeader());
		}
	}
	
	private void addPageDataPokemon(Pokemon pokemonForm) {
		if (!StringUtils.isEmpty(pokemonForm.getName())) {
			pokemon.setName(pokemonForm.getName());
		}
		
		if (!StringUtils.isEmpty(pokemonForm.isWild())) {
			pokemon.setWild(true);
		}
		
		if (!StringUtils.isEmpty(pokemonForm.getPower())) {
			pokemon.setPower(pokemonForm.getPower());
		}
	}
	
	@PostMapping("capturePokemon")
	public ModelAndView capturePokemon() {
		ModelAndView modelAndView = new ModelAndView("index");
		pokeball.catchPokemon(pokemon, pokemonTrainer.getTeam());
		addAllObjects(modelAndView);
		return modelAndView;
	}
	
//	@PostMapping("switchWeapon")
//	public ModelAndView switchWeapon() {
//		Weapon tmp;
//		tmp = this.pokemonTrainer.getPrimary();
//		this.pokemonTrainer.setPrimary(this.pokemonTrainer.getSecondary());
//		this.pokemonTrainer.setSecondary(tmp);
//		if (this.pokemonTrainer.getPrimary().getName() != null) {
//			System.out.println("El arma activa es " + this.pokemonTrainer.getPrimary().getName());
//		}
//		ModelAndView modelAndView = new ModelAndView("index");
//		modelAndView.addObject("person", this.pokemonTrainer);
//		return modelAndView;
//	}



}
