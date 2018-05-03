package us.walkley.mw.engicalc;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatImageButton;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.NumberFormat;

public class SimpleBeamUDL extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_simple_beam_udl); //getActionBar().setDisplayHomeAsUpEnabled(true);

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
            if (View.VISIBLE == findViewById(R.id.elasticityFragment_Frame).getVisibility()){
                // Make EFragment view go away
                findViewById(R.id.elasticityFragment_Frame).setVisibility(View.GONE);
            }
            /*if(View.VISIBLE == findViewById(R.id.iFragment_Frame).getVisibility()){
                // Make IFragment view go away
                findViewById(R.id.iFragment_Frame).setVisibility(View.GONE);
            }*/

            // Make activity view visible
            findViewById(R.id.parentLayout).setVisibility(View.VISIBLE);
            // (pop off backStack)
            getFragmentManager().popBackStack();
            // Set next focus
            showKeyboard(findViewById(R.id.input_i));
        }
    }



    private void startEFragment(){
        hideKeyboard(this);
        //getSupportFragmentManager().beginTransaction().replace(R.id.frag_frame, new EValueListFragment()).commit();
        FragmentManager manager = getFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(R.id.elasticityFragment_Frame, new ElasticityFragment());
        transaction.addToBackStack(null);
        transaction.commit();
    }

    private void calculateAll(View view){
        double w=0, l=0, x=0, e=0, i=0;
        NumberFormat nf = NumberFormat.getInstance();

        try{
            w = Double.valueOf(((EditText)findViewById(R.id.input_w)).getText().toString());
            l = Double.valueOf(((EditText)findViewById(R.id.input_l)).getText().toString());
            x = Double.valueOf(((EditText)findViewById(R.id.input_x)).getText().toString());

            ((TextView)findViewById(R.id.answer1)).setText(nf.format(equation1(w,l)));
            ((TextView)findViewById(R.id.answer2)).setText(nf.format(equation2(w,l,x)));
            ((TextView)findViewById(R.id.answer3)).setText(nf.format(equation3(w,l,x)));
            ((TextView)findViewById(R.id.answer4)).setText(nf.format(equation4(w,l,x)));
        }catch (IllegalStateException | NumberFormatException exc) {
            Toast.makeText(this, "Invalid input.", Toast.LENGTH_LONG).show();
        }

        try{
            i = Double.valueOf(((EditText)findViewById(R.id.input_i)).getText().toString());
            e = Double.valueOf(((EditText)findViewById(R.id.input_E)).getText().toString());
            double ei= e*i;

            ((TextView)findViewById(R.id.answer5)).setText(nf.format(equation5(w,l,x,ei)));
            ((TextView)findViewById(R.id.answer6)).setText(nf.format(equation6(w,l,x,ei)));
        }catch (IllegalStateException | NumberFormatException exc) {
            Toast.makeText(this, "Invalid inputs for E and I.", Toast.LENGTH_LONG).show();
        }

        hideKeyboard(this);

    }

    private double equation1(double w, double l){
        return (w*l)/2;
    }

    private double equation2(double w, double l, double x){
        return w*(l/2-x);
    }

    private double equation3(double w, double l, double x){
        return (w*l*l)/8; //(w*l^2)/8
    }

    private double equation4(double w, double l, double x){
        return (w*x/2)*(l-x);
    }

    private double equation5(double w, double l, double x, double EI){
        return (5*w*(Math.pow(l,4)))/(384*EI);  //(5*w*l^4)/(384*ei)
    }

    private double equation6(double w, double l, double x, double EI){
        return (w*x)/(24*EI)*(Math.pow(l,3)-2*l*x*x+Math.pow(x,3));  //((w*x)/(24*ei))*(l^3 - 2*l*x^2 + x^3);
    }

    public void onItemSelected(AdapterView<?> parent, View view, int pos, long id){
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

}