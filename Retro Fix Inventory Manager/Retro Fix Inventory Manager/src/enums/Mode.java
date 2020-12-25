package enums;

import javax.swing.JOptionPane;

/**
 * Keeps track of the mode the user is currently in.  If the mode weren't being kept
 * track of, some functions would happen when they're not supposed to.  This ensures
 * the proper code executes during each respective mode, and skips over the code
 * reserved for other modes.  One instance of this enumeration is made in RetroFix,
 * called currentMode.
 * @author Eric Sweeten
 */
public enum Mode
{
  /**
   * Add item mode
   */
  ADD,
  /**
   * Add section mode
   */
  ADD_SECTION,
  /**
   * Delete item mode
   */
  DELETE,
  /**
   * Delete section mode
   */
  DELETE_SECTION,
  /**
   * Duplicate item mode
   */
  DUPLICATE,
  /**
   * Flip the page mode
   */
  FLIP_PAGE,
  /**
   * Modify item mode
   */
  MODIFY,
  /**
   * Modify section mode
   */
  MODIFY_SECTION;
  /**
   * Checks the String passed-in with the passed-in array of Strings for a duplicate name
   * or if the name is blank
   * @param s The passed-in String to compare to the array of Strings
   * @param sectionNames The passed-in array of Strings
   * @return true if it's a blank String or a duplicate name; false if it's available
   */
  public static boolean checkIfDuplicateOrBlankName(final String s, final String[] sectionNames)
  {
    boolean skip = false;
    if (s.isEmpty())
    {
      JOptionPane.showMessageDialog(null, Texts.ERROR_BLANK_PAGE_NAME.getText());
      skip = true;
    }
    else
    {
      for (String str : sectionNames)
      {
        if (s.equals(str))
        {
          JOptionPane.showMessageDialog(null, Texts.ERROR_DUPLICATE_PAGE.getText());
          skip = true;
          break;
        }
      }
    }
    return skip;
  }
}
