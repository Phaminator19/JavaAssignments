package ShapePack;

public class Square extends Shape2D {
    private double sides;

    public Square(double sides) {
        super("square");
        this.sides = sides;
    }
    public void setSides (double sides) {
        this.sides = sides;
    }

    public double getSquareArea () {
        return sides*sides;
    }
    public String getSquareName() {
        return ShapeName;
    }


    @Override
    public String getName () {
        return getSquareName();
    }

    @Override
    public double getArea() {
        return getSquareArea();
    }
}
