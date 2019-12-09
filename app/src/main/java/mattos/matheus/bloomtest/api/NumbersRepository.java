package mattos.matheus.bloomtest.api;

import android.app.Application;

import java.util.ArrayList;
import java.util.List;

import androidx.lifecycle.MutableLiveData;
import mattos.matheus.bloomtest.database.AppDatabase;
import mattos.matheus.bloomtest.database.CardDao;
import mattos.matheus.bloomtest.model.Card;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NumbersRepository {

    private static NumbersRepository numbersRepository;
    private NumbersApi numbersApi;
    private CardDao cardDao;
    private List<Card> cardsList;

    public static NumbersRepository getInstance(Application application) {
        if (numbersRepository == null) {
            numbersRepository = new NumbersRepository(application);
        }
        return numbersRepository;
    }

    private NumbersRepository(Application application) {
        AppDatabase appDatabase = AppDatabase.getDatabase(application);
        cardDao = appDatabase.cardDao();
        cardsList = new ArrayList<>();
        numbersApi = RetrofitService.createService(NumbersApi.class);
    }

    public MutableLiveData<String> getYearTrivia(int year) {

        final MutableLiveData<String> yearTriviaData = new MutableLiveData<>();

        numbersApi.getYearTrivia(year).enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                if (response.isSuccessful()) {
                    yearTriviaData.setValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                yearTriviaData.setValue(null);
            }
        });

        return yearTriviaData;
    }

    public MutableLiveData<List<Card>> getNumberTrivia(int number) {

        final MutableLiveData<List<Card>> triviaData = new MutableLiveData<>();

        // Checking local database to not fetch from remote server more than once for each number
        if (cardDao.getAllCards().size() < 50) {
            numbersApi.getNumberTrivia(number).enqueue(new Callback<String>() {
                @Override
                public void onResponse(Call<String> call, Response<String> response) {
                    if (response.isSuccessful()) {

                            Card card = new Card();
                            card.setNumber(number);
                            card.setTitle("Random Fact #" + number);
                            card.setDescription(response.body());
                            cardsList.add(card);
                            cardDao.save(card);

                            triviaData.setValue(cardDao.getAllCards());
                    }
                }

                @Override
                public void onFailure(Call<String> call, Throwable t) {
                    triviaData.setValue(null);
                }
            });
        } else {
            triviaData.setValue(cardDao.getAllCards());
        }

        return triviaData;
    }

}
