package org.example.web4.services;

import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.springframework.jmx.export.annotation.ManagedResource;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Component
@ManagedResource(objectName = "org.example.web4.services:type=AreaCalculator", description = "Calculates polygon area based on added points")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AreaCalculator implements AreaCalculatorMBean, Serializable {
    final List<Point> points = new ArrayList<>();

    @Override
    public synchronized void addPoint(double x, double y) {
        points.add(new Point(x, y));
        getArea();
    }

    @Override
    public synchronized double getArea() {
        if (points.size() < 3) return 0.0;

        double area = 0.0;
        int n = points.size();
        for (int i = 0; i < n; i++) {
            Point p1 = points.get(i);
            Point p2 = points.get((i + 1) % n);
            area += (p1.x * p2.y) - (p2.x * p1.y);
        }
        return Math.abs(area / 2.0);
    }
    private record Point(double x, double y) {
    }
}
