package ShapePack;

public class Triangle extends Shape2D {
    private double base;
    private double height;

    public Triangle(double base, double height) {
        super("triangle");
        this.base = base;
        this.height = height;
    }
    public void setSides (double base) {
        this.base = base;
    }
    public void setHeight(double height) {
        this.height = height;
    }

    public double getTriangleArea() {
        return (base*height)/2;
    }
    public String getTriangleName() {
        return ShapeName;
    }

    @Override
    public String getName () {
        return getTriangleName();
    }

    @Override
    public double getArea() {
        return getTriangleArea();
    }
}
