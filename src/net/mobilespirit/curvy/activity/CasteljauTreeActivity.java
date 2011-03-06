package net.mobilespirit.curvy.activity;

import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.provider.MediaStore;
import android.view.Window;
import net.mobilespirit.curvy.domain.Point.Point2D;
import net.mobilespirit.curvy.domain.curve.BezierCurve;
import net.mobilespirit.curvy.domain.tree.CasteljauTree;
import net.mobilespirit.curvy.painter.CasteljauTreePainter;

import java.util.ArrayList;
import java.util.List;

/**
 * Curvy - university related project
 * User: Venelin Valkov <valkov@mobile-spirit.net>
 * Date: 28-02-2011
 * Time: 16:18
 * Package: net.mobilespirit.curvy.activity
 */
public class CasteljauTreeActivity extends Activity {

//    private int id;

    private String imagePath;

    private ProgressDialog progressDialog;

    private static final int PROGRESS_DIALOG = 1;

    @Override
    protected Dialog onCreateDialog(int id) {
        switch (id) {
            case PROGRESS_DIALOG:
                progressDialog = new ProgressDialog(this);
                progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
                progressDialog.setTitle("Computing...");
                progressDialog.setMessage("Initializing the building tree process...");
                return progressDialog;
            default:
                return  null;
        }
    }

    @Override
    protected void onPrepareDialog(int id, Dialog dialog) {
        switch (id) {
            case PROGRESS_DIALOG:
                progressDialog.setProgress(0);
                double[] coordinates = getIntent().getDoubleArrayExtra("coordinates");
                Double[] newCoordinates = new Double[coordinates.length];
                for(int i = 0; i < coordinates.length;i++) {
                    newCoordinates[i] = coordinates[i];
                }
                BuildCasteljauTreeTask task = new BuildCasteljauTreeTask();
                task.execute(newCoordinates);
        }
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);

        showDialog(PROGRESS_DIALOG);
    }

    private void onBuildComplete(String imagePath) {
        dismissDialog(PROGRESS_DIALOG);
        this.imagePath = imagePath;
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_VIEW);

        intent.setDataAndType(Uri.parse(imagePath), "image/png");
        startActivityForResult(intent, 0);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);    
        switch (requestCode) {
            case 0:
                getContentResolver().delete(Uri.parse(imagePath), null, null);
                finish();
            break;
        }
    }

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message message) {
            Bundle data = message.getData();
            progressDialog.setMessage(data.getString("progress"));
        }
    };

    private class BuildCasteljauTreeTask extends AsyncTask<Double[], Integer, String> {

        @Override
        protected String doInBackground(Double[]... coordinatesArray) {
            Double[] coordinates = coordinatesArray[0];
            Bundle bundle = new Bundle();
//            bundle.putString("progress", "Preparing coordinates creation");

            List<Point2D> pointList = new ArrayList<Point2D>(coordinates.length);
            for(int i = 0; i < coordinates.length; i+=2) {
                Point2D point = new Point2D(coordinates[i], coordinates[i + 1]);
                pointList.add(point);
            }
            bundle.putString("progress", "Building coordinates complete");

            Message message = handler.obtainMessage();
            message.setData(bundle);
            handler.sendMessage(message);

            BezierCurve curve = new BezierCurve(0.4, pointList);
            Bitmap bitmap = CasteljauTreePainter.createBitmap(curve.buildCasteljauTree());

            bundle.putString("progress", "Building the tree complete");

            message = handler.obtainMessage();
            message.setData(bundle);
            handler.sendMessage(message);

            imagePath = MediaStore.Images.Media.insertImage(getContentResolver(), bitmap, "casteljau", "casteljau");
            return imagePath;
        }

        @Override
        protected void onPostExecute(String imagePath) {
            onBuildComplete(imagePath);
        }
    }
}