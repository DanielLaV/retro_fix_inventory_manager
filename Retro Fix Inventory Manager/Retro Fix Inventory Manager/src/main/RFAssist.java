package main;

import inventory.InventoryItem;
import inventory.Item;
import inventory.VideoGame;

import java.util.ArrayList;

import javax.swing.JOptionPane;

import tools.User;
import enums.InventoryType;
import enums.Mode;
import enums.Texts;
import gui.InventoryItemTextField;

/**
 * The purpose of this class is to reduce the number of lines of code in the class RetroFix,
 * because it was getting too long.
 * @author Eric Sweeten
 */
public class RFAssist
{
  /**
   * The "Condition" combo box index for "new"
   */
  private static final int NEW = 1;
  /**
   * The "Condition" combo box index for "mint"
   */
  private static final int MINT = 2;
  /**
   * The "Condition" combo box index for "good"
   */
  private static final int GOOD = 3;
  /**
   * The "Condition" combo box index for "fair"
   */
  private static final int FAIR = 4;
  /**
   * The "Condition" combo box index for "poor"
   */
  private static final int POOR = 5;
  /**
   * The one and only main Retro Fix object that will be passed in
   */
  private RetroFix retroFix;
  /**
   * Checks to see if any of the Inventory Item Text Field buttons were clicked
   * @param obj The GUI component that was clicked by the user
   */
  public void checkForIITextFieldButtons(final Object obj)
  {
    if (retroFix.isAnyMiniGUIVisible()) return;
    for (int x = 0; x < InventoryItemTextField.NUMBER_OF_II_TEXT_FIELDS; x++)
    {
      if (retroFix.getIITexts()[x].getButtonModify().equals(obj) && !(retroFix.getIITexts()[x].getTextFieldName().getText().isEmpty()))
      {
        retroFix.setCurrentMode(Mode.MODIFY);
        retroFix.getModifyInventoryItemGUI().setIndex(x);
        retroFix.getModifyInventoryItemGUI().getTextFieldName().setText(retroFix.getIITexts()[x].getTextFieldName().getText());
        retroFix.getModifyInventoryItemGUI().getTextFieldLoosePrice().setText(retroFix.getIITexts()[x].getTextFieldLoosePrice().getText());
        retroFix.getModifyInventoryItemGUI().getTextFieldCompletePrice().setText(retroFix.getIITexts()[x].getTextFieldCompletePrice().getText());
        retroFix.getModifyInventoryItemGUI().getTextFieldNewPrice().setText(retroFix.getIITexts()[x].getTextFieldNewPrice().getText());
        String s = retroFix.getIITexts()[x].getTextFieldCondition().getText();
        int i = 0;
        if (s.equals(Texts.CONDITION_NEW.getText())) i = NEW;
        else if (s.equals(Texts.CONDITION_MINT.getText())) i = MINT;
        else if (s.equals(Texts.CONDITION_GOOD.getText())) i = GOOD;
        else if (s.equals(Texts.CONDITION_FAIR.getText())) i = FAIR;
        else if (s.equals(Texts.CONDITION_POOR.getText())) i = POOR;
        retroFix.getModifyInventoryItemGUI().getComboBoxCondition().setSelectedIndex(i);
        s = retroFix.getIITexts()[x].getTextFieldBox().getText();
        i = Texts.isBlankYesOrNo(s);
        retroFix.getModifyInventoryItemGUI().getComboBoxBox().setSelectedIndex(i);
        s = retroFix.getIITexts()[x].getTextFieldManual().getText();
        i = Texts.isBlankYesOrNo(s);
        retroFix.getModifyInventoryItemGUI().getComboBoxManual().setSelectedIndex(i);
        retroFix.getModifyInventoryItemGUI().getTextFieldNotes().setText(retroFix.getIITexts()[x].getTextFieldNotes().getText());
        retroFix.getModifyInventoryItemGUI().getTextFieldValue().setText(retroFix.getIITexts()[x].getTextFieldValue().getText());
        retroFix.getModifyInventoryItemGUI().setTitle(Texts.BUTTON_MODIFY.getText());
        retroFix.getModifyInventoryItemGUI().setInventoryType(retroFix.getSections().getInventoryType()[retroFix.getCurrentSection()]);
        retroFix.getModifyInventoryItemGUI().makeVisible(true);
      }
      else if (retroFix.getIITexts()[x].getButtonDuplicate().equals(obj))
      {
        retroFix.setCurrentMode(Mode.DUPLICATE);
        InventoryItem newItem;
        if (retroFix.getSections().getInventoryType()[retroFix.getCurrentSection()] == InventoryType.VIDEO_GAME)
          newItem = new VideoGame();
        else
          newItem = new Item();
        //int i = x * (comboBoxPages.getSelectedIndex() + 1);
        if (!retroFix.getIITexts()[x].getTextFieldName().getText().isEmpty())
        {
          newItem.setName(retroFix.getIITexts()[x].getTextFieldName().getText());
          if (newItem.getInventoryType() == InventoryType.VIDEO_GAME)
          {
            ((VideoGame)newItem).setLoosePrice(retroFix.getIITexts()[x].getTextFieldLoosePrice().getText());
            ((VideoGame)newItem).setCompletePrice(retroFix.getIITexts()[x].getTextFieldCompletePrice().getText());
            ((VideoGame)newItem).setNewPrice(retroFix.getIITexts()[x].getTextFieldNewPrice().getText());
            ((VideoGame)newItem).setBox(retroFix.getIITexts()[x].getTextFieldBox().getText());
            ((VideoGame)newItem).setManual(retroFix.getIITexts()[x].getTextFieldManual().getText());  
          }
          newItem.setCondition(retroFix.getIITexts()[x].getTextFieldCondition().getText());
          newItem.setNotes(retroFix.getIITexts()[x].getTextFieldNotes().getText());
          newItem.setValue(retroFix.getIITexts()[x].getTextFieldValue().getText());
          retroFix.getInventoryItems().get(retroFix.getCurrentSection()).add(newItem);
          retroFix.refresh();
        }
      }
      else if (retroFix.getIITexts()[x].getButtonDelete().equals(obj))
      {
        int i = x + retroFix.getCurrentPage() * InventoryItemTextField.NUMBER_OF_II_TEXT_FIELDS;
        if (i < retroFix.getInventoryItems().get(retroFix.getCurrentSection()).size())
        {
          retroFix.setCurrentMode(Mode.DELETE);
          if (!retroFix.getInventoryItems().get(retroFix.getCurrentSection()).get(i).getName().isEmpty())
          {
            retroFix.getInventoryItems().get(retroFix.getCurrentSection()).remove(i);
            retroFix.refresh();
          }
        }
      }
    }
  }
  /**
   * Prompts the user if they're sure they want to either close the database or start a new
   * database, depending on which menu item they clicked on.  Then it sets up a new database
   * if they click "Yes".
   * @param close true if they clicked to close a database; false if they clicked to start
   * a new one
   */
  public void closeOrNew(final boolean close)
  {
    if (JOptionPane.showConfirmDialog(retroFix.getMainFrame(), close ? Texts.MESSAGE_CLOSE.getText() : Texts.MESSAGE_NEW.getText()) == JOptionPane.YES_OPTION)
    {
      retroFix.getDatabaseIO().close();
      retroFix.getSections().clear();
      retroFix.getInventoryItems().clear();
      retroFix.getInventoryItems().add(new ArrayList<InventoryItem>());
      retroFix.getUsers().clear();
      retroFix.setCurrentSection(0);
      retroFix.getMainFrame().setTitle(Texts.RETRO_FIX_INVENTORY_MANAGER.getText());
      User u = new User(Texts.TEXT_ADMIN.getText());
      retroFix.setUser(u);
      retroFix.getUsers().add(u);
      retroFix.setMainFrameTitleAndLabelLoggedInAs();
      refreshSections();
      retroFix.refresh();
    }
  }
  /**
   * Passes in the main Retro Fix object
   * @param retro The one and only instance of Retro Fix class in the program
   */
  public void passInRetroFix(final RetroFix retro)
  {
    retroFix = retro;
    start();
  }
  /**
   * Alphabetizes the sections, then refreshes the combo box showing them
   */
  public void refreshSections()
  {
    String cur = (String)retroFix.getComboBoxSections().getSelectedItem();
    retroFix.getSections().alphabetize();
    /*
     * Refills the combo box displaying the sections
     */
    retroFix.getComboBoxSections().removeAllItems();
    for (int i = 0; i < retroFix.getSections().getSectionNames().length; i++)
    {
      retroFix.getComboBoxSections().addItem(retroFix.getSections().getSectionNames()[i]);
      if (cur == retroFix.getSections().getSectionNames()[i])
        retroFix.getComboBoxSections().setSelectedIndex(i);
    }
    /*
     * When a section is added, switch to the new section.
     */
    if (retroFix.getCurrentMode() == Mode.ADD_SECTION)
    {
      for (int i = 0; i < retroFix.getSections().getSectionNames().length; i++)
      {
        if (retroFix.getSections().getSectionNames()[i].equals(retroFix.getSectionManagerGUI().getAddedSection()))
        {
          retroFix.getComboBoxSections().setSelectedIndex(i);
          retroFix.setCurrentSection(i);
        }
      }
      retroFix.getComboBoxPage().setSelectedIndex(0);
      retroFix.refresh();
    }
  }
  /**
   * Selects the appropriate page number
   */
  public void selectAppropriatePageNumber()
  {
    retroFix.setCurrentPage(Integer.parseInt(retroFix.getComboBoxPage().getSelectedItem().toString()) - 1);
    /*
     * This long if statement accounts for the special case when you delete the
     * last entry showing on a page.  When that happens, the page must change
     * to the page before it.
     */
    if (retroFix.getCurrentMode() == Mode.DELETE &&
        retroFix.getInventoryItems().get(retroFix.getCurrentSection()).size() % InventoryItemTextField.NUMBER_OF_II_TEXT_FIELDS == 0 &&
        retroFix.getCurrentPage() == retroFix.getNumberOfPages() - 1)
    {
      retroFix.setCurrentPage(retroFix.getNumberOfPages() - 2);
      if (retroFix.getCurrentPage() < 0) retroFix.setCurrentPage(0); //prevents an error if you add just one entry to a blank page, then delete it and try and delete again
      retroFix.getComboBoxPage().setSelectedIndex(retroFix.getCurrentPage());
    }
    if (retroFix.getInventoryItems().size() == 0) return;
    retroFix.setNumberOfPages((int)((retroFix.getInventoryItems().get(retroFix.getCurrentSection()).size() - 1) / InventoryItemTextField.NUMBER_OF_II_TEXT_FIELDS + 1));
    if (retroFix.getCurrentMode() != Mode.MODIFY && retroFix.getCurrentMode() != Mode.DELETE && retroFix.getCurrentMode() != Mode.FLIP_PAGE)
    {
      if (retroFix.getInventoryItems().get(retroFix.getCurrentSection()).size() > (retroFix.getCurrentPage() + 1) * InventoryItemTextField.NUMBER_OF_II_TEXT_FIELDS)
      {
        retroFix.getComboBoxPage().setSelectedIndex(retroFix.getComboBoxPage().getItemCount() - 1);
        retroFix.setCurrentPage(retroFix.getNumberOfPages() - 1);
      }
    }
  }
  /**
   * Helper method called after passInRetroFix is called.  This sets up a couple items in
   * the main Retro Fix class, such as setting up an administrator user for the new database,
   * and setting up an empty list of inventory items
   */
  private void start()
  {
    retroFix.setNumberOfPages(1);
    retroFix.getInventoryItems().add(new ArrayList<InventoryItem>());
    User u = new User(Texts.TEXT_ADMIN.getText());
    retroFix.setUser(u);
    retroFix.getUsers().add(retroFix.getUser());
  }
}
