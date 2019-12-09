package mattos.matheus.bloomtest.ui.main;

import java.util.ArrayList;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class MainViewModel extends ViewModel {

    MutableLiveData<ArrayList<String>> stringLiveData;
    ArrayList<String> stringList;

    public MainViewModel() {
        stringLiveData = new MutableLiveData<>();

        init();
    }

    public MutableLiveData<ArrayList<String>> getUserMutableLiveData() {
        return stringLiveData;
    }

    public void init() {
        populateList();
        stringLiveData.setValue(stringList);
    }

    public void populateList() {

        stringList = new ArrayList<>();
        stringList.add("Title");
        stringList.add("Title");
        stringList.add("Title");
        stringList.add("Title");
        stringList.add("Title");
        stringList.add("Title");
        stringList.add("Title");
        stringList.add("Title");
        stringList.add("Title");
        stringList.add("Title");
    }
}
