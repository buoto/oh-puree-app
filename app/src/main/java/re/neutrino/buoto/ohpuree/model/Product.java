package re.neutrino.buoto.ohpuree.model;

/**
 * Created by buoto on 5/20/16.
 */
public class Product {
    private int id;
    private String name;
    private boolean for_vegans;
    private boolean for_vegetarians;

    @Override
    public String toString() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Product product = (Product) o;

        return id == product.id;

    }

    @Override
    public int hashCode() {
        return id;
    }
}
