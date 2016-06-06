package re.neutrino.buoto.ohpuree.view;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;
import android.widget.TextView;

import com.cunoraz.tagview.Tag;
import com.cunoraz.tagview.TagView;
import com.squareup.picasso.Picasso;

import re.neutrino.buoto.ohpuree.R;
import re.neutrino.buoto.ohpuree.controller.RecipeController;

public class RecipeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);

        String recipeString = getIntent().getStringExtra("recipe");
        if (recipeString == null)
            finish();
        RecipeController controller = new RecipeController(recipeString);
        toolbar.setTitle(controller.getTitle());

        TextView ingredients = (TextView) findViewById(R.id.ingredients);
        ingredients.setText(controller.getIngredients());
        TextView textView = (TextView) findViewById(R.id.recipe_text);
        textView.setText(controller.getText());

        ImageView pictureView = (ImageView) findViewById(R.id.recipe_picture);

        String picturePath = controller.getPicture();
        if (picturePath.isEmpty()) {
            pictureView.setImageResource(R.mipmap.recipe);
        } else {
            Picasso.with(this)
                    .load(picturePath)
                    .placeholder(R.mipmap.recipe)
                    .resize(400, 400)
                    .into(pictureView);
        }

        TagView specs = (TagView) findViewById(R.id.specs);
        if (controller.isRecipeVegan()) {
            Tag t = new Tag("vegan");
            t.layoutColor = Color.YELLOW;
            specs.addTag(t);
        }
        if (controller.isRecipeVege()) {
            Tag t = new Tag("vege");
            t.layoutColor = getResources().getColor(R.color.colorAccent);
            specs.addTag(t);
        }


    }
}
