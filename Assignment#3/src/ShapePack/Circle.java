package ShapePack;

public class Circle extends Shape2D {

    private double radius;

    public Circle(double radius) {
        super("circle");
        this.radius = radius;
    }
    public void setRadius (double radius) {
        this.radius = radius;
    }

    public double getCircleArea () {
        return 3.14159*Math.pow(radius, 2);
    }

    public String getCircleName() {
        return ShapeName;
    }


    @Override
    public String getName () {
        return getCircleName();
    }

    @Override
    public double getArea() {
        return getCircleArea();
    }
}
