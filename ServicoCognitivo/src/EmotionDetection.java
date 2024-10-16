import okhttp3.*;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;

public class EmotionDetection 
{
    private static final String subscriptionKey = "6d1d4a0714b4419797523d37b1101657";
    private static final String endpoint = "https://faceapi-sentimentos.cognitiveservices.azure.com/";
    private static final String faceApiUrl = endpoint + "/face/v1.0/detect";

    public static void main(String[] args) 
    {
        String imageUrl = "https://www.instagram.com/p/DAs-4dEICj6/";

        try {
            detectEmotions(imageUrl);
        } 
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void detectEmotions(String imageUrl) throws IOException 
    {
        OkHttpClient client = new OkHttpClient();

        JSONObject json = new JSONObject();
        json.put("https://www.instagram.com/p/DAs-4dEICj6/", imageUrl);

        RequestBody body = RequestBody.create(
                json.toString(),
                MediaType.parse("application/json")
        );

        HttpUrl.Builder urlBuilder = HttpUrl.parse(faceApiUrl).newBuilder();
        urlBuilder.addQueryParameter("returnFaceAttributes", "emotion");

        Request request = new Request.Builder()
                .url(urlBuilder.build())
                .header("Content-Type", "application/json")
                .header("Ocp-Apim-Subscription-Key", subscriptionKey)
                .post(body)
                .build();

        try (Response response = client.newCall(request).execute()) {
            if (response.isSuccessful()) {
                String jsonData = response.body().string();
                JSONArray jsonArray = new JSONArray(jsonData);

                if (jsonArray.length() > 0) {
                    JSONObject faceAttributes = jsonArray.getJSONObject(0)
                            .getJSONObject("faceAttributes")
                            .getJSONObject("emotion");

                    System.out.println("Emoções detectadas:");
                    System.out.println(faceAttributes.toString(2));
                } else {
                    System.out.println("Nenhuma face detectada.");
                }
            } else {
                System.out.println("Erro: " + response.message());
            }
        }
    }
}
