package tetris.glass;

import java.util.*;
import tetris.glass.GlassPoint.*;

public class Figure2DSquare extends Figure
{
  public Figure2DSquare(int pPosX, /*int pPosY,*/ Figure.Direction pFgXZ)
  {
    super(Figure.FigureType.SQUARE, Color.YELLOW, pPosX, /*pPosY,*/ 0, 0, pFgXZ, Figure.Direction.NORTH);
    points = Arrays.asList
      ( new FigurePoint(new GlassPoint(Color.YELLOW),0,0,0), new FigurePoint(new GlassPoint(Color.YELLOW),1,0,0)
      , new FigurePoint(new GlassPoint(Color.YELLOW),0,1,0), new FigurePoint(new GlassPoint(Color.YELLOW),1,1,0));
  }
  
  //No rotation for square
  public void setPosXYZ() { }
}