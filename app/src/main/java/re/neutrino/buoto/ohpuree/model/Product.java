package re.neutrino.buoto.ohpuree.model;

/**
 * Product model used to store product data and deserialize server response entities.
 */
public class Product
{
    private int id;
    private String name;
    private boolean for_vegans;
    private boolean for_vegetarians;

    /**
     * Defines how to display product
     * @return product name
     */
    @Override
    public String toString()
    {
        return name;
    }

    /**
     * Gets name of the product
     * @return name of the product
     */
    public String getName()
    {
        return name;
    }

    /**
     * Defines how to compare products
     * @param o object to compare
     * @return true - if it is the same product or it has the same id; false - otherwise;
     */
    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Product product = (Product) o;

        return id == product.id;

    }

    /**
     * Gets hash code of the product
     * @return hash code - id of the product
     */
    @Override
    public int hashCode()
    {
        return id;
    }

    /**
     * Gets id of the product
     * @return id of the product
     */
    public int getId()
    {
        return id;
    }
}
