package com.demo.redisCache.entities.dtos;

import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Data
public class PageDto<T> {
    private List<T> content;
    private int totalPages;
    private long totalElements;
}
