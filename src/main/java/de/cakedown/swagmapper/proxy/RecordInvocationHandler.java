package de.cakedown.swagmapper.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

public class RecordInvocationHandler implements InvocationHandler {

    private Object target;

    public RecordInvocationHandler(Object target) {
        this.target = target;
    }

    private final Map<String, Method> methods = new HashMap<>();

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            Object result = methods.get(method.getName()).invoke(target, args);
            return result;
    }
}
