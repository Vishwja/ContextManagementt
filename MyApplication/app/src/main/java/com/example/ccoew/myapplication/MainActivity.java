package com.example.ccoew.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.Manifest;import android.app.Activity;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.Cursor;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.test.mock.MockPackageManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    Button btnShowLocation,badd;
    GPSTracker gps;
    TextView text1,text2,text3;
    EditText t1,t2,t3,t4,t5,t6,t7,t8,t9,t10;
    SQLiteDatabase db1;
    private static final int REQUEST_CODE_PERMISSION = 2;
    String mPermission = Manifest.permission.ACCESS_FINE_LOCATION;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        try {
            if (ActivityCompat.checkSelfPermission(this, mPermission)
                    != MockPackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(this, new String[]{mPermission},
                        REQUEST_CODE_PERMISSION);
// If any permission above not allowed by user, this condition will
//execute every time, else your else part will work
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        btnShowLocation = (Button) findViewById(R.id.button);
        //badd = (Button)findViewById(R.id.button4);
        text1=(TextView)findViewById(R.id.textView);
        text2=(TextView)findViewById(R.id.textView2);
        text3=(TextView)findViewById(R.id.textView3);
        t1=(EditText)findViewById(R.id.editText4);
        t2=(EditText)findViewById(R.id.editText5);
        t3=(EditText)findViewById(R.id.editText7);
        t4=(EditText)findViewById(R.id.editText8);
        t5=(EditText)findViewById(R.id.editText9);
        t6=(EditText)findViewById(R.id.editText10);
        t7=(EditText)findViewById(R.id.editText11);
        t8=(EditText)findViewById(R.id.editText12);
        t9=(EditText)findViewById(R.id.editText13);
        t10=(EditText)findViewById(R.id.editText14);

        db1 = openOrCreateDatabase("stud", Context.MODE_PRIVATE, null);
        db1.execSQL("CREATE TABLE IF NOT EXISTS stud(" +
                "name VARCHAR," +
                "gender VARCHAR," +
                "age VARCHAR," +
                "ht VARCHAR," +
                "wt VARCHAR," +
                "color VARCHAR," +
                "edu VARCHAR," +
                "des VARCHAR," +
                "ppr VARCHAR," +
                "admin VARCHAR)");
    }

    void mymsg(String title, String str) {
        AlertDialog.Builder bld = new AlertDialog.Builder(this);
        bld.setTitle(title);
        bld.setMessage(str);
        bld.show();
    }

    void dispIn()
    {
        StringBuffer sb = new StringBuffer();
        Cursor c = db1.rawQuery("select * from stud", null);
        while (c.moveToNext()) {
            sb.append("\nName : " + c.getString(0));
            sb.append("\nEdu. Qualification : " + c.getString(6));
            sb.append("\nDesignation : " + c.getString(7));
            sb.append("\nPaper Published : " + c.getString(8));
            sb.append("\nAdmin Resp. : " + c.getString(9));
        }
        String str;
        str = sb.toString();
        mymsg("Database summary", str);
    }

    void dispOut()
    {
        StringBuffer sb = new StringBuffer();
        Cursor c = db1.rawQuery("select * from stud", null);
        while (c.moveToNext()) {
            sb.append("\nName : " + c.getString(0));
            sb.append("\nEdu. Qualification : " + c.getString(6));
            sb.append("\nDesignation : " + c.getString(7));
            sb.append("\nGender : " + c.getString(1));
            sb.append("\nAge : " + c.getString(2));
            sb.append("\nHeight : " + c.getString(3));
            sb.append("\nWeight : " + c.getString(4));
            sb.append("\nColour Complexion : " + c.getString(5));
        }
        String str;
        str = sb.toString();
        mymsg("Database summary", str);
    }

    public void findLoc(View v)
    {
        gps = new GPSTracker(MainActivity.this);
// check if GPS enabled
        double lat = gps.getLatitude();
        double longitude = gps.getLongitude();
        text2.setText("Latitude-"+gps.getLatitude());
        text3.setText("Longitude-"+gps.getLongitude());
//assuming 18.4864° , 73.8161°
        if((18.4860000 <= lat && lat <= 18.4870000) && (73.8150000 <= longitude
                && longitude <= 73.8160500)) {
            text1.setText("In College Campus");
            dispIn();
        }
        else{
            text1.setText("Outside College Campus");
            dispOut();
        }
    }

    public void addData(View v)
    {
        db1.execSQL("insert into stud values ('"+
                t1.getText() + "','"
                + t2.getText() + "','"
                + t3.getText() + "','"
                + t4.getText() + "','"
                + t5.getText() + "','"
                + t6.getText() + "','"
                + t7.getText() + "','"
                + t8.getText() + "','"
                + t9.getText() + "','"
                + t10.getText() + "')");
        Toast.makeText(getApplicationContext(), "Saved into student database",
                Toast.LENGTH_LONG).show();
    }
}