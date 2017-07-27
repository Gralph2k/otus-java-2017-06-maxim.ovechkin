package atmDepartment.atm;

import atmDepartment.atm.currency.CurrencyName;
import atmDepartment.atm.currency.Currency;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;

import static org.junit.Assert.*;

/**
 * Created by maxim.ovechkin on 19.07.2017.
 */
public class AtmTest {
    Atm atm;
    @Before
    public void setUp() throws Exception {
        atm = new Atm(Currency.RUB);
        atm.loadCassette(100,100,200);
        atm.loadCassette(500,100,200);
        atm.loadCassette(1000,100,200);
        atm.loadCassette(5000,100,200);
    }

    @Test
    public void getAmount() throws Exception {
        assertEquals(atm.getBalance(),660000);
    }

    @Test
    public void getCurrency() throws Exception {
        Assert.assertEquals(atm.getCurrency().getCurrencyName(), CurrencyName.RUB);
    }

    @Test
    public void getCassetesList() throws Exception {
        assertEquals(atm.getCassetesList().size(),4);
        assertEquals(atm.getCassetesList().get(0).getNomination(),100);
        Assert.assertEquals(atm.getCassetesList().get(0).getCurrency(),Currency.RUB);
        assertEquals(atm.getCassetesList().get(0).getBalance(),100*100);
    }

    @Test
    public void putBanknotes() throws Exception {
        assertEquals(atm.putBanknotes(CurrencyName.RUB,100,50),true);
        assertEquals(atm.putBanknotes(CurrencyName.EUR,100,100),false);
        assertEquals(atm.putBanknotes(CurrencyName.RUB,50,100),false);
        assertEquals(atm.putBanknotes(CurrencyName.RUB,100,1000),false);
    }

    @Test
    public void withdrawBanknotesByCount() throws Exception {
        assertEquals(atm.withdrawBanknotes(100,10),true); //Успешная попытка
        assertEquals(atm.withdrawBanknotes(50,20),false); //Снимаю несуществующий номинал
        assertEquals(atm.withdrawBanknotes(100,1000),false); //Недостаточное количество купюр
    }

    @Test
    public void splitAmountToBanknotesBigFirst() throws Exception {
        final int AMOUNT=6700;
        atm.setWithdrawStrategy(new WithdrawStrategy_BigFirst());
        HashMap<Integer,Integer> change = atm.splitAmountToBanknotes(AMOUNT);
        assertEquals((int) change.get(5000),1);
        assertEquals((int) change.get(1000),1);
        assertEquals((int) change.get(500),1);
        assertEquals((int) change.get(100),2);
        atm.withdrawBanknotes(AMOUNT);
    }

    @Test
    public void splitAmountToBanknotesWithCharge() throws Exception {
        final int AMOUNT=6700;
        atm.setWithdrawStrategy(new WithdrawStrategy_WithCharge());
        HashMap<Integer,Integer> change = atm.splitAmountToBanknotes(AMOUNT);
        assertFalse(change.containsKey(5000));
        assertEquals((int) change.get(1000),6);
        assertEquals((int) change.get(500),1);
        assertEquals((int) change.get(100),2);
        atm.withdrawBanknotes(AMOUNT);
    }

    @Test(expected = AtmException.class)
    public void splitAmountToBanknotes_NonSplittableAmount() throws Exception {
        HashMap<Integer,Integer> banknotes = atm.splitAmountToBanknotes(6750);
    }

    @Test
    public void withdrawBanknotes_OK() throws Exception {
        int balance = atm.getBalance();
        boolean result= atm.withdrawBanknotes(6700);
        assertEquals(atm.getBalance(),balance-6700);
        assertTrue(result);
    }

    @Test
    public void withdrawBanknotes_Illegal() throws Exception {
        int balance = atm.getBalance();
        boolean result= atm.withdrawBanknotes(6750);
        assertEquals(atm.getBalance(),balance);
        assertFalse(result);

        balance = atm.getBalance();
        result= atm.withdrawBanknotes(-100);
        assertEquals(atm.getBalance(),balance);
        assertFalse(result);
    }




}