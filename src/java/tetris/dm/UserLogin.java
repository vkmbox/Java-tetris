package tetris.dm;

import javax.persistence.Entity;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

@Entity
public class UserLogin
{
  private String login;
  public String getLogin()
  { return login; }
  
  private byte[] passw;
  public boolean isPasswCorrect( byte[] value )
  {
    return Arrays.equals(passw, getDigest(value));
  }
  public void setDigest( byte[] value )
  {
    passw = getDigest(value);
  }
  
  static final MessageDigest md;
  
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
  };
  
  static byte[] getDigest( byte[] value )
  {
    md.update( value);
    MessageDigest tc;
    try
    {
      tc = (MessageDigest)md.clone();
    } catch (CloneNotSupportedException ex)
    {
      throw new Error("MD5 is not implenmented in JVM");
    }
    return tc.digest();
  }
}