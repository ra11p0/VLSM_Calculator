package com.ra11p0;

import java.util.ArrayList;

public class CollectionsMath<e> {
    public int count(ArrayList<e> list, e item)
    {
        int occurrences = 0;
        for(e x: list) if (x == item) occurrences++;
        return occurrences;
    }
    public int selectMax(ArrayList<e> list, e item)
    {
        int occurrences = 0;
        for(e x: list) if (x == item) occurrences++;
        return occurrences;
    }
}
