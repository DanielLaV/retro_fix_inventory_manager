package gui;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JRadioButton;

import main.RetroFix;
import tools.SpringUtilities;
import enums.Dimensions;
import enums.Texts;

/**
 * The mini-GUI accessed through SectionManagerGUI's "More Options" button when clicked.  This GUI
 * is all about converting the inventory types of each section, from GENERIC_ITEM to VIDEO_GAME or
 * vice-versa.
 * @author Kevin Powell, Eric Sweeten
 */
public class SectionManagerMoreOptionsGUI extends AbstractMiniGUI
{
  /**
   * The button for "Close"
   */
  private final JButton buttonClose = new JButton(Texts.BUTTON_CLOSE.getText());
  /**
   * The button for "Apply"
   */
  private final JButton buttonApply = new JButton(Texts.BUTTON_APPLY.getText());
  /**
   * The button group for the radio buttons "Video Game" and "Other"
   */
  private final ButtonGroup buttonGroup = new ButtonGroup();
  /**
   * The radio button for "Video Game"
   */
  private final JRadioButton radioButtonVideoGame = new JRadioButton(Texts.RADIO_BUTTON_VIDEO_GAME.getText());
  /**
   * The radio button for "Other"
   */
  private final JRadioButton radioButtonOther = new JRadioButton(Texts.RADIO_BUTTON_OTHER.getText());
  /**
   * The combo box displaying the sections
   */
  private final JComboBox<String> comboBoxSections = new JComboBox<String>();
  /**
   * The constructor for this class, which sets up the GUI components
   */
  public SectionManagerMoreOptionsGUI()
  {
    frame.setTitle(Texts.FRAME_TITLE_SECTION_MANAGER_MORE_OPTIONS.getText());
    frame.getRootPane().setDefaultButton(buttonClose);
    frame.setSize(Dimensions.SECTION_MANAGER_MORE_OPTIONS_GUI.getWidth(), Dimensions.SECTION_MANAGER_MORE_OPTIONS_GUI.getHeight());
    buttonGroup.add(radioButtonVideoGame);
    buttonGroup.add(radioButtonOther);
    panel.add(radioButtonVideoGame);
    panel.add(radioButtonOther);
    panel.add(comboBoxSections);
    panel.add(buttonClose);
    panel.add(buttonApply);
    panel.add(new JLabel(""));
    SpringUtilities.makeCompactGrid(panel, BackgroundImageGUI.PADDING_2, RetroFix.PADDING_3, BackgroundImageGUI.PADDING_5, BackgroundImageGUI.PADDING_5, BackgroundImageGUI.PADDING_5, BackgroundImageGUI.PADDING_5);
  }
  /**
   * Gets the button "Apply"
   * @return JButton buttonApply
   */
  public JButton getButtonApply()
  {
    return buttonApply;
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
   * Gets the combo box displaying the sections
   * @return JComboBox<String> comboBoxSections
   */
  public JComboBox<String> getComboBoxSections()
  {
    return comboBoxSections;
  }
  /**
   * Gets the radio button "Other"
   * @return JRadioButton radioButtonOther
   */
  public JRadioButton getRadioButtonOther()
  {
    return radioButtonOther;
  }
  /**
   * Gets the radio button "Video Game"
   * @return JRadioButton radioButtonVideoGame
   */
  public JRadioButton getRadioButtonVideoGame()
  {
    return radioButtonVideoGame;
  }
  /**
   * Makes the frame visible or not.  If so, readjusts the location and requests focus to buttonClose
   * @param bool true if frame is to be made visible; false otherwise
   */
  public void makeVisible(final boolean bool)
  {
    if (bool)
    {
      readjustLocation();
      buttonClose.requestFocus();
    }
    frame.setVisible(bool);
  }
  /**
   * Updates the combo box displaying the sections
   * @param sections the combo box displaying the sections
   */
  public void updateSections(final String[] sections)
  {
    comboBoxSections.removeAllItems();
    for (String str : sections)
    {
      comboBoxSections.addItem(str);
    }
    comboBoxSections.setSelectedIndex(0);
  }
}
