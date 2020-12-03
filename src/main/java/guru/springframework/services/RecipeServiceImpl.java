package guru.springframework.services;

import java.util.HashSet;
import java.util.Set;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import guru.springframework.domain.Recipe;
import guru.springframework.repositories.RecipeRepository;

@Service
@Slf4j
public class RecipeServiceImpl implements RecipeService {

	private final RecipeRepository recipeRepository;

	public RecipeServiceImpl(RecipeRepository recipeRepository) {
		super();
		this.recipeRepository = recipeRepository;
	}

	@Override
	public Set<Recipe> getAllRecipies() {

		//log.debug("Inside getAllRecipies");
		Set<Recipe> allRecipes = new HashSet<Recipe>();
		recipeRepository.findAll().iterator().forEachRemaining(allRecipes::add);

		return allRecipes;
	}

}