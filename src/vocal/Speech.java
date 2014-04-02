
 

 
/**
 *
 * @author Rahana,Anu,Sebin,Pratheesh
 */
import com.sun.speech.freetts.jsapi.FreeTTSEngineCentral;
import java.util.Locale;
import javax.speech.EngineCreate;
import javax.speech.EngineList;
import javax.speech.synthesis.Synthesizer;
import javax.speech.synthesis.SynthesizerModeDesc;

public class Speech {
    Synthesizer synthesizer=null;
    public void speak(String s1)
    {
        try {
            System.out.println("speech conversion");
            SynthesizerModeDesc s = new SynthesizerModeDesc(Locale.ENGLISH);
            FreeTTSEngineCentral central = new FreeTTSEngineCentral();
            EngineList list = central.createEngineList(s);
            if (list.size() > 0) {
                EngineCreate create = (EngineCreate) list.get(2);
                synthesizer = (Synthesizer) create.createEngine();
            }
            System.out.println("Synthesizer  =  "+synthesizer);
            synthesizer.allocate();
            synthesizer.resume();
            synthesizer.speakPlainText(s1, null);
            synthesizer.waitEngineState(Synthesizer.QUEUE_EMPTY);
        } catch (Exception ex) {
        }


    }

}

