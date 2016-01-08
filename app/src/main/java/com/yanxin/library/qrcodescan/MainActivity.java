package com.yanxin.library.qrcodescan;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.zxing.WriterException;
import com.yanxin.library.zxing.activity.CaptureActivity;
import com.yanxin.library.zxing.utils.QrCodeUtils;

public class MainActivity extends AppCompatActivity {

    private EditText mEditText;
    private ImageView mQRCodeImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mEditText = (EditText) findViewById(R.id.edit_content);
        mQRCodeImageView = (ImageView) findViewById(R.id.image_qrcode);
    }

    public void startScan(View view) {
        startActivity(new Intent(this, CaptureActivity.class));
    }

    public void createQRCode(View view) {
        String content = mEditText.getText().toString();
        if (TextUtils.isEmpty(content)) {
            Toast.makeText(this, "不能为空", Toast.LENGTH_SHORT).show();
            return;
        }
        try {
            Bitmap bitmap = QrCodeUtils.create2DCode(content);
            mQRCodeImageView.setImageBitmap(bitmap);
        } catch (WriterException e) {
            e.printStackTrace();
        }
    }

}
