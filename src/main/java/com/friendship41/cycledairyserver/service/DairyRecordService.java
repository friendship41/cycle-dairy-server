package com.friendship41.cycledairyserver.service;

import com.friendship41.cycledairyserver.data.database.DairyRecord;
import java.util.List;

public interface DairyRecordService {
  DairyRecord insertDairyRecord(DairyRecord dairyRecord);
  void deleteDairyRecord(DairyRecord dairyRecord);
  DairyRecord selectDairyRecord(DairyRecord dairyRecord);
  List<DairyRecord> selectDairyRecordList(DairyRecord dairyRecord);
  DairyRecord updateDairyRecordStartElements(DairyRecord dairyRecord);
  DairyRecord updateDairyRecordEndElements(DairyRecord dairyRecord);
}
