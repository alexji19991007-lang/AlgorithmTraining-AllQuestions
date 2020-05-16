// Using Factory Pattern
// 1. Separate instance/object creation logic from its usage.
// 2. Cleaner especially when we have complicated instance creation logic.
// 3. Provide chances to have different object allocation strategies.
public class ShapeFactory {
    // The key to Factory Pattern is polymorphism.
    public Shape getShape(String shapeType) {
        if (shapeType == null) {
            return null;
        } else if (shapeType.equalsIgnoreCase("CIRCLE")) {
            return new Circle();
        } else if (shapeType.equalsIgnoreCase("SQUARE")) {
            return new Square();
        } else if (shapeType.equalsIgnoreCase("RECTANGLE")) {
            return new Rectangle();
        }
        return null;
    }
}
