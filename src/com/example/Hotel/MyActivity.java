package com.example.Hotel;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.text.Editable;
import android.text.TextWatcher;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.ViewPager;
import android.support.v4.view.PagerAdapter;
import android.view.*;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.*;

import java.lang.ref.WeakReference;

public class MyActivity extends Activity {
    /**
     * Called when the activity is first created.
     */
    private TextClock textClock;
    private TextClock textClock2;
    private ViewPager viewPager;
    private ImageView[] tips;
    private ImageView[] mImageViews;
    private int[] imgIdArray;
    private Handler handler = new ImageHandler(new WeakReference<MyActivity>(this));
    private static final String LOG_TAG = "MyActivity";
    private static final String PASSWORD = "15638223310";
    private RoomData roomData;
    private static TextView[] viewTextArray = new TextView[18];
    private static ProgressDialog progressDialog;
    private static String[] tempRoomType;
    private static int[] tempNormalPrice;
    private static int[] tempSpecialPrice;
    private static TextView msg;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        viewTextArray[0] = (TextView) this.findViewById(R.id.roomType1);
        viewTextArray[1] = (TextView) this.findViewById(R.id.roomType2);
        viewTextArray[2] = (TextView) this.findViewById(R.id.roomType3);
        viewTextArray[3] = (TextView) this.findViewById(R.id.roomType4);
        viewTextArray[4] = (TextView) this.findViewById(R.id.roomType5);
        viewTextArray[5] = (TextView) this.findViewById(R.id.roomType6);
        viewTextArray[6] = (TextView) this.findViewById(R.id.normalPrice1);
        viewTextArray[7] = (TextView) this.findViewById(R.id.normalPrice2);
        viewTextArray[8] = (TextView) this.findViewById(R.id.normalPrice3);
        viewTextArray[9] = (TextView) this.findViewById(R.id.normalPrice4);
        viewTextArray[10] = (TextView) this.findViewById(R.id.normalPrice5);
        viewTextArray[11] = (TextView) this.findViewById(R.id.normalPrice6);
        viewTextArray[12] = (TextView) this.findViewById(R.id.specialPrice1);
        viewTextArray[13] = (TextView) this.findViewById(R.id.specialPrice2);
        viewTextArray[14] = (TextView) this.findViewById(R.id.specialPrice3);
        viewTextArray[15] = (TextView) this.findViewById(R.id.specialPrice4);
        viewTextArray[16] = (TextView) this.findViewById(R.id.specialPrice5);
        viewTextArray[17] = (TextView) this.findViewById(R.id.specialPrice6);
        Button button = (Button) this.findViewById(R.id.button);
        textClock = (TextClock) this.findViewById(R.id.mClock);
        textClock2 = (TextClock) this.findViewById(R.id.textClock);
        ViewGroup group = (ViewGroup) this.findViewById(R.id.viewGroup);
        viewPager = (ViewPager) this.findViewById(R.id.viewPager);
        roomData = (RoomData) getApplication();
        progressDialog = new ProgressDialog(this);
        progressDialog.setTitle("注意！");
        progressDialog.setMessage("正在初始化，请稍后");
        progressDialog.setCancelable(false);
        new InitTask().execute();
        imgIdArray = new int[]{R.drawable.sp1, R.drawable.sp2, R.drawable.sp3, R.drawable.sp4, R.drawable.sp5,
                R.drawable.sp6, R.drawable.sp7, R.drawable.sp8, R.drawable.sp9, R.drawable.sp10, R.drawable.sp11, R.drawable.sp12};
        tips = new ImageView[imgIdArray.length];
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        for (int i = 0; i < tips.length; i++) {
            //设置点点
            ImageView imageView = new ImageView(this);
            imageView.setLayoutParams(new LinearLayout.LayoutParams(10, 10));
            tips[i] = imageView;
            if (i == 0) {
                tips[i].setBackgroundResource(R.drawable.focused);
            } else {
                tips[i].setBackgroundResource(R.drawable.unfocused);
            }
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT));
            layoutParams.leftMargin = 5;
            layoutParams.rightMargin = 5;
            group.addView(imageView, layoutParams);
        }
        mImageViews = new ImageView[imgIdArray.length];
        for (int i = 0; i < mImageViews.length; i++) {
            //装载图片
            ImageView imageView = new ImageView(this);
            mImageViews[i] = imageView;
            imageView.setBackgroundResource(imgIdArray[i]);
        }

        viewPager.setAdapter(new PagerAdapter() {//设置适配器
            @Override
            public int getCount() {
                return Integer.MAX_VALUE;
            }

            @Override
            public boolean isViewFromObject(View arg0, Object arg1) {
                return arg0 == arg1;
            }

            @Override
            public void destroyItem(ViewGroup container, int position, Object object) {
//                super.destroyItem(container, position, object);
//                container.removeView(mImageViews[position % mImageViews.length]);
            }

            @Override
            public float getPageWidth(int position) {
                final int mposition = position % mImageViews.length;
                if (mposition == 0 || mposition == 1 || mposition == 3 || mposition == 6 || mposition == 9)
                    return 0.75f;
                return 1f;
            }

            @Override
            public Object instantiateItem(ViewGroup container, int position) {
                //装载图片
                position %= mImageViews.length;
                if (position < 0) {
                    position = mImageViews.length + position;
                }
                ImageView view = mImageViews[position];
                ViewParent vp = view.getParent();
                if (vp != null) {
                    ViewGroup parent = (ViewGroup) vp;
                    parent.removeView(view);
                }
                container.addView(view);
                return view;
            }
        });
        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override//监听器，设置点点的动作
            public void onPageScrolled(int i, float v, int i1) {

            }

            @Override
            public void onPageSelected(int i) {
                setImageBackground(i % mImageViews.length);
                handler.sendMessage(Message.obtain(handler, ImageHandler.MSG_PAGE_CHANGED, i, 0));

            }

            @Override
            public void onPageScrollStateChanged(int i) {
                switch (i) {
                    case ViewPager.SCROLL_STATE_DRAGGING:
                        handler.sendEmptyMessage(ImageHandler.MSG_KEEP_SILENT);
                        break;
                    case ViewPager.SCROLL_STATE_IDLE:
                        handler.sendEmptyMessageDelayed(ImageHandler.MSG_UPDATE_IMAGE, ImageHandler.MSG_DELAY);
                        break;
                    default:
                        break;
                }

            }

            private void setImageBackground(int selectItems) {
                //设置点点改变
                for (int i = 0; i < tips.length; i++) {
                    if (i == selectItems) {
                        tips[i].setBackgroundResource(R.drawable.focused);
                    } else {
                        tips[i].setBackgroundResource(R.drawable.unfocused);
                    }
                }
            }
        });
        msg = (TextView) this.findViewById(R.id.msg);
        handler.sendEmptyMessageDelayed(ImageHandler.MSG_UPDATE_IMAGE, ImageHandler.MSG_DELAY);
        textClock.setFormat12Hour("yyyy年MM月dd日");//设置日期
        textClock2.setFormat24Hour("HH:mm");
        textClock2.setFormat12Hour("hh:mm");
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(MyActivity.this);
                builder.setTitle("提示");
                LayoutInflater factory = LayoutInflater.from(MyActivity.this);
                final View passView = factory.inflate(R.layout.password_dialog, null);
                builder.setView(passView);
                builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        final EditText editText = (EditText) passView.findViewById(R.id.password);
                        String password = editText.getText().toString().trim();
                        if (password.equals(PASSWORD)) {
                            Intent intent = new Intent(MyActivity.this, EditPage.class);
                            startActivity(intent);
//                            MyActivity.this.finish();
                        }
                    }
                });
                builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
                AlertDialog alertDialog = builder.create();
                alertDialog.show();
            }
        });
    }

    public class InitTask extends AsyncTask<String, Integer, Integer> {
        @Override
        protected Integer doInBackground(String... params) {
            int values = 0;
            DataSharedPreferences dataSharedPreferences = new DataSharedPreferences(MyActivity.this);
            roomData.setRoomType(dataSharedPreferences.ReadRoomType());
            values += 20;
            publishProgress(values);
            roomData.setNormalPrice(dataSharedPreferences.ReadNormalPrice());
            values += 20;
            publishProgress(values);
            roomData.setSpecialPrice(dataSharedPreferences.ReadSpecialPrice());
            values += 20;
            publishProgress(values);
            roomData.setMsg(dataSharedPreferences.ReadMsg());
            values += 20;
            publishProgress(values);
            tempRoomType = roomData.getRoomType();
            tempNormalPrice = roomData.getNormalPrice();
            tempSpecialPrice = roomData.getSpecialPrice();
            values += 20;
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
            for (int i = 0; i < 18; i++) {
                if (i < 6)
                    viewTextArray[i].setText(tempRoomType[i]);
                if (i >= 6 && i < 12)
                    viewTextArray[i].setText(tempNormalPrice[i - 6] + "");
                if (i >= 12)
                    viewTextArray[i].setText(tempSpecialPrice[i - 12] + "");
            }
            msg.setText(roomData.getMsg());
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
            progressDialog.setProgress(values[0]);
        }

    }

    private static class ImageHandler extends Handler {
        protected static final int MSG_UPDATE_IMAGE = 1;
        protected static final int MSG_KEEP_SILENT = 2;
        protected static final int MSG_BREAK_SILENT = 3;
        protected static final int MSG_PAGE_CHANGED = 4;
        protected static final long MSG_DELAY = 3000;
        private WeakReference<MyActivity> weakReference;
        private int currentItem = 0;

        protected ImageHandler(WeakReference<MyActivity> wk) {
            weakReference = wk;
        }

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            Log.d(LOG_TAG, "receive message" + msg.what);
            MyActivity myActivity = weakReference.get();
            if (myActivity == null) {
                return;
            }
            if (myActivity.handler.hasMessages(MSG_UPDATE_IMAGE)) {
                myActivity.handler.removeMessages(MSG_UPDATE_IMAGE);
            }
            switch (msg.what) {
                case MSG_UPDATE_IMAGE:
                    currentItem++;
                    myActivity.viewPager.setCurrentItem(currentItem);
                    myActivity.handler.sendEmptyMessageDelayed(MSG_UPDATE_IMAGE, MSG_DELAY);
                    break;
                case MSG_KEEP_SILENT:
                    break;
                case MSG_BREAK_SILENT:
                    myActivity.handler.sendEmptyMessageDelayed(MSG_UPDATE_IMAGE, MSG_DELAY);
                    break;
                case MSG_PAGE_CHANGED:
                    currentItem = msg.arg1;
                    break;
                default:
                    break;
            }
        }
    }

}