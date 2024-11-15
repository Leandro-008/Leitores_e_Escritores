import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {
    
    public static void main(String[] args) throws InterruptedException {
        System.out.println("Testando Semáforos:");
        testarSemaforos();

        System.out.println("\nTestando Mutex:");
        testarMutex();

        System.out.println("\nTestando Monitores:");
        testarMonitores();

        System.out.println("\nTestando Troca de Mensagens:");
        testarTrocaDeMensagens();

        System.out.println("\nTestando Barreiras:");
        testarBarreiras();
    }

    private static void testarSemaforos() throws InterruptedException {
        LeitorEscritorSemaforo leitorEscritor = new LeitorEscritorSemaforo();
        ExecutorService executor = Executors.newFixedThreadPool(5);

        // Criar leitores
        for (int i = 0; i < 3; i++) {
            final int leitorId = i + 1;
            executor.execute(() -> {
                try {
                    leitorEscritor.iniciarLeitura();
                    Thread.sleep(500); // Simula leitura
                    leitorEscritor.terminarLeitura();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }

        // Criar escritores
        for (int i = 0; i < 2; i++) {
            final int escritorId = i + 1;
            executor.execute(() -> {
                try {
                    leitorEscritor.iniciarEscrita();
                    Thread.sleep(500); // Simula escrita
                    leitorEscritor.terminarEscrita();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }

        executor.shutdown();
        while (!executor.isTerminated()) {
            Thread.sleep(100);
        }
    }

    private static void testarMutex() throws InterruptedException {
        LeitorEscritorMutex leitorEscritor = new LeitorEscritorMutex();
        ExecutorService executor = Executors.newFixedThreadPool(5);

        // Criar leitores
        for (int i = 0; i < 3; i++) {
            final int leitorId = i + 1;
            executor.execute(() -> {
                try {
                    leitorEscritor.iniciarLeitura();
                    Thread.sleep(500); // Simula leitura
                    leitorEscritor.terminarLeitura();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }

        // Criar escritores
        for (int i = 0; i < 2; i++) {
            final int escritorId = i + 1;
            executor.execute(() -> {
                try {
                    leitorEscritor.iniciarEscrita();
                    Thread.sleep(500); // Simula escrita
                    leitorEscritor.terminarEscrita();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }

        executor.shutdown();
        while (!executor.isTerminated()) {
            Thread.sleep(100);
        }
    }

    private static void testarMonitores() throws InterruptedException {
        LeitorEscritorMonitor leitorEscritor = new LeitorEscritorMonitor();
        ExecutorService executor = Executors.newFixedThreadPool(5);

        // Criar leitores
        for (int i = 0; i < 3; i++) {
            final int leitorId = i + 1;
            executor.execute(() -> {
                try {
                    leitorEscritor.iniciarLeitura();
                    Thread.sleep(500); // Simula leitura
                    leitorEscritor.terminarLeitura();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }

        // Criar escritores
        for (int i = 0; i < 2; i++) {
            final int escritorId = i + 1;
            executor.execute(() -> {
                try {
                    leitorEscritor.iniciarEscrita();
                    Thread.sleep(500); // Simula escrita
                    leitorEscritor.terminarEscrita();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }

        executor.shutdown();
        while (!executor.isTerminated()) {
            Thread.sleep(100);
        }
    }

    private static void testarTrocaDeMensagens() throws InterruptedException {
        LeitorEscritorMensagem leitorEscritor = new LeitorEscritorMensagem();
        ExecutorService executor = Executors.newFixedThreadPool(5);

        // Criar leitores
        for (int i = 0; i < 3; i++) {
            final int leitorId = i + 1;
            executor.execute(() -> {
                try {
                    leitorEscritor.iniciarLeitura();
                    Thread.sleep(500); // Simula leitura
                    leitorEscritor.terminarLeitura();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }

        // Criar escritores
        for (int i = 0; i < 2; i++) {
            final int escritorId = i + 1;
            executor.execute(() -> {
                try {
                    leitorEscritor.iniciarEscrita();
                    Thread.sleep(500); // Simula escrita
                    leitorEscritor.terminarEscrita();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }

        executor.shutdown();
        while (!executor.isTerminated()) {
            Thread.sleep(100);
        }
    }

    private static void testarBarreiras() throws InterruptedException {
        // Assume 5 threads no total (3 leitores, 2 escritores)
        LeitorEscritorBarreira leitorEscritor = new LeitorEscritorBarreira(5);
        ExecutorService executor = Executors.newFixedThreadPool(5);

        // Criar leitores
        for (int i = 0; i < 3; i++) {
            final int leitorId = i + 1;
            executor.execute(() -> {
                try {
                    leitorEscritor.iniciarLeitura();
                    Thread.sleep(500); // Simula leitura
                    leitorEscritor.terminarLeitura();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
        }

        // Criar escritores
        for (int i = 0; i < 2; i++) {
            final int escritorId = i + 1;
            executor.execute(() -> {
                try {
                    leitorEscritor.iniciarEscrita();
                    Thread.sleep(500); // Simula escrita
                    leitorEscritor.terminarEscrita();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }

        executor.shutdown();
        while (!executor.isTerminated()) {
            Thread.sleep(100);
        }
    }
}
