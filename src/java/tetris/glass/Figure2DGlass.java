package tetris.glass;

import java.util.*;

public class Figure2DGlass
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
  
  private static Figure2DGlass instance;
  //Not thread-safe because no threads in application
  public static Figure2DGlass getInstance()
  {
    if (width == 0 || height == 0)
      throw new IllegalArgumentException("width and height should be installed, use SetDimensions method");
    if (instance == null) 
      instance = new Figure2DGlass();
    return instance;
  }
  
  private LinkedList<Figure> figures = new LinkedList<>();

  public Figure getCurrent()
  {
    return figures.getFirst();
  }
  
  public void Add(Figure element)
  {
    figures.addFirst(element);
  }
  
  public List<Figure.Point> getPoints()
  {
    List<Figure.Point> result = new ArrayList<>();
    ListIterator<Figure> itr = figures.listIterator();
    while (itr.hasNext())
      result.addAll(itr.next().getPoints());
    return result;
      
  }
}