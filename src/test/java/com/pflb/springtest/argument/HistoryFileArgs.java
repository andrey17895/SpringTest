package com.pflb.springtest.argument;

import com.pflb.springtest.provider.HistoryFileProvider;
import org.junit.jupiter.params.provider.Arguments;

import java.io.IOException;
import java.util.stream.Stream;

public class HistoryFileArgs {
    public static Stream<Arguments> save() throws IOException {
        return Stream.of(
                Arguments.of(
                        HistoryFileProvider.entity_firefox_v1_2_id_1()
                )
        );
    }
}
