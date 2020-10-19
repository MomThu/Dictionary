package sample;

import com.sun.speech.freetts.VoiceManager;

import java.util.Locale;
import javax.speech.Central;
import javax.speech.synthesis.Synthesizer;
import javax.speech.synthesis.SynthesizerModeDesc;

public class textToSpeech {

    public static void speech(String text)
    {

        VoiceManager voiceManager = VoiceManager.getInstance();
        com.sun.speech.freetts.Voice syntheticVoice = voiceManager.getVoice("kevin16");
        syntheticVoice.allocate();
        syntheticVoice.speak(text);
        syntheticVoice.deallocate();
    }

    /*public static void main(String[] args) {
        textToSpeech.speech("Huy's name is dog");
    }*/
}
