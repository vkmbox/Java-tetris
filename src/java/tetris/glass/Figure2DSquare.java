package tetris.glass;

public class Figure2DSquare extends Figure
{
  public Figure2DSquare(int pPosX, int pPosY, Figure.Direction pFgXZ)
  {
    super(FigureType.SQUARE, pPosX, pPosY, 0, pFgXZ, Figure.Direction.NORTH);
    //...
  }
}