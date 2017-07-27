package atmDepartment.atm.helper;


import atmDepartment.atm.Cassette;

import java.text.DecimalFormat;
import java.util.List;

/**
 * Created by maxim.ovechkin on 19.07.2017.
 */
public class AtmHelper {
    private AtmHelper(){}

    static public String numberFormat(double value ) {
        String pattern =  "###,###,###,###";
        DecimalFormat myFormatter = new DecimalFormat(pattern);
        return myFormatter.format(value);
    }



}
