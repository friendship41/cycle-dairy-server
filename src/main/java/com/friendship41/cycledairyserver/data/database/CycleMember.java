package com.friendship41.cycledairyserver.data.database;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "cycle_member")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CycleMember {
  @Id
  private String memberId;
  private String password;
  private String email;
}
