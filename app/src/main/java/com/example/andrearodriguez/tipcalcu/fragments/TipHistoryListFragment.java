package com.example.andrearodriguez.tipcalcu.fragments;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.andrearodriguez.tipcalcu.R;
import com.example.andrearodriguez.tipcalcu.activities.TipDetailActivity;
import com.example.andrearodriguez.tipcalcu.adapters.OnItemClickListener;
import com.example.andrearodriguez.tipcalcu.adapters.TipAdapter;
import com.example.andrearodriguez.tipcalcu.models.TipRecord;

import butterknife.Bind;
import butterknife.ButterKnife;


/**
 * A simple {@link Fragment} subclass.
 */
public class TipHistoryListFragment extends Fragment implements TipHistoryListFragmentListener, OnItemClickListener {

    @Bind(R.id.recycleView)
    RecyclerView recycleView;
    private TipAdapter adapter;


    public TipHistoryListFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_tip_history_list, container, false);
        ButterKnife.bind(this, view);
        initAdapter();
        initRecyclerView();
        return view;
    }

    private void initRecyclerView() {
        recycleView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recycleView.setAdapter(adapter);

    }

    private void initAdapter() {
        if (adapter == null) {
            adapter = new TipAdapter(getActivity().getApplicationContext(), this);
        }
    }


    @Override
    public void addToList(TipRecord record) {
        adapter.add(record);

    }

    @Override
    public void clearList() {
        adapter.clear();

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @Override
    public void OnItemclick(TipRecord tipRecord) {

        Intent intent = new Intent(getActivity(), TipDetailActivity.class);
        intent.putExtra(TipDetailActivity.TIP_KEY, tipRecord.getTip());
        intent.putExtra(TipDetailActivity.DATE_KEY, tipRecord.getDateFormatted());
        intent.putExtra(TipDetailActivity.BILL_TOTAL_KEY, tipRecord.getBill());
        startActivity(intent);
    }
}
