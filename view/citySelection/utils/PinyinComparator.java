package com.zt.agw.view.citySelection.utils;

/**
 * Created by come on 2016/12/6.
 */

import com.zt.agw.view.citySelection.bean.SortModel;

import java.util.Comparator;


public class PinyinComparator implements Comparator<SortModel>
{

    public int compare(SortModel o1, SortModel o2)
    {
        if (o1.getSortLetters().equals("@") || o2.getSortLetters().equals("#"))
        {
            return -1;
        } else if (o1.getSortLetters().equals("#") || o2.getSortLetters().equals("@"))
        {
            return 1;
        } else
        {
            return o1.getSortLetters().compareTo(o2.getSortLetters());
        }
    }

}

