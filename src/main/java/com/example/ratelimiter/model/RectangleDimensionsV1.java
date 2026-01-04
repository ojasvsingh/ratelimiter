package com.example.ratelimiter.model;

//Create a RectangleDimensions object from the json request

public class RectangleDimensionsV1{

    private double length;
    private double width;

    public RectangleDimensionsV1(){} //default constructor

    public RectangleDimensionsV1(double length, double width){
        this.length = length;
        this.width = width;
    }

    public double getLength(){
        return this.length;
    }
    public void setLength(double length){
        this.length = length;
    }
    public double getWidth(){
        return this.width;
    }
    public void setWidth(double width){
        this.width = width;
    }
}
