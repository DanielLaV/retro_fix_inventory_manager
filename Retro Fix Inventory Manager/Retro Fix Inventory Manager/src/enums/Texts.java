package enums;

import tools.Settings;

/**
 * The enumeration that holds stray Strings.  The main purpose of this enum is to take
 * stray Strings out of the program and put them in here.  Some people consider it bad
 * programming practice to have stray numbers and Strings in your program, and to instead
 * declare them in constants or enums (such as here).
 * @author Eric Sweeten
 */
public enum Texts
{
  BUTTON_REPORT (" Report"),
  BUTTON_SECTION ("Section"),
  FRAME_TITLE_PASSWORD ("Password"),
  MESSAGE_ARE_YOU_SURE("Are you sure you want to "),
  MESSAGE_CURRENT (", current = "),
  MESSAGE_ENTER_NEW_TAB ("Enter new tab size - default = "),
  RETRO_FIX_INVENTORY_MANAGER ("Retro Fix Inventory Manager"),
  
  ANSWER_YES ("Yes"),
  ANSWER_NO ("No"),
  ATTRIBUTE_NAME ("Name"),
  ATTRIBUTE_LOOSE ("Loose"),
  ATTRIBUTE_COMPLETE ("Complete"),
  ATTRIBUTE_NEW ("New"),
  ATTRIBUTE_BOX ("Box"),
  ATTRIBUTE_MANUAL ("Manual"),
  ATTRIBUTE_CONDITION ("Condition"),
  ATTRIBUTE_NOTES ("Notes"),
  ATTRIBUTE_VALUE ("Value"),
  BUTTON_ACCEPT ("Accept"),
  BUTTON_ADD ("Add"),
  BUTTON_APPLY ("Apply"),
  BUTTON_BROWSE ("Browse"),
  BUTTON_CANCEL ("Cancel"),
  BUTTON_CLEAR ("Clear"),
  BUTTON_CLOSE ("Close"),
  BUTTON_DELETE ("Delete"),
  BUTTON_MODIFY ("Modify"),
  BUTTON_MORE_OPTIONS ("More Options"),
  BUTTON_OKAY ("Okay"),
  BUTTON_ORDER ("Order"),
  BUTTON_PRINTABLE ("Printable"),
  BUTTON_COMPLETE_REPORT ("Complete" + BUTTON_REPORT.getText()),
  BUTTON_SECTION_REPORT (BUTTON_SECTION.getText() + BUTTON_REPORT.getText()),
  BUTTON_SHORTCUT_DELETE ("-"),
  BUTTON_SHORTCUT_DUPLICATE ("+"),
  BUTTON_TAB_SIZE ("Tab Size"),
  CHARACTER_APOSTROPHE ("'"),
  CHARACTER_DASH (" - "),
  CHARACTER_ONE ("1"),
  CHARACTER_QUESTION_MARK ("?"),
  COLOR_BLACK ("Black"),
  COLOR_BLUE ("Blue"),
  COLOR_CYAN ("Cyan"),
  COLOR_DARK_GRAY ("Dark Gray"),
  COLOR_GRAY ("Gray"),
  COLOR_GREEN ("Green"),
  COLOR_LIGHT_GRAY ("Light Gray"),
  COLOR_MAGENTA ("Magenta"),
  COLOR_ORANGE ("Orange"),
  COLOR_PINK ("Pink"),
  COLOR_RED ("Red"),
  COLOR_WHITE ("White"),
  COLOR_YELLOW ("Yellow"),
  CONDITION_FAIR ("fair"),
  CONDITION_GOOD ("good"),
  CONDITION_MINT ("mint"),
  CONDITION_NEW ("new"),
  CONDITION_POOR ("poor"),
  ERROR_ADMIN_NAME ("You cannot use, modify, or delete the name 'admin'.  That login name is reserved for the administrator."),
  ERROR_AT_LEAST_ONE_PAGE ("Must have at least one page.  Create\nanother page to delete this one."),
  ERROR_BLANK_II_NAME ("Cannot have inventory item with blank name.\nSelection has been cancelled."),
  ERROR_BLANK_PAGE_NAME ("Page name cannot be blank."),
  ERROR_DUPLICATE_PAGE ("There is already a page with that name."),
  ERROR_INCORRECT_PASSWORD ("Incorrect password"),
  ERROR_INVALID_USER_NAME ("No such user name exists.  If you feel this is an error, contact the administrator."),
  ERROR_MUST_SAVE ("You must save the database before creating users."),
  ERROR_UNABLE_TO_LOAD_DATABASE_FILE ("Unable to load database file"),
  ERROR_UNABLE_TO_SAVE_DATABASE_FILE ("Unable to save database file"),
  ERROR_USE_SAVE_AS_FIRST ("Error.  File has never been saved.  Use Save As first."),
  ERROR_USER_NAME_BLANK ("The user name cannot be blank."),
  ERROR_USER_NAME_TAKEN ("That user name is already taken.  Each user name must be unique."),
  FILE_CHOOSER_CURRENT_DIRECTORY ("user.home"),
  FILE_NAME_EXTENSION_BMP ("BMP"),
  FILE_NAME_EXTENSION_FILTER_IMAGES ("Images"),
  FILE_NAME_EXTENSION_INVENTORY_FILES ("Inventory Files"),
  FILE_NAME_EXTENSION_GIF ("GIF"),
  FILE_NAME_EXTENSION_INV ("INV"),
  FILE_NAME_EXTENSION_JPG ("JPG"),
  FILE_NAME_EXTENSION_PNG ("PNG"),
  FILE_SETTINGS ("settings.dat"),
  FONT_TIMES_NEW_ROMAN ("Times New Roman"),
  FRAME_TITLE_BACKGROUND_IMAGE ("Background Image"),
  FRAME_TITLE_PRINTABLE_REPORT ("Printable" + BUTTON_REPORT.getText()),
  FRAME_TITLE_SECTION_MANAGER ("Section Manager"),
  FRAME_TITLE_SECTION_MANAGER_MORE_OPTIONS ("Section Manager - More Options"),
  FRAME_TITLE_USER_MANAGER ("User Manager"),
  KEY_STROKE_F1 ("F1"),
  KEY_STROKE_F2 ("F2"),
  KEY_STROKE_F10 ("F10"),
  LABEL_CURRENT_PASSWORD ("Current " + FRAME_TITLE_PASSWORD.getText()),
  LABEL_ENTER_PASSWORD ("Enter " + FRAME_TITLE_PASSWORD.getText()),
  LABEL_REENTER_PASSWORD ("Re-enter " + FRAME_TITLE_PASSWORD.getText()),
  LABEL_PAGE ("Page"),
  LABEL_PRICE (" Price"),
  LABEL_TOTALS ("Totals"),
  LABEL_USER_NAME ("User Name"),
  MENU_FILE ("File"),
  MENU_HELP ("Help"),
  MENU_ITEM_ABOUT ("About " + RETRO_FIX_INVENTORY_MANAGER.getText()),
  MENU_ITEM_BACKGROUND_COLOR ("Background Color"),
  MENU_ITEM_BACKGROUND_IMAGE ("Background Image"),
  MENU_ITEM_BORDER_COLOR ("Border Color"),
  MENU_ITEM_CLOSE ("Close"),
  MENU_ITEM_EXIT ("Exit"),
  MENU_ITEM_HOW ("How to use app"),
  MENU_ITEM_LOAD ("Load"),
  MENU_ITEM_LOGOUT ("Log Out"),
  MENU_ITEM_MANAGE ("Manage"),
  MENU_ITEM_NEW ("New Database..."),
  MENU_ITEM_PASSWORD ("Password"),
  MENU_ITEM_RESTORE_DEFAULTS ("Restore Defaults"),
  MENU_ITEM_SAVE ("Save"),
  MENU_ITEM_SAVE_AS ("Save As"),
  MENU_ITEM_SHOW_INVALID_NUMBERS ("Show Invalid Numbers"),
  MENU_ITEM_TEXT_COLOR ("Text Color"),
  MENU_SETTINGS ("Settings"),
  MENU_USER ("User"),
  MESSAGE_ABOUT (RETRO_FIX_INVENTORY_MANAGER.getText() + " v1.0\n\nCreated by...\nEric Sweeten\nKevin Powell\nDaniel LaVergne\nEric Bergren"),
  MESSAGE_ADD_USER ("Enter the name of the user you want to add."),
  MESSAGE_CLOSE (MESSAGE_ARE_YOU_SURE.getText() + "log out and close the current database without saving?\n\nClick 'Yes' if you do want to close without saving or if you have already saved.\nClick 'No' or 'Cancel' to go back and save the database."),
  MESSAGE_DELETE_USER (MESSAGE_ARE_YOU_SURE.getText() + "delete the user "),
  MESSAGE_HOW ("<html><body><p style='width: 1000px;'>When Retro Fix Inventory Manager first loads, it starts out with a blank database.  When you look at the bottom, you will notice you are automatically "
             + "logged in as 'admin'.  You will also notice in one drop-down menu (the drop-down menu displaying the 'Sections'), it says 'First'.  This is the default name of the one section you have when a "
             + "new database is started.<br><br>Add, delete, and modify inventory items:<br>In order to add an inventory item, click on the 'Add' button located at the bottom-right.  In order to delete, modify, or duplicate an item, click on the applicable "
             + "button on the right of the screen, marked '-', pencil icon, and '+', respectively.  Once the page is full of inventory items, additional inventory items will carry on to the next page.  In order to flip back "
             + "and forth through pages, click on the 'Page' drop-down menu at the bottom left and click on the desired page.<br><br>Add, modify, and delete sections:<br>In order to add, delete, and modify sections, click on 'Section' at the bottom-left and "
             + "a pop-up screen will appear offering options to add, delete, and modify sections.  If you want to change a section from the default inventory type (Video Game) to Generic Item, click on 'More Options' "
             + "and the option to convert will be there.  Note that when you convert from a Video Game to a Generic Item, you will lose any data you have in the Loose Price, Complete Price, New Price, Box, and "
             + "Manual fields.<br><br>Manage Users:<br>In order to manage users, you have to be logged in as 'admin'.  Once you are, or if you are by default (you've just started a new database), you must save the database to a file "
             + "before you can add users.  To do this, go to File->Save As and pick an appropriate directory and file name to save the file.  Once that is complete, go to User->Manage and from there are the "
             + "options to add, delete, and modify user names and also user permissions.  Note that you cannot add, delete, or modify the 'admin' user name.<br><br>Changing your password:<br>To change passwords, once you are logged on, "
             + "go to File->Password, type in your password, and then type in your new password and retype it.  The new password and retype must match, along with your original password, for your new password "
             + "to be accepted.  Note that the default, automatically-assigned password for every user, including admin, is blank (just hit enter).<br><br>Loading a database:<br>In order to load a database from file, go to File->Load, browse to your database file, and double-click on it.  A pop-up screen will appear.  There you will enter the name of the user "
             + "you wish to log in as (administrator user name is 'admin') and password.  The default password is blank (just hit enter).<br><br>Saving a database:<br>In order to save a database, if it's never been saved before, you must first click 'Save 'As' "
             + "and select an appropriate file name and location.  Once this is done and it's been successfully saved, you can save from then on by clicking 'Save' instead of 'Save As'.  Also, to tell when a file has been saved or when a file is loaded, "
             + "it will show the URL in the application's frame title.<br><br>Reports:<br>You can display reports by clicking the 'Overall Report' or 'Section Report' buttons at the bottom-right.  These will show the applicable reports.  To display a printable version of the report, there will be a button on the report GUI labeled 'Printable' which you "
             + "can click.<br><br>Settings:<br>You can manipulate settings in the program to change aesthetics and other options.  In order to change the background, border, or text color, go to Settings and click the appropriate "
             + "option.  In order to change the background image, the option to change it is also under Settings.  In order to make it where numerical errors are shown, check the box where it says"
             + "'Show Invalid Numbers'.  To restore to default settings, click 'Restore Defaults'.</p></body></html>"),
  MESSAGE_LOGOUT ("Logging out will close the database.  Are you sure you want to log out?  To log back in under a different user, simply re-open the database."),
  MESSAGE_PASSWORDS_DONT_MATCH ("Passwords don't match"),
  MESSAGE_MODIFY_USER ("Enter the new name of the user"),
  MESSAGE_NEW (MESSAGE_ARE_YOU_SURE.getText() + "start a new database (this will close the current one without saving)?\n\nClick 'Yes' if you do want to start a new database.\nClick 'No' or 'Cancel' to go back and save first or to not start a new database."),
  MESSAGE_NEW_SECTION_NAME ("Enter new name of section"),
  MESSAGE_RESTORE_DEFAULTS ("Are you sure you want to restore default settings?"),
  MESSAGE_TAB_SIZE_OVERALL (MESSAGE_ENTER_NEW_TAB.getText() + Settings.DEFAULT_TAB_SIZE_OVERALL + MESSAGE_CURRENT.getText()),
  MESSAGE_TAB_SIZE_SECTION (MESSAGE_ENTER_NEW_TAB.getText() + Settings.DEFAULT_TAB_SIZE_SECTION + MESSAGE_CURRENT.getText()),
  RADIO_BUTTON_CAN_ADD_ITEMS ("Can add items"),
  RADIO_BUTTON_CAN_ADD_SECTIONS ("Can add sections"),
  RADIO_BUTTON_CAN_MODIFY_ITEMS ("Can modify items"),
  RADIO_BUTTON_CAN_MODIFY_SECTIONS ("Can modify sections"),
  RADIO_BUTTON_CAN_DELETE_ITEMS ("Can delete items"),
  RADIO_BUTTON_CAN_DELETE_SECTIONS ("Can delete sections"),
  RADIO_BUTTON_CAN_ORDER_ITEMS ("Can order items"),
  RADIO_BUTTON_FROM_FILE ("From file"),
  RADIO_BUTTON_NONE ("None"),
  RADIO_BUTTON_OTHER ("Other"),
  RADIO_BUTTON_VIDEO_GAME ("Video Game"),
  RESOURCE_CONTROLLER ("/controller.png"),
  RESOURCE_PENCIL ("/pencil.png"),
  TEXT_ADMIN ("admin"),
  TEXT_FIRST ("First"),
  TEXT_LOGGED_IN_AS ("Logged in as "),
  TEXT_NOT_APPLICABLE ("N/A"),
  TOOLTIP_OVERALL_TOTALS ("Overall totals of all sections, on this page and all others");
  /**
   * The text this enum holds
   */
  private final String text;
  /**
   * Constructor for enum Texts, which holds a single String
   * @param text The string for this enum to hold
   */
  private Texts(final String text)
  {
    this.text = text;
  }
  /**
   * Gets the text this enum is holding
   * @return String text
   */
  public String getText()
  {
    return text;
  }
  /**
   * Integer constant equal to 1 and only used in this class -
   * used with isBlankYesOrNo method below
   */
  private static final int YES = 1;
  /**
   * Integer constant equal to 2 and only used in this class -
   * used with isBlankYesOrNo method below
   */
  private static final int NO = 2;
  /**
   * Integer constant equal to 0 and only used in this class -
   * used with isBlankYesOrNo method below
   */
  private static final int BLANK = 0;
  /**
   * Gets a String with the specified number of blanks (spaces)
   * @param amount the number of blank spaces requested in the String
   * @return String with a specified number of blank spaces
   */
  public static String getBlanks(final int amount)
  {
    String blankString = "";
    for (int i = 0; i < amount; i++)
    {
      blankString += " ";
    }
    return blankString;
  }
  /**
   * Returns a numerical value assocated with YES, NO, or BLANK, depending on the String passed
   * in, and whether it is the same ("Yes", "No", or "").
   * @param s
   * @return
   */
  public static int isBlankYesOrNo(final String s)
  {
    return s.equals(ANSWER_YES.getText()) ? YES : (s.equals(ANSWER_NO.getText()) ? NO : BLANK);
  }
}
