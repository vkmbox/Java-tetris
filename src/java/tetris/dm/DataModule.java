package tetris.dm;

import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;

public final class DataModule {
  private final EntityManagerFactory emf;
  private final EntityManager em;
  public EntityManager getEm()
  { return em;}
  
  private DataModule() {
    emf = Persistence.createEntityManagerFactory("app-main");
    em = emf.createEntityManager();
  }
  
  //Single - thread singleton
  private static DataModule instance;
  public static DataModule getInstance()
  {
    if (instance == null) instance = new DataModule();
    return instance;
  }
}