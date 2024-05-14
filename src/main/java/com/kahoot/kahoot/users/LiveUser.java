package com.kahoot.kahoot.users;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class LiveUser {
  private String username;
  private String imageUrl;
  private List<Answer> answers;

  public LiveUser getByUsername(String username) {
    return this;
  }
}
