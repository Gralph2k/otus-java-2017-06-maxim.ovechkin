package atm.Currency;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by maxim.ovechkin on 17.07.2017.
 */
public enum Currency {
    RUB(Currencies.RUB,new ArrayList<>(Arrays.asList(10,50,100,500,1000,5000))),
    EUR(Currencies.EUR,new ArrayList<>(Arrays.asList(5,10,20,50,100,200,500))),
    USD(Currencies.USD,new ArrayList<>(Arrays.asList(1,2,5,10,20,50,100)))
    ;
    private List<Integer> nominations;
    private Currencies currency;

    public List<Integer> getNominations() {
        return nominations;
    }

    public Currencies getCurrency() {
        return currency;
    }

    Currency(Currencies currency, List<Integer> nominations) {
        this.nominations = nominations;
        this.currency = currency;
    }
}
