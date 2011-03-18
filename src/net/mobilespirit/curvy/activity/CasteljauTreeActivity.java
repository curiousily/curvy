package net.mobilespirit.curvy.activity;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.preference.PreferenceManager;
import android.provider.MediaStore;
import android.view.Window;
import net.mobilespirit.curvy.R;
import net.mobilespirit.curvy.application.CurvyApplication;
import net.mobilespirit.curvy.domain.point.Point2D;
import net.mobilespirit.curvy.domain.curve.BezierCurve;
import net.mobilespirit.curvy.domain.tree.CasteljauTree;
import net.mobilespirit.curvy.painter.CasteljauTreePainter;

import java.util.List;

/**
 * Curvy - university related project
 * User: Venelin Valkov <valkov@mobile-spirit.net>
 * Date: 28-02-2011
 * Time: 16:18
 * Package: net.mobilespirit.curvy.activity
 */
public class CasteljauTreeActivity extends BaseCurvyActivity {

    private static final int PROGRESS_DIALOG_ID = 1;
    private static final String IMAGE_TITLE = "casteljau";
    private static final String IMAGE_DESCRIPTION = "casteljau";

    private static final String PROGRESS_KEY = "progress";

    private static final int VIEW_IMAGE = 0;

    private String imagePath;

    private ProgressDialog progressDialog;

    private BuildCasteljauTreeTask task;

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message message) {
            Bundle data = message.getData();
            progressDialog.setMessage(data.getString(PROGRESS_KEY));
        }
    };
    private boolean notShowingImage = true;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
    }

    @Override
    protected void onResume() {
        super.onResume();
        showDialog(PROGRESS_DIALOG_ID);
    }

    @Override
    protected void onPause() {
        super.onPause();
        if(!task.isCancelled()) {
            task.cancel(true);
        }
        if(notShowingImage) {
            deleteImage();
        }
        dismissDialog(PROGRESS_DIALOG_ID);
    }

    @Override
    protected Dialog onCreateDialog(int id) {
        switch (id) {
            case PROGRESS_DIALOG_ID:
                progressDialog = new ProgressDialog(this);
                progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
                progressDialog.setTitle(getString(R.string.building_tree_progress_title));
                progressDialog.setMessage(getString(R.string.building_tree_init));
                return progressDialog;
            default:
                return  null;
        }
    }

    @Override
    protected void onPrepareDialog(int id, Dialog dialog) {
        switch (id) {
            case PROGRESS_DIALOG_ID:
                progressDialog.setProgress(0);
                task = new BuildCasteljauTreeTask();
                task.execute(getCurvyApplication().getPointList());
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case VIEW_IMAGE:
                notShowingImage = true;
                deleteImage();
                finish();
            break;
        }
    }

    private void deleteImage() {
        if(imagePath != null) {
            getContentResolver().delete(Uri.parse(imagePath), null, null);
            imagePath = null;
        }
    }

    private void onBuildComplete(String imagePath) {
        this.imagePath = imagePath;
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_VIEW);

        intent.setDataAndType(Uri.parse(imagePath), "image/png");
        notShowingImage = false;
        startActivityForResult(intent, VIEW_IMAGE);
    }

    private class BuildCasteljauTreeTask extends AsyncTask<List<Point2D>, Integer, String> {

        @Override
        protected String doInBackground(List<Point2D>... lists) {
            sendProgressMessage(R.string.building_coordinates_done);
            BezierCurve curve = new BezierCurve(getCurveCoefficient(), lists[0]);
            CasteljauTree tree = curve.buildCasteljauTree();
            sendProgressMessage(R.string.building_tree_done);
            Bitmap bitmap = CasteljauTreePainter.createBitmap(tree);
            sendProgressMessage(R.string.building_image_done);
            imagePath = MediaStore.Images.Media.insertImage(getContentResolver(), bitmap, IMAGE_TITLE, IMAGE_DESCRIPTION);
            sendProgressMessage(R.string.saving_image_done);
            return imagePath;
        }

        private void sendProgressMessage(int resourceId) {
            Bundle bundle = new Bundle();
            bundle.putString(PROGRESS_KEY, getString(resourceId));
            Message message = handler.obtainMessage();
            message.setData(bundle);
            handler.sendMessage(message);
        }

        @Override
        protected void onPostExecute(String imagePath) {
            onBuildComplete(imagePath);
        }
    }
}