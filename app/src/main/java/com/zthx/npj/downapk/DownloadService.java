package com.zthx.npj.downapk;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Binder;
import android.os.Build;
import android.os.Environment;
import android.os.IBinder;
import android.support.annotation.RequiresApi;
import android.support.v4.app.NotificationCompat;
import android.support.v4.content.FileProvider;
import android.widget.Toast;

import com.zthx.npj.R;
import com.zthx.npj.ui.MainActivity;

import java.io.File;

public class DownloadService extends Service {
    private DownloadTask downloadTask;
    private String downloadUrl;

    String CHANNEL_ID = "com.zthx.npj";
    String CHANNEL_NAME = "npj";


    private DownloadListener listener = new DownloadListener() {
        @RequiresApi(api = Build.VERSION_CODES.O)
        @Override
        public void onProgress(int progress) {
            getNotificationManager().notify(1, getNotification("农品街App下载中...",progress));
        }
        @RequiresApi(api = Build.VERSION_CODES.O)
        @Override
        public void onSuccess() {
            downloadTask = null;
// 下载成功时将前台服务通知关闭，并创建一个下载成功的通知
            stopForeground(true);
            getNotificationManager().notify(1, getNotification("农品街App下载完成",
                    -1));

            String fileName = downloadUrl.substring(downloadUrl.lastIndexOf("/"));
            String directory = Environment.getExternalStoragePublicDirectory
                    (Environment.DIRECTORY_DOWNLOADS).getPath();
            installApk(new File(directory+fileName));

        }
        @RequiresApi(api = Build.VERSION_CODES.O)
        @Override
        public void onFailed() {
            downloadTask = null;
// 下载失败时将前台服务通知关闭，并创建一个下载失败的通知
            stopForeground(true);
            getNotificationManager().notify(1, getNotification("下载失败", -1));
        }
        @Override
        public void onPaused() {
            downloadTask = null;
            Toast.makeText(DownloadService.this, "暂停下载", Toast.LENGTH_SHORT).
                    show();
        }
        @Override
        public void onCanceled() {
            downloadTask = null;
            stopForeground(true);
            Toast.makeText(DownloadService.this, "取消下载", Toast.LENGTH_SHORT).
                    show();
        }
    };
    private DownloadBinder mBinder = new DownloadBinder();
    @Override
    public IBinder onBind(Intent intent) {
        return mBinder;
    }
    public class DownloadBinder extends Binder {
        @RequiresApi(api = Build.VERSION_CODES.O)
        public void startDownload(String url) {
            if (downloadTask == null) {
                downloadUrl = url;
                downloadTask = new DownloadTask(listener);
                downloadTask.execute(downloadUrl);
                startForeground(1, getNotification("农品街App下载中...", 0));
            }
        }
        public void pauseDownload() {
            if (downloadTask != null) {
                downloadTask.pauseDownload();
            }
        }
        public void cancelDownload() {
            if (downloadTask != null) {
                downloadTask.cancelDownload();
            } else {
                if (downloadUrl != null) {
// 取消下载时需将文件删除，并将通知关闭
                    String fileName = downloadUrl.substring(downloadUrl.
                            lastIndexOf("/"));
                    String directory = Environment.getExternalStoragePublicDirectory
                            (Environment.DIRECTORY_DOWNLOADS).getPath();
                    File file = new File(directory + fileName);
                    if (file.exists()) {
                        file.delete();
                    }
                    getNotificationManager().cancel(1);
                    stopForeground(true);
                }
            }
        }
    }
    private NotificationManager getNotificationManager() {
        return (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private Notification getNotification(String title, int progress) {
        NotificationChannel notificationChannel = new NotificationChannel(CHANNEL_ID, CHANNEL_NAME, NotificationManager.IMPORTANCE_HIGH);
        NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        notificationManager.createNotificationChannel(notificationChannel);
        Intent intent = new Intent(this, MainActivity.class);
        PendingIntent pi = PendingIntent.getActivity(this, 0, intent, 0);
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this,CHANNEL_ID);
        builder.setSmallIcon(R.mipmap.ic_launcher);
        builder.setLargeIcon(BitmapFactory.decodeResource(getResources(),
                R.mipmap.ic_launcher));
        builder.setContentIntent(pi);
        builder.setContentTitle(title);
        if (progress >= 0) {
// 当progress大于或等于0时才需显示下载进度
            builder.setContentText(progress + "%");
            builder.setProgress(100, progress, false);
        }
        return builder.build();
    }

    private void installApk(File file) {
        //找到系统的安装界面,把安装过程中要用到的东西传递进去,让系统帮助我们安装.
        Intent intent = new Intent("android.intent.action.VIEW");
        intent.addCategory("android.intent.category.DEFAULT");
        intent.setDataAndType(FileProvider.getUriForFile(this,this.getApplicationContext().getPackageName() + ".file_provider",file), "application/vnd.android.package-archive");
        //通过隐式意图开启系统的安装apk界面
        startActivity(intent);
    }
}
