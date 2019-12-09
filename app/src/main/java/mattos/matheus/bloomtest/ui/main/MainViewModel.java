package mattos.matheus.bloomtest.ui.main;

import android.app.Application;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;
import mattos.matheus.bloomtest.api.NumbersRepository;
import mattos.matheus.bloomtest.model.Card;

public class MainViewModel extends AndroidViewModel {

    private MutableLiveData<List<Card>> triviaLiveData;
    private NumbersRepository numbersRepository;

    public MainViewModel(@NonNull Application application) {
        super(application);
        triviaLiveData = new MutableLiveData<>();
        numbersRepository = new NumbersRepository(application);
    }

    public MutableLiveData<List<Card>> getTriviaLiveData() {
        return triviaLiveData;
    }

    void fetchData() {
        numbersRepository = NumbersRepository.getInstance(getApplication());

        for (int i = 1; i <= 50; i++) {
            triviaLiveData = numbersRepository.getNumberTrivia(i);
        }
    }
}
