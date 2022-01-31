package lesson3;

public class PingPong {

    public static void main(String[] args) {
        PingPong pingPong = new PingPong();
        new Thread(()->{
            while (true) {
                pingPong.ping();
            }
        }).start();
        new Thread(()->{
            while (true) {
                pingPong.pong();
            }
        }).start();
    }

    private boolean flag;

    public PingPong() {
        flag = false;
    }

    public synchronized void ping() {
        while (flag) {
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("Ping");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        flag = true;
        this.notifyAll();

    }
    public synchronized void pong() {
        while (!flag) {
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("Pong");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        flag = false;
        this.notifyAll();
    }
}
