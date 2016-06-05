package re.neutrino.buoto.ohpuree;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.buoto.ohpuree.controller.SearchResultController;
import com.google.gson.Gson;
import com.squareup.picasso.Picasso;

import re.neutrino.buoto.ohpuree.model.Recipe;

public class ResultsActivity extends AppCompatActivity {

    /**
     * The {@link android.support.v4.view.PagerAdapter} that will provide
     * fragments for each of the sections. We use a
     * {@link FragmentPagerAdapter} derivative, which will keep every
     * loaded fragment in memory. If this becomes too memory intensive, it
     * may be best to switch to a
     * {@link android.support.v4.app.FragmentStatePagerAdapter}.
     */
    private SectionsPagerAdapter mSectionsPagerAdapter;

    /**
     * The {@link ViewPager} that will host the section contents.
     */
    private ViewPager mViewPager;
    private SearchResultController controller;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results);

        String response = getIntent().getStringExtra("response");
        if (response == null)
            finish();
        controller = new SearchResultController(this, response);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);


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
            String picturePath = recipe.getPicture();
            if (picturePath.isEmpty()) {
                picture.setImageResource(R.mipmap.recipe);
            } else {
                Picasso.with(getContext())
                        .load(picturePath)
                        .placeholder(R.mipmap.recipe)
                        .into(picture);
            }
            rootView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    controller.selectRecipe(recipe);
                }
            });
            return rootView;
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

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            // getItem is called to instantiate the fragment for the given page.
            // Return a RecipeFragment (defined as a static inner class below).
            return RecipeFragment.newInstance(controller, controller.getRecipe(position));
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
