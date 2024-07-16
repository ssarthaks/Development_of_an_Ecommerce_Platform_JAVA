package controller;

import javax.servlet.ServletContext;
import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;
import java.util.concurrent.ConcurrentHashMap;

@WebListener
public class SessionTracker implements HttpSessionListener {

    private static final ConcurrentHashMap<String, HttpSession> activeSessions = new ConcurrentHashMap<>();

    @Override
    public void sessionCreated(HttpSessionEvent se) {
        HttpSession session = se.getSession();
        activeSessions.put(session.getId(), session);
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent se) {
        HttpSession session = se.getSession();
        activeSessions.remove(session.getId());
    }

    public static void terminateAllSessions(ServletContext context) {
        for (HttpSession session : activeSessions.values()) {
            session.invalidate();
        }
    }
}
