package ru.otus.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.otus.hw.config.AppProperties;
import ru.otus.hw.exceptions.QuestionReadException;
import ru.otus.hw.service.StreamsIOService;
import ru.otus.hw.service.TestService;
import ru.otus.hw.service.TestServiceImpl;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class TestServiceImplTest {
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();

    @Test
    void executeTest1_Success() {
        TestService service = new TestServiceImpl(new StreamsIOService(new PrintStream(outContent)),
                new AppProperties("wrongPath.json"));
        Assertions.assertThrows(QuestionReadException.class, service::executeTest);
    }

    @Test
    void executeTest2_Success() {
        TestService service = new TestServiceImpl(new StreamsIOService(new PrintStream(outContent)),
                new AppProperties("questionsForTest.csv"));
        service.executeTest();
        Assertions.assertTrue(outContent.toString().trim().contains("Please answer the questions below"));
    }

}
