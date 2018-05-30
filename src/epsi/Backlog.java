package epsi;

import java.util.ArrayList;

public class Backlog {

    private static ArrayList<Cocktail> cocktails;
    private int mutex;

    public Backlog()
    {
        cocktails = new ArrayList<>();
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

    public Cocktail dequeue() throws InterruptedException {

        Cocktail result;

        this.lock();

        if(cocktails.isEmpty())
        {
            result = null;
        }
        else {
            result =  cocktails.remove(0);
        }

        this.unlock();
        return result;
    }

    public void enqueue(Cocktail cocktail) throws InterruptedException {
        this.lock();
        cocktails.add(cocktail);
        this.unlock();
    }

    public boolean isEmpty()
    {
        return cocktails.isEmpty();
    }
}
