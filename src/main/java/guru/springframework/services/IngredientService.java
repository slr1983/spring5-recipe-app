package guru.springframework.services;

import guru.springframework.commands.IngredientCommand;
import guru.springframework.commands.RecipeCommand;
import guru.springframework.domain.Ingredient;
import guru.springframework.domain.Recipe;

import java.util.Set;

public interface IngredientService {

	IngredientCommand saveIngredientCommand(IngredientCommand command);

	IngredientCommand findByRecipeIdAndIngredientId(Long recipeId, Long ingredientId);
	
	public void deleteById(Long recipeId, Long idToDelete);
}
