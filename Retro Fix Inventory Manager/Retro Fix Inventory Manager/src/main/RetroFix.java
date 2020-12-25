package main;

import gui.BackgroundImageGUI;
import gui.InventoryItemTextField;
import gui.ModifyInventoryItemGUI;
import gui.PasswordGUI;
import gui.ReportGUI;
import gui.SectionManagerGUI;
import gui.SectionManagerMoreOptionsGUI;
import gui.UserManagerGUI;
import inventory.InventoryItem;
import inventory.Item;
import inventory.Organizer;
import inventory.Sections;
import inventory.VideoGame;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SpringLayout;

import tools.DatabaseIO;
import tools.Settings;
import tools.SpringUtilities;
import tools.User;
import enums.Colors;
import enums.Dimensions;
import enums.InventoryType;
import enums.Mode;
import enums.Money;
import enums.ReportType;
import enums.Texts;

/**
 * This class is the central class of the program, as well as the biggest.  This is where the user will
 * spend most of their time.  It keeps track of the main frame and the main panel showing the inventory
 * items, along with the menu bar and other GUI objects the user can interact with.
 * @author Eric Sweeten
 */
@SuppressWarnings("serial")
public class RetroFix extends JPanel
{
  /**
   * This is for the three empty spaces above the sets of three JButtons on the right and to the right of the
   * nine JLabels up top.  It is also used as an argument to SpringUtilties in some of the mini-GUIs, for the
   * sake of making a grid of the GUI elements.
   */
  public static final int PADDING_3 = 3;
  /**
   * Used as an argument to SprintUtilities makeCompactGrid method, for the sake of making a grid out of the
   * GUI elements
   */
  private static final int PADDING_12 = 12;
  /**
   * Used as an argument to SprintUtilities makeCompactGrid method, for the sake of making a grid out of the
   * GUI elements
   */
  private static final int PADDING_7 = 7;
  /**
   * Used in conjunction with getBlanks method of Texts class, to help make JLabel names of proper size
   */
  private static final int PADDING_10 = 10;
  /**
   * Used in conjunction with getBlanks method of Texts class, to help make JLabel names of proper size
   */
  private static final int PADDING_75 = 75;
  /**
   * Used in confunction with getBlanks method of Texts class, to help make JLabel names of proper size
   */
  private static final int PADDING_126 = 126;
  /**
   * This listens to most of the GUI components in this class, as well as a lot of the GUI components in the
   * mini-GUIs which cause the mini-GUI to close and come back to this class.
   */
  private final GlobalActionListener globalActionListener = new GlobalActionListener();
  /**
   * The one and only mouse listener for this class
   */
  private final GlobalMouseListener globalMouseListener = new GlobalMouseListener();
  /**
   * An action listener that listens to one object - the combo box displaying the page
   */
  private final ComboBoxPageActionListener comboBoxPageActionListener = new ComboBoxPageActionListener();
  
  /**
   * The main frame of RetroFix - also RetroFix's only frame
   */
  private final JFrame mainFrame = new JFrame();
  /**
   * The panel that shows the inventory items
   */
  private final JPanel databasePanel = new JPanel();
  /**
   * The panel that holds the two panels lowerLeftPanel and lowerRightPanel
   */
  private final JPanel lowerPanel = new JPanel();
  /**
   * The panel that holds the Section button, sections combo box, page label, page combo box, and
   * "Logged in as" label
   */
  private final JPanel lowerLeftPanel = new JPanel();
  /**
   * The panel that holds the Add, Section Report, and Complete Report buttons
   */
  private final JPanel lowerRightPanel = new JPanel();
  
  /**
   * An instance of Sections class, which keeps track of the sections
   */
  private Sections sections = new Sections();
  
  /**
   * A 2xdeep ArrayList which keeps track of the inventory items.  There is a minimum of one section but no minimum
   * for the number of inventory items per section.
   */
  private List<ArrayList<InventoryItem>> inventoryItems = new ArrayList<ArrayList<InventoryItem>>();
  /**
   * The list keeping track of the users (User objects)
   */
  private List<User> users = new ArrayList<User>();
  /**
   * The inventory type the database panel is currently showing
   */
  private InventoryType currentDatabasePanel;
  /**
   * The combo box for sections, which contains a minimum of one section
   */
  private final JComboBox<String> comboBoxSections = new JComboBox<>(sections.getSectionNames());
  /**
   * The combo box for page, which contains at least page 1
   */
  private final JComboBox<String> comboBoxPage = new JComboBox<>();
  
  /**
   * The label for "Name"
   */
  private final JLabel labelName = new JLabel(Texts.ATTRIBUTE_NAME.getText() + Texts.getBlanks(PADDING_126));
  /**
   * The label for "Loose"
   */
  private final JLabel labelLoose = new JLabel(Texts.ATTRIBUTE_LOOSE.getText() + Texts.getBlanks(ModifyInventoryItemGUI.PADDING_6));
  /**
   * The label for "Complete"
   */
  private final JLabel labelComplete = new JLabel(Texts.ATTRIBUTE_COMPLETE.getText() + Texts.getBlanks(ModifyInventoryItemGUI.PADDING_3));
  /**
   * The label for "New"
   */
  private final JLabel labelNew = new JLabel(Texts.ATTRIBUTE_NEW.getText() + Texts.getBlanks(ReportGUI.PADDING_9));
  /**
   * The label for "Box"
   */
  private final JLabel labelBox = new JLabel(Texts.ATTRIBUTE_BOX.getText());
  /**
   * The label for "Manual"
   */
  private final JLabel labelManual = new JLabel(Texts.ATTRIBUTE_MANUAL.getText());
  /**
   * The label for "Condition"
   */
  private final JLabel labelCondition = new JLabel(Texts.ATTRIBUTE_CONDITION.getText() + Texts.getBlanks(PADDING_10));
  /**
   * The label for "Notes"
   */
  private final JLabel labelNotes = new JLabel(Texts.ATTRIBUTE_NOTES.getText() + Texts.getBlanks(PADDING_75));
  /**
   * The label for "Value"
   */
  private final JLabel labelValue = new JLabel(Texts.ATTRIBUTE_VALUE.getText() + Texts.getBlanks(ReportGUI.PADDING_9));  

  /**
   * The label for "Page"
   */
  private final JLabel labelPage = new JLabel(Texts.LABEL_PAGE.getText());
  /**
   * The label for "Logged in as"
   */
  private final JLabel labelLoggedInAs = new JLabel();
  
  /**
   * The button "Add"
   */
  private final JButton buttonAdd = new JButton(Texts.BUTTON_ADD.getText());
  /**
   * The button "Section"
   */
  private final JButton buttonSection = new JButton(Texts.BUTTON_SECTION.getText());
  /**
   * The button "Section Report"
   */
  private final JButton buttonSectionReport = new JButton(Texts.BUTTON_SECTION_REPORT.getText());
  /**
   * The button "Complete Report"
   */
  private final JButton buttonCompleteReport = new JButton(Texts.BUTTON_COMPLETE_REPORT.getText());

  /**
   * The DatabaseIO object - look in the class for a detailed explanation of what it does
   */
  private final DatabaseIO databaseIO = new DatabaseIO(this);
  /**
   * The Organizer object - look in the class for a detailed explanation of what it does
   */
  private final Organizer organizer = new Organizer();
  /**
   * The RFMenuBar object - look in the class for a detailed explanation of what it does
   */
  private final RFMenuBar rfMenuBar = new RFMenuBar();
  /**
   * The RFAssist object - look in the class for a detailed explanation of what it does
   */
  private final RFAssist rfAssist = new RFAssist();
  
  /**
   * The current user of the program
   */
  private User user;
  /**
   * The inventory item text fields - all the text fields in the database panel
   */
  private final InventoryItemTextField IITexts[] = new InventoryItemTextField[InventoryItemTextField.NUMBER_OF_II_TEXT_FIELDS];
  /**
   * The ModifyInventoryItemGUI object - look in the class for a detailed explanation of what it does
   */
  private final ModifyInventoryItemGUI modifyInventoryItemGUI = new ModifyInventoryItemGUI();
  /**
   * The SectionManagerGUI object - look in the class for a detailed explanation of what it does
   */
  private final SectionManagerGUI sectionManagerGUI = new SectionManagerGUI();
  /**
   * The SectionManagerMoreOptionsGUI object - look in the class for a detailed explanation of what it does
   */
  private final SectionManagerMoreOptionsGUI sectionManagerMoreOptionsGUI = new SectionManagerMoreOptionsGUI();
  /**
   * The BackgroundImageGUI object - look in the class for a detailed explanation of what it does
   */
  private final BackgroundImageGUI backgroundImageGUI = new BackgroundImageGUI();
  /**
   * The ReportGUI object - look in the class for a detailed explanation of what it does
   */
  private final ReportGUI reportGUI = new ReportGUI(this);
  /**
   * The PasswordGUI object - look in the class for a detailed explanation of what it does
   */
  private final PasswordGUI passwordGUI = new PasswordGUI(false, this);
  /**
   * The UserManagerGUI object - look in the class for a detailed explanation of what it does
   */
  private final UserManagerGUI userManagerGUI = new UserManagerGUI(databaseIO, users);
  
  /**
   * The current mode the user is in
   */
  private Mode currentMode;
  /**
   * The current background image
   */
  private BufferedImage backgroundImage;
  
  /**
   * The current page
   */
  private int currentPage;
  /**
   * The total number of pages for this section
   */
  private int numberOfPages;
  /**
   * The current section
   */
  private int currentSection;
  /** 
   * Constructor for RetroFix.  Sets up the GUI objects here and through helper methods called from here.
   * Also loads the settings file.
   */
  public RetroFix()
  {
    setupGUIComponents();
    setupListeners();
    rfMenuBar.setupMnemonicsAndAccelerators();
    setupPanels();
    Settings.loadSettingsFile();
    loadBackgroundImage();
    Colors.passInColorablesByReference(rfMenuBar.getRadioButtonMenuItemBackgroundColor(),
        rfMenuBar.getRadioButtonMenuItemTextColor(), rfMenuBar.getRadioButtonMenuItemBorderColor(), IITexts);
    /*
     * This is called once, when RetroPanel first loads.
     */
    Colors.selectAppropriateBackgroundTextAndBorderColors();
    rfMenuBar.getMenuItemCheckBoxShowInvalidNumbers().setSelected(Settings.shouldShowInvalidNumbers());
    sections.passInInventoryItems(inventoryItems);
    //InputStream is = Main.class.getResourceAsStream();
    try
    {
      mainFrame.setIconImage(ImageIO.read(getClass().getResourceAsStream(Texts.RESOURCE_CONTROLLER.getText())));
    }
    catch (final IOException exc)
    {
      //do nothing
    }
  }
  /**
   * Checks to see if the accept button in Modify Inventory Item GUI was clicked
   * @param obj The clicked-on GUI component
   */
  private void checkForAcceptButton(final Object obj)
  {
    if (obj == modifyInventoryItemGUI.getButtonAccept())
    {
      if (modifyInventoryItemGUI.getTextFieldName().getText().isEmpty())
      {
        JOptionPane.showMessageDialog(mainFrame, Texts.ERROR_BLANK_II_NAME.getText());
      }
      else
      {
        int x = modifyInventoryItemGUI.getIndex();
        int i = modifyInventoryItemGUI.getIndex() + currentPage * InventoryItemTextField.NUMBER_OF_II_TEXT_FIELDS;
        if (currentMode == Mode.ADD)
        {
          inventoryItems.get(currentSection).add(new VideoGame());
          i = inventoryItems.get(currentSection).size() - 1;
        }
        inventoryItems.get(currentSection).get(i).setName(modifyInventoryItemGUI.getTextFieldName().getText());
        if (inventoryItems.get(currentSection).get(i).getInventoryType() == InventoryType.VIDEO_GAME)
        {
          ((VideoGame)inventoryItems.get(currentSection).get(i)).setLoosePrice(modifyInventoryItemGUI.getTextFieldLoosePrice().getText());
          ((VideoGame)inventoryItems.get(currentSection).get(i)).setCompletePrice(modifyInventoryItemGUI.getTextFieldCompletePrice().getText());
          ((VideoGame)inventoryItems.get(currentSection).get(i)).setNewPrice(modifyInventoryItemGUI.getTextFieldNewPrice().getText());
          ((VideoGame)inventoryItems.get(currentSection).get(i)).setBox(modifyInventoryItemGUI.getComboBoxBox().getSelectedItem().toString());
          ((VideoGame)inventoryItems.get(currentSection).get(i)).setManual(modifyInventoryItemGUI.getComboBoxManual().getSelectedItem().toString());
        }
        inventoryItems.get(currentSection).get(i).setCondition(modifyInventoryItemGUI.getComboBoxCondition().getSelectedItem().toString());
        inventoryItems.get(currentSection).get(i).setNotes(modifyInventoryItemGUI.getTextFieldNotes().getText());
        inventoryItems.get(currentSection).get(i).setValue(modifyInventoryItemGUI.getTextFieldValue().getText());
        if (x < InventoryItemTextField.NUMBER_OF_II_TEXT_FIELDS)
        {
          IITexts[x].setName(modifyInventoryItemGUI.getTextFieldName().getText());
          IITexts[x].setLoosePrice(modifyInventoryItemGUI.getTextFieldLoosePrice().getText());
          IITexts[x].setCompletePrice(modifyInventoryItemGUI.getTextFieldCompletePrice().getText());
          IITexts[x].setNewPrice(modifyInventoryItemGUI.getTextFieldNewPrice().getText());
          IITexts[x].setBox(modifyInventoryItemGUI.getComboBoxBox().getSelectedItem().toString());
          IITexts[x].setManual(modifyInventoryItemGUI.getComboBoxManual().getSelectedItem().toString());
          IITexts[x].setCondition(modifyInventoryItemGUI.getComboBoxCondition().getSelectedItem().toString());
          IITexts[x].setNotes(modifyInventoryItemGUI.getTextFieldNotes().getText());
          IITexts[x].setValue(modifyInventoryItemGUI.getTextFieldValue().getText());
        }
        refresh();
        Money.checkValiditiyOfNumbers(IITexts);
      }
      modifyInventoryItemGUI.clear();
      restoreFocusToMainPanel();
      modifyInventoryItemGUI.makeVisible(false);
    }
  }
  /**
   * Checks to see if certain GUI components with action listeners were
   * clicked on by the user.  This is called when the global action listener
   * hears something that the main method listening to actions did not find
   * a match.
   * @param obj The GUI object that was clicked on
   */
  private void checkForOtherActions(final Object obj)
  {
    if (isAnyMiniGUIVisible()) return;
    if (obj == rfMenuBar.getMenuItemNew())
    {
      rfAssist.closeOrNew(false);
    }
    else if (obj == rfMenuBar.getMenuItemLoad())
    {
      databaseIO.loadPasswordGUI();
    }
    else if (obj == rfMenuBar.getMenuItemSave())
    {
      databaseIO.save(false);
      setMainFrameTitleAndLabelLoggedInAs();
    }
    else if (obj == rfMenuBar.getMenuItemSaveAs())
    {
      databaseIO.saveAs();
      setMainFrameTitleAndLabelLoggedInAs();
    }
    else if (obj == rfMenuBar.getMenuItemClose())
    {
      rfAssist.closeOrNew(true);
    }
    else if (obj == rfMenuBar.getMenuItemPassword())
    {
      passwordGUI.clear();
      passwordGUI.updateUserName();
      passwordGUI.makeVisible(true);
    }
    else if (obj == rfMenuBar.getMenuItemExit())
    {
      System.exit(0);
    }
    else if (obj == rfMenuBar.getMenuItemHow())
    {
      JOptionPane.showMessageDialog(mainFrame, Texts.MESSAGE_HOW.getText(), Texts.MENU_HELP.getText() + Texts.CHARACTER_DASH.getText() + Texts.MENU_ITEM_HOW.getText()+ Texts.CHARACTER_DASH.getText() + Texts.RETRO_FIX_INVENTORY_MANAGER.getText(), JOptionPane.INFORMATION_MESSAGE);
    }
    else if (obj == rfMenuBar.getMenuItemAbout())
    {
      JOptionPane.showMessageDialog(null, Texts.MESSAGE_ABOUT.getText());
    }
    else if (obj == buttonSection)
    {
      sectionManagerGUI.updateSections(sections.getSectionNames());
      sectionManagerGUI.makeVisible(true);
    }
    else if (obj == comboBoxSections)
    {
      currentSection = comboBoxSections.getSelectedIndex();
      currentPage = 0;
      comboBoxPage.removeActionListener(comboBoxPageActionListener);
      comboBoxPage.setSelectedIndex(0);
      comboBoxPage.addActionListener(comboBoxPageActionListener);
      if (currentSection == -1) currentSection = 0;
      refresh();
    }
    Color color;
    for (int i = 0; i < Colors.TOTAL; i++)
    {
      if (obj == rfMenuBar.getRadioButtonMenuItemBackgroundColor()[i])
      {
        Colors.setBackgroundColorSaveFileAndRepaint(obj);
        if (Colors.getBackgroundColor() != null) repaint();
      }
      else if (obj == rfMenuBar.getRadioButtonMenuItemTextColor()[i])
      {
        color = Colors.setTextFieldColors(obj);
        if (color != null) convertLabelColors(color);
      }
      else if (obj == rfMenuBar.getRadioButtonMenuItemBorderColor()[i])
      {
        Colors.convertAllTextFieldBorders(obj);
      }
    }
  }
  /**
   * Converts all label colors to a specific color
   * @param color The color that will be the color of the labels
   */
  private void convertLabelColors(final Color color)
  {
    labelName.setForeground(color);
    labelLoose.setForeground(color);
    labelComplete.setForeground(color);
    labelNew.setForeground(color);
    labelBox.setForeground(color);
    labelManual.setForeground(color);
    labelCondition.setForeground(color);
    labelNotes.setForeground(color);
    labelValue.setForeground(color);
    labelPage.setForeground(color);
    labelLoggedInAs.setForeground(color);
  }
  /**
   * Gets the combo box for Page
   * @return JComboBox<String> comboBoxPage
   */
  public JComboBox<String> getComboBoxPage()
  { return comboBoxPage; }
  /**
   * Gets the combo box displaying the sections
   * @return JComboBox<String> comboBoxSections
   */
  public JComboBox<String> getComboBoxSections()
  { return comboBoxSections; }
  /**
   * Gets the current mode
   * @return the current mode Retro Fix is in
   */
  public Mode getCurrentMode()
  { return currentMode; }
  /**
   * Gets the current page
   * @return the current page the user is on
   */
  public int getCurrentPage()
  { return currentPage; }
  /**
   * Gets the current section
   * @return int currentSection
   */
  public int getCurrentSection()
  { return currentSection; }
  /**
   * Gets the instantiated Database IO object
   * @return DatabaseIO databaseIO
   */
  public DatabaseIO getDatabaseIO()
  { return databaseIO; }
  /**
   * Gets the array of Inventory Item Text Field objects, which are all the
   * Inventory Item Text Fields showing on the main frame of the application
   * @return InventoryItemTextFild[] IITexts
   */
  public InventoryItemTextField[] getIITexts()
  { return IITexts; }
  /**
   * Gets the Inventory Items
   * @return List<ArrayList<InventoryItem>> inventoryItems
   */
  public List<ArrayList<InventoryItem>> getInventoryItems()
  { return inventoryItems; }
  /**
   * Gets the main frame of Retro Fix
   * @return JFrame mainFrame
   */
  public JFrame getMainFrame()
  { return mainFrame; }
  /**
   * Gets the instantiated Modify Inventory Item GUI object
   * @return ModifyInventoryItemGUI modifyInventoryItemGUI
   */
  public ModifyInventoryItemGUI getModifyInventoryItemGUI()
  { return modifyInventoryItemGUI; }
  /**
   * Gets the number of pages in this section
   * @return int numberOfPages
   */
  public int getNumberOfPages()
  { return numberOfPages; }
  /**
   * Gets the instantiated Section Manager GUI object
   * @return SectionManagerGUI sectionManagerGUI
   */
  public SectionManagerGUI getSectionManagerGUI()
  { return sectionManagerGUI; }
  /**
   * Gets the instantiated object Sections
   * @return Sections sections
   */
  public Sections getSections()
  { return sections; }
  /**
   * Gets the instantiated object User (the current user)
   * @return User uer
   */
  public User getUser()
  { return user; }
  /**
   * Gets the List of Users (current users for this database)
   * @return List<User> users
   */
  public List<User> getUsers()
  { return users; }
  /**
   * Determines whether one of the many mini-GUIs is visible (currently being used by the user)
   * @return true if one of the mini-GUIs is visible; false if none are
   */
  public boolean isAnyMiniGUIVisible()
  { return modifyInventoryItemGUI.isVisible() || sectionManagerGUI.isVisible() || backgroundImageGUI.isVisible() || sectionManagerMoreOptionsGUI.isVisible() || reportGUI.isVisible() || reportGUI.isPrintableReportGUIVisible() || userManagerGUI.isVisible(); }
  /**
   * Loads the background image
   */
  private void loadBackgroundImage()
  {
    BackgroundImageGUI.loadBackgroundImage(this);
  }
  /**
   * Paints the background color as well as image to the screen
   */
  public void paintComponent(final Graphics g)
  {
    super.paintComponent(g);
    Graphics2D g2d = (Graphics2D)g;
    g2d.setColor(Colors.getBackgroundColor());
    g2d.fillRect(0, 0, getWidth(), getHeight());
    g.drawImage(backgroundImage, 0, 0, null);
  }
  /**
   * Refreshes the Inventory Item Text Fields and checks the validity of the numbers
   * inside the number fields, and shows invalid numbers to the user if the settings
   * are set to do so (default is that they are)
   */
  public void refresh()
  {
    if (sections.getInventoryType()[currentSection] != currentDatabasePanel)
    {
      refreshDatabasePanel(sections.getInventoryType()[currentSection]);
      repaint();
    }
    rfAssist.selectAppropriatePageNumber();
    int i;
    for (i = 0; i < InventoryItemTextField.NUMBER_OF_II_TEXT_FIELDS; i++)
    {
      IITexts[i].setName("");
      IITexts[i].setLoosePrice("");
      IITexts[i].setCompletePrice("");
      IITexts[i].setNewPrice("");
      IITexts[i].setBox("");
      IITexts[i].setManual("");
      IITexts[i].setCondition("");
      IITexts[i].setNotes("");
      IITexts[i].setValue("");
      IITexts[i].getTextFieldName().setOpaque(false);
      IITexts[i].getTextFieldLoosePrice().setOpaque(false);
      IITexts[i].getTextFieldCompletePrice().setOpaque(false);
      IITexts[i].getTextFieldNewPrice().setOpaque(false);
      IITexts[i].getTextFieldBox().setOpaque(false);
      IITexts[i].getTextFieldManual().setOpaque(false);
      IITexts[i].getTextFieldCondition().setOpaque(false);
      IITexts[i].getTextFieldNotes().setOpaque(false);
      IITexts[i].getTextFieldValue().setOpaque(false);
    }
    int y = 0;
    if (inventoryItems.size() == 0) return;
    for (i = 0; i < InventoryItemTextField.NUMBER_OF_II_TEXT_FIELDS; i++)
    {
      y = i + InventoryItemTextField.NUMBER_OF_II_TEXT_FIELDS * currentPage;
      if (y < 0) return;
      if (y >= inventoryItems.get(currentSection).size()) break;
      IITexts[i].setName(inventoryItems.get(currentSection).get(y).getName());
      if (inventoryItems.get(currentSection).get(y).getInventoryType() == InventoryType.VIDEO_GAME)
      {
        IITexts[i].setLoosePrice(((VideoGame)inventoryItems.get(currentSection).get(y)).getLoosePrice());
        IITexts[i].setCompletePrice(((VideoGame)inventoryItems.get(currentSection).get(y)).getCompletePrice()); 
        IITexts[i].setNewPrice(((VideoGame)inventoryItems.get(currentSection).get(y)).getNewPrice());
        IITexts[i].setBox(((VideoGame)inventoryItems.get(currentSection).get(y)).getBox());
        IITexts[i].setManual(((VideoGame)inventoryItems.get(currentSection).get(y)).getManual());
      }
      IITexts[i].setCondition(inventoryItems.get(currentSection).get(y).getCondition());
      IITexts[i].setNotes(inventoryItems.get(currentSection).get(y).getNotes());
      IITexts[i].setValue(inventoryItems.get(currentSection).get(y).getValue());
      IITexts[i].getTextFieldLoosePrice().setBackground(null);
      IITexts[i].getTextFieldCompletePrice().setBackground(null);
      IITexts[i].getTextFieldNewPrice().setBackground(null);
      IITexts[i].getTextFieldValue().setBackground(null);
    }
    refreshComboBoxPage();
    Money.checkValiditiyOfNumbers(IITexts);
  }
  /**
   * Puts the correct page in the combo box that displays the page
   */
  private void refreshComboBoxPage()
  {
    comboBoxPage.removeActionListener(comboBoxPageActionListener);
    comboBoxPage.removeAllItems();
    for (int i = 0; i < (inventoryItems.get(currentSection).size() - 1) / InventoryItemTextField.NUMBER_OF_II_TEXT_FIELDS + 1; i++)
    {
      comboBoxPage.addItem((i + 1) + "");  
    }
    comboBoxPage.setSelectedIndex(currentPage);
    comboBoxPage.addActionListener(comboBoxPageActionListener);
  }
  /**
   * Refreshes the database panel depending on the inventory type.  If the inventory type is for
   * video games, there are more text fields in the panel than if it were for generic items.
   * @param inventoryType Refreshes the database panel according to the inventory type -
   * Video Game inventory type has more text fields, while Generic Item has less
   */
  private void refreshDatabasePanel(final InventoryType inventoryType)
  {
    databasePanel.removeAll();
    databasePanel.add(labelName);
    if (inventoryType == InventoryType.VIDEO_GAME)
    {
      databasePanel.add(labelLoose);
      databasePanel.add(labelComplete);
      databasePanel.add(labelNew);
      databasePanel.add(labelBox);
      databasePanel.add(labelManual);
    }
    databasePanel.add(labelCondition);
    databasePanel.add(labelNotes);
    databasePanel.add(labelValue);
    /*
     * These three phantom JLabels are meant to put invisible GUI components to
     * account for SpringUtilities makeCompactGrid method which requires a grid.
     * In this case, exsactly 31 x 12.  These three JLabels are to account for
     * the very first row of JLabels, above the three JButtons for delete,
     * modify, and duplicate.
     */
    for (int i = 0; i < PADDING_3; i++)
    {
      databasePanel.add(new JLabel());
    }
    for (int i = 0; i < InventoryItemTextField.NUMBER_OF_II_TEXT_FIELDS; i++)
    {
      databasePanel.add(IITexts[i].getTextFieldName());
      if (inventoryType == InventoryType.VIDEO_GAME)
      {
        databasePanel.add(IITexts[i].getTextFieldLoosePrice());
        databasePanel.add(IITexts[i].getTextFieldCompletePrice());
        databasePanel.add(IITexts[i].getTextFieldNewPrice());
        databasePanel.add(IITexts[i].getTextFieldBox());
        databasePanel.add(IITexts[i].getTextFieldManual());
      }
      databasePanel.add(IITexts[i].getTextFieldCondition());
      databasePanel.add(IITexts[i].getTextFieldNotes());
      databasePanel.add(IITexts[i].getTextFieldValue());
      databasePanel.add(IITexts[i].getButtonDelete());
      databasePanel.add(IITexts[i].getButtonModify());
      databasePanel.add(IITexts[i].getButtonDuplicate());
    }
    databasePanel.revalidate(); // Without this, there would be blank spots where "Loose Price", "Complete Price", "New Price", "Box", and "Manual" are
    SpringUtilities.makeCompactGrid(databasePanel, InventoryItemTextField.NUMBER_OF_II_TEXT_FIELDS + 1, inventoryType == InventoryType.VIDEO_GAME ? PADDING_12 : PADDING_7, 0, 0, 0, 0);
    currentDatabasePanel = sections.getInventoryType()[currentSection];
  }
  /**
   * Restore focus to main panel - restores focus to the main panel in Retro Fix
   */
  private void restoreFocusToMainPanel()
  {
    requestFocus();
  }
  /**
   * Sets the background image
   * @param image The image to make the background image
   */
  public void setBackgroundImage(final BufferedImage image)
  {
    backgroundImage = image;
  }
  /**
   * Sets the current mode
   * @param mode The mode to be the current mode
   */
  public void setCurrentMode(final Mode mode)
  { currentMode = mode; }
  /**
   * Sets the current page
   * @param page Sets the currentPage to page
   */
  public void setCurrentPage(final int page)
  { currentPage = page; }
  /**
   * Sets the current section to the specified index
   * @param section The index to set the current section to
   */
  public void setCurrentSection(final int section)
  { currentSection = section; }
  /**
   * Sets the inventory items
   * @param inv The new inventory items to be set
   */
  public void setInventoryItems(final List<ArrayList<InventoryItem>> inv)
  { inventoryItems = inv; }
  /**
   * Set main frame title and label “logged in as” - Adjusts the text in the title and
   * “logged in as” label to correspond to the currently loaded file and logged-in user
   */
  public void setMainFrameTitleAndLabelLoggedInAs()
  {
    String str = Texts.RETRO_FIX_INVENTORY_MANAGER.getText();
    if (databaseIO.isFileLoaded())
      str += Texts.CHARACTER_DASH.getText() + databaseIO.getFile().toString();
    mainFrame.setTitle(str);
    labelLoggedInAs.setText(Texts.TEXT_LOGGED_IN_AS.getText() + Texts.CHARACTER_APOSTROPHE.getText() + user.getName() + Texts.CHARACTER_APOSTROPHE.getText());
  }
  /**
   * Sets the number of pages that there are in this section
   * @param num the number of pages there are in this section
   */
  public void setNumberOfPages(final int num)
  { numberOfPages = num; }
  /**
   * Sets the current sections to equal the passed-in value
   * @param sect Sections sections object to be set to equal sect
   */
  public void setSections(final Sections sect)
  { sections = sect; }
  /**
   * Sets up the GUI components in Retro Fix.  This is called once when Retro Fix is loaded,
   * when the application first starts.
   */
  public void setupGUIComponents()
  {
    for (int i = 0; i < InventoryItemTextField.NUMBER_OF_II_TEXT_FIELDS; i++)
    {
      InventoryItemTextField ii = new InventoryItemTextField();
      IITexts[i] = ii;
    }
    rfAssist.passInRetroFix(this);
    mainFrame.setSize(Dimensions.RETRO_FIX.getWidth(), Dimensions.RETRO_FIX.getHeight());
    mainFrame.setLocationRelativeTo(null);
    mainFrame.add(this);
    setMainFrameTitleAndLabelLoggedInAs();
    mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    mainFrame.setJMenuBar(rfMenuBar);
    setLayout(new BorderLayout());
    comboBoxPage.addItem(Texts.CHARACTER_ONE.getText());
    for (int i = 0; i < Colors.TOTAL; i++)
    {
      rfMenuBar.getRadioButtonMenuItemBackgroundColor()[i].addActionListener(globalActionListener);
      rfMenuBar.getRadioButtonMenuItemTextColor()[i].addActionListener(globalActionListener);
      rfMenuBar.getRadioButtonMenuItemBorderColor()[i].addActionListener(globalActionListener);
    }
    modifyInventoryItemGUI.setRetroFixFrame(mainFrame);
    backgroundImageGUI.setRetroFixFrame(mainFrame);
    modifyInventoryItemGUI.setRetroFixFrame(mainFrame);
    passwordGUI.setRetroFixFrame(mainFrame);
    reportGUI.setRetroFixFrame(mainFrame);
    sectionManagerGUI.setRetroFixFrame(mainFrame);
    sectionManagerMoreOptionsGUI.setRetroFixFrame(mainFrame);
    userManagerGUI.setRetroFixFrame(mainFrame);
  }
  /**
   * Sets up listeners for the various GUI components.  This is also called once when
   * Retro Fix is first loaded.
   */
  public void setupListeners()
  {
    rfMenuBar.setupActionListeners(globalActionListener);
    modifyInventoryItemGUI.getButtonAccept().addActionListener(globalActionListener);
    sectionManagerGUI.getButtonAdd().addActionListener(globalActionListener);
    sectionManagerGUI.getButtonModify().addActionListener(globalActionListener);
    sectionManagerGUI.getButtonDelete().addActionListener(globalActionListener);
    sectionManagerGUI.getButtonClose().addActionListener(globalActionListener);
    sectionManagerGUI.getButtonMoreOptions().addActionListener(globalActionListener);
    sectionManagerMoreOptionsGUI.getButtonClose().addActionListener(globalActionListener);
    sectionManagerMoreOptionsGUI.getButtonApply().addActionListener(globalActionListener);
    sectionManagerMoreOptionsGUI.getComboBoxSections().addActionListener(globalActionListener);;
    backgroundImageGUI.getButtonAccept().addActionListener(globalActionListener);
    reportGUI.getButtonOkay().addActionListener(globalActionListener);
    databaseIO.getButtonAccept().addActionListener(globalActionListener);
    userManagerGUI.getButtonClose().addActionListener(globalActionListener);
    buttonAdd.addMouseListener(globalMouseListener);
    
    labelName.addMouseListener(globalMouseListener);
    labelLoose.addMouseListener(globalMouseListener);
    labelComplete.addMouseListener(globalMouseListener);
    labelNew.addMouseListener(globalMouseListener);
    labelBox.addMouseListener(globalMouseListener);
    labelManual.addMouseListener(globalMouseListener);
    labelCondition.addMouseListener(globalMouseListener);
    labelNotes.addMouseListener(globalMouseListener);
    labelValue.addMouseListener(globalMouseListener);
    
    buttonSectionReport.addMouseListener(globalMouseListener);
    buttonCompleteReport.addMouseListener(globalMouseListener);
    buttonSection.addActionListener(globalActionListener);
    for (int i = 0; i < InventoryItemTextField.NUMBER_OF_II_TEXT_FIELDS; i++)
    {
      IITexts[i].getButtonDelete().addMouseListener(globalMouseListener);
      IITexts[i].getButtonModify().addMouseListener(globalMouseListener);
      IITexts[i].getButtonDuplicate().addMouseListener(globalMouseListener);
    }
    comboBoxPage.addActionListener(comboBoxPageActionListener);
    comboBoxSections.addActionListener(globalActionListener);
  }
  /**
   * Sets up the panels used in RetroFix.  This is also called only once, when
   * Retro Fix first loads.
   */
  private void setupPanels()
  {
    databasePanel.setLayout(new SpringLayout());
    refreshDatabasePanel(InventoryType.VIDEO_GAME);
    databasePanel.setOpaque(false);
    lowerPanel.setOpaque(false);
    lowerLeftPanel.setOpaque(false);
    lowerRightPanel.setOpaque(false);
    add(databasePanel, BorderLayout.CENTER);
    lowerLeftPanel.add(buttonSection);
    lowerLeftPanel.add(comboBoxSections);
    lowerLeftPanel.add(labelPage);
    lowerLeftPanel.add(comboBoxPage);
    lowerLeftPanel.add(labelLoggedInAs);
    lowerPanel.setLayout(new BorderLayout());
    lowerPanel.add(lowerLeftPanel, BorderLayout.WEST);
    lowerRightPanel.add(buttonAdd);
    lowerRightPanel.add(buttonSectionReport);
    lowerRightPanel.add(buttonCompleteReport);
    lowerPanel.add(lowerRightPanel, BorderLayout.EAST);
    add(lowerPanel, BorderLayout.SOUTH);
  }
  /**
   * Sets the user and enables or disables various GUI components, depending
   * on their permissions
   * @param u The logged-in user of the database
   */
  public void setUser(final User u)
  { 
    user = u;
    buttonAdd.setEnabled(user.getCanAddItem());
    for (InventoryItemTextField ii : IITexts)
    {
      if (user.getCanAddItem())
        ii.getButtonDuplicate().setEnabled(true);
      else
        ii.getButtonDuplicate().setEnabled(false);
      if (user.getCanModifyItem())
        ii.getButtonModify().setEnabled(true);
      else
        ii.getButtonModify().setEnabled(false);
      if (user.getCanDeleteItem())
        ii.getButtonDelete().setEnabled(true);
      else
        ii.getButtonDelete().setEnabled(false);
    }
    sectionManagerGUI.getButtonAdd().setEnabled(user.getCanAddSection());
    sectionManagerGUI.getButtonModify().setEnabled(user.getCanModifySection());
    sectionManagerGUI.getButtonMoreOptions().setEnabled(user.getCanModifySection());
    sectionManagerGUI.getButtonDelete().setEnabled(user.getCanDeleteSection());
  }
  /**
   * Sets the users
   * @param u The list of all the users (User objects) of the database
   */
  public void setUsers(final ArrayList<User> u)
  { users = u; }
  /**
   * Called once from the Main class, in order to get rid of the warning that
   * the instantiated Retro Fix object is never used.  Calling this one method
   * gets rid of that warning.
   */
  public void start()
  {
    mainFrame.setVisible(true);
  }
  /**
   * This listens in to only one object - the “Page” combo box
   * @author Eric Sweeten
   */
  private class ComboBoxPageActionListener implements ActionListener
  {
    @Override
    public void actionPerformed(ActionEvent arg0)
    {
      currentMode = Mode.FLIP_PAGE;
      currentPage = comboBoxPage.getSelectedIndex();
      refresh();
    }
  }
  
  /**
   * Listens in for all the GUI components that have globalActionListener added
   * to them as their action listener
   * @author Eric Sweeten
   */
  private class GlobalActionListener implements ActionListener
  {
    @Override
    public void actionPerformed(final ActionEvent arg0)
    {
      final Object obj = arg0.getSource();
      checkForAcceptButton(obj);
      checkForOtherActions(obj);
      if (obj == sectionManagerGUI.getButtonAdd())
      {
        if (!Mode.checkIfDuplicateOrBlankName(sectionManagerGUI.getAddedSection(), sections.getSectionNames()))
        {
          inventoryItems.add(new ArrayList<InventoryItem>());
          String newSections[] = new String[sections.getSectionNames().length + 1];
          InventoryType[] newIT = new InventoryType[sections.getInventoryType().length + 1];
          int i;
          for (i = 0; i < sections.getSectionNames().length; i++)
          {
            newSections[i] = sections.getSectionNames()[i];
            newIT[i] = sections.getInventoryType()[i];
          }
          newSections[i] = sectionManagerGUI.getAddedSection();
          newIT[i] = InventoryType.VIDEO_GAME;
          sections.reinitialize(newSections.length, newIT.length);
          for (i = 0; i < sections.getSectionNames().length; i++)
          {
            sections.getSectionNames()[i] = newSections[i];
            sections.getInventoryType()[i] = newIT[i];
          }
          currentMode = Mode.ADD_SECTION;
          rfAssist.refreshSections();
        }
        restoreFocusToMainPanel();
        sectionManagerGUI.makeVisible(false);
      }
      else if (obj == sectionManagerGUI.getButtonModify())
      {
        String newName = JOptionPane.showInputDialog(null, Texts.MESSAGE_NEW_SECTION_NAME.getText());
        if (!Mode.checkIfDuplicateOrBlankName(newName, sections.getSectionNames())) sections.getSectionNames()[sectionManagerGUI.getModifyIndex()] = newName;
        restoreFocusToMainPanel();
        sectionManagerGUI.makeVisible(false);
        currentMode = Mode.MODIFY_SECTION;
        rfAssist.refreshSections();
      }
      else if (obj == sectionManagerGUI.getButtonDelete())
      {
        if (inventoryItems.size() == 1)
        {
          JOptionPane.showMessageDialog(null, Texts.ERROR_AT_LEAST_ONE_PAGE.getText());
          return;
        }
        inventoryItems.remove(sectionManagerGUI.getDeletedIndex());
        String newSections[] = new String[sections.getSectionNames().length - 1];
        InventoryType newIT[] = new InventoryType[sections.getSectionNames().length - 1];
        int i;
        for (i = 0; i < sections.getSectionNames().length; i++)
        {
          if (i == sectionManagerGUI.getDeletedIndex()) break;
          newSections[i] = sections.getSectionNames()[i];
          newIT[i] = sections.getInventoryType()[i];
        }
        for ( ; i < sections.getSectionNames().length - 1; i++)
        {
          newSections[i] = sections.getSectionNames()[i + 1];
          newIT[i] = sections.getInventoryType()[i + 1];
        }
        sections.reinitialize(newSections.length, newIT.length);
        for (i = 0; i < newSections.length; i++)
        {
          sections.getSectionNames()[i] = newSections[i];
          sections.getInventoryType()[i] = newIT[i];
        }
        sectionManagerGUI.makeVisible(false);
        restoreFocusToMainPanel();
        currentMode = Mode.DELETE_SECTION;
        rfAssist.refreshSections();
      }
      else if (obj == sectionManagerGUI.getButtonClose())
      {
        sectionManagerGUI.makeVisible(false);
        restoreFocusToMainPanel();
      }
      else if (obj == sectionManagerGUI.getButtonMoreOptions())
      {
        sectionManagerGUI.makeVisible(false);
        sectionManagerMoreOptionsGUI.updateSections(sections.getSectionNames());
        sectionManagerMoreOptionsGUI.makeVisible(true);
      }
      else if (obj == sectionManagerMoreOptionsGUI.getComboBoxSections())
      {
        if (sectionManagerMoreOptionsGUI.getComboBoxSections().getSelectedIndex() > -1)
        {
          if (sections.getInventoryType()[sectionManagerMoreOptionsGUI.getComboBoxSections().getSelectedIndex()] == InventoryType.VIDEO_GAME)
            sectionManagerMoreOptionsGUI.getRadioButtonVideoGame().doClick();
          else
            sectionManagerMoreOptionsGUI.getRadioButtonOther().doClick();
        }
      }
      else if (obj == sectionManagerMoreOptionsGUI.getButtonClose())
      {
        sectionManagerMoreOptionsGUI.makeVisible(false);
        restoreFocusToMainPanel();
      }
      else if (obj == sectionManagerMoreOptionsGUI.getButtonApply())
      {
        InventoryType it = sectionManagerMoreOptionsGUI.getRadioButtonVideoGame().isSelected() ? InventoryType.VIDEO_GAME : InventoryType.GENERIC_ITEM;
        sections.getInventoryType()[sectionManagerMoreOptionsGUI.getComboBoxSections().getSelectedIndex()] = it;
        /*
         * This ensures that when a VideoGame database is converted into a GenericItem
         * database, the values do not restore when it is converted back.  That
         * can cause some problems.  Also, if a database is GenericType and is
         * converted to VideoGame, the values in these fields will not populate
         * with values that don't belong.  For example, if you create a generic
         * inventory item called "Space Invaders arcade machine" with notes "arcade
         * machine from the 80's", and you want to convert this database from
         * GenericType to VideoGame, it will not populate with potential old values
         * specific for VideoGame.
         */
        Item tempItem;
        if (it == InventoryType.GENERIC_ITEM)
        {
          for (InventoryItem inv : inventoryItems.get(sectionManagerMoreOptionsGUI.getComboBoxSections().getSelectedIndex()))
          {
            if (inv.getInventoryType() == InventoryType.VIDEO_GAME)
            {
              (((VideoGame)((InventoryItem)inv))).setLoosePrice("");
              (((VideoGame)((InventoryItem)inv))).setCompletePrice("");
              (((VideoGame)((InventoryItem)inv))).setNewPrice("");
              (((VideoGame)((InventoryItem)inv))).setBox("");
              (((VideoGame)((InventoryItem)inv))).setManual("");
            }
            tempItem = new Item();
            tempItem.setName(inv.getName());
            tempItem.setCondition(inv.getCondition());
            tempItem.setNotes(inv.getNotes());
            tempItem.setValue(inv.getValue());
            tempItem.setInventoryType(InventoryType.GENERIC_ITEM);
            inv = tempItem;
          }
        }
        else
        {
          VideoGame tempVideoGame;
          for (InventoryItem inv : inventoryItems.get(sectionManagerMoreOptionsGUI.getComboBoxSections().getSelectedIndex()))
          {
            tempVideoGame = new VideoGame();
            tempVideoGame.setName(inv.getName());
            tempVideoGame.setCondition(inv.getCondition());
            tempVideoGame.setNotes(inv.getNotes());
            tempVideoGame.setValue(inv.getValue());
            tempVideoGame.setInventoryType(InventoryType.VIDEO_GAME);
            inv = tempVideoGame;
          }
        }
        sectionManagerMoreOptionsGUI.makeVisible(false);
        restoreFocusToMainPanel();
        refresh(); //might be necessary if refreshing current one replaces to new kind
      }
      else if (obj == backgroundImageGUI.getButtonAccept())
      {
        Settings.setShouldLoadImage(backgroundImageGUI.getRadioButtonFile().isSelected());
        Settings.saveSettingsFile();
        loadBackgroundImage();
        backgroundImageGUI.makeVisible(false);
        restoreFocusToMainPanel();
      }
      else if (obj == reportGUI.getButtonOkay())
      {
        reportGUI.makeVisible(false);
      }
      else if (obj == databaseIO.getButtonAccept())
      {
        databaseIO.load();
        if (user.getName().equals(Texts.TEXT_ADMIN.getText()))
          rfMenuBar.getMenuItemManage().setEnabled(true);
        else
          rfMenuBar.getMenuItemManage().setEnabled(false);
        setMainFrameTitleAndLabelLoggedInAs();
        refresh();
        rfAssist.refreshSections();
      }
      else if (obj == userManagerGUI.getButtonClose())
      {
        userManagerGUI.makeVisible(false);
      }
      else if (obj == rfMenuBar.getMenuItemBackgroundImage())
      {
        if (Settings.shouldLoadImage())
          backgroundImageGUI.getRadioButtonFile().doClick();
        else
          backgroundImageGUI.getRadioButtonNone().doClick();
        backgroundImageGUI.getTextFieldFile().setText(Settings.getImageFile().toString());
        backgroundImageGUI.makeVisible(true);
      }
      else if (obj == rfMenuBar.getMenuItemCheckBoxShowInvalidNumbers())
      {
        Settings.setShouldShowInvalidNumbers(!Settings.shouldShowInvalidNumbers()); 
        Settings.saveSettingsFile();
        refresh();
      }
      else if (obj == rfMenuBar.getMenuItemRestoreDefaults())
      {
        if (JOptionPane.showConfirmDialog(mainFrame, Texts.MESSAGE_RESTORE_DEFAULTS.getText()) == JOptionPane.YES_OPTION)
        {
          rfMenuBar.getRadioButtonMenuItemBackgroundColor()[Colors.BLACK.getIndex()].doClick();
          rfMenuBar.getRadioButtonMenuItemTextColor()[Colors.WHITE.getIndex()].doClick();
          rfMenuBar.getRadioButtonMenuItemBorderColor()[Colors.WHITE.getIndex()].doClick();
          Settings.resetImageFile();
          backgroundImageGUI.getRadioButtonNone().doClick();
          backgroundImageGUI.getButtonAccept().doClick();
          Settings.setTabSizeOverall(Settings.DEFAULT_TAB_SIZE_OVERALL);
          Settings.setTabSizeSection(Settings.DEFAULT_TAB_SIZE_SECTION);
          repaint();
        }
      }
      else if (obj == rfMenuBar.getMenuItemManage())
      {
        userManagerGUI.makeVisible(true);
      }
      else if (obj == rfMenuBar.getMenuItemLogout())
      {
        rfAssist.closeOrNew(true);
      }
    }
  }
  /**
   * This listens in to mouse clicks on various GUI components which are assigned a mouse listener
   * @author Eric Sweeten
   */
  private class GlobalMouseListener implements MouseListener
  {
    @Override
    public void mouseClicked(final MouseEvent arg0)
    {
      rfAssist.checkForIITextFieldButtons(arg0.getSource());
      if (isAnyMiniGUIVisible()) return;
      if (buttonAdd.equals(arg0.getSource()))
      {
        currentMode = Mode.ADD;
        modifyInventoryItemGUI.setIndex(inventoryItems.get(currentSection).size());
        modifyInventoryItemGUI.setTitle(Texts.BUTTON_ADD.getText());
        modifyInventoryItemGUI.setInventoryType(sections.getInventoryType()[currentSection]);
        modifyInventoryItemGUI.makeVisible(true);
      }
      else if (arg0.getSource() == labelName || arg0.getSource() == labelLoose || arg0.getSource() == labelComplete ||
          arg0.getSource() == labelNew || arg0.getSource() == labelBox || arg0.getSource() == labelManual ||
          arg0.getSource() == labelCondition || arg0.getSource() == labelNotes || arg0.getSource() == labelValue)
      {
        if (!user.getCanOrder()) return;
        InventoryItem tempInventoryItem;
        String s1, s2;
        boolean isVideoGame;
        boolean compare = false;
        for (int i = 0; i < inventoryItems.get(currentSection).size() - 1; i++)
        {
          for (int j = 1; j < inventoryItems.get(currentSection).size() - i; j++)
          {
            isVideoGame = inventoryItems.get(currentSection).get(j).getInventoryType() == InventoryType.VIDEO_GAME;
            if (arg0.getSource() == labelName)
            {
              s1 = inventoryItems.get(currentSection).get(j - 1).getName();
              s2 = inventoryItems.get(currentSection).get(j).getName();
              if (organizer.getName())
                compare = s1.compareToIgnoreCase(s2) > 0;
              else
                compare = s1.compareToIgnoreCase(s2) < 0;
            }
            else if (arg0.getSource() == labelLoose && isVideoGame)
            {
              s1 = ((VideoGame)inventoryItems.get(currentSection).get(j - 1)).getLoosePrice();
              s2 = ((VideoGame)inventoryItems.get(currentSection).get(j)).getLoosePrice();
              if (organizer.getLoosePrice())
                compare = InventoryItemTextField.getCompare(s1, s2, true);
              else
                compare = InventoryItemTextField.getCompare(s1, s2, false);
            }
            else if (arg0.getSource() == labelComplete && isVideoGame)
            {
              s1 = ((VideoGame)inventoryItems.get(currentSection).get(j - 1)).getCompletePrice();
              s2 = ((VideoGame)inventoryItems.get(currentSection).get(j)).getCompletePrice();
              if (organizer.getCompletePrice())
                compare = InventoryItemTextField.getCompare(s1, s2, true);
              else
                compare = InventoryItemTextField.getCompare(s1, s2, false);
            }
            else if (arg0.getSource() == labelNew && isVideoGame)
            {
              s1 = ((VideoGame)inventoryItems.get(currentSection).get(j - 1)).getNewPrice();
              s2 = ((VideoGame)inventoryItems.get(currentSection).get(j)).getNewPrice();
              if (organizer.getNewPrice())
                compare = InventoryItemTextField.getCompare(s1, s2, true);
              else
                compare = InventoryItemTextField.getCompare(s1, s2, false);
            }
            else if (arg0.getSource() == labelBox && isVideoGame)
            {
              s1 = ((VideoGame)inventoryItems.get(currentSection).get(j - 1)).getBox();
              s2 = ((VideoGame)inventoryItems.get(currentSection).get(j)).getBox();
              if (organizer.getBox())
                compare = s1.compareToIgnoreCase(s2) > 0;
              else
                compare = s1.compareToIgnoreCase(s2) < 0;
            }
            else if (arg0.getSource() == labelManual && isVideoGame)
            {
              s1 = ((VideoGame)inventoryItems.get(currentSection).get(j - 1)).getManual();
              s2 = ((VideoGame)inventoryItems.get(currentSection).get(j)).getManual();
              if (organizer.getManual())
                compare = s1.compareToIgnoreCase(s2) > 0;
              else
                compare = s1.compareToIgnoreCase(s2) < 0;
            }
            else if (arg0.getSource() == labelCondition)
            {
              s1 = inventoryItems.get(currentSection).get(j - 1).getCondition();
              s2 = inventoryItems.get(currentSection).get(j).getCondition();
              if (organizer.getCondition())
                compare = s1.compareToIgnoreCase(s2) > 0;
              else
                compare = s1.compareToIgnoreCase(s2) < 0;
            }
            else if (arg0.getSource() == labelNotes)
            {
              s1 = inventoryItems.get(currentSection).get(j - 1).getNotes();
              s2 = inventoryItems.get(currentSection).get(j).getNotes();
              if (organizer.getNotes())
                compare = s1.compareToIgnoreCase(s2) > 0;
              else
                compare = s1.compareToIgnoreCase(s2) < 0;
            }
            else if (arg0.getSource() == labelValue)
            {
              s1 = inventoryItems.get(currentSection).get(j - 1).getValue();
              s2 = inventoryItems.get(currentSection).get(j).getValue();
              if (organizer.getValue())
                compare = InventoryItemTextField.getCompare(s1, s2, true);
              else
                compare = InventoryItemTextField.getCompare(s1, s2, false);
            }
            if (compare)
            {
              tempInventoryItem = inventoryItems.get(currentSection).get(j - 1);
              inventoryItems.get(currentSection).set(j - 1, inventoryItems.get(currentSection).get(j));
              inventoryItems.get(currentSection).set(j, tempInventoryItem);
            }
          }
        }
        if (arg0.getSource() == labelName) organizer.toggleName();
        else if (arg0.getSource() == labelLoose) organizer.toggleLoosePrice();
        else if (arg0.getSource() == labelComplete) organizer.toggleCompletePrice();
        else if (arg0.getSource() == labelNew) organizer.toggleNewPrice();
        else if (arg0.getSource() == labelBox) organizer.toggleBox();
        else if (arg0.getSource() == labelManual) organizer.toggleManual();
        else if (arg0.getSource() == labelCondition) organizer.toggleCondition();
        else if (arg0.getSource() == labelNotes) organizer.toggleNotes();
        else if (arg0.getSource() == labelValue) organizer.toggleValue();
        refresh();
      }
      else if (arg0.getSource() == buttonSectionReport)
      {
        reportGUI.setReportType(ReportType.SECTION);
        reportGUI.makeVisible(true);
      }
      else if (arg0.getSource() == buttonCompleteReport)
      {
        reportGUI.setReportType(ReportType.OVERALL);
        reportGUI.makeVisible(true);
      }
    }
    @Override
    public void mouseEntered(final MouseEvent arg0)
    { }
    @Override
    public void mouseExited(final MouseEvent arg0)
    { }
    @Override
    public void mousePressed(final MouseEvent arg0)
    { }
    @Override
    public void mouseReleased(final MouseEvent arg0)
    { }
  }
}
