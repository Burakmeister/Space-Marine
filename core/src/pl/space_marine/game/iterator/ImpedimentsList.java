package pl.space_marine.game.iterator;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import pl.space_marine.game.impediments.Impediment;

public class ImpedimentsList implements Iterable{
    private List<Impediment> impediments;
    private int cursor;

    public ImpedimentsList(){
        this.cursor=0;
        this.impediments = new ArrayList<>();
    }

    @Override
    public Iterator iterator() {
        return new ImpedimentsIterator<Impediment>();
    }

    public void add(Impediment impediment){
        this.impediments.add(impediment);
    }

    public void remove(Impediment impediment){
        this.impediments.remove(impediment);
    }

    public void remove(int index){
        this.impediments.remove(index);
    }

    public void remove(){
        this.impediments.remove(this.cursor);
    }

    public int size(){
        return this.impediments.size();
    }

    public Impediment get(){
        return this.impediments.get(this.cursor);
    }

    public Impediment get(int idx){
        return this.impediments.get(idx);
    }
}
