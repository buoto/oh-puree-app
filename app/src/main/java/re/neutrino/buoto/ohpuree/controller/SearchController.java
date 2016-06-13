package re.neutrino.buoto.ohpuree.controller;

import com.google.gson.Gson;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import java.util.ArrayList;

import re.neutrino.buoto.ohpuree.api.APIClient;
import re.neutrino.buoto.ohpuree.model.Product;

/**
 * Controller managing adding products and preparing search query.
 */
public class SearchController
{
    private ArrayList<Product> products;

    /**
     * Initializes list of searched products
     */
    public SearchController()
    {
        products = new ArrayList<>();
    }

    /**
     * Adds product to the list of searched products
     * @param product to add
     */
    public void addProduct(Product product)
    {
        products.add(product);
    }

    /**
     * Removes product from the list of searched roducts
     * @param pos position of the product to remove
     */
    public void removeProduct(int pos)
    {
        products.remove(pos);
    }

    /**
     * Search for the recipe consisted of the given products
     * @param handler to handle server response
     */
    public void search(AsyncHttpResponseHandler handler)
    {
        RequestParams params = new RequestParams();
        for (Product p : products)
        {
            params.add("products", Integer.toString(p.getId()));
        }

        APIClient.get("/search/", params, handler);
    }

    /**
     * Converts list of searched products to JSON
     * @return JSON representation of products
     */
    public String getProductsJSONString()
    {
        Gson g = new Gson();
        return g.toJson(products);
    }
}
