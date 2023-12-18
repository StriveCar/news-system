package com.news.NS.domain.dto.UserInteract;

import io.swagger.models.auth.In;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.sql.Timestamp;
import java.util.Random;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CollectNewsDTO {
    @NotNull
    private Integer userId;
    @NotNull
    private Integer newsId;
    @NotNull
    private Timestamp collectionTime;
}
