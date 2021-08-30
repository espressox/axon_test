package com.example.axon_test.client.account.dto.command;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.Instant;

/**
 * @author xin
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class QueryAccountCommand {

    @NotBlank
    @NotNull
    private Long id;
    private Instant endDate;
}
