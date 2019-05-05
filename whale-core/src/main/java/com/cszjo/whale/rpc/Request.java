package com.cszjo.whale.rpc;

public interface Request {

    String getInterfaceName();

    String getMethodName();

    String getArgumentsDesc();

    Object[] getArguments();
}
