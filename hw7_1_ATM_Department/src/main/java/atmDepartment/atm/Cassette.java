package atmDepartment.atm;

import atmDepartment.atm.currency.Currency;

import static atmDepartment.atm.helper.AtmHelper.numberFormat;

/**
 * Created by maxim.ovechkin on 17.07.2017.
 */
public class Cassette implements Comparable<Cassette> {
    private Currency currency;
    private int nomination;
    private int banknotesCount;
    private int size;

    public Cassette(Currency currency, int nomination, int banknotesCount, int size){
        this.currency=currency;
        this.size=size;
        setNomination(nomination);
        setBanknotesCount(banknotesCount);
    }

    private void setNomination(int nomination){
        for (int n:currency.getNominations()) {
            if (n==nomination) {
                this.nomination=nomination;
                return;
            }
        }
        throw new AtmException(String.format("%s - недопустимый номинал валюты %s", numberFormat(nomination), currency));
    }

    private void setBanknotesCount(int banknotesCount){
        if (banknotesCount<0) {
            throw new AtmException(String.format("Попытка установить отрицательное число банкнот %s",numberFormat(banknotesCount)));
        }
        putBanknotes(banknotesCount);
    }

    Currency getCurrency() {
        return currency;
    }

    int getNomination() {
        return nomination;
    }

    int getBanknotesCount() {
        return banknotesCount;
    }

    public int getSize() {
        return size;
    }

    private int getFreeSpace() { return size-banknotesCount;}

    int getBalance(){
        return banknotesCount*nomination;
    }

    void putBanknotes(int count){
        if (count>getFreeSpace()) {
            throw new AtmException(String.format("Кассета заполнена, возможно добавить не более %s банкнот",numberFormat(getFreeSpace())));
        }
        banknotesCount+=count;
    }

    void withdrawBanknotes(int count){
        if (count<0) {
            throw new AtmException(String.format("Попытка снять отрицательное количетво банкнот %s",numberFormat(count)));
        }
        if (count>banknotesCount) {
            throw new AtmException(String.format("Недостаточное количетво банкнот. Попытка снять: %s. Доступное количество:%s",numberFormat(count),numberFormat(banknotesCount)));
        }
        banknotesCount-=count;
    }

    @Override
    public int compareTo(Cassette o) {
        if (o.nomination>this.nomination) {
            return -1;
        } else if (o.nomination<this.nomination) {
            return 1;
        } else {
            return 0;
        }
    }
}
