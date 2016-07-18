package com.example.Hotel;

import android.app.Application;

/**
 * Created by Code on 2016/7/11 0011.
 */
public class RoomData extends Application {
    public String roomType1;
    public String roomType2;
    public String roomType3;
    public String roomType4;
    public String roomType5;
    public String roomType6;
    public int normalPrice1;
    public int normalPrice2;
    public int normalPrice3;
    public int normalPrice4;
    public int normalPrice5;
    public int normalPrice6;
    public int specialPrice1;
    public int specialPrice2;
    public int specialPrice3;
    public int specialPrice4;
    public int specialPrice5;
    public int specialPrice6;
    public String msg;

    public void setRoomType1(String roomType1) {
        this.roomType1 = roomType1;
    }

    public void setRoomType2(String roomType2) {
        this.roomType2 = roomType2;
    }

    public void setRoomType3(String roomType3) {
        this.roomType3 = roomType3;
    }

    public void setRoomType4(String roomType4) {
        this.roomType4 = roomType4;
    }

    public void setRoomType5(String roomType5) {
        this.roomType5 = roomType5;
    }

    public void setNormalPrice1(int normalPrice1) {
        this.normalPrice1 = normalPrice1;
    }

    public void setNormalPrice2(int normalPrice2) {
        this.normalPrice2 = normalPrice2;
    }

    public void setNormalPrice3(int normalPrice3) {
        this.normalPrice3 = normalPrice3;
    }

    public void setNormalPrice4(int normalPrice4) {
        this.normalPrice4 = normalPrice4;
    }

    public void setNormalPrice5(int normalPrice5) {
        this.normalPrice5 = normalPrice5;
    }

    public void setSpecialPrice1(int specialPrice1) {
        this.specialPrice1 = specialPrice1;
    }

    public void setSpecialPrice2(int specialPrice2) {
        this.specialPrice2 = specialPrice2;
    }

    public void setSpecialPrice3(int specialPrice3) {
        this.specialPrice3 = specialPrice3;
    }

    public void setSpecialPrice4(int specialPrice4) {
        this.specialPrice4 = specialPrice4;
    }

    public void setSpecialPrice5(int specialPrice5) {
        this.specialPrice5 = specialPrice5;
    }

    public String getRoomType6() {
        return roomType6;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public void setRoomType6(String roomType6) {
        this.roomType6 = roomType6;
    }

    public int getNormalPrice6() {
        return normalPrice6;
    }

    public void setNormalPrice6(int normalPrice6) {
        this.normalPrice6 = normalPrice6;
    }

    public int getSpecialPrice6() {
        return specialPrice6;
    }

    public void setSpecialPrice6(int specialPrice6) {
        this.specialPrice6 = specialPrice6;
    }

    public String getRoomType1() {
        return roomType1;
    }

    public String getRoomType2() {
        return roomType2;
    }

    public String getRoomType3() {
        return roomType3;
    }

    public String getRoomType4() {
        return roomType4;
    }

    public String getRoomType5() {
        return roomType5;
    }

    public int getNormalPrice1() {
        return normalPrice1;
    }

    public int getNormalPrice2() {
        return normalPrice2;
    }

    public int getNormalPrice3() {
        return normalPrice3;
    }

    public int getNormalPrice4() {
        return normalPrice4;
    }

    public int getNormalPrice5() {
        return normalPrice5;
    }

    public int getSpecialPrice1() {
        return specialPrice1;
    }

    public int getSpecialPrice2() {
        return specialPrice2;
    }

    public int getSpecialPrice3() {
        return specialPrice3;
    }

    public int getSpecialPrice4() {
        return specialPrice4;
    }

    public int getSpecialPrice5() {
        return specialPrice5;
    }

//    @Override
    public void onCreate() {
        super.onCreate();
        setRoomType1("");
        setSpecialPrice1(0);
        setNormalPrice1(0);
        setRoomType2("");
        setSpecialPrice2(0);
        setNormalPrice2(0);
        setRoomType3("");
        setSpecialPrice3(0);
        setNormalPrice3(0);
        setRoomType4("");
        setSpecialPrice4(0);
        setNormalPrice4(0);
        setRoomType5("");
        setSpecialPrice5(0);
        setNormalPrice5(0);
        setRoomType6("");
        setSpecialPrice6(0);
        setNormalPrice6(0);
        setMsg("");
    }

}
