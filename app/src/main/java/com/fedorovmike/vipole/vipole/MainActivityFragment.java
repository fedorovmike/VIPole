package com.fedorovmike.vipole.vipole;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.List;


/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment implements AdapterView.OnItemClickListener {
    private static final String LOG_TAG = MainActivityFragment.class.getSimpleName();
    ListView mLeftList;
    ListView mRightList;
    private ItemAdapter adapter;

    static final String[] PAGES = new String[] {"All Pages", "Page1", "Page2", "Page3", "Page4"};

    public MainActivityFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_main, container, false);
        mLeftList = (ListView) rootView.findViewById(R.id.lvList1);
        mRightList = (ListView) rootView.findViewById(R.id.lvList2);

        if (mLeftList !=null) {
            mLeftList.setAdapter(new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, PAGES));
            mLeftList.setOnItemClickListener(this);
        };

        List<ItemInfo> items = new ArrayList<ItemInfo>();
        for(int i = 0; i < 200; ++i) {
            items.add(new ItemInfo("Title " + i, "TYPE_"+ (i/4)%4, i));
        }

        adapter = new ItemAdapter(getActivity(), R.id.lvList2, items);
        mRightList.setAdapter(adapter);

        return rootView;
    }

    //Click on item in Pages List
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        //Log.d(LOG_TAG, "position = " + position + ", id = " + id);
        Integer mStart = 0;
        Integer mEnd   = 200;
        if (position > 0) {
            mStart = 50 * (position - 1);
            mEnd   = 50 * position;
        }

        List<ItemInfo> items = new ArrayList<ItemInfo>();
        for(int i = mStart; i < mEnd; ++i) {
            items.add(new ItemInfo("Title " + i, "TYPE_" + (i/4)%4, i));
        }
        adapter.clear();
        adapter.addAll(items);
    }
}
