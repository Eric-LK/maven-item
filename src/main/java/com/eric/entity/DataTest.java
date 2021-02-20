package com.eric.entity;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Tolerate;

/**
 * @author liuBing
 */
@Builder
@Data

public class DataTest {
    private String id;

    @Tolerate
    public DataTest() {}
}
