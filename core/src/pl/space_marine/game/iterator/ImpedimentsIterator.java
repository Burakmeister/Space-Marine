package pl.space_marine.game.iterator;

import com.badlogic.gdx.Gdx;

import java.util.Iterator;

import pl.space_marine.game.impediments.Impediment;
import pl.space_marine.game.rocket.Rocket;

public class ImpedimentsIterator<E> implements Iterator<E> {

    public static final float SCALE = 0.2f;
    public static final int DISTANCE_TO_ROCKET = (int) (7* Gdx.graphics.getHeight()/6*SCALE);  //max
    public static final int DISTANCE_TO_ROCKET_WIDTH = (int) (Gdx.graphics.getWidth()*SCALE);  //max
    public static final int MAX_IMPEDIMENTS_ON_SCREEN = 300;
    private Impediment[] tab;
    private int trueLength = 0;
    private int cursor;
    private Rocket rocket;
    private int maxOnScreen;

    public ImpedimentsIterator(int maxOnScreen, Rocket rocket, Impediment[] tab, int trueLength) {
        this.cursor = -1;
        this.trueLength = trueLength;
        this.tab = tab;
        this.rocket = rocket;
        this.maxOnScreen = maxOnScreen;
    }

    @Override
    public boolean hasNext() {
        if (trueLength > cursor + 1) {
            return true;
        }
        return false;
    }

    @Override
    public E next() {
        return (E) tab[++cursor];
    }

    @Override
    public void remove() {
        for (int i = cursor; i < trueLength - 1; i++) {
            tab[i] = tab[i + 1];
        }
        this.tab[trueLength - 1] = null;
        trueLength--;
    }


    public void add(Object obj) throws Exception {
        if (obj instanceof Impediment) {
            if (trueLength < maxOnScreen) {
                tab[trueLength] = (Impediment) obj;
                trueLength++;
                return;
            }

            int i;
            Impediment temp = tab[0];
            for (i = 1; i < tab.length && tab[i] != null; i++) {
                if(temp.getX() > rocket.getX()+DISTANCE_TO_ROCKET_WIDTH || temp.getX() < rocket.getX()-DISTANCE_TO_ROCKET_WIDTH || temp.getY() < rocket.getY()-DISTANCE_TO_ROCKET){
                    swap(temp, (Impediment) obj);
                    return;
                }
                temp=tab[i];
            }
        } else {
            throw new Exception("List store only Impediment object!");
        }
    }

    public void refreshCursor() {
        this.cursor = -1;
    }

    public boolean isEmpty() {
        return tab.length == 0;
    }

    public void swap(Impediment oldImp, Impediment newImp) {
        for (int i = 0; i < tab.length; i++) {
            if (tab[i].equals(oldImp)) {
                tab[i] = newImp;
                return;
            }
        }
    }

    public int size() {
        return this.trueLength;
    }

}
