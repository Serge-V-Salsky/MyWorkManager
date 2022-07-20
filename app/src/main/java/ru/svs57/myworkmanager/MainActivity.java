package ru.svs57.myworkmanager;

import androidx.appcompat.app.AppCompatActivity;
import androidx.work.OneTimeWorkRequest;
import androidx.work.Operation;
import androidx.work.WorkManager;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import java.util.UUID;


public class MainActivity extends AppCompatActivity {
    final String TAG = "workmng";
 //   WorkRequest myWorkRequest;
    private UUID workerId = null;
    private TextView tvWorkerField;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tvWorkerField = findViewById(R.id.tvWorkerID);
    }

    @Override
    public void onStart()
    {
        super.onStart();
    }

    public void buttonProc(View view)
    {
       switch(view.getId())
        {
            case(R.id.button1):
                Log.d(TAG, "\n================\n");
                OneTimeWorkRequest.Builder myWorkRequest =
                        new OneTimeWorkRequest.Builder(MyWorker.class);
                OneTimeWorkRequest myWork = myWorkRequest.build();
                WorkManager.getInstance(this).enqueue(myWork);
                workerId = myWork.getId();
                tvWorkerField.setText(workerId.toString());
                Log.d(TAG, String.valueOf(workerId));
                break;
            case (R.id.button2):
                if (workerId != null)
                {
                    Context myContext = getApplicationContext();
                    Operation status = WorkManager.getInstance(myContext).cancelWorkById(workerId);
                    Log.d(TAG, "Pressed " + workerId + " " + status);
                }
                break;
        }
     }
}
