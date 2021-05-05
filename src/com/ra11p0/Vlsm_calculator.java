package com.ra11p0;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Vlsm_calculator {
    public Vlsm_calculator()
    {
        List<Network> networks = new ArrayList<Network>();
        networks.add(new Network(8));
        networks.add(new Network(60));
        networks.add(new Network(20));
        networks.add(new Network(40));
        networks.add(new Network(20));
        networks.add(new Network(2));
        networks.add(new Network(8));
        networks.add(new Network(120));
        networks.add(new Network(84));

        int[] rootAdress = new int[4];
        rootAdress[0] = 192;
        rootAdress[1] = 168;
        rootAdress[2] = 10;
        rootAdress[3] = 0;
        int[] tempAdress = rootAdress;

        Collections.sort(networks, new Comparator<Network>() {
            @Override
            public int compare(Network o1, Network o2) {
                return Integer.compare(o2.get_hostCount(), o1.get_hostCount());
            }
        });

        for(Network x: networks)
        {
            if (tempAdress[3] + x.get_hostCount() >= 255) {
                tempAdress[2] += 1;
                tempAdress[3] = 0;
            }
            x.set_networkAdress(tempAdress);
            tempAdress[3] += x.get_hostCount();
        }

        for (Network i: networks)
        {
            System.out.println(i.get_hostCount() + ", " + i.get_networkAdressString() + ", " + i.get_maskString());
        }

    }
}
