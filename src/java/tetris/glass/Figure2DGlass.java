package tetris.glass;

import java.util.*;

public class Figure2DGlass
{
  private static int width;
  private static int height;
  
  public static void SetDimensions(int pWidth, int pHeight)
  {
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
  
  private List<Figure> figures = new ArrayList();
  
  //public Figure2DGlass()
  
  public void Add(Figure element)
  {
    
  }
}