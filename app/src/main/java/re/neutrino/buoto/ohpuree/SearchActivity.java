package re.neutrino.buoto.ohpuree;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.cunoraz.tagview.Tag;
import com.cunoraz.tagview.TagView;
import com.google.gson.Gson;
import com.loopj.android.http.TextHttpResponseHandler;

import cz.msebera.android.httpclient.Header;
import re.neutrino.buoto.ohpuree.controller.SearchController;
import re.neutrino.buoto.ohpuree.model.Product;

public class SearchActivity extends AppCompatActivity {

    private static final String TAG = "Search";
    private final SearchController controller = new SearchController();
    private TagView tagView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
//        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);

        initTags();
        initFAB();

        Button b = (Button) findViewById(R.id.search_recipes_btn);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                searchAndShow();
            }
        });
    }

    private void searchAndShow() {

        controller.search(new TextHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, String responseString) {
                startResults(responseString);
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {

            }
        });
    }

    private void startResults(String responseString) {
        Intent intent = new Intent(this, ResultsActivity.class);
        intent.putExtra("response", responseString);
        startActivity(intent);
    }

    private void initTags() {
        tagView = (TagView) findViewById(R.id.tag_group);
        assert tagView != null;
    }

    private void initFAB() {
        final AppBarLayout bar = (AppBarLayout) findViewById(R.id.app_bar);
        assert bar != null;
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        assert fab != null;
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bar.setExpanded(false);
                startChoose();
            }
        });
    }

    private void startChoose() {
        Intent intent = new Intent(this, ChooseProductsActivity.class);
        startActivityForResult(intent, ChooseProductsActivity.SKILL_SELECTED);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == ChooseProductsActivity.SKILL_SELECTED) {
            Gson gson = new Gson();
            String json = data.getExtras().getString("product");
            Product product = gson.fromJson(json, Product.class);
            addProduct(product);
        }
    }

    private void addProduct(Product product) {
        Tag t = new Tag(product.toString());
        addTag(t);
        controller.addProduct(product);
    }

    private void addTag(Tag t) {
        //noinspection deprecation
        t.layoutColor = getResources().getColor(R.color.colorAccent);
        tagView.addTag(t);
    }
}
