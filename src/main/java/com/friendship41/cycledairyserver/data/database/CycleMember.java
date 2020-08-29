package com.friendship41.cycledairyserver.data.database;

import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
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
  @NotNull
  private String password;
  @NotNull
  private String email;
  private Date registerDate;
  private Date lastLoginDate;

  @PrePersist
  public void prePersist() {
    this.lastLoginDate = this.lastLoginDate == null ? new Date() : this.lastLoginDate;
  }
}
