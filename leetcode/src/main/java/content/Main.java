package content;

public class Main {
    boolean joy;

    public synchronized void guardedJoy() {
        // This guard only loops once for each special event, which may not
        // be the event we're waiting for.
        while(!joy) {
            try {
                wait();
            } catch (InterruptedException e) {}
        }
        System.out.println("Joy and efficiency have been achieved!");
    }

    public static void main(String[] args) {
        new Main().guardedJoy();
    }
}
