package re.neutrino.buoto.ohpuree.controller;

import android.net.Uri;
import android.util.Log;
import android.widget.ArrayAdapter;

import com.loopj.android.http.RequestParams;

import java.util.ArrayList;

import re.neutrino.buoto.ohpuree.api.APIClient;
import re.neutrino.buoto.ohpuree.api.ObjectResponseHandler;
import re.neutrino.buoto.ohpuree.api.response.ProductsResponse;
import re.neutrino.buoto.ohpuree.model.Product;

/**
 * Controller managing fetching products from server and serving them to View.
 */
public class ProductSearchController
{
    private final ArrayAdapter<Product> adapter;
    private RequestParams nextParams = null;
    private boolean hasMore = false;

    /**
     * Makes adapter to notify about text field changed
     * @param adapter that contains found products
     */
    public ProductSearchController(ArrayAdapter<Product> adapter)
    {
        this.adapter = adapter;
        adapter.setNotifyOnChange(true);
    }

    /**
     * Search for the first bunch of results
     */
    public void fetchInitial()
    {
        fetch(true);
    }

    /**
     * Search for the products which contain given string
     * @param query string typed
     */
    public void fetchInitial(String query)
    {
        nextParams = new RequestParams("q", query);
        fetch(true);
    }

    /**
     * Search for more products
     * @return true - if there are some more products; false - otherwise;
     */
    public boolean fetchMore()
    {
        if (!hasMore)
            return false;
        fetch(false);
        return true;
    }

    private void fetch(final boolean shouldClear)
    {
        APIClient.get("/products/", nextParams, new ObjectResponseHandler<ProductsResponse>()
        {
            @Override
            public void onSuccess(int statusCode, ProductsResponse object)
            {
                if (shouldClear && !adapter.isEmpty())
                {
                    adapter.clear();
                }
                appendNewElements(object);
                setNextParams(object);
            }

            @Override
            public void onFailure(int statusCode, String responseString)
            {
                Log.e(TAG, responseString);
            }
        });
    }

    private void appendNewElements(ProductsResponse object)
    {
        ArrayList<Product> prods = object.getResults();
        adapter.addAll(prods);
    }

    private void setNextParams(ProductsResponse response)
    {
        if (!response.hasNext())
        {
            hasMore = false;
            nextParams = null;
            return;
        }

        hasMore = true;
        nextParams = new RequestParams();
        Uri nextUri = Uri.parse(response.getNext());
        for (String key : nextUri.getQueryParameterNames())
        {
            nextParams.add(key, nextUri.getQueryParameter(key));
        }
    }
}
