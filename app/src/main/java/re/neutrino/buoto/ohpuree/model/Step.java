package re.neutrino.buoto.ohpuree.model;

/**
 * Step model used to store step data and deserialize server response entities.
 * Step stores text and ordinal about action in food making process.
 */
public class Step
{
    private String text;
    private int ordinal;

    /**
     * Defines how to display steps
     * @return step with its ordinal
     */
    @Override
    public String toString()
    {
        return String.format("%d. %s\n", ordinal, text);
    }
}
