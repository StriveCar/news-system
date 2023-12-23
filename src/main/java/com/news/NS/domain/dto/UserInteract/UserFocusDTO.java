package com.news.NS.domain.dto.UserInteract;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.sql.Timestamp;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserFocusDTO {
    @NotNull
    private Integer userId;
    @NotNull
    private Integer focusedUserId;

    private Timestamp FocusTime;
}
