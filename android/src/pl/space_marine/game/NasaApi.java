package pl.space_marine.game;

import java.io.IOException;

import okhttp3.Headers;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class NasaApi {
    private final Retrofit retrofit;

    /**
     * The service instance used to get the astronomy picture of the day.
     */
    private final AstronomyPictureDayService astronomyPictureDayService;


    /**
     * The API key which is sent in REST requests.
     */
    private final String apiKey;

    /**
     * The amount of requests allowed per hour.
     */
    private int rateLimit = -1;

    /**
     * The amount of remaining requests allowed to be made this hour.
     */
    private int rateLimitRemaining = -1;

    /**
     * Constructs a new NASA API instance without an API key which makes REST requests default to the demo API key.
     * The demo API key can be used for 30 requests per IP address per hour and 50 requests per IP address per day.
     */
    public NasaApi() {
        this("");
    }

    /**
     * Constructs a new NASA API instance with the specified API key.
     *
     * @param apiKey The API key.
     */
    public NasaApi(final String apiKey) {
        retrofit = new Retrofit.Builder().baseUrl("https://api.nasa.gov").addConverterFactory(GsonConverterFactory
                .create()).build();
        astronomyPictureDayService = retrofit.create(AstronomyPictureDayService.class);
        this.apiKey = apiKey;
    }

    /**
     * Gets the astronomy picture of the day from the specified date and optionally the concept tags and hd picture.
     *
     * @param date The date of the astronomy picture of the day image to retrieve.
     * @param conceptTags Whether or not an ordered dictionary of concepts from the APOD explanation should be returned.
     * @param hd Whether or not the URL for the high resolution image should be returned.
     * @return The astronomy picture of the day.
     * @throws IOException If the call is unsuccessful.
     */
    public AstronomyPictureDay getAstronomyPictureOfTheDay(final String date, final boolean conceptTags,
                                                           final boolean hd) throws IOException {
        return get(astronomyPictureDayService.astronomyPictureOfTheDay(date, conceptTags, hd, apiKey));
    }


    /**
     * Gets the response body for the specified call and sets the rate limit vatiables.
     *
     * @param call The call.
     * @param <T> The type of the call.
     * @return The response body.
     * @throws IOException If the call is unsuccessful.
     */
    private <T> T get(final Call<T> call) throws IOException {
        final Response<T> response = call.execute();
        final Headers headers = response.headers();
        rateLimit = Integer.parseInt(headers.get("X-RateLimit-Limit"));
        rateLimitRemaining = Integer.parseInt(headers.get("X-RateLimit-Remaining"));
        return response.body();
    }


        }
