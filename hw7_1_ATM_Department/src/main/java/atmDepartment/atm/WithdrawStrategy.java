package atmDepartment.atm;


import atmDepartment.atm.currency.Currency;

import java.util.HashMap;
import java.util.List;

/**
 * Created by maxim.ovechkin on 27.07.2017.
 */
public interface WithdrawStrategy {
    HashMap<Integer, Integer> splitAmountToBanknotes(int amount, Currency currency, List<Cassette> cassetesList);

    String getWithdrawStrategyName() ;
}
