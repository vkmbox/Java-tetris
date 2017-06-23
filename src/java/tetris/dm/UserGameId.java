package tetris.dm;

import java.io.Serializable;
import java.time.LocalDateTime;
import javax.persistence.Embeddable;
import javax.persistence.ManyToOne;
import javax.persistence.Column;
import javax.persistence.Convert;

@Embeddable
public class UserGameId implements Serializable
{
  public UserGameId() {}
  public UserGameId(UserLogin login, LocalDateTime start) 
  { this.login = login; this.startTime = start; }
  
  @ManyToOne
  private UserLogin login;
  public UserLogin getLogin() { return login; }
  public void setLogin( UserLogin value ) { login = value; }
  
  @Column //(columnDefinition = "TIMESTAMP")
  @Convert(converter = LocalDateTimeAttributeConverter.class)
  private LocalDateTime startTime;
  public LocalDateTime getStartTime() { return startTime; }
  public void setStartTime( LocalDateTime value ) { startTime = value; }
  
  @Override
  public boolean equals(Object obj)
  {
    if ( obj instanceof UserGameId == false ) return false;
    UserGameId oth = (UserGameId)obj;
    return this.login.equals(oth.login) && this.startTime.equals(oth.startTime);
  }
  
  @Override
  public int hashCode()
  {
    return login.hashCode()+startTime.hashCode();
  }
  
}