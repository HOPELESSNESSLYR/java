package com.degson.common.exception.file;

import com.degson.common.exception.base.BaseException;


/**
 * 文件信息异常类
 * 
 * @author degson
 */
public class FileException extends BaseException
{
    private static final long serialVersionUID = 1L;

    public FileException(String code, Object[] args)
    {
        super("file", code, args, null);
    }

}
