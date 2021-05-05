package com.ra11p0;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Network {
    public int get_hostCount() {
        return _hostCount;
    }

    private int _hostCount;

    public int[] get_mask() {
        return _mask;
    }

    private int[] _mask = new int[4];

    public Network(int mask[])
    {
        ArrayList<Character> tempMask = new ArrayList<Character>();
        _mask = mask;
        for(int i: mask)
        {
            String temp = Integer.toBinaryString(i);
            while (temp.length() < 8) temp = '0' + temp;
            for (char x:temp.toCharArray()) tempMask.add(x);
        }

        _hostCount = (int)Math.pow(2, new CollectionsMath().count(tempMask, '0'));
    }
    public Network(int hostCount)
    {
        int hosts = 2;
        while(hosts - 2 < hostCount) hosts *= 2;
        _hostCount = hostCount;
        String mask = Integer.toBinaryString(hosts-1) + "1";
        while(mask.length() <= 32) mask = 0 + mask;

        String fixedMask = "";
        for(char x: mask.toCharArray()) fixedMask = fixedMask + (x == '0' ? '1':'0');

        mask = "";
        int i = 0;
        for(char x:fixedMask.toCharArray())
        {
            if (mask.length() < 8)
            {
                mask = mask + x;
            }
            else
            {
                _mask[i] = Integer.parseInt(mask, 2);
                System.out.println(mask);
                i++;
                mask = "" + x;
            }
        }

    }
}
