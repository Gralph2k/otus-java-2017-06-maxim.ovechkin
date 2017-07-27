package atmDepartment.atm;

/**
 * Created by maxim.ovechkin on 27.07.2017.
 */
public class AtmIdGenerator{
    private static int id=0;

    public static int getNewId(){
        id++;
        return id;
    }
}