package pl.compendium.hello.twitter.model;

import org.joda.time.DateTime;

public class Tweet {
    public final int _id;
    public final String author;
    public final String message;
    public final DateTime date;

    public Tweet(String message, String author) {
        this(0, message, author, new DateTime());
    }

    public Tweet(int _id, String message, String author) {
        this(_id, message, author, new DateTime());
    }

    public Tweet(int _id, String message, String author, DateTime date) {
        this._id = _id;
        this.author = author;
        this.message = message;
        this.date = date;
    }

    @Override
    public String toString() {
        return "\"" + message + "\"" + " by " + author + " @ " + date;
    }
}
