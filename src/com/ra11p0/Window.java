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
        this.setPreferredSize(new Dimension(440, 640));
        this.setResizable(false);

        Object[][] data = {{"Kielce", "192.168.1.10", "a", "/24"},
                           {"Warszawa", "192.168.2.10", "a", "/24"},
                           {"Radom", "192.168.3.10", "a", "/24"}};
        String[] columnNames = {"Name", "Network address", "Broadcast address", "Mask"};



        JButton calculateButton = new JButton();
        JButton addButton = new JButton();
        JButton removeButton = new JButton();

        DefaultTableModel inputTableModel = new DefaultTableModel();

        JTable inputTable = new JTable(inputTableModel);
        JTable outputTable = new JTable(data, columnNames);

        JScrollPane inputScrollPane = new JScrollPane(inputTable);
        JScrollPane outputScrollPane = new JScrollPane(outputTable);

        JPanel tableButtons = new JPanel(new GridLayout(2, 1));


        inputTableModel.addColumn("Name");
        inputTableModel.addColumn("Number of hosts");


        inputTable.setSize(400, 200);

        outputTable.setSize(new Dimension(400, 200));
        outputTable.setEnabled(false);

        addButton.addActionListener(e -> inputTableModel.addRow(new Object[]{"*name*", "*hosts count*"}));
        addButton.setText("Add record");

        removeButton.addActionListener(e -> inputTableModel.removeRow(inputTableModel.getRowCount()-1));
        removeButton.setText("Remove record");

        calculateButton.setMaximumSize(new Dimension(100, 40));
        calculateButton.setText("Calculate");
        calculateButton.addActionListener(e ->
        {
            List<Network> networks = new ArrayList<Network>();
            for (int i = 0; i< inputTableModel.getRowCount(); i++)
            {
                if (inputTable.isEditing()) inputTable.getCellEditor().stopCellEditing();
                String name = inputTableModel.getValueAt(i, 0).toString();
                int hosts;
                try
                {
                    hosts = Integer.parseInt(inputTable.getValueAt(i, 1).toString());
                }
                catch (NumberFormatException x)
                {
                    System.err.println("Not a number");
                    return;
                }
                networks.add(new Network(name, hosts));
            }

            for (Network i: networks)
            {
                System.out.println(i.get_hostCount() + ", " + i._name);
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
        this.add(calculateButton);
        this.pack();

    }
}
