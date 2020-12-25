package tools;

import java.io.Serializable;

import enums.Texts;

/**
 * Keeps track of a user's name, password, and their permissions
 * @author Eric Bergren, Eric Sweeten
 */
public class User implements Serializable
{
  /**
   * The generated serializable ID for the sake of being able to save instances of
   * this class to file
   */
  private static final long serialVersionUID = 3938569312973622258L;
  /**
   * The usrer's name
   */
  private String name;
  /**
   * The user's password
   */
  private String password = "";
  /**
   * Whether or not the user can add a section
   */
  private boolean canAddSection;
  /**
   * Whether or not the user can modify a section
   */
  private boolean canModifySection;
  /**
   * Whether or not the user can delete a section
   */
  private boolean canDeleteSection;
  /**
   * Whether or not the user can add an item
   */
  private boolean canAddItem;
  /**
   * Whether or not the user can modify an item
   */
  private boolean canModifyItem;
  /**
   * Whether or not the user can delete an item
   */
  private boolean canDeleteItem;
  /**
   * Whether or not the user can order attributes (such as alphabetically or numerically)
   */
  private boolean canOrder;
  /**
   * Constructor for a User.  By default, administrators have all permissions set to true,
   * while other users have all permissions set to false.
   * @param name
   */
  public User(final String name)
  {
    this.name = name;
    if (name.equals(Texts.TEXT_ADMIN.getText()))
    {
      canAddItem = canModifyItem = canDeleteItem = canAddSection = canModifySection = canDeleteSection = canOrder = true;
    }
  }
  /**
   * States whether or not the user can add an item
   * @return boolean canAddItem
   */
  public boolean getCanAddItem()
  {
    return canAddItem;
  }
  /**
   * States whether or not the user can add a section
   * @return boolean canAddSection
   */
  public boolean getCanAddSection()
  {
    return canAddSection;
  }
  /**
   * States whether or not the user can delete an item
   * @return boolean canDeleteItem
   */
  public boolean getCanDeleteItem()
  {
    return canDeleteItem;
  }
  /**
   * States whether or not the user can delete a section
   * @return boolean canDeleteSection
   */
  public boolean getCanDeleteSection()
  {
    return canDeleteSection;
  }
  /**
   * States whether or not the user can modify an item
   * @return boolean canModifyItem
   */
  public boolean getCanModifyItem()
  {
    return canModifyItem;
  }
  /**
   * States whether or not the user can modify a section
   * @return boolean canModifySection
   */
  public boolean getCanModifySection()
  {
    return canModifySection;
  }
  /**
   * States whether or not the user can order
   * @return boolean canOrder
   */
  public boolean getCanOrder()
  {
    return canOrder;
  }
  /**
   * Gets the user's name
   * @return String name
   */
  public String getName()
  {
    return name;
  }
  /**
   * Gets the user's password
   * @return String password
   */
  public String getPassword()
  {
    return password;
  }
  /**
   * Sets whether or not the user can add an item
   * @param bool true if the user can add an item; false otherwise
   */
  public void setCanAddItem(final boolean bool)
  {
    canAddItem = bool;
  }
  /**
   * Sets whether or not the user can add a section
   * @param bool true if the user can add a section; false otherwise
   */
  public void setCanAddSection(final boolean bool)
  {
    canAddSection = bool;
  }
  /**
   * Sets whether or not the user can delete an item
   * @param bool true if the user can delete an item; false otherwise
   */
  public void setCanDeleteItem(final boolean bool)
  {
    canDeleteItem = bool;
  }
  /**
   * Sets whether or not the user can delete a section
   * @param bool true if the user can delete a section; false otherwise
   */
  public void setCanDeleteSection(final boolean bool)
  {
    canDeleteSection = bool;
  }
  /**
   * Sets whether or not the user can modify an item
   * @param bool true if the user can modify an item; false otherwise
   */
  public void setCanModifyItem(final boolean bool)
  {
    canModifyItem = bool;
  }
  /**
   * Sets whether or not the user can modify a section
   * @param bool true if the user can modify a section; false otherwise
   */
  public void setCanModifySection(final boolean bool)
  {
    canModifySection = bool;
  }
  /**
   * Sets whether or not the user can order
   * @param bool true if the user can order; false otherwise
   */
  public void setCanOrder(final boolean bool)
  {
    canOrder = bool;
  }
  /**
   * Sets the user's name
   * @param name the user's name
   */
  public void setName(final String name)
  {
    this.name = name;
  }
  /**
   * Sets the user's password
   * @param password the user's password
   */
  public void setPassword(final String password)
  {
    this.password = password;
  }
}
