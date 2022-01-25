package lesson1;

public class Square implements Figure{

    @Override
    public void draw() {
        System.out.println("Рисуем квадрат");
    }

    @Override
    public void draw(Color color) {
        this.draw();
        System.out.println("Закрашиваем, цвет - " + color.getColorName());
    }
}
