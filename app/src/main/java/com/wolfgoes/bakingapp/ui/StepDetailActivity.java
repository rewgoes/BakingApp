package com.wolfgoes.bakingapp.ui;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import com.wolfgoes.bakingapp.R;
import com.wolfgoes.bakingapp.model.Recipe;
import com.wolfgoes.bakingapp.util.Constants;

public class StepDetailActivity extends AppCompatActivity {

    private Recipe mRecipe;
    private int mSelected = 0;

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putParcelable(Constants.STATE_EXTRA_RECIPE, mRecipe);
        outState.putInt(Constants.STATE_EXTRA_POSITION, mSelected);
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_step_detail);

        if (getActionBar() != null) {
            getActionBar().setDisplayHomeAsUpEnabled(true);
        }

        if (savedInstanceState == null) {
            mRecipe = getIntent().getParcelableExtra(Constants.EXTRA_RECIPE);
            mSelected = getIntent().getIntExtra(Constants.EXTRA_POSITION, 0);

            FragmentManager fragmentManager = getSupportFragmentManager();

            StepDetailFragment stepDetailFragment = new StepDetailFragment();
            stepDetailFragment.setSelected(mSelected);
            stepDetailFragment.setRecipe(mRecipe);
            fragmentManager.beginTransaction()
                    .add(R.id.step_detail_container, stepDetailFragment)
                    .commit();
        } else {
            mRecipe = savedInstanceState.getParcelable(Constants.STATE_EXTRA_RECIPE);
            mSelected = savedInstanceState.getInt(Constants.STATE_EXTRA_POSITION);
        }

        setTitle(mRecipe.name);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
