package sample;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import okhttp3.*;

import java.io.IOException;

public class api_translate {
    /*  Configure the local environment:
     * Set the TRANSLATOR_TEXT_SUBSCRIPTION_KEY and TRANSLATOR_TEXT_ENDPOINT environment
     * variables on your local machine using the appropriate method for your
     * preferred shell (Bash, PowerShell, Command Prompt, etc.).
     *
     * For TRANSLATOR_TEXT_ENDPOINT, use the same region you used to get your
     * subscription keys.
     *
     * If the environment variable is created after the application is launched
     * in a console or with Visual Studio, the shell (or Visual Studio) needs to be
     * closed and reloaded to take the environment variable into account.
     */
    private static String subscriptionKey = "0d3c36d7979d419eab31e8f1be770726";
    private static String endpoint = "https://api.cognitive.microsofttranslator.com/";
    private static String url = endpoint + "/translate?api-version=3.0&to=en";

    public static void setUrlVi() {
        url = endpoint + "/translate?api-version=3.0&to=vi";
    }

    public static void setUrlEn() {
        url = endpoint + "/translate?api-version=3.0&to=en";
    }

    // Instantiates the OkHttpClient.
    static OkHttpClient client = new OkHttpClient();

    // This function performs a POST request.
    public static String Post(String text) throws IOException {
        MediaType mediaType = MediaType.parse("application/json");
        RequestBody body = RequestBody.create(mediaType,
                "[{\n\t\"Text\": \"" + text + "\"\n}]");
        Request request = new Request.Builder()
                .url(url).post(body)
                .addHeader("Ocp-Apim-Subscription-Key", subscriptionKey)
                .addHeader("Ocp-Apim-Subscription-Region", "eastasia")
                .addHeader("Content-type", "application/json").build();
        Response response = client.newCall(request).execute();
        return response.body().string();
    }

    // This function prettifies the json response.
    public static String TranslateFromJson(String json_text) {
        try {
            String jsonText = Post(json_text);
            JsonParser parser = new JsonParser();
            Object object = parser.parse(jsonText);
            JsonObject jsonObject = (JsonObject) ((JsonArray) object).get(0);
            JsonArray jsonArray = (JsonArray) (jsonObject.get("translations"));
            JsonObject jsonObject1 = (JsonObject) jsonArray.get(0);
            String result = String.valueOf(jsonObject1.get("text"));
            result = result.substring(1, result.length() - 1);
            return result;
        } catch (IOException e) {
            System.out.println(e.getMessage());
            return "0";
        }
    }

}
