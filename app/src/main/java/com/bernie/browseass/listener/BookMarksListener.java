package com.bernie.browseass.listener;

import bernie.greendao.dao.BrowseAssBookMarks;

/**
 * Created by bernie.shi on 2016/9/7.
 */

public interface BookMarksListener {
    void chooseBookMark(String webSite);
    void fixBookMark(BrowseAssBookMarks bookMarks);
}
