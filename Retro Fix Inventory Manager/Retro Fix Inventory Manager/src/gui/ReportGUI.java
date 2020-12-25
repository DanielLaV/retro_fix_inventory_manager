package gui;

import inventory.InventoryItem;
import inventory.VideoGame;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;

import main.RetroFix;
import tools.SpringUtilities;
import enums.InventoryType;
import enums.Money;
import enums.ReportType;
import enums.Texts;

/**
 * This is the GUI that shows either a Section Report or an Overall Report.
 * @author Eric Sweeten, Eric Bergren
 */
public class ReportGUI extends AbstractMiniGUI
{
  /**
   * Used as an argument to SpringUtilities makeCompactGrid method, for the sake of making a compact
   * grid of the GUI elements
   */
  public static final int PADDING_9 = 9;
  /**
   * Used as an argument to SpringUtilities makeCompactGrid method, for the sake of making a compact
   * grid of the GUI elements.  Also used in other makeCompactGrid methods.
   */
  public static final int PADDING_4 = 4;
  /**
   * Used to get blanks in formulating JLabels labelNewPrice and labelValue
   */
  private static final int PADDING_19 = 19;
  /**
   * The JLabel for "Loose", positioned in the first row of the report
   */
  private final JLabel labelLoosePrice = new JLabel(Texts.ATTRIBUTE_LOOSE.getText() + Texts.getBlanks(ModifyInventoryItemGUI.PADDING_3));
  /**
   * The JLabel for "Complete", positioned in the first row of the report
   */
  private final JLabel labelCompletePrice = new JLabel(Texts.ATTRIBUTE_COMPLETE.getText());
  /**
   * The JLabel for "New", positioned in the first row of the report
   */
  private final JLabel labelNewPrice = new JLabel(Texts.ATTRIBUTE_NEW.getText() + Texts.getBlanks(PADDING_19));  
  /**
   * The JLabel for "Value", positioned in the first row of the report
   */
  private final JLabel labelValue = new JLabel(Texts.ATTRIBUTE_VALUE.getText() + Texts.getBlanks(PADDING_19));
  /**
   * This holds all the modifiable labels.  For an overall report, it is 40 by 5.  For a
   * section report, it's 40 by 9 for Video Games and 40 by 4 for Generic Items.
   */
  private JLabel labels[][];
  /**
   * The JLabel on the bottom-left of the Overall Report that says "Totals"
   */
  private final JLabel labelTotals = new JLabel(Texts.LABEL_TOTALS.getText());
  /**
   * The JLabel for "Name", positioned in the first row of the report
   */
  private final JLabel labelName = new JLabel(Texts.ATTRIBUTE_NAME.getText());
  /**
   * The JLabel for "Box", positioned in the first row of the report
   */
  private final JLabel labelBox = new JLabel(Texts.ATTRIBUTE_BOX.getText());
  /**
   * The JLabel for "Manual", positioned in the first row of the report
   */
  private final JLabel labelManual = new JLabel(Texts.ATTRIBUTE_MANUAL.getText());
  /**
   * The JLabel for "Condition", positioned in the first row of the report
   */
  private final JLabel labelCondition = new JLabel(Texts.ATTRIBUTE_CONDITION.getText());
  /**
   * The JLabel for "Notes", positioned in the first row of the report
   */
  private final JLabel labelNotes = new JLabel(Texts.ATTRIBUTE_NOTES.getText());
  /**
   * The JLabel that shows the grand total for all loose prices in the Overall Report
   */
  private final JLabel labelTotalLoosePrice = new JLabel();
  /**
   * The JLabel that shows the grand total for all complete prices in the Overall Report
   */
  private final JLabel labelTotalCompletePrice = new JLabel();
  /**
   * The JLabel that shows the grand total for all new prices in the Overall Report
   */
  private final JLabel labelTotalNewPrice = new JLabel();
  /**
   * The JLabel that shows the grand total for all value prices in the Overall Report
   */
  private final JLabel labelTotalValue = new JLabel();
  /**
   * The String array for the combo box that shows the page numbers.  Shows just
   * page 1 by default.  
   */
  String pageNumbers[] = {Texts.CHARACTER_ONE.getText()};
  /**
   * The JComboBox displaying the page number.  The page number may also be changed from
   * this combo box.
   */
  private final JComboBox<String> comboBoxPageNumber = new JComboBox<>(pageNumbers);
  /**
   * The button for "Okay", which closes the report
   */
  private final JButton buttonOkay = new JButton(Texts.BUTTON_OKAY.getText());
  /**
   * The button for "Printable", which shows a version of the report that can be modified
   * and copied using Ctrl+C.
   */
  private final JButton buttonPrintable = new JButton(Texts.BUTTON_PRINTABLE.getText());
  /**
   * The action listener for various GUI components in ReportGUI, including the combo
   * box to select the page number, the button to display the printable report, the
   * the button to close the printable report.
   */
  private final SimpleActionListener actionListener = new SimpleActionListener();
  /**
   * The JPanel that holds the combo box for page number.  This, as well as the next two
   * similar JPanels, are necessary to allow the GUI components to stretch in the manner
   * they do when the JFrame is resized.
   */
  private final JPanel comboBoxPageNumberPanel = new JPanel();
  /**
   * The JPanel that holds the JButton for "Okay"
   */
  private final JPanel buttonOkayPanel = new JPanel();
  /**
   * The JPanel that holds the JButton for "Printable"
   */
  private final JPanel buttonPrintPanel = new JPanel();
  /**
   * This same border is used in labels[][] above for the JLabels that have values in them.
   * It's declared here so only one instance is made of it, in order to reduce redundancy
   * and to speed up the program.
   */
  private final Border border = BorderFactory.createLineBorder(Color.BLACK);
  /**
   * An instance of PrintableReportGUI, which is the GUI that is a helper to this GUI
   * (ReportGUI).  PrintableReportGUI shows a printable version of the report, which
   * can be used to modify and copy into another program or Excel using Ctrl+C.
   */
  private final PrintableReportGUI prGUI = new PrintableReportGUI();
  /**
   * Keeps track whether this report is a Section Report or an Overall Report
   */
  private ReportType reportType;
  /**
   * The only instance of RetroFix, passed in from RetroFix class
   */
  private final RetroFix retroFix;
  /**
   * Constructor for ReportGUI, which sets up GUI components and passes in the RetroFix object
   * by reference
   */
  public ReportGUI(final RetroFix retroFix)
  {
    this.retroFix = retroFix;
    prGUI.setRetroFixFrame(retroFixFrame);
    frame.setResizable(true);
    comboBoxPageNumber.addActionListener(actionListener);
    buttonPrintable.addActionListener(actionListener);
    labelTotals.setToolTipText(Texts.TOOLTIP_OVERALL_TOTALS.getText());
    prGUI.getButtonOkay().addActionListener(actionListener);
    prGUI.getFrame().addWindowListener(new WindowListener()
    {
      @Override
      public void windowActivated(final WindowEvent e)
      {
      }
      @Override
      public void windowClosed(final WindowEvent e)
      {
      }
      @Override
      public void windowClosing(final WindowEvent e)
      {
        makeVisible(true);
      }
      @Override
      public void windowDeactivated(final WindowEvent e)
      {
      }
      @Override
      public void windowDeiconified(final WindowEvent e)
      {
      }
      @Override
      public void windowIconified(final WindowEvent e)
      {
      }
      @Override
      public void windowOpened(final WindowEvent e)
      {
      }
    });
  }
  /**
   * Clears the values in all the custom labels and takes away their borders
   */
  private void clearLabels()
  {
    for (int i = 0; i < 40; i ++)
    {
      for (int ii = 0; ii < labels[0].length; ii++)
      {
        labels[i][ii].setBorder(null);
        labels[i][ii].setText("");
      }
    }
  }
  /**
   * Helps to set up the panel based on whether it's an Overall Report or a
   * Section Report, and if it's a Section Report, whether it's for Video Games
   * or Generic Items
   * @param x the second dimension in the two-dimensional array, dependent on
   * whether it's an Overall Report (5), Video Games Section Report (9), or
   * Video Games Generic Items Report (4).
   */
  private void fillCustomLabels(final int x)
  {
    for (int i = 0; i < 40; i ++)
    {
      for (int ii = 0; ii < x; ii++)
      {
        labels[i][ii] = new JLabel();
        panel.add(labels[i][ii]);
      }
    }
  }
  /**
   * Helper method to setupPanelByReportType.  It's called twice in the calling method
   * at different times, depending on whether it's an Overall Report or a Section Report
   */
  private void finishUpPanels()
  {
    comboBoxPageNumberPanel.add(comboBoxPageNumber);
    buttonOkayPanel.add(buttonOkay);
    buttonPrintPanel.add(buttonPrintable);
    panel.add(comboBoxPageNumberPanel);
    panel.add(buttonOkayPanel);
    panel.add(buttonPrintPanel);
  }
  /**
   * Gets the JButton "Okay".  This is so RetroFix's action listener can listen in
   * for when it's clicked.
   * @return JButton "Okay"
   */
  public JButton getButtonOkay()
  {
    return buttonOkay;
  }
  /**
   * Gets the report in the form or one long String.  It's simpler dealing with the printable
   * report String rather than the ReportGUI report because the printable String has the entire
   * report, from beginning to end, in one long String, for either OVERALL or the current
   * SECTION, whereas the ReportGUI report only shows one page at a time, and you can select
   * between pages.
   * @return The printable (copy and pastable) report in the form of a String
   */
  private String getReport()
  {
    InventoryType inventoryType = retroFix.getSections().getInventoryType()[retroFix.getCurrentSection()];
    String report = "";
    double totL;
    double totC;
    double totN;
    double totV;
    double grandTotL = 0;
    double grandTotC = 0;
    double grandTotN = 0;
    double grandTotV = 0;
    String c0, c1, c2, c3, c4, c5, c6, c7, c8;
    c1 = c2 = c3 = c4 = c5 = "";
    if (reportType == ReportType.OVERALL)
    {
      report = "\t" + Texts.ATTRIBUTE_LOOSE.getText() + "\t" + Texts.ATTRIBUTE_COMPLETE.getText() + "\t" + Texts.ATTRIBUTE_NEW.getText() + "\t" + Texts.ATTRIBUTE_VALUE.getText() + "\n\n";
      for (int i = 0; i < retroFix.getSections().getSectionNames().length; i++)
      {
        inventoryType = retroFix.getSections().getInventoryType()[i];
        totL = totC = totN = totV = 0;
        for (InventoryItem II : retroFix.getInventoryItems().get(i))
        {
          totL += Money.getDouble(II, Money.LOOSE);
          totC += Money.getDouble(II, Money.COMPLETE);
          totN += Money.getDouble(II, Money.NEW);
          totV += Money.getDouble(II, Money.VALUE);
        }
        c1 = inventoryType == InventoryType.VIDEO_GAME ? Money.getCurrency(totL) : Texts.TEXT_NOT_APPLICABLE.getText();
        c2 = inventoryType == InventoryType.VIDEO_GAME ? Money.getCurrency(totC) : Texts.TEXT_NOT_APPLICABLE.getText();
        c3 = inventoryType == InventoryType.VIDEO_GAME ? Money.getCurrency(totN) : Texts.TEXT_NOT_APPLICABLE.getText();
        c4 = Money.getCurrency(totV);
        report += retroFix.getSections().getSectionNames()[i] + "\t" + c1 + "\t" + c2 + "\t" + c3 + "\t" + c4 + "\n";
        grandTotL += totL;
        grandTotC += totC;
        grandTotN += totN;
        grandTotV += totV;
      }
      c1 = Money.getCurrency(grandTotL);
      c2 = Money.getCurrency(grandTotC);
      c3 = Money.getCurrency(grandTotN);
      c4 = Money.getCurrency(grandTotV);
      report += "\nTotals\t" + c1 + "\t" + c2 + "\t" + c3 + "\t" + c4 + "\n";
    }
    else
    {
      report = Texts.ATTRIBUTE_NAME.getText() + "\t";
      if (inventoryType == InventoryType.VIDEO_GAME)
        report += Texts.ATTRIBUTE_LOOSE.getText() + "\t" + Texts.ATTRIBUTE_COMPLETE.getText() + "\t" + Texts.ATTRIBUTE_NEW.getText() + "\t" + Texts.ATTRIBUTE_BOX.getText() + "\t" + Texts.ATTRIBUTE_MANUAL.getText() + "\t";
      report += Texts.ATTRIBUTE_CONDITION.getText() + "\t" + Texts.ATTRIBUTE_NOTES.getText() + "\t" + Texts.ATTRIBUTE_VALUE.getText() + "\n\n";
      InventoryItem II;
      for (int i = 0; i < retroFix.getInventoryItems().get(retroFix.getCurrentSection()).size(); i++)
      {
        II = retroFix.getInventoryItems().get(retroFix.getCurrentSection()).get(i);
        c0 = II.getName();
        if (II.getInventoryType() == InventoryType.VIDEO_GAME)
        {  
          c1 = ((VideoGame)II).getLoosePrice();
          c2 = ((VideoGame)II).getCompletePrice();
          c3 = ((VideoGame)II).getNewPrice();
          c4 = ((VideoGame)II).getBox();
          c5 = ((VideoGame)II).getManual();
        }
        c6 = II.getCondition();
        c7 = II.getNotes();
        c8 = II.getValue();
        report += c0 + "\t";
        if (inventoryType == InventoryType.VIDEO_GAME)
          report += c1 + "\t" + c2 + "\t" + c3 + "\t" + c4 + "\t" + c5 + "\t";
        report += c6 + "\t" + c7 + "\t" + c8 + "\n";
      }
    }
    return report;
  }
  /**
   * Says whether or not the printable report GUI is visible
   * @return true is prGUI is visible (the frame in it) and false otherwise
   */
  public boolean isPrintableReportGUIVisible()
  {
    return prGUI.isVisible();
  }
  /**
   * Makes the JFrame visible or not.  If making it visible, sets up various things
   * to prepare data to be read by the user, including setting up the panel, setting
   * up the page number combo box, displaying the information on the screen in the
   * appropriate JLabels, and finally, adjusting the location of the JFrame to the
   * center of the screen.
   * @param bool
   */
  public void makeVisible(final boolean bool)
  {
    frame.setVisible(bool);
    if (bool)
    {
      setupPanelByReportType();
      updateComboBoxPageNumber(0);
      updateInfo();
      readjustLocation();
    }
  }
  /**
   * Sets the report type.  This is called from RetroFix before making the
   * JFrame visible.
   * @param report The type of report that will be displayed
   */
  public void setReportType(final ReportType report)
  {
    frame.setTitle(report == ReportType.OVERALL ? Texts.BUTTON_COMPLETE_REPORT.getText() : Texts.BUTTON_SECTION_REPORT.getText());
    reportType = report;
  }
  /**
   * Sets up the main JPanel according to the type of report and whether it's for
   * Video Games or Generic Items if it's a Section Report
   */
  private void setupPanelByReportType()
  {
    InventoryType inventoryType = retroFix.getSections().getInventoryType()[retroFix.getCurrentSection()];
    panel.removeAll();
    int x;
    if (reportType == ReportType.OVERALL)
    {
      x = 5;
      labels = new JLabel[40][x];
      panel.add(new JLabel());
      panel.add(labelLoosePrice);
      panel.add(labelCompletePrice);
      panel.add(labelNewPrice);
      panel.add(labelValue);
      fillCustomLabels(x);
      panel.add(labelTotals);
      panel.add(labelTotalLoosePrice);
      panel.add(labelTotalCompletePrice);
      panel.add(labelTotalNewPrice);
      panel.add(labelTotalValue);
      finishUpPanels();
      labelTotalLoosePrice.setBorder(border);
      labelTotalCompletePrice.setBorder(border);
      labelTotalNewPrice.setBorder(border);
      labelTotalValue.setBorder(border);
      for (int i = 0; i < 2; i++)
      {
        panel.add(new JPanel());
      }
    }
    else
    {
      x = inventoryType == InventoryType.VIDEO_GAME ? 9 : 4;
      labels = new JLabel[40][x];
      panel.add(labelName);
      if (inventoryType == InventoryType.VIDEO_GAME)
      {
        panel.add(labelLoosePrice);
        panel.add(labelCompletePrice);
        panel.add(labelNewPrice);
        panel.add(labelBox);
        panel.add(labelManual);
      }
      panel.add(labelCondition);
      panel.add(labelNotes);
      panel.add(labelValue);
      fillCustomLabels(x);
      finishUpPanels();
      for (int i = 0; i < (inventoryType == InventoryType.VIDEO_GAME ? 6 : 1); i++)
      {
        panel.add(new JPanel());
      }
    }
    SpringUtilities.makeCompactGrid(panel, reportType == ReportType.OVERALL ? 43 : 42, reportType == ReportType.OVERALL ? 5 : (inventoryType == InventoryType.VIDEO_GAME ? PADDING_9 : PADDING_4), 0, 0, 0, 0);
    frame.pack();
  }
  /**
   * Puts the appropriate page numbers in the combo box for page numbers,
   * then selects page the page number that's passed in
   * @param The page number to select in the combo box for page numbers
   */
  private void updateComboBoxPageNumber(final int page)
  {
    comboBoxPageNumber.removeActionListener(actionListener);
    comboBoxPageNumber.removeAllItems();
    int num = (reportType == ReportType.SECTION ? retroFix.getInventoryItems().get(retroFix.getCurrentSection()).size() : retroFix.getSections().getSectionNames().length) / 40 + 1;
    for (int i = 0; i < num; i++)
    {
      comboBoxPageNumber.addItem(i + 1 + "");
    }
    comboBoxPageNumber.addActionListener(actionListener);
    comboBoxPageNumber.setSelectedIndex(page);
  }
  /**
   * Displays the report information in all the appropriate JLabels based on the type
   * of report (OVERALL or SECTION).  Also makes the borders visible for the JLabels
   * that will show numbers.
   */
  private void updateInfo()
  {
    int currentPage = Integer.parseInt(comboBoxPageNumber.getSelectedItem().toString()) - 1;
    updateComboBoxPageNumber(currentPage);
    String c0, c1, c2, c3, c4, c5, c6, c7, c8;
    c0 = c1 = c2 = c3 = c4 = c5 = c6 = c7 = c8 = "";
    if (reportType == ReportType.OVERALL)
    {
      double totL;
      double totC;
      double totN;
      double totV;
      double grandTotL;
      double grandTotC;
      double grandTotN;
      double grandTotV;
      grandTotL = grandTotC = grandTotN = grandTotV = 0;
      int c = 0;
      for (int i = currentPage * 40; i < currentPage * 40 + 40; i++)
      {
        totL = totC = totN = totV = 0;
        if (i >= retroFix.getSections().getSectionNames().length) break;
        for (InventoryItem II : retroFix.getInventoryItems().get(i))
        {
          totL += Money.getDouble(II, Money.LOOSE);
          totC += Money.getDouble(II, Money.COMPLETE);
          totN += Money.getDouble(II, Money.NEW);
          totV += Money.getDouble(II, Money.VALUE);
        }
        c1 = retroFix.getSections().getInventoryType()[i] == InventoryType.GENERIC_ITEM ? "N/A" : Money.getCurrency(totL);
        c2 = retroFix.getSections().getInventoryType()[i] == InventoryType.GENERIC_ITEM ? "N/A" : Money.getCurrency(totC);
        c3 = retroFix.getSections().getInventoryType()[i] == InventoryType.GENERIC_ITEM ? "N/A" : Money.getCurrency(totN);
        c4 = Money.getCurrency(totV);
        labels[c][1].setBorder(border);
        labels[c][2].setBorder(border);
        labels[c][3].setBorder(border);
        labels[c][4].setBorder(border);
        labels[c][0].setText(retroFix.getSections().getSectionNames()[i]);
        labels[c][1].setText(c1);
        labels[c][2].setText(c2);
        labels[c][3].setText(c3);
        labels[c][4].setText(c4);
        grandTotL += totL;
        grandTotC += totC;
        grandTotN += totN;
        grandTotV += totV;
        c++;
      }
      c1 = Money.getCurrency(grandTotL);
      c2 = Money.getCurrency(grandTotC);
      c3 = Money.getCurrency(grandTotN);
      c4 = Money.getCurrency(grandTotV);
      labelTotalLoosePrice.setText(c1);
      labelTotalCompletePrice.setText(c2);
      labelTotalNewPrice.setText(c3);
      labelTotalValue.setText(c4);
    }
    else
    {
      InventoryType inventoryType = retroFix.getSections().getInventoryType()[retroFix.getCurrentSection()];
      int c = 0;
      InventoryItem II;
      for (int i = currentPage * 40; i < currentPage * 40 + 40; i++)
      {
        if (i >= retroFix.getInventoryItems().get(retroFix.getCurrentSection()).size()) break;
        II = retroFix.getInventoryItems().get(retroFix.getCurrentSection()).get(i);
        c0 = II.getName();
        if (II.getInventoryType() == InventoryType.VIDEO_GAME)
        {  
          c1 = ((VideoGame)II).getLoosePrice();
          c2 = ((VideoGame)II).getCompletePrice();
          c3 = ((VideoGame)II).getNewPrice();
          c4 = ((VideoGame)II).getBox();
          c5 = ((VideoGame)II).getManual();
        }
        c6 = II.getCondition();
        c7 = II.getNotes();
        c8 = II.getValue();
        labels[c][0].setText(c0);
        if (inventoryType == InventoryType.VIDEO_GAME)
        {
          labels[c][4].setText(c4);
          labels[c][5].setText(c5);
          labels[c][6].setText(c6);
          labels[c][7].setText(c7);
          labels[c][8].setText(c8);
        }
        else
        {
          c1 = c6;
          c2 = c7;
          c3 = c8;
        }
        labels[c][1].setText(c1);
        labels[c][2].setText(c2);
        labels[c][3].setText(c3);
        labels[c][0].setBorder(border);
        labels[c][1].setBorder(border);
        labels[c][2].setBorder(border);
        labels[c][3].setBorder(border);
        if (inventoryType == InventoryType.VIDEO_GAME)
        {
          labels[c][4].setBorder(border);
          labels[c][5].setBorder(border);
          labels[c][6].setBorder(border);
          labels[c][7].setBorder(border);
          labels[c][8].setBorder(border);
        }
        c++;
      }
    }
    frame.revalidate();
    frame.pack();
  }
  /**
   * The action listener class instantiated for this class as "actionListener".
   * Used to listener to the combo box for page number select, the "Okay" button
   * in prGUI (PrintableReportGUI), and the "Printable" button to show the prGUI
   * @author Eric Sweeten
   */
  private class SimpleActionListener implements ActionListener
  {
    @Override
    public void actionPerformed(ActionEvent arg0)
    {
      if (arg0.getSource() == comboBoxPageNumber)
      {
        clearLabels();
        updateInfo();
      }
      else if (arg0.getSource() == prGUI.getButtonOkay())
      {
        prGUI.makeVisible(false);
        makeVisible(true);
      }
      else
      {
        makeVisible(false);
        prGUI.setReportType(reportType);
        prGUI.display(getReport());
      }
    }
  }
}
