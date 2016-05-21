package com.example.buoto.ohpuree.controller;

import android.net.Uri;
import android.util.Log;
import android.widget.ArrayAdapter;

import com.example.buoto.ohpuree.api.APIClient;
import com.example.buoto.ohpuree.api.ObjectResponseHandler;
import com.example.buoto.ohpuree.api.response.ProductsResponse;
import com.example.buoto.ohpuree.model.Product;
import com.loopj.android.http.RequestParams;

import java.util.ArrayList;
import java.util.HashSet;

/**
 * Created by buoto on 5/21/16.
 */
public class ProductSearchController {

    private final HashSet<Product> chosen = new HashSet<>();
    private final ArrayAdapter<Product> adapter;
    private RequestParams nextParams = null;
    private boolean hasMore = false;

    public ProductSearchController(ArrayAdapter<Product> adapter) {
        this.adapter = adapter;
        adapter.setNotifyOnChange(true);
    }

    public void fetchInitial() {
        fetch(true);
    }

    public void fetchInitial(String query) {
        nextParams = new RequestParams("q", query);
        fetch(true);
    }

    public boolean fetchMore() {
        if (!hasMore)
            return false;
        fetch(false);
        return true;
    }

    private void fetch(final boolean shouldClear) {
        APIClient.get("/products/", nextParams, new ObjectResponseHandler<ProductsResponse>() {
            @Override
            public void onSuccess(int statusCode, ProductsResponse object) {
                if (shouldClear && !adapter.isEmpty()) {
                    adapter.clear();
                    adapter.addAll(chosen);
                }
                appendNewElements(object);
                setNextParams(object);
            }

            @Override
            public void onFailure(int statusCode, String responseString) {
                Log.e(TAG, responseString);
            }
        });
    }

    private void appendNewElements(ProductsResponse object) {
        ArrayList<Product> prods = object.getResults();
        for (Product p : chosen) {
            prods.remove(p);
        }
        adapter.addAll(prods);
    }

    private void setNextParams(ProductsResponse response) {
        if (!response.hasNext()) {
            hasMore = false;
            nextParams = null;
            return;
        }

        hasMore = true;
        nextParams = new RequestParams();
        Uri nextUri = Uri.parse(response.getNext());
        for (String key : nextUri.getQueryParameterNames()) {
            nextParams.add(key, nextUri.getQueryParameter(key));
        }
    }

    public void changeState(int position) {
        synchronized (adapter) {
            Product product = adapter.getItem(position);
            if (chosen.contains(product)) {
                unselectProduct(product);
            } else {
                selectProduct(product);
            }
        }
    }

    private void selectProduct(Product product) {
        chosen.add(product);
    }

    private void unselectProduct(Product product) {
        chosen.remove(product);
    }
}
