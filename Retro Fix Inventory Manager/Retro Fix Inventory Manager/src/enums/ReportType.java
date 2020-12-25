package enums;

/**
 * Keeps track of the Report Type, which is used in ReportGUI
 * @author Eric Sweeten
 */
public enum ReportType
{
  /**
   * The section reports shows a list of all inventory items in the current sections,
   * including their names, condition, notes, and value for both video games and other
   * inventory, as well as loose price, complete price, new price, box, and manual
   * for video games.
   */
  SECTION,
  /**
   * The overall report shows the number totals in currency values for each of the
   * sections, as well as overall values for all of the sections.
   */
  OVERALL
}
