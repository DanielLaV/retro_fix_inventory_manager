package gui;

import java.awt.Color;
import java.awt.Font;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JTextField;

import enums.Texts;

/**
 * This class holds the only GUI text fields held in the panel in RetroFix that shows the
 * inventory items.  An instance of this class is one row of those text fields, as in one
 * inventory item.
 * @author Daniel LaVergne, Eric Sweeten
 */
public class InventoryItemTextField
{
  /**
   * The Icon used for the Modify buttons
   */
  private static Icon imageIconPencil;
  /**
   * This boolean keeps track whether the image icon for pencil has already been loaded
   */
  private static boolean imageIconLoaded;
  /**
   * The customizable text field displaying the name
   */
  private final JTextField name = new JTextField();
  /**
   * The customizable text field displaying the loose price
   */
  private final JTextField loosePrice = new JTextField();
  /**
   * The customizable text field displaying the complete price
   */
  private final JTextField completePrice = new JTextField();
  /**
   * The customizable text field displaying the new price
   */
  private final JTextField newPrice = new JTextField();
  /**
   * The customizable text field displaying whether or not it has a box, or blank
   */
  private final JTextField box = new JTextField();
  /**
   * The customizable text field displaying whether or not it has a manual, or blank
   */
  private final JTextField manual = new JTextField();
  /**
   * The customizable text field displaying the condition, either blank or from one of
   * five pre-determined conditions
   */
  private final JTextField condition = new JTextField();
  /**
   * The customizable text field displaying the notes
   */
  private final JTextField notes = new JTextField();
  /**
   * The customizable text field displaying the value
   */
  private final JTextField value = new JTextField();
  /**
   * The delete button on the right of each set of text fields, which is shown by one
   * character (the left of the three far right buttons)
   */
  private final JButton buttonDelete = new JButton(Texts.BUTTON_SHORTCUT_DELETE.getText());
  /**
   * The modify button on the right of each set of text fields, which is shown by one
   * character (the middle of the three far right buttons)
   */
  private final JButton buttonModify = new JButton(imageIconPencil);
  /**
   * The duplicate button on the right of each set of text fields, which is shown by one
   * character (the right of the three far right buttons)
   */
  private final JButton buttonDuplicate = new JButton(Texts.BUTTON_SHORTCUT_DUPLICATE.getText());
  /**
   * Constructor for an InventoryItemTextField object.  These are set to be see-through
   * and uneditable.  You cannot edit the text fields directly from the panel.  You have to
   * click on "Add" or one of the modify or duplicate buttons to edit data.
   * @param index The set of text fields - 0 being the top, 9 being the 10th down, 14 being
   * the 15th down, etc., down to about the 30th, depending on the value of
   * NUMBER_OF_II_TEXT_FIELDS above.
   */
  public InventoryItemTextField()
  {
    name.setOpaque(false);
    loosePrice.setOpaque(false);
    completePrice.setOpaque(false);
    newPrice.setOpaque(false);
    box.setOpaque(false);
    manual.setOpaque(false);
    condition.setOpaque(false);
    notes.setOpaque(false);
    value.setOpaque(false);
    buttonDelete.setOpaque(false);
    buttonModify.setOpaque(false);
    buttonDuplicate.setOpaque(false);

    convertTextFields(Color.WHITE);
    
    name.setEditable(false);
    loosePrice.setEditable(false);
    completePrice.setEditable(false);
    newPrice.setEditable(false);
    box.setEditable(false);
    manual.setEditable(false);
    condition.setEditable(false);
    notes.setEditable(false);
    value.setEditable(false);

    Font font = new Font(Texts.FONT_TIMES_NEW_ROMAN.getText(), Font.BOLD, 16);
    buttonDelete.setFont(font);
    buttonDuplicate.setFont(font);
    if (!imageIconLoaded)
    {
      try
      {
        imageIconLoaded = true;
        imageIconPencil = new ImageIcon(ImageIO.read(getClass().getResourceAsStream(Texts.RESOURCE_PENCIL.getText())));
      }
      catch (final IOException exc)
      {
        //do nothing
      }
    }
    buttonModify.setIcon(imageIconPencil);
  }
  /**
   * Converts the colors of the text field borders
   * @param color The new color for the borders of the text fields
   */
  public void convertBorders(final Color color)
  {
    name.setBorder(BorderFactory.createLineBorder(color));
    loosePrice.setBorder(BorderFactory.createLineBorder(color));
    completePrice.setBorder(BorderFactory.createLineBorder(color));
    newPrice.setBorder(BorderFactory.createLineBorder(color));
    box.setBorder(BorderFactory.createLineBorder(color));
    manual.setBorder(BorderFactory.createLineBorder(color));
    condition.setBorder(BorderFactory.createLineBorder(color));
    notes.setBorder(BorderFactory.createLineBorder(color));
    value.setBorder(BorderFactory.createLineBorder(color));
  }
  /**
   * Converts the colors of the text in the text fields
   * @param color The new color for the text in the text fields
   */
  public void convertTextFields(final Color color)
  {
    name.setForeground(color);
    loosePrice.setForeground(color);
    completePrice.setForeground(color);
    newPrice.setForeground(color);
    box.setForeground(color);
    manual.setForeground(color);
    condition.setForeground(color);
    notes.setForeground(color);
    value.setForeground(color);
  }
  /**
   * Gets the delete button
   * @return JButton for delete
   */
  public JButton getButtonDelete()
  {
    return buttonDelete;
  }
  /**
   * Gets the duplicate button
   * @return JButton for duplicate
   */
  public JButton getButtonDuplicate()
  {
    return buttonDuplicate;
  }
  /**
   * Gets the modify button
   * @return JButton for modify
   */
  public JButton getButtonModify()
  {
    return buttonModify;
  }
  /**
   * Gets the text field for box
   * @return JTextField for box
   */
  public JTextField getTextFieldBox()
  {
    return box;
  }
  /**
   * Gets the text field for complete price
   * @return JTextField for complete price
   */
  public JTextField getTextFieldCompletePrice()
  {
    return completePrice;
  }
  /**
   * Gets the text field for condition
   * @return JTextField for condition
   */
  public JTextField getTextFieldCondition()
  {
    return condition;
  }
  /**
   * Gets the text field for loose price
   * @return JTextField for loose price
   */
  public JTextField getTextFieldLoosePrice()
  {
    return loosePrice;
  }
  /**
   * Gets the text field for manual
   * @return JTextField for manual
   */
  public JTextField getTextFieldManual()
  {
    return manual;
  }
  /**
   * Gets the text field for name
   * @return JTextField for name
   */
  public JTextField getTextFieldName()
  {
    return name;
  }
  /**
   * Gets the text field for new price
   * @return JTextField for new price
   */
  public JTextField getTextFieldNewPrice()
  {
    return newPrice;
  }
  /**
   * Gets the text field for notes
   * @return JTextField for notes
   */
  public JTextField getTextFieldNotes()
  {
    return notes;
  }
  /**
   * Gets the text field for value
   * @return JTextField for value
   */
  public JTextField getTextFieldValue()
  {
    return value;
  }
  /**
   * Sets the text in the text field for box
   * @param box text to be set in JTextField for box
   */
  public void setBox(final String box)
  {
    this.box.setText(box);
  }
  /**
   * Sets the text in the text field for complete price
   * @param completePrice text to be set in JTextField for complete price
   */
  public void setCompletePrice(final String completePrice)
  {
    this.completePrice.setText(completePrice);
  }
  /**
   * Sets the text in the text field for condition
   * @param condition text to be set in JTextField for condition
   */
  public void setCondition(final String condition)
  {
    this.condition.setText(condition);
  }
  /**
   * Sets the text in the text field for loose price
   * @param loosePrice text to be set in JTextField for loose price
   */
  public void setLoosePrice(final String loosePrice)
  {
    this.loosePrice.setText(loosePrice);
  }
  /**
   * Sets the text in the text field for manual
   * @param manual text to be set in JTextField for manual
   */
  public void setManual(final String manual)
  {
    this.manual.setText(manual);
  }
  /**
   * Sets the text in the text field for name
   * @param name text to be set in JTextField for name
   */
  public void setName(final String name)
  {
    this.name.setText(name);
    this.name.setCaretPosition(0);
  }
  /**
   * Sets the text in the text field for new price
   * @param newPrice text to be set in JTextField for new price
   */
  public void setNewPrice(final String newPrice)
  {
    this.newPrice.setText(newPrice);
  }
  /**
   * Sets the text in the text field for notes
   * @param notes text to be set in JTextField for notes
   */
  public void setNotes(final String notes)
  {
    this.notes.setText(notes);
    this.notes.setCaretPosition(0);
  }
  /**
   * Sets the text in the text field for value
   * @param value text to be set in JTextField for value
   */
  public void setValue(final String value)
  {
    this.value.setText(value);
  }
  /**
   * The current number of sets of Inventory Item Text Fields I use
   */
  public static final int NUMBER_OF_II_TEXT_FIELDS = 30;
  /**
   * Checks two String objects to see what order they are in, whether
   * alphabetically or numerically
   * @param s1 The first String
   * @param s2 The second String
   * @param greaterThan True is ascending, false is descending
   * @return True if passed-in greaterThan is true and it is in fact ascending,
   * false if descending.  True if passed-in greaterThan is false and it is
   * descending, and false if ascending.
   */
  public static boolean getCompare(final String s1, final String s2, final boolean greaterThan)
  {
    boolean compare = false;
    try
    {
      if (greaterThan)
      {
        compare = Double.parseDouble(s1) > Double.parseDouble(s2);
      }
      else
      {
        compare = Double.parseDouble(s1) < Double.parseDouble(s2);
      }
    }
    catch (final NumberFormatException nfe)
    {
      if (greaterThan)
      {
        compare = s1.compareToIgnoreCase(s2) > 0;
      }
      else
      {
        compare = s1.compareToIgnoreCase(s2) < 0;
      }
    }
    return compare;
  }
}
