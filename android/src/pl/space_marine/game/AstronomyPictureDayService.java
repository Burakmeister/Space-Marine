package pl.space_marine.game;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface AstronomyPictureDayService {
    @GET("/planetary/apod")
    Call<AstronomyPictureDay> astronomyPictureOfTheDay(@Query("date") String date,
                                                       @Query("concept_tags") boolean conceptTags,
                                                       @Query("hd") boolean hd,
                                                       @Query("api_key") String apiKey);
}
