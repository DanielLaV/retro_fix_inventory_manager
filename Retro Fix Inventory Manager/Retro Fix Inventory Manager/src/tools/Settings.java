package tools;

import java.awt.Color;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import enums.Texts;

/**
 * This class keeps track of the universal settings that can be changed by any user.
 * Every time any of these settings are changed, the file is re-saved.
 * @author Eric Sweeten
 */
public class Settings
{
  /**
   * Default tab size for "Overall" reports
   */
  public static final int DEFAULT_TAB_SIZE_OVERALL = 10;
  /**
   * Default tab size for "Section" reports
   */
  public static final int DEFAULT_TAB_SIZE_SECTION = 15;
  /**
   * Background color
   */
  private static Color backgroundColor;
  /**
   * Text color
   */
  private static Color textColor;
  /**
   * Border color
   */
  private static Color borderColor;
  /**
   * Keeps track of whether or not the background image should be shown
   */
  private static boolean shouldLoadImage;
  /**
   * Keeps track of whether or not invalid numbers should be shown
   */
  private static boolean shouldShowInvalidNumbers;
  /**
   * The background image file
   */
  private static File imageFile;
  /**
   * The tab size for "Overall" reports
   */
  private static int tabSizeOverall;
  /**
   * The tab size for "Section" reports
   */
  private static int tabSizeSection;
  /**
   * The Settings.dat file
   */
  private static final File file = new File(Texts.FILE_SETTINGS.getText());
  /**
   * Sets all the settings to their defaults
   */
  private static void defaultSettings()
  {
    backgroundColor = Color.BLACK;
    textColor = Color.WHITE;
    borderColor = Color.WHITE;
    shouldLoadImage = false;
    shouldShowInvalidNumbers = true;
    tabSizeOverall = DEFAULT_TAB_SIZE_OVERALL;
    tabSizeSection = DEFAULT_TAB_SIZE_SECTION;
    imageFile = new File("");
    saveSettingsFile();
  }
  /**
   * Gets the background color
   * @return Color backgroundColor
   */
  public static Color getBackgroundColor()
  {
    return backgroundColor;
  }
  /**
   * Gets the border color
   * @return Color borderColor
   */
  public static Color getBorderColor()
  {
    return borderColor;
  }
  /**
   * Grets the background image file
   * @return File imageFile
   */
  public static File getImageFile()
  {
    return imageFile;
  }
  /**
   * Gets the "Overall" report tab size
   * @return int tabSizeOverall
   */
  public static int getTabSizeOverall()
  {
    return tabSizeOverall;
  }
  /**
   * Grtes the "Sections" report tab size
   * @return int tabSizeSection
   */
  public static int getTabSizeSection()
  {
    return tabSizeSection;
  }
  /**
   * Gets the text color
   * @return Color textColor
   */
  public static Color getTextColor()
  {
    return textColor;
  }
  /**
   * Loads the settings from the Settings.dat file
   */
  public static void loadSettingsFile()
  {
    try
    {
      final FileInputStream fi = new FileInputStream(file);
      final ObjectInputStream input = new ObjectInputStream(fi);
      backgroundColor = (Color)input.readObject();
      borderColor = (Color)input.readObject();
      textColor = (Color)input.readObject();
      shouldLoadImage = input.readBoolean();
      shouldShowInvalidNumbers = input.readBoolean();
      tabSizeOverall = input.readInt();
      tabSizeSection = input.readInt();
      imageFile = (File)input.readObject();
      input.close();
      fi.close();
    }
    /*
     * Many exceptions may happen, including IOException, ClassNotFoundException, ClassCastException,
     * and other unpredictable behavior.  It's best to go with a generic Exception handler to catch
     * each and every possible exception when attempting to open and read the contents of the
     * settings file.
     */
    catch (final Exception e)
    {
      defaultSettings();
    }
  }
  /**
   * I created the static method resetImageFile() here, so when it's called from RetroPanel,
   * I don't have to use "File" from RetroPanel, and therefore don't have to import
   * Java.io.File in RetroPanel.
   */
  public static void resetImageFile()
  {
    setImageFile(new File(""));
  }
  /**
   * Saves the settings to the Settings.dat file
   */
  public static void saveSettingsFile()
  {
    try
    {
      final FileOutputStream fo = new FileOutputStream(file);
      final ObjectOutputStream output = new ObjectOutputStream(fo);
      output.writeObject(backgroundColor);
      output.writeObject(borderColor);
      output.writeObject(textColor);
      output.writeBoolean(shouldLoadImage);
      output.writeBoolean(shouldShowInvalidNumbers);
      output.writeInt(tabSizeOverall);
      output.writeInt(tabSizeSection);
      output.writeObject(imageFile);
      output.close();
      fo.close();
    }
    catch (final IOException e)
    {
      // do nothing (can't save settings; no need to tell user every time the program loads)
    }
  }
  /**
   * Sets the background color
   * @param color Color to set the background color to
   */
  public static void setBackgroundColor(final Color color)
  {
    backgroundColor = color;
  }
  /**
   * Sets the border color
   * @param color Color to set the border color to
   */
  public static void setBorderColor(final Color color)
  {
    borderColor = color;
  }
  /**
   * Sets the background image file
   * @param file File object to set the background image file object to
   */
  public static void setImageFile(final File file)
  {
    imageFile = file;
  }
  /**
   * Sets the boolean that keeps track of whether or not the background image should be loaded
   * @param bool true if the background image should be loaded; false otherwise
   */
  public static void setShouldLoadImage(final boolean bool)
  {
    shouldLoadImage = bool;
  }
  /**
   * Sets the boolean that keeps track on whether or not invalid numbers should be shown
   * @param bool true if invalid numbers should be shown; false otherwise
   */
  public static void setShouldShowInvalidNumbers(final boolean bool)
  {
    shouldShowInvalidNumbers = bool;
  }
  /**
   * Sets the tab size for "Overall" reports
   * @param size tab size for "Overall" reports, expressed as an int
   */
  public static void setTabSizeOverall(final int size)
  {
    tabSizeOverall = size;
  }
  /**
   * Sets the tab size for "Section" reports
   * @param size tab size for "Section" reports, expressed as an int
   */
  public static void setTabSizeSection(final int size)
  {
    tabSizeSection = size;
  }
  /**
   * Sets the text color
   * @param color The text color is set to this passed-in color
   */
  public static void setTextColor(final Color color)
  {
    textColor = color;
  }
  /**
   * Finds out if the background image should be loaded
   * @return boolean shouldLoadImage
   */
  public static boolean shouldLoadImage()
  {
    return shouldLoadImage;
  }
  /**
   * Finds out if invalid numbers should be shown
   * @return boolean showInvalidNumbers
   */
  public static boolean shouldShowInvalidNumbers()
  {
    return shouldShowInvalidNumbers;
  }
}
