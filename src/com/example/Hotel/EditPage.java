package com.example.Hotel;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

/**
 * Created by Code on 2016/7/11 0011.
 */
public class EditPage extends Activity {
    private RoomData roomData;
    private static EditText e_roomType1;
    private static EditText e_roomType2;
    private static EditText e_roomType3;
    private static EditText e_roomType4;
    private static EditText e_roomType5;
    private static EditText e_roomType6;
    private static EditText e_normalPrice1;
    private static EditText e_normalPrice2;
    private static EditText e_normalPrice3;
    private static EditText e_normalPrice4;
    private static EditText e_normalPrice5;
    private static EditText e_normalPrice6;
    private static EditText e_specialPrice1;
    private static EditText e_specialPrice2;
    private static EditText e_specialPrice3;
    private static EditText e_specialPrice4;
    private static EditText e_specialPrice5;
    private static EditText e_specialPrice6;
    private static EditText setMsg;
    private static Button button1;
    private static Button button2;

//    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.editpage);
        e_roomType1 = (EditText) findViewById(R.id.e_roomType1);
        e_roomType2 = (EditText) findViewById(R.id.e_roomType2);
        e_roomType3 = (EditText) findViewById(R.id.e_roomType3);
        e_roomType4 = (EditText) findViewById(R.id.e_roomType4);
        e_roomType5 = (EditText) findViewById(R.id.e_roomType5);
        e_roomType6 = (EditText) findViewById(R.id.e_roomType6);
        e_normalPrice1 = (EditText) findViewById(R.id.e_normalPrice1);
        e_normalPrice2 = (EditText) findViewById(R.id.e_normalPrice2);
        e_normalPrice3 = (EditText) findViewById(R.id.e_normalPrice3);
        e_normalPrice4 = (EditText) findViewById(R.id.e_normalPrice4);
        e_normalPrice5 = (EditText) findViewById(R.id.e_normalPrice5);
        e_normalPrice6 = (EditText) findViewById(R.id.e_normalPrice6);
        e_specialPrice1 = (EditText) findViewById(R.id.e_specialPrice1);
        e_specialPrice2 = (EditText) findViewById(R.id.e_specialPrice2);
        e_specialPrice3 = (EditText) findViewById(R.id.e_specialPrice3);
        e_specialPrice4 = (EditText) findViewById(R.id.e_specialPrice4);
        e_specialPrice5 = (EditText) findViewById(R.id.e_specialPrice5);
        e_specialPrice6 = (EditText) findViewById(R.id.e_specialPrice6);
        setMsg = (EditText) findViewById(R.id.setMsg);
        button1 = (Button) findViewById(R.id.positive);
        button2 = (Button) findViewById(R.id.negative);
        roomData = (RoomData) getApplication();
        e_roomType1.setText(roomData.getRoomType1());
        e_roomType2.setText(roomData.getRoomType2());
        e_roomType3.setText(roomData.getRoomType3());
        e_roomType4.setText(roomData.getRoomType4());
        e_roomType5.setText(roomData.getRoomType5());
        e_roomType6.setText(roomData.getRoomType6());
        e_normalPrice1.setText(roomData.getNormalPrice1() + "");
        e_normalPrice2.setText(roomData.getNormalPrice2() + "");
        e_normalPrice3.setText(roomData.getNormalPrice3() + "");
        e_normalPrice4.setText(roomData.getNormalPrice4() + "");
        e_normalPrice5.setText(roomData.getNormalPrice5() + "");
        e_normalPrice6.setText(roomData.getNormalPrice6() + "");
        e_specialPrice1.setText(roomData.getSpecialPrice1() + "");
        e_specialPrice2.setText(roomData.getSpecialPrice2() + "");
        e_specialPrice3.setText(roomData.getSpecialPrice3() + "");
        e_specialPrice4.setText(roomData.getSpecialPrice4() + "");
        e_specialPrice5.setText(roomData.getSpecialPrice5() + "");
        e_specialPrice6.setText(roomData.getSpecialPrice6() + "");
        setMsg.setText(roomData.getMsg());
        button2.setOnClickListener(new View.OnClickListener() {
//            @Override
            public void onClick(View v) {
                Intent intent = new Intent(EditPage.this, MyActivity.class);
                startActivity(intent);
            }
        });
        button1.setOnClickListener(new View.OnClickListener() {
//            @Override
            public void onClick(View v) {


                if (e_roomType1.length() != 0) {
                    roomData.setRoomType1(e_roomType1.getText().toString());
                    roomData.setNormalPrice1(Integer.parseInt(e_normalPrice1.getText().toString()));
                    roomData.setSpecialPrice1(Integer.parseInt(e_specialPrice1.getText().toString()));
                }

                if (e_roomType2.length() != 0) {
                    roomData.setRoomType2(e_roomType2.getText().toString());
                    roomData.setNormalPrice2(Integer.parseInt(e_normalPrice2.getText().toString()));
                    roomData.setSpecialPrice2(Integer.parseInt(e_specialPrice2.getText().toString()));
                }
                if (e_roomType3.length() != 0) {
                    roomData.setRoomType3(e_roomType3.getText().toString());
                    roomData.setNormalPrice3(Integer.parseInt(e_normalPrice3.getText().toString()));
                    roomData.setSpecialPrice3(Integer.parseInt(e_specialPrice3.getText().toString()));
                }
                if (e_roomType4.length() != 0) {

                    roomData.setRoomType4(e_roomType4.getText().toString());
                    roomData.setNormalPrice4(Integer.parseInt(e_normalPrice4.getText().toString()));
                    roomData.setSpecialPrice4(Integer.parseInt(e_specialPrice4.getText().toString()));
                }
                if (e_roomType5.length() != 0) {
                    roomData.setRoomType5(e_roomType5.getText().toString());
                    roomData.setNormalPrice5(Integer.parseInt(e_normalPrice5.getText().toString()));
                    roomData.setSpecialPrice5(Integer.parseInt(e_specialPrice5.getText().toString()));
                }
                if (e_roomType6.length() != 0) {
                    roomData.setRoomType6(e_roomType6.getText().toString());
                    roomData.setNormalPrice6(Integer.parseInt(e_normalPrice6.getText().toString()));
                    roomData.setSpecialPrice6(Integer.parseInt(e_specialPrice6.getText().toString()));
                }
                roomData.setMsg(setMsg.getText().toString());
                Intent intent = new Intent(EditPage.this, MyActivity.class);
                startActivity(intent);
            }
        });
    }
}
