/**
 * Created by kevin on 22.12.2016.
 */


import org.junit.Test;
import org.junit.Assert;
import java.util.ArrayList;
import java.util.Arrays;

public class CMultipleChoiceTest {
    private static final String name = "testExercise";
    private static final String newQuestion = "newQuestion";
    private static final Boolean newAnswer = false;
    private static final ArrayList<String> questions = new ArrayList<String>(Arrays.asList("Question1","Question2","Question3","Question4"));
    private static final ArrayList<Boolean> answers= new ArrayList<Boolean>(Arrays.asList(true,false,true,false));
    private static final ArrayList<Boolean> testAnswersTrue= new ArrayList<Boolean>(Arrays.asList(true,false,true,false));
    private static final ArrayList<Boolean> testAnswersFalse = new ArrayList<Boolean>(Arrays.asList(false,true,false,true));
    private static final ArrayList<Boolean> testAnswersOneFalse = new ArrayList<Boolean>(Arrays.asList(true,false,false,false));
    private static final ArrayList<Boolean> testAnswersSize = new ArrayList<Boolean>(Arrays.asList(true,false));

    @Test
    public void testCreateMultipleChoice ()
    {
        CMultipleChoice exercise = new CMultipleChoice(name,questions,answers);
        Assert.assertEquals("Name should be equal to the name given to the constructor",name,exercise.getM_name());
        Assert.assertEquals("Questions should be equal to the questions given to the constructor",questions,exercise.getM_questions());
        Assert.assertEquals("Answers should be equal to the answers given to the constructor", answers, exercise.getM_correctAnswers());
    }
    @Test
    public void testControlMultipleChoice ()
    {
    CMultipleChoice exercise = new CMultipleChoice(name,questions,answers);
        Assert.assertEquals("Result should be 100.0%",100.0,exercise.controlMultipleChoice(testAnswersTrue),0.0);
        Assert.assertEquals("Result should be 0.0%",0.0,exercise.controlMultipleChoice(testAnswersFalse),0.0);
        Assert.assertEquals("Result should be 75.0%",75.0,exercise.controlMultipleChoice(testAnswersOneFalse),0.0);
        Assert.assertEquals("Result should be -1%",-1.0,exercise.controlMultipleChoice(testAnswersSize),0.0);
    }

    @Test
    public void testRemoveQuestionAndAnswer ()
    {
        CMultipleChoice exercise = new CMultipleChoice(name,questions,answers);
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
        CMultipleChoice exercise = new CMultipleChoice(name,questions,answers);
        int currSizeQuestions = exercise.getM_questions().size();
        int currSizeAnswers = exercise.getM_correctAnswers().size();
        exercise.addQuestionAndAnswer(newQuestion,newAnswer);
        Assert.assertEquals(currSizeQuestions+1,exercise.getM_questions().size());
        Assert.assertEquals(currSizeAnswers+1,exercise.getM_correctAnswers().size());
    }
}
