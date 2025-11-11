import java.util.concurrent.Semaphore;

class W {
    static Semaphore mutex = new Semaphore(1);
    static Semaphore wrt = new Semaphore(1);
    static int readers = 0;

    static class Reader implements Runnable {
        int id;
        Reader(int id) { this.id = id; }
        public void run() {
            try {
                mutex.acquire();
                readers++;
                if (readers == 1) wrt.acquire();
                mutex.release();

                System.out.println("Reader " + id + " reading");
                Thread.sleep(500);

                mutex.acquire();
                readers--;
                if (readers == 0) wrt.release();
                mutex.release();

                System.out.println("Reader " + id + " finished");
            } catch (InterruptedException e) {}
        }
    }

    static class Writer implements Runnable {
        int id;
        Writer(int id) { this.id = id; }
        public void run() {
            try {
                wrt.acquire();

                System.out.println("Writer " + id + " writing");
                Thread.sleep(500);

                wrt.release();
                System.out.println("Writer " + id + " finished");
            } catch (InterruptedException e) {}
        }
    }
}

public class RW {
    public static void main(String[] args) {
        new Thread(new W.Reader(1)).start();
        new Thread(new W.Reader(2)).start();
        new Thread(new W.Writer(1)).start();
        new Thread(new W.Reader(3)).start();
        new Thread(new W.Writer(2)).start();
        new Thread(new W.Reader(4)).start();
    }
}