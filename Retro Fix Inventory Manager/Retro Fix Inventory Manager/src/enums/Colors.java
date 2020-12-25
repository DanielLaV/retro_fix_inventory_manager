package enums;

import gui.InventoryItemTextField;

import java.awt.Color;

import javax.swing.JRadioButtonMenuItem;

import tools.Settings;

/**
 * This enumeration Colors has everything to do with the color options
 * in Retro Fix Inventory Manager application.  The static methods manipulate
 * GUI object colors in RetroFix through objects passed in by reference.
 * The colors themselves are indexed and the enumeration keeps track of each
 * index, and the total number of indeces (as TOTAL).
 * @author Kevin Powell, Eric Sweeten
 */
public enum Colors
{
  BLACK (0),
  BLUE (1),
  CYAN (2),
  DARK_GRAY (3),
  GRAY (4),
  GREEN (5),
  LIGHT_GRAY (6),
  MAGENTA (7),
  ORANGE (8),
  PINK (9),
  RED (10),
  WHITE (11),
  YELLOW (12);
  /**
   * Refers to the array index where the color is stored
   */
  private int index;
  /**
   * Constructor for enum Colors
   * @param index The index referring to this color
   */
  private Colors(final int index)
  {
    this.index = index;
  }
  /**
   * Gets the integer num
   * @return integer num
   */
  public int getIndex()
  {
    return index;
  }
  /**
   * The total number of colors (and therefore indeces)
   */
  public static final int TOTAL = 13;
  /**
   * Radio buttons for background color, passed in by reference from RetroFix
   */
  private static JRadioButtonMenuItem[] radioButtonMenuItemBackgroundColor;
  /**
   * Radio buttons for text color, passed in by reference from RetroFix
   */
  private static JRadioButtonMenuItem[] radioButtonMenuItemTextColor;
  /**
   * Radio buttons for border color, passed in by reference from RetroFix
   */
  private static JRadioButtonMenuItem[] radioButtonMenuItemBorderColor;
  /**
   * Text fields for Video Games, passed in by reference from RetroFix
   */
  private static InventoryItemTextField[] IITexts;
  /**
   * The background color
   */
  private static Color backgroundColor;
  /**
   * Converts the border colors for video game text fields, then updates the
   * settings and saves the settings file
   * @param color
   */
  private static void convertAllTextFieldBorders(final Color color)
  {
    for (InventoryItemTextField vgtf : IITexts)
    {
      vgtf.convertBorders(color);
    }
    Settings.setBorderColor(color);
    Settings.saveSettingsFile();
  }
  /**
   * Determines what radio button was clicked for border color then calls helper
   * method to change the color for borders
   * @param obj The radio button object that was clicked
   */
  public static void convertAllTextFieldBorders(final Object obj)
  {
    if (obj == radioButtonMenuItemBorderColor[BLACK.getIndex()])
    {
      convertAllTextFieldBorders(Color.BLACK);
    }
    else if (obj == radioButtonMenuItemBorderColor[BLUE.getIndex()])
    {
      convertAllTextFieldBorders(Color.BLUE);
    }
    else if (obj == radioButtonMenuItemBorderColor[CYAN.getIndex()])
    {
      convertAllTextFieldBorders(Color.CYAN);
    }
    else if (obj == radioButtonMenuItemBorderColor[DARK_GRAY.getIndex()])
    {
      convertAllTextFieldBorders(Color.DARK_GRAY);
    }
    else if (obj == radioButtonMenuItemBorderColor[GRAY.getIndex()])
    {
      convertAllTextFieldBorders(Color.GRAY);
    }
    else if (obj == radioButtonMenuItemBorderColor[GREEN.getIndex()])
    {
      convertAllTextFieldBorders(Color.GREEN);
    }
    else if (obj == radioButtonMenuItemBorderColor[LIGHT_GRAY.getIndex()])
    {
      convertAllTextFieldBorders(Color.LIGHT_GRAY);
    }
    else if (obj == radioButtonMenuItemBorderColor[MAGENTA.getIndex()])
    {
      convertAllTextFieldBorders(Color.MAGENTA);
    }
    else if (obj == radioButtonMenuItemBorderColor[ORANGE.getIndex()])
    {
      convertAllTextFieldBorders(Color.ORANGE);
    }
    else if (obj == radioButtonMenuItemBorderColor[PINK.getIndex()])
    {
      convertAllTextFieldBorders(Color.PINK);
    }
    else if (obj == radioButtonMenuItemBorderColor[RED.getIndex()])
    {
      convertAllTextFieldBorders(Color.RED);
    }
    else if (obj == radioButtonMenuItemBorderColor[WHITE.getIndex()])
    {
      convertAllTextFieldBorders(Color.WHITE);
    }
    else if (obj == radioButtonMenuItemBorderColor[YELLOW.getIndex()])
    {
      convertAllTextFieldBorders(Color.YELLOW);
    }
  }
  /**
   * Converts text color in video game text fields, updates text color in
   * settings, and saves settings file
   * @param color The new text color for the text fields
   */
  private static void convertIITextFieldsAndSaveFile(final Color color)
  {
    for (InventoryItemTextField iitf : IITexts)
    {
      iitf.convertTextFields(color);
    }
    Settings.setTextColor(color);
    Settings.saveSettingsFile();
  }
  /**
   * Gets the background color
   * @return The background color
   */
  public static Color getBackgroundColor()
  {
    return backgroundColor;
  }
  /**
   * This retrieves the radio button name based on the index number.
   * If I didn't make this static, then it would have to accessed through
   * one of the 14 enumeration names above, rather than statically
   * (by class).  Making it static reduces redundancy, as it would
   * otherwise be instantiated 14 times, when only one instance of
   * this (the static one) is necessary.
   * @param index The index the color is at
   * @return The name of the radio button
   */
  public static String getRadioButtonName(final int index)
  {
    String str = "";
    switch(index)
    {
    case 0: str = Texts.COLOR_BLACK.getText(); break;
    case 1: str = Texts.COLOR_BLUE.getText(); break;
    case 2: str = Texts.COLOR_CYAN.getText(); break;
    case 3: str = Texts.COLOR_DARK_GRAY.getText(); break;
    case 4: str = Texts.COLOR_GRAY.getText(); break;
    case 5: str = Texts.COLOR_GREEN.getText(); break;
    case 6: str = Texts.COLOR_LIGHT_GRAY.getText(); break;
    case 7: str = Texts.COLOR_MAGENTA.getText(); break;
    case 8: str = Texts.COLOR_ORANGE.getText(); break;
    case 9: str = Texts.COLOR_PINK.getText(); break;
    case 10: str = Texts.COLOR_RED.getText(); break;
    case 11: str = Texts.COLOR_WHITE.getText(); break;
    case 12: str = Texts.COLOR_YELLOW.getText();
    }
    return str;
  }
  /**
   * Passes in some objects from RetroFix by reference
   * @param radioButtonMenuItemBackgroundColor2 radio buttons for background color
   * @param radioButtonMenuItemTextColor2 radio buttons for text color
   * @param radioButtonMenuItemBorderColor2 radio buttons for border color
   * @param VGTexts2 video game text fields
   */
  public static void passInColorablesByReference(
    final JRadioButtonMenuItem[] radioButtonMenuItemBackgroundColor2,
    final JRadioButtonMenuItem[] radioButtonMenuItemTextColor2,
    final JRadioButtonMenuItem[] radioButtonMenuItemBorderColor2,
    final InventoryItemTextField[] IITexts2)
  {
    radioButtonMenuItemBackgroundColor = radioButtonMenuItemBackgroundColor2;
    radioButtonMenuItemTextColor = radioButtonMenuItemTextColor2;
    radioButtonMenuItemBorderColor = radioButtonMenuItemBorderColor2;
    IITexts = IITexts2;
  }
  /**
   * Selects the appropriate background, text, and border colors depending
   * on the what they are in the settings
   */
  public static void selectAppropriateBackgroundTextAndBorderColors()
  {
    if (Settings.getBackgroundColor().equals(Color.BLACK))
    {
      radioButtonMenuItemBackgroundColor[BLACK.getIndex()].doClick();
    }
    else if (Settings.getBackgroundColor().equals(Color.BLUE))
    {
      radioButtonMenuItemBackgroundColor[BLUE.getIndex()].doClick();
    }
    else if (Settings.getBackgroundColor().equals(Color.CYAN))
    {
      radioButtonMenuItemBackgroundColor[CYAN.getIndex()].doClick();
    }
    else if (Settings.getBackgroundColor().equals(Color.DARK_GRAY))
    {
      radioButtonMenuItemBackgroundColor[DARK_GRAY.getIndex()].doClick();
    }
    else if (Settings.getBackgroundColor().equals(Color.GRAY))
    {
      radioButtonMenuItemBackgroundColor[GRAY.getIndex()].doClick();
    }
    else if (Settings.getBackgroundColor().equals(Color.GREEN))
    {
      radioButtonMenuItemBackgroundColor[GREEN.getIndex()].doClick();
    }
    else if (Settings.getBackgroundColor().equals(Color.LIGHT_GRAY))
    {
      radioButtonMenuItemBackgroundColor[LIGHT_GRAY.getIndex()].doClick();
    }
    else if (Settings.getBackgroundColor().equals(Color.MAGENTA))
    {
      radioButtonMenuItemBackgroundColor[MAGENTA.getIndex()].doClick();
    }
    else if (Settings.getBackgroundColor().equals(Color.ORANGE))
    {
      radioButtonMenuItemBackgroundColor[ORANGE.getIndex()].doClick();
    }
    else if (Settings.getBackgroundColor().equals(Color.PINK))
    {
      radioButtonMenuItemBackgroundColor[PINK.getIndex()].doClick();
    }
    else if (Settings.getBackgroundColor().equals(Color.RED))
    {
      radioButtonMenuItemBackgroundColor[RED.getIndex()].doClick();
    }
    else if (Settings.getBackgroundColor().equals(Color.WHITE))
    {
      radioButtonMenuItemBackgroundColor[WHITE.getIndex()].doClick();
    }
    else if (Settings.getBackgroundColor().equals(Color.YELLOW))
    {
      radioButtonMenuItemBackgroundColor[YELLOW.getIndex()].doClick();
    }
    if (Settings.getTextColor().equals(Color.BLACK))
    {
      radioButtonMenuItemTextColor[BLACK.getIndex()].doClick();
    }
    else if (Settings.getTextColor().equals(Color.BLUE))
    {
      radioButtonMenuItemTextColor[BLUE.getIndex()].doClick();
    }
    else if (Settings.getTextColor().equals(Color.CYAN))
    {
      radioButtonMenuItemTextColor[CYAN.getIndex()].doClick();
    }
    else if (Settings.getTextColor().equals(Color.DARK_GRAY))
    {
      radioButtonMenuItemTextColor[DARK_GRAY.getIndex()].doClick();
    }
    else if (Settings.getTextColor().equals(Color.GRAY))
    {
      radioButtonMenuItemTextColor[GRAY.getIndex()].doClick();
    }
    else if (Settings.getTextColor().equals(Color.GREEN))
    {
      radioButtonMenuItemTextColor[GREEN.getIndex()].doClick();
    }
    else if (Settings.getTextColor().equals(Color.LIGHT_GRAY))
    {
      radioButtonMenuItemTextColor[LIGHT_GRAY.getIndex()].doClick();
    }
    else if (Settings.getTextColor().equals(Color.MAGENTA))
    {
      radioButtonMenuItemTextColor[MAGENTA.getIndex()].doClick();
    }
    else if (Settings.getTextColor().equals(Color.ORANGE))
    {
      radioButtonMenuItemTextColor[ORANGE.getIndex()].doClick();
    }
    else if (Settings.getTextColor().equals(Color.PINK))
    {
      radioButtonMenuItemTextColor[PINK.getIndex()].doClick();
    }
    else if (Settings.getTextColor().equals(Color.RED))
    {
      radioButtonMenuItemTextColor[RED.getIndex()].doClick();
    }
    else if (Settings.getTextColor().equals(Color.WHITE))
    {
      radioButtonMenuItemTextColor[WHITE.getIndex()].doClick();
    }
    else if (Settings.getTextColor().equals(Color.YELLOW))
    {
      radioButtonMenuItemTextColor[YELLOW.getIndex()].doClick();
    }
    if (Settings.getBorderColor().equals(Color.BLACK))
    {
      radioButtonMenuItemBorderColor[BLACK.getIndex()].doClick();
    }
    else if (Settings.getBorderColor().equals(Color.BLUE))
    {
      radioButtonMenuItemBorderColor[BLUE.getIndex()].doClick();
    }
    else if (Settings.getBorderColor().equals(Color.CYAN))
    {
      radioButtonMenuItemBorderColor[CYAN.getIndex()].doClick();
    }
    else if (Settings.getBorderColor().equals(Color.DARK_GRAY))
    {
      radioButtonMenuItemBorderColor[DARK_GRAY.getIndex()].doClick();
    }
    else if (Settings.getBorderColor().equals(Color.GRAY))
    {
      radioButtonMenuItemBorderColor[GRAY.getIndex()].doClick();
    }
    else if (Settings.getBorderColor().equals(Color.GREEN))
    {
      radioButtonMenuItemBorderColor[GREEN.getIndex()].doClick();
    }
    else if (Settings.getBorderColor().equals(Color.LIGHT_GRAY))
    {
      radioButtonMenuItemBorderColor[LIGHT_GRAY.getIndex()].doClick();
    }
    else if (Settings.getBorderColor().equals(Color.MAGENTA))
    {
      radioButtonMenuItemBorderColor[MAGENTA.getIndex()].doClick();
    }
    else if (Settings.getBorderColor().equals(Color.ORANGE))
    {
      radioButtonMenuItemBorderColor[ORANGE.getIndex()].doClick();
    }
    else if (Settings.getBorderColor().equals(Color.PINK))
    {
      radioButtonMenuItemBorderColor[PINK.getIndex()].doClick();
    }
    else if (Settings.getBorderColor().equals(Color.RED))
    {
      radioButtonMenuItemBorderColor[RED.getIndex()].doClick();
    }
    else if (Settings.getBorderColor().equals(Color.WHITE))
    {
      radioButtonMenuItemBorderColor[WHITE.getIndex()].doClick();
    }
    else if (Settings.getBorderColor().equals(Color.YELLOW))
    {
      radioButtonMenuItemBorderColor[YELLOW.getIndex()].doClick();
    }
  }
  /**
   * Sets the background color in the settings file and saves the
   * settings file
   * @param color The background color to be saved in settings
   */
  private static void setBackgroundAndRepaint(final Color color)
  {
    Settings.setBackgroundColor(color);
    Settings.saveSettingsFile();
  }
  /**
   * Determines what radio button was clicked for background color, then
   * adjust the background color after updating the settings file
   * @param obj The radio button that was clicked
   */
  public static void setBackgroundColorSaveFileAndRepaint(final Object obj)
  {
    if (obj == radioButtonMenuItemBackgroundColor[BLACK.getIndex()])
    {
      setBackgroundAndRepaint(Color.BLACK);
      backgroundColor = Color.BLACK;
    }
    else if (obj == radioButtonMenuItemBackgroundColor[BLUE.getIndex()])
    {
      setBackgroundAndRepaint(Color.BLUE);
      backgroundColor = Color.BLUE;
    }
    else if (obj == radioButtonMenuItemBackgroundColor[CYAN.getIndex()])
    {
      setBackgroundAndRepaint(Color.CYAN);
      backgroundColor = Color.CYAN;
    }
    else if (obj == radioButtonMenuItemBackgroundColor[DARK_GRAY.getIndex()])
    {
      setBackgroundAndRepaint(Color.DARK_GRAY);
      backgroundColor = Color.DARK_GRAY;
    }
    else if (obj == radioButtonMenuItemBackgroundColor[GRAY.getIndex()])
    {
      setBackgroundAndRepaint(Color.GRAY);
      backgroundColor = Color.GRAY;
    }
    else if (obj == radioButtonMenuItemBackgroundColor[GREEN.getIndex()])
    {
      setBackgroundAndRepaint(Color.GREEN);
      backgroundColor = Color.GREEN;
    }
    else if (obj == radioButtonMenuItemBackgroundColor[LIGHT_GRAY.getIndex()])
    {
      setBackgroundAndRepaint(Color.LIGHT_GRAY);
      backgroundColor = Color.LIGHT_GRAY;
    }
    else if (obj == radioButtonMenuItemBackgroundColor[MAGENTA.getIndex()])
    {
      setBackgroundAndRepaint(Color.MAGENTA);
      backgroundColor = Color.MAGENTA;
    }
    else if (obj == radioButtonMenuItemBackgroundColor[ORANGE.getIndex()])
    {
      setBackgroundAndRepaint(Color.ORANGE);
      backgroundColor = Color.ORANGE;
    }
    else if (obj == radioButtonMenuItemBackgroundColor[PINK.getIndex()])
    {
      setBackgroundAndRepaint(Color.PINK);
      backgroundColor = Color.PINK;
    }
    else if (obj == radioButtonMenuItemBackgroundColor[RED.getIndex()])
    {
      setBackgroundAndRepaint(Color.RED);
      backgroundColor = Color.RED;
    }
    else if (obj == radioButtonMenuItemBackgroundColor[WHITE.getIndex()])
    {
      setBackgroundAndRepaint(Color.WHITE);
      backgroundColor = Color.WHITE;
    }
    else if (obj == radioButtonMenuItemBackgroundColor[YELLOW.getIndex()])
    {
      setBackgroundAndRepaint(Color.YELLOW);
      backgroundColor = Color.YELLOW;
    }
  }
  /**
   * Sets the text field colors depending on the radio button that was clicked
   * @param obj The radio button in text field colors that was clicked
   * @return The color that was clicked on is being returned as a Color object
   */
  public static Color setTextFieldColors(final Object obj)
  {
    Color color = null;
    if (obj == radioButtonMenuItemTextColor[BLACK.getIndex()])
    {
      convertIITextFieldsAndSaveFile(Color.BLACK);
      color = Color.BLACK;
    }
    else if (obj == radioButtonMenuItemTextColor[BLUE.getIndex()])
    {
      convertIITextFieldsAndSaveFile(Color.BLUE);
      color = Color.BLUE;
    }
    else if (obj == radioButtonMenuItemTextColor[CYAN.getIndex()])
    {
      convertIITextFieldsAndSaveFile(Color.CYAN);
      color = Color.CYAN;
    }
    else if (obj == radioButtonMenuItemTextColor[DARK_GRAY.getIndex()])
    {
      convertIITextFieldsAndSaveFile(Color.DARK_GRAY);
      color = Color.DARK_GRAY;
    }
    else if (obj == radioButtonMenuItemTextColor[GRAY.getIndex()])
    {
      convertIITextFieldsAndSaveFile(Color.GRAY);
      color = Color.GRAY;
    }
    else if (obj == radioButtonMenuItemTextColor[GREEN.getIndex()])
    {
      convertIITextFieldsAndSaveFile(Color.GREEN);
      color = Color.GREEN;
    }
    else if (obj == radioButtonMenuItemTextColor[LIGHT_GRAY.getIndex()])
    {
      convertIITextFieldsAndSaveFile(Color.LIGHT_GRAY);
      color = Color.LIGHT_GRAY;
    }
    else if (obj == radioButtonMenuItemTextColor[MAGENTA.getIndex()])
    {
      convertIITextFieldsAndSaveFile(Color.MAGENTA);
      color = Color.MAGENTA;
    }
    else if (obj == radioButtonMenuItemTextColor[ORANGE.getIndex()])
    {
      convertIITextFieldsAndSaveFile(Color.ORANGE);
      color = Color.ORANGE;
    }
    else if (obj == radioButtonMenuItemTextColor[PINK.getIndex()])
    {
      convertIITextFieldsAndSaveFile(Color.PINK);
      color = Color.PINK;
    }
    else if (obj == radioButtonMenuItemTextColor[RED.getIndex()])
    {
      convertIITextFieldsAndSaveFile(Color.RED);
      color = Color.RED;
    }
    else if (obj == radioButtonMenuItemTextColor[WHITE.getIndex()])
    {
      convertIITextFieldsAndSaveFile(Color.WHITE);
      color = Color.WHITE;
    }
    else if (obj == radioButtonMenuItemTextColor[YELLOW.getIndex()])
    {
      convertIITextFieldsAndSaveFile(Color.YELLOW);
      color = Color.YELLOW;
    }
    return color;
  }
}
