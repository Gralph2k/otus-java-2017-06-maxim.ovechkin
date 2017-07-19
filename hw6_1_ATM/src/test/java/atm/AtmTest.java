package atm;

import atm.Currency.CurrencyName;
import atm.Currency.Currency;
import org.junit.Before;
import org.junit.Test;

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
        assertEquals(atm.getAmount(),660000);
    }

    @Test
    public void getCurrency() throws Exception {
        assertEquals(atm.getCurrency().getCurrencyName(),CurrencyName.RUB);
    }

    @Test
    public void getCassetesList() throws Exception {
        assertEquals(atm.getCassetesList().size(),4);
        assertEquals(atm.getCassetesList().get(0).getNomination(),100);
        assertEquals(atm.getCassetesList().get(0).getCurrency(),Currency.RUB);
        assertEquals(atm.getCassetesList().get(0).getAmount(),100*100);
    }

    @Test
    public void inputBanknotes() throws Exception {
        assertEquals(atm.inputBanknotes(CurrencyName.RUB,100,50),true);
        assertEquals(atm.inputBanknotes(CurrencyName.EUR,100,100),false);
        assertEquals(atm.inputBanknotes(CurrencyName.RUB,50,100),false);
        assertEquals(atm.inputBanknotes(CurrencyName.RUB,100,1000),false);
    }

}