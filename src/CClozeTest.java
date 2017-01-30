/**
 * Created by kevin on 23.12.2016.
 */
import org.junit.Before;
import org.junit.Test;
import org.junit.Assert;


public class CClozeTest {

    private CCloze exercise;

    @Before
    public void setUp()
    {
        exercise = new CCloze(CDataBase.nameExercise,CDataBase.questions,CDataBase.answersCloze);
    }

    @Test
    public void testCreateCloze ()
    {
        Assert.assertEquals("Name should be equal to the nameExercise given to the constructor",CDataBase.nameExercise,exercise.getM_name());
        Assert.assertEquals("Questions should be equal to the questions given to the constructor",CDataBase.questions,exercise.getM_questions());
        Assert.assertEquals("Answers should be equal to the answersMultipleChoice given to the constructor", CDataBase.answersCloze, exercise.getM_correctAnswers());
    }
    @Test
    public void testControlCCloze ()
    {
        Assert.assertEquals("Result should be 100.0%",100.0,exercise.controlCloze(CDataBase.testAnswersTrueCloze),0.0);
        Assert.assertEquals("Result should be 0.0%",0.0,exercise.controlCloze(CDataBase.testAnswersFalseCloze),0.0);
        Assert.assertEquals("Result should be 75.0%",75.0,exercise.controlCloze(CDataBase.testAnswersOneFalseCloze),0.0);
        Assert.assertEquals("Result should be -1%",-1.0,exercise.controlCloze(CDataBase.testAnswersSizeCloze),0.0);
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
        exercise.addQuestionAndAnswer(CDataBase.newQuestion,CDataBase.newAnswerCloze);
        Assert.assertEquals(currSizeQuestions+1,exercise.getM_questions().size());
        Assert.assertEquals(currSizeAnswers+1,exercise.getM_correctAnswers().size());
    }
}
