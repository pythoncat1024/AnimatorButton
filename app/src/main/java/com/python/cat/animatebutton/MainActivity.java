package com.python.cat.animatebutton;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.python.cat.animatorbutton.AnimatorButton;

public class MainActivity extends AppCompatActivity {

    private AnimatorButton ab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ab = (AnimatorButton) findViewById(R.id.btn_animate);
        ab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ab.start(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(getApplication(), "ok", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }
}
