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
import android.widget.Toast;

/**
 * The type Main activity.
 */
public class MainActivity extends AppCompatActivity {

    private EditText editText;
    private String content;
    private String sharePreferenceNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);//隐藏标题
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);//设置全屏

        setContentView(R.layout.activity_main);

        editText = (EditText) findViewById(R.id.editText);
        Button button = (Button) findViewById(R.id.button);

        SharedPreferences sharedPreferences = getSharedPreferences("preferencesNumber", MODE_PRIVATE);//第一个参数是文件的名称
        sharePreferenceNumber = sharedPreferences.getString("number", "");
        editText.setText(sharePreferenceNumber);

        assert button != null;
        button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                content = editText.getText().toString();
                if (!content.isEmpty()) {
                    Intent intent = new Intent(MainActivity.this, TestActivity.class);
                    intent.putExtra("number", content);
                    startActivity(intent);
                    finish();
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

    @Override
    protected void onStop() {
        SharedPreferences preferences = getSharedPreferences("preferencesNumber", MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("number", content);
        editor.apply();
        super.onStop();
    }
}
