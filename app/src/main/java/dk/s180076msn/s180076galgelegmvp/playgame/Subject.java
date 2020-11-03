package dk.s180076msn.s180076galgelegmvp.playgame;

public interface Subject {

    void register(Observer newObserver);

    void unregister(Observer deleteObserver);

    void notifyObservers();
}
