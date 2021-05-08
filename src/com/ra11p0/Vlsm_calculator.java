package com.ra11p0;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Vlsm_calculator {
    public Vlsm_calculator(List<Network> networks, int[] rootAdress)
    {
        int[] tempAdress = rootAdress;
        Collections.sort(networks, new Comparator<Network>() {
            @Override
            public int compare(Network o1, Network o2) {
                return Integer.compare(o2.get_hostCount(), o1.get_hostCount());
            }
        });
        for(Network x: networks)
        {
            if (tempAdress[3] + x.get_hostCount() > 256) {
                tempAdress[2] += 1;
                tempAdress[3] = 0;
            }
            x.set_networkAdress(tempAdress);
            tempAdress[3] += x.get_hostCount();
        }
    }
}
