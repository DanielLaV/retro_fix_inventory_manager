package inventory;

import enums.InventoryType;

/**
 * The abstract class for inventory items that Item (for generic items) and VideoGame extends
 * @author Eric Sweeten, Daniel LaVergne
 */
public abstract class AbstractInventoryItem implements InventoryItem
{
  /**
   * The serializable long ID for the sake of saving objects of this class to file
   */
  private static final long serialVersionUID = -1971293550504853584L;
  /**
   * The name of the item
   */
  private String name;
  /**
   * The condition of the item
   */
  private String condition;
  /**
   * The notes for the item
   */
  private String notes;
  /**
   * The value of the item
   */
  private String value;
  /**
   * The type of inventory the item is
   */
  protected InventoryType inventoryType;
  /**
   * Constructor for AbstractInventoryItem, which does nothing
   */
  public AbstractInventoryItem()
  {
  }
  /**
   * Gets the item's condition
   * @return String condition
   */
  public String getCondition()
  {
    return condition;
  }
  /**
   * Gets the item's inventory type
   * @return inventoryType
   */
  public InventoryType getInventoryType()
  {
    return inventoryType;
  }
  /**
   * Gets the item's name
   * @return String name
   */
  public String getName()
  {
    return name;
  }
  /**
   * Gets the item's notes
   * @return String notes
   */
  public String getNotes()
  {
    return notes;
  }
  /**
   * Gets the item's value
   * @return String value
   */
  public String getValue()
  {
    return value;
  }
  /**
   * Sets the item's condition
   * @param condition the item's condition
   */
  public void setCondition(final String condition)
  {
    this.condition = condition;
  }
  /**
   * Sets the item's inventory type
   * @param type the item's inventory type
   */
  public void setInventoryType(final InventoryType type)
  {
    inventoryType = type;
  }
  /**
   * Sets the item's name
   * @param name the item's name
   */
  public void setName(final String name)
  {
    this.name = name;
  }
  /**
   * Sets the item's notes
   * @param notes the item's notes
   */
  public void setNotes(final String notes)
  {
    this.notes = notes;
  }
  /**
   * Sets the item's value
   * @param value the item's value
   */
  public void setValue(final String value)
  {
    this.value = value;
  }
}
