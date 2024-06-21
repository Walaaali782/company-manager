/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.customermanage;

/**
 *
 * @author MO
 */
public class Customer {
     int contractId;
     String name;
     String nationality;
     String phone;
     double currentBillAmount;
     double accumulatedBillAmount;

    public Customer(int contractId, String name, String nationality, String phone, double currentBillAmount, double accumulatedBillAmount) {
        this.contractId = contractId;
        this.name = name;
        this.nationality = nationality;
        this.phone = phone;
        this.currentBillAmount = currentBillAmount;
        this.accumulatedBillAmount = accumulatedBillAmount;
    }

    public int getContractId() {
        return contractId;
    }

    public String getName() {
        return name;
    }

    public String getNationality() {
        return nationality;
    }

    public String getPhone() {
        return phone;
    }

    public double getCurrentBillAmount() {
        return currentBillAmount;
    }

    public double getAccumulatedBillAmount() {
        return accumulatedBillAmount;
    }

    public void setCurrentBillAmount(double currentBillAmount) {
        this.currentBillAmount = currentBillAmount;
    }

    public void setAccumulatedBillAmount(double accumulatedBillAmount) {
        this.accumulatedBillAmount = accumulatedBillAmount;
    }

   
    public void print() {
        System.out.println("Contract ID: " + contractId + "\n" +
                "Name: " + name + "\n" +
                "Nationality: " + nationality + "\n" +
                "Phone: " + phone + "\n" +
                "Current Bill Amount: " + currentBillAmount + "\n" +
                "Accumulated Bill Amount: " + accumulatedBillAmount + "\n");
    }
}


