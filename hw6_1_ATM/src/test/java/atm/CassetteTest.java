package atm;

import atm.Currency.Currencies;
import atm.Currency.Currency;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.*;

/**
 * Created by maxim.ovechkin on 17.07.2017.
 */
public class CassetteTest {
    @Test
    public void testSetNomination() throws Exception {
        Cassette cassette = new Cassette(Currency.EUR,10, 100);
        assertEquals(cassette.currency.getCurrency(), Currencies.EUR);
        assertEquals(cassette.nomination,10);
        assertEquals(cassette.getAmount(),1000);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testIllegalNomination() {
        Cassette cassette = new Cassette(Currency.EUR,105, 100);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testIllegalBanknotesCount() {
        Cassette cassette = new Cassette(Currency.EUR,100, -100);
    }
}