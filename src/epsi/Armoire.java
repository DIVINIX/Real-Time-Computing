package epsi;

import java.util.HashMap;

enum Alcools {
    RHUM("rhum"),
    AMARETTO("amaretto"),
    VODKA("vodka"),
    TEQUILA("tequila"),
    WHISKY("whisky");

    private String name = "";

    Alcools(String name){
        this.name = name;
    }

    public String toString() {
        return name;
    }
}

enum Diluants {
    ORANGE("orange"),
    LIMONADE("limonade"),
    ANANAS("ananas"),
    GRENADINE("grenadine"),
    MENTHE("menthe"),
    CITRON("citron");

    private String name = "";

    Diluants(String name){
        this.name = name;
    }

    public String toString() {
        return name;
    }
}

public class Armoire {

    private HashMap<String, Bottle> bottles;
    private int mutex;

    public Armoire() {
        this.bottles = new HashMap<>();
        this.mutex = 1;
    }

    private synchronized void lock() throws InterruptedException {
        if(this.mutex == 0) {
            this.wait();
        }
        this.mutex--;
    }

    private synchronized void unlock() {
        this.mutex++;
        this.notify();
    }

    public Bottle getBottle(String name, String barman) throws InterruptedException {

        Bottle result;

        this.lock();
        System.out.println("[LOCK] " + name + " par " + barman);
        if(bottles.isEmpty())
        {
            result = null;
        }
        else {
            result = bottles.get(name);
            bottles.remove(name);
        }
        return result;
    }

    public void returnBottle(Bottle bottle, String barman) {
        bottles.put(bottle.getName(), bottle);
        System.out.println("[UNLOCK] " + bottle.getName() + " par " + barman);
        this.unlock();
    }

    public void addBottle(String name, Bottle bottle) {
        this.bottles.put(name, bottle);
    }


}
