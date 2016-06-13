package re.neutrino.buoto.ohpuree.model;

/**
 * RecipeEntry model used to store recipe entry data (product and amount with unit) and deserialize server response entities.
 *
 */
public class ProductEntry
{
    private Product product;
    private String amount;
    private String unit;

    public Product getProduct()
    {
        return product;
    }

    @Override
    public String toString()
    {
        return String.format("%s %s %s\n", amount, unit, product);
    }
}
