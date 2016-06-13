package re.neutrino.buoto.ohpuree.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Recipe model used to store recipe data and deserialize server response.
 */
public class Recipe
{
    private int id;
    private String name;
    private String picture;

    @SerializedName("entries")
    private ArrayList<ProductEntry> products;

    private ArrayList<Step> steps;

    private boolean for_vegans;
    private boolean for_vegetarians;

    @SerializedName("author")
    private int authorID;
    private int ingredients;

    /**
     * Defines how to display recipe information
     * @return recipe information
     */
    @Override
    public String toString()
    {
        return "Recipe{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", picture='" + picture + '\'' +
                ", products=" + products +
                ", steps=" + steps +
                ", for_vegans=" + for_vegans +
                ", for_vegetarians=" + for_vegetarians +
                ", authorID=" + authorID +
                '}';
    }

    /**
     * Gets the picture of the recipe
     * @return picture
     */
    public String getPicture()
    {
        return picture;
    }

    /**
     * Gets the name of the recipe
     * @return name
     */
    public String getName()
    {
        return name;
    }

    /**
     * Converts recipe steps into one string
     * @return recipe steps to print
     */
    public String printSteps()
    {
        StringBuilder sb = new StringBuilder();
        for (Step step : steps)
        {
            sb.append(step);
        }
        return sb.toString();
    }

    /**
     * Gets products of the recipe
     * @return list of products
     */
    public ArrayList<ProductEntry> getProducts()
    {
        return products;
    }

    /**
     * Checks out is it vegetarian recipe
     * @return true - if the recipe is for vegetarian; false - otherwise;
     */
    public boolean isVegetarian()
    {
        return for_vegetarians;
    }

    /**
     * Checks out is it vegan recipe
     * @return true - if the recipe is for vegan; false - otherwise;
     */
    public boolean isVegan()
    {
        return for_vegans;
    }

    /**
     * Converts products into one string
     * @return products to print
     */
    public String printIngredients()
    {
        StringBuilder sb = new StringBuilder();
        for (ProductEntry e : products)
        {
            sb.append(e);
        }
        return sb.toString();
    }
}
