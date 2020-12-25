package inventory;

import enums.InventoryType;

/**
 * There are two kinds of AbstractInventoryItem classes - VideoGame and Item.  This is Item, which is simpler
 * than VideoGame
 * @author Daniel LaVergne, Eric Sweeten
 *
 */
public class Item extends AbstractInventoryItem
{
  /**
   * For the sake of saving instances of this class 
   */
  private static final long serialVersionUID = -5574293431908911444L;
/**
 * Generic Items are for items that are not video games
 */
  public Item()
  {
    inventoryType = InventoryType.GENERIC_ITEM;
  }
}
