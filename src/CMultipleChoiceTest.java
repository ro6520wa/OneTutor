/**
 * Created by kevin on 22.12.2016.
 */


import org.junit.Before;
import org.junit.Test;
import org.junit.Assert;

public class CMultipleChoiceTest {

    private CMultipleChoice exercise;

    @Before
    public void setUp()
    {
        exercise = new CMultipleChoice(CDataBase.nameExercise,CDataBase.questions,CDataBase.answersMultipleChoice);
    }

    @Test
    public void testCreateMultipleChoice ()
    {
        Assert.assertEquals("Name should be equal to the nameExercise given to the constructor",CDataBase.nameExercise,exercise.getM_name());
        Assert.assertEquals("Questions should be equal to the questions given to the constructor",CDataBase.questions,exercise.getM_questions());
        Assert.assertEquals("Answers should be equal to the answersMultipleChoice given to the constructor", CDataBase.answersMultipleChoice, exercise.getM_correctAnswers());
    }

    @Test
    public void testControlMultipleChoice ()
    {
        Assert.assertEquals("Result should be 100.0%",100.0,exercise.controlMultipleChoice(CDataBase.testAnswersTrueMultipleChoice),0.0);
        Assert.assertEquals("Result should be 0.0%",0.0,exercise.controlMultipleChoice(CDataBase.testAnswersFalseMultipleChoice),0.0);
        Assert.assertEquals("Result should be 75.0%",75.0,exercise.controlMultipleChoice(CDataBase.testAnswersOneFalseMultipleChoice),0.0);
        Assert.assertEquals("Result should be -1%",-1.0,exercise.controlMultipleChoice(CDataBase.testAnswersSizeMultipleChoice),0.0);
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
        exercise.addQuestionAndAnswer(CDataBase.newQuestion,CDataBase.newAnswerMultipleChoice);
        Assert.assertEquals(currSizeQuestions+1,exercise.getM_questions().size());
        Assert.assertEquals(currSizeAnswers+1,exercise.getM_correctAnswers().size());
    }
}
