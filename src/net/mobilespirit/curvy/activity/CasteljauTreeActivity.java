package net.mobilespirit.curvy.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.ViewGroup;
import android.view.Window;
import net.mobilespirit.curvy.view.CasteljauTreeView;

/**
 * Project info
 * User: Vini
 * Date: 28-02-2011
 * Time: 16:18
 * Package: net.mobilespirit.curvy.activity
 */
public class CasteljauTreeActivity extends Activity {
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.FILL_PARENT, ViewGroup.LayoutParams.FILL_PARENT);
        setContentView(new CasteljauTreeView(this), params);
    }
}