package re.neutrino.buoto.ohpuree.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by buoto on 5/20/16.
 */
public class Recipe {
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

    @Override
    public String toString() {
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

    public String getPicture() {
        return picture;
    }

    public String getName() {
        return name;
    }
}
