package atm;

import atm.currency.Currency;
import atm.currency.CurrencyName;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

import static atm.helper.AtmHelper.numberFormat;


/**
 * Created by maxim.ovechkin on 17.07.2017.
 */
public class Atm {
    private static Logger logger = LoggerFactory.getLogger(Atm.class);
    private static final Currency DEFAULT_CURRENCY = Currency.RUB;
    private Currency currency;
    private List<Cassette> cassetesList = new ArrayList<>();

    public Atm(Currency currency) {
        this.currency = currency;
    }

    public Atm() {
        this(DEFAULT_CURRENCY);
    }

    public final void loadCassette(int nomination, int banknotesCount, int size) {
        cassetesList.add(new Cassette(currency, nomination, banknotesCount, size));
        Collections.sort(cassetesList,
                new Comparator<Cassette>() {
                    public int compare(Cassette o1, Cassette o2) {
                        return o1.compareTo(o2);
                    }
                }
        );
    }

    public void defaultAtmInit() {
        cassetesList.clear();
        loadCassette(100, 100, 200);
        loadCassette(500, 100, 200);
        loadCassette(1000, 100, 200);
        loadCassette(5000, 50, 200);
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
        StringBuilder status = new StringBuilder().append(String.format("\nВалюта банкомата: %s \nДоступный остаток: %s %s", getCurrency(), numberFormat(getBalance()), getCurrency()));
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
        int storeAmount = amount;
        HashMap<Integer, Integer> result = new HashMap<>();
        for (int i = cassetesList.size() - 1; i >= 0; i--) {
            Cassette cassette = cassetesList.get(i);
            int nomination = cassette.getNomination();
            if (nomination <= amount) {
                int count = Math.floorDiv(amount, nomination);
                count = Math.min(count, cassette.getBanknotesCount());
                if (count > 0) {
                    result.put(nomination, count);
                    amount -= count * nomination;
                }
            }
            if (amount == 0) {
                break;
            }
        }
        if (amount == 0) {
            return result;
        } else if (amount > 0) {
            throw new AtmException(String.format("Имеющихся банкнот недостаточно для выдачи требуемой суммы %s %s", numberFormat(storeAmount), getCurrency()));
        } else
            throw new AtmException("Something goes wrong");
    }

    public void printStatus() {
        System.out.println(getStatus());
    }

}
