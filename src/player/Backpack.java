package player;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

/**
 * Represents the backpack of a character
 *
 * @author Stephen Miller (smiller@providertrust.com)
 * @since 9/2/22
 */
public class Backpack
{
    private final List<Item> items;

    public Backpack()
    {
        items = new ArrayList<>();
    }

    public void addItem(Item item)
    {
        items.add(item);
    }

    public Item removeItem(ItemType type)
    {
        Item item = items.stream()
                .filter(it -> it.type == type)
                .findFirst()
                .orElse(null);

        items.remove(item);

        return item;
    }

    public boolean contains(ItemType type)
    {
        return items.stream().anyMatch(it -> it.type == type);
    }
}
