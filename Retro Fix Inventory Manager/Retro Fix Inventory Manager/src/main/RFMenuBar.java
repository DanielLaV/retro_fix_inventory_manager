package main;

import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.KeyStroke;

import enums.Colors;
import enums.Mnemonic;
import enums.Texts;

/**
 * This is a helper class to the main RetroFix class, and meant to reduce the number of
 * lines of code in RetroFix.  This keeps track of all the menus and menu items for the
 * menu bar.  This class is the menu bar when instantiated and set to Retro Fix's main
 * frame's menu bar.
 * @author Eric Sweeten, Eric Bergren
 */
@SuppressWarnings("serial")
public class RFMenuBar extends JMenuBar
{
  /**
   * The menu for "File"
   */
  private final JMenu menuFile = new JMenu(Texts.MENU_FILE.getText());
  /**
   * The menu item for "New"
   */
  private final JMenuItem menuItemNew = new JMenuItem(Texts.MENU_ITEM_NEW.getText());
  /**
   * The menu item for "Load"
   */
  private final JMenuItem menuItemLoad = new JMenuItem(Texts.MENU_ITEM_LOAD.getText());
  /**
   * The menu item for "Save"
   */
  private final JMenuItem menuItemSave = new JMenuItem(Texts.MENU_ITEM_SAVE.getText());
  /**
   * The menu item for "Save As"
   */
  private final JMenuItem menuItemSaveAs = new JMenuItem(Texts.MENU_ITEM_SAVE_AS.getText());
  /**
   * The menu item for "Close"
   */
  private final JMenuItem menuItemClose = new JMenuItem(Texts.MENU_ITEM_CLOSE.getText());
  /**
   * The menu item for "Exit"
   */
  private final JMenuItem menuItemExit = new JMenuItem(Texts.MENU_ITEM_EXIT.getText());
  /**
   * The menu for "User"
   */
  private final JMenu menuUser = new JMenu(Texts.MENU_USER.getText());
  /**
   * The menu item for "Manager"
   */
  private final JMenuItem menuItemManage = new JMenuItem(Texts.MENU_ITEM_MANAGE.getText());
  /**
   * The menu item for "Logout"
   */
  private final JMenuItem menuItemLogout = new JMenuItem(Texts.MENU_ITEM_LOGOUT.getText());
  /**
   * The menu item for "Password"
   */
  private final JMenuItem menuItemPassword = new JMenuItem(Texts.MENU_ITEM_PASSWORD.getText());
  /**
   * The menu for "Settings"
   */
  private final JMenu menuSettings = new JMenu(Texts.MENU_SETTINGS.getText());
  /**
   * The inner menu for "Background Color"
   */
  private final JMenu menuItemBackgroundColor = new JMenu(Texts.MENU_ITEM_BACKGROUND_COLOR.getText());
  /**
   * The background color button group
   */
  private final ButtonGroup radioButtonMenuItemsForBackgroundColor = new ButtonGroup();
  /**
   * The background color radio buttons
   */
  private final JRadioButtonMenuItem[] radioButtonMenuItemBackgroundColor = new JRadioButtonMenuItem[Colors.TOTAL];
  /**
   * The inner menu for "Border Color"
   */
  private final JMenu menuBorderColor = new JMenu(Texts.MENU_ITEM_BORDER_COLOR.getText());
  /**
   * The border color button group
   */
  private final ButtonGroup radioButtonMenuItemsForBorderColor = new ButtonGroup();;
  /**
   * The border color radio buttons
   */
  private final JRadioButtonMenuItem[] radioButtonMenuItemBorderColor = new JRadioButtonMenuItem[Colors.TOTAL];
  /**
   * The inner menu for "Text Color"  
   */
  private final JMenu menuTextColor = new JMenu(Texts.MENU_ITEM_TEXT_COLOR.getText());
  /**
   * The text color button group
   */
  private final ButtonGroup radioButtonMenuItemsForTextColor = new ButtonGroup();;
  /**
   * The text color radio buttons
   */
  private final JRadioButtonMenuItem[] radioButtonMenuItemTextColor = new JRadioButtonMenuItem[Colors.TOTAL];
  /**
   * The menu item for "Background Image"  
   */
  private final JMenuItem menuItemBackgroundImage = new JMenuItem(Texts.MENU_ITEM_BACKGROUND_IMAGE.getText());
  /**
   * The checkbox menu item for "Show Invalid Numbers"
   */
  private final JCheckBoxMenuItem menuItemCheckboxShowInvalidNumbers = new JCheckBoxMenuItem(Texts.MENU_ITEM_SHOW_INVALID_NUMBERS.getText());
  /**
   * The menu item for "Restore Defaults"
   */
  private final JMenuItem menuItemRestoreDefaults = new JMenuItem(Texts.MENU_ITEM_RESTORE_DEFAULTS.getText());
  /**
   * The menu for "Help"
   */
  private final JMenu menuHelp = new JMenu(Texts.MENU_HELP.getText());
  /**
   * The menu item for "How"
   */
  private final JMenuItem menuItemHow = new JMenuItem(Texts.MENU_ITEM_HOW.getText());
  /**
   * The menu item for "About"
   */
  private final JMenuItem menuItemAbout = new JMenuItem(Texts.MENU_ITEM_ABOUT.getText());
  /**
   * The constructor for RFMenuBar, which sets up the GUI components
   */
  public RFMenuBar()
  {
    menuFile.add(menuItemNew);
    menuFile.add(menuItemLoad);
    menuFile.add(menuItemSave);
    menuFile.add(menuItemSaveAs);
    menuFile.add(menuItemClose);
    menuFile.addSeparator();
    menuFile.add(menuItemExit);
    menuUser.add(menuItemLogout);
    menuUser.add(menuItemManage);
    menuUser.add(menuItemPassword);
    menuSettings.add(menuItemBackgroundColor);
    menuSettings.add(menuBorderColor);
    menuSettings.add(menuTextColor);
    menuSettings.addSeparator();
    menuSettings.add(menuItemBackgroundImage);
    menuSettings.add(menuItemCheckboxShowInvalidNumbers);
    menuSettings.addSeparator();
    menuSettings.add(menuItemRestoreDefaults);
    menuHelp.add(menuItemHow);
    menuHelp.addSeparator();
    menuHelp.add(menuItemAbout);
    add(menuFile);
    add(menuUser);
    add(menuSettings);
    add(menuHelp);
    for (int i = 0; i < Colors.TOTAL; i++)
    {
      radioButtonMenuItemBackgroundColor[i] = new JRadioButtonMenuItem(Colors.getRadioButtonName(i));
      radioButtonMenuItemsForBackgroundColor.add(radioButtonMenuItemBackgroundColor[i]);
      menuItemBackgroundColor.add(radioButtonMenuItemBackgroundColor[i]);
      radioButtonMenuItemBorderColor[i] = new JRadioButtonMenuItem(Colors.getRadioButtonName(i));
      radioButtonMenuItemsForBorderColor.add(radioButtonMenuItemBorderColor[i]);
      menuBorderColor.add(radioButtonMenuItemBorderColor[i]);
      radioButtonMenuItemTextColor[i] = new JRadioButtonMenuItem(Colors.getRadioButtonName(i));
      radioButtonMenuItemsForTextColor.add(radioButtonMenuItemTextColor[i]);
      menuTextColor.add(radioButtonMenuItemTextColor[i]);
    }
  }
  /**
   * Gets the menu item "About"
   * @return JMenuItem menuItemAbout
   */
  public JMenuItem getMenuItemAbout()
  {
    return menuItemAbout;
  }
  /**
   * Gets the menu item for "Background Image"
   * @return JMenuItem menuItemBackgroundImage
   */
  public JMenuItem getMenuItemBackgroundImage()
  {
    return menuItemBackgroundImage;
  }
  /**
   * Gets the check box menu item for "Show Invalid Numbers"
   * @return JCheckBoxMenuItem menuItemCheckboxShowInvalidNumbers
   */
  public JCheckBoxMenuItem getMenuItemCheckBoxShowInvalidNumbers()
  {
    return menuItemCheckboxShowInvalidNumbers;
  }
  /**
   * Gets the menu item "Close"
   * @return JMenuItem menuItemClose
   */
  public JMenuItem getMenuItemClose()
  {
    return menuItemClose;
  }
  /**
   * Gets the menu item "Exit"
   * @return JMenuItem menuItemExit
   */
  public JMenuItem getMenuItemExit()
  {
    return menuItemExit;
  }
  /**
   * Gets the menu item "How"
   * @return JMenuItem menuItemHow
   */
  public JMenuItem getMenuItemHow()
  {
    return menuItemHow;
  }
  /**
   * Gets the menu item "Load"
   * @return JMenuItem menuItemLoad
   */
  public JMenuItem getMenuItemLoad()
  {
    return menuItemLoad;
  }
  /**
   * Gets the menu item for "Logout"
   * @return JMenuItem menuItemLogout
   */
  public JMenuItem getMenuItemLogout()
  {
    return menuItemLogout;
  }
  /**
   * Gets the menu item for "Manage"
   * @return JMenuItem menuItemManage
   */
  public JMenuItem getMenuItemManage()
  {
    return menuItemManage;
  }
  /**
   * Gets the menu item "New"
   * @return JMenuItem menuItemNew
   */
  public JMenuItem getMenuItemNew()
  {
    return menuItemNew;
  }
  /**
   * Gets the menu item "Password"
   * @return JMenuItem menuItemPassword
   */
  public JMenuItem getMenuItemPassword()
  {
    return menuItemPassword;
  }
  /**
   * Gets the menu item for "Restore Defaults"
   * @return JMenuItem menuItemRestoreDefaults
   */
  public JMenuItem getMenuItemRestoreDefaults()
  {
    return menuItemRestoreDefaults;
  }
  /**
   * Gets the menu item "Save"
   * @return JMenuItem menuItemSave
   */
  public JMenuItem getMenuItemSave()
  {
    return menuItemSave;
  }
  /**
   * Gets the menu item "Save As"
   * @return JMenuItem menuItemSaveAs
   */
  public JMenuItem getMenuItemSaveAs()
  {
    return menuItemSaveAs;
  }
  /**
   * Gets the radio buttons for background colors (13 of them)
   * @return JRadioButtonMenuItem[] radioButtonMenuItemBackgroundColor
   */
  public JRadioButtonMenuItem[] getRadioButtonMenuItemBackgroundColor()
  {
    return radioButtonMenuItemBackgroundColor;
  }
  /**
   * Gets the radio buttons for border color (13 of them)
   * @return JRadioButtonMenuItem[] radioButtonMenuItemBorderColor
   */
  public JRadioButtonMenuItem[] getRadioButtonMenuItemBorderColor()
  {
    return radioButtonMenuItemBorderColor;
  }
  /**
   * Gets the radio buttons for text color (13 of them)
   * @return JRadioButtonMenuItem[] radioButtonMenuItemTextColor
   */
  public JRadioButtonMenuItem[] getRadioButtonMenuItemTextColor()
  {
    return radioButtonMenuItemTextColor;
  }
  /**
   * Sets up the action listeners to listen in for when any of the menu bar items get clicked by the user
   * @param actionListener The action listener from RetroFix that listens to these menu items
   */
  public void setupActionListeners(ActionListener actionListener)
  {
    menuItemNew.addActionListener(actionListener);
    menuItemLoad.addActionListener(actionListener);
    menuItemSave.addActionListener(actionListener);
    menuItemSaveAs.addActionListener(actionListener);
    menuItemClose.addActionListener(actionListener);
    menuItemExit.addActionListener(actionListener);
    menuItemManage.addActionListener(actionListener);
    menuItemLogout.addActionListener(actionListener);
    menuItemPassword.addActionListener(actionListener);
    menuItemBackgroundImage.addActionListener(actionListener);
    menuItemCheckboxShowInvalidNumbers.addActionListener(actionListener);
    menuItemRestoreDefaults.addActionListener(actionListener);
    menuItemHow.addActionListener(actionListener);
    menuItemAbout.addActionListener(actionListener);
  }
  /**
   * The mnemonics allow for the ability to navigate the menu using the keyboard.
   * The numbers are from the ASCII character table.  65 is capital A, 66 capital B,
   * 67 capital C, etc.  For example, the mnemonic for menFile is set to 70,
   * meaning F.  This will cause the F in File to be underlined in the JMenu, and
   * accessible by pressing Alt+F.
   * The accelerators allow quick access to their associated menu items through the
   * use of F keys.  Fot example, F1 brings up help and F10 exits the application.
   */
  public void setupMnemonicsAndAccelerators()
  {
    menuFile.setMnemonic(Mnemonic.F.getAscii());
    menuHelp.setMnemonic(Mnemonic.H.getAscii());
    menuItemExit.setMnemonic(Mnemonic.X.getAscii());
    menuItemNew.setMnemonic(Mnemonic.N.getAscii());
    menuItemLoad.setMnemonic(Mnemonic.L.getAscii());
    menuItemSave.setMnemonic(Mnemonic.S.getAscii());
    menuItemSaveAs.setMnemonic(Mnemonic.A.getAscii());
    menuItemSaveAs.setDisplayedMnemonicIndex(5);
    menuItemClose.setMnemonic(Mnemonic.C.getAscii());
    menuUser.setMnemonic(Mnemonic.U.getAscii());
    menuItemManage.setMnemonic(Mnemonic.M.getAscii());
    menuItemLogout.setMnemonic(Mnemonic.L.getAscii());
    menuItemPassword.setMnemonic(Mnemonic.P.getAscii());
    menuItemHow.setMnemonic(Mnemonic.H.getAscii());
    menuItemAbout.setMnemonic(Mnemonic.A.getAscii());
    menuSettings.setMnemonic(Mnemonic.S.getAscii());
    menuItemBackgroundColor.setMnemonic(Mnemonic.B.getAscii());
    menuTextColor.setMnemonic(Mnemonic.T.getAscii());
    menuBorderColor.setMnemonic(Mnemonic.C.getAscii());
    menuItemBackgroundImage.setMnemonic(Mnemonic.I.getAscii());
    menuItemCheckboxShowInvalidNumbers.setMnemonic(Mnemonic.S.getAscii());
    menuItemRestoreDefaults.setMnemonic(Mnemonic.R.getAscii());
    menuItemHow.setAccelerator(KeyStroke.getKeyStroke(Texts.KEY_STROKE_F1.getText()));
    menuItemNew.setAccelerator(KeyStroke.getKeyStroke(Texts.KEY_STROKE_F2.getText()));
    menuItemExit.setAccelerator(KeyStroke.getKeyStroke(Texts.KEY_STROKE_F10.getText()));
  }
}
