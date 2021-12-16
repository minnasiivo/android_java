package com.example.appnbrone.companies;

public class Detail {
    String mName;
    String mId;
    String mForm;
    String mDate;
    int mPosition;
    public Detail(String mName, String mId, String mForm, String mDate, int mPosition) {
        this.mName = mName;
        this.mId = mId;
        this. mForm = mForm;
        this. mDate = mDate;
        this.mPosition = mPosition;
    }
    public String getName(){
        return mName;
    }
    public void setName(String mName) {
        mName = mName;
    }
    public String getId(){ return mId;}
    public void setId(String mId){mId =mId;}
    public String getForm(){return mForm;}
    public void setForm(String mForm){mForm = mForm;}
    public String getDate(){return  mDate;}
    public void setDate(String mDate){mDate = mDate;}

    public int getPosition() {
        return mPosition;
    }
    public void setPosition(int number) {
        mPosition = mPosition;

    }
}
