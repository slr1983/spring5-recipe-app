package guru.springframework.services;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.HashSet;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import guru.springframework.domain.Recipe;
import guru.springframework.repositories.RecipeRepository;

public class RecipeServiceImplTest {

	RecipeServiceImpl recipeServiceImpl;

	@Mock
	RecipeRepository recipeRepository;

	@Before
	public void setUp() throws Exception {

		MockitoAnnotations.initMocks(this);

		//recipeServiceImpl = new RecipeServiceImpl(recipeRepository);
	}

	@Test
	public final void testGetAllRecipies() {
		Recipe recipe = new Recipe();
		HashSet<Recipe> recipesReturned = new HashSet<Recipe>();

		recipesReturned.add(recipe);

		when(recipeRepository.findAll()).thenReturn(recipesReturned);

		Set<Recipe> recipes = recipeServiceImpl.getAllRecipies();

		System.out.println("Number of Recipes : " + recipes.size());

		assertEquals(1, recipes.size());
		
		verify(recipeRepository,times(1)).findAll();

	}

}
