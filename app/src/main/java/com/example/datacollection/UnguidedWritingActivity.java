package com.example.datacollection;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.res.Resources;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class UnguidedWritingActivity extends AppCompatActivity {

    private CanvasView canvasView;
    MyDbHandler db;
    int i = 1;
    String character;
    int width;
    int height;
    String stuff;
    TextView characterTextView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_unguided_writing);
        canvasView = (CanvasView)findViewById(R.id.canvas);
        characterTextView = (TextView)findViewById(R.id.charTextView);

        width  = Resources.getSystem().getDisplayMetrics().widthPixels;
        height = Resources.getSystem().getDisplayMetrics().heightPixels;

        db = new MyDbHandler(this, "HandwritingData.db", null, 1);
        characterTextView.setText("Write  " + util.getCharacter(i));
    }
    public void Next(View view)
    {
        int h = canvasView.getHeight();
        int w = canvasView.getWidth();
        Bundle bundle = getIntent().getExtras();
        stuff = bundle.getString("stuff");
        if (canvasView.listX.size() != 0) {
            String id = db.getStrokeid(Integer.parseInt(stuff),util.getCharacter(i),"Unguided");
            if(id == null) {
                db.addStrokeData(canvasView, Integer.parseInt(stuff), w, h, util.getCharacter(i), "Unguided", util.getDeviceName());
            }
            else
            {
                db.updateStrokeData(canvasView, Integer.parseInt(db.getStrokeid(Integer.parseInt(stuff),util.getCharacter(i),"Unguided")));
            }
        }
        canvasView.clearCanvas();
        if (i == 37)
        {
            Intent intent = new Intent(UnguidedWritingActivity.this, BaselineWritingActivity.class);
            intent.putExtras(bundle);
            startActivity(intent);
        }
        else {
            i++;
            characterTextView.setText("Write  " + util.getCharacter(i));
        }
    }
    public void clearCanvas(View view) {
        canvasView.clearCanvas();
    }
    public void Back(View view)
    {
        canvasView.clearCanvas();
        if ( i > 1)
        {
            i--;
            characterTextView.setText("Write  " + util.getCharacter(i));
        }
    }


}
