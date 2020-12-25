package gui;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SpringLayout;

import tools.Settings;
import tools.SpringUtilities;
import enums.Dimensions;
import enums.ReportType;
import enums.Texts;

/**
 * The printable report GUI class, which is a mini-GUI.  This is a relatively straight-forward
 * class that displays the report in a format that can be copied and pasted into another
 * document so it can be printed, or into a program like Excel.
 * @author Kevin Powell, Eric Sweeten
 */
public class PrintableReportGUI extends AbstractMiniGUI
{
  /**
   * The size of the only font used in this class
   */
  private static final int FONT_SIZE = 16;
  /**
   * The horizontal as well as vertical text area size of the only JTextArea used in this class
   */
  private static final int TEXT_AREA_SIZE = 30;
  /**
   * The text area which displays the full report
   */
  private JTextArea textArea = new JTextArea(TEXT_AREA_SIZE, TEXT_AREA_SIZE);
  /**
   * The scroll pane which will allow the user to scroll through and see the entire report
   * in the JTextArea
   */
  private JScrollPane scrollPane = new JScrollPane();
  /**
   * The font used in the JTextArea.  I made it MONOSPACED so it will align vertically as well
   * as horizontally.
   */
  private Font font = new Font(Font.MONOSPACED, Font.PLAIN, FONT_SIZE);
  /**
   * The panel which holds the scroll pane (which in turn holds the text area)
   */
  private JPanel textAreaPanel = new JPanel();
  /**
   * The panel at the bottom which holds the two buttons for Okay and Tab Size
   */
  private JPanel optionsPanel = new JPanel();
  /**
   * The button for "Okay"
   */
  private JButton buttonOkay = new JButton(Texts.BUTTON_OKAY.getText());
  /**
   * The button for "Tab Size"
   */
  private JButton buttonTabSize = new JButton(Texts.BUTTON_TAB_SIZE.getText());
  /**
   * The report type, whether OVERALL or SECTION
   */
  private ReportType reportType;
  /**
   * Constructor for PrintableReportGUI.  Sets up the panel, frame, and the GUI components,
   * then adds an anonymous action listener that listens to the button for Tab Size.  It's
   * anonymous because it only listens to that one particular button and has a very simple
   * function.  After it determines the new tab size, if it's valid, it updates and saves
   * the Settings file.
   */
  public PrintableReportGUI()
  {
    panel.setLayout(new BorderLayout());
    frame.setResizable(true);
    frame.setTitle(Texts.FRAME_TITLE_PRINTABLE_REPORT.getText());
    textArea.setFont(font);
    
    textAreaPanel.setLayout(new SpringLayout());
    scrollPane.getViewport().add(textArea);
    textAreaPanel.add(scrollPane);
    optionsPanel.add(buttonOkay);
    optionsPanel.add(buttonTabSize);
    panel.add(textAreaPanel, BorderLayout.NORTH);
    panel.add(optionsPanel, BorderLayout.SOUTH);
    buttonTabSize.addActionListener(new ActionListener()
    {
      @Override
      public void actionPerformed(ActionEvent arg0)
      {
        int tabSize;
        String message;
        if (reportType == ReportType.OVERALL)
        {
          tabSize = Settings.getTabSizeOverall();
          message = Texts.MESSAGE_TAB_SIZE_OVERALL.getText() + Settings.getTabSizeOverall();
        }
        else
        {
          tabSize = Settings.getTabSizeSection();
          message = Texts.MESSAGE_TAB_SIZE_SECTION.getText() + Settings.getTabSizeSection();
        }
        String str = JOptionPane.showInputDialog(frame, message);
        if (str != null)
        {
          try
          {
            tabSize = Integer.parseInt(str);
          }
          catch (final NumberFormatException exc)
          {
            tabSize = 10;
          }
        }
        textArea.setTabSize(tabSize);
        if (reportType == ReportType.OVERALL)
          Settings.setTabSizeOverall(tabSize);
        else
          Settings.setTabSizeSection(tabSize);
        Settings.saveSettingsFile();
      }
    });
    SpringUtilities.makeCompactGrid(textAreaPanel, 1, 1, 0, 0, 0, 0);
  }
  /**
   * Gets the frame ready in terms of its size to display the report, then readjusts its
   * location, sets the tab size, fills the text area with the report String, then makes
   * the frame visible
   * @param report The report in the form of a String
   */
  public void display(final String report)
  {
    frame.setSize(reportType == ReportType.OVERALL ? Dimensions.PRINTABLE_REPORT_GUI_OVERALL.getWidth() : Dimensions.PRINTABLE_REPORT_GUI_SECTION.getWidth(), reportType == ReportType.OVERALL ? Dimensions.PRINTABLE_REPORT_GUI_OVERALL.getHeight() : Dimensions.PRINTABLE_REPORT_GUI_SECTION.getHeight());
    readjustLocation();
    textArea.setTabSize(reportType == ReportType.OVERALL ? Settings.getTabSizeOverall() : Settings.getTabSizeSection());
    textArea.setText(report);
    frame.setVisible(true);
  }
  /**
   * Gets the button for "Okay"
   * @return JButton buttonOkay
   */
  public JButton getButtonOkay()
  {
    return buttonOkay;
  }
  /**
   * Gets the main frame for this class
   * @return JFrame frame
   */
  public JFrame getFrame()
  {
    return frame;
  }
  /**
   * Makes the frame visible or not
   * @param bool true makes the frame visible; false makes it invisible
   */
  public void makeVisible(final boolean bool)
  {
    readjustLocation();
    frame.setVisible(bool);
  }
  /**
   * Sets the report type
   * @param reportType either OVERALL or SECTIO
   */
  public void setReportType(final ReportType reportType)
  {
    this.reportType = reportType;
  }
}
