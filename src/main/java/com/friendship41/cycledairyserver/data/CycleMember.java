package com.friendship41.cycledairyserver.data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "cycle_member")
@Data
public class CycleMember {
  @Id
  private String memberId;
  private String password;
  private String email;
}
