package main;

/**
 * This is the main class.  It's purpose is to have the main method and to instantiate
 * and use the class where the program will be ran from, which is RetroFix.
 * @author Eric Sweeten
 */
public class Main
{
  /**
   * The program starts execution here.  I instantiate the main class, RetroFix, then
   * call its start method.
   * @param args The arguments passed in to the program, which are ignored.
   */
  public static void main(final String[] args)
  {
    RetroFix retroFix = new RetroFix();
    /*
     * The reason I call its start method, and the reason for its start method, is to take
     * away the warning of not using the RetroFix object retroFix.  Otherwise I can just
     * instantiate RetroFix and it would work the same.
     */
    retroFix.start();
  }
}
