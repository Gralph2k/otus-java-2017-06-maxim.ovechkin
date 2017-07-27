package atmDepartment.atm;


import java.util.HashMap;
import java.util.List;

import atmDepartment.atm.currency.Currency;

import static atmDepartment.atm.helper.AtmHelper.numberFormat;

/**
 * Created by maxim.ovechkin on 24.07.2017.
 * Выдача максимально крупными купюрами
 * 6000 -> 1*5000+1*1000
 */
public class WithdrawStrategy_BigFirst implements WithdrawStrategy {
    public HashMap<Integer, Integer> splitAmountToBanknotes(int amount, Currency currency, List<Cassette> cassetesList){
        int storeAmount = amount;
        HashMap<Integer, Integer> result = new HashMap<>();
        for (int i = cassetesList.size() - 1; i >= 0; i--) {
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
            throw new AtmException(String.format("Имеющихся банкнот недостаточно для выдачи требуемой суммы %s %s", numberFormat(storeAmount), currency));
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
       return "выдача крупными банкнотами";
    }


}
