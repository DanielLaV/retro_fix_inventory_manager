package enums;

/**
 * This keeps track of the dimensions for the various Mini-GUIs and the main GUI, RetroFix.
 * Only the printable report GUIs and the main RetroFix GUI are resizable.
 * @author Eric Bergren, Eric Sweeten
 */
public enum Dimensions
{
  /**
   * Default dimensions for BackgroundImageGUI
   */
  BACKGROUND_IMAGE_GUI (280, 130),
  /**
   * Default dimensions for ModifyInventoryItemGUI when the InventoryType equals OTHER
   */
  MODIFY_INVENTORY_ITEM_GUI_FOR_OTHER (300, 230),
  /**
   * Default dimensions for ModifyInventoryItemGUI when the InventoryType equals VIDEO_GAME
   */
  MODIFY_INVENTORY_ITEM_GUI_FOR_VIDEO_GAMES (300, 400),
  /**
   * Default dimensions for PasswordGUI when the user changes their password
   */
  PASSWORD_GUI (400, 200),
  /**
   * Default dimensions for PrintableReportGUI when the ReportType equals OVERALL
   */
  PRINTABLE_REPORT_GUI_OVERALL (500, 800),
  /**
   * Default dimensions for PrintableReportGUI when the ReportType equals SECTION
   */
  PRINTABLE_REPORT_GUI_SECTION (1500, 800),
  /**
   * Default dimensions for PasswordGUI when loading a database
   */
  QUICK_PASSWORD_GUI (400, 135),
  /**
   * Default dimensions for the main RetroFix GUI
   */
  RETRO_FIX (1420, 1000),
  /**
   * Default dimensions for SectionManagerGUI
   */
  SECTION_MANAGER_GUI (300, 160),
  /**
   * Default dimensions for SectionManagerMoreOptionsGUI
   */
  SECTION_MANAGER_MORE_OPTIONS_GUI (420, 100),
  /**
   * Default dimensions for UserManagerGUI
   */
  USER_MANAGER_GUI (330, 220);
  /**
   * The height of the frame
   */
  private int height;
  /**
   * The width of the frame
   */
  private int width;
  /**
   * Constructor for Dimensions enum, which sets the width and height of the frame
   * @param width The width of the frame to be set
   * @param height The height of the frame to be set
   */
  private Dimensions(final int width, final int height)
  {
    this.width = width;
    this.height = height;
  }
  /**
   * Gets the frame height
   * @return The frame height
   */
  public int getHeight()
  {
    return height;
  }
  /**
   * Gets the frame width
   * @return The frame width
   */
  public int getWidth()
  {
    return width;
  }
}
