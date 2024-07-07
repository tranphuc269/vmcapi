package com.vai.vmcapi.repo.infra;

import java.io.InputStream;

public interface IS3Repository {
    void putObject(InputStream data, String src, String contentType) throws Exception;

    byte[] getObject(String src) throws Exception;

}
