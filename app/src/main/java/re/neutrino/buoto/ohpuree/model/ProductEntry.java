package re.neutrino.buoto.ohpuree.model;

/**
 * RecipeEntry model used to store recipe entry data (product and amount with unit) and deserialize server response entities.
 */
public class ProductEntry
{
    private Product product;
    private String amount;
    private String unit;

    /**
     * Gets the product name
     * @return product
     */
    public Product getProduct()
    {
        return product;
    }

    /**
     * Defines how to display product information
     * @return product with its dose
     */
    @Override
    public String toString()
    {
        return String.format("%s %s %s\n", amount, unit, product);
    }
}
