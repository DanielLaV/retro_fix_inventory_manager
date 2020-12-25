package inventory;

import enums.InventoryType;

/**
 * The class for video games, which extends the abstract inventory item class
 * @author Eric Sweeten
 */
public class VideoGame extends AbstractInventoryItem
{
  /**
   * The serial number for the sake of save objects of this class
   */
  private static final long serialVersionUID = -4759987082293294100L;
  /**
   * The loose price String
   */
  private String loosePrice;
  /**
   * The complete price String
   */
  private String completePrice;
  /**
   * The new price String
   */
  private String newPrice;
  /**
   * The box String
   */
  private String box;
  /**
   * The manual String
   */
  private String manual;
  /**
   * The VideoGame constructor, which sets InventoryType to VIDEO_GAME
   */
  public VideoGame()
  {
    inventoryType = InventoryType.VIDEO_GAME;
  }
  /**
   * Gets the String box
   * @return String box
   */
  public String getBox()
  {
    return box;
  }
  /**
   * Gets the String completePrice
   * @return String completePrice
   */
  public String getCompletePrice()
  {
    return completePrice;
  }
  /**
   * Gets the String loosePrice
   * @return String loosePrice
   */
  public String getLoosePrice()
  {
    return loosePrice;
  }
  /**
   * Gets the String manual
   * @return String manual
   */
  public String getManual()
  {
    return manual;
  }
  /**
   * Gets the String newPrice
   * @return String newPrice
   */
  public String getNewPrice()
  {
    return newPrice;
  }
  /**
   * Sets the String box
   * @param box String box
   */
  public void setBox(final String box)
  {
    this.box = box;
  }
  /**
   * Sets the String completePrice
   * @param completePrice String completePrice
   */
  public void setCompletePrice(final String completePrice)
  {
    this.completePrice = completePrice;
  }
  /**
   * Sets the String completePrice
   * @param loosePrice String completePrice
   */
  public void setLoosePrice(final String loosePrice)
  {
    this.loosePrice = loosePrice;
  }
  /**
   * Sets the String manual
   * @param manual String manual
   */
  public void setManual(final String manual)
  {
    this.manual = manual;
  }
  /**
   * Sets the String newPrice
   * @param newPrice String newPrice
   */
  public void setNewPrice(final String newPrice)
  {
    this.newPrice = newPrice;
  }
}
