package enums;

import gui.InventoryItemTextField;
import inventory.InventoryItem;
import inventory.VideoGame;

import java.text.NumberFormat;

import tools.Settings;

/**
 * Enumeration class for the attributes that represent a monetary value, including complete
 * price, loose price, new price, and value.  Also has a few static helper methods dealing
 * with money and numerical values.
 * @author Eric Sweeten
 */
public enum Money
{
  /**
   * "Complete Price", which is supposed to be a monetary value
   */
  COMPLETE,
  /**
   * "Loose Price", which is supposed to be a monetary value
   */
  LOOSE,
  /**
   * "New Price", which is supposed to be a monetary value
   */
  NEW,
  /**
   * "Value", which is supposed to be a monetary value
   */
  VALUE;
  /**
   * The number formatter, which gets the currency version of the double passed in.  It's
   * used multiple times and so it's declared once here as a static variable.
   */
  private static NumberFormat formatter = NumberFormat.getCurrencyInstance();
  /**
   * Checks the validity of the numbers.  If it is not a valid number and the option to
   * show the user invalid numbers is selected, then the opacity for that text field is
   * set to true.  Otherwise it is set to false.
   * @param IITexts The complete set of Inventory Item Text Fields the application uses,
   * which is about 30 or so, depending on the value of
   * InventoryItemTextField.NUMBER_OF_II_TEXT_FIELDS
   */
  public static void checkValiditiyOfNumbers(final InventoryItemTextField[] IITexts)
  {
    for (int i = 0; i < IITexts.length; i++)
    {
      try
      {
        if (Double.isNaN(Double.parseDouble(IITexts[i].getTextFieldLoosePrice().getText())))
        {
          IITexts[i].getTextFieldLoosePrice().setOpaque(true);
        }
        else
        {
          IITexts[i].getTextFieldLoosePrice().setOpaque(false);;
        }
      }
      catch (final NumberFormatException nfe)
      {
        if (!IITexts[i].getTextFieldLoosePrice().getText().isEmpty()) IITexts[i].getTextFieldLoosePrice().setOpaque(true);    
      }
      try
      {
        if (Double.isNaN(Double.parseDouble(IITexts[i].getTextFieldCompletePrice().getText())))
        {
          IITexts[i].getTextFieldCompletePrice().setOpaque(true);
        }
        else
        {
          IITexts[i].getTextFieldCompletePrice().setOpaque(false);;
        }
      }
      catch (final NumberFormatException nfe)
      {
        if (!IITexts[i].getTextFieldCompletePrice().getText().isEmpty()) IITexts[i].getTextFieldCompletePrice().setOpaque(true);    
      }
      try
      {
        if (Double.isNaN(Double.parseDouble(IITexts[i].getTextFieldNewPrice().getText())))
        {
          IITexts[i].getTextFieldNewPrice().setOpaque(true);
        }
        else
        {
          IITexts[i].getTextFieldNewPrice().setOpaque(false);
        }
      }
      catch (final NumberFormatException nfe)
      {
        if (!IITexts[i].getTextFieldNewPrice().getText().isEmpty()) IITexts[i].getTextFieldNewPrice().setOpaque(true);
      }
      try
      {
        if (Double.isNaN(Double.parseDouble(IITexts[i].getTextFieldValue().getText())))
        {
          IITexts[i].getTextFieldValue().setOpaque(true);
        }
        else
        {
          IITexts[i].getTextFieldValue().setOpaque(false);
        }
      }
      catch (final NumberFormatException nfe)
      {
        if (!IITexts[i].getTextFieldValue().getText().isEmpty()) IITexts[i].getTextFieldValue().setOpaque(true);
      }
      if (!Settings.shouldShowInvalidNumbers())
      {
        IITexts[i].getTextFieldLoosePrice().setOpaque(false);;
        IITexts[i].getTextFieldCompletePrice().setOpaque(false);;
        IITexts[i].getTextFieldNewPrice().setOpaque(false);
        IITexts[i].getTextFieldValue().setOpaque(false);
      }
    }
  }
  /**
   * Gets the String currency version of the passed-in double
   * @param number The passed-in double
   * @return The currency version of the passed-in double, which is basically the double
   * rounded to two decimal points and a "$" put before it.
   */
  public static String getCurrency(final double number)
  {
    return formatter.format(number);
  }
  /**
   * Gets the double version of the string, depending on what is requested
   * @param II The Inventory Item which has a String value that will be parsed to a double
   * @param money The String value of InventoryItem, equivalent to the enum Money, which
   * will be parsed
   * @return The parsed double from the String value
   */
  public static double getDouble(final InventoryItem II, final Money money)
  {
    double num = 0;
    String str = "";
    if (II.getInventoryType() == InventoryType.VIDEO_GAME)
    {
      if (money == LOOSE)
      {
        str = ((VideoGame)II).getLoosePrice().toString();
      }
      else if (money == COMPLETE)
      {
        str = ((VideoGame)II).getCompletePrice().toString();
      }
      else if (money == NEW)
      {
        str = ((VideoGame)II).getNewPrice().toString();
      }
    }
    if (money == VALUE)
    {
      str = II.getValue().toString();
    }
    try
    {
      num = Double.parseDouble(str);
    }
    catch (final NumberFormatException e)
    { 
      //do nothing, except continue
    }
    return num;
  }
}
