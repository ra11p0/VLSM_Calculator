package com.ra11p0;

public class Vlsm_calculator {
    public Vlsm_calculator()
    {
        Network newNetwork = new Network(60);
        for (int i: newNetwork.get_mask())
        {
            System.out.print(i + ".");
        }

    }
}
