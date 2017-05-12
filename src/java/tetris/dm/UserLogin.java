package tetris.dm;

//import javax.persistence.Table;
import javax.persistence.Entity;
import javax.persistence.Id;
//import java.security.MessageDigest;
//import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

@Entity
//@Table(name="USERLOGIN")
public class UserLogin
{
  public UserLogin(){}

  public UserLogin( String login, byte[] passw )
  { this.login = login; setDigest(passw); }
  
  @Id
  private String login;
  public String getLogin()
  { return login; }
  
  private byte[] passw;
  public boolean isPasswCorrect( byte[] value )
  {
    return Arrays.equals(passw, getDigest(value));
  }
  public final void setDigest( byte[] value )
  {
    passw = getDigest(value);
  }
  
  /*static final MessageDigest md;
  
  static 
  { 
     { try
       {
         md = MessageDigest.getInstance("MD5");
       } catch (NoSuchAlgorithmException ex)
       {
         throw new Error("MD5 is not implenmented in JVM");
       }
     }
  };*/
  
  static synchronized byte[] getDigest( byte[] value )
  {
    return value;
    /*md.update( value);
    MessageDigest tc;
    try
    {
      tc = (MessageDigest)md.clone();
    } catch (CloneNotSupportedException ex)
    {
      throw new Error("MD5 is not implenmented in JVM");
    }
    return tc.digest();*/
  }
}