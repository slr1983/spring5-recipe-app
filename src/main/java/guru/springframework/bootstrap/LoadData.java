package guru.springframework.bootstrap;

import java.math.BigDecimal;
import java.util.ArrayList;

import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import guru.springframework.domain.Difficulty;
import guru.springframework.domain.Ingredient;
import guru.springframework.domain.Notes;
import guru.springframework.domain.Recipe;
import guru.springframework.repositories.CategoryRepository;
import guru.springframework.repositories.RecipeRepository;
import guru.springframework.repositories.UnitOfMeasureRepository;

@Component
public class LoadData implements ApplicationListener<ContextRefreshedEvent> {

	private final CategoryRepository categoryRepository;
	private final UnitOfMeasureRepository unitOfMeasureRepository;
	private final RecipeRepository recipeRepository;

	public LoadData(CategoryRepository categoryRepository, UnitOfMeasureRepository unitOfMeasureRepository,
			RecipeRepository recipeRepository) {
		super();
		this.categoryRepository = categoryRepository;
		this.unitOfMeasureRepository = unitOfMeasureRepository;
		this.recipeRepository = recipeRepository;
	}

	/*
	 * @Override public void run(String... args) throws Exception {
	 * loadDataIntoRecipe();
	 * 
	 * }
	 */

	@Override
	public void onApplicationEvent(ContextRefreshedEvent event) {
		loadDataIntoRecipe();

	}

	public void loadDataIntoRecipe() {

		ArrayList<Recipe> recipesList = new ArrayList<Recipe>();

		Recipe spicyGrilledChickenTacos = new Recipe();
		Notes spicyGrilledChickenTacosNotes = new Notes();
		Ingredient ingredient1 = new Ingredient();
		Ingredient ingredient2 = new Ingredient();
		Ingredient ingredient3 = new Ingredient();

		spicyGrilledChickenTacos.setCookTime(15);
		spicyGrilledChickenTacos.setDescription("Spicy Grilled Chicken Tacos");
		spicyGrilledChickenTacos.setDifficulty(Difficulty.MODERATE);
		spicyGrilledChickenTacos
				.setDirections("Spicy grilled chicken tacos! Quick marinade, then grill. Ready in about 30 minutes. "
						+ "Great for a quick weeknight dinner, backyard cookouts, and tailgate parties.\r\n");
		spicyGrilledChickenTacosNotes.setRecipe(spicyGrilledChickenTacos);
		spicyGrilledChickenTacosNotes.setRecipeNotes(
				"First, I marinate the chicken briefly in a spicy paste of ancho chile powder, oregano, cumin, and sweet orange juice while the grill is heating. You can also use this time to prepare the taco toppings.\r\n"
						+ "\r\n"
						+ "Grill the chicken, then let it rest while you warm the tortillas. Now you are ready to assemble the tacos and dig in. The whole meal comes together in about 30 minutes!");
		spicyGrilledChickenTacos.setNotes(spicyGrilledChickenTacosNotes);

		// spicyGrilledChickenTacos.setImage();

		spicyGrilledChickenTacos.setPrepTime(20);
		spicyGrilledChickenTacos.setServings(4);
		spicyGrilledChickenTacos.setSource("simplyrecipes");
		spicyGrilledChickenTacos.setUrl("https://www.simplyrecipes.com/recipes/spicy_grilled_chicken_tacos/");

		ingredient1.setAmount(new BigDecimal("2"));
		ingredient1.setDescription("ancho chili powder");
		ingredient1.setUnitOfMeasure(unitOfMeasureRepository.findByDescription("Tablespoon").get());
		ingredient1.setRecipe(spicyGrilledChickenTacos);

		ingredient2.setAmount(new BigDecimal("1"));
		ingredient2.setDescription("dried oregano");
		ingredient2.setUnitOfMeasure(unitOfMeasureRepository.findByDescription("Teaspoon").get());
		ingredient2.setRecipe(spicyGrilledChickenTacos);

		ingredient3.setAmount(new BigDecimal("1"));
		ingredient3.setDescription("dried cumin");
		ingredient3.setUnitOfMeasure(unitOfMeasureRepository.findByDescription("Teaspoon").get());
		ingredient3.setRecipe(spicyGrilledChickenTacos);

		spicyGrilledChickenTacos.getIngredients().add(ingredient1);
		spicyGrilledChickenTacos.getIngredients().add(ingredient2);
		spicyGrilledChickenTacos.getIngredients().add(ingredient3);

		spicyGrilledChickenTacos.getCategories().add(categoryRepository.findByDescription("American").get());
		spicyGrilledChickenTacos.getCategories().add(categoryRepository.findByDescription("Mexican").get());

		recipesList.add(spicyGrilledChickenTacos);

		Recipe perfectGuacamole = new Recipe();
		Notes perfectGuacamolesNotes = new Notes();
		Ingredient ingredient4 = new Ingredient();
		Ingredient ingredient5 = new Ingredient();
		Ingredient ingredient6 = new Ingredient();

		perfectGuacamole.setCookTime(20);
		perfectGuacamole.setDescription("How to Make Perfect Guacamole Recipe");
		perfectGuacamole.setDifficulty(Difficulty.HARD);
		perfectGuacamole.setDirections(
				"Be careful handling chiles if using. Wash your hands thoroughly after handling and do not touch"
						+ " your eyes or the area near your eyes with your hands for several hours.");
		perfectGuacamolesNotes.setRecipe(perfectGuacamole);
		perfectGuacamolesNotes.setRecipeNotes("Cut the avocado, remove flesh: Cut the avocados in half. Remove the pit."
				+ " Score the inside of the avocado with a blunt knife and scoop out the flesh with a spoon. (See How to Cut and Peel an Avocado.) Place in a bowl."
				+ "Mash with a fork: Using a fork, roughly mash the avocado. (Don't overdo it! The guacamole should be a little chunky.)");
		perfectGuacamole.setNotes(perfectGuacamolesNotes);

		// perfectGuacamole.setImage();

		perfectGuacamole.setPrepTime(10);
		perfectGuacamole.setServings(2);
		perfectGuacamole.setSource("simplyrecipes");
		perfectGuacamole.setUrl("https://www.simplyrecipes.com/recipes/perfect_guacamole/");

		ingredient4.setAmount(new BigDecimal("1"));
		ingredient4.setDescription("fresh lime juice or lemon juice");
		ingredient4.setUnitOfMeasure(unitOfMeasureRepository.findByDescription("Tablespoon").get());
		ingredient4.setRecipe(perfectGuacamole);

		ingredient5.setAmount(new BigDecimal("2"));
		ingredient5.setDescription("minced red onion or thinly sliced green onion");
		ingredient5.setUnitOfMeasure(unitOfMeasureRepository.findByDescription("Tablespoon").get());
		ingredient5.setRecipe(perfectGuacamole);

		ingredient6.setAmount(new BigDecimal("1"));
		ingredient6.setDescription("freshly grated black pepper");
		ingredient6.setUnitOfMeasure(unitOfMeasureRepository.findByDescription("Dash").get());
		ingredient6.setRecipe(perfectGuacamole);

		perfectGuacamole.getIngredients().add(ingredient4);
		perfectGuacamole.getIngredients().add(ingredient5);
		perfectGuacamole.getIngredients().add(ingredient6);

		perfectGuacamole.getCategories().add(categoryRepository.findByDescription("American").get());
		perfectGuacamole.getCategories().add(categoryRepository.findByDescription("Italian").get());

		recipesList.add(perfectGuacamole);
		
		recipesList.add(spicyGrilledChickenTacos.getRecipeObjectClone());
		recipesList.add(perfectGuacamole.getRecipeObjectClone());
		recipesList.add(spicyGrilledChickenTacos.getRecipeObjectClone());
		recipesList.add(spicyGrilledChickenTacos.getRecipeObjectClone());
		recipesList.add(perfectGuacamole.getRecipeObjectClone());
		recipesList.add(perfectGuacamole.getRecipeObjectClone());
		recipesList.add(spicyGrilledChickenTacos.getRecipeObjectClone());
		recipesList.add(spicyGrilledChickenTacos.getRecipeObjectClone());
		recipesList.add(perfectGuacamole.getRecipeObjectClone());
		recipesList.add(spicyGrilledChickenTacos.getRecipeObjectClone());
		recipesList.add(perfectGuacamole.getRecipeObjectClone());
		recipesList.add(perfectGuacamole.getRecipeObjectClone());
		recipesList.add(perfectGuacamole.getRecipeObjectClone());
		recipesList.add(spicyGrilledChickenTacos.getRecipeObjectClone());
		recipesList.add(spicyGrilledChickenTacos.getRecipeObjectClone());
		recipesList.add(perfectGuacamole.getRecipeObjectClone());
		recipesList.add(spicyGrilledChickenTacos.getRecipeObjectClone());
		recipesList.add(spicyGrilledChickenTacos.getRecipeObjectClone());
		recipesList.add(perfectGuacamole.getRecipeObjectClone());
		recipesList.add(perfectGuacamole.getRecipeObjectClone());
		

		recipeRepository.saveAll(recipesList);

		System.out.println("Saved Recipe...");

	}

}