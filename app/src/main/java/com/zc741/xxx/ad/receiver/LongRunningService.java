package com.zc741.xxx.ad.receiver;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.SystemClock;
import android.support.annotation.Nullable;

import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest.HttpMethod;

import static com.zc741.xxx.ad.TestActivity.PRODUCT_URL;

/**
 * Created by xxx on 2016/12/28.
 */

public class LongRunningService extends Service {
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        final String clientId = intent.getStringExtra("clientId");
        final int id = Integer.parseInt(clientId);
        //Logger.d(id);
        new Thread(new Runnable() {
            @Override
            public void run() {
                //Logger.d("LongRunningService", "executed at " + new Date().toString());
                String url = PRODUCT_URL + "/heart_notice?clientId=" + id;
                HttpUtils utils = new HttpUtils();
                utils.send(HttpMethod.GET, url, new RequestCallBack<String>() {
                    @Override
                    public void onSuccess(ResponseInfo<String> responseInfo) {
                        //Logger.d(responseInfo.result);
                    }

                    @Override
                    public void onFailure(HttpException e, String s) {
                        //Logger.e(e + s);
                    }
                });
            }
        }).start();

        AlarmManager manager = (AlarmManager) getSystemService(ALARM_SERVICE);
        int anHour = 60 * 60 * 1000; // 这是一小时的毫秒数
        long triggerAtTime = SystemClock.elapsedRealtime() + anHour;
        Intent i = new Intent(this, AlarmReceiver.class);
        PendingIntent pi = PendingIntent.getBroadcast(this, 0, i, 0);
        manager.set(AlarmManager.ELAPSED_REALTIME_WAKEUP, triggerAtTime, pi);
        return super.onStartCommand(intent, flags, startId);
    }
}