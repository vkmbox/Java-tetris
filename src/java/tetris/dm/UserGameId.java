package tetris.dm;

import java.io.Serializable;
import java.time.LocalDate;
import javax.persistence.Embeddable;
import javax.persistence.OneToOne;

@Embeddable
public class UserGameId implements Serializable
{
  public UserGameId() {}
  public UserGameId(UserLogin login, LocalDate start) 
  { this.login = login; /*this.start = start;*/ }
  
  @OneToOne
  private UserLogin login;
  public UserLogin getLogin() { return login; }
  public void setLogin( UserLogin value ) { login = value; }
  
  private LocalDate start;
  public LocalDate getStart() { return start; }
  public void setStart( LocalDate value ) { start = value; }
  
  @Override
  public boolean equals(Object obj)
  {
    if ( obj instanceof UserGameId == false ) return false;
    UserGameId oth = (UserGameId)obj;
    return this.login.equals(oth.login) && this.start.equals(oth.start);
  }
  
  @Override
  public int hashCode()
  {
    return login.hashCode()+start.hashCode();
  }
  
}