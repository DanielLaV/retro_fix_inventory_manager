package enums;

/**
 * This enum is to keep track of the Inventory Type.  Since it's for a video game business,
 * the primary inventory is going to be video games.  There is a way to put all other kinds
 * of inventory as well, which is what the other option is for.
 * @author Eric Sweeten
 */
public enum InventoryType
{
  /**
   * All non-video game items or all items the user wishes to put that only has the
   * attributes Name, Condition, Notes, and Value
   */
  GENERIC_ITEM,
  /**
   * The main inventory type this application deals with, or is intended to deal with anyway
   * (though it's built to deal with all types).  VIDEO_GAME is specifically for video games,
   * or any inventory item the user wishes to have the values of Name, Loose Price, Complete
   * Price, New Price, Box, Manual, Condition, Notes, and Value.
   */
  VIDEO_GAME;
}
