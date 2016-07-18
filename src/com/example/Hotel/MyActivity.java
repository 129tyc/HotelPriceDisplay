package com.example.Hotel;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
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
    private static TextView roomType1;
    private static TextView normalPrice1;
    private static TextView SpecialPrice1;
    private static TextView roomType2;
    private static TextView normalPrice2;
    private static TextView SpecialPrice2;
    private static TextView roomType3;
    private static TextView normalPrice3;
    private static TextView SpecialPrice3;
    private static TextView roomType4;
    private static TextView normalPrice4;
    private static TextView SpecialPrice4;
    private static TextView roomType5;
    private static TextView normalPrice5;
    private static TextView SpecialPrice5;
    private static TextView roomType6;
    private static TextView normalPrice6;
    private static TextView SpecialPrice6;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        roomType1 = (TextView) this.findViewById(R.id.roomType1);
        roomType2 = (TextView) this.findViewById(R.id.roomType2);
        roomType3 = (TextView) this.findViewById(R.id.roomType3);
        roomType4 = (TextView) this.findViewById(R.id.roomType4);
        roomType5 = (TextView) this.findViewById(R.id.roomType5);
        roomType6 = (TextView) this.findViewById(R.id.roomType6);
        normalPrice1 = (TextView) this.findViewById(R.id.normalPrice1);
        normalPrice2 = (TextView) this.findViewById(R.id.normalPrice2);
        normalPrice3 = (TextView) this.findViewById(R.id.normalPrice3);
        normalPrice4 = (TextView) this.findViewById(R.id.normalPrice4);
        normalPrice5 = (TextView) this.findViewById(R.id.normalPrice5);
        normalPrice6 = (TextView) this.findViewById(R.id.normalPrice6);
        SpecialPrice1 = (TextView) this.findViewById(R.id.specialPrice1);
        SpecialPrice2 = (TextView) this.findViewById(R.id.specialPrice2);
        SpecialPrice3 = (TextView) this.findViewById(R.id.specialPrice3);
        SpecialPrice4 = (TextView) this.findViewById(R.id.specialPrice4);
        SpecialPrice5 = (TextView) this.findViewById(R.id.specialPrice5);
        SpecialPrice6 = (TextView) this.findViewById(R.id.specialPrice6);
        Button button = (Button) this.findViewById(R.id.button);
        textClock = (TextClock) this.findViewById(R.id.mClock);
        textClock2 = (TextClock) this.findViewById(R.id.textClock);
        ViewGroup group = (ViewGroup) this.findViewById(R.id.viewGroup);
        viewPager = (ViewPager) this.findViewById(R.id.viewPager);
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
//               return super.instantiateItem(container, position);
                //装载图片
//                container.addView(mImageViews[position % mImageViews.length], 0);
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
        TextView msg = (TextView)this.findViewById(R.id.msg);
        handler.sendEmptyMessageDelayed(ImageHandler.MSG_UPDATE_IMAGE,ImageHandler.MSG_DELAY);
        textClock.setFormat12Hour("yyyy年MM月dd日");//设置日期
        textClock2.setFormat24Hour("HH:mm");
        textClock2.setFormat12Hour("hh:mm");
        roomData = (RoomData) getApplication();
        roomType1.setText(roomData.getRoomType1());
        roomType2.setText(roomData.getRoomType2());
        roomType3.setText(roomData.getRoomType3());
        roomType4.setText(roomData.getRoomType4());
        roomType5.setText(roomData.getRoomType5());
        roomType6.setText(roomData.getRoomType6());
        normalPrice1.setText(roomData.getNormalPrice1() + "");
        normalPrice2.setText(roomData.getNormalPrice2() + "");
        normalPrice3.setText(roomData.getNormalPrice3() + "");
        normalPrice4.setText(roomData.getNormalPrice4() + "");
        normalPrice5.setText(roomData.getNormalPrice5() + "");
        normalPrice6.setText(roomData.getNormalPrice6() + "");
        SpecialPrice1.setText(roomData.getSpecialPrice1() + "");
        SpecialPrice2.setText(roomData.getSpecialPrice2() + "");
        SpecialPrice3.setText(roomData.getSpecialPrice3() + "");
        SpecialPrice4.setText(roomData.getSpecialPrice4() + "");
        SpecialPrice5.setText(roomData.getSpecialPrice5() + "");
        SpecialPrice6.setText(roomData.getSpecialPrice6() + "");
        msg.setText(roomData.getMsg());
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
                            MyActivity.this.finish();
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