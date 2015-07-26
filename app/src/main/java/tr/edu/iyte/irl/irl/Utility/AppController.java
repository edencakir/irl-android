package tr.edu.iyte.irl.irl.Utility;

import android.app.Application;
import android.graphics.Typeface;
import android.text.TextUtils;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

/**
 * Created by Enes on 7/26/15.
 */

public class AppController extends Application {

    private static final String VOLLEY_TAG_ALL_REQUESTS = AppController.class
            .getSimpleName();
    private static AppController mInstance;
    // SMS Count & Date
    private int mSMSCount = 0;

    // When the app first open
    private String smsdate = "";
    private RequestQueue mRequestQueue;
    private Typeface tahoma;

    public static synchronized AppController getInstance() {
        return mInstance;
    }

    public static String getVolleyTagAllRequests() {
        return VOLLEY_TAG_ALL_REQUESTS;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        mInstance = this;

        tahoma = Typeface.createFromAsset(getAssets(),
                "Tahoma.ttf");
    }

    public RequestQueue getRequestQueue() {
        if (mRequestQueue == null) {
            mRequestQueue = Volley.newRequestQueue(getApplicationContext());
        }

        return mRequestQueue;
    }

    public <T> void addToRequestQueue(Request<T> req, String tag) {
        req.setTag(TextUtils.isEmpty(tag) ? VOLLEY_TAG_ALL_REQUESTS : tag);
        getRequestQueue().add(req);
    }

    public <T> void addToRequestQueue(Request<T> req) {
        req.setTag(VOLLEY_TAG_ALL_REQUESTS);
        getRequestQueue().add(req);
    }

    public void cancelPendingRequests(Object tag) {
        if (mRequestQueue != null) {
            mRequestQueue.cancelAll(tag);
        }
    }

    public Typeface getTahomaFont() {
        return tahoma;
    }

    public RequestQueue getmRequestQueue() {
        return mRequestQueue;
    }

    public void setmRequestQueue(RequestQueue mRequestQueue) {
        this.mRequestQueue = mRequestQueue;
    }
}
