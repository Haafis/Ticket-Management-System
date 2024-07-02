package com.ticket_management_system.model.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class PagingRequest {
    private int start;
    private int length;
    private int draw;
    private Search search;
}
