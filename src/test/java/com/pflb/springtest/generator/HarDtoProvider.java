package com.pflb.springtest.generator;

import com.pflb.springtest.model.dto.har.*;
import org.springframework.http.HttpMethod;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class HarDtoProvider {

    public static HarDto harDto(String url, HarPostDataDto postDataDto) {
        return new HarDto(
                new HarLogDto(
                        Collections.singletonList(
                                new HarEntryDto(
                                        new HarRequestDto(
                                                url,
                                                Collections.emptyList(),
                                                postDataDto,
                                                HttpMethod.GET
                                        )

                                )
                        )
                )
        );
    }

    public static HarDto harDto(int paramsCount, String text) {
        return HarDto.builder()
                .log(
                        HarLogDto.builder()
                                .browser(
                                        HarBrowserDto.builder()
                                                .name("firefox")
                                                .version("2.7.4.1")
                                        .build()
                                )
                                .version("1.2")
                                .entries(
                                        Arrays.asList(
                                                HarEntryDto.builder()
                                                        .request(
                                                                HarRequestDto.builder()
                                                                        .headers(Collections.emptyList())
                                                                        .method(HttpMethod.GET)
                                                                        .url("ya.ru")
                                                                        .postData(
                                                                                HarPostDataDto.builder()
                                                                                        .text(text)
                                                                                        .params(harParamsDtoList(paramsCount))
                                                                                        .build()
                                                                        )
                                                                .build()
                                                        )
                                                        .build()
                                        )
                                )
                        .build()
                )
                .build();
    }

    public static HarPostDataDto harPostDataDto (List<HarParamsDto> harParamsDtos) {
        return HarPostDataDto.builder().text("text").params(harParamsDtos).build();
    }

    public static List<HarParamsDto> harParamsDtoList(int count) {
        return Stream.iterate(0, i -> i + 1)
                .limit(count)
                .map(i -> HarParamsDto.builder().name("nave" + i).value("value" + i).build())
                .collect(Collectors.toList());
    }

}
