package mattos.matheus.bloomtest.api;

import retrofit2.Retrofit;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class RetrofitService {

    private static Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("http://numbersapi.com/")
            .addConverterFactory(ScalarsConverterFactory.create())
            .build();


    public static <T> T createService(Class<T> serviceClass) {
        return retrofit.create(serviceClass);
    }

}
