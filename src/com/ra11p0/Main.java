package com.ra11p0;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

import static javax.swing.WindowConstants.EXIT_ON_CLOSE;

public class Main {

    public static void main(String[] args) {

        /*
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




	new Vlsm_calculator(networks, rootAdress);

        for (Network i: networks)
        {
            System.out.println(i.get_hostCount() + ", " + i.get_networkAdressString() + ", " + i.get_maskString());
        }
*/

        new Window();

    }
}
