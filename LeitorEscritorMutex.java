import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.Condition;

public class LeitorEscritorMutex {
    private int leitores = 0;
    private final Lock lock = new ReentrantLock();
    private final Condition podeEscrever = lock.newCondition();
    private boolean escrevendo = false;

    public void iniciarLeitura() throws InterruptedException {
        lock.lock();
        try {
            while (escrevendo) {
                podeEscrever.await(); // Espera até que nenhum escritor esteja escrevendo
            }
            leitores++;
        } finally {
            lock.unlock();
        }
        System.out.println(Thread.currentThread().getName() + " está lendo.");
    }

    public void terminarLeitura() {
        lock.lock();
        try {
            leitores--;
            if (leitores == 0) {
                podeEscrever.signal(); // Notifica escritores
            }
        } finally {
            lock.unlock();
        }
        System.out.println(Thread.currentThread().getName() + " terminou de ler.");
    }

    public void iniciarEscrita() throws InterruptedException {
        lock.lock();
        try {
            while (escrevendo || leitores > 0) {
                podeEscrever.await(); // Espera até que não haja leitores ou escritores
            }
            escrevendo = true;
        } finally {
            lock.unlock();
        }
        System.out.println(Thread.currentThread().getName() + " está escrevendo.");
    }

    public void terminarEscrita() {
        lock.lock();
        try {
            escrevendo = false;
            podeEscrever.signalAll(); // Notifica todos
        } finally {
            lock.unlock();
        }
        System.out.println(Thread.currentThread().getName() + " terminou de escrever.");
    }
}
