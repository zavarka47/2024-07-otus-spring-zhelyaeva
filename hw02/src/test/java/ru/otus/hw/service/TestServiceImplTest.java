package ru.otus.hw.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import ru.otus.hw.dao.QuestionDao;
import ru.otus.hw.domain.Answer;
import ru.otus.hw.domain.Question;
import ru.otus.hw.domain.Student;
import ru.otus.hw.domain.TestResult;

import java.util.List;

import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
public class TestServiceImplTest {

    @Mock
    IOService ioService;
    @Mock
    QuestionDao questionDao;
    @InjectMocks
    TestServiceImpl service;
    static List<Answer> answers;
    static List<Question> questions;
    static Student student;
    static TestResult result;

    @BeforeAll
    static void init() {

        answers = List.of(
                new Answer("textAnswer1", true),
                new Answer("textAnswer2", false),
                new Answer("textAnswer3", false));

        questions = List.of(
                new Question("textQuestion1", answers),
                new Question("textQuestion2", answers),
                new Question("textQuestion3", answers));

        student = new Student("firstName", "lastName");
        result = new TestResult(student);
        result.setRightAnswersCount(3);
        result.getAnsweredQuestions().addAll(questions);

    }

    @Test
    void When_executeTestFor_Expect_Success() {

        when(questionDao.findAll()).thenReturn(questions);
        when(ioService.readIntForRange(0, 2, "errMsg")).thenReturn(0);

        Assertions.assertEquals(service.executeTestFor(student), result);

    }
}
