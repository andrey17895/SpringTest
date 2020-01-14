package com.pflb.springtest.argument;

import com.pflb.springtest.model.exception.ApplicationException;
import com.pflb.springtest.model.exception.CustomExceptionType;
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
    private static Stream<Arguments> getAllFiles_thenReturnDtoList() throws IOException {
        return Stream.of(
                Arguments.of(
                        Collections.singletonList(HistoryFileProvider.dto_firefox_v1_2()),
                        Collections.singletonList(HistoryFileProvider.entity_firefox_v1_2_id_1())
                ),
                Arguments.of(
                        Arrays.asList(
                                HistoryFileProvider.dto_firefox_v1_2(),
                                HistoryFileProvider.dto_chrome_v1_2()
                        ),
                        Arrays.asList(
                                HistoryFileProvider.entity_firefox_v1_2_id_1(),
                                HistoryFileProvider.entity_chrome_v1_2_id_2()
                        )
                ),
                Arguments.of(
                        Collections.emptyList(),
                        Collections.emptyList()
                )
        );
    }

    private static Stream<Arguments> processFile_thenThrowException() throws IOException {
        return Stream.of(
                Arguments.of(
                        HarDtoProvider.multipart_invalid_no_log(),
                        HarDtoProvider.dto_invalid_no_log(),
                        new ApplicationException(CustomExceptionType.BAD_HAR_FILE)
                )
        );
    }

    private static Stream<Arguments> processFile_thenReturnDto() throws IOException {
        return Stream.of(
                Arguments.of(
                        HistoryFileProvider.dto_firefox_v1_2(),
                        HistoryFileProvider.entity_firefox_v1_2_id_1(),
                        HarDtoProvider.multipart_valid_har(),
                        HarDtoProvider.dto_valid_empty_body()
                )
        );
    }
}
