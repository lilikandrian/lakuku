package com.nguject.bisniz.Topsis;
/**
 *
 * @author User
 */
import java.util.Scanner;
import java.util.ArrayList;
import java.math.*;
import java.util.Arrays;
import java.text.DecimalFormat;

public class Topsis {


    private static DecimalFormat df=new DecimalFormat("#.##");
    CriteriaListData source=new CriteriaListData(null,0.0,0.0,0.0,0.0,0.0);

    ArrayList<CriteriaListData> sellerProduct=new ArrayList<CriteriaListData>();
    ArrayList<CriteriaListData> Matrix1=new ArrayList<CriteriaListData>();
    ArrayList<CriteriaListData> Matrix2=new ArrayList<CriteriaListData>();
    ArrayList<CriteriaListData> Matrix3=new ArrayList<CriteriaListData>();
    ArrayList<CriteriaListData> MatrixHelper=new ArrayList<CriteriaListData>();

    public MatrixAdapter mx=new MatrixAdapter();
    //Insert User Data

    public double productDesc[]=new double[100];
    public double productPrice[]=new double[100];
    public double ppView[]=new double[100];
    public double ppInterest[]=new double[100];
    public double feedback[]=new double[100];
    public void firstData(String productName,double productPrice,double productDesc,double ppInterest,double ppView,double sellerFeedback){

        sellerProduct.add(new CriteriaListData(productName,productPrice,productDesc,ppInterest,ppView,sellerFeedback));
    }

    public void dataInput(String productName,double productPrice,double productDesc,double ppInterest,double ppView,double sellerFeedback){

        System.out.println( " Desc "+productDesc);
        source.setProductName(productName);
        source.setProductPrice(productPrice);
        source.setProductDesc(productDesc);
        source.setFeedback(sellerFeedback);
        source.setPpView(ppView);
        source.setPpInterest(ppInterest);
        sellerProduct.add(new CriteriaListData(source.getProductName(),source.getProductPrice(),source.getProductDesc(),source.getPpInterest(),source.getPpView(),source.getFeedback()));

    }

    public int dataOutput(){
        int size=sellerProduct.size();
        System.out.println("\n____________________________________________________________________\n");
        System.out.println(" |Nama         | Price   | Desc  | Interest | View  | Feedback \n");

        for(CriteriaListData data:sellerProduct){

            System.out.println(" |"+sellerProduct.indexOf(data)+""+data.getProductName()+"     "+data.getProductPrice()+"     "+data.getProductDesc()+"    "+data.getPpInterest()+"     "+data.getPpView()+"     "+data.getFeedback());

            //Insert ArrayList data to array Matrix 1
            productPrice[sellerProduct.indexOf(data)]=data.getProductPrice();
            productDesc[sellerProduct.indexOf(data)]=data.getProductDesc();
            feedback[sellerProduct.indexOf(data)]=data.getFeedback();
            ppView[sellerProduct.indexOf(data)]=data.getPpView();
            ppInterest[sellerProduct.indexOf(data)]=data.getPpInterest();

        }
        return size;
    }

    public void getdataOutput2(double dPrice,double dDesc,double dInterest,double dppView,double dFeedback){
        int size=sellerProduct.size();

        System.out.println("\n______________________________Matrix 1________________________________\n");
        System.out.println(" |Nama         | Price   | Desc   | Interest| View | Feedback \n");


        for(CriteriaListData data:sellerProduct){

            System.out.println(" | "+data.getProductName()+"     "+df.format(data.getProductPrice()/dPrice)+"     "+df.format(data.getProductDesc()/dDesc)+"     "+df.format(data.getPpInterest()/dInterest)+"     "+df.format(data.getPpView() / dppView)+"     "+df.format(data.getFeedback()/dFeedback));


            //Insert ArrayList data to array Matrix 2
            productPrice[sellerProduct.indexOf(data)]=data.getProductPrice();
            productDesc[sellerProduct.indexOf(data)]=data.getProductDesc();
            ppView[sellerProduct.indexOf(data)]=data.getPpView();
            ppInterest[sellerProduct.indexOf(data)]=data.getPpInterest();
            feedback[sellerProduct.indexOf(data)]=data.getFeedback();
            Matrix1.add(new CriteriaListData(data.getProductName(),Double.parseDouble(df.format(data.getProductPrice()/dPrice)),Double.parseDouble(df.format(data.getProductDesc()/dDesc)),Double.parseDouble(df.format(data.getPpInterest() / dInterest)),Double.parseDouble(df.format(data.getPpView()/dppView)),Double.parseDouble(df.format(data.getFeedback()/dFeedback))));

        }


    }


    public void getdataOutput3(){
        int size=sellerProduct.size();

        double productDesc[]=new double[size];
        double productPrice[]=new double[size];
        double ppView[]=new double[size];
        double ppInterest[]=new double[size];
        double feedback[]=new double[size];
        //Weight Criteria Data 1 - 5
        int wPrices=5,wDesc=4,wView=2,wInterest=2,wFeedback=4;
        double vPrices,vDesc,vView,vInterest,vFeedback;

        //int wPrices=1,wDesc=1,wView=1,wLevel=1,wInterest=1,wFeedback=1;


        System.out.println("\n____________________________Matrix 2____________________________________\n");
        System.out.println(" |Nama         | Price   | Desc   | Interest| View | Feedback \n");
        for(CriteriaListData data:Matrix1){

            if(data.getProductPrice()==0){
                vPrices=0;
            }else{
                vPrices=Double.parseDouble(df.format(wPrices*data.getProductPrice()));
            }
            if(data.getProductDesc()==0){
                vDesc=0;
            }else{
                vDesc=Double.parseDouble(df.format(wDesc*data.getProductDesc()));
            }

            if(data.getPpInterest()==0){
                vInterest=0;
            }else{
                vInterest=Double.parseDouble(df.format(wInterest*data.getPpInterest()));
            }

            if(data.getPpView()==0){
                vView=0;
            }else{
                vView=Double.parseDouble(df.format(wView*data.getPpView()));
            }

            if(data.getFeedback()==0){
                vFeedback=0;
            }else{
                vFeedback=Double.parseDouble(df.format(wFeedback*data.getFeedback()));
            }

            System.out.println(" | "+data.getProductName()+"     "+vPrices+"     "+vDesc+"     "+vInterest+"     "+vView+"     "+vFeedback);

            //Insert ArrayList data to array Matrix 2
            productPrice[Matrix1.indexOf(data)]=vPrices;
            productDesc[Matrix1.indexOf(data)]=vDesc;
            ppView[Matrix1.indexOf(data)]=vView;
            ppInterest[Matrix1.indexOf(data)]=vInterest;
            feedback[Matrix1.indexOf(data)]=vFeedback;
            MatrixHelper.add(new CriteriaListData(data.getProductName(),vPrices,vDesc,vInterest,vView,vFeedback));
        }

        System.out.println(" Positive Solution +: "+ mx.countColumnSolution(productPrice,"Min")+"   "+mx.countColumnSolution(productDesc,"Max")+"   "+mx.countColumnSolution(ppInterest,"Max")+"  "+mx.countColumnSolution(ppView,"Max")+"  "+mx.countColumnSolution(feedback, "Max"));
        System.out.println(" Negative Solution -: "+ mx.countColumnSolution(productPrice,"Max")+"   "+mx.countColumnSolution(productDesc,"Min")+"   "+mx.countColumnSolution(ppInterest,"Min")+"  "+mx.countColumnSolution(ppView,"Min")+"  "+mx.countColumnSolution(feedback, "Min"));


        Matrix2.add(new CriteriaListData("Positive Solution +: ",mx.countColumnSolution(productPrice,"Min"),mx.countColumnSolution(productDesc,"Max"),mx.countColumnSolution(ppInterest,"Max"),mx.countColumnSolution(ppView,"Max"),mx.countColumnSolution(feedback, "Max")));

        Matrix2.add(new CriteriaListData("Negative Solution -: ",mx.countColumnSolution(productPrice,"Max"),mx.countColumnSolution(productDesc,"Min"),mx.countColumnSolution(ppInterest,"Min"),mx.countColumnSolution(ppView,"Min"),mx.countColumnSolution(feedback, "Min")));

    }


    public void getdataOutput4(){
        //int size=sellerProduct.size();
        System.out.println("\n____________________________Matrix 3____________________________________\n");
        System.out.println(" |Nama         | Price   | Desc   | Interest| View | Feedback \n");


        for(CriteriaListData data:Matrix2){
            System.out.println(" | "+data.getProductName()+"     "+df.format(data.getProductPrice())+"     "+df.format(data.getProductDesc())+"     "+df.format(data.getPpInterest())+"     "+df.format(data.getPpView())+"     "+df.format(data.getFeedback()));

        }
    }

    public double getdataOutput5(){
        int size=Matrix2.size();

        double productDesc[]=new double[size];
        double productPrice[]=new double[size];
        double sellerLevel[]=new double[size];
        double ppView[]=new double[size];
        double ppInterest[]=new double[size];
        double feedback[]=new double[size];

        //Weight Criteria Data 1 - 5
        for(CriteriaListData data:Matrix2){
            //Insert ArrayList data to array Matrix 2
            productPrice[Matrix2.indexOf(data)]=data.getProductPrice();
            productDesc[Matrix2.indexOf(data)]=data.getProductDesc();
            ppView[Matrix2.indexOf(data)]=data.getPpView();
            ppInterest[Matrix2.indexOf(data)]=data.getPpInterest();
            feedback[Matrix2.indexOf(data)]=data.getFeedback();

        }
        int size2=sellerProduct.size();
        double productDesc2[]=new double[size2];
        double productPrice2[]=new double[size2];
        double sellerLevel2[]=new double[size2];
        double ppView2[]=new double[size2];
        double ppInterest2[]=new double[size2];
        double feedback2[]=new double[size2];


        for(CriteriaListData data:MatrixHelper){

            productPrice2[MatrixHelper.indexOf(data)]=data.getProductPrice();
            productDesc2[MatrixHelper.indexOf(data)]=data.getProductDesc();
            ppView2[MatrixHelper.indexOf(data)]=data.getPpView();
            ppInterest2[MatrixHelper.indexOf(data)]=data.getPpInterest();
            feedback2[MatrixHelper.indexOf(data)]=data.getFeedback();

        }


        double positiveDistance[]=mx.countRowDistance('+',productPrice, productDesc, ppView, ppInterest, feedback, productPrice2, productDesc2, ppView2, ppInterest2, feedback2, size2);
        System.out.println("\n___________Matrix 4 +____________\n");

        for(int i=0;i<size2;i++){
            System.out.println("| "+i+" | "+df.format(positiveDistance[i]));
        }

        double negativeDistance[]=mx.countRowDistance('-',productPrice, productDesc, ppView, ppInterest, feedback, productPrice2, productDesc2, ppView2, ppInterest2, feedback2, size2);
        System.out.println("\n___________Matrix 4 -____________\n");

        for(int i=0;i<size2;i++){
            System.out.println("| "+i+" | "+df.format(negativeDistance[i]));
        }

        double preferenceValue[]=mx.finalCount(positiveDistance,negativeDistance, size2);
        System.out.println("\n___________Final Result of Prediction____________\n");
        double result=0.0;
        for(int i=0;i<size2;i++){
            System.out.print("| "+i+" | "+Double.parseDouble(df.format(preferenceValue[i]*100)));System.out.println("%");
            if(i==(size2-1)){
                result=preferenceValue[i]*100;
            }
        }
        System.out.println(" Result "+result);
        return Double.parseDouble(df.format(result));
    }

}