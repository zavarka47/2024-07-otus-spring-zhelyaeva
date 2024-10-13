package ru.otus.hw.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.hw.dao.QuestionDao;
import ru.otus.hw.domain.Answer;
import ru.otus.hw.domain.Student;
import ru.otus.hw.domain.TestResult;

import java.util.concurrent.atomic.AtomicInteger;

@Service
@RequiredArgsConstructor
public class TestServiceImpl implements TestService {

    private final LocalizedIOService ioService;

    private final QuestionDao questionDao;

    @Override
    public TestResult executeTestFor(Student student) {
        ioService.printLine("");
        ioService.printLineLocalized("TestService.answer.the.questions");
        ioService.printLine("");

        var questions = questionDao.findAll();
        var testResult = new TestResult(student);

        for (var question: questions) {
            var answers = question.answers();
            ioService.printFormattedLine(question.text(), answers.stream().map(Answer::text).toList());
            var count = new AtomicInteger();
            answers.forEach(answer -> ioService.printLine(count.incrementAndGet() + ". " + answer.text()));
            var answr = ioService.readIntForRangeLocalized(1, answers.size(), "TestService.answer.the.err.msg");

            var isAnswerValid = question.answers().get(answr - 1).isCorrect();
            testResult.applyAnswer(question, isAnswerValid);
        }
        return testResult;
    }

}
