import java.util.concurrent.Semaphore;

public class ReaderWriter {
    static Semaphore mutex = new Semaphore(1);
    static Semaphore writerLock = new Semaphore(1);
    static int ReaderCount = 0;

    static class Reader implements Runnable {
        public void run() {
            try {
                // 1. Entry Section :
                mutex.acquire();
                ReaderCount++;
                if (ReaderCount == 1)
                    writerLock.acquire();
                mutex.release();

                // 2. Reading Section:
                System.out.println(Thread.currentThread().getName() + " is Reading");
                Thread.sleep(1000);

                // 3.Exit Section:
                mutex.acquire();
                ReaderCount--;
                if (ReaderCount == 0)
                    writerLock.release();
                mutex.release();
                System.out.println(Thread.currentThread().getName() + " just Finished Reading");

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    static class Writer implements Runnable {
        public void run() {
            try {
                // 1. Writer Acquire buffer:
                writerLock.acquire();
                System.out.println(Thread.currentThread().getName() + " is Writing");
                Thread.sleep(2000);

                // 2. Writer finished the writing
                writerLock.release();
                System.out.println(Thread.currentThread().getName() + " just Finished Writing");
            } catch (Exception e) {
                e.printStackTrace();
            }

        }

    }

    public static void main(String argss[]) {
        Thread r1 = new Thread(new Reader(), "Prasad");
        Thread r2 = new Thread(new Reader(), "Ajit");
        Thread r3 = new Thread(new Reader(), "Abhishek");
        Thread w1 = new Thread(new Writer(), "Shakesphere");

        w1.start();
        r1.start();
        r2.start();
        r3.start();
    }

}