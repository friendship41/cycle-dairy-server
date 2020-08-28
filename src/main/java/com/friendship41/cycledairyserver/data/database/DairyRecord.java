package com.friendship41.cycledairyserver.data.database;

import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "dairy_record")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DairyRecord {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer dairyRecordSeq;
  @NotNull
  private String memberId;
  @NotNull
  private String startLocationName;
  @NotNull
  private Double startLocationLat;
  @NotNull
  private Double startLocationLon;
  @NotNull
  private String endLocationName;
  @NotNull
  private Double endLocationLat;
  @NotNull
  private Double endLocationLon;
  private Date dairyDate;

  @PrePersist
  public void prePersist() {
    this.dairyDate = this.dairyDate == null ? new Date() : this.dairyDate;
  }
}
