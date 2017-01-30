/**
 * Created by kevin on 22.12.2016.
 */


public class CMaterial {

    //constructor
    protected CMaterial(String _name, String _filePath)
    {
        this.m_name = _name;
        this.m_filePath = _filePath;
    }

    //attributes
    private String m_name;
    private String m_filePath;


    //methods
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
}
