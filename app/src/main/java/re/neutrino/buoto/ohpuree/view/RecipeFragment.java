package re.neutrino.buoto.ohpuree.view;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.cunoraz.tagview.Tag;
import com.cunoraz.tagview.TagView;
import com.squareup.picasso.Picasso;

import re.neutrino.buoto.ohpuree.R;
import re.neutrino.buoto.ohpuree.controller.SearchResultController;
import re.neutrino.buoto.ohpuree.model.Product;
import re.neutrino.buoto.ohpuree.model.ProductEntry;
import re.neutrino.buoto.ohpuree.model.Recipe;

/**
 * Fragment used to represent tabs in SearchResultsView
 *
 */
public class RecipeFragment extends Fragment
{
    /**
     * The fragment argument representing the section number for this
     * fragment.
     */
    private Recipe recipe;
    private SearchResultController controller;

    public RecipeFragment()
    {
    }

    /**
     * Returns a new instance of this fragment for the given section
     * number.
     */
    public static RecipeFragment newInstance(SearchResultController controller, Recipe recipe)
    {
        RecipeFragment fragment = new RecipeFragment();
        fragment.setRecipe(recipe);
        fragment.setController(controller);
        return fragment;
    }

    private void setRecipe(Recipe recipe)
    {
        this.recipe = recipe;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container,
                             Bundle savedInstanceState)
    {
        View rootView = inflater.inflate(R.layout.fragment_results, container, false);
        TextView textView = (TextView) rootView.findViewById(R.id.recipe_name);
        textView.setText(recipe.getName());

        ImageView picture = (ImageView) rootView.findViewById(R.id.picture);
        loadPicture(picture);

        initMissingProducts(rootView);

        rootView.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                controller.selectRecipe(recipe);
            }
        });
        return rootView;
    }

    private void initMissingProducts(View rootView)
    {
        TagView missing_prods = (TagView) rootView.findViewById(R.id.missing_prods);
        for (ProductEntry p : recipe.getProducts())
        {
            Product prod = p.getProduct();
            if (controller.wasSearched(prod))
                continue;
            if (missing_prods.getVisibility() == View.GONE)
            {
                missing_prods.setVisibility(View.VISIBLE);
                TextView label = (TextView) rootView.findViewById(R.id.missing_prods_label);
                label.setVisibility(View.VISIBLE);
            }
            Tag t = new Tag(prod.getName());
            t.layoutColor = Color.RED;
            missing_prods.addTag(t);
        }
    }

    private void loadPicture(ImageView picture)
    {
        String picturePath = recipe.getPicture();
        if (picturePath.isEmpty())
        {
            picture.setImageResource(R.mipmap.recipe);
        } else
        {
            Picasso.with(getContext())
                    .load(picturePath)
                    .placeholder(R.mipmap.recipe)
                    .resize(400, 400)
                    .into(picture);
        }
    }


    public void setController(SearchResultController controller)
    {
        this.controller = controller;
    }
}
