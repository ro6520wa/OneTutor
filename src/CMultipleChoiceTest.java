/**
 * Created by kevin on 22.12.2016.
 */


import org.junit.Before;
import org.junit.Test;
import org.junit.Assert;

public class CMultipleChoiceTest {

    private CMultipleChoice exercise;

    @Before
    public void setUp() throws Exception
    {
        exercise = new CMultipleChoice(CDataBase.name,CDataBase.questions,CDataBase.answers);
    }
    @Test
    public void testCreateMultipleChoice ()
    {
        Assert.assertEquals("Name should be equal to the name given to the constructor",CDataBase.name,exercise.getM_name());
        Assert.assertEquals("Questions should be equal to the questions given to the constructor",CDataBase.questions,exercise.getM_questions());
        Assert.assertEquals("Answers should be equal to the answers given to the constructor", CDataBase.answers, exercise.getM_correctAnswers());
    }
    @Test
    public void testControlMultipleChoice ()
    {
        Assert.assertEquals("Result should be 100.0%",100.0,exercise.controlMultipleChoice(CDataBase.testAnswersTrue),0.0);
        Assert.assertEquals("Result should be 0.0%",0.0,exercise.controlMultipleChoice(CDataBase.testAnswersFalse),0.0);
        Assert.assertEquals("Result should be 75.0%",75.0,exercise.controlMultipleChoice(CDataBase.testAnswersOneFalse),0.0);
        Assert.assertEquals("Result should be -1%",-1.0,exercise.controlMultipleChoice(CDataBase.testAnswersSize),0.0);
    }

    @Test
    public void testRemoveQuestionAndAnswer ()
    {
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
        int currSizeQuestions = exercise.getM_questions().size();
        int currSizeAnswers = exercise.getM_correctAnswers().size();
        exercise.addQuestionAndAnswer(CDataBase.newQuestion,CDataBase.newAnswer);
        Assert.assertEquals(currSizeQuestions+1,exercise.getM_questions().size());
        Assert.assertEquals(currSizeAnswers+1,exercise.getM_correctAnswers().size());
    }
}
