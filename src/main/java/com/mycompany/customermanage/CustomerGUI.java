/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.customermanage;

/**
 *
 * @author MO
 */
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class CustomerGUI extends JFrame implements ActionListener {
    private static final long serialVersionUID = 1L;
    private ArrayList<Customer> customerList;
    private JTable customerTable;
    private DefaultTableModel tableModel;
    private JTextField contractIdField, nameField, nationalityField, phoneField, currentBillAmountField, accumulatedBillAmountField;
    
    public CustomerGUI() {
        super("Customers Management System");
        customerList = new ArrayList<Customer>();
        tableModel = new DefaultTableModel(new Object[][] {}, new String[] {"Contract ID", "Name", "Nationality", "Phone", "Current Bill Amount", "Accumulated Bill Amount"});
        customerTable = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(customerTable);
        scrollPane.setBorder(BorderFactory.createTitledBorder("Customer List"));
        add(scrollPane, BorderLayout.CENTER);
        
        JPanel inputPanel = new JPanel(new GridLayout(6, 2));
        inputPanel.setBorder(BorderFactory.createTitledBorder("Customer Information"));
        inputPanel.add(new JLabel("Contract ID"));
        contractIdField = new JTextField();
        inputPanel.add(contractIdField);
        inputPanel.add(new JLabel("Name"));
        nameField = new JTextField();
        inputPanel.add(nameField);
        inputPanel.add(new JLabel("Nationality"));
        nationalityField = new JTextField();
        inputPanel.add(nationalityField);
        inputPanel.add(new JLabel("Phone"));
        phoneField = new JTextField();
        inputPanel.add(phoneField);
        inputPanel.add(new JLabel("Current Bill Amount"));
        currentBillAmountField = new JTextField();
        inputPanel.add(currentBillAmountField);
        inputPanel.add(new JLabel("Accumulated Bill Amount"));
        accumulatedBillAmountField = new JTextField();
        inputPanel.add(accumulatedBillAmountField);
        add(inputPanel, BorderLayout.NORTH);
        
        JPanel buttonPanel = new JPanel();
        buttonPanel.setBorder(BorderFactory.createTitledBorder("Actions"));
        JButton addButton = new JButton("Add");
        addButton.addActionListener(this);
        JButton deleteButton = new JButton("Delete");
        deleteButton.addActionListener(this);
        JButton searchButton = new JButton("Search");
        searchButton.addActionListener(this);
        JButton sortButton = new JButton("Sort");
        sortButton.addActionListener(this);
        buttonPanel.add(addButton);
        buttonPanel.add(deleteButton);
        buttonPanel.add(searchButton);
        buttonPanel.add(sortButton);
        add(buttonPanel, BorderLayout.SOUTH);
        
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }
    
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("Add")) {
            try {
                int contractId = Integer.parseInt(contractIdField.getText());
                String name = nameField.getText();
                String nationality = nationalityField.getText();
                String phone = phoneField.getText();
                double currentBillAmount = Double.parseDouble(currentBillAmountField.getText());
                double accumulatedBillAmount = Double.parseDouble(accumulatedBillAmountField.getText());
                Customer customer = new Customer(contractId, name, nationality, phone, currentBillAmount, accumulatedBillAmount);
                customerList.add(customer);
                updateTable();
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Invalid input");
            }
        } else if (e.getActionCommand().equals("Delete")) {
            try {
                int contractId = Integer.parseInt(contractIdField.getText());
                boolean found = false;
                for (int i = 0; i < customerList.size(); i++) {
                    Customer customer = customerList.get(i);
                    if (customer.getContractId() == contractId) {
                        customerList.remove(i);
                        found = true;
                        break;
                    }
                }
                if (found) {
                    updateTable();
                } else {
                    JOptionPane.showMessageDialog(this, "Customer not found");
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Invalid input");
            }
        } else if (e.getActionCommand().equals("Search")) {
            try {
                int contractId = Integer.parseInt(contractIdField.getText());
                boolean found = false;
                for (int i = 0; i < customerList.size(); i++) {
                    Customer customer = customerList.get(i);
                    if (customer.getContractId() == contractId) {
                        JOptionPane.showMessageDialog(this, customer.getName());
                        found = true;
                        break;
                    }
                }
                if (!found) {
                    JOptionPane.showMessageDialog(this, "Customer not found");
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Invalid input");
            }
        } else if (e.getActionCommand().equals("Sort")) {
            Collections.sort(customerList, new Comparator<Customer>() {
                public int compare(Customer c1, Customer c2) {
                    return c1.getContractId() - c2.getContractId();
                }
            });
            updateTable();
        }
    }
    
    private void updateTable() {
        tableModel.setRowCount(0);
        for (Customer customer : customerList) {
            Object[] row = {customer.getContractId(), customer.getName(), customer.getNationality(), customer.getPhone(), customer.getCurrentBillAmount(), customer.getAccumulatedBillAmount()};
            tableModel.addRow(row);
        }
    }
   
//    public static void main(String[] args) {
//        new CustomerGUI();
//    }
}