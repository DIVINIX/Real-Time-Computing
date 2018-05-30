package epsi;

import java.util.ArrayList;
import java.util.Random;

public class Program {

    private static final int ORDER_NUM = 4;

    public static void main(String[] args) throws InterruptedException {

        Armoire armoire = new Armoire();
        armoire.addBottle(Alcools.RHUM.toString(),new Bottle(Alcools.RHUM.toString(), 75));
        armoire.addBottle(Alcools.AMARETTO.toString(),new Bottle(Alcools.AMARETTO.toString(), 75));
        armoire.addBottle(Alcools.VODKA.toString(),new Bottle(Alcools.VODKA.toString(), 75));
        armoire.addBottle(Alcools.TEQUILA.toString(),new Bottle(Alcools.TEQUILA.toString(), 75));
        armoire.addBottle(Alcools.WHISKY.toString(),new Bottle(Alcools.WHISKY.toString(), 75));
        armoire.addBottle(Diluants.ORANGE.toString(),new Bottle(Diluants.ORANGE.toString(), 75));
        armoire.addBottle(Diluants.LIMONADE.toString(),new Bottle(Diluants.LIMONADE.toString(), 75));
        armoire.addBottle(Diluants.ANANAS.toString(),new Bottle(Diluants.ANANAS.toString(), 75));
        armoire.addBottle(Diluants.GRENADINE.toString(),new Bottle(Diluants.GRENADINE.toString(), 75));
        armoire.addBottle(Diluants.MENTHE.toString(),new Bottle(Diluants.MENTHE.toString(), 75));
        armoire.addBottle(Diluants.CITRON.toString(),new Bottle(Diluants.CITRON.toString(), 75));

        ArrayList<Cocktail> carte = new ArrayList<>();
        ArrayList<Barman> barmans = new ArrayList<>();
        Backlog orders = new Backlog();

        barmans.add(new Barman("Anatole", orders, 2000));
        barmans.add(new Barman("Berthe", orders, 4000));
        barmans.add(new Barman("Celestin", orders, 5000));
        barmans.add(new Barman("Desire", orders, 6000));

        Backlog backlog = new Backlog();

        Cocktail Mojito = new Cocktail("Mojito", armoire);
        Mojito.addAlcool(Alcools.RHUM);
        Mojito.addDiluant(Diluants.CITRON);
        Mojito.addDiluant(Diluants.LIMONADE);
        carte.add(Mojito);

        Cocktail CranberryCookie = new Cocktail("CranberryCookie", armoire);
        CranberryCookie.addAlcool(Alcools.AMARETTO);
        CranberryCookie.addDiluant(Diluants.CITRON);
        CranberryCookie.addDiluant(Diluants.LIMONADE);
        carte.add(CranberryCookie);

        Cocktail Orgasme = new Cocktail("Orgasme", armoire);
        Orgasme.addAlcool(Alcools.WHISKY);
        Orgasme.addAlcool(Alcools.TEQUILA);
        Orgasme.addDiluant(Diluants.MENTHE);
        carte.add(Orgasme);

        Cocktail TequilaSunrise = new Cocktail("TequilaSunrise", armoire);
        TequilaSunrise.addAlcool(Alcools.TEQUILA);
        TequilaSunrise.addDiluant(Diluants.ORANGE);
        TequilaSunrise.addDiluant(Diluants.GRENADINE);
        carte.add(TequilaSunrise);

        Cocktail Planteur = new Cocktail("Planteur", armoire);
        Planteur.addAlcool(Alcools.RHUM);
        Planteur.addDiluant(Diluants.ANANAS);
        Planteur.addDiluant(Diluants.GRENADINE);
        carte.add(Planteur);

        for (Barman b : barmans) {
            b.start();
        }

        Random customers = new Random();
        for (int i=0; i<ORDER_NUM; i++)
        {
            int choice = customers.nextInt(carte.size());
            System.out.println("[Arrivée] Commande n°" + i + " : " + carte.get(choice).getName());
            orders.enqueue(carte.get(choice));
            Thread.sleep(5);
        }

        for (Barman b : barmans) {
            b.join();
        }
    }
}
