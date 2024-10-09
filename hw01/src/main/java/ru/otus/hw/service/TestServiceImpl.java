package ru.otus.hw.service;

import lombok.RequiredArgsConstructor;
import ru.otus.hw.config.TestFileNameProvider;
import ru.otus.hw.dao.CsvQuestionDao;
import ru.otus.hw.dao.QuestionDao;

import java.util.concurrent.atomic.AtomicInteger;

@RequiredArgsConstructor
public class TestServiceImpl implements TestService {

    private final IOService ioService;

    private final TestFileNameProvider provider;

    @Override
    public void executeTest() {
        ioService.printLine("");
        ioService.printFormattedLine("Please answer the questions below%n");
        QuestionDao questionDao = new CsvQuestionDao(provider);
        questionDao.findAll()
                .forEach(question -> {
                    var count = new AtomicInteger();
                    ioService.printLine(question.text());
                    question.answers().forEach(answer -> {
                        ioService.printLine(count.incrementAndGet()
                                + ". " + answer.text());
                    });
                    ioService.printLine("______");

                });

        // Получить вопросы из дао и вывести их с вариантами ответов
    }
}
