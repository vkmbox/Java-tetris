package tetris.glass;

import java.util.*;
import tetris.glass.GlassPoint.*;

public class Figure2DTriangle extends Figure
{
  public Figure2DTriangle(int pPosX, Figure.Direction pFgXY)
  {
    super(Figure.FigureType.SQUARE, Color.GREEN, pPosX, -1, 0, Figure.Direction.NORTH, pFgXY);
    points = Arrays.asList
      ( new FigurePoint(new GlassPoint(Color.GREEN),-1,0,0), new FigurePoint(new GlassPoint(Color.GREEN),0,0,0)
      , new FigurePoint(new GlassPoint(Color.GREEN),1,0,0), new FigurePoint(new GlassPoint(Color.GREEN),0,1,0));
    savePointsTo(glassPoints);
  }
  
}
