package tools;

import enums.Texts;
import gui.PasswordGUI;
import inventory.InventoryItem;
import inventory.Sections;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

import main.RetroFix;

/**
 * This class is all about loading and saving database files
 * @author Eric Sweeten, Daniel LaVergne
 */
public class DatabaseIO
{
  /**
   * The file chooser, which is used to browse the computer and select a file for either
   * loading or saving
   */
  private final JFileChooser fileChooser = new JFileChooser();
  /**
   * The password GUI used to prompt the user for a username and password to access
   * the database
   */
  private final PasswordGUI passwordGUI;
  /**
   * Used with JOptionPane to keep track of the user's choice
   */
  private int choice;
  /**
   * A flag keeping track of whether or not the file is loaded
   */
  private boolean fileLoaded;
  /**
   * A file object used for loading and saved files
   */
  private File file;
  /**
   * The main Retro Fix object - the only one instantiated in the program
   */
  private final RetroFix retroFix;
  /**
   * Constructor for DatabaseIO, which sets up the Password GUI, file chooser, and
   * sets the file loaded boolean flag to false.  Also passes in the main Retro Fix
   * object
   * @param retroFix
   */
  public DatabaseIO(final RetroFix retroFix)
  {
    this.retroFix = retroFix;
    passwordGUI = new PasswordGUI(true, retroFix);
    fileChooser.setFileFilter(new FileNameExtensionFilter(Texts.FILE_NAME_EXTENSION_INVENTORY_FILES.getText(), Texts.FILE_NAME_EXTENSION_INV.getText()));
    fileLoaded = false;
  }
  /**
   * Sets the file loaded boolean flag to false and the file object to null.  Does not
   * actuall close the file here, as that's done in the same method it's opened in.
   */
  public void close()
  {
    fileLoaded = false;
    file = null;
  }
  /**
   * Gets the button "Accept"
   * @return JButton buttonAccept of passwordGUI
   */
  public JButton getButtonAccept()
  {
    return passwordGUI.getButtonAccept();
  }
  /**
   * Gets the File object
   * @return File file
   */
  public File getFile()
  {
    return file;
  }
  /**
   * States whether or not the file has been loaded
   * @return returns true if the file has been loaded, and false otherwise
   */
  public boolean isFileLoaded()
  {
    return fileLoaded;
  }
  /**
   * Loads the database file from memory, checks to see if there is a user by the inputted
   * name, and if so, checks to see if the password is valid.  If so, it loads the database
   * into memory
   */
  @SuppressWarnings("unchecked")
  public void load()
  {
    String password;
    if (choice == JFileChooser.APPROVE_OPTION)
    {
      file = fileChooser.getSelectedFile();
      try
      {
        final FileInputStream fi = new FileInputStream(file);
        final ObjectInputStream input = new ObjectInputStream(fi);
        boolean skipInvalidUserName = false;
        passwordGUI.makeVisible(false);
        Sections sections = (Sections)input.readObject();
        List<ArrayList<InventoryItem>> inv = (List<ArrayList<InventoryItem>>)input.readObject();
        ArrayList<User> users = (ArrayList<User>)input.readObject();

        String userName = passwordGUI.getTextFieldUserName().getText();
        password = new String(passwordGUI.getPassword());
        for (User u : users)
        {
          if (u.getName().equals(userName))
          {
            if (u.getPassword().equals(password))
            {
              fileLoaded = true;
              retroFix.setSections(sections);
              retroFix.setInventoryItems(inv);
              retroFix.setUsers(users);
              retroFix.setUser(u);
              passwordGUI.setDontShowError();
              break;
            }
            else
            {
              JOptionPane.showMessageDialog(retroFix.getMainFrame(), Texts.ERROR_INCORRECT_PASSWORD.getText());
              skipInvalidUserName = true;
              break;
            }
          }
        }
        if (!fileLoaded && !skipInvalidUserName)
          JOptionPane.showMessageDialog(retroFix.getMainFrame(), Texts.ERROR_INVALID_USER_NAME.getText());
        input.close();
        fi.close();
      }
      catch (final Exception e)
      {
        JOptionPane.showMessageDialog(retroFix.getMainFrame(), Texts.ERROR_UNABLE_TO_LOAD_DATABASE_FILE.getText());
      }
    }
  }
  /**
   * Requests which file the user wants to open, then calls the password GUI to ask a
   * the required username and password to access it
   */
  public void loadPasswordGUI()
  {
    fileChooser.setCurrentDirectory(new File(file == null ? System.getProperty(Texts.FILE_CHOOSER_CURRENT_DIRECTORY.getText()) : file.getAbsolutePath()));
    choice = fileChooser.showOpenDialog(retroFix.getMainFrame());
    if (choice == JFileChooser.APPROVE_OPTION)
    {
      passwordGUI.clear();
      passwordGUI.updateUserName();
      passwordGUI.makeVisible(true);
    }
  }
  /**
   * Saves the database and users of the database to a file
   * @param skip Whether or not to skip the "Save As first" error message if the file's
   * not loaded
   */
  public void save(final boolean skip)
  {
    if (!fileLoaded && !skip)
    {
      JOptionPane.showMessageDialog(retroFix.getMainFrame(), Texts.ERROR_USE_SAVE_AS_FIRST.getText());
      return;
    }
    file = fileChooser.getSelectedFile();
    try
    {
      if (!file.toString().endsWith(".inv"))
        file = new File(file.toString() + ".inv");
    }
    catch (final NullPointerException e)
    {
      JOptionPane.showMessageDialog(retroFix.getMainFrame(), Texts.ERROR_MUST_SAVE.getText());
      return;
    }
    try
    {
      final FileOutputStream fo = new FileOutputStream(file);
      final ObjectOutputStream output = new ObjectOutputStream(fo);
      output.writeObject(retroFix.getSections());
      output.writeObject(retroFix.getInventoryItems());
      output.writeObject(retroFix.getUsers());
      fileLoaded = true;
      output.close();
      fo.close();
    }
    catch (final IOException e)
    {
      JOptionPane.showMessageDialog(retroFix.getMainFrame(), Texts.ERROR_UNABLE_TO_SAVE_DATABASE_FILE.getText());
    }
  }
  /**
   * Asks the user for the file name to save as, then saves it if it's a valid name
   */
  public void saveAs()
  {
    fileChooser.setCurrentDirectory(new File(System.getProperty(Texts.FILE_CHOOSER_CURRENT_DIRECTORY.getText())));
    if (fileChooser.showSaveDialog(retroFix.getMainFrame()) == JFileChooser.APPROVE_OPTION)
      save(true);
  }
}
