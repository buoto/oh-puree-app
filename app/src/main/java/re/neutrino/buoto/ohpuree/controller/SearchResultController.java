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
 * Created by buoto on 6/5/16.
 */
public class SearchResultController {
    private final ArrayList<Recipe> results;
    private final ResultsActivity resultsActivity;
    private final HashSet<Product> searchedProducts;

    public SearchResultController(ResultsActivity resultsActivity, String response, String searchedProds) {
        Gson g = new Gson();
        SearchResponse searchResponse = g.fromJson(response, SearchResponse.class);
        results = searchResponse.getResults();
        this.resultsActivity = resultsActivity;

        searchedProducts = g.fromJson(searchedProds, new TypeToken<HashSet<Product>>() {
        }.getType());
    }

    public int getCount() {
        return results.size();
    }

    public String getRecipeName(int position) {
        return results.get(position).getName();
    }

    public Recipe getRecipe(int position) {
        return results.get(position);
    }

    public void selectRecipe(Recipe recipe) {
        resultsActivity.selectRecipe(recipe);
    }


    public boolean wasSearched(Product product) {
        return searchedProducts.contains(product);
    }
}
