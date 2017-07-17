package atm;

import atm.Currency.Currency;

/**
 * Created by maxim.ovechkin on 17.07.2017.
 */
class Cassette {
    Currency currency;
    int nomination;
    int banknotesCount;


    Cassette(Currency currency, int nomination, int banknotesCount){
        this.currency=currency;
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
        throw new IllegalArgumentException(String.format("%d - недопустимый номинал валюты %s", nomination, currency));
    }

    public void setBanknotesCount(int banknotesCount){
        if (banknotesCount<0) {
            throw new IllegalArgumentException(String.format("Попытка установить отрицательное число банкнот %d",banknotesCount));
        }
        this.banknotesCount=banknotesCount;
    }

    public int getAmount(){
        return banknotesCount*nomination;
    }
}
