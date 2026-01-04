package com.example.ratelimiter.model;

//Creates json response from the area calculation

public class AreaV1{
    
    private String shape;
    private double area;

    public AreaV1(){}

    public AreaV1(String shape, double area){
        this.shape = shape;
        this.area = area;
    }

    public String getShape(){
        return this.shape;
    }
    public void setShape(String shape){
        this.shape = shape;
    }
    public double getArea(){
        return this.area;
    }
    public void setArea(double area){
        this.area = area;
    }
}

