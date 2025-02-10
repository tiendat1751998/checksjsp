package com.datdev.paging;

import com.datdev.sort.Sorter;

public interface Pageble {
    Integer getPage();
    Integer getOffSet();
    Integer getLimit();
    Sorter getSorter();
}
