package inventory;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import enums.InventoryType;
import enums.Texts;

/**
 * This class keeps track of the sections, including their names and
 * inventory types
 * @author Eric Sweeten
 */
public class Sections implements Serializable
{
  /**
   * This is the serializable long for the sake of saving objects of this
   * class to file
   */
  private static final long serialVersionUID = -1970452257268281802L;
  /**
   * The section names - starts out with "First"
   */
  private String[] sections = {Texts.TEXT_FIRST.getText()};
  /**
   * The inventory types per each section - the first is a VIDEO_GAME inventory
   * type by default
   */
  private InventoryType[] inventoryTypeBySection = {InventoryType.VIDEO_GAME};
  /**
   * The inventory items, which are passed in to this class
   */
  private List<ArrayList<InventoryItem>> inventoryItems;
  /**
   * Alphabetizes the sections by name
   */
  public void alphabetize()
  {
    String tempString;
    InventoryType tempIT;
    ArrayList<InventoryItem> tempList = new ArrayList<InventoryItem>();
    for (int i = 0; i < sections.length - 1; i++)
    {
      for (int j = 1; j < sections.length - i; j++)
      {
        if (sections[j - 1].compareToIgnoreCase(sections[j]) > 0)
        {
          tempString = sections[j - 1];          
          sections[j - 1] = sections[j];
          sections[j] = tempString;

          tempIT = inventoryTypeBySection[j - 1];
          inventoryTypeBySection[j - 1] = inventoryTypeBySection[j];
          inventoryTypeBySection[j] = tempIT;
          
          tempList = inventoryItems.get(j - 1);
          /*
           * tempList2 must clone the jth index of ArrayList InventoryItem, otherwise
           * it will always reference the old, which is bad.  You want reference to
           * a new.  The default will cause two pages referencing the same
           * information, and one disconnected, inaccessible page.
           */
          @SuppressWarnings("unchecked")
          ArrayList<InventoryItem> tempList2 = (ArrayList<InventoryItem>)inventoryItems.get(j).clone();
          inventoryItems.set(j - 1, tempList2);
          inventoryItems.set(j, tempList);
        }
      }
    }
  }
  /**
   * Resets the section names array and inventory types array to "First" and VIDEO_GAME, respectively
   */
  public void clear()
  {
    sections = new String[]{Texts.TEXT_FIRST.getText()};
    inventoryTypeBySection = new InventoryType[]{InventoryType.VIDEO_GAME};
  }
  /**
   * Returns the inventory types of the sections, in an array
   * @return InventoryType[] inventoryTypeBySection
   */
  public InventoryType[] getInventoryType()
  {
    return inventoryTypeBySection;
  }
  /**
   * Returns the section names, in an array
   * @return String[] sections 
   */
  public String[] getSectionNames()
  {
    return sections;
  }
  /**
   * Passes in inventoryItems 2xdeep ArrayList from RetroFix's constructor
   * @param inventoryItems RetroFix's inventoryItems
   */
  public void passInInventoryItems(List<ArrayList<InventoryItem>> inventoryItems)
  {
    this.inventoryItems = inventoryItems;
  }
  /**
   * Reinitializes sections to a passed-in size
   * @param newSectionsLength the number of section names
   * @param newITLength the number of section inventory types
   */
  public void reinitialize(final int newSectionsLength, final int newITLength)
  {
    sections = new String[newSectionsLength];
    inventoryTypeBySection = new InventoryType[newITLength];
  }
}
