package re.neutrino.buoto.ohpuree.controller;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.HashSet;

import re.neutrino.buoto.ohpuree.api.response.SearchResponse;
import re.neutrino.buoto.ohpuree.model.Product;
import re.neutrino.buoto.ohpuree.model.Recipe;
import re.neutrino.buoto.ohpuree.view.ResultsActivity;

/**
 * Controller storing search results and serving them to views.
 */
public class SearchResultController
{
    private final ArrayList<Recipe> results;
    private final ResultsActivity resultsActivity;
    private final HashSet<Product> searchedProducts;

    /**
     * Initializes search results
     * @param resultsActivity view
     * @param response server response after search
     * @param searchedProds JSON representation of searched products
     */
    public SearchResultController(ResultsActivity resultsActivity, String response, String searchedProds)
    {
        Gson g = new Gson();
        SearchResponse searchResponse = g.fromJson(response, SearchResponse.class);
        results = searchResponse.getResults();
        this.resultsActivity = resultsActivity;

        searchedProducts = g.fromJson(searchedProds, new TypeToken<HashSet<Product>>()
        {
        }.getType());
    }

    /**
     * Gets number of recipes in search results
     * @return number of recipes found
     */
    public int getCount()
    {
        return results.size();
    }

    /**
     * Gets the recipe name of the specific position in search result
     * @param position of the recipe to get the name
     * @return name of the recipe on the position given
     */
    public String getRecipeName(int position)
    {
        return results.get(position).getName();
    }

    /**
     * Gets the recipe of specific position in search result
     * @param position of the recipe to get
     * @return recipe on the position given
     */
    public Recipe getRecipe(int position)
    {
        return results.get(position);
    }

    /**
     * Selects recipe from the results given
     * @param recipe to select
     */
    public void selectRecipe(Recipe recipe)
    {
        Gson g = new Gson();
        String recipeString = g.toJson(recipe);
        resultsActivity.selectRecipe(recipeString);
    }

    /**
     * Checks out if the product was searched
     * @param product to check
     * @return true - if the product was one of the searched products; false - otherwise;
     */
    public boolean wasSearched(Product product)
    {
        return searchedProducts.contains(product);
    }
}
