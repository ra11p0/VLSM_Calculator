package com.ra11p0;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Window extends JFrame {
    public Window()
    {
        FlowLayout layout = new FlowLayout();
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLayout(layout);
        this.setVisible(true);
        this.setPreferredSize(new Dimension(440, 680));
        this.setResizable(false);

        Object[][] data = {{"Kielce", "192.168.1.10", "a", "/24"},
                           {"Warszawa", "192.168.2.10", "a", "/24"},
                           {"Radom", "192.168.3.10", "a", "/24"}};
        String[] columnNames = {"Name", "Network address", "Broadcast address", "Mask"};



        JButton calculateButton = new JButton("Calculate");
        JButton addButton = new JButton("Add record");
        JButton removeButton = new JButton("Remove last record");

        DefaultTableModel inputTableModel = new DefaultTableModel();

        JTable inputTable = new JTable(inputTableModel);
        JTable outputTable = new JTable(data, columnNames);

        JScrollPane inputScrollPane = new JScrollPane(inputTable);
        JScrollPane outputScrollPane = new JScrollPane(outputTable);

        JPanel tableButtons = new JPanel(new GridLayout(2, 1));

        JTextArea rootAddressArea = new JTextArea();

        rootAddressArea.setPreferredSize(new Dimension(400, 20));

        inputTableModel.addColumn("Name");
        inputTableModel.addColumn("Number of hosts");


        inputTable.setSize(400, 200);

        outputTable.setSize(new Dimension(400, 200));
        outputTable.setEnabled(false);

        addButton.addActionListener(e -> inputTableModel.addRow(new Object[]{"*name*", "*hosts count*"}));

        removeButton.addActionListener(e -> {
            try {
                inputTableModel.removeRow(inputTableModel.getRowCount() - 1);
            }
            catch (ArrayIndexOutOfBoundsException x)
            {
                new JOptionPane().showMessageDialog(this, "There is no record to remove!","Error!", JOptionPane.ERROR_MESSAGE);
            }
        });

        calculateButton.addActionListener(e -> {
            if (inputTableModel.getRowCount() == 0 ) {
                new JOptionPane().showMessageDialog(this, "Input table is empty!","Error!", JOptionPane.ERROR_MESSAGE);
                return;}
            List<Network> networks = new ArrayList<>();
            for (int i = 0; i< inputTableModel.getRowCount(); i++) {
                if (inputTable.isEditing()) inputTable.getCellEditor().stopCellEditing();
                String name = inputTableModel.getValueAt(i, 0).toString();
                int hosts;
                try
                {
                    hosts = Integer.parseInt(inputTable.getValueAt(i, 1).toString());
                }
                catch (NumberFormatException x)
                {
                    new JOptionPane().showMessageDialog(this, "Number format exception in input table!","Error!", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                networks.add(new Network(name, hosts));
            }
            int[] rootAddress = new int[4];

            String temp = "";
            int addressOctet = 0;
            rootAddressArea.setText(rootAddressArea.getText() + '.');
            for(char c: rootAddressArea.getText().toCharArray())
            {
                if (c != '.')temp = temp + c;
                else {
                    try
                    {
                        int i = Integer.parseInt(temp);
                        rootAddress[addressOctet] = i;
                        temp = "";
                        addressOctet++;
                    }
                    catch(NumberFormatException x)
                    {
                        new JOptionPane().showMessageDialog(this, "Number format exception in address field!","Error!", JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                }

            }
            rootAddressArea.setText(rootAddressArea.getText().substring(0, rootAddressArea.getText().length()-1));

            new Vlsm_calculator(networks, rootAddress);

            for (Network i: networks)
            {
                System.out.println(i.get_hostCount() + ", " + i.get_networkAdressString() + ", " + i.get_maskString());
            }


        });

        inputScrollPane.setPreferredSize(new Dimension(400, 200));
        outputScrollPane.setPreferredSize(new Dimension(400, 200));

        tableButtons.setPreferredSize(new Dimension(400, 100));
        tableButtons.add(addButton);
        tableButtons.add(removeButton);

        this.add(new JLabel("Output"));
        this.add(outputScrollPane);
        this.add(new JLabel("Input"));
        this.add(inputScrollPane);
        this.add(tableButtons);
        this.add(new JLabel("Network address:"));
        this.add(rootAddressArea);
        this.add(calculateButton);
        this.pack();

    }
}
