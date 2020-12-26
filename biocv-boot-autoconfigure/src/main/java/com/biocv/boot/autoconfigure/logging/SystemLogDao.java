package com.biocv.boot.autoconfigure.logging;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

/**
 * dao
 *
 * @author kai
 * @date 2020/12/25 20:55
 */
@Transactional
public interface SystemLogDao extends JpaRepository<SystemLog,String> {
}
