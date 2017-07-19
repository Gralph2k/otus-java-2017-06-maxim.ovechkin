package atm;

import atm.Currency.CurrencyName;
import atm.Currency.Currency;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by maxim.ovechkin on 17.07.2017.
 */
public class Atm {
    static Logger logger = LoggerFactory.getLogger(Atm.class);
    private static final Currency DEFAULT_CURRENCY = Currency.RUB;
    Currency currency;
    List<Cassette> cassetesList = new ArrayList<>();

    public Atm(Currency currency) {
        this.currency=currency;
    }

    public Atm(){
        this(DEFAULT_CURRENCY);
    }

    public final void loadCassette(int nomination, int banknotesCount, int size) {
        cassetesList.add(new Cassette(currency,nomination,banknotesCount, size));
    }

    protected void init(){
        loadCassette(100,100,200);
        loadCassette(500,100,200);
        loadCassette(1000,100,200);
        loadCassette(5000,100,200);
    }


    public int getAmount(){
        int amount=0;
        for (Cassette cassette:cassetesList) {
            amount+=cassette.getAmount();
        }
        return amount;
    }

    public Currency getCurrency() {
        return currency;
    }

    protected List<Cassette> getCassetesList() {
        return cassetesList;
    }

    public String getStatus(){
        StringBuilder status= new StringBuilder().append(String.format("\nВалюта банкомата: %s \nДоступный остаток: %d %s",getCurrency(), getAmount(), getCurrency()));
        for (Cassette cassette:cassetesList) {
            status.append(String.format("\n\tНоминал %s \t  %d шт.",cassette.getNomination(),cassette.getBanknotesCount()));
        }
       return status.toString();
    }

    public boolean inputBanknotes(CurrencyName currency, int nomination, int count) {
        if (!this.currency.getCurrencyName().equals(currency)) {
            logger.error("Банкомат не принимает %s",currency.toString());
            return false;
        }
        for (Cassette cassette:cassetesList) {
            if (cassette.getNomination()==nomination) {
                try {
                    cassette.inputBanknotes(count);
                    return true;
                } catch (AtmException e ) {
                    logger.error(e.getMessage());
                    return false;
                }
            }
        }
        logger.error("Банкома не принимает банкноты номиналом %d",nomination);
        return false;
    }
}
