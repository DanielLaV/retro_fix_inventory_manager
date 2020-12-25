package gui;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import main.RetroFix;
import tools.SpringUtilities;
import tools.User;
import enums.Dimensions;
import enums.Texts;

/**
 * This mini-GUI deals with the user password.  It (this class) is instantiated twice -
 * once in RetroFix, as a modify password-type of PasswordGUI, and the other in
 * DatabaseIO, which is just for entering your user name and password.
 * @author Eric Bergren, Eric Sweeten
 */
public class PasswordGUI extends AbstractMiniGUI
{
  /**
   * The label for user name
   */
  private final JLabel labelUserName = new JLabel(Texts.LABEL_USER_NAME.getText());
  /**
   * The label that says "Current Password"
   */
  private final JLabel labelCurrentPassword = new JLabel(Texts.LABEL_CURRENT_PASSWORD.getText());
  /**
   * The label that says "Enter Password"
   */
  private final JLabel labelEnterPassword = new JLabel(Texts.LABEL_ENTER_PASSWORD.getText());
  /**
   * The label that says "Re-enter Password"
   */
  private final JLabel labelReenterPassword = new JLabel(Texts.LABEL_REENTER_PASSWORD.getText());
  /**
   * The text field for user name
   */
  private final JTextField textFieldUserName = new JTextField();
  /**
   * The password field for current password
   */
  private final JPasswordField currentPassword = new JPasswordField();
  /**
   * The password field for enter password
   */
  private final JPasswordField passwordField1 = new JPasswordField();
  /**
   * The password field for re-enter password
   */
  private final JPasswordField passwordField2 = new JPasswordField();
  /**
   * The button for "Accept"
   */
  private final JButton buttonAccept = new JButton(Texts.BUTTON_ACCEPT.getText());
  /**
   * The button for "Cancel"
   */
  private final JButton buttonCancel = new JButton(Texts.BUTTON_CANCEL.getText());
  /**
   * The boolean that says whether or not this is a quick password GUI.  Quick is for
   * loading a database file, and non-quick is for changing your password.
   */
  private final boolean quick;
  /**
   * This prevents a redundant error from being shown
   */
  private boolean dontShowError;
  /**
   * The only instance of Retro Fix passed in by reference
   */
  private RetroFix retroFix;
  /**
   * This is the action listener that listens to buttonAccept and buttonCancel.
   */
  private SimpleActionListener actionListener = new SimpleActionListener();
  /**
   * Constructor for PasswordGUI
   * @param quick true if it's for loading a database, false if it's for changing your password
   */
  public PasswordGUI(final boolean quick, final RetroFix retroFix)
  {
    this.retroFix = retroFix;
    this.quick = quick;
    frame.setTitle(Texts.FRAME_TITLE_PASSWORD.getText());
    frame.setSize(quick ? Dimensions.QUICK_PASSWORD_GUI.getWidth()
        : Dimensions.PASSWORD_GUI.getWidth(), quick
        ? Dimensions.QUICK_PASSWORD_GUI.getHeight()
        : Dimensions.PASSWORD_GUI.getHeight());
    panel.add(labelUserName);
    panel.add(textFieldUserName);
    if (!quick)
    {
      textFieldUserName.setEnabled(false);
      textFieldUserName.setDisabledTextColor(Color.BLACK);
      panel.add(labelCurrentPassword);
      panel.add(currentPassword);
    }
    panel.add(labelEnterPassword);
    panel.add(passwordField1);
    if (!quick)
    {
      panel.add(labelReenterPassword);
      panel.add(passwordField2);
    }
    panel.add(buttonAccept);
    panel.add(buttonCancel);
    
    buttonAccept.addActionListener(actionListener);
    buttonCancel.addActionListener(actionListener);
    
    SpringUtilities.makeCompactGrid(panel, quick ? ModifyInventoryItemGUI.PADDING_3 : BackgroundImageGUI.PADDING_5, BackgroundImageGUI.PADDING_2, BackgroundImageGUI.PADDING_5, BackgroundImageGUI.PADDING_5, BackgroundImageGUI.PADDING_5, BackgroundImageGUI.PADDING_5);
  }
  /**
   * Clears the password fields
   */
  public void clear()
  {
    currentPassword.setText("");
    passwordField1.setText("");
    passwordField2.setText("");
  }
  /**
   * Gets the button for "Accept"
   * @return JButton buttonAccept
   */
  public JButton getButtonAccept()
  {
    return buttonAccept;
  }
  /**
   * Gets the password
   * @return the password in passwordField1
   */
  public String getPassword()
  {
    return new String(passwordField1.getPassword());
  }
  /**
   * Gets the text field for user name
   * @return JTextField textFieldUserName
   */
  public JTextField getTextFieldUserName()
  {
    return textFieldUserName;
  }
  /**
   * Makes the frame visible or not, plus adjust the frame's location and whether or not
   * currentPassword should be enabled based on whether or not the current user has a
   * password.  If the user does have a password, then disabled it.  Otherwise, enable it.
   * @param bool true makes the frame visible; false makes it invisible
   */
  public void makeVisible(final boolean bool)
  {
    readjustLocation();
    frame.setVisible(bool);
    currentPassword.setEnabled(!retroFix.getUser().getPassword().isEmpty());
  }
  public void setDontShowError()
  {
    dontShowError = true;
  }
  /**
   * Updates the user name based on the current user
   */
  public void updateUserName()
  {
    textFieldUserName.setText(retroFix.getUser().getName());
  }
  /**
   * This action listener class is instantiated once, and buttonAccept and buttonCancel
   * have that object added to it as an action listener.  This class listens in if
   * buttonAccept or buttonCancel is pressed.  If buttonCancel is clicked, it closed
   * the password GUI.  If buttonAccept is clicked, it checks to see if it is the
   * correct password if quick is true, or if the passwords match is quick is false
   * (for the sake of changing passwords).  
   * @author Eric Sweeten
   */
  private class SimpleActionListener implements ActionListener
  {
    @Override
    public void actionPerformed(ActionEvent e)
    {
      if (e.getSource() == buttonAccept)
      {
        if (retroFix.getUser().getPassword().isEmpty()
          || (retroFix.getUser().getPassword().equals(new String(currentPassword.getPassword()))))
        {
          if (new String(passwordField1.getPassword()).equals(new String(passwordField2.getPassword())))
          {
            for (User u : retroFix.getUsers())
            {
              if (u.getName().equals(textFieldUserName.getText()) && u.getPassword().equals(new String(currentPassword.getPassword())))
              {
                retroFix.setUser(u);
                if ((new String(passwordField1.getPassword())).equals(new String(passwordField2.getPassword())))
                  retroFix.getUser().setPassword(new String(passwordField1.getPassword()));
              }
            }
          }
          else
          {
            if (!quick)
              JOptionPane.showMessageDialog(retroFix.getMainFrame(), Texts.MESSAGE_PASSWORDS_DONT_MATCH.getText());
          }
        }
        else
        {          
          if (!dontShowError)
          {
            JOptionPane.showMessageDialog(retroFix.getMainFrame(), Texts.ERROR_INCORRECT_PASSWORD.getText());
            dontShowError = false;
          }
        }
        makeVisible(false);
      }
      else
      {
        makeVisible(false);
      }
    }
  }
}
