package re.neutrino.buoto.ohpuree.api;

import com.google.gson.Gson;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.TextHttpResponseHandler;

import java.lang.reflect.ParameterizedType;

import cz.msebera.android.httpclient.Header;

/**
 * Response handler using gson to deserialize from raw JSON data to object of type T.
 * <p>
 */
public abstract class ObjectResponseHandler<T> extends AsyncHttpResponseHandler
{
    public static final String DEFAULT_CHARSET = "UTF-8";
    protected static String TAG = "APIClient";
    private final Class<T> cls;
    private final Gson gson;

    public ObjectResponseHandler()
    {
        // uber hack from http://stackoverflow.com/questions/3403909/get-generic-type-of-class-at-runtime
        this.cls = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
        gson = new Gson();
    }

    @Override
    public void onSuccess(int statusCode, Header[] headers, byte[] responseBody)
    {
        T object = gson.fromJson(TextHttpResponseHandler.getResponseString(responseBody, DEFAULT_CHARSET), cls);
        onSuccess(statusCode, object);
    }

    @Override
    public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error)
    {
        String reponseString = TextHttpResponseHandler.getResponseString(responseBody, DEFAULT_CHARSET);
        if (reponseString == null)
        {
            reponseString = "";
        }
        onFailure(statusCode, reponseString);
    }

    public abstract void onSuccess(int statusCode, T object);

    public abstract void onFailure(int statusCode, String responseString);


}
