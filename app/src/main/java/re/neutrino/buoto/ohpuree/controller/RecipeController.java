package re.neutrino.buoto.ohpuree.controller;

import com.google.gson.Gson;

import re.neutrino.buoto.ohpuree.model.Recipe;

/**
 * Created by buoto on 6/5/16.
 */
public class RecipeController {
    private Recipe recipe;

    public RecipeController(String recipeString) {
        initRecipe(recipeString);

    }

    private void initRecipe(String recipeString) {
        Gson g = new Gson();
        recipe = g.fromJson(recipeString, Recipe.class);
    }

    public String getText() {
        return recipe.printSteps();
    }

    public String getTitle() {
        return recipe.getName();
    }


    public String getPicture() {
        return recipe.getPicture();
    }
}
