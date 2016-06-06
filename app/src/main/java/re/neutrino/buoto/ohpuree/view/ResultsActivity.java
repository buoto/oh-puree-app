package re.neutrino.buoto.ohpuree.view;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.cunoraz.tagview.Tag;
import com.cunoraz.tagview.TagView;
import com.google.gson.Gson;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import re.neutrino.buoto.ohpuree.R;
import re.neutrino.buoto.ohpuree.controller.SearchResultController;
import re.neutrino.buoto.ohpuree.model.Product;
import re.neutrino.buoto.ohpuree.model.ProductEntry;
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
        Log.d(TAG, searchedProducts);
        controller = new SearchResultController(this, response, searchedProducts);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        SectionsPagerAdapter sectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        ViewPager viewPager = (ViewPager) findViewById(R.id.container);
        viewPager.setAdapter(sectionsPagerAdapter);
    }

    public void selectRecipe(Recipe recipe) {
        Intent intent = new Intent(this, RecipeActivity.class);
        Gson g = new Gson();
        String recipeString = g.toJson(recipe);
        intent.putExtra("recipe", recipeString);
        startActivity(intent);
    }


    /**
     * Recipe fragment
     */
    public static class RecipeFragment extends Fragment {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private Recipe recipe;
        private SearchResultController controller;

        public RecipeFragment() {
        }

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        public static RecipeFragment newInstance(SearchResultController controller, Recipe recipe) {
            RecipeFragment fragment = new RecipeFragment();
            fragment.setRecipe(recipe);
            fragment.setController(controller);
            return fragment;
        }

        private void setRecipe(Recipe recipe) {
            this.recipe = recipe;
        }

        @Override
        public View onCreateView(LayoutInflater inflater, final ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_results, container, false);
            TextView textView = (TextView) rootView.findViewById(R.id.recipe_name);
            textView.setText(recipe.getName());

            ImageView picture = (ImageView) rootView.findViewById(R.id.picture);
            loadPicture(picture);

            initMissingProducts(rootView);

            rootView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    controller.selectRecipe(recipe);
                }
            });
            return rootView;
        }

        private void initMissingProducts(View rootView) {
            TagView missing_prods = (TagView) rootView.findViewById(R.id.missing_prods);
            for (ProductEntry p : recipe.getProducts()) {
                Product prod = p.getProduct();
                if (controller.wasSearched(prod))
                    continue;
                if (missing_prods.getVisibility() == View.GONE) {
                    missing_prods.setVisibility(View.VISIBLE);
                    TextView label = (TextView) rootView.findViewById(R.id.missing_prods_label);
                    label.setVisibility(View.VISIBLE);
                }
                Tag t = new Tag(prod.getName());
                t.layoutColor = Color.RED;
                missing_prods.addTag(t);
            }
        }

        private void loadPicture(ImageView picture) {
            String picturePath = recipe.getPicture();
            if (picturePath.isEmpty()) {
                picture.setImageResource(R.mipmap.recipe);
            } else {
                Picasso.with(getContext())
                        .load(picturePath)
                        .placeholder(R.mipmap.recipe)
                        .resize(400, 400)
                        .into(picture);
            }
        }


        public void setController(SearchResultController controller) {
            this.controller = controller;
        }
    }

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
