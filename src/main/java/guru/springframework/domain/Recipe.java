package guru.springframework.domain;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class Recipe {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String description;
	private Integer prepTime;
	private Integer cookTime;
	private Integer servings;
	private String source;
	private String url;
	private String directions;

	@Enumerated(EnumType.STRING)
	private Difficulty difficulty;

	@Lob
	private Byte[] image;

	@OneToOne(cascade = CascadeType.ALL)
	private Notes notes;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "recipe")
	Set<Ingredient> ingredients = new HashSet<Ingredient>();

	@ManyToMany
	@JoinTable(name = "recipe_category", joinColumns = @JoinColumn(name = "recipe_id"), inverseJoinColumns = @JoinColumn(name = "category_id"))
	Set<Category> categories = new HashSet<Category>();

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Integer getPrepTime() {
		return prepTime;
	}

	public void setPrepTime(Integer prepTime) {
		this.prepTime = prepTime;
	}

	public Integer getCookTime() {
		return cookTime;
	}

	public void setCookTime(Integer cookTime) {
		this.cookTime = cookTime;
	}

	public Integer getServings() {
		return servings;
	}

	public void setServings(Integer servings) {
		this.servings = servings;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getDirections() {
		return directions;
	}

	public void setDirections(String directions) {
		this.directions = directions;
	}

	public Difficulty getDifficulty() {
		return difficulty;
	}

	public void setDifficulty(Difficulty difficulty) {
		this.difficulty = difficulty;
	}

	public Byte[] getImage() {
		return image;
	}

	public void setImage(Byte[] image) {
		this.image = image;
	}

	public Notes getNotes() {
		return notes;
	}

	public void setNotes(Notes notes) {
		this.notes = notes;
	}

	public Set<Ingredient> getIngredients() {
		return ingredients;
	}

	public void setIngredients(Set<Ingredient> ingredients) {
		this.ingredients = ingredients;
	}

	public Set<Category> getCategories() {
		return categories;
	}

	public void setCategories(Set<Category> categories) {
		this.categories = categories;
	}

	public Recipe getRecipeObjectClone() {

		Recipe objectToBeCopiedIn = new Recipe();

		Set<Ingredient> copyIngredients = new HashSet<Ingredient>();
		Set<Category> copyCategories = new HashSet<Category>();

		objectToBeCopiedIn.id = this.id;
		objectToBeCopiedIn.description = this.description;
		objectToBeCopiedIn.prepTime = this.prepTime;
		objectToBeCopiedIn.cookTime = this.cookTime;
		objectToBeCopiedIn.servings = this.servings;
		objectToBeCopiedIn.source = this.source;
		objectToBeCopiedIn.url = this.url;
		objectToBeCopiedIn.directions = this.directions;
		objectToBeCopiedIn.difficulty = this.difficulty;
		objectToBeCopiedIn.image = this.image;
		objectToBeCopiedIn.notes = this.notes.getNotesCopy();

		for (Ingredient ingredientToBeCopied : this.ingredients) {

			copyIngredients.add(ingredientToBeCopied.getIngredientCopy());

		}

		objectToBeCopiedIn.setIngredients(copyIngredients);

		for (Category categoryToBeCopied : this.categories) {

			copyCategories.add(categoryToBeCopied.getCategoryClone());

		}

		objectToBeCopiedIn.setCategories(copyCategories);

		return objectToBeCopiedIn;

	}

	public Recipe() {
	}

	public Recipe addIngredient(Ingredient ingredient) {

		ingredient.setRecipe(this);

		this.ingredients.add(ingredient);

		return this;

	}

}