package com.example.andrearodriguez.tipcalcu.fragments;

import com.example.andrearodriguez.tipcalcu.models.TipRecord;

/**
 * Created by andrearodriguez on 6/3/16.
 */
public interface TipHistoryListFragmentListener {
    void addToList(TipRecord record);
    void clearList();
}
