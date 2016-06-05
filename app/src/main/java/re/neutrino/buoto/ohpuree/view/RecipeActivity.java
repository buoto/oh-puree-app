package re.neutrino.buoto.ohpuree.view;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;
import android.widget.TextView;

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

    }
}
