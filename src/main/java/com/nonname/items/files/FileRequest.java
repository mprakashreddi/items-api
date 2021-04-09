package com.nonname.items.files;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FileRequest {
    public String fileDirectoryPath;
    public String method;
    public String[] files;
}
