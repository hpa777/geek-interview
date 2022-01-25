package lesson1;

public class Triangle implements Figure {

    @Override
    public void draw() {
        System.out.println("Рисуем треугольник");
    }

    @Override
    public void draw(Color color) {
        this.draw();
        System.out.println("Закрашиваем, цвет - " + color.getColorName());
    }
}
