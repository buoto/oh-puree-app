package re.neutrino.buoto.ohpuree;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.google.gson.Gson;

import re.neutrino.buoto.ohpuree.model.Product;


public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(re.neutrino.buoto.ohpuree.R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(re.neutrino.buoto.ohpuree.R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(re.neutrino.buoto.ohpuree.R.id.fab);
        assert fab != null;
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                startChoose();
                startSearch();
            }

        });

    }


    private void startSearch() {
        Intent intent = new Intent(this, SearchActivity.class);
        startActivity(intent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(re.neutrino.buoto.ohpuree.R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == re.neutrino.buoto.ohpuree.R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
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
        TextView t = (TextView) findViewById(re.neutrino.buoto.ohpuree.R.id.hello);
        assert t != null;
        t.append(product.toString());
    }
}
