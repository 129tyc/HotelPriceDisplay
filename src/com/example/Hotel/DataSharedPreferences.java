package com.example.Hotel;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by Code on 2016/7/19 0019.
 */
public class DataSharedPreferences {
    private Context context;

    public DataSharedPreferences(Context context) {
        this.context = context;
    }

    public boolean SaveRoomType(String[] data) {
        boolean flag = false;
        SharedPreferences sharedPreferences = context.getSharedPreferences("RoomTypeData", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        for (int i = 0; i < 6; i++) {
            editor.putString("RoomType" + i, data[i]);
        }
        flag = editor.commit();
        return flag;
    }

    public boolean SaveNormalPrice(int[] data) {
        boolean flag = false;
        SharedPreferences sharedPreferences = context.getSharedPreferences("NormalPriceData", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        for (int i = 0; i < 6; i++) {
            editor.putInt("NormalPrice" + i, data[i]);
        }
        flag = editor.commit();
        return flag;
    }

    public boolean SaveSpecialPrice(int[] data) {
        boolean flag = false;
        SharedPreferences sharedPreferences = context.getSharedPreferences("SpecialPriceData", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        for (int i = 0; i < 6; i++) {
            editor.putInt("SpecialPrice" + i, data[i]);
        }
        flag = editor.commit();
        return flag;
    }

    public boolean SaveMsg(String data) {
        boolean flag = false;
        SharedPreferences sharedPreferences = context.getSharedPreferences("MsgData", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("Msg", data);
        flag = editor.commit();
        return flag;
    }

    public String[] ReadRoomType() {
        SharedPreferences sharedPreferences = context.getSharedPreferences("RoomTypeData", Context.MODE_PRIVATE);
        String[] temp = new String[6];
        for (int i = 0; i < 6; i++) {
            temp[i] = sharedPreferences.getString("RoomType" + i, "");
        }
        return temp;
    }

    public int[] ReadNormalPrice() {
        SharedPreferences sharedPreferences = context.getSharedPreferences("NormalPriceData", Context.MODE_PRIVATE);
        int[] temp = new int[6];
        for (int i = 0; i < 6; i++) {
            temp[i] = sharedPreferences.getInt("NormalPrice" + i, 0);
        }
        return temp;
    }

    public int[] ReadSpecialPrice() {
        SharedPreferences sharedPreferences = context.getSharedPreferences("SpecialPriceData", Context.MODE_PRIVATE);
        int[] temp = new int[6];
        for (int i = 0; i < 6; i++) {
            temp[i] = sharedPreferences.getInt("SpecialPrice" + i, 0);
        }
        return temp;
    }

    public String ReadMsg() {
        SharedPreferences sharedPreferences = context.getSharedPreferences("MsgData", Context.MODE_PRIVATE);
        String temp;
        temp = sharedPreferences.getString("Msg", "");
        return temp;
    }
}

