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
import java.util.Iterator;
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

public class CustomerGUII extends JFrame implements ActionListener {
    private static final long serialVersionUID = 1L;
    private CustomerLinkedList customerList;
    private JTable customerTable;
    private DefaultTableModel tableModel;
    private JTextField contractIdField, nameField, nationalityField, phoneField, currentBillAmountField, accumulatedBillAmountField;
    
    public CustomerGUII() {
        super("Customers Management System");
        customerList = new CustomerLinkedList();
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
                customerList.insert(customer);
                updateTable();
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Invalid input");
            }
        } else if (e.getActionCommand().equals("Delete")) {
            try {
                int contractId = Integer.parseInt(contractIdField.getText());
                boolean deleted = customerList.delete(contractId);
                if (deleted) {
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
                Customer customer = customerList.linearSearch(contractId);
                if (customer != null) {
                    JOptionPane.showMessageDialog(this, customer.getName());
                } else {
                    JOptionPane.showMessageDialog(this, "Customer not found");
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Invalid input");
            }
        } else if (e.getActionCommand().equals("Sort")) {
            customerList.insertionSort();
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
    
   
}

class CustomerLinkedList implements Iterable<Customer> {
    private Node head;
    private int size;
    
    public CustomerLinkedList() {
        head = null;
        size = 0;
    }
    
    public void insert(Customer customer) {
        Node newNode = new Node(customer);
        if (head == null) {
            head = newNode;
        } else {
            Node current = head;
            while (current.next != null) {
                current = current.next;
            }
            current.next = newNode;
        }
        size++;
    }
    
    public boolean delete(int contractId) {
        if (head == null) {
            return false;
        }
        if (head.customer.getContractId() == contractId) {
            head = head.next;
            size--;
            return true;
        }
        Node prev = head;
        Node current = head.next;
        while (current != null) {
            if (current.customer.getContractId() == contractId) {
                prev.next = current.next;
                size--;
                return true;
            }
            prev = current;
            current = current.next;
        }
        return false;
    }
    
    public Customer linearSearch(int contractId) {
        Node current = head;
        while (current != null) {
            if (current.customer.getContractId() == contractId) {
                return current.customer;
            }
            current = current.next;
        }
        return null;
    }
    
    public Customer binarySearch(int contractId) {
        // not implemented
        return null;
    }
    
    public void insertionSort() {
        if (head == null || head.next == null) {
            return;
        }
        Node sorted = head;
        Node current = head.next;
        sorted.next = null;
        while (current != null) {
            Node next = current.next;
            if (current.customer.getContractId() < sorted.customer.getContractId()) {
                current.next = sorted;
                sorted = current;
            } else {
                Node prev = sorted;
                Node temp = sorted.next;
                while (temp != null && current.customer.getContractId() > temp.customer.getContractId()) {
                    prev = temp;
                    temp = temp.next;
                }
                prev.next = current;
                current.next = temp;
            }
            current = next;
        }
        head = sorted;
    }
    
    public void heapSort() {
        // not implemented
    }
    
    public int size() {
        return size;
    }
    
    public boolean isEmpty() {
        return size == 0;
    }
    
    public void clear() {
        head = null;
        size = 0;
    }
    
    public Iterator<Customer> iterator() {
        return new CustomerIterator();
    }
    
    private class Node {
        Customer customer;
        Node next;
        
        public Node(Customer customer) {
            this.customer = customer;
            next = null;
        }
    }
    
    private class CustomerIterator implements Iterator<Customer> {
        private Node current;
        
        public CustomerIterator() {
            current = head;
        }
        
        public boolean hasNext() {
            return current != null;
        }
        
        public Customer next() {
            Customer customer = current.customer;
            current = current.next;
            return customer;
        }
    }
}
