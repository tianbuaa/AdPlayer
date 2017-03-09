package com.zc741.xxx.ad;

import android.app.Notification;
import android.app.NotificationManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.NotificationCompat;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import com.google.gson.Gson;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest.HttpMethod;
import com.orhanobut.logger.Logger;
import com.squareup.picasso.Picasso;
import com.tencent.bugly.crashreport.CrashReport;
import com.tencent.bugly.crashreport.CrashReport.UserStrategy;
import com.zc741.xxx.ad.bean.AppVersion;
import com.zc741.xxx.ad.bean.ClientAdNotice;
import com.zc741.xxx.ad.bean.DonateList;
import com.zc741.xxx.ad.bean.WSDonate;
import com.zc741.xxx.ad.bean.WSMessage;
import com.zc741.xxx.ad.receiver.LongRunningService;
import com.zc741.xxx.ad.utils.DonateDataBeanConverter;
import com.zc741.xxx.ad.utils.MarqueeTextView;
import com.zc741.xxx.ad.utils.WebSocketClient;

import org.apache.http.message.BasicNameValuePair;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URI;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import static com.zc741.xxx.ad.R.id.marqueeText;

/**
 * The type Test activity.
 */
public class TestActivity extends AppCompatActivity {
    private DonateList donateList;
    private int mVersionCode;
    private float mCurrentVersionName;
    private String mDownloadUrl;
    private long mPercent;

    private static final int INSERT_DONATE = 0;
    /**
     * The constant LOCALHOST_URL.
     */
    public static final String LOCALHOST_URL = "http://10.10.1.122:8080/cli";
    /**
     * The constant PRODUCT_URL.
     */
    public static final String PRODUCT_URL = "http://www.siyuanzaixian.cn/cli";
    private ClientAdNotice resource;
    private String url;
    private List<ClientAdNotice.AdListBean> videoList;
    private int size;
    private int number;
    private VideoView videoView;
    private MarqueeTextView marqueeTextView;
    private List<DonateList.ListBean> list;
    private RecyclerView recyclerView;
    private MyAdapter myAdapter;

    private int maxPageNum;
    /*private Handler handler = new Handler();*/
    private Runnable runnable = new Runnable() {
        @Override
        public void run() {
            //this.initDonate();
            handler.postDelayed(this, 1000 * 3);
        }
    };
    private int first;
    private String wsMessage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //requestWindowFeature(Window.FEATURE_NO_TITLE);//隐藏标题
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);//设置全屏
        setContentView(R.layout.activity_test);

        //接收传递的值
        Intent intent = getIntent();
        String content = intent.getStringExtra("number");
        number = Integer.valueOf(content);
        //Logger.d(number);

        //ClientId
        TextView clientId = (TextView) findViewById(R.id.clientId);
        assert clientId != null;
        clientId.setText(number + "");

        //广告和公告
        videoView = (VideoView) findViewById(R.id.videoView);
        marqueeTextView = (MarqueeTextView) findViewById(marqueeText);

        recyclerView = (RecyclerView) findViewById(R.id.list_item);
        recyclerView.setLayoutManager(new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL));

        //版本检测
        deleteApk();
        currentVersion();
        serverVersion();

        //ad notice qrcode
        initMarquee();
        //initAd
        initAd();

        //请求捐赠列表数据
        getDonateServer();

        //心跳处理
        Intent service = new Intent(this, LongRunningService.class);
        service.putExtra("clientId", content);
        startService(service);

        //Bugly 异常上传
        Context context = getApplicationContext();
        // 获取当前包名
        String packageName = context.getPackageName();
        // 获取当前进程名
        String processName = getProcessName(android.os.Process.myPid());
        // 设置是否为上报进程
        UserStrategy strategy = new UserStrategy(context);
        strategy.setUploadProcess(processName == null || processName.equals(packageName));
        CrashReport.initCrashReport(context, "79a52e22ce", true, strategy);

        //延时滚动操作
        TimerTask task = new TimerTask() {
            public void run() {
                listScrollUp();
            }
        };
        Timer timer = new Timer();
        timer.schedule(task, 2000);

        handler.postDelayed(runnable, 1000);
    }

    private void initAd() {
        String url = PRODUCT_URL + "/clientAdNotice?clientId=" + number;
        HttpUtils utils = new HttpUtils();
        utils.send(HttpMethod.GET, url, new RequestCallBack<String>() {
            @Override
            public void onSuccess(ResponseInfo<String> responseInfo) {
                String result = responseInfo.result;
                Gson gson = new Gson();
                resource = gson.fromJson(result, ClientAdNotice.class);
                if (resource.getAdList().size() == 0) {
                    videoView.setVideoURI(Uri.parse("http://syzxbkt.oss-cn-hangzhou.aliyuncs.com/video/ad/yns/%E5%BE%B7%E6%B8%85%E6%B0%B8%E5%AE%81%E5%AF%BA%E5%BE%B7%E6%B8%85%E6%B0%B8%E5%AE%81%E5%AF%BA.mp4"));
                    videoView.start();
                } else {
                    initVideo();
                }
                //生成二维码
                initQrcode();

                //socket
                socketMethod();
            }

            @Override
            public void onFailure(HttpException e, String s) {
                Logger.e(e + s);
                AlertDialog.Builder alertDialog = new AlertDialog.Builder(TestActivity.this);
                alertDialog.setTitle("错误");
                alertDialog.setMessage("视频播放失败，资源有问题，请联系管理员。");
                alertDialog.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = new Intent(TestActivity.this, MainActivity.class);
                        startActivity(intent);
                    }
                });
                alertDialog.create().show();
            }
        });
    }

    private void socketMethod() {
        List<BasicNameValuePair> extraHeaders = Arrays.asList(new BasicNameValuePair("Cookie", "session=abcd"));
        String wsUrl = "ws://www.siyuanzaixian.cn:18080/client.ws/" + resource.getTemple_id() + "/" + number;
        WebSocketClient client = new WebSocketClient(URI.create(wsUrl), new WebSocketClient.Listener() {
            @Override
            public void onConnect() {
                Logger.d("Connect !!!");
            }

            @Override
            public void onMessage(String message) {
                //Logger.d(message);
                wsMessage = message;
                boolean gsonType = message.contains("donation");
                if (!gsonType) {
                    //Logger.d("NoticeAd");
                    Gson gson = new Gson();
                    WSMessage msg = gson.fromJson(message, WSMessage.class);
                    String data = msg.getData();
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                        if (Objects.equals(data, "ad")) {
                            //initAd
                            Logger.d("请求ad");
                            initAd();
                        } else {
                            Logger.d("请求notice");
                            initMarquee();
                        }
                    }
                } else {
                    //Logger.d("Donation");
                    //停止滚动
                    //listSc rollOff();
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            Message msg = new Message();
                            msg.what = INSERT_DONATE;
                            handler.sendMessage(msg);
                        }
                    }).start();
                }
            }

            @Override
            public void onMessage(byte[] data) {
                Logger.d(data);
            }

            @Override
            public void onDisconnect(int code, String reason) {
                Logger.d(reason);
            }

            @Override
            public void onError(Exception error) {
                Logger.e(String.valueOf(error));
            }
        }, extraHeaders);
        client.connect();
    }

    private void initDonation(String wsMessage) {
        Gson gson = new Gson();
        WSDonate donateInfo = gson.fromJson(wsMessage, WSDonate.class);
        Logger.d(wsMessage);
        DonateList.ListBean donateList = DonateDataBeanConverter.wsDonateDataBeanToListBean(donateInfo.getData());
        list.add(0, donateList);
        myAdapter.notifyItemInserted(0);
        //如果在第一项添加模拟数据需要调用 scrollToPosition（0）把列表移动到顶端（可选）
        recyclerView.scrollToPosition(0);
        listScrollUp();
    }

    private void initMarquee() {
        String url = PRODUCT_URL + "/clientAdNotice?clientId=" + number;
        HttpUtils utils = new HttpUtils();
        utils.send(HttpMethod.GET, url, new RequestCallBack<String>() {
            @Override
            public void onSuccess(ResponseInfo<String> responseInfo) {
                String result = responseInfo.result;
                Gson gson = new Gson();
                resource = gson.fromJson(result, ClientAdNotice.class);
                //公告栏
                initNotice();
                //生成二维码
                initQrcode();
            }

            @Override
            public void onFailure(HttpException e, String s) {
                Logger.e(e + s);
            }
        });
    }

    //公告栏
    private void initNotice() {
        String multText = "";
        int size = resource.getNoticeList().size();
        List<ClientAdNotice.NoticeListBean> marqueeList = resource.getNoticeList();
        for (int i = 0; i < size; i++) {
            multText = multText + marqueeList.get(i).getSummary() + "                                                                                                 ";
        }
        marqueeTextView.setText(multText);
    }

    //视频播放
    private void initVideo() {
        //1. 获取视频列表
        videoList = resource.getAdList();
        size = videoList.size();
        first = 0;
        String videoUrl = videoList.get(first).getRes().getVideoCode();
        assert videoView != null;
        videoView.setMediaController(new MediaController(this));
        videoView.setVideoURI(Uri.parse(videoUrl));
        videoView.start();
        videoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                mp.start();
                //mp.setLooping(true);
            }
        });
        videoView.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                mp.start();
                if (first < size - 1) {
                    first++;
                    String videoUrl = videoList.get(first).getRes().getVideoCode();
                    videoView.setVideoURI(Uri.parse(videoUrl));
                } else {
                    first = 0;
                    String videoUrl = videoList.get(first).getRes().getVideoCode();
                    videoView.setVideoURI(Uri.parse(videoUrl));
                }
            }
        });
    }

    //生成二维码
    private void initQrcode() {
        //支付二维码
        String payQRCodeUrl = resource.getTemple().getDonate_url();
        ImageView imageView = (ImageView) findViewById(R.id.donateQrcode);
        if (payQRCodeUrl == "") {
            Toast.makeText(this, "支付二维码为空", Toast.LENGTH_SHORT).show();
        } else {
            Picasso.with(this)
                    .load(payQRCodeUrl)
                    .placeholder(R.mipmap.syzx)
                    .error(R.mipmap.syzx)
                    .into(imageView);
        }
        //寺院在线二维码
        getTempleQRCode();
    }

    /**
     * Description: 寺院在线二维码
     */
    private void getTempleQRCode() {
        String templeQRCodeUrl = resource.getSiyuanzaixian().getQrcode_url();
        ImageView imageView = (ImageView) findViewById(R.id.templeQRCode);
        Picasso.with(this)
                .load(templeQRCodeUrl)
                .error(R.mipmap.syzx)
                .into(imageView);
    }

    //请求捐赠列表数据
    private void getDonateServer() {
        //首页输入 clientId 传递接收
        String url = PRODUCT_URL + "/nativeList?clientId=" + number;
        HttpUtils utils = new HttpUtils();
        utils.send(HttpMethod.GET, url, new RequestCallBack<String>() {

            @Override
            public void onSuccess(ResponseInfo<String> responseInfo) {
                String result = responseInfo.result;

                Gson gson = new Gson();
                donateList = gson.fromJson(result, DonateList.class);
                maxPageNum = donateList.getTotalPage();
                list = donateList.getList();
                myAdapter = new MyAdapter(list);
                recyclerView.setAdapter(myAdapter);
            }

            @Override
            public void onFailure(HttpException e, String s) {
                Logger.d(e + s);
            }
        });
    }

    private class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {
        /**
         * Instantiates a new My adapter.
         *
         * @param list the list
         */
        public MyAdapter(List<DonateList.ListBean> list) {
            super();
        }

        @Override
        public MyAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(TestActivity.this).inflate(R.layout.donate_list, parent, false);
            ViewHolder holder = new ViewHolder(view);
            return holder;
        }

        @Override
        public void onBindViewHolder(MyAdapter.ViewHolder holder, int position) {
            Picasso.with(TestActivity.this)
                    .load(donateList.getList().get(position).getHeadimgurl())
                    .error(R.mipmap.avantar)
                    .into(holder.userIcon);
            holder.userName.setText(list.get(position).getSalutation());
            holder.donateFee.setText(list.get(position).getAmount() + " 元");
            holder.comment.setText(list.get(position).getContent());

            //监听
            //如果 position == list.size()-1; 时 增加一条数据 随机
            if ((position % 19) == 0 && position > 0) {
                System.out.println("insert------------");
                String url = PRODUCT_URL + "/nativeList?clientId=" + number + "&page=1";
                HttpUtils utils = new HttpUtils();
                utils.send(HttpMethod.GET, url, new RequestCallBack<String>() {
                    @Override
                    public void onSuccess(ResponseInfo<String> responseInfo) {
                        String result = responseInfo.result;
                        Gson gson = new Gson();
                        DonateList dl = gson.fromJson(result, DonateList.class);
                        List<DonateList.ListBean> nextList = dl.getList();
                        Random random = new Random();
                        int randomNum = random.nextInt(19);
                        list.add(0, nextList.get(randomNum));//随机取一个数据
                        myAdapter.notifyItemInserted(0);
                        //如果在第一项添加模拟数据需要调用 scrollToPosition（0）把列表移动到顶端（可选）
                        recyclerView.scrollToPosition(0);
                        //listScrollUp();
                    }

                    @Override
                    public void onFailure(HttpException e, String s) {
                        System.out.println(e + s);
                    }
                });
            }
        }

        @Override
        public int getItemCount() {
            return list.size();
        }

        /**
         * The type View holder.
         */
        public class ViewHolder extends RecyclerView.ViewHolder {

            private final ImageView userIcon;
            private final TextView donateFee;
            private final TextView comment;
            private final TextView userName;

            /**
             * Instantiates a new View holder.
             *
             * @param itemView the item view
             */
            public ViewHolder(View itemView) {
                super(itemView);
                userIcon = (ImageView) itemView.findViewById(R.id.userIcon);
                userName = (TextView) itemView.findViewById(R.id.userName);
                donateFee = (TextView) itemView.findViewById(R.id.donateFee);
                comment = (TextView) itemView.findViewById(R.id.comment);
            }
        }
    }

    /**
     * The Handler.
     * 滚动动画
     */
    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            handler.removeCallbacks(run_scroll_up);

            if (msg.what == INSERT_DONATE) {
                initDonation(wsMessage);
            }
        }
    };

    /**
     * List scroll up.
     */
    public void listScrollUp() {
        handler.postDelayed(run_scroll_up, 0);
    }

    /**
     * List scroll off.
     */
    public void listScrollOff() {
        handler.removeCallbacks(run_scroll_up);
    }

    /**
     * The Run scroll up.
     */
    Runnable run_scroll_up = new Runnable() {
        @Override
        public void run() {
            recyclerView.scrollBy(1, 1);
            handler.postDelayed(run_scroll_up, 10);
        }
    };

    private void deleteApk() {
        File file = new File(Environment.getExternalStorageDirectory().getPath() + "/ad.apk");
        if (file.exists()) {
            file.delete();
            System.out.println("删除文件");
        } else {
            return;
        }
    }

    /*版本检测-start*/
    private void currentVersion() {
        PackageManager packageManager = getPackageManager();
        try {
            PackageInfo packageInfo = packageManager.getPackageInfo(getPackageName(), 0);
            mVersionCode = packageInfo.versionCode;
            String currentVersion = packageInfo.versionName;
            mCurrentVersionName = Float.parseFloat(currentVersion);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void serverVersion() {
        String serverVersionUrl = PRODUCT_URL + "/app_check";
        HttpUtils utils = new HttpUtils();
        utils.send(HttpMethod.GET, serverVersionUrl, new RequestCallBack<String>() {
            @Override
            public void onSuccess(ResponseInfo<String> responseInfo) {
                String result = responseInfo.result;
                parseVersion(result);
            }

            @Override
            public void onFailure(HttpException e, String s) {
                e.printStackTrace();
            }
        });
    }

    private void parseVersion(String result) {
        Gson gson = new Gson();
        AppVersion version = gson.fromJson(result, AppVersion.class);
        String serverVersionName = version.getVersionName();
        float serverVersionNameToInt = Float.parseFloat(serverVersionName);
        mDownloadUrl = version.getUpdateUrl();

        if (serverVersionNameToInt > mCurrentVersionName) {
            checkUpdate();
        } else {
            Logger.d("当前是最新版本" + serverVersionNameToInt);
        }
    }

    private void checkUpdate() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("有新版本了！");
        builder.setMessage("建议下载安装体验新功能");
        builder.setPositiveButton("下载", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                Toast.makeText(TestActivity.this, "正在下载...", Toast.LENGTH_SHORT).show();
                downloadAPK();
            }
        });
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                Toast.makeText(TestActivity.this, "下载已取消", Toast.LENGTH_SHORT).show();
            }
        });
        builder.create().show();
    }

    private void downloadAPK() {
        HttpUtils utils = new HttpUtils();
        String targetPath = Environment.getExternalStorageDirectory().getPath() + "/ad.apk";
        utils.download(mDownloadUrl, targetPath, true, new RequestCallBack<File>() {

            @Override
            public void onSuccess(ResponseInfo<File> responseInfo) {
                //成功后跳转到安装界面
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.addCategory(Intent.CATEGORY_DEFAULT);
                intent.setDataAndType(Uri.fromFile(responseInfo.result), "application/vnd.android.package-archive");
                startActivity(intent);
            }

            @Override
            public void onLoading(long total, long current, boolean isUploading) {
                super.onLoading(total, current, isUploading);
                mPercent = current * 100 / total;
                showNotification();
            }

            @Override
            public void onFailure(HttpException e, String s) {
                Toast.makeText(TestActivity.this, "服务器下载出问题了...>_<", Toast.LENGTH_SHORT).show();
                e.printStackTrace();
                System.out.println("e=" + e + ";s=" + s);
            }
        });
    }

    //Notification 下载动画
    private void showNotification() {
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this);
        Notification notification = new Notification();
        notification.icon = R.drawable.download_anim;
        notification.flags |= Notification.FLAG_ONGOING_EVENT;//删除后还会出现 提示会更加明显
        builder.setSmallIcon(R.drawable.download_anim);
        //builder.setProgress(100, 0, true);
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher);
        builder.setLargeIcon(bitmap);
        builder.setProgress(100, (int) mPercent, false);//进度条显示进度
        builder.setColor(Color.argb(255, 3, 169, 244));//下拉后显示在右下角的的图标的颜色
        builder.setContentTitle("正在下载...");
        builder.setContentText("当前进度:" + mPercent + "%");
        NotificationManager manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        manager.notify(0, builder.build());

        //下载完成后notification取消显示
        if (mPercent == 100) {
            manager.cancel(0);
        } else {
            //System.out.println("nothing happened");
        }
    }
    /*版本检测-end*/

    @Override
    protected void onStop() {
        super.onStop();
        finish();
        //Logger.d("onStop : finish app");
    }

    @Override
    protected void onDestroy() {
        handler.removeCallbacks(runnable); //停止刷新
        super.onDestroy();
        finish();
        //Logger.d("onDestroy : finish app");
    }

    /**
     * 获取进程号对应的进程名
     *
     * @param pid 进程号
     * @return 进程名
     */
    private static String getProcessName(int pid) {
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader("/proc/" + pid + "/cmdline"));
            String processName = reader.readLine();
            if (!TextUtils.isEmpty(processName)) {
                processName = processName.trim();
            }
            return processName;
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        } finally {
            try {
                if (reader != null) {
                    reader.close();
                }
            } catch (IOException exception) {
                exception.printStackTrace();
            }
        }
        return null;
    }
}
