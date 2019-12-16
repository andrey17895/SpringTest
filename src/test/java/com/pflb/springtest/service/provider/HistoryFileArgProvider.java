package com.pflb.springtest.service.provider;

import com.pflb.springtest.generator.HistoryFileProvider;
import com.pflb.springtest.generator.TestGenerator;
import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.params.provider.Arguments;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.stream.Stream;

public class HistoryFileArgProvider {
    @NotNull
    private static Stream<Arguments> getAllFiles() {
        return Stream.of(
                Arguments.of(
                        Collections.singletonList(HistoryFileProvider.dto()),
                        Collections.singletonList(HistoryFileProvider.entity())
                ),
                Arguments.of(
                        Arrays.asList(
                                HistoryFileProvider.dto("firefox", "1.2", "HarFile"),
                                HistoryFileProvider.dto("chrome", "1.2", "HarFile")
                        ),
                        Arrays.asList(
                                HistoryFileProvider.entity(1L, "firefox", "1.2", "HarFile"),
                                HistoryFileProvider.entity(2L, "chrome", "1.2", "HarFile")
                        )
                ),
                Arguments.of(
                        Collections.emptyList(),
                        Collections.emptyList()
                )
        );
    }

    private static Stream<Arguments> processFileOk() throws IOException {
        return Stream.of(
                Arguments.of(
                        HistoryFileProvider.dto("firefox", "1.2", "HarFile"),
                        HistoryFileProvider.entity(1L, "firefox", "1.2", "HarFile"),
                        TestGenerator.multipartFile()
                )
        );
    }
}
