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
  private final ArrayList<GlassPoint>[] points = new ArrayList[height]; //ArrayList<GlassPoint> points = new ArrayList<>();
  
  public void initialize()
  {
    for ( int ii = 0; ii < points.length; ii++)
      points[ii] = null;
    current = null;
    score = 0;
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
      getCurrent().savePointsToArray(points);
      checkAndRemoveLines();
      add();
      return;
    }
    
    getCurrent().shiftY();
  }
  
  private int score;
  public int getScore() { return score; }  
  
  private void checkAndRemoveLines()
  {
    int ii = height - 1;
    while ( ii >=0)
    {  
      if (points[ii] != null && points[ii].size() >= width+1)
      {  
        score++;
        for ( int jj = ii; jj > 0; jj-- )
        {  
          points[jj] = points[jj - 1];
          if (points[jj] != null)
            for (GlassPoint gp: points[jj])
              gp.setPosY(jj+1);
        }
      }
      else
        ii--;
    }
  }
  
  //Checks if figure can be added in top line
  private void add() throws NoPlaceForFigureException //
  {
    Figure pElement = null;
    switch (rnd.nextInt(5))
    {
      case 0:
        pElement = new Figure2DStick(2+rnd.nextInt(width-4), Figure.Direction.byOrder(rnd.nextInt(4)));
        break;
      case 1:
        pElement = new Figure2DLeftCorner(1+rnd.nextInt(width-2), Figure.Direction.byOrder(rnd.nextInt(4)));
        break;
      case 2:
        pElement = new Figure2DRightCorner(1+rnd.nextInt(width-2), Figure.Direction.byOrder(rnd.nextInt(4)));
        break;
      case 3:
        pElement = new Figure2DSquare(1+rnd.nextInt(width-2), Figure.Direction.byOrder(rnd.nextInt(4)));
        break;
      case 4:
        pElement = new Figure2DTriangle(1+rnd.nextInt(width-2), Figure.Direction.byOrder(rnd.nextInt(4)));
        break;
    }
    pElement.setPointsXYZ();
    if (isIntersection(pElement, 0, 0)) throw new NoPlaceForFigureException();
    
    current = pElement;
    //current.savePointsTo(points);
    //figures.addFirst(element);
  }
  
  public boolean isIntersection(Figure pElement, int pShiftX, int pShiftY)
  {
    for (Figure.FigurePoint fp : pElement.points)
    {
      int posX = fp.getPoint().getPosX()+pShiftX, posY = fp.getPoint().getPosY()+pShiftY;
      if ( posX > width || posX < 0 || posY > height ) return true;
      for (ArrayList<GlassPoint> list: points)
        if (list != null)
          for (GlassPoint gp: list)
            if ( fp.getPoint() != gp && posX == gp.getPosX() && posY == gp.getPosY() )
              return true;
    }
    return false;
  }
  
  public boolean isIntersection( Figure pElement, boolean clockwise ) 
  {
    pElement.rotateXY(clockwise);
    boolean result = isIntersection(pElement, 0, 0);
    pElement.rotateXY(!clockwise);
    return result;
  }
  
  public List<GlassPoint>[] getPoints()
  {
    return points; //new ArrayList(points);
    
    /*List<Figure.Point> result = new ArrayList<>();
    ListIterator<Figure> itr = figures.listIterator();
    while (itr.hasNext())
      result.addAll(itr.next().getPoints());
    return result;*/
      
  }
}