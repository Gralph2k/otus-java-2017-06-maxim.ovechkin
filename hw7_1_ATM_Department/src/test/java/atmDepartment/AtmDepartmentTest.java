package atmDepartment;

import atmDepartment.atm.Atm;
import atmDepartment.atm.currency.Currency;
import atmDepartment.atm.currency.CurrencyName;
import org.junit.Before;
import org.junit.Test;

import java.util.Map;

import static org.junit.Assert.assertEquals;

/**
 * Created by maxim.ovechkin on 27.07.2017.
 */
public class AtmDepartmentTest {
    AtmDepartment atmDepartment;
    @Before
    public void setUp() throws Exception {
        atmDepartment = new AtmDepartment();
        Atm atm;
        atm = new Atm(Currency.RUB);
        atm.loadCassette(100,100,200);
        atm.loadCassette(500,100,200);
        atm.loadCassette(1000,100,200);
        atm.loadCassette(5000,100,200);
        atmDepartment.addAtm(atm);
        atm = new Atm(Currency.USD);
        atm.loadCassette(5, 100, 200);
        atm.loadCassette(10, 100, 200);
        atm.loadCassette(20, 100, 200);
        atm.loadCassette(100, 50, 200);
        atmDepartment.addAtm(atm);
    }

    @Test
    public void test_getBalance() throws Exception {
        Map<CurrencyName, Integer> balance = atmDepartment.getBalance();
        assertEquals(balance.size(),2);
        assertEquals(balance.get(CurrencyName.RUB),Integer.valueOf(660000));
        assertEquals(balance.get(CurrencyName.USD),Integer.valueOf(8500));
    }

    @Test
    public void test_Memento() throws Exception {
        Map<CurrencyName, Integer> balance = atmDepartment.getBalance(); //Начальное значение
        assertEquals(balance.get(CurrencyName.RUB),Integer.valueOf(660000));
        atmDepartment.getAtmList().get(0).withdrawBanknotes(100000);
        balance = atmDepartment.getBalance();
        assertEquals(balance.get(CurrencyName.RUB),Integer.valueOf(560000));//Сняли деньги
        atmDepartment.restoreAtmsState();
        balance = atmDepartment.getBalance();
        assertEquals(balance.get(CurrencyName.RUB),Integer.valueOf(660000));//Восстановились до начального значения
    }
}
