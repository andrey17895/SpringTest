package com.pflb.springtest.service.provider;

import com.pflb.springtest.generator.TestProfileProvider;
import org.junit.jupiter.params.provider.Arguments;

import java.util.Arrays;
import java.util.Collections;
import java.util.stream.Stream;

public class TestProfileArgProvider {

    public static Stream<Arguments> getAllProfiles_Exists() {
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
    public static Stream<Arguments> getTestProfileById_Exists() {
        return Stream.of(
                Arguments.of(
                        TestProfileProvider.dto(),
                        TestProfileProvider.entity(1L)
                )
        );
    }
    public static Stream<Arguments> createTestProfile() {
        return Stream.of(
                Arguments.of(
                        TestProfileProvider.dto(),
                        TestProfileProvider.entity(1L)
                )
        );
    }
    public static Stream<Arguments> updateTestProfile_Exists() {
        return Stream.of(
                Arguments.of(
                        TestProfileProvider.dto(),
                        TestProfileProvider.entity(1L)
                )
        );
    }
}
