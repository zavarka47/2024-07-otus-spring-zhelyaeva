package ru.otus.hw.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import ru.otus.hw.domain.Student;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
public class StudentServiceImplTest {

    @Mock
    IOService ioService;
    @InjectMocks
    StudentServiceImpl service;

    @Test
    void When_executeTestFor_Expect_Success() {

        when(ioService.readStringWithPrompt(any())).thenReturn("name");

        Assertions.assertEquals(service.determineCurrentStudent(), new Student("name", "name"));

    }
}
