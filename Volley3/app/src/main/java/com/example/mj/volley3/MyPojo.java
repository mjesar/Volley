package com.example.mj.volley3;


public class MyPojo
{
    private String body;

    private String userID;

    private String title;

    private String id;



    public String getBody ()
    {
        return body;
    }

    public void setBody (String body)
    {
        this.body = body;
    }

    public String getUserID ()
    {
        return userID;
    }

    public void setUserID (String userID)
    {
        this.userID = userID;
    }

    public String getTitle(String title)
    {
        return title;
    }

    public void setTitle (String title)
    {
        this.title = title;
    }

    public String getId()
    {
        return id;
    }

    public void setId (String id)
    {
        this.id= id;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [body = "+body+", userID = "+userID+", title = "+title+", name = "+id+"]";
    }
}
