package com.zthx.npj.ui;

import android.app.Dialog;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.mapapi.SDKInitializer;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.BitmapDescriptor;
import com.baidu.mapapi.map.BitmapDescriptorFactory;
import com.baidu.mapapi.map.MapStatus;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.MarkerOptions;
import com.baidu.mapapi.map.MyLocationConfiguration;
import com.baidu.mapapi.map.MyLocationData;
import com.baidu.mapapi.map.OverlayOptions;
import com.baidu.mapapi.model.LatLng;
import com.baidu.mapapi.search.geocode.GeoCodeResult;
import com.baidu.mapapi.search.geocode.GeoCoder;
import com.baidu.mapapi.search.geocode.OnGetGeoCoderResultListener;
import com.baidu.mapapi.search.geocode.ReverseGeoCodeOption;
import com.baidu.mapapi.search.geocode.ReverseGeoCodeResult;
import com.zthx.npj.R;
import com.zthx.npj.utils.SharePerferenceUtils;

import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ShowLocationActivity extends ActivityBase {
    @BindView(R.id.mapView)
    MapView mapView;
    @BindView(R.id.title_back)
    ImageView titleBack;
    @BindView(R.id.ac_title)
    TextView acTitle;
    @BindView(R.id.at_location_store_tv_ruzhu)
    TextView atLocationStoreTvRuzhu;

    //地图控件
    //百度地图
    private BaiduMap baiduMap;
    //防止每次定位都重新设置中心点和marker
    private boolean isFirstLocation = true;
    //初始化LocationClient定位类
    private LocationClient mLocationClient = null;
    //BDAbstractLocationListener为7.2版本新增的Abstract类型的监听接口，原有BDLocationListener接口
    private BDLocationListener myListener = new MyLocationListener();
    //经纬度
    private GeoCoder mSearch;
    private String Lat = "";
    private String Lng = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //在使用SDK各组件之前初始化context信息，传入ApplicationContext
        //注意该方法要再setContentView方法之前实现
        SDKInitializer.initialize(getApplicationContext());
        setContentView(R.layout.activity_show_location);
        ButterKnife.bind(this);

        back(titleBack);
        changeTitle(acTitle, "商家位置");

        Lat = getIntent().getStringExtra("key0");
        Lng = getIntent().getStringExtra("key1");
        atLocationStoreTvRuzhu.setText("本机地图");
        atLocationStoreTvRuzhu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showBottomDialog();
            }
        });


        initView();
        initMap();
        /*baiduMap.setOnMapClickListener(new BaiduMap.OnMapClickListener() {
            @Override
            public void onMapClick(LatLng latLng) {
                baiduMap.clear();
                BitmapDescriptor bitmap = BitmapDescriptorFactory.fromResource(R.drawable.location_store_locate);
                OverlayOptions options = new MarkerOptions().position(latLng).icon(bitmap);
                baiduMap.addOverlay(options);
                LatLng latLng1=new LatLng(Double.parseDouble(Lat),Double.parseDouble(Lng));
                initGeoCoder(latLng1);
            }

            @Override
            public boolean onMapPoiClick(MapPoi mapPoi) {
                return false;
            }
        });*/
    }

    /**
     * 初始化控件
     */
    public void initView() {
        mapView = (MapView) findViewById(R.id.mapView);
    }

    /**
     * 初始化地图
     */
    public void initMap() {
        //得到地图实例
        baiduMap = mapView.getMap();
        /*
        设置地图类型
         */
        //普通地图
        baiduMap.setMapType(BaiduMap.MAP_TYPE_NORMAL);
        //卫星地图
        //baiduMap.setMapType(BaiduMap.MAP_TYPE_SATELLITE);
        //空白地图, 基础地图瓦片将不会被渲染。在地图类型中设置为NONE，将不会使用流量下载基础地图瓦片图层。使用场景：与瓦片图层一起使用，节省流量，提升自定义瓦片图下载速度。
        //baiduMap.setMapType(BaiduMap.MAP_TYPE_NONE);
        //开启交通图
        baiduMap.setTrafficEnabled(true);
        //关闭缩放按钮
        mapView.showZoomControls(false);
        // 开启定位图层
        baiduMap.setMyLocationEnabled(true);
        //声明LocationClient类
        // 开启定位图层
        baiduMap.setMyLocationEnabled(true);
        //声明LocationClient类
        mLocationClient = new LocationClient(this);
        //注册监听函数
        mLocationClient.registerLocationListener(myListener);
        initLocation();
        //开始定位
        mLocationClient.start();

        //自己的位置
        LatLng latLng1 = new LatLng(Double.parseDouble(SharePerferenceUtils.getLat(this)), Double.parseDouble(SharePerferenceUtils.getLng(this)));
        BitmapDescriptor bitmap = BitmapDescriptorFactory.fromResource(R.drawable.location_store_locate);
        OverlayOptions options = new MarkerOptions().position(latLng1).icon(bitmap);
        baiduMap.addOverlay(options);
    }

    private void initLocation() {
        LocationClientOption option = new LocationClientOption();
        //可选，默认高精度，设置定位模式，高精度，低功耗，仅设备
        option.setLocationMode(LocationClientOption.LocationMode.Hight_Accuracy);
        //可选，默认gcj02，设置返回的定位结果坐标系
        option.setCoorType("bd09ll");
        //可选，默认0，即仅定位一次，设置发起定位请求的间隔需要大于等于1000ms才是有效的
        int span = 5000;
        option.setScanSpan(span);
        //可选，设置是否需要地址信息，默认不需要
        option.setIsNeedAddress(true);
        //可选，默认false,设置是否使用gps
        option.setOpenGps(true);
        //可选，默认false，设置是否当GPS有效时按照1S/1次频率输出GPS结果
        option.setLocationNotify(true);
        //可选，默认false，设置是否需要位置语义化结果，可以在BDLocation.getLocationDescribe里得到，结果类似于“在北京天安门附近”
        option.setIsNeedLocationDescribe(true);
        //可选，默认false，设置是否需要POI结果，可以在BDLocation.getPoiList里得到
        option.setIsNeedLocationPoiList(true);
        //可选，默认true，定位SDK内部是一个SERVICE，并放到了独立进程，设置是否在stop的时候杀死这个进程，默认不杀死
        option.setIgnoreKillProcess(false);
        //可选，默认false，设置是否收集CRASH信息，默认收集
        option.SetIgnoreCacheException(false);
        //可选，默认false，设置是否需要过滤GPS仿真结果，默认需要
        option.setEnableSimulateGps(false);
        mLocationClient.setLocOption(option);
    }


    /**
     * 实现定位监听 位置一旦有所改变就会调用这个方法
     * 可以在这个方法里面获取到定位之后获取到的一系列数据
     */
    public class MyLocationListener implements BDLocationListener {

        @Override
        public void onReceiveLocation(BDLocation location) {
            //获取定位结果
           /* location.getTime();    //获取定位时间
            location.getLocationID();    //获取定位唯一ID，v7.2版本新增，用于排查定位问题
            location.getLocType();    //获取定位类型
            location.getLatitude();    //获取纬度信息
            location.getLongitude();    //获取经度信息
            location.getRadius();    //获取定位精准度
            location.getAddrStr();    //获取地址信息
            location.getCountry();    //获取国家信息
            location.getCountryCode();    //获取国家码
            location.getCity();    //获取城市信息
            location.getCityCode();    //获取城市码
            location.getDistrict();    //获取区县信息
            location.getStreet();    //获取街道信息
            location.getStreetNumber();    //获取街道码
            location.getLocationDescribe();    //获取当前位置描述信息
            location.getPoiList();    //获取当前位置周边POI信息
            location.getBuildingID();    //室内精准定位下，获取楼宇ID
            location.getBuildingName();    //室内精准定位下，获取楼宇名称
            location.getFloor();    //室内精准定位下，获取当前位置所处的楼层信息*/
            location.setLatitude(Double.parseDouble(Lat));
            location.setLongitude(Double.parseDouble(Lng));

            //这个判断是为了防止每次定位都重新设置中心点和marker
            if (isFirstLocation) {
                isFirstLocation = false;
                //设置并显示中心点
                setPosition2Center(baiduMap, location, true);
            }
        }
    }

    /**
     * 设置中心点和添加marker
     *
     * @param map
     * @param bdLocation
     * @param isShowLoc
     */
    public void setPosition2Center(BaiduMap map, BDLocation bdLocation, Boolean isShowLoc) {
        MyLocationData locData = new MyLocationData.Builder()
                .accuracy(bdLocation.getRadius())
                .direction(bdLocation.getRadius()).latitude(bdLocation.getLatitude())
                .longitude(bdLocation.getLongitude()).build();

        map.setMyLocationData(locData);



        if (isShowLoc) {
            LatLng ll = new LatLng(bdLocation.getLatitude(), bdLocation.getLongitude());
            MapStatus.Builder builder = new MapStatus.Builder();
            builder.target(ll).zoom(18.0f);
            map.animateMapStatus(MapStatusUpdateFactory.newMapStatus(builder.build()));

            MyLocationConfiguration.LocationMode mCurrentMode = MyLocationConfiguration.LocationMode.NORMAL;
            int accuracyCircleFillColor = 0xAAFFFF88;
            int accuracyCircleStrokeColor = 0xAA00FF00;
            BitmapDescriptor currentMarker = BitmapDescriptorFactory.fromResource(R.drawable.locate);
            baiduMap.setMyLocationConfiguration(new MyLocationConfiguration(mCurrentMode, true, currentMarker, accuracyCircleFillColor, accuracyCircleStrokeColor));
            currentMarker.recycle();
        }
    }

    private void initGeoCoder(LatLng latLng) {
        mSearch = GeoCoder.newInstance();
        mSearch.setOnGetGeoCodeResultListener(mOnGetGeoCoderResultListener);
        //mSearch.geocode(new GeoCodeOption().city("cityName").address("address"));
        mSearch.reverseGeoCode(new ReverseGeoCodeOption().location(latLng));
    }

    private OnGetGeoCoderResultListener mOnGetGeoCoderResultListener = new OnGetGeoCoderResultListener() {
        @Override
        public void onGetGeoCodeResult(GeoCodeResult geoCodeResult) {

        }

        @Override
        public void onGetReverseGeoCodeResult(ReverseGeoCodeResult reverseGeoCodeResult) {
            ArrayList<String> list = new ArrayList<>();
            for (int i = 0; i < reverseGeoCodeResult.getPoiList().size(); i++) {
                list.add(reverseGeoCodeResult.getPoiList().get(i).getName());
            }
        }
    };

    private boolean isInstalled(String packageName) {
        PackageManager manager = this.getPackageManager();
        //获取所有已安装程序的包信息
        List<PackageInfo> installedPackages = manager.getInstalledPackages(0);
        if (installedPackages != null) {
            for (PackageInfo info : installedPackages) {
                if (info.packageName.equals(packageName))
                    return true;
            }
        }
        return false;
    }
    private void goToGaodeMap() {
        if (!isInstalled("com.autonavi.minimap")) {
            showToast("请安装高德地图客户端");
            return;
        }
        LatLng endPoint = new LatLng(Double.parseDouble(Lat), Double.parseDouble(Lng));//坐标转换
        StringBuffer stringBuffer = new StringBuffer("androidamap://navi?sourceApplication=").append("amap");
        stringBuffer.append("&lat=").append(endPoint.latitude)
                .append("&lon=").append(endPoint.longitude)
                .append("&dev=").append(0)
                .append("&style=").append(2);
        Intent intent = new Intent("android.intent.action.VIEW", Uri.parse(stringBuffer.toString()));
        intent.setPackage("com.autonavi.minimap");
        startActivity(intent);
    }

    private void goToBaiduMap() throws URISyntaxException {
        if (!isInstalled("com.baidu.BaiduMap")) {
            showToast("请安装百度地图客户端");
            return;
        }

        String url_map = "intent://map/direction?" +
                //"origin=latlng:" + dqLatitude + "," + dqLongitude +   //起点  此处不传值默认选择当前位置如果传参则提示参数错误
                "destination=latlng:" + Lat + "," + Lng + "|name:" + "" +
                "&mode=driving&" +          //导航路线方式
                "region=" + "" +           //
                "&src=" + "" + "#Intent;scheme=bdapp;package=com.baidu.BaiduMap;end";
        Intent intent = Intent.getIntent(url_map);
        startActivity(intent); // 启动调用
    }

    private void goToTengxunMap(){
        if (!isInstalled("com.tencent.map")) {
            showToast("请安装腾讯地图客户端");
            return;
        }
        String url1 = "qqmap://map/routeplan?type=drive&to=" + "" + "&tocoord=" + Lat+ "," +Lng  + "&policy=2&referer=myapp";
        Intent intent = new Intent("android.intent.action.VIEW", android.net.Uri.parse(url1));
        startActivity(intent);
    }


    private void showBottomDialog() {
        //1、使用Dialog、设置style
        final Dialog dialog = new Dialog(this, R.style.DialogTheme);
        //2、设置布局
        View view = View.inflate(this, R.layout.dialog_map_layout, null);
        dialog.setContentView(view);
        Window window = dialog.getWindow();
        //设置弹出位置
        window.setGravity(Gravity.BOTTOM);
        //设置弹出动画
        window.setWindowAnimations(R.style.main_menu_animStyle);
        //设置对话框大小
        window.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        dialog.show();
        dialog.findViewById(R.id.dl_map_baidu).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    goToBaiduMap();
                } catch (URISyntaxException e) {
                    e.printStackTrace();
                }
                dialog.dismiss();
            }
        });
        dialog.findViewById(R.id.dl_map_gaode).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goToGaodeMap();
                dialog.dismiss();
            }
        });
        dialog.findViewById(R.id.dl_map_tengxun).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goToTengxunMap();
                dialog.dismiss();
            }
        });
        dialog.findViewById(R.id.dl_photo_cancel).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
    }




}