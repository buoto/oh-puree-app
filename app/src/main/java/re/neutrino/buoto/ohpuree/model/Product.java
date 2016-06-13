package re.neutrino.buoto.ohpuree.model;

/**
 * Product model used to store product data and deserialize server response entities.
 *
 */
public class Product
{
    private int id;
    private String name;
    private boolean for_vegans;
    private boolean for_vegetarians;

    @Override
    public String toString()
    {
        return name;
    }

    public String getName()
    {
        return name;
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Product product = (Product) o;

        return id == product.id;

    }

    @Override
    public int hashCode()
    {
        return id;
    }

    public int getId()
    {
        return id;
    }
}
