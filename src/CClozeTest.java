/**
 * Created by kevin on 23.12.2016.
 */
import org.junit.Test;
import org.junit.Assert;
import java.util.ArrayList;
import java.util.Arrays;

public class CClozeTest {
    private static final String name = "testExercise";
    private static final String newQuestion = "newQuestion";
    private static final String newAnswer = "falseAnswer";
    private static final ArrayList<String> questions = new ArrayList<String>(Arrays.asList("Question1","Question2","Question3","Question4"));
    private static final ArrayList<String> answers= new ArrayList<String>(Arrays.asList("Answer1","Answer2","Answer3","Answer4"));
    private static final ArrayList<String> testAnswersTrue= new ArrayList<String >(Arrays.asList("Answer1","Answer2","Answer3","Answer4"));
    private static final ArrayList<String> testAnswersFalse = new ArrayList<String>(Arrays.asList("falseAnswer","falseAnswer","falseAnswer","falseAnswer"));
    private static final ArrayList<String> testAnswersOneFalse = new ArrayList<String>(Arrays.asList("Answer1","Answer2","Answer3","falseAnswer"));
    private static final ArrayList<String> testAnswersSize = new ArrayList<String>(Arrays.asList("Answer1","Answer2"));

    @Test
    public void testCreateCloze ()
    {
        CCloze exercise = new CCloze(name,questions,answers);
        Assert.assertEquals("Name should be equal to the name given to the constructor",name,exercise.getM_name());
        Assert.assertEquals("Questions should be equal to the questions given to the constructor",questions,exercise.getM_questions());
        Assert.assertEquals("Answers should be equal to the answers given to the constructor", answers, exercise.getM_correctAnswers());
    }
    @Test
    public void testControlCCloze ()
    {
        CCloze exercise = new CCloze(name,questions,answers);
        Assert.assertEquals("Result should be 100.0%",100.0,exercise.controlCloze(testAnswersTrue),0.0);
        Assert.assertEquals("Result should be 0.0%",0.0,exercise.controlCloze(testAnswersFalse),0.0);
        Assert.assertEquals("Result should be 75.0%",75.0,exercise.controlCloze(testAnswersOneFalse),0.0);
        Assert.assertEquals("Result should be -1%",-1.0,exercise.controlCloze(testAnswersSize),0.0);
    }

    @Test
    public void testRemoveQuestionAndAnswer ()
    {
        CCloze exercise = new CCloze(name,questions,answers);
        int currSizeQuestions = exercise.getM_questions().size();
        int currSizeAnswers = exercise.getM_correctAnswers().size();
        //Test return value
        Assert.assertEquals(true,exercise.removeQuestionAndAnswer(2));
        Assert.assertEquals(false,exercise.removeQuestionAndAnswer(5));
        //Test size of ArrayLists after removing Question and corresponding Answer
        exercise.removeQuestionAndAnswer(1);
        Assert.assertEquals(currSizeQuestions-2,exercise.getM_questions().size());
        Assert.assertEquals(currSizeAnswers-2,exercise.getM_correctAnswers().size());
    }

    @Test
    public void testAddQuestionAndAnswer ()
    {
        CCloze exercise = new CCloze(name,questions,answers);
        int currSizeQuestions = exercise.getM_questions().size();
        int currSizeAnswers = exercise.getM_correctAnswers().size();
        exercise.addQuestionAndAnswer(newQuestion,newAnswer);
        Assert.assertEquals(currSizeQuestions+1,exercise.getM_questions().size());
        Assert.assertEquals(currSizeAnswers+1,exercise.getM_correctAnswers().size());
    }
}
