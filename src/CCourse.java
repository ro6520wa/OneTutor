/**
 * This class represents a specific course within an institution.
 * It is characterized by a title, an admin, an array of exercises, an array of materials, an array of members
 * and a license.<br>
 * Each course belongs to an institution and only the administrator of the course is able to add/remove exercises and
 * materials. Apart of that, each member is able to call the "controlExercise" method to have its exercise checked.
 *
 * @author Kevin Kosinski
 * @see CExercise
 * @see CInstitution
 * @see CLicense
 * @see CMaterial
 * @see CMember
 */


import java.io.*;
import java.util.ArrayList;

public class CCourse {

    /**
     * Class constructor that creates an instance of this class with the given attributes.
     * It also creates a new instance of {@link CLicense} and adds this course to the admins course list.
     *
     * @param   _title            Character string that represents the name of this course
     * @param   _courseAdmin      Member who is the administrator of the course with the mentioned privileges
     * @param   _InstitutionID    Integer value to identify the institution this course belongs to
     * @return                    An instance of this class with the specific attributes
     */
    public CCourse (String _title, CMember _courseAdmin, int _InstitutionID)
    {
        this.m_title = _title;
        this.m_admin = _courseAdmin;
        this.m_license = new CLicense();
        this.m_InstitutionID = _InstitutionID;

        this.m_exercises = new ArrayList<CExercise>();
        this.m_materials = new ArrayList<CMaterial>();
        this.m_members = new ArrayList<CMember>();

        _courseAdmin.addCourse(this);
    }

    /**************
     *ATTRIBUTES
     *************/
    private String m_title;
    private CMember m_admin;
    private CLicense m_license;
    private ArrayList<CExercise> m_exercises;
    private ArrayList<CMember> m_members;
    private ArrayList<CMaterial> m_materials;
    private int m_InstitutionID;

    /**************
     *METHODS
     *************/

    public int getM_InstitutionID() {
        return m_InstitutionID;
    }

    public String getM_title() {
        return m_title;
    }

    public boolean setM_title(String m_title, CMember _member) {

        boolean success = false;

        //check if _member has permission to change m_title
        if (_member.getM_ID() == this.m_admin.getM_ID())
        {
            this.m_title = m_title;
            return success = true;
        }
        else
        {
            return success;
        }
    }

    public CMember getM_admin() {
        return m_admin;
    }

    public CLicense getM_license() {
        return m_license;
    }

    public ArrayList<CExercise> getM_exercises() {
        return m_exercises;
    }

    public ArrayList<CMember> getM_members() {
        return m_members;
    }

    public ArrayList<CMaterial> getM_materials() {
        return m_materials;
    }

    public void addMember(CMember _member)
    {
        this.m_members.add(_member);
    }

    public void removeMember(CMember _member) {this.m_members.remove(_member);}

    /**
     * This method adds the given exercise to the exercises list of the course.
     * Only the administrator of the course is allowed to add exercises.
     *
     * @param   _name             Character string that represents the name of this exercise
     * @param   _questions        Array of character strings that represents the questions
     * @param   _answers          Array of character strings that represents the answers
     * @param   _isCloze          Boolean to identify the kind of this exercise, according to this an instance of
     *                            {@link CCloze} or {@link CMultipleChoice} will be created
     * @param   _member           Member that is calling this method, required to validate if it is the administrator
     * @return                    True or false depending on whether the exercise has been added successful
     */
    public boolean setExercise (String _name, ArrayList<String> _questions, ArrayList _answers, boolean _isCloze, CMember _member)
    {
        boolean success = false;
        if (this.m_admin.getM_ID() == _member.getM_ID())
        {
            if (_isCloze)
            {
                CExercise newExercise = new CCloze(_name, _questions, _answers);
                this.m_exercises.add(newExercise);
                return success = true;
            }
            else
            {
                CExercise newExercise = new CMultipleChoice(_name, _questions, _answers);
                this.m_exercises.add(newExercise);
                return success = true;
            }
        }

        return success;
    }

    /**
     * This method removes the given exercise from the exercises list of the course.
     * Only the administrator of the course is allowed to remove exercises.
     *
     * @param   _exercise   Exercise that has to be deleted
     * @param   _member     Member who is calling this method, required to validate if it is the administrator
     * @return              True or false depending on whether the exercise has been deleted successful
     */
    public boolean removeExercise(CExercise _exercise, CMember _member)
    {
        boolean success = false;

        if (this.m_admin.getM_ID() == _member.getM_ID())
        {
            if (this.m_exercises.contains(_exercise))
            {
                this.m_exercises.remove(_exercise);
                return success = true;
            }
        }

        return success;
    }

    /**
     * With this method each member of the course is able to get its exercises checked automatically.
     * If the given exercise does not exist in the exercises list of the course it returns the invalid value -2.
     * Else it calls the appropriate control-method of the exercise by checking which kind of exercise it is.
     *
     * @param   _exercise   Exercise that has to be checked
     * @param   _answers    Array of either character strings or boolean that will be compared with the correct answers
     * @return              Float value that represents the result achieved
     */
    public float controlExercise (CExercise _exercise, ArrayList _answers)
    {
        float result = 0;

        if (!this.m_exercises.contains(_exercise))
        {
            return result = - 2;
        }

        if (_exercise instanceof CCloze)
        {
            result =(((CCloze) _exercise).controlCloze(_answers));
        } else
        {
            result =(((CMultipleChoice) _exercise).controlMultipleChoice(_answers));
        }

        return result;
    }

    /**
     * This method provides the opportunity to upload each kind of files as additional information to the courses topic.
     * The files to be uploaded has to be located in the callers home directory. Only the administrator of the
     * course is allowed to upload files.<br>
     * It uses the helper function "readFile" to create a byte array that serves as input for the output stream.
     * If the file has been uploaded successful this method creates a new instance of {@link CMaterial} with the appropriate
     * attributes and adds it to the material list of the course.
     *
     * @param   _title                  Array of character strings that represents the name of the material
     * @param   _fileType               Character string to identify which kind of file has to be created
     * @param   _member                 Member who is calling this method, required to validate if it is the administrator
     * @return                          True or false depending on whether the material has been uploaded successful
     * @throws  IOException             If an input or output exception occurred
     * @throws  FileNotFoundException   If the given file does not exist in the callers home directory
     */
    public boolean setMaterial(String _title, String _fileType, CMember _member) throws  IOException
    {
        boolean success = false;

        if (this.m_admin.getM_ID() == _member.getM_ID())
        {
            //Create paths for input-/output streams and filename
            String filename = _title + "." + _fileType;
            String workDir = System.getProperty("user.dir");
            String downloadDir = System.getProperty("user.home");
            String inputPath = downloadDir + File.separator + filename;
            String finalUploadDir = workDir + File.separator + "sonstiges" + File.separator + filename;

            try
            {
                File inputFile = new File(inputPath);
                File outputFile = new File(finalUploadDir);

                if (!outputFile.exists())
                {
                    outputFile.createNewFile();
                }

                FileOutputStream outputStream = null;

                try {

                    outputStream = new FileOutputStream(outputFile);

                    //write bytes to outputStream, using helper function readFile
                    outputStream.write(readFile(inputFile));
                }
                catch (FileNotFoundException fnfe) {
                    fnfe.printStackTrace();
                    return success;
                }
                catch (IOException ioe)
                {
                    ioe.printStackTrace();
                    return success;
                }
                finally {
                    try{
                        if (outputStream != null){
                            outputStream.close();
                        }
                    } catch (IOException ioe) {
                        ioe.printStackTrace();
                        return success;
                    }
                }

            }
            catch (IOException ioe)
            {
                ioe.printStackTrace();
                return success;
            }

            //first: create new material
            //second: add new created material to material list
            int materialID = this.generateID();
            CMaterial newMaterial = new CMaterial(_title, finalUploadDir, _fileType, materialID);
            this.m_materials.add(newMaterial);

            return success = true;
        }
        else
        {
            return success;
        }
    }

    /**
     * This method removes the given material from the material list of the course. Only the administrator of the course
     * is allowed to remove material.
     * It returns false either if the given member is not the administrator of the course or the given material does
     * not exist in the material list of the course.
     * The server administrator is responsible for deleting unnecessary files on the server.
     *
     * @param   _material   Material that has to be deleted
     * @param   _member     Member who is calling this method, required to validate if it is the administrator
     * @return              True or false depending on whether the material has been deleted successful
     */
    public boolean removeMaterial (CMaterial _material, CMember _member)
    {
        boolean success = false;

        if (this.m_admin.getM_ID() == _member.getM_ID() && this.m_materials.contains(_material))
        {
            this.m_materials.remove(_material);
            return success = true;
        }
        else
        {
            return success;
        }
    }

    /**************
    *HELPER FUNCTIONS
    *************/

     //creates a new ID for the material that was uploaded
    private int generateID() {
        int currSize = this.m_materials.size();
        int newID = currSize + 1;

        return newID;
    }

    /**
     * This method creates a byte array with the content of the given file. The length of this file has to be
     * not exceed the value of an integer. It is called by the "setMaterial" method.
     *
     * @param   _file           File that serves as input for the byte array
     * @return                  Array of bytes with the content of the given file
     * @throws  IOException     If an input or output exception occurred or the given file is too large or it could not
     *                          been read the completely file
     */
    private byte[] readFile(File _file) throws IOException
    {
        //calculate size of file
        long length = _file.length();

        //make sure that file is not longer than max value of Integer
        if (length > Integer.MAX_VALUE) {
            throw new IOException("File too large!");
        }

        //size of file is ok -> create byte array according to size
        byte[] buffer = new byte[(int) length];

        //read bytes
        int offset = 0;
        int countRead = 0;

        InputStream in = new FileInputStream(_file);
        try{
            //ensure that EOF isn´t reached and calculate countRead
            while (offset < buffer.length && (countRead = in.read(buffer, offset, buffer.length-offset)) >= 0) {

                offset += countRead;
            }
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
        finally {

            try {
                if (in != null) {
                    in.close();
                }
            } catch (IOException ioe)
            {
                ioe.printStackTrace();
            }

            //check if all bytes have been read
            if (offset < buffer.length) {
                throw new IOException("Couldn´t read completely file" + _file.getName());
            }
        }

        return buffer;
    }
}



