package com.biocv.boot.vis.cmd.dao;

import com.biocv.boot.autoconfigure.data.jpa.BaseRepository;
import com.biocv.boot.vis.cmd.model.Cmd;

/**
 * cmd dao
 *
 * @author Tyler.Feng
 * @date 2021-01-29 14:42
 * @since 1.0.0
 */
public interface CmdDao extends BaseRepository<Cmd,String> {
}
