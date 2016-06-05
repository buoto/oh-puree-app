package com.example.buoto.ohpuree.controller;

import com.google.gson.Gson;

import java.util.ArrayList;

import re.neutrino.buoto.ohpuree.ResultsActivity;
import re.neutrino.buoto.ohpuree.api.response.SearchResponse;
import re.neutrino.buoto.ohpuree.model.Recipe;

/**
 * Created by buoto on 6/5/16.
 */
public class SearchResultController {
    private final ArrayList<Recipe> results;
    private final ResultsActivity resultsActivity;

    public SearchResultController(ResultsActivity resultsActivity, String response) {
        Gson g = new Gson();
        SearchResponse searchResponse = g.fromJson(response, SearchResponse.class);
        results = searchResponse.getResults();
        this.resultsActivity = resultsActivity;
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
}
