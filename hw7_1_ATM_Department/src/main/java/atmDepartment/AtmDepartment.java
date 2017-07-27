package atmDepartment;

import atmDepartment.atm.Atm;
import atmDepartment.atm.currency.CurrencyName;
import atmDepartment.atm.AtmMemento;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static atmDepartment.atm.helper.AtmHelper.numberFormat;

/**
 * Created by maxim.ovechkin on 27.07.2017.
 */
public class AtmDepartment {
    private List<Atm> atmList=new ArrayList<>();
    private Map<Integer,AtmMemento> atmStates = new HashMap<>();

    public List<Atm> getAtmList() {
        return atmList;
    }

    public Map<CurrencyName, Integer> getBalance(){
        Map<CurrencyName, Integer> result = new HashMap<>();
        for (Atm atm:atmList) {
            CurrencyName currencyName = atm.getCurrency().getCurrencyName();
            if (result.containsKey(currencyName)) {
                Integer curAmount = result.get(currencyName);
                result.replace(currencyName,curAmount+atm.getBalance());

            } else {
                result.put(currencyName, atm.getBalance());
            }
        }
        return result;
    }

    public String getStatus() {
        StringBuilder status=new StringBuilder();
        status.append(String.format("Количество банкоматов: %s\nДоступный остаток",atmList.size()));
        for (Map.Entry<CurrencyName,Integer> entry:getBalance().entrySet()) {
            status.append(String.format("\n\t %s %s ", numberFormat(entry.getValue()),entry.getKey()));
        }
        int i=0;
        for (Atm atm:atmList) {
            status.append(String.format("\nБанкомат №%d: %s %s ",atm.getId(),numberFormat(atm.getBalance()),atm.getCurrency()));
        }
        return status.toString();
    }

    public void addAtm(Atm atm) {
        atmList.add(atm);
        saveAtmState(atm);
    }

    public boolean deleteAtm(Atm atm){
        if (atmList.contains(atm)){
            atmList.remove(atm);
            return true;
        }
        return false;
    }

    public void restoreAtmsState(){
        for (Atm atm:atmList) {
            restoreAtmState(atm);
        }
    }

    public void saveAtmState(Atm atm){
        atmStates.put(atm.getId(),atm.saveToMemento());
    }

    public void restoreAtmState(Atm atm){
        AtmMemento atmMemento = atmStates.get(atm.getId());
        atm.restoreFromMemento(atmMemento);
    }
}
