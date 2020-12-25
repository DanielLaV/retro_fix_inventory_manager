package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.imageio.ImageIO;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.filechooser.FileNameExtensionFilter;

import main.RetroFix;
import tools.Settings;
import tools.SpringUtilities;
import enums.Dimensions;
import enums.Texts;

/**
 * The mini-GUI for browsing and selecting a background image, as well as deselecting one
 * if the user does not want a background image
 * @author Kevin Powell, Eric Sweeten
 */
public class BackgroundImageGUI extends AbstractMiniGUI
{
  /**
   * Used as an argument to SpringUtilities makeCompactGrid method, for the sake of making a compact
   * grid of the GUI elements.  Also used in ModifyInventoryItemGUI's SpringUtilties makeCompactGrid
   * method for the same reason.
   */
  public static final int PADDING_2 = 2;
  /**
   * Used as an argument to SpringUtilities makeCompactGrid method, for the sake of making a compact
   * grid of the GUI elements.  Also used in some other SprintUtilities makeCompactGrid methods, hence
   * it being public
   */
  public static final int PADDING_5 = 5;
  /**
   * The button for "Browse" to browse for a background image
   */
  private final JButton buttonBrowse = new JButton(Texts.BUTTON_BROWSE.getText());
  /**
   * The button to "Accept" the selected background image settings
   */
  private final JButton buttonAccept = new JButton(Texts.BUTTON_ACCEPT.getText());
  /**
   * The button group containing the two buttons "None" and "From file" below
   */
  private final ButtonGroup buttonGroup = new ButtonGroup();
  /**
   * The radio button for "None".  Selecting this tells the application the user doesn't want
   * a background image once "Accept" is clicked.
   */
  private final JRadioButton radioButtonNone = new JRadioButton(Texts.RADIO_BUTTON_NONE.getText());
  /**
   * The radio button for "From file".  Selecting this tells the application the user doesn't
   * want a background image once "Accept" is clicked.
   */
  private final JRadioButton radioButtonFile = new JRadioButton(Texts.RADIO_BUTTON_FROM_FILE.getText());
  /**
   * The text field that shows the image file that will be loaded if "From file" radio button is
   * clicked as well as "Accept"
   */
  private final JTextField textFieldFile = new JTextField();
  /**
   * The file chooser to browse to and select an image file
   */
  private final JFileChooser fileChooser = new JFileChooser();
  /**
   * The image file
   */
  private File imageFile;
  /**
   * The constructor for BackgroundImageGUI.  The frame is set up, as well as the button group
   * and panel.  Then an anonymous action listener is added to listen to buttonBrowse, which
   * opens the file chooser.
   */
  public BackgroundImageGUI()
  {
    frame.setTitle(Texts.FRAME_TITLE_BACKGROUND_IMAGE.getText());
    frame.setSize(Dimensions.BACKGROUND_IMAGE_GUI.getWidth(), Dimensions.BACKGROUND_IMAGE_GUI.getHeight());
    frame.getRootPane().setDefaultButton(buttonAccept);
    buttonGroup.add(radioButtonNone);
    buttonGroup.add(radioButtonFile);
    panel.add(radioButtonFile);
    panel.add(radioButtonNone);
    panel.add(textFieldFile);
    panel.add(buttonBrowse);
    panel.add(buttonAccept);
    panel.add(new JLabel());
    buttonBrowse.addActionListener(new ActionListener()
    {
      @Override
      public void actionPerformed(ActionEvent arg0)
      {
        if (arg0.getSource() == buttonBrowse)
        {
          fileChooser.setAcceptAllFileFilterUsed(false);
          fileChooser.addChoosableFileFilter(new FileNameExtensionFilter(Texts.FILE_NAME_EXTENSION_FILTER_IMAGES.getText(), Texts.FILE_NAME_EXTENSION_JPG.getText(), Texts.FILE_NAME_EXTENSION_PNG.getText(), Texts.FILE_NAME_EXTENSION_GIF.getText(), Texts.FILE_NAME_EXTENSION_BMP.getText()));
          if (fileChooser.showOpenDialog(frame) == JFileChooser.APPROVE_OPTION)
          {
            imageFile = fileChooser.getSelectedFile();
            textFieldFile.setText(imageFile.toString());
            Settings.setImageFile(imageFile);
          }
        }
      }
    });
    SpringUtilities.makeCompactGrid(panel, RetroFix.PADDING_3, PADDING_2, PADDING_5, PADDING_5, PADDING_5, PADDING_5);
  }
  /**
   * Gets the accept button
   * @return JButton "Accept"
   */
  public JButton getButtonAccept()
  {
    return buttonAccept;
  }
  /**
   * Gets the radio button "From file"
   * @return JRadioButton radioButtonFile
   */
  public JRadioButton getRadioButtonFile()
  {
    return radioButtonFile;
  }
  /**
   * Gets the radio button "None"
   * @return JRadioButton radioButtonNone
   */
  public JRadioButton getRadioButtonNone()
  {
    return radioButtonNone;
  }
  /**
   * Gets the text field containing the file
   * @return JTextField textFieldFile
   */
  public JTextField getTextFieldFile()
  {
    return textFieldFile;
  }
  /**
   * Makes the frame visible or not.  If the frame is to be made visible, it sets the directory
   * for the file chooser and readjusts the location of the frame.
   * @param bool true sets the frame visible; false sets it invisible
   * @param frame RetroFix's JFrame
   */
  public void makeVisible(final boolean bool)
  {
    if (bool)
    {
      fileChooser.setCurrentDirectory(new File(Settings.getImageFile().toString().isEmpty() ? System.getProperty(Texts.FILE_CHOOSER_CURRENT_DIRECTORY.getText()) : Settings.getImageFile().getAbsolutePath()));
      readjustLocation();
    }
    frame.setVisible(bool);
  }
  /**
   * Loads the background image into Retro Fix if the settings say to do so, the paints it
   * onto the screen
   * @param retroFix The only instance of Retro Fix, passed in so its background image can
   * be set and repaint method be called
   */
  public static void loadBackgroundImage(RetroFix retroFix)
  {
    try
    {
      if (Settings.shouldLoadImage())
        retroFix.setBackgroundImage(ImageIO.read(Settings.getImageFile()));
      else
        retroFix.setBackgroundImage(null);
      retroFix.repaint();
    }
    /*
     * Many exceptions may happen, such as IOException, IllegalArgumentException, and
     * NullPointerException.  It's best to use a generic Exception handler that catches
     * every exception.  If an exception happens, it means the image could not be
     * loaded, so do nothing.
     */
    catch (final Exception e)
    {
      // do nothing
    }
    //repaint();
  }
}
