package com.vas.activity.dispatcher;

import androidx.activity.CompatActivity;
import androidx.activity.OnActivityResultCallback;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

public class MainActivity extends CompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(view -> {
            Intent intent = new Intent(getActivity(), ResultActivity.class);
            startActivityForResult(intent, new OnActivityResultCallback() {
                @Override
                public void handleOnActivityResult(int resultCode, @Nullable Intent data) {
                    String result = (resultCode == RESULT_OK) ? "RESULT_OK" : "RESULT_CANCELED";
                    Toast.makeText(getActivity(),
                            "OnActivityResultCallback executed: " + result,
                            Toast.LENGTH_LONG)
                            .show();
                }
            });
        });

    }
}
