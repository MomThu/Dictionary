package sample;

import com.microsoft.cognitiveservices.speech.*;

public class api_speech {

    /*private static String subscriptionKey = "3210f48b4e924f76b9f679af5db033d1";
    private static String endpoint = "https://eastasia.api.cognitive.microsoft.com/sts/v1.0/issuetoken";
    private static String url = endpoint + "/translate?api-version=3.0&to=en";*/
    private static String speechSubscriptionKey = "3210f48b4e924f76b9f679af5db033d1";
    private static String serviceRegion = "eastasia";

    public static String getSsml(String language, String name, String text) {
        if (!language.equals("vi-VN")) {
            return "<speak version=\"1.0\" xmlns=\"https://www.w3.org/2001/10/synthesis\" xml:lang=\"" + language + "\">\n" +
                    "  <voice name=\"" + name + "\">\n" + text + "\n" +
                    "  </voice>\n" +
                    "</speak>";
        } else {
            return "<speak version=\"1.0\" xmlns=\"https://www.w3.org/2001/10/synthesis\" xml:lang=\"" + language + "\">\n" +
                    "  <voice name=\"" + name + "\">\n" +
                    "<prosody rate=\"0.8\">" + text + "\n</prosody>\n" +
                    "  </voice>\n" +
                    "</speak>";
        }
    }
    public static void textToSpeechApi(String language, String name, String text) {
        try {

            SpeechConfig config = SpeechConfig.fromSubscription(speechSubscriptionKey, serviceRegion);

            SpeechSynthesizer synth = new SpeechSynthesizer(config);

            SpeechSynthesisResult result = synth.SpeakSsml(getSsml(language, name, text));

            if (result.getReason() == ResultReason.SynthesizingAudioCompleted) {
                System.out.println("Speech synthesized to speaker for text [" + text + "]");
            }
            else if (result.getReason() == ResultReason.Canceled) {
                SpeechSynthesisCancellationDetails cancellation = SpeechSynthesisCancellationDetails.fromResult(result);
                System.out.println("CANCELED: Reason=" + cancellation.getReason());

                if (cancellation.getReason() == CancellationReason.Error) {
                    System.out.println("CANCELED: ErrorCode=" + cancellation.getErrorCode());
                    System.out.println("CANCELED: ErrorDetails=" + cancellation.getErrorDetails());
                    System.out.println("CANCELED: Did you update the subscription info?");
                }
            }

            result.close();
            synth.close();

        } catch (Exception ex) {
            System.out.println("Unexpected exception: " + ex.getMessage());
        }
    }
}
