package us.walkley.mw.engicalc;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.NumberFormat;

public class SimpleBeamCLAP extends EquationSet {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_simple_beam_clap);

        //Set Onclick Listeners
        setOnClickListeners(R.id.calculate_button, R.id.search_button_E, R.id.search_button_i);
    }

    @Override
    protected void onResume() {
        super.onResume();
        showKeyboard(findViewById(R.id.input_P));
    }

    void calculateAll(View view){
        double p=0,a=0,b=0,x=0,l=0,e=0,i=0;
        NumberFormat nf = NumberFormat.getInstance();

        try{
            p = Double.valueOf(((EditText)findViewById(R.id.input_P)).getText().toString());
            a = Double.valueOf(((EditText)findViewById(R.id.input_a)).getText().toString());
            b = Double.valueOf(((EditText)findViewById(R.id.input_b)).getText().toString());
            x = Double.valueOf(((EditText)findViewById(R.id.input_x)).getText().toString());
            l = a+b;


            ((TextView)findViewById(R.id.answer1)).setText(nf.format(equation1(p,b,l)));
            ((TextView)findViewById(R.id.answer2)).setText(nf.format(equation2(p,a,l)));
            ((TextView)findViewById(R.id.answer3)).setText(nf.format(equation3(p,a,b,l)));
            ((TextView)findViewById(R.id.answer4)).setText(nf.format(equation4(p,b,x,l)));
        }catch (IllegalStateException | NumberFormatException exc) {
            Toast.makeText(this, "Invalid input.", Toast.LENGTH_LONG).show();
        }

        try{
            e = Double.valueOf(((EditText)findViewById(R.id.input_E)).getText().toString());
            i = Double.valueOf(((EditText)findViewById(R.id.input_i)).getText().toString());

            ((TextView)findViewById(R.id.answer5)).setText(nf.format(equation5(p, a, b, l, e, i)));
            ((TextView)findViewById(R.id.answer6)).setText(nf.format(equation6(p, a, b, l, e, i)));
            ((TextView)findViewById(R.id.answer7)).setText(nf.format(equation7(p, b, x, l, e, i)));
            ((TextView)findViewById(R.id.answer8)).setText(nf.format(equation8(p, a, x, l, e, i)));
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

    private double equation5(double P, double a, double b, double l, double E, double I){
        return (P*a*b*(a+2*b)*(Math.sqrt(3*a*(a+2*b)))/(27*E*I*l));  //Pab(a+2b)*sq(3a(a+2b))/(27EI*l)
    }

    private double equation6(double P, double a, double b, double l, double E, double I){
        return ((P*a*a*b*b)/(3*E*I*l));  //Pa^2b^2/3EI*l
    }

    private double equation7(double P, double b, double x, double l, double E, double I){
        return (((P*b*x)/(6*E*I*l))*(l*l-b*b-x*x)); //(Pbx/6EI*l)*(l^2-b^2-x^2)
    }

    private double equation8(double P, double a, double x, double l, double E, double I){
        return (((P*a*(l-x))/(6*E*I*l))*(2*l*x-x*x-a*a)); //(Pa(l-x)/6EI*l)*(2*l*x-x^2-a^2)
    }

}
