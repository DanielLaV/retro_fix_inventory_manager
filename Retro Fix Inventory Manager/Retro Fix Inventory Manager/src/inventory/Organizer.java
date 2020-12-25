package inventory;

/**
 * This class keeps track of which way the next ordering should go when one of the attribute's
 * labels are clicked in RetroFix's database panel.  Every other click de-alphabetizes, and all
 * the other clicks alphabetize.  Or for numerical values - every other clicks de-orders
 * (reverse) while all other clicks order in ascending order.
 * @author Eric Bergren, Eric Sweeten
 */
public class Organizer
{
  /**
   * Which way the next order of name is supposed to go
   */
  private boolean name;
  /**
   * Which way the next order of loose price is supposed to go
   */
  private boolean loosePrice;
  /**
   * Which way the next order of complete price is supposed to go
   */
  private boolean completePrice;
  /**
   * Which way the next order of new price is supposed to go
   */
  private boolean newPrice;
  /**
   * Which way the next order of box is supposed to go
   */
  private boolean box;
  /**
   * Which way the next order of box iw supposed to go
   */
  private boolean manual;
  /**
   * Which way the next order of condition is supposed to go
   */
  private boolean condition;
  /**
   * Which way the next order of notes is supposed to go
   */
  private boolean notes;
  /**
   * Which way the next order of value it supposed to go
   */
  private boolean value;
  /**
   * Constructor for Organizer.  Everything is set to ture, which is meant to set every next inquiry to alphabetical.
   */
  public Organizer()
  {
    name = loosePrice = completePrice = newPrice = box = manual = condition = notes = value = true;
  }
  /**
   * Gets the way box is supposed to go (true = alphabetical)
   * @return boolean box
   */
  public boolean getBox()
  {
    return box;
  }
  /**
   * Gets the way complete price is supposed to go (true = numerical)
   * @return boolean completePrice
   */ 
  public boolean getCompletePrice() 
  {
    return completePrice;
  }
  /**
   * Gets the way box is condition to go (true = alphabetical)
   * @return boolean condition
   */
  public boolean getCondition()
  {
    return condition;
  }
  /**
   * Gets the way loose price is supposed to go (true = numerical)
   * @return boolean loosePrice
   */
  public boolean getLoosePrice()
  {
    return loosePrice;
  }
  /**
   * Gets the way manual is supposed to go (true = alphabetical)
   * @return boolean manual
   */
  public boolean getManual()
  {
    return manual;
  }
  /**
   * Gets the way name is supposed to go (true = alphabetical)
   * @return boolean name
   */
  public boolean getName()
  {
    return name;
  }
  /**
   * Gets the way new price is supposed to go (true = numerical)
   * @return boolean newPrice
   */
  public boolean getNewPrice()
  {
    return newPrice;
  }
  /**
   * Gets the way notes is supposed to go (true = alphabetical)
   * @return boolean notes
   */
  public boolean getNotes()
  {
    return notes;
  }
  /**
   * Gets the way value is supposed to go (true = alphabetical)
   * @return boolean value
   */
  public boolean getValue()
  {
    return value;
  }
  /**
   * toggles box boolean
   */
  public void toggleBox()
  {
    box = !box;
  }
  /**
   * toggles completePrice boolean
   */
  public void toggleCompletePrice()
  {
    completePrice = !completePrice;
  }
  /**
   * toggles condtion boolean
   */
  public void toggleCondition()
  {
    condition = !condition;
  }
  /**
   * toggles loose price boolean
   */
  public void toggleLoosePrice()
  {
    loosePrice = !loosePrice;
  }
  /**
   * toggles manual boolean
   */
  public void toggleManual()
  {
    manual = !manual;
  }
  /**
   * toggles name boolean
   */
  public void toggleName()
  {
    name = !name;
  }
  /**
   * toggles new price boolean
   */
  public void toggleNewPrice()
  {
    newPrice = !newPrice;
  }
  /**
   * toggles newPrice boolean
   */
  public void toggleNotes()
  {
    notes = !notes;
  }
  /**
   * toggles newPrice boolean
   */
  public void toggleValue()
  {
    value = !value;
  }
}

