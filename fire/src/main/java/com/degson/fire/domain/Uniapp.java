package com.degson.fire.domain;

import org.springframework.web.multipart.MultipartFile;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Uniapp {
    @JsonProperty("qrContent")
    private String qrContent;
    @JsonProperty("image")
    private String image;
}
