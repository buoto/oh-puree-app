package re.neutrino.buoto.ohpuree;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.cunoraz.tagview.TagView;

public class SearchActivity extends AppCompatActivity {

    private static final String TAG = "Search";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        final AppBarLayout bar = (AppBarLayout) findViewById(R.id.app_bar);
        TagView tagGroup = (TagView) findViewById(R.id.tag_group);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        assert fab != null;
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bar.setExpanded(false);
            }
        });
    }

    private void startChoose() {
        Intent intent = new Intent(this, ChooseProductsActivity.class);
        startActivityForResult(intent, ChooseProductsActivity.SKILL_SELECTED);
    }
}
