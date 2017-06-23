package tetris.dm;

import java.text.MessageFormat;
import java.time.LocalDateTime;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import javax.persistence.Query;

public final class DataModule implements AutoCloseable {
  private final EntityManagerFactory emf;
  private final EntityManager em;
  public EntityManager getEm()
  { return em;}
  
  private DataModule() {
    emf = Persistence.createEntityManagerFactory("app-main");
    em = emf.createEntityManager();
  }
  
  public UserLogin saveUserLogin( String login, byte[] passw )
  {
    UserLogin user = new UserLogin(login, passw );
    EntityTransaction tx = em.getTransaction();
    tx.begin();
    em.persist(user);
    tx.commit();
    return user;
  }
  
  public UserLogin mergeUserLogin( String login, byte[] passw )
  {
    UserLogin user = new UserLogin(login, passw );
    EntityTransaction tx = em.getTransaction();
    tx.begin();
    em.merge(user);
    tx.commit();
    return user;
  }

  public UserLogin getUserByName( String login ) throws NoResultException
  {
    Query qu = em.createQuery("select UL from UserLogin UL where UL.login = :login ");
    qu.setParameter("login", login);
    return (UserLogin)qu.getSingleResult();
  }
  
  public boolean checkUserPassw( String login, byte[] passw )
  {
    try
    {
      UserLogin user = getUserByName(login);
      return user.isPasswCorrect(passw);
    }
    catch (NoResultException ex)
    {
      return false;
    }
  }
  
  public UserGame startUserGame( String userName )
  {
    UserLogin login = getUserByName(userName);
    UserGame game = new UserGame(login, LocalDateTime.now() );
    EntityTransaction tx = em.getTransaction();
    tx.begin();
    em.merge(game);
    tx.commit();
    return game;    
  }

  public void finishUserGame( UserGame game, int score )
  {
    game.setScore(score);
    game.setFinishTime(LocalDateTime.now());
    EntityTransaction tx = em.getTransaction();
    tx.begin();
    em.merge(game);
    tx.commit();
  }

  public String getUserStat( String userName )
  {
    Query qu = em.createQuery
      ( "select count(UG), min(UG.score), max(UG.score), avg(UG.score)"
      + "     , avg(UG.finishTime - UG.id.startTime)" 
      + "  from UserGame UG where UG.id.login.login = :login ");
    qu.setParameter("login", userName);
    Object[] res = (Object[])qu.getSingleResult();
    return MessageFormat.format("Games finished - {0}, min score - {1}, max score - {2}"
      + ", avg score - {3}, average duration - {4}", res[0], res[1], res[2], res[3], res[4]); 
  }
  
  //Single - thread singleton
  private static DataModule instance;
  public static DataModule getInstance()
  {
    if (instance == null) instance = new DataModule();
    return instance;
  }
  
  @Override
  protected void finalize()
  {
    close();
  }

  @Override
  public void close()
  {
    if ( em != null && em.isOpen() ) em.close();
    if ( emf != null && emf.isOpen() ) emf.close();
  }
}