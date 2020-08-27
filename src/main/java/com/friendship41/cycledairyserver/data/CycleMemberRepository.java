package com.friendship41.cycledairyserver.data;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CycleMemberRepository extends JpaRepository<CycleMember, String> {
}
