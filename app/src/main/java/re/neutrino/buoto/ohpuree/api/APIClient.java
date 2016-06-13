package re.neutrino.buoto.ohpuree.api;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;


/**
 * APIClient is a static class wrapping connection to backend.
 *
 */
public class APIClient
{
    public static final String API_URL = "http://192.168.43.65:8000";

    private static final AsyncHttpClient httpClient = new AsyncHttpClient();

    private APIClient()
    {
    }

    public static void get(String path, RequestParams params, AsyncHttpResponseHandler handler)
    {
        httpClient.get(getAbsoluteUrl(path), params, handler);
    }

    public static void post(String path, RequestParams params, AsyncHttpResponseHandler handler)
    {
        httpClient.post(getAbsoluteUrl(path), params, handler);
    }

    private static String getAbsoluteUrl(String path)
    {
        return API_URL + path;
    }
}
