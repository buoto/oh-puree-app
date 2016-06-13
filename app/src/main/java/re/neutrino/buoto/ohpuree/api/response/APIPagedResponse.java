package re.neutrino.buoto.ohpuree.api.response;

import java.util.ArrayList;

/**
 * Structure used to deserialize paged server responses.
 */
class APIPagedResponse<T>
{
    private int count;
    private String next;
    private String previous;
    private ArrayList<T> results;

    /**
     * Gets number of results' pages
     * @return number of pages of the results
     */
    public int getCount()
    {
        return count;
    }

    /**
     * Checks out is there the next results' page
     * @return true - if there is the next result; false - otherwise;
     */
    public boolean hasNext()
    {
        return next != null;
    }

    /**
     * Gets next results' page
     * @return next page of the results
     */
    public String getNext()
    {
        return next;
    }

    /**
     * Gets results of any response
     * @return results of the response
     */
    public ArrayList<T> getResults()
    {
        return results;
    }


}
