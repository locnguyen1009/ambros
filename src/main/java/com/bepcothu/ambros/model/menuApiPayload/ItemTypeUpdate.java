package com.bepcothu.ambros.model.menuApiPayload;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.AnyKeyJavaClass;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ItemTypeUpdate {
  private String category;
}
