package pl.space_marine.game;

import com.google.gson.annotations.SerializedName;

public class AstronomyPictureDay {
    private String url;
    @SerializedName("media_type")
    private String mediaType;
    private String explanation;
    private String title;

    public String getUrl() {
        return url;
    }

    public String getMediaType() {
        return mediaType;
    }

    public String getExplanation() {
        return explanation;
    }

    public String getTitle() {
        return title;
    }
}
