import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class LeitorEscritorMensagem {
    private final BlockingQueue<String> fila = new LinkedBlockingQueue<>();
    private boolean escrevendo = false;

    public void iniciarLeitura() throws InterruptedException {
        while (escrevendo) {
            Thread.sleep(100); // Simula espera ativa ou condição
        }
        fila.put("Leitura Iniciada por " + Thread.currentThread().getName());
        System.out.println(Thread.currentThread().getName() + " está lendo.");
    }

    public void terminarLeitura() throws InterruptedException {
        fila.put("Leitura Terminada por " + Thread.currentThread().getName());
        System.out.println(Thread.currentThread().getName() + " terminou de ler.");
    }

    public void iniciarEscrita() throws InterruptedException {
        escrevendo = true;
        System.out.println(Thread.currentThread().getName() + " está escrevendo.");
    }

    public void terminarEscrita() throws InterruptedException {
        escrevendo = false;
        fila.put("Escrita Terminada por " + Thread.currentThread().getName());
        System.out.println(Thread.currentThread().getName() + " terminou de escrever.");
    }
}
