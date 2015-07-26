package tr.edu.iyte.irl.irl.Utility;

import java.io.UnsupportedEncodingException;
import java.util.Map;

import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.android.volley.toolbox.HttpHeaderParser;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

/**
 * Created by Enes on 7/26/15.
 */

public class GsonRequest<T> extends Request<T> {
    private final Gson mGson = new Gson();
    private final Class<T> mResponseClass;
    private final Listener<T> mListener;
    private final Map<String, String> mParameters;
    private Map<String, String> mHeaders;

    public GsonRequest(int method, String url, Class<T> responseClass,
                       Map<String, String> parameters, Listener<T> listener,
                       ErrorListener errorListener) {
        super(method, url, errorListener);

        this.mListener = listener;
        this.mResponseClass = responseClass;
        this.mParameters = parameters;
    }

    @Override
    public Map<String, String> getHeaders() throws AuthFailureError {
        return mHeaders != null ? mHeaders : super.getHeaders();
    }

    @Override
    protected Map<String, String> getParams() throws AuthFailureError {
        return mParameters;
    }

    @Override
    protected void deliverResponse(T response) {
        if (mListener != null) {
            mListener.onResponse(response);
        } else {
            Log.d("json",
                    "GsonRequest, listener was null delivering response.");
        }
    }

    @Override
    protected Response<T> parseNetworkResponse(NetworkResponse response) {
        try {
            String json = new String(response.data,
                    HttpHeaderParser.parseCharset(response.headers));

            Log.d("json", json);

            T result = mGson.fromJson(json, this.mResponseClass);

            return Response.success(result,
                    HttpHeaderParser.parseCacheHeaders(response));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();

            return Response.error(new ParseError(e));
        } catch (JsonSyntaxException e) {
            e.printStackTrace();

            return Response.error(new ParseError(e));
        }
    }
}