package com.zthx.npj.ui;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;
import android.widget.Toast;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.zthx.npj.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TestActivity extends AppCompatActivity {

    public LocationClient mLocationClient;
    @BindView(R.id.btnPay)
    TextView btnPay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //创建一个LocationClient的实例,接受的context通过getApplicationContext()方法获取。
        mLocationClient = new LocationClient(getApplicationContext());
        //调用LocationClient的registerLocationListener()方法来注册一个监听器 当获取到位置信息的时候，就会回调这个定位监听器
        mLocationClient.registerLocationListener(new MyLocationListener());
        setContentView(R.layout.activity_test);
        ButterKnife.bind(this);

        /*
         * 之前在AndroidManifest.xml内声明了很多权限。
         * 其中有4个是危险权限。不过ACCESS_COARSE_LOCATION 和 ACCESS_FINE_LOCATION都属于一个权限组，所以两者只需要申请其中一个就可以了。
         * 如何在运行时一次申请三个权限呢？
         * 首先创建一个空的List集合，然后依次判断这三个权限有没有被授权，如果没有授权就添加到List集合中，最后将List集合转化成数组，在调用ActivityCompat.requestPermissions()方法就可以一次性申请。
         */
        List<String> permissionList = new ArrayList<>();
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            permissionList.add(Manifest.permission.ACCESS_FINE_LOCATION);
        }
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED) {
            permissionList.add(Manifest.permission.READ_PHONE_STATE);
        }
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            permissionList.add(Manifest.permission.WRITE_EXTERNAL_STORAGE);
        }
        if (!permissionList.isEmpty()) {
            String[] permissions = permissionList.toArray(new String[permissionList.size()]);
            ActivityCompat.requestPermissions(this, permissions, 1);
        } else {
            requestLocation();
        }
    }

    private void requestLocation() {
        initLocation();
        //调用start方法会回调到我们注册的监听器上面
        mLocationClient.start();
    }

    private void initLocation(){
        LocationClientOption option = new LocationClientOption();
        option.setScanSpan(5000);
        mLocationClient.setLocOption(option);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case 1:
                if (grantResults.length > 0) {
                    //将每个申请的权限都进行判断 如果存在一个没有被授权，那么就调用finish()方法关闭程序。
                    for (int result : grantResults) {
                        if (result != PackageManager.PERMISSION_GRANTED) {
                            Toast.makeText(TestActivity.this, "必须同意所有权限才能使用本程序", Toast.LENGTH_SHORT).show();
                            finish();
                            return;
                        }
                    }
                    //所有权限都已经授权，那么直接调用requestLocation()方法开始定位。
                    requestLocation();
                } else {
                    Toast.makeText(TestActivity.this, "发生未知错误", Toast.LENGTH_SHORT).show();
                    finish();
                }
                break;
            default:
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mLocationClient.stop();
    }

    public class MyLocationListener implements BDLocationListener {
        @Override
        public void onReceiveLocation(final BDLocation location) {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    StringBuilder currentPosition = new StringBuilder();
                    //通过BDLocation的getLatitude()方法获取当前位置的纬度
                    currentPosition.append("纬度").append(location.getLatitude()).append("\n");
                    //通过BDLocation的getLongitude()方法获取当前位置的经度。
                    currentPosition.append("经线").append(location.getLongitude()).append("\n");
                    //getLocType()方法获取当前的定位方式。
                    if (location.getLocType() == BDLocation.TypeGpsLocation) {
                        currentPosition.append("GPS");
                    } else if (location.getLocType() == BDLocation.TypeNetWorkLocation) {
                        currentPosition.append("网络");
                    }
                    btnPay.setText(currentPosition);
                }
            });
        }
    }
}