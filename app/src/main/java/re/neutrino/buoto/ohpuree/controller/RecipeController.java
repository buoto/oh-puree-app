package re.neutrino.buoto.ohpuree.controller;

import com.google.gson.Gson;

import re.neutrino.buoto.ohpuree.model.Recipe;

/**
 * Controller handling Recipe view. Provides data about currently chosen recipe.
 */
public class RecipeController
{
    private Recipe recipe;

    /**
     * Initializes recipe read from JSON
     * @param recipeString JSON representation of the recipe
     */
    public RecipeController(String recipeString)
    {
        initRecipe(recipeString);
    }

    private void initRecipe(String recipeString)
    {
        Gson g = new Gson();
        recipe = g.fromJson(recipeString, Recipe.class);
    }

    /**
     * Gets content of the recipe in one string
     * @return recipe steps to print
     */
    public String getText()
    {
        return recipe.printSteps();
    }

    /**
     * Gets recipe title
     * @return recipe title
     */
    public String getTitle()
    {
        return recipe.getName();
    }

    /**
     * Gets recipe picture
     * @return recipe picture
     */
    public String getPicture()
    {
        return recipe.getPicture();
    }

    /**
     * Checks out is it vegan recipe
     * @return true - if the recipe is for vegan; false - otherwise;
     */
    public boolean isRecipeVegan()
    {
        return recipe.isVegan();
    }

    /**
     * Checks out is it vegetarian recipe
     * @return true - if the recipe is for vegetarian; false - otherwise;
     */
    public boolean isRecipeVege()
    {
        return recipe.isVegetarian();
    }

    /**
     * Gets recipe products in one string
     * @return recipe products to print
     */
    public String getIngredients()
    {
        return recipe.printIngredients();
    }
}
