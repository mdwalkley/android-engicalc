package us.walkley.mw.engicalc;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

//TODO: Rename app: EngiCalc, Calcineer, Engilator, Abdaraxus, Harpalus...

public class MainActivity extends AppCompatActivity {
    ExpandableListAdapter listAdapter;
    ExpandableListView expListView;
    List<String> listDataHeader;
    HashMap<String, List<String>> listDataChild;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initializeData();

        expListView = (ExpandableListView)findViewById(R.id.expandableListView_MainActivity);
        listAdapter = new ExpandableListAdapter_MainActivity(this, listDataHeader, listDataChild);
        expListView.setAdapter(listAdapter);


        //onClick Listener
        expListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener(){

            @Override
            public boolean onChildClick(ExpandableListView parent, View view, int i, int i1, long id) { // i=group, i1=child
                Intent intent = null;

                switch(i){ //i = group
                    case 0: //Simple Beam
                        switch(i1){ //i1 = child
                            case 0: //Uniformly Distributed Load
                                intent = new Intent(MainActivity.this, SimpleBeamUDL.class);
                                break;
                            case 1: //Concentrated Load at Any Point
                                //intent = new Intent(MainActivity.this, simpleBeamCLAP.class);
                                break;
                        }
                        break;
                    case 1: //Complex Beam
                        switch(i1){
                            case 0:

                                break;
                        }
                        break;
                    case 2: //More Coming Soon
                        switch(i1){
                            case 0:

                                break;
                        }
                        break;
                    default:
                }

                if(intent != null){startActivity(intent);}



//                Toast.makeText(getApplicationContext(),
//                        listDataHeader.get(i) + ":" +listDataChild.get(listDataHeader.get(i)).get(i1), Toast.LENGTH_SHORT).show();
                return false;
            }

        });

    }

    private void initializeData(){
        listDataHeader = new ArrayList<String>();
        listDataChild = new HashMap<String, List<String>>();

        //Adding Header Data
        listDataHeader.add("Simple Beam");
        listDataHeader.add("Complex Beam");
        listDataHeader.add("More Coming Soon...");

        //Adding Child Data
        List<String> simpleBeamList = new ArrayList<String>();
        simpleBeamList.add("Uniformly Distributed Load"); //0:0
        simpleBeamList.add("Concentrated Load at Any Point"); //0:1

        List<String> complexBeamList = new ArrayList<String>();
        complexBeamList.add("Fucked Up Load"); //1:0

        List<String> comingSoonList = new ArrayList<String>();
        comingSoonList.add("...but not yet."); //2:0

        listDataChild.put(listDataHeader.get(0), simpleBeamList);
        listDataChild.put(listDataHeader.get(1), complexBeamList);
        listDataChild.put(listDataHeader.get(2), comingSoonList);

    }

}
