/**
 * Created by kevin on 22.12.2016.
 */
public class CPermission {
    //attributes
    private boolean m_execute;
    private boolean m_read;
    private boolean m_write;

    //constructor
    public CPermission (boolean _execute, boolean _read, boolean _write)
    {
        this.m_execute = _execute;
        this.m_read = _read;
        this.m_write = _write;
    }
    //methods
    public boolean isM_execute() {
        return m_execute;
    }

    public void setM_execute(boolean _execute) {
        this.m_execute = _execute;
    }

    public boolean isM_read() {
        return m_read;
    }

    public void setM_read(boolean _read) {
        this.m_read = _read;
    }

    public boolean isM_write() {
        return m_write;
    }

    public void setM_write(boolean _write) {
        this.m_write = _write;
    }
}
