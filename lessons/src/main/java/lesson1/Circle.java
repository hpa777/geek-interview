package lesson1;

public class Circle implements Figure{

    @Override
    public void draw() {
        System.out.println("Рисуем круг");
    }

    @Override
    public void draw(Color color) {
        this.draw();
        System.out.println("Закрашиваем, цвет - " + color.getColorName());
    }
}
