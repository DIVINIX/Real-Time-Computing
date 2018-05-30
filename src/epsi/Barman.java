package epsi;

public class Barman extends Thread {

    private String name;
    private Backlog orders;
    private boolean isOnService;
    private int timeToPrepare;

    public Barman(String name, Backlog orders, int timeToPrepare) {
        this.name = name;
        this.orders = orders;
        this.timeToPrepare = timeToPrepare;
        this.isOnService = true;
        System.out.println("[Début de service] " + this.name);
    }

    @Override
    public void run() {
        try {
            Thread.sleep(1000);
            while (!orders.isEmpty())
            {
                Cocktail cocktail = orders.dequeue();
                System.out.println("Le Barman : " + name + ", prépare le cocktail suivant : " + cocktail.getName());
                cocktail.prepare(this.name);
                Thread.sleep(this.timeToPrepare);
                System.out.println("Le Barman : " + name + ", a terminé le cocktail suivant : " + cocktail.getName());
            }

            if(this.isOnService){
                System.out.println("[Fin de service] " + this.name);
                this.isOnService = false;
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
