/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.customermanage;

/**
 *
 * @author MO
 */
public class ArrayCustomerList  {
    
      public static final int MAXSIZE = 1000;
    public Customer[] customers;
    public int size;

    
    
    public ArrayCustomerList() {
        customers = new Customer[MAXSIZE];
        size = 0;
    }


    
     
     public int linearSearch(int contractId) {
        for (int i = 0; i < size; i++) {
            if (customers[i].getContractId() ==contractId) {
                return i+1 ;
            }
        }
        return -1;
    }
     
     
     
public int binarySearch(int contractId) {
  
        int left = 0;
        int right = size - 1;

        while (left <= right) {
            int mid = (left+right)/2;

            if (customers[mid].getContractId()==contractId) {
                return mid+1;
            } else if (customers[mid].getContractId() < contractId) {
                left = mid + 1+1;
            } else {
                right = mid ;
            }
        }

        return -1;
    }



public void deleteCustomer(int contractId) {
        for (int i = 0; i < size; i++) {
            if (customers[i].getContractId()== contractId) {
                for (int j = i; j < size - 1; j++) {
                    customers[j] = customers[j + 1];
                }
                customers[size - 1] = null;
                size--;
                break;
            }
        }
    }

public void InsertionSort()
    {
        for (int i = 1; i < size; i++)
        {
            Customer key = customers[i];
            int j = i - 1;

            while (j >= 0 && customers[j].getContractId()> key.getContractId())
            {
                customers[j + 1] = customers[j];
                j--;
            }

            customers[j + 1] = key;
        }
    }
     

     
     public void HeapSort()
    {
        for (int i = size / 2 - 1; i >= 0; i--)
        {
            Heapify(size, i);
        }

        for (int i = size - 1; i >= 0; i--)
        {
            Customer temp = customers[0];
            customers[0] = customers[i];
            customers[i] = temp;

            Heapify(i, 0);
        }
    }
    public void Heapify(int n, int i)
    {
        int largest = i;
        int leftChild = 2 * i + 1;
        int rightChild = 2 * i + 2;

        if (leftChild < n && customers[leftChild].getContractId() > customers[largest].getContractId())
        {
            largest = leftChild;
        }

        if (rightChild < n && customers[rightChild].getContractId() > customers[largest].getContractId())
        {
            largest = rightChild;
        }

        if (largest != i)
        {
            Customer temp = customers[i];
            customers[i] = customers[largest];
            customers[largest] = temp;

            Heapify(n, largest);
        }
    }
    
    
   
     public void addCustomer(Customer customer) {
        if (size < MAXSIZE) {
            customers[size] = customer;
            size++;
        } else {
            System.out.println("The list is full.");
        }
    
     }
     
    public Customer searchCustomer(int contract_ID) 
{
        for (int i = 0; i < size; i++) {
            if(customers[i].getContractId() == contract_ID) {
                return customers[i];
            }
        }
        return null;
    }

    public void printall(){
        for (int i = 0; i < size; i++) {
            System.out.println("Contract ID: " + customers[i].getContractId() + "\n" +
                "Name: " + customers[i].getName() + "\n" +
                "Nationality: " + customers[i].getNationality() + "\n" +
                "Phone: " + customers[i].getPhone() + "\n" +
                "Current Bill Amount: " + customers[i].getCurrentBillAmount() + "\n" +
                "Accumulated Bill Amount: " + customers[i].getAccumulatedBillAmount() + "\n");
            
        }
    }
} 
    
    
    
   
 
    

