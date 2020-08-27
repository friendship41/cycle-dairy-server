package com.friendship41.cycledairyserver.controller;

import com.friendship41.cycledairyserver.data.DairyRecord;
import com.friendship41.cycledairyserver.service.DairyRecordService;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/record")
public class DairyRecordController {
  @Autowired
  private DairyRecordService dairyRecordService;

  @PostMapping
  public Object postRecord(@RequestBody @Valid DairyRecord dairyRecord) {
    return dairyRecordService.insertDairyRecord(dairyRecord);
  }
}
