package tetris.glass;

import java.util.*;
import java.awt.Color;

public abstract class Figure
{
  public enum Direction
  {
    NORTH, EAST, SOUTH, WEST;
  }

  protected Figure.Direction fgXZ;
  public Figure.Direction getFgXZ(){ return fgXZ; }

  protected Figure.Direction fgXY;
  public Figure.Direction getFgXY(){ return fgXY; }
  
  public class Point
  {
    //Set relative position for Direction.NORTH
    public Point(int pX, int pY, int pZ)
    {
      relX = pX; relY = pY; relZ = pZ;
    }
    
    private final int relX;
    public int getPosX()
    { 
      switch(Figure.this.getFgXZ())
      {
        case NORTH: 
          return Figure.this.getPosX() + relX;
        case EAST: 
          return Figure.this.getPosX() - relY;
        case SOUTH:
          return Figure.this.getPosX() - relX;
        case WEST:
          return Figure.this.getPosX() + relY;
        default:
          return 0;
      }
    }
    
    private final int relY;
    public int getPosY()
    { 
      switch(Figure.this.getFgXZ())
      {
        case NORTH: 
          return Figure.this.getPosY() + relY;
        case EAST: 
          return Figure.this.getPosY() - relX;
        case SOUTH:
          return Figure.this.getPosY() - relY;
        case WEST:
          return Figure.this.getPosY() + relX;
        default:
          return 0;
      }
    }
    
    private final int relZ;
    public int getPosZ(){ return 0; }
    
    //private Color color;
    public Color getColor(){ return Figure.this.fgColor; }
  }
  
  protected final FigureType fgType;
  public FigureType getFgType()
  {
    return fgType;
  }
  
  protected /*final*/ List<Point> points;
  public List<Point> getPoints()
  {
    return new ArrayList(points);
  }
  
  protected /*final*/ Color fgColor;
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
  
  protected Figure( FigureType pFgType, int pPosX, int pPosY, int pPosZ, Figure.Direction pFgXZ, Figure.Direction pFgXY )
  {
    fgType = pFgType;
    posX = pPosX;
    posY = pPosY;
    posZ = pPosZ;
    fgXZ = pFgXZ;
    fgXY = pFgXY;
  }
}