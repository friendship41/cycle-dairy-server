package com.friendship41.cycledairyserver.data;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DairyRecordRepository extends JpaRepository<DairyRecord, Integer> {
  List<DairyRecord> findAllByMemberId(String memberId);
}
