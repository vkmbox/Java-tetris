package tetris.glass;

import java.util.*;

// Coordinates:
//
//  0,0    \
//  ---------
//  |      /
//  |
// \|/
//  |
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
  
  private Random rnd = new Random();
  private final ArrayList<GlassPoint> points = new ArrayList<>();
  
  public void initialize()
  {
    points.clear();
  }
  
  private Figure current;
  public Figure getCurrent()
  {
    return current;
  }
  
  //
  public void doStep() throws NoPlaceForFigureException
  {
    if (getCurrent() == null)
    {  
      add();
      return;
    }
    
    if ( isIntersection(getCurrent(), 0, 1) )
    {  
      getCurrent().savePointsTo(points);
      add();
      return;
    }
    
    getCurrent().shiftY();
    
  }
  
  //Checks if figure can be added in top line
  public void add() throws NoPlaceForFigureException //
  {
    Figure pElement = null;
    switch (rnd.nextInt(5))
    {
      case 0:
      case 1:
      case 2:
      case 3:
      case 4:
        pElement = new Figure2DSquare(rnd.nextInt(width), Figure.Direction.byOrder(rnd.nextInt(4)));
    }
    if (isIntersection(pElement, 0, 0)) throw new NoPlaceForFigureException();
    
    current = pElement;
    current.setPointsXYZ();
    //current.savePointsTo(points);
    //figures.addFirst(element);
  }
  
  public boolean isIntersection(Figure pElement, int pShiftX, int pShiftY)
  {
    for (GlassPoint gp: points)
      for (Figure.FigurePoint fp : pElement.points)
        if ( fp.getPoint() != gp && fp.getPoint().getPosX()+pShiftX == gp.getPosX() 
          && fp.getPoint().getPosY()+pShiftY == gp.getPosY() )
          return true;
    
    return false;
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