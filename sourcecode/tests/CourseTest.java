/**
 * This is the test class for {@link Course}.
 * It includes tests regarding set/control/remove exercises and add/remove material.
 * Before running this class ensure that the test files are located in your home directory.
 * This files can be found under "others".
 *
 * @author  Kevin Kosinski
 * @see     Cloze
 * @see     Course
 * @see     Exercise
 * @see     Material
 * @see     MultipleChoice
 */

import org.junit.Before;
import org.junit.Test;
import org.junit.Assert;

import java.io.IOException;

public class CourseTest {
    private Course course;

    @Before
    public void setUp() throws IOException {
        course = new Course(DataBase.titleCourse, DataBase.testMemberAdmin, DataBase.InstitutionID);
        course.setExercise(DataBase.nameExercise, DataBase.questions, DataBase.answersCloze,true, DataBase.testMemberAdmin);
        course.setExercise(DataBase.nameExercise, DataBase.questions, DataBase.answersMultipleChoice,false, DataBase.testMemberAdmin);
        course.setMaterial(DataBase.TestFileName, DataBase.TestFileType, DataBase.testMemberAdmin);

    }

    @Test
    public void testSetExercise ()
    {
        int currSizeExercises = course.getM_exercises().size();

        //set Cloze True
        course.setExercise(DataBase.nameExercise, DataBase.questions, DataBase.answersCloze,true, DataBase.testMemberAdmin);
        Assert.assertEquals(currSizeExercises+1,course.getM_exercises().size());
        Assert.assertTrue("Member is not an administrator", true);

        //set Cloze false -> no permission
        course.setExercise(DataBase.nameExercise, DataBase.questions, DataBase.answersCloze,true, DataBase.testMemberNoAdmin);
        Assert.assertEquals(currSizeExercises+1,course.getM_exercises().size());
        Assert.assertFalse("Member is an administrator", false);

        //set MultipleChoice True
        currSizeExercises = course.getM_exercises().size();
        course.setExercise(DataBase.nameExercise, DataBase.questions, DataBase.answersMultipleChoice,false, DataBase.testMemberAdmin);
        Assert.assertEquals(currSizeExercises+1,course.getM_exercises().size());
        Assert.assertTrue("Member is not an administrator", true);

        //set MultipleChoice False
        course.setExercise(DataBase.nameExercise, DataBase.questions, DataBase.answersMultipleChoice,false, DataBase.testMemberNoAdmin);
        Assert.assertEquals(currSizeExercises+1,course.getM_exercises().size());
        Assert.assertFalse("Member is an administrator", false);
    }

    @Test
    public void testControlExercise() {

        //controlExercise() cloze
        Exercise clozeInCourse = course.getM_exercises().get(0);
        Exercise clozeNotInCourse = new Cloze("NotInCourse", DataBase.questions, DataBase.answersCloze);

        Assert.assertEquals("Result should be 100.0%",100.0,course.controlExercise(clozeInCourse, DataBase.answersCloze),0.0);
        Assert.assertEquals("Result should be 0.0%",0.0,course.controlExercise(clozeInCourse, DataBase.testAnswersFalseCloze),0.0);
        Assert.assertEquals("Result should be 75.0%",75.0,course.controlExercise(clozeInCourse, DataBase.testAnswersOneFalseCloze),0.0);
        Assert.assertEquals("Result should be -1",-1.0,course.controlExercise(clozeInCourse, DataBase.testAnswersSizeCloze),0.0);
        Assert.assertEquals("Result should be -2",-2.0,course.controlExercise(clozeNotInCourse, DataBase.answersCloze),0.0);

        //controlExercise() multipleChoice
        Exercise multipleChoiceInCourse = course.getM_exercises().get(1);
        Exercise multipleChoiceNotInCourse = new Cloze("NotInCourse", DataBase.questions, DataBase.answersCloze);

        Assert.assertEquals("Result should be 100.0%",100.0,course.controlExercise(multipleChoiceInCourse, DataBase.answersMultipleChoice),0.0);
        Assert.assertEquals("Result should be 0.0%",0.0,course.controlExercise(multipleChoiceInCourse, DataBase.testAnswersFalseMultipleChoice),0.0);
        Assert.assertEquals("Result should be 75.0%",75.0,course.controlExercise(multipleChoiceInCourse, DataBase.testAnswersOneFalseMultipleChoice),0.0);
        Assert.assertEquals("Result should be -1",-1.0,course.controlExercise(clozeInCourse, DataBase.testAnswersSizeMultipleChoice),0.0);
        Assert.assertEquals("Result should be -2",-2.0,course.controlExercise(multipleChoiceNotInCourse, DataBase.answersCloze),0.0);
    }

    @Test
    public void testRemoveExercise () {

        int currSizeExercises = course.getM_exercises().size();

        //true conditions
        Assert.assertTrue("Exercise not removed!",course.removeExercise(course.getM_exercises().get(0), DataBase.testMemberAdmin));
        Assert.assertEquals(currSizeExercises-1,course.getM_exercises().size());

        //false conditions
        Assert.assertFalse("Exercise has been removed!", course.removeExercise(course.getM_exercises().get(0), DataBase.testMemberNoAdmin));
        Assert.assertEquals(currSizeExercises-1,course.getM_exercises().size());

    }

    @Test
    public void testSetMaterial () throws IOException {

        //set Material True
        int currSizeMaterial = course.getM_materials().size();
        Assert.assertTrue("Material hasn`t been set.", course.setMaterial(DataBase.NewTestFileName, DataBase.NewTestFileType, DataBase.testMemberAdmin));
        Assert.assertEquals(currSizeMaterial+1,course.getM_exercises().size());

        //set Material false
        String titleFalse ="false";
        String fileTypeFalse = "txt";
        Assert.assertFalse("Material has been set.", course.setMaterial(titleFalse,fileTypeFalse, DataBase.testMemberAdmin));
        Assert.assertFalse("Material has been set.", course.setMaterial(DataBase.NewTestFileName, DataBase.NewTestFileType, DataBase.testMemberNoAdmin));
    }

    @Test
    public void testRemoveMaterial () {

        //remove Material False
        String titleFalse ="false";
        String fileTypeFalse = "txt";
        Assert.assertFalse("Material has been removed.", course.removeMaterial(course.getM_materials().get(0), DataBase.testMemberNoAdmin));

        //remove Material True
        int currSizeMaterial = course.getM_materials().size();
        Assert.assertTrue("Material hasn´t been removed.",course.removeMaterial(course.getM_materials().get(0), DataBase.testMemberAdmin));
        Assert.assertEquals(currSizeMaterial-1,course.getM_materials().size());


    }

}
