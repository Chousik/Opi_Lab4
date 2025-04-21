package org.example.web4.services;

import org.example.web4.controllers.SessionController;
import org.example.web4.dataBase.models.Points;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class SessionControllerTest {

    @Mock
    private SessionService sessionService;

    @InjectMocks
    private SessionController sessionController;

    @Test
    public void testAddPointValid() {
        Points mockPoint = new Points();
        mockPoint.setX(1);
        mockPoint.setY(1);
        mockPoint.setR(2);
        mockPoint.setIshit(true);

        when(sessionService.addPoint(any(Points.class))).thenReturn(mockPoint);

        Points result = sessionController.addPoint(1, 1, 2);

        Assertions.assertNotNull(result);
        assertTrue(result.isIshit());
        verify(sessionService, times(1)).addPoint(any(Points.class));
    }

    @Test()
    public void testAddPointInvalid() {
        Assertions.assertThrows(RuntimeException.class,() -> sessionController.addPoint(10, 10, 5));
    }

    @Test
    public void testGetPointsByUser() {
        Points point1 = new Points();
        Points point2 = new Points();

        List<Points> mockPoints = Arrays.asList(point1, point2);

        when(sessionService.getUserPoints()).thenReturn(mockPoints);

        List<Points> results = sessionController.getPointsByUser();

        assertEquals(2, results.size());
        verify(sessionService, times(1)).getUserPoints();
    }

    @Test
    public void testGetPointsByUserEmpty() {
        when(sessionService.getUserPoints()).thenReturn(Collections.emptyList());

        List<Points> results = sessionController.getPointsByUser();

        assertTrue(results.isEmpty());
        verify(sessionService, times(1)).getUserPoints();
    }
}
