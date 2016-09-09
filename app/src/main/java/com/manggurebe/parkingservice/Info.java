package com.manggurebe.parkingservice;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.text.method.LinkMovementMethod;
import android.text.method.ScrollingMovementMethod;
import android.widget.TextView;

/**
 * Created by buqento on 7/8/2016.
 */

public class Info extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.info);
        TextView tv = (TextView) findViewById(R.id.textInfo);
        tv.setMovementMethod(new ScrollingMovementMethod());
        tv.setMovementMethod(LinkMovementMethod.getInstance());
    }

}
