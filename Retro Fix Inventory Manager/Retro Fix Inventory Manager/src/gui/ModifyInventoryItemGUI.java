package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JTextField;

import tools.SpringUtilities;
import enums.Dimensions;
import enums.InventoryType;
import enums.Texts;

/**
 * The mini-GUI for adding and modifying inventory items
 * @author Kevin Powell, Eric Sweeten
 */
public class ModifyInventoryItemGUI extends AbstractMiniGUI
{
  /**
   * Used as an argument to SpringUtilities makeCompactGrid method, for the sake of making a compact
   * grid of the GUI elements
   */
  public static final int PADDING_3 = 3;
  /**
   * Used as an argument to SpringUtilities makeCompactGrid method, for the sake of making a compact
   * grid of the GUI elements
   */
  public static final int PADDING_6 = 6;
  /**
   * Used as an argument to SpringUtilities makeCompactGrid method, for the sake of making a compact
   * grid of the GUI elements
   */
  private static final int PADDING_11 = 11;
  /**
   * The label "Name"
   */
  private final JLabel labelName = new JLabel(Texts.ATTRIBUTE_NAME.getText());
  /**
   * The label "Loose Price"
   */
  private final JLabel labelLoosePrice = new JLabel(Texts.ATTRIBUTE_LOOSE.getText() + Texts.LABEL_PRICE.getText());
  /**
   * The label "Complete Price"
   */
  private final JLabel labelCompletePrice = new JLabel(Texts.ATTRIBUTE_COMPLETE.getText() + Texts.LABEL_PRICE.getText());
  /**
   * The label "New Price"
   */
  private final JLabel labelNewPrice = new JLabel(Texts.ATTRIBUTE_NEW.getText() + Texts.LABEL_PRICE.getText());
  /**
   * The label "Box"
   */
  private final JLabel labelBox = new JLabel (Texts.ATTRIBUTE_BOX.getText());
  /**
   * The label "Manual"
   */
  private final JLabel labelManual = new JLabel(Texts.ATTRIBUTE_MANUAL.getText());
  /**
   * The label "Condition"
   */
  private final JLabel labelCondition = new JLabel(Texts.ATTRIBUTE_CONDITION.getText());
  /**
   * The label "Notes"
   */
  private final JLabel labelNotes = new JLabel(Texts.ATTRIBUTE_NOTES.getText()); 
  /**
   * Thr label "Value"
   */
  private final JLabel labelValue = new JLabel(Texts.ATTRIBUTE_VALUE.getText());
  /**
   * The text field for Name
   */
  private final JTextField textFieldName = new JTextField();
  /**
   * The text field for Loose Price
   */
  private final JTextField textFieldLoosePrice = new JTextField();
  /**
   * The text field for Complete Price
   */
  private final JTextField textFieldCompletePrice = new JTextField();
  /**
   * The text field for New Price
   */
  private final JTextField textFieldNewPrice = new JTextField();
  /**
   * The text field for Notes
   */
  private final JTextField textFieldNotes = new JTextField();
  /**
   * The text field for Value
   */
  private final JTextField textFieldValue = new JTextField();
  /**
   * A String array containing "", "Yes", and "No"
   */
  private final String[] yesNo = {"", Texts.ANSWER_YES.getText(), Texts.ANSWER_NO.getText()};
  /**
   * A combo box for Box containing the string array yesNo
   */
  private final JComboBox<String> comboBoxBox = new JComboBox<>(yesNo);
  /**
   * A combo box for Manual containing the string array yesNo
   */
  private final JComboBox<String> comboBoxManual = new JComboBox<>(yesNo);
  /**
   * A String array for all possible conditions, including "", "New", "Mint", "Good", "Fair", and "Poor"
   */
  private final String[] conditions = {"", Texts.CONDITION_NEW.getText(), Texts.CONDITION_MINT.getText(), Texts.CONDITION_GOOD.getText(), Texts.CONDITION_FAIR.getText(), Texts.CONDITION_POOR.getText()};
  /**
   * A combo box displaying the possible conditions with the String conditions
   */
  private final JComboBox<String> conditionComboBox = new JComboBox<>(conditions);
  /**
   * The button for Accept
   */
  private final JButton buttonAccept = new JButton(Texts.BUTTON_ACCEPT.getText());
  /**
   * The button for Clear
   */
  private final JButton buttonClear = new JButton(Texts.BUTTON_CLEAR.getText());
  /**
   * The button for Cancel
   */
  private final JButton buttonCancel = new JButton(Texts.BUTTON_CANCEL.getText());
  /**
   * The local action listener, an instance of the local action listener class in this class
   * (ModifyInventoryActionListener), which listens to buttonCancel and buttonClear.
   */
  private final ModifyInventoryActionListener modifyInventoryActionListener = new ModifyInventoryActionListener();
  /**
   * The index of the InventoryItemTextField for the IITextFields in RetroFix
   */
  private int index;
  /**
   * The InventoryType in the currently loaded section in RetroFix
   */
  private InventoryType inventoryType;
  /**
   * Constructor for this class,which sets up a couple GUI components by adding action listeners to them
   * and sets the default button to buttonAccept
   */
  public ModifyInventoryItemGUI()
  {
    buttonClear.addActionListener(modifyInventoryActionListener);
    buttonCancel.addActionListener(modifyInventoryActionListener);
    frame.getRootPane().setDefaultButton(buttonAccept);
  }
  /**
   * Clears all test fields and sets the combo boxes to index 0
   */
  public void clear()
  {
    textFieldName.setText("");
    textFieldLoosePrice.setText("");
    textFieldCompletePrice.setText("");
    textFieldNewPrice.setText("");
    comboBoxBox.setSelectedIndex(0);
    comboBoxManual.setSelectedIndex(0);
    conditionComboBox.setSelectedIndex(0);
    textFieldNotes.setText("");
    textFieldValue.setText("");
  }
  /**
   * Returns the button Accept
   * @return JButton Accept
   */
  public JButton getButtonAccept()
  {
    return buttonAccept;
  }
  /**
   * Returns the combo box for Box
   * @return JComboBox comboBoxBox
   */
  public JComboBox<String> getComboBoxBox()
  {
    return comboBoxBox;
  }
  /**
   * Returns the combo box for Condition
   * @return JComboBox conditionComboBox
   */
  public JComboBox<String> getComboBoxCondition()
  {
    return conditionComboBox;
  }
  /**
   * Returns the combo box for Manual
   * @return JComboBox comboBoxManual
   */
  public JComboBox<String> getComboBoxManual()
  {
    return comboBoxManual;
  }
  /**
   * Gets the appropriate Inventory Item Text Field index
   * @return inventory item's index
   */
  public int getIndex()
  {
    return index;
  }
  /**
   * Returns the text field for Complete Price
   * @return JTextField textFieldCompletePrice
   */
  public JTextField getTextFieldCompletePrice()
  {
    return textFieldCompletePrice;
  }
  /**
   * Returns the text field for Loose Price
   * @return JTextField textFieldLoosePrice
   */
  public JTextField getTextFieldLoosePrice()
  {
    return textFieldLoosePrice;
  }
  /**
   * Returns the text field for Name
   * @return JTextField textFieldName
   */
  public JTextField getTextFieldName()
  {
    return textFieldName;
  }
  /**
   * Returns the text field for New Price
   * @return JTextField textFieldNewPrice
   */
  public JTextField getTextFieldNewPrice()
  {
    return textFieldNewPrice;
  }
  /**
   * Returns the text field for Notes
   * @return JTextField textFieldNotes
   */
  public JTextField getTextFieldNotes()
  {
    return textFieldNotes;
  }
  /**
   * Returns the text field for Value
   * @return JTextField textFieldValue
   */
  public JTextField getTextFieldValue()
  {
    return textFieldValue;
  }
  /**
   * Makes the JFrame visible or not.  If not visible, all it does is make it not invisible.
   * If it makes it visible, it sets up the panel, frame size, requests focus to textFieldName,
   * and readjusts location.
   * @param bool Makes the JFrame visible or not (true = visible, false = invisible)
   */
  public void makeVisible(final boolean bool)
  {
    int width;
    int height;
    if (inventoryType == InventoryType.VIDEO_GAME)
    {
      width = Dimensions.MODIFY_INVENTORY_ITEM_GUI_FOR_VIDEO_GAMES.getWidth();
      height = Dimensions.MODIFY_INVENTORY_ITEM_GUI_FOR_VIDEO_GAMES.getHeight();
    }
    else
    {
      width = Dimensions.MODIFY_INVENTORY_ITEM_GUI_FOR_OTHER.getWidth();
      height = Dimensions.MODIFY_INVENTORY_ITEM_GUI_FOR_OTHER.getHeight();
    }
    if (bool)
    {
      setupPanel();
      frame.setSize(width, height);
      textFieldName.requestFocus();
      readjustLocation();
    }
    frame.setVisible(bool);
  }
  /**
   * Sets the index in order to know which InventoryItemTextField
   * @param i index, pointing to the appropriate InventoryItemTextField
   */
  public void setIndex(final int i)
  {
    index = i;
  }
  /**
   * Sets the appropriate InventoryType, depending on the InventoryType of the section
   * @param inventoryType The InventoryType
   */
  public void setInventoryType(final InventoryType inventoryType)
  {
    this.inventoryType = inventoryType;
  }
  /**
   * Sets the title for the frame
   * @param title Title for the JFrame
   */
  public void setTitle(final String title)
  {
    frame.setTitle(title);
  }
  /**
   * Sets up the panel.  More components are added if it's for a video game than a generic item.
   */
  public void setupPanel()
  {
    panel.removeAll();
    panel.add(labelName);
    panel.add(textFieldName);
    if (inventoryType == InventoryType.VIDEO_GAME)
    {
      panel.add(labelLoosePrice);
      panel.add(textFieldLoosePrice);
      panel.add(labelCompletePrice);
      panel.add(textFieldCompletePrice);
      panel.add(labelNewPrice);
      panel.add(textFieldNewPrice);
      panel.add(labelBox);
      panel.add(comboBoxBox);
      panel.add(labelManual);
      panel.add(comboBoxManual);
    }
    panel.add(labelCondition);
    panel.add(conditionComboBox);
    panel.add(labelNotes);
    panel.add(textFieldNotes);
    panel.add(labelValue); 
    panel.add(textFieldValue);
    panel.add(buttonAccept);
    panel.add(buttonClear);
    panel.add(buttonCancel);
    panel.add(new JLabel());
    panel.revalidate();
    SpringUtilities.makeCompactGrid(panel, inventoryType == InventoryType.VIDEO_GAME ? PADDING_11 : PADDING_6, BackgroundImageGUI.PADDING_2, PADDING_3, PADDING_3, PADDING_3, PADDING_3);
  }
  /**
   * The local action listener class which listens to buttonClear and buttonCancel
   * @author Eric Sweeten
   */
  private class ModifyInventoryActionListener implements ActionListener
  {
    @Override
    public void actionPerformed(final ActionEvent arg0)
    {
      if (arg0.getSource() == buttonClear)
      {
        clear();
      }
      else if (arg0.getSource() == buttonCancel)
      {
        clear();
        makeVisible(false);
      }
    }
  }
}
