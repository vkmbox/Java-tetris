package tetris.glass;

import java.util.*;
import java.awt.Color;

public abstract class Figure
{
  public enum FigureType
  {
    STICK, LEFTCORNER, RIGHTCORNER, SQUARE, TRIANGLE;
  }  
  public enum Direction
  {
    NORTH, EAST, SOUTH, WEST;
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
    
  private int posY;
  public int getPosY(){ return posY; }
    
  private int posZ;
  public int getPosZ(){ return posZ; }
  
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