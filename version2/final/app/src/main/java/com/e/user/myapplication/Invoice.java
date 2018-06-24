package com.e.user.myapplication;

/**
 * Created by User on 2018/6/24.
 */

public class Invoice {
    private int[] _invoice;
    private int i =0,_type=0;

    Invoice(int[] invoice){
        _invoice = invoice;
    }

    public void addNumber(int number){
        _invoice[i] = number;
        i++;
    }
    public int getNumber(int number){
        return _invoice[number];
    }
    public int[] getAllNumber(){
        return _invoice;
    }

    public void setType(int type){
        _type = type;
    }
    public int getType(){
        return _type;
    }
}
