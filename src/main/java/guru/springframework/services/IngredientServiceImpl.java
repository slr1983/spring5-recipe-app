package guru.springframework.services;

import java.util.Optional;

import org.springframework.stereotype.Service;

import guru.springframework.commands.IngredientCommand;
import guru.springframework.converters.IngredientCommandToIngredient;
import guru.springframework.converters.IngredientToIngredientCommand;
import guru.springframework.domain.Ingredient;
import guru.springframework.domain.Recipe;
import guru.springframework.repositories.IngredientRepository;
import guru.springframework.repositories.RecipeRepository;
import guru.springframework.repositories.UnitOfMeasureRepository;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class IngredientServiceImpl implements IngredientService {

	private final RecipeRepository recipeRepository;
	private final IngredientRepository ingredientRepository;
	private final UnitOfMeasureRepository unitOfMeasureRepository;

	private final IngredientToIngredientCommand ingredientToIngredientCommand;
	private final IngredientCommandToIngredient ingredientCommandToIngredient;

	public IngredientServiceImpl(RecipeRepository recipeRepository,
			IngredientToIngredientCommand ingredientToIngredientCommand,
			IngredientCommandToIngredient ingredientCommandToIngredient,
			UnitOfMeasureRepository unitOfMeasureRepository, IngredientRepository ingredientRepository) {
		this.ingredientRepository = ingredientRepository;
		this.unitOfMeasureRepository = unitOfMeasureRepository;
		this.ingredientToIngredientCommand = ingredientToIngredientCommand;
		this.ingredientCommandToIngredient = ingredientCommandToIngredient;
		this.recipeRepository = recipeRepository;
	}

	@Override
	public IngredientCommand saveIngredientCommand(IngredientCommand command) {

		Optional<Recipe> optionalRecipe = recipeRepository.findById(command.getRecipeId());

		if (!optionalRecipe.isPresent()) {
			throw new RuntimeException("Recipe not Saved/Found in DataBase...");
		}

		Recipe recipe = optionalRecipe.get();

		Optional<Ingredient> optionalIngredient = recipe.getIngredients().stream()
				.filter(ingredient -> ingredient.getId().equals(command.getId())).findFirst();

		if (optionalIngredient.isPresent()) {
			Ingredient ingredientFound = optionalIngredient.get();
			ingredientFound.setDescription(command.getDescription());
			ingredientFound.setAmount(command.getAmount());
			ingredientFound.setUnitOfMeasure(unitOfMeasureRepository.findById(command.getUom().getId())
					.orElseThrow(() -> new RuntimeException("UOM NOT FOUND"))); // todo address this
		} else {
			// add new Ingredient
			Ingredient ingredient = ingredientCommandToIngredient.convert(command);
			assert ingredient != null;
			ingredient.setRecipe(recipe);
			recipe.addIngredient(ingredient);
		}

		Recipe savedRecipe = recipeRepository.save(recipe);

		Optional<Ingredient> savedIngredientOptional = savedRecipe.getIngredients().stream()
				.filter(recipeIngredients -> recipeIngredients.getId().equals(command.getId())).findFirst();

		// check by description
		if (!savedIngredientOptional.isPresent()) {

			log.debug("Unable to find Ingredient in DB as ID was null, so checking with Desc+Amt+UOM ID...");
			// not totally safe... But best guess
			savedIngredientOptional = savedRecipe.getIngredients().stream()
					.filter(recipeIngredients -> recipeIngredients.getDescription().equals(command.getDescription()))
					.filter(recipeIngredients -> recipeIngredients.getAmount().equals(command.getAmount()))
					.filter(recipeIngredients -> recipeIngredients.getUnitOfMeasure().getId()
							.equals(command.getUom().getId()))
					.findFirst();
		}

		return ingredientToIngredientCommand.convert(savedIngredientOptional.get());
	}

	@Override
	public IngredientCommand findByRecipeIdAndIngredientId(Long recipeId, Long ingredientId) {

		Optional<Recipe> recipeOptional = recipeRepository.findById(recipeId);

		if (!recipeOptional.isPresent()) {

			log.error("recipe id not found. Id: " + recipeId);
		}

		Recipe recipe = recipeOptional.get();

		Optional<IngredientCommand> currentIngredient = recipe.getIngredients().stream()
				.filter(ingredient -> ingredient.getId().equals(ingredientId))
				.map(inputIngredient -> ingredientToIngredientCommand.convert(inputIngredient)).findFirst();

		return currentIngredient.isPresent() ? currentIngredient.get() : null;
	}

	@Override
	public void deleteById(Long recipeId, Long idToDelete) {

		log.debug("Deleting ingredient: " + recipeId + ":" + idToDelete);

		Optional<Recipe> recipeOptional = recipeRepository.findById(recipeId);

		if (recipeOptional.isPresent()) {
			Recipe recipe = recipeOptional.get();
			log.debug("found recipe");

			Optional<Ingredient> ingredientOptional = recipe.getIngredients().stream()
					.filter(ingredient -> ingredient.getId().equals(idToDelete)).findFirst();

			if (ingredientOptional.isPresent()) {
				log.debug("found Ingredient");
				Ingredient ingredientToDelete = ingredientOptional.get();
				ingredientToDelete.setRecipe(null);
				recipe.getIngredients().remove(ingredientOptional.get());
				recipeRepository.save(recipe);
			}
		} else {
			log.debug("Recipe Id Not found. Id:" + recipeId);
		}
	}
}