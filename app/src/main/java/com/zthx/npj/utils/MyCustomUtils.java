package com.zthx.npj.utils;

import android.widget.ImageView;

import com.zthx.npj.R;

public class MyCustomUtils {
    public static void showLevelImg(int level,ImageView img){
        switch (level) {
            case 0: img.setImageResource(R.drawable.level0);break;
            case 1: img.setImageResource(R.drawable.level1);break;
            case 2: img.setImageResource(R.drawable.level2);break;
            case 3: img.setImageResource(R.drawable.level3);break;
            case 4: img.setImageResource(R.drawable.level4);break;
            case 5: img.setImageResource(R.drawable.level5);break;
            case 6: img.setImageResource(R.drawable.level6);break;
            case 7: img.setImageResource(R.drawable.level7);break;
            case 8: img.setImageResource(R.drawable.level8);break;
            case 9: img.setImageResource(R.drawable.level9);break;
            case 10: img.setImageResource(R.drawable.level10);break;
        }
    }
}
