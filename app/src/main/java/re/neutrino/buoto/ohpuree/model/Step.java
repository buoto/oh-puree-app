package re.neutrino.buoto.ohpuree.model;

/**
 * Created by buoto on 5/20/16.
 */
public class Step {
    private String text;
    private int ordinal;

    @Override
    public String toString() {
        return String.format("%d. %s\n", ordinal, text);
    }
}
