package com.biocv.boot.autoconfigure.data.jpa;

import com.biocv.boot.pojo.BaseBo;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.NoRepositoryBean;

/**
 * 默认的jpa接口
 * @author kai
 * @date 2020/12/18 15:59
 */
@NoRepositoryBean
public interface BaseRepository<T,ID> extends JpaRepository<T,ID>, JpaSpecificationExecutor<T> {

    /**
     * 通用条件分页查询
     *
     * @param condition
     * @param pageIndex
     * @param pageSize
     * @return com.biocv.base.bean.Page
     * @author Tyler.feng@zkteco.com
     * @throws
     * @date  2020-10-23 22:55
     * @since 1.0.0
     */
    Page<T> findByPage(BaseBo condition, int pageIndex, int pageSize);

}
