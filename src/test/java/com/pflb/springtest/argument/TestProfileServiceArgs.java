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
                        Collections.singletonList(TestProfileProvider.entity_id_1())
                ),
                Arguments.of(
                        Arrays.asList(
                                TestProfileProvider.dto(),
                                TestProfileProvider.dto()
                        ),
                        Arrays.asList(
                                TestProfileProvider.entity_id_1(),
                                TestProfileProvider.entity_id_2()
                        )
                )
        );
    }

    public static Stream<Arguments> getTestProfileById_thenReturnDto() {
        return Stream.of(
                Arguments.of(
                        TestProfileProvider.dto(),
                        TestProfileProvider.entity_id_1()
                )
        );
    }
    public static Stream<Arguments> createTestProfile_thenReturnDto() {
        return Stream.of(
                Arguments.of(
                        TestProfileProvider.dto(),
                        TestProfileProvider.entity_id_1()
                )
        );
    }
    public static Stream<Arguments> updateTestProfile_thenReturnDto() {
        return Stream.of(
                Arguments.of(
                        TestProfileProvider.dto(),
                        TestProfileProvider.entity_id_1()
                )
        );
    }
}
