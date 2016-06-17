package tetris.glass;

public abstract class Figure
{
  protected final FigureType fgType;
  public FigureType getFgType()
  {
    return fgType;
  }
  protected Figure(FigureType pFgType)
  {
    fgType = pFgType;
  }
}