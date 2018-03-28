/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nguject.bisniz.Topsis;


/**
 *
 * @author User
 */
public class CriteriaListData {
    String productName;
    double productPrice,productDesc,ppView,ppInterest,feedback;

    public CriteriaListData(String productName, double productPrice, double productDesc,double ppInterest,double ppView,double sellerFeedback ) {
        this.productName = productName;
        this.productPrice = productPrice;
        this.productDesc = productDesc;
        this.feedback=sellerFeedback;
        this.ppView=ppView;
        this.ppInterest=ppInterest;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public double getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(double productPrice) {
        this.productPrice = productPrice;
    }

    public double getProductDesc() {
        return productDesc;
    }

    public void setProductDesc(double productDesc) {
        this.productDesc = productDesc;
    }


    public double getPpView() {
        return ppView;
    }

    public void setPpView(double ppView) {
        this.ppView = ppView;
    }

    public double getPpInterest() {
        return ppInterest;
    }

    public void setPpInterest(double ppInterest) {
        this.ppInterest = ppInterest;
    }

    public double getFeedback() {
        return feedback;
    }

    public void setFeedback(double feedback) {
        this.feedback = feedback;
    }

    
}