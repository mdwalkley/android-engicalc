package us.walkley.mw.engicalc;

import android.content.Context;
import android.os.Bundle;
import android.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ExpandableListView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import us.walkley.mw.engicalc.dummy.DummyContent;
import us.walkley.mw.engicalc.dummy.DummyContent.DummyItem;

/**
 * A fragment representing a list of Items.
 * <p/>
 * Activities containing this fragment MUST implement the {@link OnListFragmentInteractionListener}
 * interface.
 */
public class InertiaFragment extends Fragment {

    private static final String ARG_COLUMN_COUNT = "column-count";
    private int mColumnCount = 1;
    private OnListFragmentInteractionListener mListener;
    private ArrayList<ElasticityFragment_MaterialChildInfo> listData;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public InertiaFragment() {
    }

    // TODO: Customize parameter initialization
    @SuppressWarnings("unused")
    public static InertiaFragment newInstance(int columnCount) {
        InertiaFragment fragment = new InertiaFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_COLUMN_COUNT, columnCount);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            mColumnCount = getArguments().getInt(ARG_COLUMN_COUNT);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_inertia, container, false);

        // Make activity view go away
        getActivity().findViewById(R.id.parentLayout).setVisibility(View.GONE);
        // Make fragment view visible
        getActivity().findViewById(R.id.inertiaFragment_Frame).setVisibility(View.VISIBLE);
        // Initialize data for expandable list
        populateList();

        // Set the adapter
        if (view instanceof RecyclerView) {
            Context context = view.getContext();
            RecyclerView recyclerView = (RecyclerView) view;
            if (mColumnCount <= 1) {
                recyclerView.setLayoutManager(new LinearLayoutManager(context));
            } else {
                recyclerView.setLayoutManager(new GridLayoutManager(context, mColumnCount));
            }
            recyclerView.setAdapter(new InertiaFragment_RecyclerViewAdapter(listData, mListener));
        }

        return view;
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnListFragmentInteractionListener) {
            mListener = (OnListFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnListFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnListFragmentInteractionListener {
        void onListFragmentInteraction_Inertia(ElasticityFragment_MaterialChildInfo i);
    }

    private void populateList() {
        listData = new ArrayList<>();

        listData.add(new ElasticityFragment_MaterialChildInfo("2 x 2", 5.71));
        listData.add(new ElasticityFragment_MaterialChildInfo("2 x 4", 2.12));
        listData.add(new ElasticityFragment_MaterialChildInfo("2 x 6", 1.65));
        listData.add(new ElasticityFragment_MaterialChildInfo("2 x 8", 0.32));
        listData.add(new ElasticityFragment_MaterialChildInfo("2 x 10", 0.15));
        listData.add(new ElasticityFragment_MaterialChildInfo("4 x 4", 5.88));
        listData.add(new ElasticityFragment_MaterialChildInfo("More coming soon...", 0));

    }

}
