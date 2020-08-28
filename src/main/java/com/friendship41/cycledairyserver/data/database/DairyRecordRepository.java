package com.friendship41.cycledairyserver.data.database;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DairyRecordRepository extends JpaRepository<DairyRecord, Integer> {
  List<DairyRecord> findAllByMemberId(String memberId);
  void deleteAllByMemberId(String memberId);
}
