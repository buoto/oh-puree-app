package re.neutrino.buoto.ohpuree.api.response;

import java.util.ArrayList;

/**
 * Created by buoto on 5/21/16.
 */
public class APIPagedResponse<T> {
    private int count;
    private String next;
    private String previous;
    private ArrayList<T> results;

    public int getCount() {
        return count;
    }

    public boolean hasNext() {
        return next != null;
    }

    public String getNext() {
        return next;
    }

    public ArrayList<T> getResults() {
        return results;
    }


}
