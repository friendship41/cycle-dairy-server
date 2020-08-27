package com.friendship41.cycledairyserver.service;

import com.friendship41.cycledairyserver.data.DairyRecord;

public interface DairyRecordService {
  DairyRecord insertDairyRecord(DairyRecord dairyRecord);
  DairyRecord deleteDairyRecord(DairyRecord dairyRecord);
  DairyRecord selectDairyRecord(DairyRecord dairyRecord);
  DairyRecord selectDairyRecordList();
  DairyRecord updateDairyRecord(DairyRecord dairyRecord);
}
