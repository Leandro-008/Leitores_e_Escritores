public class LeitorEscritorMonitor {
    private int leitores = 0;
    private boolean escrevendo = false;

    public synchronized void iniciarLeitura() throws InterruptedException {
        while (escrevendo) {
            wait(); // Espera até que não haja escritores
        }
        leitores++;
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
            wait(); // Espera até que não haja leitores ou escritores
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
