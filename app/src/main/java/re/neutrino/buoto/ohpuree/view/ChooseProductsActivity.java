package re.neutrino.buoto.ohpuree.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import com.google.gson.Gson;

import re.neutrino.buoto.ohpuree.controller.ProductSearchController;
import re.neutrino.buoto.ohpuree.model.Product;

/**
 * Activity which lists products fetched from backend and allows user to select them.
 */
public class ChooseProductsActivity extends AppCompatActivity
{
    /**
     * Status code for selected product
     */
    public static final int PRODUCT_SELECTED = 11232;
    private ProductSearchController controller;
    private ListView listView;
    private EditText queryField;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(re.neutrino.buoto.ohpuree.R.layout.activity_choose_products);

        init();
    }

    private void init()
    {
        final ArrayAdapter<Product> adapter = new ArrayAdapter<>(this, re.neutrino.buoto.ohpuree.R.layout.product_item);
        initController(adapter);
        initListView(adapter);

        queryField = (EditText) findViewById(re.neutrino.buoto.ohpuree.R.id.query);
        queryField.addTextChangedListener(new TextWatcher()
        {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after)
            {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count)
            {

            }

            @Override
            public void afterTextChanged(Editable s)
            {
                controller.fetchInitial(s.toString());
            }
        });
    }

    private void initController(ArrayAdapter<Product> adapter)
    {
        controller = new ProductSearchController(adapter);
        controller.fetchInitial();
    }

    private void initListView(ArrayAdapter<Product> adapter)
    {
        listView = (ListView) findViewById(re.neutrino.buoto.ohpuree.R.id.listView);
        assert listView != null;
        listView.setAdapter(adapter);
        listView.setOnScrollListener(new AbsListView.OnScrollListener()
        {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState)
            {

            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount)
            {
                int lastOnScreen = firstVisibleItem + visibleItemCount;
                if (totalItemCount != 0 && lastOnScreen == totalItemCount)
                {
                    controller.fetchMore();
                }
            }
        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id)
            {
                returnProduct((Product) parent.getItemAtPosition(position));
            }
        });
    }

    private void returnProduct(Product chosen)
    {
        Gson gson = new Gson();
        String serialized = gson.toJson(chosen);
        Intent intent = new Intent();
        intent.putExtra("product", serialized);
        setResult(PRODUCT_SELECTED, intent);
        finish();
    }

}
