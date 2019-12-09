package mattos.matheus.bloomtest.viewmodel;

import android.app.Application;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;
import mattos.matheus.bloomtest.api.NumbersRepository;
import mattos.matheus.bloomtest.model.Card;

public class MainViewModel extends AndroidViewModel {

    private MutableLiveData<List<Card>> triviaLiveData;
    private MutableLiveData<String> triviaYearLiveData;
    private NumbersRepository numbersRepository;

    public MainViewModel(@NonNull Application application) {
        super(application);
        triviaLiveData = new MutableLiveData<>();
        triviaYearLiveData = new MutableLiveData<>();
        numbersRepository = NumbersRepository.getInstance(getApplication());
    }

    public MutableLiveData<List<Card>> getTriviaLiveData() {
        return triviaLiveData;
    }

    public MutableLiveData<String> getTriviaYearLiveData() {
        return triviaYearLiveData;
    }

    public void fetchNumbersTriviaData() {
        for (int i = 0; i < 50; i++) {
            triviaLiveData = numbersRepository.getNumberTrivia(i);
        }
    }

    public void fetchYearTriviaData(Integer year) {
        triviaYearLiveData = numbersRepository.getYearTrivia(1900 + year);
    }
}
