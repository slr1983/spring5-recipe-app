package guru.springframework.controllers;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.util.HashSet;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.ui.Model;

import com.fasterxml.jackson.annotation.JsonAnySetter;

import guru.springframework.domain.Recipe;
import guru.springframework.services.RecipeService;

public class IndexControllerTest {

	IndexController indexController;

	@Mock
	RecipeService recipeService;

	@Mock
	Model model;

	@Before
	public void setUp() throws Exception {

		MockitoAnnotations.initMocks(this);

		indexController = new IndexController(recipeService);
	}

	@Test
	public final void testIndexController() {

	}

	@Test
	public final void testShowIndex() {

		Set<Recipe> recipes = recipeService.getAllRecipies();
		verify(recipeService, times(1)).getAllRecipies();
		System.out.println("Number of Recipes : " + recipes.size());

		String viewName = indexController.showIndex(model);

		assertEquals("index", viewName);
		verify(model, times(1)).addAttribute("recipes", new HashSet<Recipe>());
	}

	@Test
	public final void testMockMVC() {
		
		MockMvc mockMvc = MockMvcBuilders.standaloneSetup(indexController).build();
		
		try {
			mockMvc.perform(get("/")).andExpect(status().isOk()).andExpect(view().name("index"));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
