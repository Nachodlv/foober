package model;

import java.io.Serializable;

public interface Model <T extends Serializable, K>{
    public T getKey();
    public Class<K> returnClass();
}
