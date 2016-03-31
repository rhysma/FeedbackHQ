package com.rhysma.feedback.feedbackhq;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Tiffany on 3/23/2016.
 */
public class FeedbackSession
{
    private int sessionId;
    private String sessionType;
    private String dateCreated;

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

    public String getDateCreated()
    {
        return dateCreated;
    }

    public void setDateCreated()
    {
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm");
        dateCreated = sdf.format(date);
    }

    public String toString()
    {
        return getSessionId() + "," + getSessionType() + "," + getDateCreated();
    }

}
