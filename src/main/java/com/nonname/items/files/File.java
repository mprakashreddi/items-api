package com.nonname.items.files;

import com.nonname.items.client.database.Item;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class File {
    String name;
    String id;
    String type;
    Records[] records;
}

@Getter
@Setter
class Records{
    Item[] items;
}