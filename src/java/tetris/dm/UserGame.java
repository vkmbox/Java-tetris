package tetris.dm;

import java.time.LocalDate;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

@Entity
public class UserGame
{
  public UserGame() {}
  public UserGame(UserLogin login, LocalDate start) 
  { 
    this.id = new UserGameId(login, start);
  }
  
  @EmbeddedId
  private UserGameId id;
  private LocalDate finish;
  public void setFinish(LocalDate finish)
  { this.finish = finish; }
  
  private int score;
  public void setScore(int score)
  { this.score = score; }
}