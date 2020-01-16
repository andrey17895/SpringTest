package com.pflb.springtest.service;

import com.pflb.springtest.model.dto.profile.TestProfileDto;
import com.pflb.springtest.model.entity.TestProfile;
import com.pflb.springtest.model.exception.ApplicationException;
import com.pflb.springtest.model.exception.CustomExceptionType;
import com.pflb.springtest.repository.TestProfileRepository;
import com.pflb.springtest.service.impl.TestProfileService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class TestProfileServiceTest {

    @Mock
    private TestProfileRepository testProfileRepository;

    @Mock
    private ModelMapper modelMapper;

    @InjectMocks
    private TestProfileService testProfileService;


    @ParameterizedTest
    @DisplayName("Get all profiles")
    @MethodSource("com.pflb.springtest.argument.TestProfileServiceArgs#getAllProfiles_thenReturnListDto")
    void getAllProfiles_thenReturnListDto_whenExists(List<TestProfileDto> testProfileDtos, List<TestProfile> testProfiles) {

        when(testProfileRepository.findAll()).thenReturn(testProfiles);
        when(modelMapper.map(any(), eq(new TypeToken<List<TestProfileDto>>() {}.getType()))).thenReturn(testProfileDtos);

        assertEquals(testProfileDtos, testProfileService.getAllProfiles());
    }

    @ParameterizedTest
    @DisplayName("Get test profile by id. Exists")
    @MethodSource("com.pflb.springtest.argument.TestProfileServiceArgs#getTestProfileById_thenReturnDto")
    void getTestProfileById_thenReturnDto_whenExists(TestProfileDto testProfileDto, TestProfile testProfile) {

        when(testProfileRepository.findById(1L)).thenReturn(Optional.of(testProfile));
        when(modelMapper.map(any(), eq(TestProfileDto.class))).thenReturn(testProfileDto);

        assertEquals(testProfileDto, testProfileService.getTestProfileById(1L));
    }

    @Test
    @DisplayName("Get test profile by id. Not exists")
    void getTestProfileById_thenThrowResuorceNotFound_whenTestProfileNotExists() {

        when(testProfileRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(ApplicationException.class, () -> testProfileService.getTestProfileById(1L));
    }

    @ParameterizedTest
    @DisplayName("Create test profile")
    @MethodSource("com.pflb.springtest.argument.TestProfileServiceArgs#createTestProfile_thenReturnDto")
    void createTestProfile_thenReturnDto_whenValid(TestProfileDto testProfileDto, TestProfile testProfile) {

        when(modelMapper.map(any(), eq(TestProfile.class))).thenReturn(testProfile);
        when(modelMapper.map(any(), eq(TestProfileDto.class))).thenReturn(testProfileDto);
        when(testProfileRepository.save(any())).thenReturn(testProfile);

        TestProfileDto actualDto = testProfileService.createTestProfile(testProfileDto);

        assertEquals(testProfileDto, actualDto);
    }

    @ParameterizedTest
    @DisplayName("Update test profile. Exists")
    @MethodSource("com.pflb.springtest.argument.TestProfileServiceArgs#updateTestProfile_thenReturnDto")
    void updateTestProfile_thenReturnDto_whenExists(TestProfileDto testProfileDto, TestProfile testProfile) {

        when(testProfileRepository.existsById(1L)).thenReturn(true);
        when(modelMapper.map(any(), eq(TestProfile.class))).thenReturn(testProfile);
        when(modelMapper.map(any(), eq(TestProfileDto.class))).thenReturn(testProfileDto);

        TestProfileDto actualDto = testProfileService.updateTestProfile(testProfileDto, 1L);

        assertEquals(testProfileDto, actualDto);
    }

    @Test
    @DisplayName("Update test profile. Not exists")
    void updateTestProfile_thenThrowResourceNotFound_whenTestProfileNotExists() {

        when(testProfileRepository.existsById(1L)).thenReturn(false);

        ApplicationException ex = assertThrows(
                ApplicationException.class,
                () -> testProfileService.updateTestProfile(new TestProfileDto(), 1L)
        );

        assertEquals(CustomExceptionType.TEST_PROFILE_NOT_FOUND, ex.getType());
    }

    @Test
    @DisplayName("Delete all")
    void deleteAll() {

        doNothing().when(testProfileRepository).deleteAll();

        testProfileService.deleteAll();

        verify(testProfileRepository).deleteAll();
    }
}