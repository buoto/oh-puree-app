package re.neutrino.buoto.ohpuree.controller;

import com.google.gson.Gson;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import java.util.ArrayList;

import re.neutrino.buoto.ohpuree.api.APIClient;
import re.neutrino.buoto.ohpuree.model.Product;

/**
 * Controller managing adding products and preparing search query.
 *
 */
public class SearchController
{

    private ArrayList<Product> products;

    public SearchController()
    {
        products = new ArrayList<>();
    }

    public void addProduct(Product product)
    {
        products.add(product);
    }

    public void removeProduct(int pos)
    {
        products.remove(pos);
    }

    public void search(AsyncHttpResponseHandler handler)
    {
        RequestParams params = new RequestParams();
        for (Product p : products)
        {
            params.add("products", Integer.toString(p.getId()));
        }

        APIClient.get("/search/", params, handler);
    }

    public String getProductsJSONString()
    {
        Gson g = new Gson();
        return g.toJson(products);
    }
}
