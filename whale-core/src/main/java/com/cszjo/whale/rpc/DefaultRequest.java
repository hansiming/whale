package com.cszjo.whale.rpc;

public class DefaultRequest implements Request {

    private String interfaceName;
    private String methodName;
    private String argumentsDesc;
    private Object[] arguments;

    private DefaultRequest(Builder builder) {
        interfaceName = builder.interfaceName;
        methodName = builder.methodName;
        argumentsDesc = builder.argumentsDesc;
        arguments = builder.arguments;
    }

    @Override
    public String getInterfaceName() {
        return this.interfaceName;
    }

    @Override
    public String getMethodName() {
        return this.methodName;
    }

    @Override
    public String getArgumentsDesc() {
        return this.argumentsDesc;
    }

    @Override
    public Object[] getArguments() {
        return arguments;
    }

    public static final class Builder {
        private String interfaceName;
        private String methodName;
        private String argumentsDesc;
        private Object[] arguments;

        public Builder() {
        }

        public Builder interfaceName(String val) {
            interfaceName = val;
            return this;
        }

        public Builder methodName(String val) {
            methodName = val;
            return this;
        }

        public Builder argumentsDesc(String val) {
            argumentsDesc = val;
            return this;
        }

        public Builder arguments(Object[] val) {
            arguments = val;
            return this;
        }

        public DefaultRequest build() {
            return new DefaultRequest(this);
        }
    }
}
