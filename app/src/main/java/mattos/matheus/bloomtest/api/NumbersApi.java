package mattos.matheus.bloomtest.api;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface NumbersApi {

    @GET("{number}")
    Call<String> getNumberTrivia(@Path("number") int number);
}
