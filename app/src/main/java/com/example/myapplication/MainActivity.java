package com.example.myapplication;

import static java.lang.Thread.sleep;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.documentfile.provider.DocumentFile;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.os.Bundle;

import java.util.Random;


public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    public void openFolder()
    {
        Intent intent= new Intent(Intent.ACTION_OPEN_DOCUMENT_TREE);
        activityResultLauncher.launch(intent);

    }

    public void openFolderDialog(View view) {
        openFolder();

    }


    ActivityResultLauncher<Intent> activityResultLauncher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
        @Override
        public void onActivityResult(ActivityResult result) {
            if(result.getResultCode() == Activity.RESULT_OK){
                Intent intent = result.getData();
                Uri uri = intent.getData();
                Log.i("Directory Tree: ", String.valueOf(uri));
                DocumentFile df = DocumentFile.fromTreeUri(getApplicationContext(), uri);
                df.createFile("txt","test.txt");
                Log.i("Directory with file: ", String.valueOf(df.getUri()));
                Intent i = new Intent(MainActivity.this, MainActivity2.class);
                startActivity(i);

            }

        }
    });
}