package player;

/**
 * Represents an item in the game
 *
 * @author Stephen Miller (smiller@providertrust.com)
 * @since 9/2/22
 */
public class Item
{
    public final ItemType type;
    private String description;

    public Item(ItemType type)
    {
        this(type, "It's hard to tell what this item is");
    }

    public Item(ItemType type, String description)
    {
        this.type = type;
        this.description = description;
    }


    /**
     * Gets Description
     *
     * @return Description
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets description
     *
     * @param description description
     */
    public void setDescription(String description) {
        this.description = description;
    }
}
