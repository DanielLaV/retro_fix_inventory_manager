package inventory;

import java.io.Serializable;

import enums.InventoryType;

/**
 * The interface which AbstractInventoryItem implements.  It has the same Java-doc as the methods in
 * AbstractInventoryItem
 * @author Daniel LaVergne, Eric Sweeten
 */
public interface InventoryItem extends Serializable
{
  /**
   * Gets the item's condition
   * @return String condition
   */
  String getCondition();
  /**
   * Gets the item's inventory type
   * @return inventoryType
   */
  InventoryType getInventoryType();
  /**
   * Gets the item's name
   * @return String name
   */
  String getName();
  /**
   * Gets the item's notes
   * @return String notes
   */
  String getNotes();
  /**
   * Gets the item's value
   * @return String value
   */
  String getValue();
  /**
   * Sets the item's condition
   * @param condition the item's condition
   */
  void setCondition(final String condition);
  /**
   * Sets the item's inventory type
   * @param type the item's inventory type
   */
  void setInventoryType (final InventoryType type);
  /**
   * Sets the item's name
   * @param name the item's name
   */
  void setName(final String name);
  /**
   * Sets the item's notes
   * @param notes the item's notes
   */
  void setNotes(final String notes);
  /**
   * Sets the item's value
   * @param value the item's value
   */
  void setValue(final String value);
}
