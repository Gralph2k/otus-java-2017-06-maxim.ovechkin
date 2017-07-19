package atm;

import atm.Currency.Currency;

/**
 * Created by maxim.ovechkin on 17.07.2017.
 */
class Cassette {
    Currency currency;
    private int nomination;
    private int banknotesCount;
    private int size;
    private static final int DEFAULT_CASSETTE_SIZE = 200;


    Cassette(Currency currency, int nomination, int banknotesCount, int size){
        this.currency=currency;
        this.size=size;
        setNomination(nomination);
        setBanknotesCount(banknotesCount);
    }

    Cassette(Currency currency, int nomination, int banknotesCount){
        this(currency,nomination,banknotesCount,DEFAULT_CASSETTE_SIZE);
    }

    private void setNomination(int nomination){
        for (int n:currency.getNominations()) {
            if (n==nomination) {
                this.nomination=nomination;
                return;
            }
        }
        throw new AtmException(String.format("%d - недопустимый номинал валюты %s", nomination, currency));
    }

    private void setBanknotesCount(int banknotesCount){
        if (banknotesCount<0) {
            throw new AtmException(String.format("Попытка установить отрицательное число банкнот %d",banknotesCount));
        }
        inputBanknotes(banknotesCount);
    }

    public Currency getCurrency() {
        return currency;
    }

    public int getNomination() {
        return nomination;
    }

    public int getBanknotesCount() {
        return banknotesCount;
    }

    public int getSize() {
        return size;
    }

    private int getFreeSpace() { return size-banknotesCount;}

    public int getAmount(){
        return banknotesCount*nomination;
    }

    public void inputBanknotes(int count){
        if (count>getFreeSpace()) {
            throw new AtmException(String.format("Кассета заполнена, возможно добавить не более %d банкнот",getFreeSpace()));
        }
        banknotesCount+=count;
    }

    public void outputBanknotes(int count){
        if (count>getBanknotesCount()) {
            throw new AtmException(String.format("Недостаточно банкнот в кассете, возможно получить не более %d шт",getBanknotesCount()));
        }
        banknotesCount-=count;
    }

}
