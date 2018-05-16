package us.walkley.mw.engicalc;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import java.util.ArrayList;

//TODO: Rename app: EngiCalc, Calcineer, Engilator, Abdaraxus, Harpalus...
//TODO: Update Elasticity Fragment to match I fragment?
//TODO: Update Equation Set UI
//TODO: Make x optional input
//TODO: Import and complete Print functionality

public class MainActivity extends AppCompatActivity {
    ListAdapter listAdapter1;
    ArrayList<MainActivity_EquationSetListItem> listData;
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initializeData();

        listView = (ListView)findViewById(R.id.listView_MainActivity);
        listAdapter1 = new MainActivity_ListAdapter(this, listData);
        listView.setAdapter(listAdapter1);

        //onClick Listener
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener(){

            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = listData.get(i).getIntent();
                if(null != intent){startActivity(intent);}
            }
        });
    }


    private void initializeData(){
        listData = new ArrayList<>();

        listData.add(new MainActivity_EquationSetListItem("Simple Beam", "Uniformly Distributed Load",
                new Intent(MainActivity.this, SimpleBeamUDL.class)));
        listData.add(new MainActivity_EquationSetListItem("Simple Beam", "Concentrated Load at Any Point",
                new Intent(MainActivity.this, SimpleBeamCLAP.class)));
        listData.add(new MainActivity_EquationSetListItem("Complex Beam", "Fucked up Load", null));
        listData.add(new MainActivity_EquationSetListItem("Coming Soon", "But Not Yet...", null));

    }

}
