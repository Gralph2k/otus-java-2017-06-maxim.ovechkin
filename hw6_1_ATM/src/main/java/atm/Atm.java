package atm;

import atm.Currency.Currency;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by maxim.ovechkin on 17.07.2017.
 */
public class Atm {
    List<Cassette> cassetesList = new ArrayList<>();

    public Atm() {

    }

    public void loadCassettes(){
        cassetesList.add(new Cassette(Currency.RUB,100,100));
        cassetesList.add(new Cassette(Currency.RUB,500,100));
        cassetesList.add(new Cassette(Currency.RUB,1000,100));
        cassetesList.add(new Cassette(Currency.RUB,5000,100));
    }

    public static void main(String[] args) {
        Atm atm = new Atm();
        atm.loadCassettes();
        System.out.println(atm.getAmount());
    }

    public int getAmount(){
        int amount=0;
        for (Cassette cassette:cassetesList) {
            amount+=cassette.getAmount();
        }
        return amount;
    }
}
