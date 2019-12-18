package com.pflb.springtest.service;

import com.pflb.springtest.model.dto.profile.RequestDto;
import com.pflb.springtest.model.entity.Request;
import com.pflb.springtest.model.entity.TestProfile;
import com.pflb.springtest.model.exception.CustomExceptionType;
import com.pflb.springtest.model.exception.ResourceNotFoundException;
import com.pflb.springtest.repository.RequestRepository;
import com.pflb.springtest.repository.TestProfileRepository;
import com.pflb.springtest.service.impl.RequestService;
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
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class RequestServiceTest {

    @Mock
    private RequestRepository requestRepository;

    @Mock
    private TestProfileRepository testProfileRepository;

    @Mock
    private ModelMapper modelMapper;

    @InjectMocks
    private RequestService requestService;

    @ParameterizedTest
    @DisplayName("Get all. Ok")
    @MethodSource("com.pflb.springtest.service.provider.RequestArgProvider#getAllRequests_Ok")
    void getAllRequests_Ok(List<RequestDto> requestsDto, List<Request> requests) {

        when(requestRepository.findAll())
                .thenReturn(requests);
        when(modelMapper.map(eq(requests), eq(new TypeToken<List<RequestDto>>() {}.getType())))
                .thenReturn(requestsDto);

        assertEquals(requestsDto, requestService.getAllRequests());
    }

    @ParameterizedTest
    @DisplayName("Get all by test profile id. Empty")
    @MethodSource("com.pflb.springtest.service.provider.RequestArgProvider#getAllRequestsByTestProfileId")
    void getAllRequestsByTestProfileId(List<RequestDto> requestsDto, List<Request> requests) {
        when(requestRepository.findByTestProfileId(anyLong())).thenReturn(requests);
        when(modelMapper.map(eq(requests), eq(new TypeToken<List<RequestDto>>() {}.getType())))
                .thenReturn(requestsDto);
        assertEquals(requestsDto, requestService.getAllRequests(1L));
    }


    @Test
    @DisplayName("Get request by id. Test profile not exists.")
    void getRequestById_testProfileNotExists() {
        when(testProfileRepository.existsById(anyLong())).thenReturn(false);

        ResourceNotFoundException ex = assertThrows(
                ResourceNotFoundException.class,
                () -> requestService.getRequestById(1L, 1L)
        );
        assertEquals(CustomExceptionType.TEST_PROFILE_NOT_FOUND, ex.getType());

    }

    @Test
    @DisplayName("Get request by id. Request not exists.")
    void getRequestById_RequestNotExists() {
        when(testProfileRepository.existsById(anyLong())).thenReturn(true);
        when(requestRepository.findByIdAndTestProfileId(anyLong(), anyLong()))
                .thenReturn(Optional.empty());

        ResourceNotFoundException ex = assertThrows(
                ResourceNotFoundException.class,
                () -> requestService.getRequestById(1L, 1L)
        );
        assertEquals(CustomExceptionType.REQUEST_NOT_FOUND, ex.getType());
    }

    @ParameterizedTest
    @DisplayName("Get request by id. Ok")
    @MethodSource("com.pflb.springtest.service.provider.RequestArgProvider#getRequestById_Ok")
    void getRequestById_Ok(RequestDto requestDto, Request request) {
        when(testProfileRepository.existsById(anyLong())).thenReturn(true);
        when(requestRepository.findByIdAndTestProfileId(anyLong(), anyLong()))
                .thenReturn(Optional.of(request));
        when(modelMapper.map(eq(request), eq(RequestDto.class)))
                .thenReturn(requestDto);

        assertEquals(requestDto, requestService.getRequestById(1L, 1L));
    }

    @Test
    @DisplayName("Delete all from repo")
    void deleteAll() {
        requestService.deleteAll();
        verify(requestRepository).deleteAll();
    }

    @ParameterizedTest
    @DisplayName("Create. Ok")
    @MethodSource("com.pflb.springtest.service.provider.RequestArgProvider#createRequest_Ok")
    void createRequest_Ok(RequestDto requestDto, Request request) {
        TestProfile testProfile = new TestProfile();
        when(modelMapper.map(eq(requestDto), eq(Request.class)))
                .thenReturn(request);
        when(testProfileRepository.findById(1L))
                .thenReturn(Optional.of(testProfile));
        when(requestRepository.save(any(Request.class)))
                .thenReturn(request);
        when(modelMapper.map(eq(request), eq(RequestDto.class)))
                .thenReturn(requestDto);

        assertEquals(requestDto, requestService.createRequest(1L, requestDto));
    }

    @Test
    @DisplayName("Create. No test profile")
    void createRequest_TestProfileNotFound() {
        when(testProfileRepository.findById(1L)).thenReturn(Optional.empty());

        ResourceNotFoundException ex = assertThrows(
                ResourceNotFoundException.class,
                () -> requestService.createRequest(1L, new RequestDto())
        );
        assertEquals(CustomExceptionType.TEST_PROFILE_NOT_FOUND, ex.getType());
    }

    @Test
    @DisplayName("Create. No test profile")
    void createRequest_RequestNotFound() {
        when(testProfileRepository.findById(1L)).thenReturn(Optional.empty());

        ResourceNotFoundException ex = assertThrows(
                ResourceNotFoundException.class,
                () -> requestService.createRequest(1L, new RequestDto())
        );
        assertEquals(CustomExceptionType.TEST_PROFILE_NOT_FOUND, ex.getType());
    }


    @ParameterizedTest
    @DisplayName("Update. Ok")
    @MethodSource("com.pflb.springtest.service.provider.RequestArgProvider#updateRequest_Ok")
    void updateRequest_Ok(RequestDto requestDto, Request request) {

        when(testProfileRepository.findById(1L)).thenReturn(Optional.of(new TestProfile()));
        when(requestRepository.existsById(1L)).thenReturn(true);
        when(requestRepository.save(any(Request.class))).thenReturn(request);
        when(modelMapper.map(eq(requestDto), eq(Request.class)))
                .thenReturn(request);
        when(modelMapper.map(eq(request), eq(RequestDto.class)))
                .thenReturn(requestDto);

        assertEquals(requestDto, requestService.updateRequest(1L, 1L, requestDto));
    }

    @Test
    @DisplayName("Update. No test profile")
    void updateRequest_RequestNotFound() {
        when(testProfileRepository.findById(1L)).thenReturn(Optional.of(new TestProfile()));
        when(requestRepository.existsById(1L)).thenReturn(false);

        ResourceNotFoundException ex = assertThrows(
                ResourceNotFoundException.class,
                () -> requestService.updateRequest(1L, 1L, new RequestDto())
        );
        assertEquals(CustomExceptionType.REQUEST_NOT_FOUND, ex.getType());
    }
}