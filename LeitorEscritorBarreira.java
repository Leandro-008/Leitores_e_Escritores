import java.util.concurrent.CyclicBarrier;

public class LeitorEscritorBarreira {
    private int leitores = 0;
    private boolean escrevendo = false;
    private final CyclicBarrier barreira;

    public LeitorEscritorBarreira(int numeroDeThreads) {
        this.barreira = new CyclicBarrier(numeroDeThreads);
    }

    public void iniciarLeitura() throws Exception {
        barreira.await(); // Aguarda que todas as threads atinjam esta barreira
        synchronized (this) {
            while (escrevendo) {
                wait();
            }
            leitores++;
        }
        System.out.println(Thread.currentThread().getName() + " está lendo.");
    }

    public synchronized void terminarLeitura() {
        leitores--;
        if (leitores == 0) {
            notifyAll(); // Notifica escritores
        }
        System.out.println(Thread.currentThread().getName() + " terminou de ler.");
    }

    public synchronized void iniciarEscrita() throws InterruptedException {
        while (escrevendo || leitores > 0) {
            wait();
        }
        escrevendo = true;
        System.out.println(Thread.currentThread().getName() + " está escrevendo.");
    }

    public synchronized void terminarEscrita() {
        escrevendo = false;
        notifyAll(); // Notifica todos
        System.out.println(Thread.currentThread().getName() + " terminou de escrever.");
    }
}
