package atmDepartment.atm;

/**
 * Created by maxim.ovechkin on 19.07.2017.
 */
public class AtmException extends IllegalArgumentException {
    public AtmException(String message) {
        super(message);
    }
}
