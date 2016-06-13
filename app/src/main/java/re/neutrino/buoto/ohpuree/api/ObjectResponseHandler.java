package re.neutrino.buoto.ohpuree.api;

import com.google.gson.Gson;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.TextHttpResponseHandler;

import java.lang.reflect.ParameterizedType;

import cz.msebera.android.httpclient.Header;

/**
 * Response handler using gson to deserialize from raw JSON data to object of type T.
 */
public abstract class ObjectResponseHandler<T> extends AsyncHttpResponseHandler
{
    /**
     * Default charset UTF-8
     */
    public static final String DEFAULT_CHARSET = "UTF-8";
    protected static String TAG = "APIClient";
    private final Class<T> cls;
    private final Gson gson;

    /**
     * Creates handler of any object
     * Gets class type of the object
     */
    public ObjectResponseHandler()
    {
        // uber hack from http://stackoverflow.com/questions/3403909/get-generic-type-of-class-at-runtime
        this.cls = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
        gson = new Gson();
    }

    /**
     * API success response handler
     * @param statusCode of the response
     * @param headers incoming headers
     * @param responseBody response from server
     */
    @Override
    public void onSuccess(int statusCode, Header[] headers, byte[] responseBody)
    {
        T object = gson.fromJson(TextHttpResponseHandler.getResponseString(responseBody, DEFAULT_CHARSET), cls);
        onSuccess(statusCode, object);
    }

    /**
     * API error response handler
     * @param statusCode of the response
     * @param headers incoming headers
     * @param responseBody response from server
     * @param error exception object from the library
     */
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

    /**
     * Has to define action on success
     * @param statusCode of the response
     * @param object got form response
     */
    public abstract void onSuccess(int statusCode, T object);

    /**
     * Has to define action on failure
     * @param statusCode of the response
     * @param responseString error message
     */
    public abstract void onFailure(int statusCode, String responseString);
}
