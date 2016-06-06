package re.neutrino.buoto.ohpuree.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import com.google.gson.Gson;

import java.util.ArrayList;

import re.neutrino.buoto.ohpuree.R;
import re.neutrino.buoto.ohpuree.controller.RecipeFragment;
import re.neutrino.buoto.ohpuree.controller.SearchResultController;
import re.neutrino.buoto.ohpuree.model.Recipe;

public class ResultsActivity extends AppCompatActivity {

    private static final String TAG = "ResultsActivity";
    private SearchResultController controller;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results);

        String response = getIntent().getStringExtra("response");
        String searchedProducts = getIntent().getStringExtra("products");
        if (response == null || searchedProducts == null)
            finish();
        controller = new SearchResultController(this, response, searchedProducts);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        SectionsPagerAdapter sectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        ViewPager viewPager = (ViewPager) findViewById(R.id.container);
        viewPager.setAdapter(sectionsPagerAdapter);
    }

    public void selectRecipe(String recipe) {
        Intent intent = new Intent(this, RecipeActivity.class);
        intent.putExtra("recipe", recipe);
        startActivity(intent);
    }


    /**
     * Recipe fragment
     */

    /**
     * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        private final ArrayList<RecipeFragment> fragments;

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
            // preload fragments
            fragments = new ArrayList<>();
            for (int i = 0; i < getCount(); i++) {
                fragments.add(RecipeFragment.newInstance(controller, controller.getRecipe(i)));
            }

        }

        @Override
        public Fragment getItem(int position) {
            // getItem is called to instantiate the fragment for the given page.
            // Return a RecipeFragment (defined as a static inner class below).
            return fragments.get(position);
        }

        @Override
        public int getCount() {
            return controller.getCount();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            if (position < controller.getCount()) {
                return controller.getRecipeName(position);
            }
            return null;
        }

    }
}
