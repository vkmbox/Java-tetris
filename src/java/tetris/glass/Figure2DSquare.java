package tetris.glass;

import java.util.*;
import tetris.glass.GlassPoint.*;

public class Figure2DSquare extends Figure
{
  public Figure2DSquare(int pPosX, Figure.Direction pFgXY)
  {
    super(Figure.FigureType.SQUARE, Color.YELLOW, pPosX, -1, 0, Figure.Direction.NORTH, pFgXY);
    points = Arrays.asList
      ( new FigurePoint(new GlassPoint(Color.YELLOW),0,0,0), new FigurePoint(new GlassPoint(Color.YELLOW),1,0,0)
      , new FigurePoint(new GlassPoint(Color.YELLOW),0,1,0), new FigurePoint(new GlassPoint(Color.YELLOW),1,1,0));
    savePointsTo(glassPoints);
  }
  
  //No rotation for square
  @Override
  public void rotateXY(boolean clockwise) { }
}