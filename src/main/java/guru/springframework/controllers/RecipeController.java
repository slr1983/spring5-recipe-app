package guru.springframework.controllers;

import guru.springframework.repositories.CategoryRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import guru.springframework.commands.RecipeCommand;
import guru.springframework.services.RecipeService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class RecipeController {

	private final RecipeService recipeService;
	private final CategoryRepository categoryRepository;

	public RecipeController(RecipeService recipeService, CategoryRepository categoryRepository) {
		this.recipeService = recipeService;
		this.categoryRepository = categoryRepository;
	}

	@GetMapping("/recipe/{id}/show")
	public String showById(@PathVariable String id, Model model) {
		
		log.debug("Displaying Recipe....");

		model.addAttribute("recipe", recipeService.findById(Long.valueOf(id)));
		return "recipe/show";
	}

	@GetMapping("recipe/new")
	public String newRecipe(Model model) {
		
		log.debug("Adding New Recipe....");
		model.addAttribute("recipe", new RecipeCommand());

		return "recipe/recipeform";
	}

	@GetMapping("recipe/{id}/update")
	public String updateRecipe(@PathVariable String id, Model model) {
		
		log.debug("Updating Recipe....");
		model.addAttribute("recipe", recipeService.findCommandById(Long.valueOf(id)));

		//model.addAttribute("uoms", categoryRepository.);

		return "recipe/recipeform";
	}

	@PostMapping("recipe")
	public String saveOrUpdate(@ModelAttribute RecipeCommand command) {

		log.debug("Save/Update id: " + command.getId());

		RecipeCommand savedCommand = recipeService.saveRecipeCommand(command);

		return "redirect:/recipe/" + savedCommand.getId() + "/show";
	}

	@GetMapping("recipe/{id}/delete")
	public String deleteById(@PathVariable String id) {

		log.debug("Deleting Recipe: " + id);

		recipeService.deleteById(Long.valueOf(id));
		return "redirect:/";
	}
}
