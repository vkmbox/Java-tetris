package tetris.glass;

import java.util.*;
import java.awt.Color;

public abstract class Figure
{
  public enum Direction
  {
    NORTH, SOUTH, WEST, EAST;
  }

  protected Figure.Direction fgXZ;
  public Figure.Direction getFgXZ(){ return fgXZ; }

  protected Figure.Direction fgXY;
  public Figure.Direction getFgXY(){ return fgXY; }
  
  public class Point
  {
    private int posX;
    public int getPosX(){ return posX; }
    
    private int posY;
    public int getPosY(){ return posY; }
    
    private int posZ;
    public int getPosZ(){ return posZ; }
    
    //private Color color;
    public Color getColor(){ return Figure.this.fgColor; }
  }
  
  protected final FigureType fgType;
  public FigureType getFgType()
  {
    return fgType;
  }
  
  protected final List<Point> points;
  protected final Color fgColor;
  
  protected Figure(FigureType pFgType)
  {
    fgType = pFgType;
  }
}