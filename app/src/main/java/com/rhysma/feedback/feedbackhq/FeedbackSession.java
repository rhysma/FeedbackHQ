package com.rhysma.feedback.feedbackhq;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Rhysma on 3/23/2016.
 */
public class FeedbackSession
{
    private int sessionId;
    private String sessionType;
    private SimpleDateFormat dateCreated;

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
        if(type == "Lecture" || type == "Seminar" || type == "Theatre")
        {
            sessionType = type;
        }

    }

    public SimpleDateFormat getDateCreated()
    {
        return dateCreated;
    }

    public void setDateCreated()
    {
        Date d = new Date();
        dateCreated = new SimpleDateFormat(d.toString());
    }

    public String toString()
    {
        return getSessionId() + "," + getSessionType() + "," + getDateCreated();
    }

}
