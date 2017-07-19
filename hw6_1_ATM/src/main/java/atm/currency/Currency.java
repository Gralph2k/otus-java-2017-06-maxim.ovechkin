package atm.currency;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by maxim.ovechkin on 17.07.2017.
 */
public enum Currency {
    RUB(CurrencyName.RUB,new ArrayList<>(Arrays.asList(10,50,100,500,1000,5000))),
    EUR(CurrencyName.EUR,new ArrayList<>(Arrays.asList(5,10,20,50,100,200,500))),
    USD(CurrencyName.USD,new ArrayList<>(Arrays.asList(1,2,5,10,20,50,100)))
    ;
    private List<Integer> nominations;
    private CurrencyName currencyName;

    public List<Integer> getNominations() {
        return nominations;
    }

    public CurrencyName getCurrencyName() {
        return currencyName;
    }

    Currency(CurrencyName currencyName, List<Integer> nominations) {
        this.nominations = nominations;
        this.currencyName = currencyName;
    }

    @Override
    public String toString(){
        return currencyName.name();
    }
}
