package com.example.my1206server;

public class ChatVO

{
    private String nick;
    private String content;
    private String day;

    public ChatVO(String nick, String content, String day) {
        this.nick = nick;
        this.content = content;
        this.day = day;
    }

    public String getNick() {
        return nick;
    }

    public void setNick(String nick) {
        this.nick = nick;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    @Override
    public String toString() {
        return "ChatVO{" +
                "nick='" + nick + '\'' +
                ", content='" + content + '\'' +
                ", day='" + day + '\'' +
                '}';
    }
}
