import atmDepartment.atm.Atm;
import atmDepartment.atm.WithdrawStrategy_WithCharge;
import atmDepartment.atm.currency.CurrencyName;

/**
 * Created by maxim.ovechkin on 19.07.2017.
 */
public class Main {
    public static void main(String[] args) {
        Atm atm = new Atm();
        atm.defaultAtmInit();
        atm.printStatus();
        atm.withdrawBanknotes(7800);
        atm.printStatus();
        atm.setWithdrawStrategy(new WithdrawStrategy_WithCharge());
        atm.withdrawBanknotes(7800);
        atm.printStatus();

        atm.putBanknotes(CurrencyName.RUB,1000,1);
        atm.printStatus();
        atm.withdrawBanknotes(1_000_000);
        atm.printStatus();

    }

}
