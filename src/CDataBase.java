import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by kevin on 22.12.2016.
 */
public class CDataBase {

    public static final String name = "testExercise";
    public static final String newQuestion = "newQuestion";
    public static final Boolean newAnswer = false;
    public static final ArrayList<String> questions = new ArrayList<String>(Arrays.asList("Question1","Question2","Question3","Question4"));
    public static final ArrayList<Boolean> answers= new ArrayList<Boolean>(Arrays.asList(true,false,true,false));
    public static final ArrayList<Boolean> testAnswersTrue= new ArrayList<Boolean>(Arrays.asList(true,false,true,false));
    public static final ArrayList<Boolean> testAnswersFalse = new ArrayList<Boolean>(Arrays.asList(false,true,false,true));
    public static final ArrayList<Boolean> testAnswersOneFalse = new ArrayList<Boolean>(Arrays.asList(true,false,false,false));
    public static final ArrayList<Boolean> testAnswersSize = new ArrayList<Boolean>(Arrays.asList(true,false));
}
