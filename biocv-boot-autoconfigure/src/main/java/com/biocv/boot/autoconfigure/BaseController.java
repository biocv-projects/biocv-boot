package com.biocv.boot.autoconfigure;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * Base Controller
 *
 * @author Tyler.Feng
 * @date 2021-01-13 15:38
 * @since 1.0.0
 */
public abstract class BaseController {

    /**
     * 获取页码
     *
     * @return int
     * @throws
     * @author Tyler.Feng
     * @date  2021-01-13 15:38
     * @since 1.0.0
     */
    protected int getPageIndex() {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder
                .getRequestAttributes()).getRequest();
        String posStart = request.getParameter("pageIndex");
        int pageSize = getPageSize();
        int pageNo = posStart == null ? 0 : Integer.valueOf(posStart) / pageSize;
        return pageNo;
    }

    /**
     * 每页大小
     *
     * @return int
     * @throws
     * @author Tyler.Feng
     * @date  2021-01-13 15:38
     * @since 1.0.0
     */
    protected int getPageSize() {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder
                .getRequestAttributes()).getRequest();
        String count = request.getParameter("pageSize");
        int pageSize = count == null ? 50 : Integer.valueOf(count);
        return pageSize;
    }
}
