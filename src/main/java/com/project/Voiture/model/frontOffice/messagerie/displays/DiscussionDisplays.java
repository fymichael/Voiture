package com.project.Voiture.model.frontOffice.messagerie.displays;

import java.util.List;

import com.project.Voiture.model.frontOffice.messagerie.model.Discussion;

import lombok.*;

@Data @NoArgsConstructor @AllArgsConstructor    
public class DiscussionDisplays {
    List<Discussion> allDiscussion;
    Discussion discussionWithOtherProfil;
}
