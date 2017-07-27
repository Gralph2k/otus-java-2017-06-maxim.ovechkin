package atmDepartment.atm;

import atmDepartment.atm.Cassette;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by maxim.ovechkin on 27.07.2017.
 */
public class AtmMemento {
    private final List<Cassette> state;

    public AtmMemento(List<Cassette> stateToSave) {
        state=copyCassetesList(stateToSave);
    }

    public List<Cassette> getSavedState() {
        return state;
    }


    public static List<Cassette> copyCassetesList(List<Cassette> sourceList){
        List<Cassette> targetList=new ArrayList<>();
        for (Cassette source:sourceList) {
            Cassette target = new Cassette(source.getCurrency(),source.getNomination(),source.getBanknotesCount(),source.getSize());
            targetList.add(target);
        }
        return targetList;
    }
}
