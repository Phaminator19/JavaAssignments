import java.io.FileNotFoundException;

public abstract class ListType {
    public abstract void write(String filename);
    public abstract void load(String filename) throws FileNotFoundException;
    public abstract void view();
    public abstract void remove(int index);
    public abstract int size();
}
