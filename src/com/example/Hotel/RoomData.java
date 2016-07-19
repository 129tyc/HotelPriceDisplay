package com.example.Hotel;

import android.app.Application;

/**
 * Created by Code on 2016/7/11 0011.
 */
public class RoomData extends Application {

    private String[] roomType = new String[6];
    private int[] normalPrice = new int[6];
    private int[] specialPrice = new int[6];
    public String msg;

    public String[] getRoomType() {
        return roomType;
    }

    public void setRoomType(String[] roomType) {
        this.roomType = roomType;
    }

    public int[] getNormalPrice() {
        return normalPrice;
    }

    public void setNormalPrice(int[] normalPrice) {
        this.normalPrice = normalPrice;
    }

    public int[] getSpecialPrice() {
        return specialPrice;
    }

    public void setSpecialPrice(int[] specialPrice) {
        this.specialPrice = specialPrice;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }


    @Override
    public void onCreate() {
        super.onCreate();
        setMsg("");
        String[] tempRoomType = new String[6];
        int[] tempNormalPrice = new int[6];
        int[] tempSpecialPrice = new int[6];
        for (int i = 0; i < 6; i++) {
            tempRoomType[i] = "";
            tempNormalPrice[i] = 0;
            tempSpecialPrice[i] = 0;
        }
        setRoomType(tempRoomType);
        setNormalPrice(tempNormalPrice);
        setSpecialPrice(tempSpecialPrice);
    }

}
