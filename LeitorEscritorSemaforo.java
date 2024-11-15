import java.util.concurrent.Semaphore;

public class LeitorEscritorSemaforo {
    private int leitores = 0;
    private final Semaphore mutex = new Semaphore(1);
    private final Semaphore recurso = new Semaphore(1);

    public void iniciarLeitura() throws InterruptedException {
        mutex.acquire();
        leitores++;
        if (leitores == 1) {
            recurso.acquire(); // Bloqueia escritores
        }
        mutex.release();
        System.out.println(Thread.currentThread().getName() + " está lendo.");
    }

    public void terminarLeitura() throws InterruptedException {
        mutex.acquire();
        leitores--;
        if (leitores == 0) {
            recurso.release(); // Libera escritores
        }
        mutex.release();
        System.out.println(Thread.currentThread().getName() + " terminou de ler.");
    }

    public void iniciarEscrita() throws InterruptedException {
        recurso.acquire();
        System.out.println(Thread.currentThread().getName() + " está escrevendo.");
    }

    public void terminarEscrita() {
        System.out.println(Thread.currentThread().getName() + " terminou de escrever.");
        recurso.release();
    }
}
