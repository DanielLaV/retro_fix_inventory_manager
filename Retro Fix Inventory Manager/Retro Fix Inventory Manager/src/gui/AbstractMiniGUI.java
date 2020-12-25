package gui;

import java.awt.event.WindowEvent;
import java.awt.event.WindowFocusListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SpringLayout;

/**
 * This is the abstract class for all the mini-GUIs, including BackgrounImageGUI,
 * ModifyInventoryItemGUI, PasswordGUI, PrintableReportGUI, ReportGUI,
 * SectionManagerGUI, SectionManagerMoreOptionsGUI, and UserManagerGUI.
 * @author Eric Sweeten
 */
public abstract class AbstractMiniGUI
{
  /**
   * Every mini-GUI has a main JFrame, which is named frame
   */
  protected final JFrame frame = new JFrame();
  /**
   * Every mini-GUI has a main JPanel, which is named panel
   */
  protected final JPanel panel = new JPanel();
  /**
   * This gets RetroFix's main frame for positioning purposes in the mini-GUI
   */
  protected JFrame retroFixFrame;
  /**
   * Constructor for AbstractMiniGUI.  The layout is set to a SpringLayout, because
   * SpringUtilities is used to make a compact grid of the GUI components.  The panel
   * is added to the frame, as always happens.  The frame is always set to non-
   * resizable, though that is changed for PrintableReportGUI and RetroFix (main GUI).
   * The focus listener is to ensure focus is returned to this mini-GUI if you try and
   * click out of it, to the RetroFix GUI, while the mini-GUI is open.  It's to prevent
   * the application from allowing you to do that, because that can cause all kinds of
   * confusion.
   */
  public AbstractMiniGUI()
  {
    panel.setLayout(new SpringLayout());
    frame.add(panel);
    //frame.setLocationRelativeTo(null);
    frame.setResizable(false);
    frame.addWindowFocusListener(new WindowFocusListener()
    {
      @Override
      public void windowGainedFocus(final WindowEvent arg0)
      {
      }
      @Override
      public void windowLostFocus(final WindowEvent arg0)
      {
        if (frame.isVisible())
        {
          frame.requestFocus();
        }
      }
    });
  }
  /**
   * Finds out if the main frame is visible
   * @return true if JFrame frame is visible, false otherwise
   */
  public boolean isVisible()
  {
    return frame.isVisible();
  }
  /**
   * Readjusts location to the center of RetroFix's JFrame mainFrame
   */
  public void readjustLocation()
  {
    frame.setLocationRelativeTo(retroFixFrame);
  }
  /**
   * Passes in RetroFix's frame for the sake of center positioning
   * @param retroFixFrame RetroFix's frame
   */
  public void setRetroFixFrame(final JFrame retroFixFrame)
  {
    this.retroFixFrame = retroFixFrame;    
  }
}
