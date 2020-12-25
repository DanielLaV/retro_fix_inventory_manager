package gui;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JTextField;

import tools.SpringUtilities;
import enums.Dimensions;
import enums.Texts;

/**
 * The Section Manager GUI is where the user can add, delete, or modify sections by name
 * @author Kevin Powell, Eric Sweeten
 */
public class SectionManagerGUI extends AbstractMiniGUI
{
  /**
   * The button for "Add"
   */
  private final JButton buttonAdd = new JButton(Texts.BUTTON_ADD.getText());
  /**
   * The text field to add a section by name
   */
  private final JTextField textFieldAdd = new JTextField();
  /**
   * The button for "Delete"
   */
  private final JButton buttonDelete = new JButton(Texts.BUTTON_DELETE.getText());
  /**
   * The combo box which holds all the section names, for the sake of deletion
   */
  private final JComboBox<String> comboBoxSectionsDelete = new JComboBox<String>();
  /**
   * The button for "Modify"
   */
  private final JButton buttonModify = new JButton(Texts.BUTTON_MODIFY.getText());
  /**
   * The combo box which holds all the section names, for the sake of modifying them
   */
  private final JComboBox<String> comboBoxSectionsModify = new JComboBox<String>();
  /**
   * The button for "Close"
   */
  private final JButton buttonClose = new JButton(Texts.BUTTON_CLOSE.getText());
  /**
   * The button for "More Options"
   */
  private final JButton buttonMoreOptions = new JButton(Texts.BUTTON_MORE_OPTIONS.getText());
  /**
   * The constructor for SectionManagerGUI.  Sets up the frame and panel.
   */
  public SectionManagerGUI()
  {
    frame.setTitle(Texts.FRAME_TITLE_SECTION_MANAGER.getText());
    panel.add(buttonAdd);
    panel.add(textFieldAdd);
    panel.add(buttonDelete);
    panel.add(comboBoxSectionsDelete);
    panel.add(buttonModify);
    panel.add(comboBoxSectionsModify);
    panel.add(buttonClose);
    panel.add(buttonMoreOptions);
    frame.getRootPane().setDefaultButton(buttonClose);
    frame.setSize(Dimensions.SECTION_MANAGER_GUI.getWidth(), Dimensions.SECTION_MANAGER_GUI.getHeight());
    SpringUtilities.makeCompactGrid(panel, ReportGUI.PADDING_4, BackgroundImageGUI.PADDING_2, BackgroundImageGUI.PADDING_5, BackgroundImageGUI.PADDING_5, BackgroundImageGUI.PADDING_5, BackgroundImageGUI.PADDING_5);
  }
  /**
   * Gets the added section name
   * @return the added sectin name in textFieldAdd
   */
  public String getAddedSection()
  {
    return textFieldAdd.getText();
  }
  /**
   * Gets the button "Add"
   * @return JButton buttonAdd
   */
  public JButton getButtonAdd()
  {
    return buttonAdd;
  }
  /**
   * Gets the button "Close"
   * @return JButton buttonClose
   */
  public JButton getButtonClose()
  {
    return buttonClose;
  }
  /**
   * Gets the button "Delete"
   * @return JButton buttonDelete
   */
  public JButton getButtonDelete()
  {
    return buttonDelete;
  }
  /**
   * Gets the button "Modify"
   * @return JButton buttonModify
   */
  public JButton getButtonModify()
  {
    return buttonModify;
  }
  /**
   * Gets the button "More Options"
   * @return JButton buttonMoreOptions
   */
  public JButton getButtonMoreOptions()
  {
    return buttonMoreOptions;
  }
  /**
   * Gets the deleted index
   * @return the deleted section's index
   */
  public int getDeletedIndex()
  {
    return comboBoxSectionsDelete.getSelectedIndex();
  }
  /**
   * Gets the modified section's index
   * @return the modified section's index
   */
  public int getModifyIndex()
  {
    return comboBoxSectionsModify.getSelectedIndex();
  }
  /**
   * Makes the frame visible or not, and does a couple things associated with the
   * requested level of visibility.
   * @param bool true to make the frame visible; false to make it invisible
   */
  public void makeVisible(final boolean bool)
  {
    if (bool)
    {
      readjustLocation();
      textFieldAdd.requestFocus();
    }
    if (!bool)
    {
      textFieldAdd.setText("");
    }
    frame.setVisible(bool);
  }
  /**
   * Updates the combo boxes for delete and modify to reflect the new Sections
   * @param sections the names of the sections in an array
   */
  public void updateSections(final String[] sections)
  {
    comboBoxSectionsDelete.removeAllItems();
    comboBoxSectionsModify.removeAllItems();
    for (String str : sections)
    {
      comboBoxSectionsDelete.addItem(str);
      comboBoxSectionsModify.addItem(str);
    }
  }
}
