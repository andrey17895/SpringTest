package com.pflb.springtest.service;

import com.pflb.springtest.generator.TestGenerator;
import com.pflb.springtest.model.dto.profile.TestProfileDto;
import com.pflb.springtest.model.entity.TestProfile;
import com.pflb.springtest.model.exception.CustomExceptionType;
import com.pflb.springtest.model.exception.ResourceNotFoundException;
import com.pflb.springtest.repository.TestProfileRepository;
import com.pflb.springtest.service.impl.TestProfileService;
import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class TestProfileServiceTest {

    @Mock
    private TestProfileRepository testProfileRepository;

    @Mock
    private ModelMapper modelMapper;

    @InjectMocks
    private TestProfileService testProfileService;

    private static List<TestProfileDto> listTestProfileDtoSingle;
    private static List<TestProfile> listTestProfileSingle;

    @BeforeAll
    static void beforeAll() {
        System.out.println("Hi");
        listTestProfileDtoSingle = Arrays.asList(
                TestGenerator.testProfileDto("ya.ru"),
                TestGenerator.testProfileDto("ya.ru")
        );
        listTestProfileSingle = Arrays.asList(
                TestGenerator.testProfileEntity(1L, "ya.ru", "text", null),
                TestGenerator.testProfileEntity(2L, "bumq.io", "text", null)
        );
    }

    @ParameterizedTest
    @DisplayName("Get all profiles")
    @MethodSource("streamOfTestProfilesLists")
    void getAllProfiles_Exists(List<TestProfileDto> testProfileDtos, List<TestProfile> testProfiles) {
        when(testProfileRepository.findAll()).thenReturn(testProfiles);
        when(modelMapper.map(any(), eq(new TypeToken<List<TestProfileDto>>() {}.getType()))).thenReturn(testProfileDtos);

        assertEquals(testProfileDtos, testProfileService.getAllProfiles());
    }

    @ParameterizedTest
    @DisplayName("Get test profile by id. Exists")
    @MethodSource("streamOfTestProfile")
    void getTestProfileById_Exists(TestProfileDto testProfileDto, TestProfile testProfile) {
        when(testProfileRepository.findById(1L)).thenReturn(Optional.of(TestGenerator.testProfileEntity(1L, "ya.ru", "text", null)));
        when(modelMapper.map(any(), eq(TestProfileDto.class))).thenReturn(TestGenerator.testProfileDto("ya.ru"));

        assertEquals(TestGenerator.testProfileDto("ya.ru"), testProfileService.getTestProfileById(1L));
    }

    @Test
    @DisplayName("Get test profile by id. Not exists")
    void getTestProfileById_NotExists() {
        when(testProfileRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> testProfileService.getTestProfileById(1L));
    }

    @ParameterizedTest
    @DisplayName("Create test profile")
    @MethodSource("streamOfTestProfile")
    void createTestProfile(TestProfileDto testProfileDto, TestProfile testProfile) {
        when(modelMapper.map(any(), eq(TestProfile.class))).thenReturn(testProfile);
        when(modelMapper.map(any(), eq(TestProfileDto.class))).thenReturn(testProfileDto);
        when(testProfileRepository.save(any())).thenReturn(testProfile);

        assertEquals(testProfileDto, testProfileService.createTestProfile(testProfileDto));
    }

    @ParameterizedTest
    @DisplayName("Update test profile. Exists")
    @MethodSource("streamOfTestProfile")
    void updateTestProfile_Exists(TestProfileDto testProfileDto, TestProfile testProfile) {
        when(testProfileRepository.existsById(1L)).thenReturn(true);
        when(modelMapper.map(any(), eq(TestProfile.class))).thenReturn(testProfile);
        when(modelMapper.map(any(), eq(TestProfileDto.class))).thenReturn(testProfileDto);

        assertEquals(testProfileDto, testProfileService.updateTestProfile(testProfileDto, 1L));
    }

    @Test
    @DisplayName("Update test profile. Not exists")
    void updateTestProfile_NotExists() {
        when(testProfileRepository.existsById(1L)).thenReturn(false);

        ResourceNotFoundException ex = assertThrows(
                ResourceNotFoundException.class,
                ()->testProfileService.updateTestProfile(new TestProfileDto(), 1L)
        );
        assertEquals(CustomExceptionType.TEST_PROFILE_NOT_FOUND, ex.getType());
    }

    @Test
    @DisplayName("Delete all")
    void deleteAll() {
        testProfileService.deleteAll();
        verify(testProfileRepository).deleteAll();
    }


    @NotNull
    private static Stream<Arguments> streamOfTestProfilesLists() {
        return Stream.of(
                Arguments.of(
                        listTestProfileDtoSingle.subList(0,1),
                        listTestProfileSingle.subList(0, 1)
                ),
                Arguments.of(
                        listTestProfileDtoSingle,
                        listTestProfileSingle
                ),
                Arguments.of(
                        Collections.emptyList(),
                        Collections.emptyList()
                )
        );
    }

    @NotNull
    private static Stream<Arguments> streamOfTestProfile() {
        return Stream.of(
                Arguments.of(
                        listTestProfileDtoSingle.get(0),
                        listTestProfileSingle.get(0)
                )
        );
    }


}