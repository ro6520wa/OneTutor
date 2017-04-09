/**
 * This class is intended to just store all necessary information about a material that has been uploaded to a course.
 *
 * @author Kevin Kosinski
 */


public class Material {

    /**************
     *CONSTRUCTOR
     *************/
    protected Material(String _name, String _filePath, String _fileType, int _ID)
    {
        this.m_name = _name;
        this.m_filePath = _filePath;
        this.m_fileType = _fileType;
        this.m_ID = _ID;
    }

    /**************
     *ATTRIBUTES
     *************/
    private String m_name;
    private String m_filePath;
    private String m_fileType;
    private  int m_ID;


    /**************
     *METHODS
     *************/
    public String getM_name() {
        return m_name;
    }

    public void setM_name(String m_name) {
        this.m_name = m_name;
    }

    public String getM_filePath() {
        return m_filePath;
    }

    public void setM_filePath(String m_filePath) {
        this.m_filePath = m_filePath;
    }

    public String getM_fileType() {
        return m_fileType;
    }

    public void setM_fileType(String m_fileType) {
        this.m_fileType = m_fileType;
    }

    public int getM_ID() {
        return m_ID;
    }

    public void setM_ID(int m_ID) {
        this.m_ID = m_ID;
    }
}
