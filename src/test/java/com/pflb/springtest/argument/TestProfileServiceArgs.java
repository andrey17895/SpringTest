package com.pflb.springtest.argument;

import com.pflb.springtest.provider.TestProfileProvider;
import org.junit.jupiter.params.provider.Arguments;

import java.util.Arrays;
import java.util.Collections;
import java.util.stream.Stream;

public class TestProfileServiceArgs {

    public static Stream<Arguments> getAllProfiles_thenReturnListDto() {
        return Stream.of(
                Arguments.of(
                        Collections.singletonList(TestProfileProvider.dto()),
                        Collections.singletonList(TestProfileProvider.entity(1L))
                ),
                Arguments.of(
                        Arrays.asList(
                                TestProfileProvider.dto(),
                                TestProfileProvider.dto()
                        ),
                        Arrays.asList(
                                TestProfileProvider.entity(1L),
                                TestProfileProvider.entity(2L)
                        )
                )
        );
    }

    public static Stream<Arguments> getTestProfileById_thenReturnDto() {
        return Stream.of(
                Arguments.of(
                        TestProfileProvider.dto(),
                        TestProfileProvider.entity(1L)
                )
        );
    }
    public static Stream<Arguments> createTestProfile_thenReturnDto() {
        return Stream.of(
                Arguments.of(
                        TestProfileProvider.dto(),
                        TestProfileProvider.entity(1L)
                )
        );
    }
    public static Stream<Arguments> updateTestProfile_thenReturnDto() {
        return Stream.of(
                Arguments.of(
                        TestProfileProvider.dto(),
                        TestProfileProvider.entity(1L)
                )
        );
    }
}
