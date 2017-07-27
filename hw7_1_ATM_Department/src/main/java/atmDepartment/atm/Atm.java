package atmDepartment.atm;

import atmDepartment.atm.currency.CurrencyName;
import atmDepartment.atm.currency.Currency;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

import static atmDepartment.atm.helper.AtmHelper.numberFormat;


/**
 * Created by maxim.ovechkin on 17.07.2017.
 */
@SuppressWarnings({"DefaultFileTemplate", "CanBeFinal"})
public class Atm implements WithdrawStrategy{
    private static Logger logger = LoggerFactory.getLogger(Atm.class);
    private static final Currency DEFAULT_CURRENCY = Currency.RUB;
    private int id;
    private Currency currency;
    private List<Cassette> cassetesList = new ArrayList<>();
    private WithdrawStrategy withdrawStrategy;


    public Atm(Currency currency) {
        this.currency = currency;
        this.withdrawStrategy=new WithdrawStrategy_BigFirst();
        this.id=AtmIdGenerator.getNewId();
    }

    public Atm() {
        this(DEFAULT_CURRENCY);
    }

    public int getId() {
        return id;
    }

    public final void loadCassette(int nomination, int banknotesCount, int size) {
        cassetesList.add(new Cassette(currency, nomination, banknotesCount, size));
        Collections.sort(cassetesList,(o1, o2) -> o1.compareTo(o2));
    }

    public int getBalance() {
        int balance = 0;
        for (Cassette cassette : cassetesList) {
            balance += cassette.getBalance();
        }
        return balance;
    }

    public Currency getCurrency() {
        return currency;
    }

    List<Cassette> getCassetesList() {
        return cassetesList;
    }

    public String getStatus() {
        StringBuilder status = new StringBuilder().append(String.format("\nБанкомат №%d\nВалюта банкомата: %s \nДоступный остаток: %s %s",getId(), getCurrency(), numberFormat(getBalance()), getCurrency()));
        for (Cassette cassette : cassetesList) {
            status.append(String.format("\n\tНоминал %s \t  %s шт.", cassette.getNomination(), numberFormat(cassette.getBanknotesCount())));
        }
        return status.toString();
    }

    public boolean putBanknotes(CurrencyName currency, int nomination, int count) {
        logger.info("Попытка внести {} шт {} {} ", numberFormat(count), numberFormat(nomination), getCurrency());
        if (!this.currency.getCurrencyName().equals(currency)) {
            logger.error("Банкомат не принимает {}", currency.toString());
            return false;
        }
        for (Cassette cassette : cassetesList) {
            if (cassette.getNomination() == nomination) {
                try {
                    cassette.putBanknotes(count);
                    logger.info("Успешно");
                    return true;
                } catch (AtmException e) {
                    logger.error(e.getMessage());
                    return false;
                }
            }
        }
        logger.error("Банкомат не принимает банкноты номиналом {}", numberFormat(nomination));
        return false;
    }

    boolean withdrawBanknotes(int nomination, int count) {
        for (Cassette cassette : cassetesList) {
            if (cassette.getNomination() == nomination) {
                try {
                    cassette.withdrawBanknotes(count);
                    return true;
                } catch (AtmException e) {
                    logger.error(e.getMessage());
                    return false;
                }
            }
        }
        logger.error("В банкомате нет банкнот номиналом {}", numberFormat(nomination));
        return false;
    }

    public final boolean withdrawBanknotes(int amount) {
        logger.info("Попытка снять {} {}", numberFormat(amount), getCurrency());
        if (amount < 0) {
            logger.error("Попытка снять отрицательную сумму {}", numberFormat(amount));
            return false;
        }
        try {
            StringBuilder sb = new StringBuilder("Выдано:");
            HashMap<Integer, Integer> change = splitAmountToBanknotes(amount);
            for (Map.Entry<Integer, Integer> entry : change.entrySet()) {
                withdrawBanknotes(entry.getKey(), entry.getValue());
                sb.append(String.format("\n%s %s - %s шт", numberFormat(entry.getKey()), getCurrency(), numberFormat(entry.getValue())));
            }
            logger.info("Успешно");
            logger.info(sb.toString());

            return true;
        } catch (AtmException e) {
            logger.error(e.getMessage());
            return false;
        }
    }

    HashMap<Integer, Integer> splitAmountToBanknotes(int amount) {
       return splitAmountToBanknotes(amount,getCurrency(),getCassetesList());
    }

    public void printStatus() {
        System.out.println(getStatus());
    }


    public WithdrawStrategy getWithdrawStrategy() {
        return withdrawStrategy;
    }

    public void setWithdrawStrategy(WithdrawStrategy withdrawStrategy) {
        this.withdrawStrategy = withdrawStrategy;
        logger.info("Задана стратегия выдачи: "+getWithdrawStrategyName());
    }


    public HashMap<Integer, Integer> splitAmountToBanknotes(int amount, Currency currency, List<Cassette> cassetesList) {
        if (withdrawStrategy==null){
            throw new AtmException("Не задана стратегия выдачи банкнот");
        }
        return withdrawStrategy.splitAmountToBanknotes(amount,currency,cassetesList);
    }

    public String getWithdrawStrategyName() {
        return withdrawStrategy.getWithdrawStrategyName();
    }

    public AtmMemento saveToMemento() {
        return new AtmMemento(this.cassetesList);
    }

    public void restoreFromMemento(AtmMemento memento) {
        cassetesList=AtmMemento.copyCassetesList(memento.getSavedState());
    }
}
