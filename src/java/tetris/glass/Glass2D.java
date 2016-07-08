package tetris.glass;

import java.util.*;

public class Glass2D
{
  private static int width;
  private static int height;
  
  public static void SetDimensions(int pWidth, int pHeight)
  {
    if (instance != null)
      throw new RuntimeException("Instance already initialized");
    width = pWidth;
    height = pHeight;
  }
  
  private static Glass2D instance;
  //Not thread-safe because no threads in application
  public static Glass2D getInstance()
  {
    if (width == 0 || height == 0)
      throw new IllegalArgumentException("width and height should be installed, use SetDimensions method");
    if (instance == null) 
      instance = new Glass2D();
    return instance;
  }
  
  private ArrayList<GlassPoint> points = new ArrayList<>();
  private Figure current;
  public Figure getCurrent()
  {
    return current;
  }
  public void Add(Figure pElement)
  {
    current = pElement;
    current.setPointsXYZ();
    current.savePointsTo(points);
    //figures.addFirst(element);
  }
  
  public List<GlassPoint> getPoints()
  {
    return points; //new ArrayList(points);
    
    /*List<Figure.Point> result = new ArrayList<>();
    ListIterator<Figure> itr = figures.listIterator();
    while (itr.hasNext())
      result.addAll(itr.next().getPoints());
    return result;*/
      
  }
}