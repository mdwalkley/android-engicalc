package us.walkley.mw.engicalc;


import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ExpandableListView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class ElasticityFragment extends Fragment {
    ElasticityFragment_ExpandableListAdapter listAdapter;
    ExpandableListView expandableListView;
    ArrayList<String> listDataHeader;
    HashMap<String, List<ElasticityFragment_MaterialChildInfo>> listDataChild;

    public ElasticityFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_elasticity, container, false);
        // Make activity view go away
        getActivity().findViewById(R.id.parentLayout).setVisibility(View.GONE);
        // Make fragment view visible
        getActivity().findViewById(R.id.elasticityFragment_Frame).setVisibility(View.VISIBLE);
        // Initialize data for expandable list
        populateList();
        // Initialize ExpandableListView and Adapter
        expandableListView = (ExpandableListView) view.findViewById(R.id.myList);
        listAdapter = new ElasticityFragment_ExpandableListAdapter(getActivity().getApplicationContext(), listDataHeader, listDataChild);
        expandableListView.setAdapter(listAdapter);

        expandableListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener(){

            @Override
            public boolean onChildClick(ExpandableListView parent, View view, int group, int child, long id) {
                // Grab selected E Value
                Double eVal = listDataChild.get(listDataHeader.get(group)).get(child).getEValue();
                //((EquationSet)getActivity()).onListFragmentInteraction_Elasticity(eVal);
                // Grab E Value EditText view from activity
                EditText inputView = getActivity().findViewById(R.id.input_E);
                // Set text in the EditText to the selected E Value
                inputView.setText(Double.toString(eVal));
                ((EquationSet)getActivity()).onBackPressed(R.id.elasticityFragment_Frame);
                return false;
            }

        });

        return view;
    }

    /*
    public interface OnListFragmentInteractionListener {
        void onListFragmentInteraction_Elasticity(Double e);
    }
*/
    private void populateList() {
        listDataHeader = new ArrayList<String>();
        listDataChild = new HashMap<String, List<ElasticityFragment_MaterialChildInfo>>();
        List<ElasticityFragment_MaterialChildInfo> inputList;

        //Douglas Fir-Larch
        listDataHeader.add("Douglas Fir-Larch");
        inputList = new ArrayList<>();
        inputList.add(new ElasticityFragment_MaterialChildInfo("Select Structural", 1900000));
        inputList.add(new ElasticityFragment_MaterialChildInfo("No. 1 & Btr", 1800000));
        inputList.add(new ElasticityFragment_MaterialChildInfo("No. 1", 1700000));
        inputList.add(new ElasticityFragment_MaterialChildInfo("No. 2", 1600000));
        inputList.add(new ElasticityFragment_MaterialChildInfo("No. 3", 1400000));
        listDataChild.put(listDataHeader.get(0), inputList);

        //Hem-Fir
        listDataHeader.add("Hem-Fir");
        inputList = new ArrayList<>();
        inputList.add(new ElasticityFragment_MaterialChildInfo("Select Structural", 1600000));
        inputList.add(new ElasticityFragment_MaterialChildInfo("No. 1 & Btr", 1500000));
        inputList.add(new ElasticityFragment_MaterialChildInfo("No. 1", 1500000));
        inputList.add(new ElasticityFragment_MaterialChildInfo("No. 2", 1300000));
        inputList.add(new ElasticityFragment_MaterialChildInfo("No. 3", 1200000));
        listDataChild.put(listDataHeader.get(1), inputList);

        //Redwood
        listDataHeader.add("Redwood");
        inputList = new ArrayList<>();
        inputList.add(new ElasticityFragment_MaterialChildInfo("Clear Structural", 1400000));
        inputList.add(new ElasticityFragment_MaterialChildInfo("Select Structural", 1400000));
        inputList.add(new ElasticityFragment_MaterialChildInfo("Select Structural, open grain", 1100000));
        inputList.add(new ElasticityFragment_MaterialChildInfo("No. 1", 1300000));
        inputList.add(new ElasticityFragment_MaterialChildInfo("No. 1, open grain", 1100000));
        inputList.add(new ElasticityFragment_MaterialChildInfo("No. 2", 1200000));
        inputList.add(new ElasticityFragment_MaterialChildInfo("No. 2, open grain", 1000000));
        inputList.add(new ElasticityFragment_MaterialChildInfo("No. 3", 1100000));
        inputList.add(new ElasticityFragment_MaterialChildInfo("No. 3, open grain", 900000));
        listDataChild.put(listDataHeader.get(2), inputList);

        //Western Cedars
        listDataHeader.add("Western Cedars");
        inputList = new ArrayList<>();
        inputList.add(new ElasticityFragment_MaterialChildInfo("Select Structural", 1100000));
        inputList.add(new ElasticityFragment_MaterialChildInfo("No. 1", 1000000));
        inputList.add(new ElasticityFragment_MaterialChildInfo("No. 2", 1000000));
        inputList.add(new ElasticityFragment_MaterialChildInfo("No. 3", 900000));
        listDataChild.put(listDataHeader.get(3), inputList);


        listDataHeader.add("More Coming Soon...");
        inputList = new ArrayList<>();
        inputList.add(new ElasticityFragment_MaterialChildInfo("...but not yet.", 0));
        listDataChild.put(listDataHeader.get(4), inputList);
    }
}