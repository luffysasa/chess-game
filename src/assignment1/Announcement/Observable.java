package assignment1.Announcement;

import assignment1.UserBuilder.User;

public abstract class Observable {
    public abstract void register(User user);
    public abstract void unregister(User user);
    public abstract void sendMessage(String newMessage);
}
