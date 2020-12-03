package guru.springframework.controllers;

import java.util.Set;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import guru.springframework.domain.Recipe;
import guru.springframework.services.RecipeService;

@Controller
public class IndexController {

	private final RecipeService recipeService;

	public IndexController(RecipeService recipeService) {

		this.recipeService = recipeService;
	}

	@RequestMapping({ "/", "", "/index", "/index.html" })
	public String showIndex(Model model) {

		Set<Recipe> allRecipesFromDB = recipeService.getAllRecipies();

		model.addAttribute("recipes", allRecipesFromDB);

		return "index";
	}

}