package org.example.web4.services;

import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.springframework.jmx.export.annotation.ManagedResource;
import org.springframework.stereotype.Component;

import javax.management.*;
import java.io.Serializable;
import java.util.concurrent.atomic.AtomicInteger;

@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Component
@ManagedResource(objectName = "org.example.web4.services:type=HitRatio", description = "Tracks hit statistics and sends notifications on 3 consecutive misses")
public class AttemptStats implements AttemptStatsMBean, NotificationBroadcaster, Serializable {
    AtomicInteger totalSupplied = new AtomicInteger();
    AtomicInteger totalIn = new AtomicInteger();
    AtomicInteger consecutiveMisses = new AtomicInteger();
    NotificationBroadcasterSupport broadcaster = new NotificationBroadcasterSupport();

    @Override
    public int getTotalSupplied() {
        return totalSupplied.get();
    }

    @Override
    public int getTotalIn() {
        return totalIn.get();
    }

    @Override
    public int getTotalMisses() {
        return totalSupplied.get() - totalIn.get();
    }

    public void recordAttempt(boolean hit) {
        totalSupplied.incrementAndGet();
        if (hit) {
            totalIn.incrementAndGet();
            consecutiveMisses.set(0);
        } else {
            if (consecutiveMisses.incrementAndGet() == 3) {
                broadcaster.sendNotification(new Notification(
                        "consecutive.misses",
                        this,
                        System.currentTimeMillis(),
                        "3 consecutive misses detected."
                ));
                consecutiveMisses.set(0);
            }
        }
    }

    @Override
    public void addNotificationListener(NotificationListener listener, NotificationFilter filter, Object handback) {
        broadcaster.addNotificationListener(listener, filter, handback);
    }

    @Override
    public void removeNotificationListener(NotificationListener listener) throws ListenerNotFoundException {
        broadcaster.removeNotificationListener(listener);
    }

    @Override
    public MBeanNotificationInfo[] getNotificationInfo() {
        return new MBeanNotificationInfo[]{
                new MBeanNotificationInfo(
                        new String[]{"consecutive.misses"},
                        Notification.class.getName(),
                        "Notification sent when 3 consecutive misses are recorded"
                )
        };
    }
}
