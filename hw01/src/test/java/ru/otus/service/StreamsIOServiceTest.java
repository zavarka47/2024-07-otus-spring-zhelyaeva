package ru.otus.service;

import org.junit.jupiter.api.*;
import ru.otus.hw.service.IOService;
import ru.otus.hw.service.StreamsIOService;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class StreamsIOServiceTest {
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    IOService ioService = new StreamsIOService(new PrintStream(outContent));


    @BeforeEach
    public void setUp() {
        System.setOut(new PrintStream(outContent));
    }

    @Test
    void ioService_printLine_Success () {
        ioService.printLine("hello");
        Assertions.assertEquals("hello", outContent.toString().trim());
    }

    @Test
    void ioService_pintFormattedLine_Success () {
        ioService.printFormattedLine("hello");
        Assertions.assertEquals("hello", outContent.toString().trim());
    }

}
