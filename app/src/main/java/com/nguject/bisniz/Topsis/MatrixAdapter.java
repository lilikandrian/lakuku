/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nguject.bisniz.Topsis;



import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
/**
 *
 * @author User
 */

public class MatrixAdapter {

    public int count(){
        return 900;
    }


    public double countColumn(double data[],int column){
        double value=0;
        for(int i=0;i<column;i++){
                value=value+(data[i]*data[i]);
        }
        return Math.sqrt(value);
    }


    public double countColumnSolution(double[] data,String key){
        Arrays.sort(data);      
        if(key=="Max"){
            return data[data.length-1];
        }else{
            return data[0];
        }
    }

    public double[] countRowDistance(char key, double[] productPrice, double[] productDesc, double[] ppView, double[] ppInterest, double[] feedback,double[] productPrice2, double[] productDesc2, double[] ppView2, double[] ppInterest2, double[] feedback2,int size){
        double value[]=new double[size];
        value[0]=0;
        for(int i=0;i<size;i++){
                if(key=='+'){
                    System.out.println("\n Price : "+productPrice2[i]+"-"+productPrice[0]);
                
                    value[i]=value[i]+Math.sqrt(Math.pow(productPrice2[i]-productPrice[0],2)+Math.pow(productDesc2[i]-productDesc[0],2)+Math.pow(ppView2[i]-ppView[0],2)+Math.pow(ppInterest2[i]-ppInterest[0],2)+Math.pow(feedback2[i]-feedback[0],2));
            
                }else{
                    System.out.println("\n Price : "+productPrice2[i]+"-"+productPrice[1]);
                
                    value[i]=value[i]+Math.sqrt(Math.pow(productPrice2[i]-productPrice[1],2)+Math.pow(productDesc2[i]-productDesc[1],2)+Math.pow(ppView2[i]-ppView[1],2)+Math.pow(ppInterest2[i]-ppInterest[1],2)+Math.pow(feedback2[i]-feedback[1],2));
        
                }
        }
        
        return value;
    }

    public double[] finalCount(double[] positive,double[] negative,int size) {

        double value[] = new double[size];
        value[0] = 0;
        for (int i = 0; i < size; i++) {
            value[i] = value[i] + (negative[i] / (positive[i] + negative[i]));
        }
        return value;
    }

}