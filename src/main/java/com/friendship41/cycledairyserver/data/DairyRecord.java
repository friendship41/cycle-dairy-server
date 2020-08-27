package com.friendship41.cycledairyserver.data;

import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "dairy_record")
@Getter
@Setter
public class DairyRecord {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private int dairyRecordSeq;
  @NotNull
  private String memberId;
  @NotNull
  private String startLocationName;
  @NotNull
  private double startLocationLat;
  @NotNull
  private double startLocationLon;
  @NotNull
  private String endLocationName;
  @NotNull
  private double endLocationLat;
  @NotNull
  private double endLocationLon;
  @NotNull
  private Date dairyDate;
}
