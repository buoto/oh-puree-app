package re.neutrino.buoto.ohpuree.model;

/**
 * Created by buoto on 5/20/16.
 */
public class ProductEntry {
    private Product product;
    private String amount;
    private String unit;

    public Product getProduct() {
        return product;
    }

    @Override
    public String toString() {
        return String.format("%s %s %s\n", amount, unit, product);
    }
}
