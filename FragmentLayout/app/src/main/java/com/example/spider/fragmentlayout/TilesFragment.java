package com.example.spider.fragmentlayout;

import android.app.FragmentTransaction;
import android.app.ListFragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

/***
 * Created by spider on 1/7/16.
 */

public class TilesFragment extends ListFragment {
    boolean mDuelPane;
    int mCurCheckPosition = 0;

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        ArrayAdapter connectArrayToListView = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_activated_1, SuperHeroInfo.NAMES);
        setListAdapter(connectArrayToListView);

        View detailsFrame = getActivity().findViewById(R.id.details);
        mDuelPane = detailsFrame != null && detailsFrame.getVisibility() == View.VISIBLE;

        if (savedInstanceState != null) {
            mCurCheckPosition = savedInstanceState.getInt("curChoice", 0);
        }

        if (mDuelPane) {
            getListView().setChoiceMode(ListView.CHOICE_MODE_SINGLE);
            showDetails(mCurCheckPosition);
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("curChoice", mCurCheckPosition);
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);
        showDetails(position);
    }

    private void showDetails(int index) {
        mCurCheckPosition = index;
        if (mDuelPane) {
            getListView().setItemChecked(index, true);
        }
        DetailsFragment details = (DetailsFragment) getFragmentManager().findFragmentById(R.id.details);
        if (details == null || details.getShownIndex() != index) {

            // Make the details fragment and give it the currently selected hero index
            details = DetailsFragment.newInstance(index);

            // Start Fragment transactions
            FragmentTransaction ft = getFragmentManager().beginTransaction();

            // Replace any other Fragment with our new Details Fragment with the right data
            ft.replace(R.id.details, details);

            // TRANSIT_FRAGMENT_FADE calls for the Fragment to fade away
            ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
            ft.commit();
        } else {
            // Launch a new Activity to show our DetailsFragment
            Intent intent = new Intent();

            // Define the class Activity to call
            intent.setClass(getActivity(), DetailsActivity.class);

            // Pass along the currently selected index assigned to the keyword index
            intent.putExtra("index", index);

            // Call for the Activity to open
            startActivity(intent);
        }
    }
}
