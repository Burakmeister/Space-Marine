package pl.space_marine.game.iterator;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ImpedimentsIterator<Impediment> implements Iterator<Impediment> {

    private List<Impediment> list;

    private int cursor;
    public ImpedimentsIterator(){
        this.cursor = -1;
        this.list = new ArrayList<>();
    }
    @Override
    public boolean hasNext() {
        if(this.list.size()>cursor+1){
            return true;
        }
        return false;
    }

    @Override
    public Impediment next() {
        return this.list.get(++cursor);
    }

    @Override
    public void remove() {
        if(cursor>=0 && cursor<list.size())
            this.list.remove(cursor);
    }
    public void add(Impediment impediment){
        this.list.add(impediment);
    }

    public void refreshCursor(){
        this.cursor=0;
    }
    public boolean isEmpty(){
        return list.isEmpty();
    }
}
