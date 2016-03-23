/**
 * Created by Rhysma on 3/23/2016.
 */
public class Session
{
    private int sessionId;
    private String sessionType;

    public int getSessionId()
    {
        return sessionId;
    }

    public void setSessionId(int id)
    {
        sessionId = id;
    }

    public String getSessionType()
    {
        return sessionType;
    }

    public void setSessionType(String type)
    {
        //validate that the type is one of the accepted types
        sessionType = type;
    }
}
