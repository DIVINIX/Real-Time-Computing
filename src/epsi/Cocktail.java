package epsi;

import java.util.ArrayList;

public class Cocktail {
    private String name;
    private Armoire armoire;
    private ArrayList<Alcools> alcools;
    private ArrayList<Diluants> diluants;

    public Cocktail(String name, Armoire armoire)
    {
        this.name = name;
        this.armoire = armoire;
        this.alcools = new ArrayList<>();
        this.diluants = new ArrayList<>();
    }

    public void addAlcool(Alcools alcool)
    {
        this.alcools.add(alcool);
    }

    public void addDiluant(Diluants diluant)
    {
        this.diluants.add(diluant);
    }

    public void prepare(String barman) throws InterruptedException {
        for (Alcools a : alcools) {
            Bottle bottle = armoire.getBottle(a.toString(), name);
            System.out.println("[Utilisation] " + a.toString() + " pour un " + this.name + " par " + barman);
            Thread.sleep(1000);
            armoire.returnBottle(bottle, barman);
        }

        for (Diluants d : diluants) {
            Bottle bottle = armoire.getBottle(d.toString(), name);
            System.out.println("[Utilisation] " + d.toString() + " pour un " + this.name + " par " + barman);
            Thread.sleep(1000);
            armoire.returnBottle(bottle, barman);
        }
    }

    public String getName()
    {
        return this.name;
    }
}
