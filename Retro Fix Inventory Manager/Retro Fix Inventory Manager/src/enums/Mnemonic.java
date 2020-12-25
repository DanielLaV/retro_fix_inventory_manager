package enums;

/**
 * This is a helper enum that keeps track of the ASCII values for certain characters,
 * for the sake of assigning mnemonics in the JMenus and JMenuItems.  The purpose is
 * to take stray numbers out of the program, as some consider that bad programming
 * practice.
 * @author Eric Sweeten
 */
public enum Mnemonic
{
  /**
   * The ASCII character value for 'A' is 65.
   */
  A (65),
  /**
   * The ASCII character value for 'B' is 66.
   */
  B (66),
  /**
   * The ASCII character value for 'C' is 67.
   */
  C (67),
  /**
   * The ASCII character value for 'F' is 70.
   */
  F (70),
  /**
   * The ASCII character value for 'H' is 72.
   */
  H (72),
  /**
   * The ASCII character value for 'I' is 73.
   */
  I (73),
  /**
   * The ASCII character value for 'L' is 76.
   */
  L (76),
  /**
   * The ASCII character value for 'M' is 77.
   */
  M (77),
  /**
   * The ASCII character value for 'N' is 78.
   */
  N (78),
  /**
   * The ASCII character value for 'P' is 80.
   */
  P (80),
  /**
   * The ASCII character value for 'R' is 82.
   */
  R (82),
  /**
   * The ASCII character value for 'S' is 83.
   */
  S (83),
  /**
   * The ASCII character value for 'T' is 84.
   */
  T (84),
  /**
   * The ASCII character value for 'U' is 85.
   */
  U (85),
  /**
   * The ASCII character value for 'X' is 88.
   */
  X (88);
  /**
   * Keeps the ASCII character value for the character
   */
  private int asciiValue;
  /**
   * Constructor for Mnemonic which sets the ASCII character value
   * @param ascii the ASCII character value
   */
  private Mnemonic(final int ascii)
  {
    asciiValue = ascii;
  }
  /**
   * Gets the ASCII character value
   * @return the ASCII character value stored in asciiValue
   */
  public int getAscii()
  {
    return asciiValue;
  }
}
