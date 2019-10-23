package com.example.myapplicationandroid.Item;

public class DataItem {

    private int mImageResource;
    private String mText1;
    private String mText2;

    public DataItem(int imageResource, String text1, String text2){
        mImageResource = imageResource;
        mText1 = text1;
        mText2 = text2;
    }

    public int getImageResources(){
        return mImageResource;
    }

    public String getText1(){
        return mText1;
    }

    public String getText2(){
        return mText2;
    }
}
