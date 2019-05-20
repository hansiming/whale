package com.cszjo.whale.core.rpc;

public interface Request {

    String getInterfaceName();

    String getMethodName();

    String getArgumentsDesc();

    Object[] getArguments();
}
