
import java.io.*;
import java.util.ArrayList;

/**
 * Created by kevin on 23.01.2017.
 */
public class CCourse {

    //constructor
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

    //attributes
    private String m_title;
    private CMember m_admin;
    private CLicense m_license;
    private ArrayList<CExercise> m_exercises;
    private ArrayList<CMember> m_members;
    private ArrayList<CMaterial> m_materials;
    private int m_InstitutionID;

    //methods

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


    public boolean setExercise (String _name, ArrayList<String> _questions, ArrayList _answers, boolean isCloze, CMember _member)
    {
        boolean success = false;
        if (this.m_admin.getM_ID() == _member.getM_ID())
        {
            if (isCloze)
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

    /**************
     *removes the given _exercise in the exercise list of the course
     *returns false if the _member was not the admin of the course
     *************/
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

    /**************
     *executes control exercise function according given _exercise
     *returns -2 if the course does not contain given _exercise
     *************/
    public int controlExercise (CExercise _exercise, ArrayList _answers)
    {
        int result = 0;

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

    /**************
     *removes the given _material in the material list of the course
     *returns false if the _member was not the admin of the course
     * server admin is responsible for deleting unnecessary files on the server
     *************/
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

    /*****************************************************************************
     ** HELPER FUNCTIONS
     ******************************************************************************/

    /**************
     **creates a new ID for the material that was uploaded
     *************/
    private int generateID() {
        int currSize = this.m_materials.size();
        int newID = currSize + 1;

        return newID;
    }

    /**************
     **returns a byte array based on given _file
     *************/
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



