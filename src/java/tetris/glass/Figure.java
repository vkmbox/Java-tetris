package tetris.glass;

import java.util.*;
import tetris.glass.GlassPoint.*;

public abstract class Figure
{
  public enum FigureType
  {
    STICK, LEFTCORNER, RIGHTCORNER, SQUARE, TRIANGLE;
  }  
  public enum Direction
  {
    NORTH, EAST, SOUTH, WEST;
    public static Direction byOrder(int order)
    {
      switch ( order )
      {
        case 0:
          return Direction.NORTH;
        case 1:
          return Direction.EAST;
        case 2:
          return Direction.SOUTH;
        case 3:
          return Direction.WEST;
        default:
          throw new IllegalArgumentException("order may be 0,1,2,3");
      }
    }
  }

  protected Figure.Direction fgXZ;
  public Figure.Direction getFgXZ(){ return fgXZ; }

  protected Figure.Direction fgXY;
  public Figure.Direction getFgXY(){ return fgXY; }
  
  public class FigurePoint
  {
    private final int relX;
    private final int relY;
    private final int relZ;
    private GlassPoint point;
    public GlassPoint getPoint()
    {
      return point;
    }

    public FigurePoint(GlassPoint pPoint, int pX, int pY, int pZ)
    {
      point = pPoint; relX = pX; relY = pY; relZ = pZ;
    }
    
    public void setPosXYZ()
    { 
      switch(Figure.this.getFgXZ())
      {
        case NORTH: 
          point.setPosX(Figure.this.getPosX() + relX);
          point.setPosY(Figure.this.getPosY() + relY);
          point.setPosZ(0);
          break;
        case EAST: 
          point.setPosX(Figure.this.getPosX() - relY);
          point.setPosY(Figure.this.getPosY() - relX);
          point.setPosZ(0);
          break;
        case SOUTH:
          point.setPosX(Figure.this.getPosX() - relX);
          point.setPosY(Figure.this.getPosY() - relY);
          point.setPosZ(0);
          break;
        case WEST:
          point.setPosX(Figure.this.getPosX() + relY);
          point.setPosY(Figure.this.getPosY() + relX);
          point.setPosZ(0);
          break;
        //default:
        //  return 0;
      }
    }
  }
  
  protected final FigureType fgType;
  public FigureType getFgType()
  {
    return fgType;
  }
  
  protected /*final*/ List<FigurePoint> points;
  public List<GlassPoint> getGlassPoints()
  {
    List<GlassPoint> result = new ArrayList(points.size());
    points.stream().forEach(( FigurePoint fp )->{result.add(fp.getPoint());});
    return result;
  }
  
  public void savePointsTo( List<GlassPoint> dest )
  { 
    for (FigurePoint fp : points)
      dest.add(fp.point);
  }
  
  public void setPointsXYZ()
  {
    for (FigurePoint fp : points)
      fp.setPosXYZ();
  }
  
  protected final Color fgColor;
  public Color getColor()
  {
    return fgColor;
  }

  private int posX;
  public int getPosX(){ return posX; }
  public void shiftX(boolean pIsLeft){ posX = posX + (pIsLeft ? -1 : +1); }
    
  private int posY;
  public int getPosY(){ return posY; }
   
  private int posZ;
  public int getPosZ(){ return posZ; }
  public void shiftZ(){ ++posZ; setPointsXYZ(); }
  public void shiftY(){ ++posY; setPointsXYZ(); }
  
  protected Figure( FigureType pFgType, Color pColor, int pPosX, int pPosY, int pPosZ
                  , Figure.Direction pFgXZ, Figure.Direction pFgXY )
  {
    fgType = pFgType;
    fgColor = pColor;
    posX = pPosX;
    posY = pPosY;
    posZ = pPosZ;
    fgXZ = pFgXZ;
    fgXY = pFgXY;
  }
}