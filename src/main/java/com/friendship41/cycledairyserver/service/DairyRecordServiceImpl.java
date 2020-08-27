package com.friendship41.cycledairyserver.service;

import com.friendship41.cycledairyserver.data.DairyRecord;
import com.friendship41.cycledairyserver.data.DairyRecordRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("DairyRecordService")
public class DairyRecordServiceImpl implements DairyRecordService {
  @Autowired
  private DairyRecordRepository dairyRecordRepository;

  @Override
  public DairyRecord insertDairyRecord(final DairyRecord dairyRecord) {
    return dairyRecordRepository.save(dairyRecord);
  }

  @Override
  public DairyRecord deleteDairyRecord(final DairyRecord dairyRecord) {
    return null;
  }

  @Override
  public DairyRecord selectDairyRecord(final DairyRecord dairyRecord) {
    if (dairyRecord.getDairyRecordSeq() > 0) {
      Optional<DairyRecord> result = dairyRecordRepository.findById(dairyRecord.getDairyRecordSeq());
      if (result.isPresent()) {
        return result.get();
      }
    } else if (dairyRecord.getMemberId() != null) {
      List<DairyRecord> dairyRecordList = dairyRecordRepository.findAllByMemberId(dairyRecord.getMemberId());
      if (dairyRecordList.size() > 0) {
        return dairyRecordList.get(0);
      }
    }
    return null;
  }

  @Override
  public DairyRecord selectDairyRecordList() {
    return null;
  }

  @Override
  public DairyRecord updateDairyRecord(final DairyRecord dairyRecord) {
    return null;
  }
}
