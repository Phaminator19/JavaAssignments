package ShapePack;

public class Pyramid extends Shape3D{
    private double length;
    private double width;
    private double height;


    public Pyramid (double length, double width, double height) {
        super("pyramid");
        this.length = length;
        this.width = width;
        this.height = height;
    }

    public void setLength (double length) {
        this.length = length;
    }
    public void setWidth (double width) {
        this.width = width;
    }
    public void setHeight (double height) {
        this.height = height;
    }

    public double getAreaPyramid() {
        return (length*width+length*Math.sqrt(Math.pow((width/2),2)+Math.pow(height, 2))+width*Math.sqrt(Math.pow((length/2),2)+Math.pow(height,2)));

    }

    public double getVolumePyramid() {
        return (length*width*height)/3;
    }

    @Override
    public String getName() {
        return ShapeName;
    }
    @Override
    public double getArea() {
        return getAreaPyramid();
    }
    @Override
    public double getVolume() {
        return getVolumePyramid();
    }
}
