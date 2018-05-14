package us.walkley.mw.engicalc;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatImageButton;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;

/**
 * Created by michael_walkley on 5/3/2018.
 */

public abstract class EquationSet extends AppCompatActivity
        implements InertiaFragment.OnListFragmentInteractionListener{ //, ElasticityFragment.OnListFragmentInteractionListener

    //Variables
    protected int activityLayout;

    EquationSet (int layoutID){
        activityLayout = layoutID;
    }

    //Abstract methods
    abstract void calculateAll(View view);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(activityLayout); //getActionBar().setDisplayHomeAsUpEnabled(true);

        //Set onclick listeners
        //Calculate button
        final Button calculateButton = (Button) findViewById(R.id.calculate_button);
        calculateButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                calculateAll(view);
            }
        });

        //ElasticityFragment button
        AppCompatImageButton elasticityButton = (AppCompatImageButton) findViewById(R.id.search_button_E);
        elasticityButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                startEFragment();
            }
        });

        //IFragment button
        AppCompatImageButton inertiaButton = (AppCompatImageButton) findViewById(R.id.search_button_i);
        inertiaButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                startIFragment();
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        showKeyboard(findViewById(R.id.input_w));
    }

    @Override
    protected void onPause() {
        super.onPause();
        hideKeyboard(this);
    }

    @Override
    public void onBackPressed(){
        if(getFragmentManager().getBackStackEntryCount() == 0){
            super.onBackPressed();
        }else{
            // Make fragment view go away
            findViewById(R.id.elasticityFragment_Frame).setVisibility(View.GONE);
            // Make activity view visible
            findViewById(R.id.parentLayout).setVisibility(View.VISIBLE);
            // (pop off backStack)
            getFragmentManager().popBackStack();
            // Set next focus
            showKeyboard(findViewById(R.id.input_i));
        }
    }

    //Set focus and show keyboard
    private void showKeyboard(View view){
        view.requestFocus();
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.toggleSoftInput(InputMethodManager.SHOW_FORCED, 0);
        //this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);
    }

    public static void hideKeyboard(Activity activity){
        InputMethodManager imm = (InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE);
        //Find the currently focused view, so we can grab the correct window token from it.
        View view = activity.getCurrentFocus();
        //If no view currently has focus, create a new one, just so we can grab a window token from it
        if (view == null){
            view = new View(activity);
        }
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        view.clearFocus();
    }


    //Elasticity Fragment
    private void startEFragment(){
        hideKeyboard(this);
        //getSupportFragmentManager().beginTransaction().replace(R.id.frag_frame, new EValueListFragment()).commit();
        FragmentManager manager = getFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(R.id.elasticityFragment_Frame, new ElasticityFragment());
        transaction.addToBackStack(null);
        transaction.commit();
    }
/*
    @Override
    public void onListFragmentInteraction_Elasticity(Double e) {
        // Grab E Value EditText View from activity
        EditText inputView = findViewById(R.id.input_E);
        // Set text in the EditText to the selected E Value
        inputView.setText(Double.toString(e));
        onBackPressed();
    }
*/
    //Inertia Fragment
    private void startIFragment(){
        hideKeyboard(this);
        FragmentManager manager = getFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(R.id.elasticityFragment_Frame, new ElasticityFragment());
        transaction.addToBackStack(null);
        transaction.commit();}

    @Override
    public void onListFragmentInteraction_Inertia(ElasticityFragment_MaterialChildInfo iVal) {
        // Grab I Value EditText view from activity
        EditText inputView = findViewById(R.id.input_i);
        // Set text in the EditText to the selected I Value
        inputView.setText(Double.toString(iVal.getEValue()));
        onBackPressed();
    }
}

