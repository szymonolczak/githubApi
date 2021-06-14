package com.szymon.empik.empik_integration.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonView;
import lombok.*;

import java.util.Date;

@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class User {
    @JsonView(GithubUserView.UserRepresentative.class)
    private Long id;
    @JsonView(GithubUserView.UserRepresentative.class)
    private String login;
    @JsonView(GithubUserView.UserRepresentative.class)
    private String name;
    @JsonView(GithubUserView.UserRepresentative.class)
    private String type;
    @JsonView(GithubUserView.UserRepresentative.class)
    @JsonProperty("avatar_url")
    private String avatarUrl;
    @JsonView(GithubUserView.UserRepresentative.class)
    @JsonProperty("created_at")
    private Date createdAt;
    private Integer followers;
    @JsonProperty("public_repos")
    private Integer publicRepos;
    @JsonView(GithubUserView.UserRepresentative.class)
    private Double userCalculation;
}
