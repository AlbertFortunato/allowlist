package org.com.allowlist.repository;

import org.com.allowlist.entity.ClienteAllowList;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EntidadeRepository extends JpaRepository<ClienteAllowList, Integer> {
}
