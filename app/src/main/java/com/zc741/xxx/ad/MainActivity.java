package com.zc741.xxx.ad;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest.HttpMethod;
import com.orhanobut.logger.Logger;
import com.zc741.xxx.ad.bean.TotalTemple;

import static com.zc741.xxx.ad.TestActivity.LOCALHOST_URL;

/**
 * The type Main activity.
 */
public class MainActivity extends AppCompatActivity {

    private EditText editText;
    private String content;
    private String sharePreferenceNumber;
    private int total;
    private TextView templeCounts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);//隐藏标题
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);//设置全屏

        setContentView(R.layout.activity_main);

        editText = (EditText) findViewById(R.id.editText);
        Button button = (Button) findViewById(R.id.button);
        templeCounts = (TextView) findViewById(R.id.totalTemple);

        SharedPreferences sharedPreferences = getSharedPreferences("preferencesNumber", MODE_PRIVATE);//第一个参数是文件的名称
        sharePreferenceNumber = sharedPreferences.getString("number", "");
        editText.setText(sharePreferenceNumber);

        // 请求数据 寺院总个数 http://localhost:8080/cli/totalTemple
        getTotalTemple();


        assert button != null;
        button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                content = editText.getText().toString();
                if (!content.isEmpty()) {
                    if (Integer.parseInt(content) > total) {
                        Toast.makeText(MainActivity.this, "请输入正确的寺庙号码", Toast.LENGTH_LONG).show();
                    } else {
                        Intent intent = new Intent(MainActivity.this, TestActivity.class);
                        intent.putExtra("number", content);
                        startActivity(intent);
                        finish();
                    }
                } else {
                    Toast.makeText(MainActivity.this, "请输入机器编号", Toast.LENGTH_LONG).show();
                }
            }
        });


        //强制弹出键盘
        /*TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                editText.setFocusable(true);
                editText.requestFocus();
                InputMethodManager inputMethodManager = (InputMethodManager) editText.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
                inputMethodManager.toggleSoftInput(0, InputMethodManager.SHOW_FORCED);
            }
        };
        Timer timer = new Timer();
        timer.schedule(timerTask, 300);*/
    }

    private void getTotalTemple() {
        String url = LOCALHOST_URL + "/totalTemple";
        HttpUtils utils = new HttpUtils();
        utils.send(HttpMethod.GET, url, new RequestCallBack<String>() {
            @Override
            public void onSuccess(ResponseInfo<String> responseInfo) {
                String result = responseInfo.result;
                //Logger.json(result);
                parseTotal(result);
            }

            @Override
            public void onFailure(HttpException e, String s) {

            }
        });
    }

    private void parseTotal(String result) {
        Gson gson = new Gson();
        TotalTemple totalTemple = gson.fromJson(result, TotalTemple.class);
        total = totalTemple.getTotal();
        Logger.d(total);
        templeCounts.setText("寺院总数：" + total);
    }

    @Override
    protected void onStop() {
        SharedPreferences preferences = getSharedPreferences("preferencesNumber", MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("number", content);
        editor.apply();
        super.onStop();
    }
}
