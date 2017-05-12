package tetris.dm;

import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import javax.persistence.Query;

public final class DataModule {
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
  
  public boolean checkUserPassw( String login, byte[] passw )
  {
    Query qu = em.createQuery("select UL from UserLogin UL where UL.login = :login ");
    qu.setParameter("login", login);
    try
    {
      UserLogin user = (UserLogin)qu.getSingleResult();
      return user.isPasswCorrect(passw);
    }
    catch (NoResultException ex)
    {
      return false;
    }
  }
  
  //Single - thread singleton
  private static DataModule instance;
  public static DataModule getInstance()
  {
    if (instance == null) instance = new DataModule();
    return instance;
  }
}