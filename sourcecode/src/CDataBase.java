/**
 * This class simulates the database which will be implemented next semester.
 * At this times it just stores test data.
 *
 * @author  Kevin Kosinski
 * @author  Ron Wagner
 * @see     CMember
 */

import java.util.ArrayList;
import java.util.Arrays;

public class CDataBase {

    /**************
     *MEMBER FOR TESTING
     *************/
    public static final String firstName = "Max";
    public static final String lastName = "Mustermann";
    public static final int idAdmin = 1234;
    public static final int idNoAdmin = 5678;
    public static final String mail = "test@mail.com";
    public static final String mail_wrong = "wrong@mail.com";
    public static final CMember testMemberAdmin = new CMember(firstName,lastName,idAdmin,mail);
    public static final CMember testMemberNoAdmin = new CMember(firstName,lastName,idNoAdmin,mail);


    /**************
     *TEST ELEMENTS FOR CEXERCISETEST
     *************/
    public static final ArrayList<String> questions = new ArrayList<String>(Arrays.asList("Question1","Question2","Question3","Question4"));
    public static final String nameExercise = "testExercise";
    public static final String newQuestion = "newQuestion";

    /**************
     *TEST ELEMENTS FOR CMULTIPLECHOICETEST
     *************/
    public static final Boolean newAnswerMultipleChoice = false;
    public static final ArrayList<Boolean> answersMultipleChoice = new ArrayList<Boolean>(Arrays.asList(true,false,true,false));
    public static final ArrayList<Boolean> testAnswersTrueMultipleChoice = new ArrayList<Boolean>(Arrays.asList(true,false,true,false));
    public static final ArrayList<Boolean> testAnswersFalseMultipleChoice = new ArrayList<Boolean>(Arrays.asList(false,true,false,true));
    public static final ArrayList<Boolean> testAnswersOneFalseMultipleChoice = new ArrayList<Boolean>(Arrays.asList(true,false,false,false));
    public static final ArrayList<Boolean> testAnswersSizeMultipleChoice = new ArrayList<Boolean>(Arrays.asList(true,false));

    /**************
     *TEST ELEMENTS FOR CCLOZETEST
     *************/
    public static final String newAnswerCloze = "falseAnswer";
    public static final ArrayList<String> answersCloze= new ArrayList<String>(Arrays.asList("Answer1","Answer2","Answer3","Answer4"));
    public static final ArrayList<String> testAnswersTrueCloze= new ArrayList<String >(Arrays.asList("Answer1","Answer2","Answer3","Answer4"));
    public static final ArrayList<String> testAnswersFalseCloze = new ArrayList<String>(Arrays.asList("falseAnswer","falseAnswer","falseAnswer","falseAnswer"));
    public static final ArrayList<String> testAnswersOneFalseCloze = new ArrayList<String>(Arrays.asList("Answer1","Answer2","Answer3","falseAnswer"));
    public static final ArrayList<String> testAnswersSizeCloze = new ArrayList<String>(Arrays.asList("Answer1","Answer2"));

    /**************
     *TEST ELEMENTS FOR CCOURSETEST
     *************/
    public static final String titleCourse = "newCourse";
    public static final int InstitutionID = 9876;

    public static final String TestFileName = "Test";
    public static final String TestFileType = "png";

    public static final String NewTestFileName = "Test";
    public static final String NewTestFileType = "pdf";

    //test elements for CInstitution
    public static final int wrongKey = 5;
}
