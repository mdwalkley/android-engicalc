package us.walkley.mw.engicalc;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class SimpleBeamCLAP extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_simple_beam_clap); //getActionBar().setDisplayHomeAsUpEnabled(true);

        final Button calculate = (Button) findViewById(R.id.calculate_button);

        calculate.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                calculateAll(view);
            }
        });
    }

    private void calculateAll(View view){
        double p=0,a=0,b=0,x=0,e=0,i=0, l=0;

        try{
            p = Double.valueOf(((EditText)findViewById(R.id.input_P)).getText().toString());
            a = Double.valueOf(((EditText)findViewById(R.id.input_a)).getText().toString());
            b = Double.valueOf(((EditText)findViewById(R.id.input_b)).getText().toString());
            x = Double.valueOf(((EditText)findViewById(R.id.input_x)).getText().toString());
            l = a+b;

            ((TextView)findViewById(R.id.answer1)).setText(Double.toString(equation1(p,b,l)));
            ((TextView)findViewById(R.id.answer2)).setText(Double.toString(equation2(p,a,l)));
            ((TextView)findViewById(R.id.answer3)).setText(Double.toString(equation3(p,a,b,l)));
            ((TextView)findViewById(R.id.answer4)).setText(Double.toString(equation4(p,b,x,l)));
        }catch (IllegalStateException | NumberFormatException exc) {
            Toast.makeText(this, "Invalid input.", Toast.LENGTH_LONG).show();
        }

        try{
            e = Double.valueOf(((EditText)findViewById(R.id.input_ei)).getText().toString());
            i = Double.valueOf(((EditText)findViewById(R.id.input_ei)).getText().toString());
            double ei=e*i;

            ((TextView)findViewById(R.id.answer5)).setText(Double.toString(equation5(p, a, b, l, ei)));
            ((TextView)findViewById(R.id.answer6)).setText(Double.toString(equation6(p, a, b, l, ei)));
            ((TextView)findViewById(R.id.answer6)).setText(Double.toString(equation7(p, b, x, l, ei)));
            ((TextView)findViewById(R.id.answer6)).setText(Double.toString(equation8(p, a, x, l, ei)));
        }catch (IllegalStateException | NumberFormatException exc) {
            Toast.makeText(this, "Invalid input.", Toast.LENGTH_LONG).show();
        }

        hideKeyboard(this);

    }

    private double equation1(double P, double b, double l){
        return (P*b)/l;
    } //Pb/l

    private double equation2(double P, double a, double l){
        return (P*a)/l;
    } //Pa/l

    private double equation3(double P, double a, double b, double l){return (P*a*b)/l; } //Pab/l

    private double equation4(double P, double b, double x, double l){
        return (P*b*x/l);
    } //Pbx/l

    private double equation5(double P, double a, double b, double l, double EI){
        return (P*a*b*(a+2*b)*(Math.sqrt(3*a*(a+2*b)))/(27*EI*l));  //Pab(a+2b)*sq(3a(a+2b))/(27EI*l)
    }

    private double equation6(double P, double a, double b, double l, double EI){
        return ((P*a*a*b*b)/(3*EI*l));  //Pa^2b^2/3EI*l
    }

    private double equation7(double P, double b, double x, double l, double EI){
        return (((P*b*x)/(6*EI*l))*(l*l-b*b-x*x)); //(Pbx/6EI*l)*(l^2-b^2-x^2)
    }

    private double equation8(double P, double a, double x, double l, double EI){
        return (((P*a*(l-x))/(6*EI*l))*(2*l*x-x*x-a*a)); //(Pa(l-x)/6EI*l)*(2*l*x-x^2-a^2)
    }

    public static void hideKeyboard(Activity activity){
        InputMethodManager imm = (InputMethodManager) activity.getSystemService(Activity.INPUT_METHOD_SERVICE);
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
