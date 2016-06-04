package re.neutrino.buoto.ohpuree.api;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import re.neutrino.buoto.ohpuree.AppConfig;


/**
 * Created by buoto on 5/20/16.
 */
public class APIClient {
    private static final AsyncHttpClient httpClient = new AsyncHttpClient();

    private APIClient() {
    }

    public static void get(String path, RequestParams params, AsyncHttpResponseHandler handler) {
        httpClient.get(getAbsoluteUrl(path), params, handler);
    }

    public static void post(String path, RequestParams params, AsyncHttpResponseHandler handler) {
        httpClient.post(getAbsoluteUrl(path), params, handler);
    }

    private static String getAbsoluteUrl(String path) {
        return AppConfig.API_URL + path;
    }
}
