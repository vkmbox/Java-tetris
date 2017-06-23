package tetris.dm;

import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

@Entity
public class UserGame
{
  public UserGame() {}
  public UserGame(UserLogin login, LocalDateTime start) 
  { 
    this.id = new UserGameId(login, start);
  }
  
  @EmbeddedId
  private UserGameId id;
  
  @Column //(columnDefinition = "TIMESTAMP")
  @Convert(converter = LocalDateTimeAttributeConverter.class)
  private LocalDateTime finishTime;
  public LocalDateTime getFinishTime() { return finishTime; }
  public void setFinishTime(LocalDateTime finish)
  { this.finishTime = finish; }
  
  @Column
  private int score;
  public void setScore(int score)
  { this.score = score; }
}