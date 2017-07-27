package atmDepartment.atm;

import atmDepartment.atm.currency.Currency;

import java.util.HashMap;
import java.util.List;

/**
 * Created by maxim.ovechkin on 27.07.2017.
 * Выдача с разменом
 * 6000 -> 5*1000+1*500+5*100
 */
public class WithdrawStrategy_WithCharge implements WithdrawStrategy {
    public HashMap<Integer, Integer> splitAmountToBanknotes(int amount, Currency currency, List<Cassette> cassetesList){
        int storeAmount = amount;
        HashMap<Integer, Integer> result = new HashMap<>();
        for (int i = cassetesList.size() - 2; i >= 0; i--) {
            Cassette cassette = cassetesList.get(i);
            int nomination = cassette.getNomination();
            int count=tryGetAmountFromCassete(amount,currency,cassette);
            if (count>0){
                result.put(nomination,count);
                amount-=count*nomination;
            }
            if (amount == 0) {
                break;
            }
        }
        if (amount == 0) {
            return result;
        } else if (amount > 0) {
            return new WithdrawStrategy_BigFirst().splitAmountToBanknotes(amount,currency,cassetesList); //use default strategy if fail
        } else
            throw new AtmException("Something goes wrong");
    }

    private int tryGetAmountFromCassete(int amount, Currency currency, Cassette cassette) {
        int nomination = cassette.getNomination();
        if ((nomination <= amount)&&(cassette.getCurrency().equals(currency))) {
            int count = Math.floorDiv(amount, nomination);
            count = Math.min(count, cassette.getBanknotesCount());
            return count;
        }
        return 0;
    }

    public String getWithdrawStrategyName() {
        return "выдача с разменом";
    }
}