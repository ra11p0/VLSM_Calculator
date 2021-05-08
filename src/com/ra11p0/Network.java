package com.ra11p0;

import java.util.ArrayList;

public class Network {
    private int _hostCount;
    private int[] _mask = new int[4];
    private int[] _networkAdress = new int[4];
    public String _name;

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
    public Network(String name, int hostCount)
    {
        int hosts = 2;
        while(hosts - 2 < hostCount) hosts *= 2;
        _hostCount = hosts;
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
                i++;
                mask = "" + x;
            }
        }
        _name = name;
    }

    public int get_hostCount() {
        return _hostCount;
    }

    public int[] get_mask() {
        return _mask;
    }

    public String get_maskString() {
        String temp = "";
        for(int x:_mask)temp=temp+x+".";
        return temp.substring(0, temp.length()-1);
    }

    public int get_maskDecimal()
    {
        String temp = "";
        for (int i: _mask)
        {
            temp = temp + Integer.toBinaryString(i);
        }
        return temp.length();
    }

    public int[] get_networkAdress() {
        return _networkAdress;
    }

    public String get_networkAdressString() {
        String temp = "";
        for(int x:_networkAdress)temp=temp+x+".";
        return temp.substring(0, temp.length()-1);
    }

    public String get_networkBroadcastAdressString() {
        String temp = "";
        int[] tempAdress = _networkAdress.clone();
        tempAdress[3] += _hostCount-1;
        for(int x:tempAdress)temp=temp+x+".";
        return temp.substring(0, temp.length()-1);
    }

    public void set_networkAdress(int[] _networkAdress) {
        this._networkAdress = _networkAdress.clone();
    }


}
