package atm;

import atm.Currency.CurrencyName;
import atm.Currency.Currency;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by maxim.ovechkin on 17.07.2017.
 */
public class CassetteTest {
    @Test
    public void test_Constructor_OK() throws Exception {
        Cassette cassette = new Cassette(Currency.EUR,10, 100);
        assertEquals(cassette.getCurrency().getCurrencyName(), CurrencyName.EUR);
        assertEquals(cassette.getNomination(),10);
        assertEquals(cassette.getAmount(),1000);
    }

    @Test(expected = AtmException.class)
    public void test_Constructor_IllegalNomination() {
        Cassette cassette = new Cassette(Currency.EUR,105, 100);
    }

    @Test(expected = AtmException.class)
    public void test_Constructor_IllegalBanknotesCount() {
        Cassette cassette = new Cassette(Currency.EUR,100, -100);
    }


    @Test(expected = AtmException.class)
    public void test_inputBanknotes_Oversize() {
        Cassette cassette = new Cassette(Currency.EUR,100, 100, 120);
        cassette.inputBanknotes(200);
    }
    @Test
    public void test_inputBanknotes_OK() {
        Cassette cassette = new Cassette(Currency.EUR,100, 100, 200);
        cassette.inputBanknotes(10);
        assertEquals(cassette.getBanknotesCount(),(100+10));
        assertEquals(cassette.getAmount(),(100+10)*100);
    }
    @Test
    public void test_getAmount_OK(){
        Cassette cassette = new Cassette(Currency.EUR,100, 100, 200);
        assertEquals(cassette.getAmount(),100*100);
    }

    @Test(expected = AtmException.class)
    public void test_outputBanknotes_Oversize() {
        Cassette cassette = new Cassette(Currency.EUR,100, 100, 120);
        cassette.outputBanknotes(200);
    }

    @Test
    public void test_outputBanknotes_OK() {
        Cassette cassette = new Cassette(Currency.EUR,100, 100, 200);
        cassette.outputBanknotes(10);
        assertEquals(cassette.getBanknotesCount(),90);
        assertEquals(cassette.getAmount(),(100-10)*100);
    }



}