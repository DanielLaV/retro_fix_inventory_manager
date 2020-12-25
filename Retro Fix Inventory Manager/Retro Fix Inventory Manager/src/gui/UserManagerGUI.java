package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;

import tools.DatabaseIO;
import tools.SpringUtilities;
import tools.User;
import enums.Dimensions;
import enums.Texts;

/**
 * The mini-GUI used by the administrator to manage users and their permissions
 * @author Eric Sweeten, Daniel LaVergne
 */
public class UserManagerGUI extends AbstractMiniGUI
{
  /**
   * The button for "Add"
   */
  private final JButton buttonAdd = new JButton(Texts.BUTTON_ADD.getText());
  /**
   * The button for "Delete"
   */
  private final JButton buttonDelete = new JButton(Texts.BUTTON_DELETE.getText());
  /**
   * The button for "Modify"
   */
  private final JButton buttonModify = new JButton(Texts.BUTTON_MODIFY.getText());
  /**
   * The button for "Close"
   */
  private final JButton buttonClose = new JButton(Texts.BUTTON_CLOSE.getText());
  /**
   * The check box that regulates user permissions of adding items
   */
  private JCheckBox checkBoxCanAddItems = new JCheckBox(Texts.RADIO_BUTTON_CAN_ADD_ITEMS.getText());
  /**
   * The check box that regulates user permissions of modifying items
   */
  private JCheckBox checkBoxCanModifyItems = new JCheckBox(Texts.RADIO_BUTTON_CAN_MODIFY_ITEMS.getText());
  /**
   * The check box that regulates user permissions of deleting items
   */
  private JCheckBox checkBoxCanDeleteItems = new JCheckBox(Texts.RADIO_BUTTON_CAN_DELETE_ITEMS.getText());
  /**
   * The check box that regulates user permissions of adding sections
   */
  private JCheckBox checkBoxCanAddSections = new JCheckBox(Texts.RADIO_BUTTON_CAN_ADD_SECTIONS.getText());
  /**
   * The check box that regulates user permissions of modifying sections
   */
  private JCheckBox checkBoxCanModifySections = new JCheckBox(Texts.RADIO_BUTTON_CAN_MODIFY_SECTIONS.getText());
  /**
   * The check box that regulates user permissions of deleting sections
   */
  private JCheckBox checkBoxCanDeleteSections = new JCheckBox(Texts.RADIO_BUTTON_CAN_DELETE_SECTIONS.getText());  
  /**
   * The check box that regulates user permissions of ordering sections
   */
  private JCheckBox checkBoxCanOrder = new JCheckBox(Texts.RADIO_BUTTON_CAN_ORDER_ITEMS.getText());
  /**
   * The combo box displaying all the users
   */
  private final JComboBox<String> comboBoxUsers = new JComboBox<String>();
  /**
   * The action listener that listens to most of the GUI components in this class
   */
  private SimpleActionListener actionListener = new SimpleActionListener();
  /**
   * The DatabaseIO object, passed in from RetroFix
   */
  private final DatabaseIO databaseIO;
  /**
   * The list of users (User objects)
   */
  private final List<User> users;
  /**
   * The constructor for UserManagerGUI, which sets up GUI components
   * @param databaseIO the DatabaseIO object passed in from RetroFix
   * @param users The list of User objects passed in from RetroFix
   */
  public UserManagerGUI(final DatabaseIO databaseIO, final List<User> users)
  {
    this.databaseIO = databaseIO;
    this.users = users;
    frame.setTitle(Texts.FRAME_TITLE_USER_MANAGER.getText());
    panel.add(comboBoxUsers);
    panel.add(buttonAdd);
    panel.add(buttonDelete);
    panel.add(buttonModify);
    panel.add(checkBoxCanAddItems);
    panel.add(checkBoxCanModifyItems);
    panel.add(checkBoxCanDeleteItems);
    panel.add(checkBoxCanAddSections);
    panel.add(checkBoxCanModifySections);
    panel.add(checkBoxCanDeleteSections);
    panel.add(checkBoxCanOrder);
    panel.add(buttonClose);
    comboBoxUsers.addActionListener(actionListener);
    buttonAdd.addActionListener(actionListener);
    buttonDelete.addActionListener(actionListener);
    buttonModify.addActionListener(actionListener);
    frame.getRootPane().setDefaultButton(buttonClose);
    frame.setSize(Dimensions.USER_MANAGER_GUI.getWidth(), Dimensions.USER_MANAGER_GUI.getHeight());
    SpringUtilities.makeCompactGrid(panel, ModifyInventoryItemGUI.PADDING_6, BackgroundImageGUI.PADDING_2, BackgroundImageGUI.PADDING_5, BackgroundImageGUI.PADDING_5, BackgroundImageGUI.PADDING_5, BackgroundImageGUI.PADDING_5);
  }
  /**
   * Checks to see if the user name is already taken, blank, or "admin", none of which is allowed
   * @param userName The user name to be checked against all the user names
   * @return error message - blank if no error
   */
  private String addOrModifyCheck(final String userName)
  {
    String error = "";
    for (User u : users)
    {
      if (u.getName().equals(userName))
      {
        error = Texts.ERROR_USER_NAME_TAKEN.getText();
        break;
      }
    }
    if (userName.isEmpty())
      error = Texts.ERROR_USER_NAME_BLANK.getText();
    if (userName.equals(Texts.TEXT_ADMIN.getText()))
      error = Texts.ERROR_ADMIN_NAME.getText();
    if (!databaseIO.isFileLoaded())
      error = Texts.ERROR_MUST_SAVE.getText();
    return error;
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
   * Makes the frame visible or not.  If so, sets up a couple things with the users and GUI components.
   * @param bool true if the frame is to be made visible; false to make it invisible
   */
  public void makeVisible(final boolean bool)
  {
    if (bool)
    {
      updateUsers("");
      readjustLocation();
      buttonClose.requestFocus();
    }
    frame.setVisible(bool);
  }
  /**
   * Orders the users alphabetically by name
   */
  private void orderUsers()
  {
    String s1, s2;
    User u;
    for (int i = 0; i < users.size() - 1; i++)
    {
      for (int j = 1; j < users.size() - i; j++)
      {
        s1 = users.get(j - 1).getName();
        s2 = users.get(j).getName();
        if (s1.compareToIgnoreCase(s2) > 0)
        {
          u = users.get(j - 1);
          users.set(j - 1, users.get(j));
          users.set(j, u);
        }
      }
    }
  }
  /**
   * Updates the users in the combo box displaying the users, then selects the requested user
   * @param userName The user to set in the combo box after it has been updated
   */
  public void updateUsers(final String userName)
  {
    comboBoxUsers.removeActionListener(actionListener);
    comboBoxUsers.removeAllItems();
    comboBoxUsers.addActionListener(actionListener);
    String n;
    int toSelect = 0;
    for (int i = 0; i < users.size(); i++)
    {
      n = users.get(i).getName();
      comboBoxUsers.addItem(n);
      if (n.equals(userName))
        toSelect = i;
    }
    comboBoxUsers.setSelectedIndex(toSelect);
  }
  /**
   * Listener that listens to “Users” combo box, “Add” button, “Delete” button,
   * “Modify” button, and all the check box items, 
   * @author Eric Sweeten
   */
  private class SimpleActionListener implements ActionListener
  {
    @Override
    public void actionPerformed(ActionEvent arg0)
    {
      Object o = arg0.getSource();
      if (o == comboBoxUsers)
      {
        checkBoxCanAddItems.removeActionListener(actionListener);
        checkBoxCanModifyItems.removeActionListener(actionListener);
        checkBoxCanDeleteItems.removeActionListener(actionListener);
        checkBoxCanAddSections.removeActionListener(actionListener);
        checkBoxCanModifySections.removeActionListener(actionListener);
        checkBoxCanDeleteSections.removeActionListener(actionListener);
        checkBoxCanOrder.removeActionListener(actionListener);
        for (User u : users)
        {
          if (u.getName() == comboBoxUsers.getSelectedItem().toString())
          {
            checkBoxCanAddItems.setSelected(u.getCanAddItem());
            checkBoxCanModifyItems.setSelected(u.getCanModifyItem());
            checkBoxCanDeleteItems.setSelected(u.getCanDeleteItem());
            checkBoxCanAddSections.setSelected(u.getCanAddSection());
            checkBoxCanModifySections.setSelected(u.getCanModifySection());
            checkBoxCanDeleteSections.setSelected(u.getCanDeleteSection());
            checkBoxCanOrder.setSelected(u.getCanOrder());
          }
        }
        checkBoxCanAddItems.addActionListener(actionListener);
        checkBoxCanModifyItems.addActionListener(actionListener);
        checkBoxCanDeleteItems.addActionListener(actionListener);
        checkBoxCanAddSections.addActionListener(actionListener);
        checkBoxCanModifySections.addActionListener(actionListener);
        checkBoxCanDeleteSections.addActionListener(actionListener);
        checkBoxCanOrder.addActionListener(actionListener);
      }
      else if (o == checkBoxCanAddItems || o == checkBoxCanModifyItems || o == checkBoxCanDeleteItems ||
          o == checkBoxCanAddSections || o == checkBoxCanModifySections || o == checkBoxCanDeleteSections ||
          o == checkBoxCanOrder)
      {
        if (comboBoxUsers.getSelectedItem().equals(Texts.TEXT_ADMIN.getText()))
        {
          checkBoxCanAddItems.setSelected(true);
          checkBoxCanModifyItems.setSelected(true);
          checkBoxCanDeleteItems.setSelected(true);
          checkBoxCanAddSections.setSelected(true);
          checkBoxCanModifySections.setSelected(true);
          checkBoxCanDeleteSections.setSelected(true);
          checkBoxCanOrder.setSelected(true);
        }
        else
        {
          for (User u : users)
          {
            if (u.getName() == comboBoxUsers.getSelectedItem().toString())
            {
              u.setCanAddItem(checkBoxCanAddItems.isSelected());
              u.setCanModifyItem(checkBoxCanModifyItems.isSelected());
              u.setCanDeleteItem(checkBoxCanDeleteItems.isSelected());
              u.setCanAddSection(checkBoxCanAddSections.isSelected());
              u.setCanModifySection(checkBoxCanModifySections.isSelected());
              u.setCanDeleteSection(checkBoxCanDeleteSections.isSelected());
              u.setCanOrder(checkBoxCanOrder.isSelected());
              break;
            }
          }
        }
      }
      else if (o == buttonAdd)
      {
        String userName = JOptionPane.showInputDialog(frame, Texts.MESSAGE_ADD_USER.getText());
        if (userName == null) userName = "";
        String error = addOrModifyCheck(userName);
        if (error.isEmpty())
        {
          User newUser = new User(userName);
          users.add(newUser);
          orderUsers();
          updateUsers(userName);
          databaseIO.save(true);
        }
        else
        {
          JOptionPane.showMessageDialog(frame, error);
        }
      }
      else if (o == buttonDelete)
      {
        if (comboBoxUsers.getSelectedItem().toString().equals(Texts.TEXT_ADMIN.getText()))
          JOptionPane.showMessageDialog(frame, Texts.ERROR_ADMIN_NAME.getText());
        else if (JOptionPane.showConfirmDialog(frame, Texts.MESSAGE_DELETE_USER.getText() + Texts.CHARACTER_QUESTION_MARK.getText()) == JOptionPane.YES_OPTION) 
        {
          int position = 0;
          for (int i = 0; i < users.size(); i++)
          {            
            if (users.get(i).getName().equals(comboBoxUsers.getSelectedItem().toString()))
            {
              users.remove(i);
              position = i;
            }
          }
          String newPos = "";
          if (comboBoxUsers.getItemCount() > position + 1)
          {
            newPos = comboBoxUsers.getItemAt(comboBoxUsers.getSelectedIndex() + 1).toString();
          }
          else
          {
            if (comboBoxUsers.getSelectedIndex() - 1 >= 0)
              newPos = comboBoxUsers.getItemAt(comboBoxUsers.getSelectedIndex() - 1).toString();
          }
          updateUsers(newPos);
        }
      }
      else if (o == buttonModify)
      {
        if (comboBoxUsers.getSelectedItem().toString().equals(Texts.TEXT_ADMIN.getText()))
        {
          JOptionPane.showMessageDialog(frame, Texts.ERROR_ADMIN_NAME.getText());
        }
        else
        {
          String userName = JOptionPane.showInputDialog(frame, Texts.MESSAGE_MODIFY_USER.getText());
          if (userName == null) userName = "";
          String error = addOrModifyCheck(userName);
          if (error.isEmpty())
          {
            for (int i = 0; i < users.size(); i++)
            {
              if (users.get(i).getName().equals(comboBoxUsers.getSelectedItem().toString()))
              {
                users.get(i).setName(userName);
                break;
              }
            }
            orderUsers();
            updateUsers(userName);
            databaseIO.save(true);
          }
          else
          {
            JOptionPane.showMessageDialog(frame, error);
          }
        }
      }
    }
  }
}
