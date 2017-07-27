import atmDepartment.AtmDepartment;
import atmDepartment.atm.Atm;
import atmDepartment.atm.WithdrawStrategy_WithCharge;
import atmDepartment.atm.currency.Currency;
import atmDepartment.atm.currency.CurrencyName;

/**
 * Created by maxim.ovechkin on 19.07.2017.
 */
public class Main {
    public static void main(String[] args) {
        AtmDepartment atmDepartment = new AtmDepartment();
        Atm atm;
        atm = new Atm(Currency.USD);
        atm.loadCassette(5, 100, 200);
        atm.loadCassette(10, 100, 200);
        atm.loadCassette(20, 100, 200);
        atm.loadCassette(100, 50, 200);
        atmDepartment.addAtm(atm);
        atm = new Atm(Currency.RUB);
        atm.loadCassette(100, 100, 200);
        atm.loadCassette(500, 100, 200);
        atm.loadCassette(1000, 100, 200);
        atm.loadCassette(5000, 50, 200);
        atmDepartment.addAtm(atm);

        System.out.println(atm.getStatus());
        System.out.println(atmDepartment.getStatus());

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
