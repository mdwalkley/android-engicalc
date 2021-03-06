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
import java.util.ArrayList;

public class SimpleBeamUDL extends EquationSet {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_simple_beam_udl);
        //Set onclick listeners
        setOnClickListeners(R.id.calculate_button, R.id.search_button_E, R.id.search_button_i);
    }

    @Override
    protected void onResume() {
        super.onResume();
        showKeyboard(findViewById(R.id.input_w));
    }

    void calculateAll(View view){
        double w=0, l=0, x=0, e=0, i=0;
        answerItemArrayList = new ArrayList<>();
        NumberFormat nf = NumberFormat.getInstance();

        try{
            w = Double.valueOf(((EditText)findViewById(R.id.input_w)).getText().toString());
            l = Double.valueOf(((EditText)findViewById(R.id.input_l)).getText().toString());
            x = Double.valueOf(((EditText)findViewById(R.id.input_x)).getText().toString());

            answerItemArrayList.add(new AnswerItem(R.drawable.simplebeam_udl_equation1, nf.format(equation1(w,l))));
            answerItemArrayList.add(new AnswerItem(R.drawable.simplebeam_udl_equation2, nf.format(equation2(w,l,x))));
            answerItemArrayList.add(new AnswerItem(R.drawable.simplebeam_udl_equation3, nf.format(equation3(w,l,x))));
            answerItemArrayList.add(new AnswerItem(R.drawable.simplebeam_udl_equation4, nf.format(equation4(w,l,x))));

            /*
            ((TextView)findViewById(R.id.answer1)).setText(nf.format(equation1(w,l)));
            ((TextView)findViewById(R.id.answer2)).setText(nf.format(equation2(w,l,x)));
            ((TextView)findViewById(R.id.answer3)).setText(nf.format(equation3(w,l,x)));
            ((TextView)findViewById(R.id.answer4)).setText(nf.format(equation4(w,l,x)));
            */

        }catch (IllegalStateException | NumberFormatException exc) {
            Toast.makeText(this, "Invalid input.", Toast.LENGTH_LONG).show();
        }

        try{
            i = Double.valueOf(((EditText)findViewById(R.id.input_i)).getText().toString());
            e = Double.valueOf(((EditText)findViewById(R.id.input_E)).getText().toString());
            double ei= e*i;

            answerItemArrayList.add(new AnswerItem(R.drawable.simplebeam_udl_equation5, nf.format(equation5(w,l,x, ei))));
            answerItemArrayList.add(new AnswerItem(R.drawable.simplebeam_udl_equation6, nf.format(equation6(w,l,x, ei))));

        /*
            ((TextView)findViewById(R.id.answer5)).setText(nf.format(equation5(w,l,x,ei)));
            ((TextView)findViewById(R.id.answer6)).setText(nf.format(equation6(w,l,x,ei)));
            */
        }catch (IllegalStateException | NumberFormatException exc) {
            Toast.makeText(this, "Invalid inputs for E and I.", Toast.LENGTH_LONG).show();
        }

        startAnswerFragment();

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

}
