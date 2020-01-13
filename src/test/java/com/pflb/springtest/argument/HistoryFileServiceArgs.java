package com.pflb.springtest.argument;

import com.pflb.springtest.provider.HarDtoProvider;
import com.pflb.springtest.provider.HistoryFileProvider;
import org.junit.jupiter.params.provider.Arguments;

import javax.validation.constraints.NotNull;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.stream.Stream;

public class HistoryFileServiceArgs {
    @NotNull
    private static Stream<Arguments> getAllFiles_thenReturnDtoList() {
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

    private static Stream<Arguments> processFile_thenReturnDto() throws IOException {
        return Stream.of(
                Arguments.of(
                        HistoryFileProvider.dto("firefox", "1.2", "HarFile"),
                        HistoryFileProvider.entity(1L, "firefox", "1.2", "HarFile"),
                        HarDtoProvider.multipart_good_har(),
                        HarDtoProvider.dto_minimal_valid()
                )
        );
    }
}
