package com.zc741.xxx.ad;

import android.Manifest;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

import com.zc741.xxx.ad.receiver.PermissionChecker;

public class SplashActivity extends AppCompatActivity {

    private static final int REQUEST_CODE = 0;
    static final String[] PERMISSIONS = new String[]{
            Manifest.permission.READ_PHONE_STATE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
    };
    private PermissionChecker mPermissionChecker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);//隐藏标题
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);//设置全屏

        setContentView(R.layout.activity_splash);

        //权限获取
        mPermissionChecker = new PermissionChecker(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (mPermissionChecker.lackPermission(PERMISSIONS)) {
            startPermissionActivity();
        } else {
            startActivity(new Intent(this, MainActivity.class));
            finish();
        }
    }

    private void startPermissionActivity() {
        PermissionsActivity.startActivityForResult(this, REQUEST_CODE, PERMISSIONS);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_CODE && requestCode == PermissionsActivity.PERMISSIONS_DENIED) {
            finish();
        } else {
            startActivity(new Intent(this, MainActivity.class));
            finish();
        }
    }
}
