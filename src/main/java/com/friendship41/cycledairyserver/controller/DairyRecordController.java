package com.friendship41.cycledairyserver.controller;

import com.friendship41.cycledairyserver.data.database.DairyRecord;
import com.friendship41.cycledairyserver.data.http.HttpResponseBody;
import com.friendship41.cycledairyserver.data.type.ReturnType;
import com.friendship41.cycledairyserver.service.DairyRecordService;
import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/record")
public class DairyRecordController {
  private final DairyRecordService dairyRecordService;

  @Autowired
  public DairyRecordController(DairyRecordService dairyRecordService) {
    this.dairyRecordService = dairyRecordService;
  }


  @PostMapping
  public Object postRecord(@RequestBody @Valid DairyRecord dairyRecord) {
    return dairyRecordService.insertDairyRecord(dairyRecord);
  }

  @GetMapping
  public Object selectRecord(
      @RequestParam(required = false) Integer dairyRecordSeq,
      @RequestParam(required = false) String memberId,
      @RequestParam ReturnType returnType) {
    switch (returnType) {
      case ONE:
        DairyRecord dairyRecord = dairyRecordService.selectDairyRecord(DairyRecord.builder()
            .dairyRecordSeq(dairyRecordSeq)
            .memberId(memberId)
            .build());
        return dairyRecord != null ?
            HttpResponseBody.createSuccessResponseBody(dairyRecord)
              : HttpResponseBody.builder()
                .responseCode(HttpResponseBody.RESPONSE_CODE_NO_RESULT)
                .responseMessage(HttpResponseBody.RESPONSE_MESSAGE_NO_RESULT)
                .build();
      case LIST:
        List<DairyRecord> dairyRecordList = dairyRecordService.selectDairyRecordList(DairyRecord.builder()
            .memberId(memberId)
            .build());
        return dairyRecordList != null ?
            HttpResponseBody.createSuccessResponseBody(dairyRecordList)
              : HttpResponseBody.builder()
                .responseCode(HttpResponseBody.RESPONSE_CODE_NO_RESULT)
                .responseMessage(HttpResponseBody.RESPONSE_MESSAGE_NO_RESULT)
                .build();
      default:
        return HttpResponseBody.createDefaultFailBody();
    }
  }

  @PutMapping
  public Object updateRecord(@RequestBody DairyRecord dairyRecord) {
    if (dairyRecord.getDairyRecordSeq() == null) {
      return HttpResponseBody.createBadRequestBody();
    }
    boolean resultFlag = true;
    DairyRecord resultData = null;
    if (dairyRecord.getStartLocationName() != null
        && dairyRecord.getStartLocationLon() != null
        && dairyRecord.getStartLocationLat() != null) {
      resultData = dairyRecordService.updateDairyRecordStartElements(dairyRecord);
      if (resultData == null) {
        resultFlag = false;
      }
    }
    if (dairyRecord.getEndLocationName() != null
        && dairyRecord.getEndLocationLat() != null
        && dairyRecord.getStartLocationLon() != null) {
      resultData = dairyRecordService.updateDairyRecordEndElements(dairyRecord);
      if (resultData == null) {
        resultFlag = false;
      }
    }

    if (!resultFlag) {
      return HttpResponseBody.builder()
          .responseCode(HttpResponseBody.RESPONSE_CODE_NO_RESULT)
          .responseMessage(HttpResponseBody.RESPONSE_MESSAGE_NO_RESULT)
          .build();
    }

    return HttpResponseBody.createSuccessResponseBody(resultData);
  }

  @DeleteMapping
  public Object deleteDairyRecord(@RequestParam Integer dairyRecordSeq) {
    dairyRecordService.deleteDairyRecord(DairyRecord.builder()
        .dairyRecordSeq(dairyRecordSeq)
        .build());
    return HttpResponseBody.createSuccessResponseBody(null);
  }
}
