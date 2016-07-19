package com.example.Hotel;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
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
    private static String[] tempRoomType;
    private static int[] tempNormalPrice;
    private static int[] tempSpecialPrice;
    private static String msg;
    private static EditText[] editTextArray = new EditText[18];
    private static ProgressDialog progressDialog;
    private static EditText setMsg;
    private static Button button1;
    private static Button button2;
    private static Button button3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.editpage);
        editTextArray[0] = (EditText) findViewById(R.id.e_roomType1);
        editTextArray[1] = (EditText) findViewById(R.id.e_roomType2);
        editTextArray[2] = (EditText) findViewById(R.id.e_roomType3);
        editTextArray[3] = (EditText) findViewById(R.id.e_roomType4);
        editTextArray[4] = (EditText) findViewById(R.id.e_roomType5);
        editTextArray[5] = (EditText) findViewById(R.id.e_roomType6);
        editTextArray[6] = (EditText) findViewById(R.id.e_normalPrice1);
        editTextArray[7] = (EditText) findViewById(R.id.e_normalPrice2);
        editTextArray[8] = (EditText) findViewById(R.id.e_normalPrice3);
        editTextArray[9] = (EditText) findViewById(R.id.e_normalPrice4);
        editTextArray[10] = (EditText) findViewById(R.id.e_normalPrice5);
        editTextArray[11] = (EditText) findViewById(R.id.e_normalPrice6);
        editTextArray[12] = (EditText) findViewById(R.id.e_specialPrice1);
        editTextArray[13] = (EditText) findViewById(R.id.e_specialPrice2);
        editTextArray[14] = (EditText) findViewById(R.id.e_specialPrice3);
        editTextArray[15] = (EditText) findViewById(R.id.e_specialPrice4);
        editTextArray[16] = (EditText) findViewById(R.id.e_specialPrice5);
        editTextArray[17] = (EditText) findViewById(R.id.e_specialPrice6);
        setMsg = (EditText) findViewById(R.id.setMsg);
        button1 = (Button) findViewById(R.id.positive);
        button2 = (Button) findViewById(R.id.negative);
        button3 = (Button) findViewById(R.id.clear);
        progressDialog = new ProgressDialog(this);
        progressDialog.setTitle("注意！");
        progressDialog.setMessage("正在保存数据，请稍后");
        progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        progressDialog.setCancelable(false);
        roomData = (RoomData) getApplication();
        tempRoomType = roomData.getRoomType();
        tempNormalPrice = roomData.getNormalPrice();
        tempSpecialPrice = roomData.getSpecialPrice();
        for (int i = 0; i < 18; i++) {
            if (i < 6)
                editTextArray[i].setText(tempRoomType[i]);
            if (i >= 6 && i < 12)
                editTextArray[i].setText(tempNormalPrice[i - 6] + "");
            if (i >= 12)
                editTextArray[i].setText(tempSpecialPrice[i - 12] + "");
        }
        setMsg.setText(roomData.getMsg());
        button2.setOnClickListener(new View.OnClickListener() {
            //            @Override
            public void onClick(View v) {
                Intent intent = new Intent(EditPage.this, MyActivity.class);
                startActivity(intent);
            }
        });
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                for (int i = 0; i < 6; i++) {
                    if (editTextArray[i].length() != 0) {
                        tempRoomType[i] = editTextArray[i].getText().toString();
                        tempNormalPrice[i] = Integer.parseInt(editTextArray[i + 6].getText().toString());
                        tempSpecialPrice[i] = Integer.parseInt(editTextArray[i + 12].getText().toString());
                    } else {
                        tempRoomType[i] = "";
                        tempNormalPrice[i] = 0;
                        tempSpecialPrice[i] = 0;
                    }
                }
                msg = setMsg.getText().toString();
                new MyTask().execute();
            }
        });
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for (EditText e :
                        editTextArray) {
                    e.setText("");
                }
                setMsg.setText("");
            }
        });
    }

    public class MyTask extends AsyncTask<Context, Integer, Integer> {
        @Override
        protected Integer doInBackground(Context... params) {
            int values = 0;
            roomData.setRoomType(tempRoomType);
            roomData.setNormalPrice(tempNormalPrice);
            roomData.setSpecialPrice(tempSpecialPrice);
            roomData.setMsg(msg);
            values += 50;
            publishProgress(values);
            DataSharedPreferences dataSharedPreferences = new DataSharedPreferences(EditPage.this);
            boolean flag = dataSharedPreferences.SaveRoomType(roomData.getRoomType());
            if (flag == true)
                values += 15;
            publishProgress(values);
            flag = dataSharedPreferences.SaveNormalPrice(roomData.getNormalPrice());
            if (flag == true)
                values += 15;
            publishProgress(values);
            flag = dataSharedPreferences.SaveSpecialPrice(roomData.getSpecialPrice());
            if (flag == true)
                values += 15;
            publishProgress(values);
            flag = dataSharedPreferences.SaveMsg(roomData.getMsg());
            if (flag == true)
                values += 5;
            publishProgress(values);
            return null;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressDialog.show();
        }

        @Override
        protected void onPostExecute(Integer integer) {
            super.onPostExecute(integer);
            progressDialog.dismiss();
            Intent intent = new Intent(EditPage.this, MyActivity.class);
            startActivity(intent);
            EditPage.this.finish();
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
            progressDialog.setProgress(values[0]);
        }
    }
}
