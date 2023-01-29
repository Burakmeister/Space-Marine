package pl.space_marine.game.iterator;

import java.util.Iterator;

import pl.space_marine.game.impediments.Impediment;
import pl.space_marine.game.rocket.Rocket;

public class ImpedimentList<I> implements Iterable<Impediment> {

    private Rocket rocket;
    private Impediment[] tab;
    private int trueLength=0;
    private int maxOnScreen;

    public ImpedimentList(Rocket rocket, int maxOnScreen){
        this.maxOnScreen = maxOnScreen;
        this.rocket = rocket;
        this.tab = new Impediment[maxOnScreen];
    }
    @Override
    public Iterator<Impediment> iterator() {
        return new ImpedimentsIterator<Impediment>(maxOnScreen, rocket, tab, trueLength);
    }

    public void add(Impediment impediment){
        this.tab[trueLength++] = impediment;
    }

    public void remove(int i){
        int j=0;
        for(j=i; j<=trueLength && j<maxOnScreen; j++){
            tab[i]=tab[i+1];
        }
        if(j==maxOnScreen){
            tab[maxOnScreen-1]=null;
        }
        trueLength--;
    }
}
