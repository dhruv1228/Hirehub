package com.hirehub.common.cloudinary;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CloudinaryUploadResponse {

    private String url;
    private String publicId;
}