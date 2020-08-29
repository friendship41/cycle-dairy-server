package com.friendship41.cycledairyserver.data.database;

import java.util.UUID;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "access_token_store")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AccessTokenStore {
  @Id
  @Column(name = "token_id")
  private String tokenId;
  @Column(name = "access_token")
  private String accessToken;
  @Column(name = "member_id")
  private String memberId;

  @PrePersist
  public void prePersist() {
    this.tokenId = this.tokenId == null ? UUID.randomUUID().toString() : this.tokenId;
  }
}
