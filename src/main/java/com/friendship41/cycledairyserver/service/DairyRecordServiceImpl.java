package com.friendship41.cycledairyserver.service;

import com.friendship41.cycledairyserver.data.database.DairyRecord;
import com.friendship41.cycledairyserver.data.database.DairyRecordRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service("DairyRecordService")
public class DairyRecordServiceImpl implements DairyRecordService {
  private final DairyRecordRepository dairyRecordRepository;

  @Autowired
  public DairyRecordServiceImpl(final DairyRecordRepository dairyRecordRepository) {
    this.dairyRecordRepository = dairyRecordRepository;
  }


  @Override
  public DairyRecord insertDairyRecord(final DairyRecord dairyRecord) {
    return dairyRecordRepository.save(dairyRecord);
  }

  @Override
  public void deleteDairyRecord(final DairyRecord dairyRecord) {
    if (dairyRecord.getDairyRecordSeq() != null) {
      dairyRecordRepository.deleteById(dairyRecord.getDairyRecordSeq());
    } else if (dairyRecord.getMemberId() != null) {
      dairyRecordRepository.deleteAllByMemberId(dairyRecord.getMemberId());
    }
  }

  @Override
  public DairyRecord selectDairyRecord(final DairyRecord dairyRecord) {
    if (dairyRecord.getDairyRecordSeq() != null) {
      Optional<DairyRecord> result = dairyRecordRepository.findById(dairyRecord.getDairyRecordSeq());
      if (result.isPresent()) {
        return result.get();
      }
    } else if (dairyRecord.getMemberId() != null) {
      List<DairyRecord> recordList = this.selectRecordListByMemberId(dairyRecord.getMemberId());
      return recordList != null ? recordList.get(0) : null;
    }
    return null;
  }

  @Override
  public List<DairyRecord> selectDairyRecordList(DairyRecord dairyRecord) {
    return this.selectRecordListByMemberId(dairyRecord.getMemberId());
  }

  @Override
  public DairyRecord updateDairyRecordStartElements(final DairyRecord dairyRecord) {
    Optional<DairyRecord> optionalDairyRecord = dairyRecordRepository.findById(dairyRecord.getDairyRecordSeq());
    if (!optionalDairyRecord.isPresent()) {
      return null;
    }
    if (dairyRecord.getStartLocationName() != null) {
      optionalDairyRecord.get().setStartLocationName(dairyRecord.getStartLocationName());
    }
    if (dairyRecord.getStartLocationLat() != null) {
      optionalDairyRecord.get().setStartLocationLat(dairyRecord.getStartLocationLat());
    }
    if (dairyRecord.getStartLocationLon() != null) {
      optionalDairyRecord.get().setStartLocationLon(dairyRecord.getStartLocationLon());
    }
    return optionalDairyRecord.get();
  }

  @Override
  public DairyRecord updateDairyRecordEndElements(final DairyRecord dairyRecord) {
    Optional<DairyRecord> optionalDairyRecord = dairyRecordRepository.findById(dairyRecord.getDairyRecordSeq());
    if (!optionalDairyRecord.isPresent()) {
      return null;
    }
    if (dairyRecord.getEndLocationName() != null) {
      optionalDairyRecord.get().setEndLocationName(dairyRecord.getEndLocationName());
    }
    if (dairyRecord.getEndLocationLat() != null) {
      optionalDairyRecord.get().setEndLocationLat(dairyRecord.getEndLocationLat());
    }
    if (dairyRecord.getEndLocationLon() != null) {
      optionalDairyRecord.get().setEndLocationLon(dairyRecord.getEndLocationLon());
    }
    return optionalDairyRecord.get();
  }

  private List<DairyRecord> selectRecordListByMemberId(String memberId) {
    List<DairyRecord> dairyRecordList = dairyRecordRepository.findAllByMemberId(memberId);
    if (dairyRecordList.size() > 0) {
      return dairyRecordList;
    } else {
      return null;
    }
  }
}
