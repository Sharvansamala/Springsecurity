package org.spring.springjpa.securityspring.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.spring.springjpa.securityspring.entity.PostEntity;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PostDTO {
    private String title;
    private String description;

    private PostEntity postEntity;

}
