package com.szymon.empik.empik_integration.model;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity(name = "user_stats")
@Getter
@Setter
@ToString
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
public class UserStats {
    @Id
    private String login;
    @Column(name = "request_count", nullable = false)
    private Long requestCount;
}
